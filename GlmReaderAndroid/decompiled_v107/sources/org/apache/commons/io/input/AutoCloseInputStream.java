package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.function.IOIntConsumer;
import org.apache.commons.io.input.ProxyInputStream;

/* loaded from: classes9.dex */
public class AutoCloseInputStream extends ProxyInputStream {

    /* loaded from: classes9.dex */
    public static class Builder extends ProxyInputStream.AbstractBuilder<AutoCloseInputStream, Builder> {
        @Override // org.apache.commons.io.input.ProxyInputStream.AbstractBuilder
        public /* bridge */ /* synthetic */ IOIntConsumer getAfterRead() {
            return super.getAfterRead();
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [org.apache.commons.io.build.AbstractStreamBuilder, org.apache.commons.io.input.AutoCloseInputStream$Builder] */
        @Override // org.apache.commons.io.input.ProxyInputStream.AbstractBuilder
        public /* bridge */ /* synthetic */ Builder setAfterRead(IOIntConsumer iOIntConsumer) {
            return super.setAfterRead(iOIntConsumer);
        }

        @Override // org.apache.commons.io.function.IOSupplier
        public AutoCloseInputStream get() throws IOException {
            return new AutoCloseInputStream(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private AutoCloseInputStream(Builder builder) throws IOException {
        super(builder);
    }

    @Deprecated
    public AutoCloseInputStream(InputStream in) {
        super(ClosedInputStream.ifNull(in));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.io.input.ProxyInputStream
    public void afterRead(int n) throws IOException {
        if (n == -1) {
            close();
        }
        super.afterRead(n);
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this.in = ClosedInputStream.INSTANCE;
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
