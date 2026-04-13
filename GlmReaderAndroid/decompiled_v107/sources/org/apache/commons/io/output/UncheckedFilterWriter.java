package org.apache.commons.io.output;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOFunction;
import org.apache.commons.io.function.IOIntConsumer;
import org.apache.commons.io.function.IORunnable;
import org.apache.commons.io.function.IOTriConsumer;
import org.apache.commons.io.function.IOTriFunction;
import org.apache.commons.io.function.Uncheck;

/* loaded from: classes9.dex */
public final class UncheckedFilterWriter extends FilterWriter {

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<UncheckedFilterWriter, Builder> {
        @Override // org.apache.commons.io.function.IOSupplier
        public UncheckedFilterWriter get() throws IOException {
            return new UncheckedFilterWriter(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private UncheckedFilterWriter(Builder builder) throws IOException {
        super(builder.getWriter());
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c) throws UncheckedIOException {
        return (Writer) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.output.UncheckedFilterWriter$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return UncheckedFilterWriter.this.m2169xc7671afa(((Character) obj).charValue());
            }
        }, Character.valueOf(c));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$append$0$org-apache-commons-io-output-UncheckedFilterWriter, reason: not valid java name */
    public /* synthetic */ Writer m2169xc7671afa(char x$0) throws IOException {
        return super.append(x$0);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence csq) throws UncheckedIOException {
        return (Writer) Uncheck.apply(new IOFunction() { // from class: org.apache.commons.io.output.UncheckedFilterWriter$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                return UncheckedFilterWriter.this.m2170xf53fb559((CharSequence) obj);
            }
        }, csq);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$append$1$org-apache-commons-io-output-UncheckedFilterWriter, reason: not valid java name */
    public /* synthetic */ Writer m2170xf53fb559(CharSequence x$0) throws IOException {
        return super.append(x$0);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence csq, int start, int end) throws UncheckedIOException {
        return (Writer) Uncheck.apply(new IOTriFunction() { // from class: org.apache.commons.io.output.UncheckedFilterWriter$$ExternalSyntheticLambda5
            @Override // org.apache.commons.io.function.IOTriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                return UncheckedFilterWriter.this.m2171x23184fb8((CharSequence) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        }, csq, Integer.valueOf(start), Integer.valueOf(end));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$append$2$org-apache-commons-io-output-UncheckedFilterWriter, reason: not valid java name */
    public /* synthetic */ Writer m2171x23184fb8(CharSequence x$0, int x$1, int x$2) throws IOException {
        return super.append(x$0, x$1, x$2);
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws UncheckedIOException {
        Uncheck.run(new IORunnable() { // from class: org.apache.commons.io.output.UncheckedFilterWriter$$ExternalSyntheticLambda6
            @Override // org.apache.commons.io.function.IORunnable
            public final void run() {
                UncheckedFilterWriter.this.m2172x17bbe853();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$close$3$org-apache-commons-io-output-UncheckedFilterWriter, reason: not valid java name */
    public /* synthetic */ void m2172x17bbe853() throws IOException {
        super.close();
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Flushable
    public void flush() throws UncheckedIOException {
        Uncheck.run(new IORunnable() { // from class: org.apache.commons.io.output.UncheckedFilterWriter$$ExternalSyntheticLambda4
            @Override // org.apache.commons.io.function.IORunnable
            public final void run() {
                UncheckedFilterWriter.this.m2173x41f43526();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$flush$4$org-apache-commons-io-output-UncheckedFilterWriter, reason: not valid java name */
    public /* synthetic */ void m2173x41f43526() throws IOException {
        super.flush();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$write$5$org-apache-commons-io-output-UncheckedFilterWriter, reason: not valid java name */
    public /* synthetic */ void m2174x7979858a(char[] x$0) throws IOException {
        super.write(x$0);
    }

    @Override // java.io.Writer
    public void write(char[] cbuf) throws UncheckedIOException {
        Uncheck.accept((IOConsumer<char[]>) new IOConsumer() { // from class: org.apache.commons.io.output.UncheckedFilterWriter$$ExternalSyntheticLambda7
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                UncheckedFilterWriter.this.m2174x7979858a((char[]) obj);
            }
        }, cbuf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$write$6$org-apache-commons-io-output-UncheckedFilterWriter, reason: not valid java name */
    public /* synthetic */ void m2175xa7521fe9(char[] x$0, int x$1, int x$2) throws IOException {
        super.write(x$0, x$1, x$2);
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] cbuf, int off, int len) throws UncheckedIOException {
        Uncheck.accept(new IOTriConsumer() { // from class: org.apache.commons.io.output.UncheckedFilterWriter$$ExternalSyntheticLambda3
            @Override // org.apache.commons.io.function.IOTriConsumer
            public final void accept(Object obj, Object obj2, Object obj3) {
                UncheckedFilterWriter.this.m2175xa7521fe9((char[]) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        }, cbuf, Integer.valueOf(off), Integer.valueOf(len));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$write$7$org-apache-commons-io-output-UncheckedFilterWriter, reason: not valid java name */
    public /* synthetic */ void m2176xd52aba48(int x$0) throws IOException {
        super.write(x$0);
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int c) throws UncheckedIOException {
        Uncheck.accept(new IOIntConsumer() { // from class: org.apache.commons.io.output.UncheckedFilterWriter$$ExternalSyntheticLambda9
            @Override // org.apache.commons.io.function.IOIntConsumer
            public final void accept(int i) {
                UncheckedFilterWriter.this.m2176xd52aba48(i);
            }
        }, c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$write$8$org-apache-commons-io-output-UncheckedFilterWriter, reason: not valid java name */
    public /* synthetic */ void m2177x30354a7(String x$0) throws IOException {
        super.write(x$0);
    }

    @Override // java.io.Writer
    public void write(String str) throws UncheckedIOException {
        Uncheck.accept((IOConsumer<String>) new IOConsumer() { // from class: org.apache.commons.io.output.UncheckedFilterWriter$$ExternalSyntheticLambda8
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                UncheckedFilterWriter.this.m2177x30354a7((String) obj);
            }
        }, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$write$9$org-apache-commons-io-output-UncheckedFilterWriter, reason: not valid java name */
    public /* synthetic */ void m2178x30dbef06(String x$0, int x$1, int x$2) throws IOException {
        super.write(x$0, x$1, x$2);
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(String str, int off, int len) throws UncheckedIOException {
        Uncheck.accept(new IOTriConsumer() { // from class: org.apache.commons.io.output.UncheckedFilterWriter$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOTriConsumer
            public final void accept(Object obj, Object obj2, Object obj3) {
                UncheckedFilterWriter.this.m2178x30dbef06((String) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        }, str, Integer.valueOf(off), Integer.valueOf(len));
    }
}
