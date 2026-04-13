package org.apache.commons.compress.archivers.dump;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.apache.commons.compress.archivers.dump.DumpArchiveConstants;
import org.apache.commons.compress.utils.ExactMath;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;

/* loaded from: classes9.dex */
final class TapeInputStream extends FilterInputStream {
    private static final int RECORD_SIZE = 1024;
    private byte[] blockBuffer;
    private int blockSize;
    private long bytesRead;
    private int currBlkIdx;
    private boolean isCompressed;
    private int readOffset;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TapeInputStream(InputStream in) {
        super(in);
        this.blockBuffer = new byte[1024];
        this.currBlkIdx = -1;
        this.blockSize = 1024;
        this.readOffset = 1024;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        if (this.readOffset < this.blockSize) {
            return this.blockSize - this.readOffset;
        }
        return this.in.available();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.in != null && this.in != System.in) {
            this.in.close();
        }
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    public byte[] peek() throws IOException {
        if (this.readOffset == this.blockSize) {
            try {
                readBlock(true);
            } catch (ShortFileException e) {
                return null;
            }
        }
        byte[] b = new byte[1024];
        System.arraycopy(this.blockBuffer, this.readOffset, b, 0, b.length);
        return b;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        throw new IllegalArgumentException("All reads must be multiple of record size (1024 bytes.");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        int n;
        if (len == 0) {
            return 0;
        }
        if (len % 1024 != 0) {
            throw new IllegalArgumentException("All reads must be multiple of record size (1024 bytes.");
        }
        int bytes = 0;
        while (bytes < len) {
            if (this.readOffset == this.blockSize) {
                try {
                    readBlock(true);
                } catch (ShortFileException e) {
                    return -1;
                }
            }
            if ((this.readOffset + len) - bytes <= this.blockSize) {
                n = len - bytes;
            } else {
                n = this.blockSize - this.readOffset;
            }
            System.arraycopy(this.blockBuffer, this.readOffset, b, off, n);
            this.readOffset += n;
            bytes += n;
            off += n;
        }
        return bytes;
    }

    private void readBlock(boolean decompress) throws IOException {
        if (this.in == null) {
            throw new IOException("Input buffer is closed");
        }
        if (!this.isCompressed || this.currBlkIdx == -1) {
            readFully(this.blockBuffer, 0, this.blockSize);
            this.bytesRead += this.blockSize;
        } else {
            readFully(this.blockBuffer, 0, 4);
            this.bytesRead += 4;
            int h = DumpArchiveUtil.convert32(this.blockBuffer, 0);
            boolean compressed = (h & 1) == 1;
            if (!compressed) {
                readFully(this.blockBuffer, 0, this.blockSize);
                this.bytesRead += this.blockSize;
            } else {
                int flags = (h >> 1) & 7;
                int length = (h >> 4) & 268435455;
                byte[] compBuffer = readRange(length);
                this.bytesRead += length;
                if (!decompress) {
                    Arrays.fill(this.blockBuffer, (byte) 0);
                } else {
                    switch (DumpArchiveConstants.COMPRESSION_TYPE.find(flags & 3)) {
                        case ZLIB:
                            Inflater inflator = new Inflater();
                            try {
                                try {
                                    inflator.setInput(compBuffer, 0, compBuffer.length);
                                    if (inflator.inflate(this.blockBuffer) != this.blockSize) {
                                        throw new ShortFileException();
                                    }
                                    break;
                                } catch (DataFormatException e) {
                                    throw new DumpArchiveException("Bad data", e);
                                }
                            } finally {
                                inflator.end();
                            }
                        case BZLIB:
                            throw new UnsupportedCompressionAlgorithmException("BZLIB2");
                        case LZO:
                            throw new UnsupportedCompressionAlgorithmException("LZO");
                        default:
                            throw new UnsupportedCompressionAlgorithmException();
                    }
                }
            }
        }
        this.currBlkIdx++;
        this.readOffset = 0;
    }

    private void readFully(byte[] b, int off, int len) throws IOException {
        int count = IOUtils.readFully(this.in, b, off, len);
        if (count < len) {
            throw new ShortFileException();
        }
    }

    private byte[] readRange(int len) throws IOException {
        byte[] ret = IOUtils.readRange(this.in, len);
        if (ret.length < len) {
            throw new ShortFileException();
        }
        return ret;
    }

    public byte[] readRecord() throws IOException {
        byte[] result = new byte[1024];
        if (-1 == read(result, 0, result.length)) {
            throw new ShortFileException();
        }
        return result;
    }

    public void resetBlockSize(int recsPerBlock, boolean isCompressed) throws IOException {
        this.isCompressed = isCompressed;
        if (recsPerBlock < 1) {
            throw new IOException("Block with " + recsPerBlock + " records found, must be at least 1");
        }
        this.blockSize = recsPerBlock * 1024;
        if (this.blockSize < 1) {
            throw new IOException("Block size cannot be less than or equal to 0: " + this.blockSize);
        }
        byte[] oldBuffer = this.blockBuffer;
        this.blockBuffer = new byte[this.blockSize];
        System.arraycopy(oldBuffer, 0, this.blockBuffer, 0, 1024);
        readFully(this.blockBuffer, 1024, this.blockSize - 1024);
        this.currBlkIdx = 0;
        this.readOffset = 1024;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long len) throws IOException {
        long n;
        if (len % FileUtils.ONE_KB != 0) {
            throw new IllegalArgumentException("All reads must be multiple of record size (1024 bytes.");
        }
        long bytes = 0;
        while (bytes < len) {
            if (this.readOffset == this.blockSize) {
                try {
                    readBlock(len - bytes < ((long) this.blockSize));
                } catch (ShortFileException e) {
                    return -1L;
                }
            }
            if (this.readOffset + (len - bytes) <= this.blockSize) {
                n = len - bytes;
            } else {
                n = this.blockSize - this.readOffset;
            }
            this.readOffset = ExactMath.add(this.readOffset, n);
            bytes += n;
        }
        return bytes;
    }
}
