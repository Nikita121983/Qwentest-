package org.apache.commons.io.output;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOIntConsumer;
import org.apache.commons.io.function.IORunnable;
import org.apache.commons.io.function.IOTriConsumer;
import org.apache.commons.io.function.Uncheck;

/* loaded from: classes9.dex */
public final class UncheckedFilterOutputStream extends FilterOutputStream {

    /* loaded from: classes9.dex */
    public static class Builder extends AbstractStreamBuilder<UncheckedFilterOutputStream, Builder> {
        @Override // org.apache.commons.io.function.IOSupplier
        public UncheckedFilterOutputStream get() throws IOException {
            return new UncheckedFilterOutputStream(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private UncheckedFilterOutputStream(Builder builder) throws IOException {
        super(builder.getOutputStream());
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws UncheckedIOException {
        Uncheck.run(new IORunnable() { // from class: org.apache.commons.io.output.UncheckedFilterOutputStream$$ExternalSyntheticLambda4
            @Override // org.apache.commons.io.function.IORunnable
            public final void run() {
                UncheckedFilterOutputStream.this.m2164x705c6664();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$close$0$org-apache-commons-io-output-UncheckedFilterOutputStream, reason: not valid java name */
    public /* synthetic */ void m2164x705c6664() throws IOException {
        super.close();
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws UncheckedIOException {
        Uncheck.run(new IORunnable() { // from class: org.apache.commons.io.output.UncheckedFilterOutputStream$$ExternalSyntheticLambda3
            @Override // org.apache.commons.io.function.IORunnable
            public final void run() {
                UncheckedFilterOutputStream.this.m2165x3ee88f7();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$flush$1$org-apache-commons-io-output-UncheckedFilterOutputStream, reason: not valid java name */
    public /* synthetic */ void m2165x3ee88f7() throws IOException {
        super.flush();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$write$2$org-apache-commons-io-output-UncheckedFilterOutputStream, reason: not valid java name */
    public /* synthetic */ void m2166x6026fe5b(byte[] x$0) throws IOException {
        super.write(x$0);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] b) throws UncheckedIOException {
        Uncheck.accept((IOConsumer<byte[]>) new IOConsumer() { // from class: org.apache.commons.io.output.UncheckedFilterOutputStream$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                UncheckedFilterOutputStream.this.m2166x6026fe5b((byte[]) obj);
            }
        }, b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$write$3$org-apache-commons-io-output-UncheckedFilterOutputStream, reason: not valid java name */
    public /* synthetic */ void m2167xed14157a(byte[] x$0, int x$1, int x$2) throws IOException {
        super.write(x$0, x$1, x$2);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] b, int off, int len) throws UncheckedIOException {
        Uncheck.accept(new IOTriConsumer() { // from class: org.apache.commons.io.output.UncheckedFilterOutputStream$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOTriConsumer
            public final void accept(Object obj, Object obj2, Object obj3) {
                UncheckedFilterOutputStream.this.m2167xed14157a((byte[]) obj, ((Integer) obj2).intValue(), ((Integer) obj3).intValue());
            }
        }, b, Integer.valueOf(off), Integer.valueOf(len));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$write$4$org-apache-commons-io-output-UncheckedFilterOutputStream, reason: not valid java name */
    public /* synthetic */ void m2168x7a012c99(int x$0) throws IOException {
        super.write(x$0);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws UncheckedIOException {
        Uncheck.accept(new IOIntConsumer() { // from class: org.apache.commons.io.output.UncheckedFilterOutputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOIntConsumer
            public final void accept(int i) {
                UncheckedFilterOutputStream.this.m2168x7a012c99(i);
            }
        }, b);
    }
}
