package org.apache.commons.io.input;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.IOFunction;
import org.apache.commons.io.function.IOIntSupplier;
import org.apache.commons.io.function.IORunnable;
import org.apache.commons.io.function.IOSupplier;
import org.apache.commons.io.function.IOTriFunction;
import org.apache.commons.io.function.Uncheck;
import org.apache.commons.io.input.UncheckedFilterInputStream;

/* loaded from: classes9.dex */
public final class UncheckedFilterInputStream extends FilterInputStream {

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<UncheckedFilterInputStream, Builder> {
        @Override // org.apache.commons.io.function.IOSupplier
        public UncheckedFilterInputStream get() {
            return (UncheckedFilterInputStream) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.input.UncheckedFilterInputStream$Builder$$ExternalSyntheticLambda0
                @Override // org.apache.commons.io.function.IOSupplier
                public final Object get() {
                    return UncheckedFilterInputStream.Builder.this.m2148xbffad909();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$get$0$org-apache-commons-io-input-UncheckedFilterInputStream$Builder, reason: not valid java name */
        public /* synthetic */ UncheckedFilterInputStream m2148xbffad909() throws IOException {
            return new UncheckedFilterInputStream(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private UncheckedFilterInputStream(Builder builder) throws IOException {
        super(builder.getInputStream());
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws UncheckedIOException {
        return Uncheck.getAsInt(new IOIntSupplier() { // from class: org.apache.commons.io.input.UncheckedFilterInputStream$$ExternalSyntheticLambda4
            @Override // org.apache.commons.io.function.IOIntSupplier
            public final int getAsInt() {
                return UncheckedFilterInputStream.this.m2141xe65b9a5f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$available$0$org-apache-commons-io-input-UncheckedFilterInputStream, reason: not valid java name */
    public /* synthetic */ int m2141xe65b9a5f() throws IOException {
        return super.available();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws UncheckedIOException {
        Uncheck.run(new IORunnable() { // from class: org.apache.commons.io.input.UncheckedFilterInputStream$$ExternalSyntheticLambda3
            @Override // org.apache.commons.io.function.IORunnable
            public final void run() {
                UncheckedFilterInputStream.this.m2142x2286bf6f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$close$1$org-apache-commons-io-input-UncheckedFilterInputStream, reason: not valid java name */
    public /* synthetic */ void m2142x2286bf6f() throws IOException {
        super.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$read$2$org-apache-commons-io-input-UncheckedFilterInputStream, reason: not valid java name */
    public /* synthetic */ int m2143x2766e7d6() throws IOException {
        return super.read();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws UncheckedIOException {
        return Uncheck.getAsInt(new IOIntSupplier() { // from class: org.apache.commons.io.input.UncheckedFilterInputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOIntSupplier
            public final int getAsInt() {
                return UncheckedFilterInputStream.this.m2143x2766e7d6();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$read$3$org-apache-commons-io-input-UncheckedFilterInputStream, reason: not valid java name */
    public /* synthetic */ Integer m2144x289d3ab5(byte[] x$0) throws IOException {
        return Integer.valueOf(super.read(x$0));
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b) throws UncheckedIOException {
        return ((Integer) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.input.UncheckedFilterInputStream$$ExternalSyntheticLambda6
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return UncheckedFilterInputStream.this.m2144x289d3ab5((byte[]) obj);
            }
        }, b)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$read$4$org-apache-commons-io-input-UncheckedFilterInputStream, reason: not valid java name */
    public /* synthetic */ Integer m2145x29d38d94(byte[] x$0, int x$1, int x$2) throws IOException {
        return Integer.valueOf(super.read(x$0, x$1, x$2));
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws UncheckedIOException {
        return ((Integer) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.input.UncheckedFilterInputStream$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                return UncheckedFilterInputStream.this.m2145x29d38d94((byte[]) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        }, b, Integer.valueOf(off), Integer.valueOf(len))).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$reset$5$org-apache-commons-io-input-UncheckedFilterInputStream, reason: not valid java name */
    public /* synthetic */ void m2146x2dd38774() throws IOException {
        super.reset();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws UncheckedIOException {
        Uncheck.run(new IORunnable() { // from class: org.apache.commons.io.input.UncheckedFilterInputStream$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IORunnable
            public final void run() {
                UncheckedFilterInputStream.this.m2146x2dd38774();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$skip$6$org-apache-commons-io-input-UncheckedFilterInputStream, reason: not valid java name */
    public /* synthetic */ Long m2147x2b981389(long x$0) throws IOException {
        return Long.valueOf(super.skip(x$0));
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long n) throws UncheckedIOException {
        return ((Long) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.input.UncheckedFilterInputStream$$ExternalSyntheticLambda5
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return UncheckedFilterInputStream.this.m2147x2b981389(((Long) obj).longValue());
            }
        }, Long.valueOf(n))).longValue();
    }
}
