package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;

/* loaded from: classes9.dex */
public class ClosedOutputStream extends OutputStream {
    public static final ClosedOutputStream INSTANCE = new ClosedOutputStream();

    @Deprecated
    public static final ClosedOutputStream CLOSED_OUTPUT_STREAM = INSTANCE;

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        throw new IOException("flush() failed: stream is closed");
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        IOUtils.checkFromIndexSize(b, off, len);
        throw new IOException("write(byte[], int, int) failed: stream is closed");
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        throw new IOException("write(int) failed: stream is closed");
    }
}
