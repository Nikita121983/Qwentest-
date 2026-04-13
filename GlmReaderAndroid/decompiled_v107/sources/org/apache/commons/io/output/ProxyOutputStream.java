package org.apache.commons.io.output;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.IOConsumer;

/* loaded from: classes9.dex */
public class ProxyOutputStream extends FilterOutputStream {

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<ProxyOutputStream, Builder> {
        @Override // org.apache.commons.io.function.IOSupplier
        public ProxyOutputStream get() throws IOException {
            return new ProxyOutputStream(this);
        }
    }

    ProxyOutputStream(Builder builder) throws IOException {
        super(builder.getOutputStream());
    }

    public ProxyOutputStream(OutputStream delegate) {
        super(delegate);
    }

    protected void afterWrite(int n) throws IOException {
    }

    protected void beforeWrite(int n) throws IOException {
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IOUtils.close(this.out, new IOConsumer() { // from class: org.apache.commons.io.output.ProxyOutputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ProxyOutputStream.this.handleIOException((IOException) obj);
            }
        });
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
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

    public ProxyOutputStream setReference(OutputStream out) {
        this.out = out;
        return this;
    }

    OutputStream unwrap() {
        return this.out;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] b) throws IOException {
        try {
            int len = IOUtils.length(b);
            beforeWrite(len);
            this.out.write(b);
            afterWrite(len);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        try {
            beforeWrite(len);
            this.out.write(b, off, len);
            afterWrite(len);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        try {
            beforeWrite(1);
            this.out.write(b);
            afterWrite(1);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    public void writeRepeat(byte[] b, int off, int len, long repeat) throws IOException {
        long remains = repeat;
        while (true) {
            long remains2 = remains - 1;
            if (remains > 0) {
                write(b, off, len);
                remains = remains2;
            } else {
                return;
            }
        }
    }

    public void writeRepeat(byte[] b, long repeat) throws IOException {
        long remains = repeat;
        while (true) {
            long remains2 = remains - 1;
            if (remains > 0) {
                write(b);
                remains = remains2;
            } else {
                return;
            }
        }
    }

    public void writeRepeat(int b, long repeat) throws IOException {
        long remains = repeat;
        while (true) {
            long remains2 = remains - 1;
            if (remains > 0) {
                write(b);
                remains = remains2;
            } else {
                return;
            }
        }
    }
}
