package org.apache.commons.compress.compressors.lz4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.utils.ByteUtils;

/* loaded from: classes9.dex */
public class FramedLZ4CompressorOutputStream extends CompressorOutputStream<OutputStream> {
    private static final byte[] END_MARK = new byte[4];
    private final byte[] blockData;
    private final byte[] blockDependencyBuffer;
    private final org.apache.commons.codec.digest.XXHash32 blockHash;
    private int collectedBlockDependencyBytes;
    private final org.apache.commons.codec.digest.XXHash32 contentHash;
    private int currentIndex;
    private final byte[] oneByte;
    private final Parameters params;

    /* loaded from: classes9.dex */
    public enum BlockSize {
        K64(65536, 4),
        K256(262144, 5),
        M1(1048576, 6),
        M4(4194304, 7);

        private final int index;
        private final int size;

        BlockSize(int size, int index) {
            this.size = size;
            this.index = index;
        }

        int getIndex() {
            return this.index;
        }

        int getSize() {
            return this.size;
        }
    }

    /* loaded from: classes9.dex */
    public static class Parameters {
        public static final Parameters DEFAULT = new Parameters(BlockSize.M4, true, false, false);
        private final BlockSize blockSize;
        private final org.apache.commons.compress.compressors.lz77support.Parameters lz77params;
        private final boolean withBlockChecksum;
        private final boolean withBlockDependency;
        private final boolean withContentChecksum;

        public Parameters(BlockSize blockSize) {
            this(blockSize, true, false, false);
        }

        public Parameters(BlockSize blockSize, boolean withContentChecksum, boolean withBlockChecksum, boolean withBlockDependency) {
            this(blockSize, withContentChecksum, withBlockChecksum, withBlockDependency, BlockLZ4CompressorOutputStream.createParameterBuilder().build());
        }

        public Parameters(BlockSize blockSize, boolean withContentChecksum, boolean withBlockChecksum, boolean withBlockDependency, org.apache.commons.compress.compressors.lz77support.Parameters lz77params) {
            this.blockSize = blockSize;
            this.withContentChecksum = withContentChecksum;
            this.withBlockChecksum = withBlockChecksum;
            this.withBlockDependency = withBlockDependency;
            this.lz77params = lz77params;
        }

        public Parameters(BlockSize blockSize, org.apache.commons.compress.compressors.lz77support.Parameters lz77params) {
            this(blockSize, true, false, false, lz77params);
        }

        public String toString() {
            return "LZ4 Parameters with BlockSize " + this.blockSize + ", withContentChecksum " + this.withContentChecksum + ", withBlockChecksum " + this.withBlockChecksum + ", withBlockDependency " + this.withBlockDependency;
        }
    }

    public FramedLZ4CompressorOutputStream(OutputStream out) throws IOException {
        this(out, Parameters.DEFAULT);
    }

    public FramedLZ4CompressorOutputStream(OutputStream out, Parameters params) throws IOException {
        super(out);
        this.oneByte = new byte[1];
        this.contentHash = new org.apache.commons.codec.digest.XXHash32();
        this.params = params;
        this.blockData = new byte[params.blockSize.getSize()];
        this.blockHash = params.withBlockChecksum ? new org.apache.commons.codec.digest.XXHash32() : null;
        out.write(FramedLZ4CompressorInputStream.LZ4_SIGNATURE);
        writeFrameDescriptor();
        this.blockDependencyBuffer = params.withBlockDependency ? new byte[65536] : null;
    }

    private void appendToBlockDependencyBuffer(byte[] b, int off, int len) {
        int len2 = Math.min(len, this.blockDependencyBuffer.length);
        if (len2 > 0) {
            int keep = this.blockDependencyBuffer.length - len2;
            if (keep > 0) {
                System.arraycopy(this.blockDependencyBuffer, len2, this.blockDependencyBuffer, 0, keep);
            }
            System.arraycopy(b, off, this.blockDependencyBuffer, keep, len2);
            this.collectedBlockDependencyBytes = Math.min(this.collectedBlockDependencyBytes + len2, this.blockDependencyBuffer.length);
        }
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            finish();
        } finally {
            super.close();
        }
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream
    public void finish() throws IOException {
        if (!isFinished()) {
            flushBlock();
            writeTrailer();
            super.finish();
        }
    }

    private void flushBlock() throws IOException {
        if (this.currentIndex != 0) {
            boolean withBlockDependency = this.params.withBlockDependency;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BlockLZ4CompressorOutputStream o = new BlockLZ4CompressorOutputStream(baos, this.params.lz77params);
            if (withBlockDependency) {
                try {
                    o.prefill(this.blockDependencyBuffer, this.blockDependencyBuffer.length - this.collectedBlockDependencyBytes, this.collectedBlockDependencyBytes);
                } catch (Throwable th) {
                    try {
                        o.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            o.write(this.blockData, 0, this.currentIndex);
            o.close();
            if (withBlockDependency) {
                appendToBlockDependencyBuffer(this.blockData, 0, this.currentIndex);
            }
            byte[] b = baos.toByteArray();
            if (b.length > this.currentIndex) {
                ByteUtils.toLittleEndian(this.out, this.currentIndex | Integer.MIN_VALUE, 4);
                this.out.write(this.blockData, 0, this.currentIndex);
                if (this.params.withBlockChecksum) {
                    this.blockHash.update(this.blockData, 0, this.currentIndex);
                }
            } else {
                ByteUtils.toLittleEndian(this.out, b.length, 4);
                this.out.write(b);
                if (this.params.withBlockChecksum) {
                    this.blockHash.update(b, 0, b.length);
                }
            }
            if (this.params.withBlockChecksum) {
                ByteUtils.toLittleEndian(this.out, this.blockHash.getValue(), 4);
                this.blockHash.reset();
            }
            this.currentIndex = 0;
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] data, int off, int len) throws IOException {
        if (this.params.withContentChecksum) {
            this.contentHash.update(data, off, len);
        }
        int blockDataRemaining = this.blockData.length - this.currentIndex;
        while (len > 0) {
            int copyLen = Math.min(len, blockDataRemaining);
            System.arraycopy(data, off, this.blockData, this.currentIndex, copyLen);
            off += copyLen;
            blockDataRemaining -= copyLen;
            len -= copyLen;
            this.currentIndex += copyLen;
            if (blockDataRemaining == 0) {
                flushBlock();
                blockDataRemaining = this.blockData.length;
            }
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        this.oneByte[0] = (byte) (b & 255);
        write(this.oneByte);
    }

    private void writeFrameDescriptor() throws IOException {
        int flags = 64;
        if (!this.params.withBlockDependency) {
            flags = 64 | 32;
        }
        if (this.params.withContentChecksum) {
            flags |= 4;
        }
        if (this.params.withBlockChecksum) {
            flags |= 16;
        }
        this.out.write(flags);
        this.contentHash.update(flags);
        int bd = (this.params.blockSize.getIndex() << 4) & 112;
        this.out.write(bd);
        this.contentHash.update(bd);
        this.out.write((int) ((this.contentHash.getValue() >> 8) & 255));
        this.contentHash.reset();
    }

    private void writeTrailer() throws IOException {
        this.out.write(END_MARK);
        if (this.params.withContentChecksum) {
            ByteUtils.toLittleEndian(this.out, this.contentHash.getValue(), 4);
        }
    }
}
