package org.apache.commons.io.input;

import java.io.FilterReader;
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
import org.apache.commons.io.input.UncheckedFilterReader;

/* loaded from: classes9.dex */
public final class UncheckedFilterReader extends FilterReader {

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<UncheckedFilterReader, Builder> {
        @Override // org.apache.commons.io.function.IOSupplier
        public UncheckedFilterReader get() {
            return (UncheckedFilterReader) Uncheck.get(new IOSupplier() { // from class: org.apache.commons.io.input.UncheckedFilterReader$Builder$$ExternalSyntheticLambda0
                @Override // org.apache.commons.io.function.IOSupplier
                public final Object get() {
                    return UncheckedFilterReader.Builder.this.m2158x24463752();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$get$0$org-apache-commons-io-input-UncheckedFilterReader$Builder, reason: not valid java name */
        public /* synthetic */ UncheckedFilterReader m2158x24463752() throws IOException {
            return new UncheckedFilterReader(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private UncheckedFilterReader(Builder builder) throws IOException {
        super(builder.getReader());
    }

    @Override // java.io.FilterReader, java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws UncheckedIOException {
        Uncheck.run(new IORunnable() { // from class: org.apache.commons.io.input.UncheckedFilterReader$$ExternalSyntheticLambda3
            @Override // org.apache.commons.io.function.IORunnable
            public final void run() {
                UncheckedFilterReader.this.m2149lambda$close$0$orgapachecommonsioinputUncheckedFilterReader();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$close$0$org-apache-commons-io-input-UncheckedFilterReader, reason: not valid java name */
    public /* synthetic */ void m2149lambda$close$0$orgapachecommonsioinputUncheckedFilterReader() throws IOException {
        super.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$mark$1$org-apache-commons-io-input-UncheckedFilterReader, reason: not valid java name */
    public /* synthetic */ void m2150lambda$mark$1$orgapachecommonsioinputUncheckedFilterReader(int x$0) throws IOException {
        super.mark(x$0);
    }

    @Override // java.io.FilterReader, java.io.Reader
    public void mark(int readAheadLimit) throws UncheckedIOException {
        Uncheck.accept(new IOIntConsumer() { // from class: org.apache.commons.io.input.UncheckedFilterReader$$ExternalSyntheticLambda6
            @Override // org.apache.commons.io.function.IOIntConsumer
            public final void accept(int i) {
                UncheckedFilterReader.this.m2150lambda$mark$1$orgapachecommonsioinputUncheckedFilterReader(i);
            }
        }, readAheadLimit);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$read$2$org-apache-commons-io-input-UncheckedFilterReader, reason: not valid java name */
    public /* synthetic */ int m2151lambda$read$2$orgapachecommonsioinputUncheckedFilterReader() throws IOException {
        return super.read();
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws UncheckedIOException {
        return Uncheck.getAsInt(new IOIntSupplier() { // from class: org.apache.commons.io.input.UncheckedFilterReader$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOIntSupplier
            public final int getAsInt() {
                return UncheckedFilterReader.this.m2151lambda$read$2$orgapachecommonsioinputUncheckedFilterReader();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$read$3$org-apache-commons-io-input-UncheckedFilterReader, reason: not valid java name */
    public /* synthetic */ Integer m2152lambda$read$3$orgapachecommonsioinputUncheckedFilterReader(char[] x$0) throws IOException {
        return Integer.valueOf(super.read(x$0));
    }

    @Override // java.io.Reader
    public int read(char[] cbuf) throws UncheckedIOException {
        return ((Integer) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.input.UncheckedFilterReader$$ExternalSyntheticLambda4
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return UncheckedFilterReader.this.m2152lambda$read$3$orgapachecommonsioinputUncheckedFilterReader((char[]) obj);
            }
        }, cbuf)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$read$4$org-apache-commons-io-input-UncheckedFilterReader, reason: not valid java name */
    public /* synthetic */ Integer m2153lambda$read$4$orgapachecommonsioinputUncheckedFilterReader(char[] x$0, int x$1, int x$2) throws IOException {
        return Integer.valueOf(super.read(x$0, x$1, x$2));
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read(char[] cbuf, int off, int len) throws UncheckedIOException {
        return ((Integer) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.input.UncheckedFilterReader$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                return UncheckedFilterReader.this.m2153lambda$read$4$orgapachecommonsioinputUncheckedFilterReader((char[]) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        }, cbuf, Integer.valueOf(off), Integer.valueOf(len))).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$read$5$org-apache-commons-io-input-UncheckedFilterReader, reason: not valid java name */
    public /* synthetic */ Integer m2154lambda$read$5$orgapachecommonsioinputUncheckedFilterReader(CharBuffer x$0) throws IOException {
        return Integer.valueOf(super.read(x$0));
    }

    @Override // java.io.Reader, java.lang.Readable
    public int read(CharBuffer target) throws UncheckedIOException {
        return ((Integer) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.input.UncheckedFilterReader$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return UncheckedFilterReader.this.m2154lambda$read$5$orgapachecommonsioinputUncheckedFilterReader((CharBuffer) obj);
            }
        }, target)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$ready$6$org-apache-commons-io-input-UncheckedFilterReader, reason: not valid java name */
    public /* synthetic */ boolean m2155lambda$ready$6$orgapachecommonsioinputUncheckedFilterReader() throws IOException {
        return super.ready();
    }

    @Override // java.io.FilterReader, java.io.Reader
    public boolean ready() throws UncheckedIOException {
        return Uncheck.getAsBoolean(new IOBooleanSupplier() { // from class: org.apache.commons.io.input.UncheckedFilterReader$$ExternalSyntheticLambda5
            @Override // org.apache.commons.io.function.IOBooleanSupplier
            public final boolean getAsBoolean() {
                return UncheckedFilterReader.this.m2155lambda$ready$6$orgapachecommonsioinputUncheckedFilterReader();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$reset$7$org-apache-commons-io-input-UncheckedFilterReader, reason: not valid java name */
    public /* synthetic */ void m2156lambda$reset$7$orgapachecommonsioinputUncheckedFilterReader() throws IOException {
        super.reset();
    }

    @Override // java.io.FilterReader, java.io.Reader
    public void reset() throws UncheckedIOException {
        Uncheck.run(new IORunnable() { // from class: org.apache.commons.io.input.UncheckedFilterReader$$ExternalSyntheticLambda7
            @Override // org.apache.commons.io.function.IORunnable
            public final void run() {
                UncheckedFilterReader.this.m2156lambda$reset$7$orgapachecommonsioinputUncheckedFilterReader();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$skip$8$org-apache-commons-io-input-UncheckedFilterReader, reason: not valid java name */
    public /* synthetic */ Long m2157lambda$skip$8$orgapachecommonsioinputUncheckedFilterReader(long x$0) throws IOException {
        return Long.valueOf(super.skip(x$0));
    }

    @Override // java.io.FilterReader, java.io.Reader
    public long skip(long n) throws UncheckedIOException {
        return ((Long) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.input.UncheckedFilterReader$$ExternalSyntheticLambda8
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return UncheckedFilterReader.this.m2157lambda$skip$8$orgapachecommonsioinputUncheckedFilterReader(((Long) obj).longValue());
            }
        }, Long.valueOf(n))).longValue();
    }
}
