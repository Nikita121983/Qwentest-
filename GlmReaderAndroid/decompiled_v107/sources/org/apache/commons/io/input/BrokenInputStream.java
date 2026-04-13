package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;
import org.apache.commons.io.function.Erase;

/* loaded from: classes9.dex */
public class BrokenInputStream extends InputStream {
    public static final BrokenInputStream INSTANCE = new BrokenInputStream();
    private final Supplier<Throwable> exceptionSupplier;

    public BrokenInputStream() {
        this((Supplier<Throwable>) new Supplier() { // from class: org.apache.commons.io.input.BrokenInputStream$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return BrokenInputStream.lambda$new$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Throwable lambda$new$0() {
        return new IOException("Broken input stream");
    }

    @Deprecated
    public BrokenInputStream(final IOException exception) {
        this((Supplier<Throwable>) new Supplier() { // from class: org.apache.commons.io.input.BrokenInputStream$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return BrokenInputStream.lambda$new$1(exception);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Throwable lambda$new$1(IOException exception) {
        return exception;
    }

    public BrokenInputStream(Supplier<Throwable> exceptionSupplier) {
        this.exceptionSupplier = exceptionSupplier;
    }

    public BrokenInputStream(final Throwable exception) {
        this((Supplier<Throwable>) new Supplier() { // from class: org.apache.commons.io.input.BrokenInputStream$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return BrokenInputStream.lambda$new$2(exception);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Throwable lambda$new$2(Throwable exception) {
        return exception;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        throw rethrow();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        throw rethrow();
    }

    Throwable getThrowable() {
        return this.exceptionSupplier.get();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        throw rethrow();
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        throw rethrow();
    }

    private RuntimeException rethrow() {
        return Erase.rethrow(getThrowable());
    }

    @Override // java.io.InputStream
    public long skip(long n) throws IOException {
        throw rethrow();
    }
}
