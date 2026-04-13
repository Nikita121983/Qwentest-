package org.apache.commons.io.output;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.function.IOConsumer;

/* loaded from: classes9.dex */
public class ProxyWriter extends FilterWriter {
    public ProxyWriter(Writer delegate) {
        super(delegate);
    }

    protected void afterWrite(int n) throws IOException {
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c) throws IOException {
        try {
            beforeWrite(1);
            this.out.append(c);
            afterWrite(1);
        } catch (IOException e) {
            handleIOException(e);
        }
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence csq) throws IOException {
        try {
            int len = IOUtils.length(csq);
            beforeWrite(len);
            this.out.append(csq);
            afterWrite(len);
        } catch (IOException e) {
            handleIOException(e);
        }
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence csq, int start, int end) throws IOException {
        try {
            beforeWrite(end - start);
            this.out.append(csq, start, end);
            afterWrite(end - start);
        } catch (IOException e) {
            handleIOException(e);
        }
        return this;
    }

    protected void beforeWrite(int n) throws IOException {
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IOUtils.close(this.out, new IOConsumer() { // from class: org.apache.commons.io.output.ProxyWriter$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ProxyWriter.this.handleIOException((IOException) obj);
            }
        });
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        try {
            this.out.flush();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleIOException(IOException e) throws IOException {
        throw e;
    }

    @Override // java.io.Writer
    public void write(char[] cbuf) throws IOException {
        try {
            int len = IOUtils.length(cbuf);
            beforeWrite(len);
            this.out.write(cbuf);
            afterWrite(len);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] cbuf, int off, int len) throws IOException {
        try {
            beforeWrite(len);
            this.out.write(cbuf, off, len);
            afterWrite(len);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int c) throws IOException {
        try {
            beforeWrite(1);
            this.out.write(c);
            afterWrite(1);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        try {
            int len = IOUtils.length(str);
            beforeWrite(len);
            this.out.write(str);
            afterWrite(len);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(String str, int off, int len) throws IOException {
        try {
            beforeWrite(len);
            this.out.write(str, off, len);
            afterWrite(len);
        } catch (IOException e) {
            handleIOException(e);
        }
    }
}
