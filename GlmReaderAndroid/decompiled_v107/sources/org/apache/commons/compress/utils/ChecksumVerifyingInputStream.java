package org.apache.commons.compress.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;

@Deprecated
/* loaded from: classes9.dex */
public class ChecksumVerifyingInputStream extends CheckedInputStream {
    private final long expected;
    private long remaining;

    public ChecksumVerifyingInputStream(Checksum checksum, InputStream in, long size, long expectedChecksum) {
        super(in, checksum);
        this.expected = expectedChecksum;
        this.remaining = size;
    }

    public long getBytesRemaining() {
        return this.remaining;
    }

    @Override // java.util.zip.CheckedInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.remaining <= 0) {
            return -1;
        }
        int data = super.read();
        if (data >= 0) {
            this.remaining--;
        }
        verify();
        return data;
    }

    @Override // java.util.zip.CheckedInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        int readCount = super.read(b, off, len);
        if (readCount >= 0) {
            this.remaining -= readCount;
        }
        verify();
        return readCount;
    }

    private void verify() throws IOException {
        if (this.remaining <= 0 && this.expected != getChecksum().getValue()) {
            throw new IOException("Checksum verification failed");
        }
    }
}
