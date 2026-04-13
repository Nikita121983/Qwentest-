package org.apache.commons.compress.compressors.snappy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.codec.digest.PureJavaCrc32C;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.lz77support.Parameters;
import org.apache.commons.compress.utils.ByteUtils;

/* loaded from: classes9.dex */
public class FramedSnappyCompressorOutputStream extends CompressorOutputStream<OutputStream> {
    private static final int MAX_COMPRESSED_BUFFER_SIZE = 65536;
    private final byte[] buffer;
    private final PureJavaCrc32C checksum;
    private final ByteUtils.ByteConsumer consumer;
    private int currentIndex;
    private final byte[] oneByte;
    private final Parameters params;

    static long mask(long x) {
        return (((x >> 15) | (x << 17)) + 2726488792L) & 4294967295L;
    }

    public FramedSnappyCompressorOutputStream(OutputStream out) throws IOException {
        this(out, SnappyCompressorOutputStream.createParameterBuilder(32768).build());
    }

    public FramedSnappyCompressorOutputStream(OutputStream out, Parameters params) throws IOException {
        super(out);
        this.checksum = new PureJavaCrc32C();
        this.oneByte = new byte[1];
        this.buffer = new byte[65536];
        this.params = params;
        this.consumer = new ByteUtils.OutputStreamByteConsumer(out);
        out.write(FramedSnappyCompressorInputStream.SZ_SIGNATURE);
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
        flushBuffer();
    }

    private void flushBuffer() throws IOException {
        if (this.currentIndex == 0) {
            return;
        }
        this.out.write(0);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream o = new SnappyCompressorOutputStream(baos, this.currentIndex, this.params);
        try {
            o.write(this.buffer, 0, this.currentIndex);
            o.close();
            byte[] b = baos.toByteArray();
            writeLittleEndian(3, b.length + 4);
            writeCrc();
            this.out.write(b);
            this.currentIndex = 0;
        } catch (Throwable th) {
            try {
                o.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] data, int off, int len) throws IOException {
        int blockDataRemaining = this.buffer.length - this.currentIndex;
        while (len > 0) {
            int copyLen = Math.min(len, blockDataRemaining);
            System.arraycopy(data, off, this.buffer, this.currentIndex, copyLen);
            off += copyLen;
            blockDataRemaining -= copyLen;
            len -= copyLen;
            this.currentIndex += copyLen;
            if (blockDataRemaining == 0) {
                flushBuffer();
                blockDataRemaining = this.buffer.length;
            }
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        this.oneByte[0] = (byte) (b & 255);
        write(this.oneByte);
    }

    private void writeCrc() throws IOException {
        this.checksum.update(this.buffer, 0, this.currentIndex);
        writeLittleEndian(4, mask(this.checksum.getValue()));
        this.checksum.reset();
    }

    private void writeLittleEndian(int numBytes, long num) throws IOException {
        ByteUtils.toLittleEndian(this.consumer, num, numBytes);
    }
}
