package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class ClosedWriter extends Writer {
    public static final ClosedWriter INSTANCE = new ClosedWriter();

    @Deprecated
    public static final ClosedWriter CLOSED_WRITER = INSTANCE;

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        throw new IOException("flush() failed: stream is closed");
    }

    @Override // java.io.Writer
    public void write(char[] cbuf, int off, int len) throws IOException {
        throw new IOException(String.format("write(%s, %d, %d) failed: stream is closed", Arrays.toString(cbuf), Integer.valueOf(off), Integer.valueOf(len)));
    }
}
