package org.apache.commons.io.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.CharBuffer;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.IOBooleanSupplier;
import org.apache.commons.io.function.IOFunction;
import org.apache.commons.io.function.IOIntConsumer;
import org.apache.commons.io.function.IOIntSupplier;
import org.apache.commons.io.function.IORunnable;
import org.apache.commons.io.function.IOSupplier;
import org.apache.commons.io.function.IOTriFunction;
import org.apache.commons.io.function.Uncheck;
import org.apache.commons.io.input.UncheckedBufferedReader;

/* loaded from: classes9.dex */
public final class UncheckedBufferedReader extends BufferedReader {

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<UncheckedBufferedReader, Builder> {
        @Override // org.apache.commons.io.function.IOSupplier
        public UncheckedBufferedReader get() {
            return (UncheckedBufferedReader) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$Builder$$ExternalSyntheticLambda0
                @Override // org.apache.commons.io.function.IOSupplier
                public final Object get() {
                    return UncheckedBufferedReader.Builder.this.m2140xc48f3479();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$get$0$org-apache-commons-io-input-UncheckedBufferedReader$Builder, reason: not valid java name */
        public /* synthetic */ UncheckedBufferedReader m2140xc48f3479() throws IOException {
            return new UncheckedBufferedReader(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private UncheckedBufferedReader(Builder builder) throws IOException {
        super(builder.getReader(), builder.getBufferSize());
    }

    @Override // java.io.BufferedReader, java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws UncheckedIOException {
        Uncheck.run(new IORunnable() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda9
            @Override // org.apache.commons.io.function.IORunnable
            public final void run() {
                UncheckedBufferedReader.this.m2130xabdb1404();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$close$0$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    public /* synthetic */ void m2130xabdb1404() throws IOException {
        super.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$mark$1$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    public /* synthetic */ void m2131x1d875af4(int x$0) throws IOException {
        super.mark(x$0);
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public void mark(int readAheadLimit) throws UncheckedIOException {
        Uncheck.accept(new IOIntConsumer() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOIntConsumer
            public final void accept(int i) {
                UncheckedBufferedReader.this.m2131x1d875af4(i);
            }
        }, readAheadLimit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$read$2$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    public /* synthetic */ int m2132xcbc9ce7e() throws IOException {
        return super.read();
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public int read() throws UncheckedIOException {
        return Uncheck.getAsInt(new IOIntSupplier() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda6
            @Override // org.apache.commons.io.function.IOIntSupplier
            public final int getAsInt() {
                return UncheckedBufferedReader.this.m2132xcbc9ce7e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$read$3$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    public /* synthetic */ Integer m2133x59047fff(char[] x$0) throws IOException {
        return Integer.valueOf(super.read(x$0));
    }

    @Override // java.io.Reader
    public int read(char[] cbuf) throws UncheckedIOException {
        return ((Integer) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return UncheckedBufferedReader.this.m2133x59047fff((char[]) obj);
            }
        }, cbuf)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$read$4$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    public /* synthetic */ Integer m2134xe63f3180(char[] x$0, int x$1, int x$2) throws IOException {
        return Integer.valueOf(super.read(x$0, x$1, x$2));
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public int read(char[] cbuf, int off, int len) throws UncheckedIOException {
        return ((Integer) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda4
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                return UncheckedBufferedReader.this.m2134xe63f3180((char[]) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        }, cbuf, Integer.valueOf(off), Integer.valueOf(len))).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$read$5$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    public /* synthetic */ Integer m2135x7379e301(CharBuffer x$0) throws IOException {
        return Integer.valueOf(super.read(x$0));
    }

    @Override // java.io.Reader, java.lang.Readable
    public int read(CharBuffer target) throws UncheckedIOException {
        return ((Integer) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda3
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return UncheckedBufferedReader.this.m2135x7379e301((CharBuffer) obj);
            }
        }, target)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$readLine$6$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    public /* synthetic */ String m2136x7a7a2016() throws IOException {
        return super.readLine();
    }

    @Override // java.io.BufferedReader
    public String readLine() throws UncheckedIOException {
        return (String) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                return UncheckedBufferedReader.this.m2136x7a7a2016();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$ready$7$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    public /* synthetic */ boolean m2137xc2f09bf6() throws IOException {
        return super.ready();
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public boolean ready() throws UncheckedIOException {
        return Uncheck.getAsBoolean(new IOBooleanSupplier() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda8
            @Override // org.apache.commons.io.function.IOBooleanSupplier
            public final boolean getAsBoolean() {
                return UncheckedBufferedReader.this.m2137xc2f09bf6();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$reset$8$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    public /* synthetic */ void m2138x27249823() throws IOException {
        super.reset();
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public void reset() throws UncheckedIOException {
        Uncheck.run(new IORunnable() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda7
            @Override // org.apache.commons.io.function.IORunnable
            public final void run() {
                UncheckedBufferedReader.this.m2138x27249823();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$skip$9$org-apache-commons-io-input-UncheckedBufferedReader, reason: not valid java name */
    public /* synthetic */ Long m2139x10708c2e(long x$0) throws IOException {
        return Long.valueOf(super.skip(x$0));
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public long skip(long n) throws UncheckedIOException {
        return ((Long) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.input.UncheckedBufferedReader$$ExternalSyntheticLambda5
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return UncheckedBufferedReader.this.m2139x10708c2e(((Long) obj).longValue());
            }
        }, Long.valueOf(n))).longValue();
    }
}
