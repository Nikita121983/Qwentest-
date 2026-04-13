package org.apache.commons.io.input;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOIntConsumer;

/* loaded from: classes9.dex */
public abstract class ProxyInputStream extends FilterInputStream {
    private final IOIntConsumer afterRead;
    private volatile boolean closed;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static abstract class AbstractBuilder<T, B extends AbstractStreamBuilder<T, B>> extends AbstractStreamBuilder<T, B> {
        private IOIntConsumer afterRead;

        public IOIntConsumer getAfterRead() {
            return this.afterRead;
        }

        public B setAfterRead(IOIntConsumer afterRead) {
            this.afterRead = afterRead;
            return (B) asThis();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProxyInputStream(AbstractBuilder<?, ?> builder) throws IOException {
        this(builder.getInputStream(), builder);
    }

    public ProxyInputStream(InputStream proxy) {
        super(proxy);
        this.afterRead = IOIntConsumer.NOOP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProxyInputStream(InputStream proxy, AbstractBuilder<?, ?> builder) {
        super(proxy);
        this.afterRead = builder.getAfterRead() != null ? builder.getAfterRead() : IOIntConsumer.NOOP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void afterRead(int n) throws IOException {
        this.afterRead.accept(n);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        if (this.in != null && !isClosed()) {
            try {
                return this.in.available();
            } catch (IOException e) {
                handleIOException(e);
                return 0;
            }
        }
        return 0;
    }

    protected void beforeRead(int n) throws IOException {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkOpen() throws IOException {
        Input.checkOpen(!isClosed());
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IOUtils.close(this.in, new IOConsumer() { // from class: org.apache.commons.io.input.ProxyInputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ProxyInputStream.this.handleIOException((IOException) obj);
            }
        });
        this.closed = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleIOException(IOException e) throws IOException {
        throw e;
    }

    boolean isClosed() {
        return this.closed;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int readLimit) {
        if (this.in != null) {
            this.in.mark(readLimit);
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return this.in != null && this.in.markSupported();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i = 1;
        try {
            beforeRead(1);
            int b = this.in.read();
            if (b == -1) {
                i = -1;
            }
            afterRead(i);
            return b;
        } catch (IOException e) {
            handleIOException(e);
            return -1;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b) throws IOException {
        try {
            beforeRead(IOUtils.length(b));
            int n = this.in.read(b);
            afterRead(n);
            return n;
        } catch (IOException e) {
            handleIOException(e);
            return -1;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        try {
            beforeRead(len);
            int n = this.in.read(b, off, len);
            afterRead(n);
            return n;
        } catch (IOException e) {
            handleIOException(e);
            return -1;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        try {
            this.in.reset();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    public ProxyInputStream setReference(InputStream in) {
        this.in = in;
        return this;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long n) throws IOException {
        try {
            return this.in.skip(n);
        } catch (IOException e) {
            handleIOException(e);
            return 0L;
        }
    }

    public InputStream unwrap() {
        return this.in;
    }
}
