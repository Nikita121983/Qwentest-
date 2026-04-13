package org.apache.commons.compress.compressors.snappy;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Arrays;
import kotlin.UByte;
import org.apache.commons.codec.digest.PureJavaCrc32C;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BoundedInputStream;

/* loaded from: classes9.dex */
public class FramedSnappyCompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    static final int COMPRESSED_CHUNK_TYPE = 0;
    static final long MASK_OFFSET = 2726488792L;
    private static final int MAX_SKIPPABLE_TYPE = 253;
    private static final int MAX_UNSKIPPABLE_TYPE = 127;
    private static final int MIN_UNSKIPPABLE_TYPE = 2;
    private static final int PADDING_CHUNK_TYPE = 254;
    private static final int STREAM_IDENTIFIER_TYPE = 255;
    static final byte[] SZ_SIGNATURE = {-1, 6, 0, 0, 115, 78, 97, 80, 112, 89};
    private static final int UNCOMPRESSED_CHUNK_TYPE = 1;
    private final int blockSize;
    private final PureJavaCrc32C checksum;
    private final BoundedInputStream countingStream;
    private SnappyCompressorInputStream currentCompressedChunk;
    private final FramedSnappyDialect dialect;
    private boolean endReached;
    private long expectedChecksum;
    private boolean inUncompressedChunk;
    private final PushbackInputStream inputStream;
    private final byte[] oneByte;
    private final ByteUtils.ByteSupplier supplier;
    private int uncompressedBytesRemaining;
    private long unreadBytes;

    public static boolean matches(byte[] signature, int length) {
        if (length < SZ_SIGNATURE.length) {
            return false;
        }
        byte[] shortenedSig = signature;
        if (signature.length > SZ_SIGNATURE.length) {
            shortenedSig = Arrays.copyOf(signature, SZ_SIGNATURE.length);
        }
        return Arrays.equals(shortenedSig, SZ_SIGNATURE);
    }

    static long unmask(long x) {
        long x2 = (x - MASK_OFFSET) & 4294967295L;
        return 4294967295L & ((x2 >> 17) | (x2 << 15));
    }

    public FramedSnappyCompressorInputStream(InputStream in) throws IOException {
        this(in, FramedSnappyDialect.STANDARD);
    }

    public FramedSnappyCompressorInputStream(InputStream in, FramedSnappyDialect dialect) throws IOException {
        this(in, 32768, dialect);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FramedSnappyCompressorInputStream(InputStream in, int blockSize, FramedSnappyDialect dialect) throws IOException {
        this.oneByte = new byte[1];
        this.expectedChecksum = -1L;
        this.checksum = new PureJavaCrc32C();
        this.supplier = new ByteUtils.ByteSupplier() { // from class: org.apache.commons.compress.compressors.snappy.FramedSnappyCompressorInputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.compress.utils.ByteUtils.ByteSupplier
            public final int getAsByte() {
                int readOneByte;
                readOneByte = FramedSnappyCompressorInputStream.this.readOneByte();
                return readOneByte;
            }
        };
        if (blockSize <= 0) {
            throw new IllegalArgumentException("blockSize must be bigger than 0");
        }
        this.countingStream = ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(in)).get();
        this.inputStream = new PushbackInputStream(this.countingStream, 1);
        this.blockSize = blockSize;
        this.dialect = dialect;
        if (dialect.hasStreamIdentifier()) {
            readStreamIdentifier();
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.inUncompressedChunk) {
            return Math.min(this.uncompressedBytesRemaining, this.inputStream.available());
        }
        if (this.currentCompressedChunk != null) {
            return this.currentCompressedChunk.available();
        }
        return 0;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            IOUtils.close(this.currentCompressedChunk);
            this.currentCompressedChunk = null;
        } finally {
            this.inputStream.close();
        }
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.countingStream.getCount() - this.unreadBytes;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.oneByte, 0, 1) == -1) {
            return -1;
        }
        return this.oneByte[0] & UByte.MAX_VALUE;
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        int read = readOnce(b, off, len);
        if (read == -1) {
            readNextBlock();
            if (this.endReached) {
                return -1;
            }
            return readOnce(b, off, len);
        }
        return read;
    }

    private long readCrc() throws IOException {
        byte[] b = new byte[4];
        int read = org.apache.commons.compress.utils.IOUtils.readFully(this.inputStream, b);
        count(read);
        if (read != 4) {
            throw new IOException("Premature end of stream");
        }
        return ByteUtils.fromLittleEndian(b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readNextBlock() throws IOException {
        verifyLastChecksumAndReset();
        this.inUncompressedChunk = false;
        int type = readOneByte();
        if (type == -1) {
            this.endReached = true;
            return;
        }
        if (type == 255) {
            this.inputStream.unread(type);
            this.unreadBytes++;
            pushedBackBytes(1L);
            readStreamIdentifier();
            readNextBlock();
            return;
        }
        if (type == PADDING_CHUNK_TYPE || (type > 127 && type <= MAX_SKIPPABLE_TYPE)) {
            skipBlock();
            readNextBlock();
            return;
        }
        if (type >= 2 && type <= 127) {
            throw new IOException("Unskippable chunk with type " + type + " (hex " + Integer.toHexString(type) + ") detected.");
        }
        if (type == 1) {
            this.inUncompressedChunk = true;
            this.uncompressedBytesRemaining = readSize() - 4;
            if (this.uncompressedBytesRemaining < 0) {
                throw new IOException("Found illegal chunk with negative size");
            }
            this.expectedChecksum = unmask(readCrc());
            return;
        }
        if (type == 0) {
            boolean expectChecksum = this.dialect.usesChecksumWithCompressedChunks();
            long size = readSize() - (expectChecksum ? 4L : 0L);
            if (size < 0) {
                throw new IOException("Found illegal chunk with negative size");
            }
            if (expectChecksum) {
                this.expectedChecksum = unmask(readCrc());
            } else {
                this.expectedChecksum = -1L;
            }
            this.currentCompressedChunk = new SnappyCompressorInputStream(((BoundedInputStream.Builder) ((BoundedInputStream.Builder) ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(this.inputStream)).setMaxCount(size)).setPropagateClose(false)).get(), this.blockSize);
            count(this.currentCompressedChunk.getBytesRead());
            return;
        }
        throw new IOException("Unknown chunk type " + type + " detected.");
    }

    private int readOnce(byte[] b, int off, int len) throws IOException {
        int read = -1;
        if (this.inUncompressedChunk) {
            int amount = Math.min(this.uncompressedBytesRemaining, len);
            if (amount == 0) {
                return -1;
            }
            read = this.inputStream.read(b, off, amount);
            if (read != -1) {
                this.uncompressedBytesRemaining -= read;
                count(read);
            }
        } else if (this.currentCompressedChunk != null) {
            long before = this.currentCompressedChunk.getBytesRead();
            read = this.currentCompressedChunk.read(b, off, len);
            if (read == -1) {
                this.currentCompressedChunk.close();
                this.currentCompressedChunk = null;
            } else {
                count(this.currentCompressedChunk.getBytesRead() - before);
            }
        }
        if (read > 0) {
            this.checksum.update(b, off, read);
        }
        return read;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int readOneByte() throws IOException {
        int b = this.inputStream.read();
        if (b == -1) {
            return -1;
        }
        count(1);
        return b & 255;
    }

    private int readSize() throws IOException {
        return (int) ByteUtils.fromLittleEndian(this.supplier, 3);
    }

    private void readStreamIdentifier() throws IOException {
        byte[] b = new byte[10];
        int read = org.apache.commons.compress.utils.IOUtils.readFully(this.inputStream, b);
        count(read);
        if (10 != read || !matches(b, 10)) {
            throw new IOException("Not a framed Snappy stream");
        }
    }

    private void skipBlock() throws IOException {
        int size = readSize();
        if (size < 0) {
            throw new IOException("Found illegal chunk with negative size");
        }
        long read = IOUtils.skip(this.inputStream, size);
        count(read);
        if (read != size) {
            throw new IOException("Premature end of stream");
        }
    }

    private void verifyLastChecksumAndReset() throws IOException {
        if (this.expectedChecksum >= 0 && this.expectedChecksum != this.checksum.getValue()) {
            throw new IOException("Checksum verification failed");
        }
        this.expectedChecksum = -1L;
        this.checksum.reset();
    }
}
