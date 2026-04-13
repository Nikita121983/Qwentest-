package org.apache.commons.compress.utils;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Deprecated
/* loaded from: classes9.dex */
public class CountingOutputStream extends FilterOutputStream {
    private long bytesWritten;

    public CountingOutputStream(OutputStream out) {
        super(out);
    }

    protected void count(long written) {
        if (written != -1) {
            this.bytesWritten += written;
        }
    }

    public long getBytesWritten() {
        return this.bytesWritten;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
        count(len);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        this.out.write(b);
        count(1L);
    }
}
