package org.apache.commons.io.output;

import java.io.Writer;
import org.apache.commons.io.IOUtils;

/* loaded from: classes9.dex */
public class NullWriter extends Writer {
    public static final NullWriter INSTANCE = new NullWriter();

    @Deprecated
    public static final NullWriter NULL_WRITER = INSTANCE;

    @Deprecated
    public NullWriter() {
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c) {
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence csq) {
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence csq, int start, int end) {
        IOUtils.checkFromToIndex(csq, start, end);
        return this;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
    }

    @Override // java.io.Writer
    public void write(char[] chr) {
        write(chr, 0, chr.length);
    }

    @Override // java.io.Writer
    public void write(char[] cbuf, int off, int len) {
        IOUtils.checkFromIndexSize(cbuf, off, len);
    }

    @Override // java.io.Writer
    public void write(int b) {
    }

    @Override // java.io.Writer
    public void write(String str) {
        write(str, 0, str.length());
    }

    @Override // java.io.Writer
    public void write(String str, int off, int len) {
        IOUtils.checkFromIndexSize(str, off, len);
    }
}
