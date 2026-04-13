package org.apache.commons.compress.compressors.lz77support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import kotlin.UByte;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.input.BoundedInputStream;

/* loaded from: classes9.dex */
public abstract class AbstractLZ77CompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    private int backReferenceOffset;
    private final byte[] buf;
    private long bytesRemaining;
    private final BoundedInputStream in;
    private int readIndex;
    private int size;
    private final int windowSize;
    private int writeIndex;
    private final byte[] oneByte = new byte[1];
    protected final ByteUtils.ByteSupplier supplier = new ByteUtils.ByteSupplier() { // from class: org.apache.commons.compress.compressors.lz77support.AbstractLZ77CompressorInputStream$$ExternalSyntheticLambda0
        @Override // org.apache.commons.compress.utils.ByteUtils.ByteSupplier
        public final int getAsByte() {
            return AbstractLZ77CompressorInputStream.this.readOneByte();
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    public AbstractLZ77CompressorInputStream(InputStream is, int windowSize) {
        this.in = ((BoundedInputStream.Builder) BoundedInputStream.builder().setInputStream(is)).asSupplier().get();
        if (windowSize <= 0) {
            throw new IllegalArgumentException("windowSize must be bigger than 0");
        }
        this.windowSize = windowSize;
        this.buf = new byte[windowSize * 3];
        this.readIndex = 0;
        this.writeIndex = 0;
        this.bytesRemaining = 0L;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.writeIndex - this.readIndex;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.in.getCount();
    }

    public int getSize() {
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean hasMoreDataInBlock() {
        return this.bytesRemaining > 0;
    }

    public void prefill(byte[] data) {
        if (this.writeIndex != 0) {
            throw new IllegalStateException("The stream has already been read from, can't prefill anymore");
        }
        int len = Math.min(this.windowSize, data.length);
        System.arraycopy(data, data.length - len, this.buf, 0, len);
        this.writeIndex += len;
        this.readIndex += len;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.oneByte, 0, 1) == -1) {
            return -1;
        }
        return this.oneByte[0] & UByte.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int readBackReference(byte[] b, int off, int len) {
        int avail = available();
        if (len > avail) {
            tryToCopy(len - avail);
        }
        return readFromBuffer(b, off, len);
    }

    private int readFromBuffer(byte[] b, int off, int len) {
        int readable = Math.min(len, available());
        if (readable > 0) {
            System.arraycopy(this.buf, this.readIndex, b, off, readable);
            this.readIndex += readable;
            if (this.readIndex > this.windowSize * 2) {
                slideBuffer();
            }
        }
        this.size += readable;
        return readable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int readLiteral(byte[] b, int off, int len) throws IOException {
        int avail = available();
        if (len > avail) {
            tryToReadLiteral(len - avail);
        }
        return readFromBuffer(b, off, len);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int readOneByte() throws IOException {
        int b = this.in.read();
        if (b == -1) {
            return -1;
        }
        count(1);
        return b & 255;
    }

    private void slideBuffer() {
        System.arraycopy(this.buf, this.windowSize, this.buf, 0, this.windowSize * 2);
        this.writeIndex -= this.windowSize;
        this.readIndex -= this.windowSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void startBackReference(int offset, long length) {
        if (offset <= 0 || offset > this.writeIndex) {
            throw new IllegalArgumentException("offset must be bigger than 0 but not bigger than the number of bytes available for back-references");
        }
        if (length < 0) {
            throw new IllegalArgumentException("length must not be negative");
        }
        this.backReferenceOffset = offset;
        this.bytesRemaining = length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void startLiteral(long length) {
        if (length < 0) {
            throw new IllegalArgumentException("length must not be negative");
        }
        this.bytesRemaining = length;
    }

    private void tryToCopy(int bytesToCopy) {
        int copy = Math.min((int) Math.min(bytesToCopy, this.bytesRemaining), this.buf.length - this.writeIndex);
        if (copy != 0) {
            if (this.backReferenceOffset == 1) {
                byte last = this.buf[this.writeIndex - 1];
                Arrays.fill(this.buf, this.writeIndex, this.writeIndex + copy, last);
                this.writeIndex += copy;
            } else if (copy < this.backReferenceOffset) {
                System.arraycopy(this.buf, this.writeIndex - this.backReferenceOffset, this.buf, this.writeIndex, copy);
                this.writeIndex += copy;
            } else {
                int fullRots = copy / this.backReferenceOffset;
                for (int i = 0; i < fullRots; i++) {
                    System.arraycopy(this.buf, this.writeIndex - this.backReferenceOffset, this.buf, this.writeIndex, this.backReferenceOffset);
                    this.writeIndex += this.backReferenceOffset;
                }
                int i2 = this.backReferenceOffset;
                int pad = copy - (i2 * fullRots);
                if (pad > 0) {
                    System.arraycopy(this.buf, this.writeIndex - this.backReferenceOffset, this.buf, this.writeIndex, pad);
                    this.writeIndex += pad;
                }
            }
        }
        this.bytesRemaining -= copy;
    }

    private void tryToReadLiteral(int bytesToRead) throws IOException {
        int reallyTryToRead = Math.min((int) Math.min(bytesToRead, this.bytesRemaining), this.buf.length - this.writeIndex);
        int bytesRead = reallyTryToRead > 0 ? IOUtils.readFully(this.in, this.buf, this.writeIndex, reallyTryToRead) : 0;
        count(bytesRead);
        if (reallyTryToRead != bytesRead) {
            throw new IOException("Premature end of stream reading literal");
        }
        this.writeIndex += reallyTryToRead;
        this.bytesRemaining -= reallyTryToRead;
    }
}
