package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Supplier;
import org.apache.commons.io.function.Erase;

/* loaded from: classes9.dex */
public class BrokenReader extends Reader {
    public static final BrokenReader INSTANCE = new BrokenReader();
    private final Supplier<Throwable> exceptionSupplier;

    public BrokenReader() {
        this((Supplier<Throwable>) new Supplier() { // from class: org.apache.commons.io.input.BrokenReader$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return BrokenReader.lambda$new$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Throwable lambda$new$0() {
        return new IOException("Broken reader");
    }

    @Deprecated
    public BrokenReader(final IOException exception) {
        this((Supplier<Throwable>) new Supplier() { // from class: org.apache.commons.io.input.BrokenReader$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return BrokenReader.lambda$new$1(exception);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Throwable lambda$new$1(IOException exception) {
        return exception;
    }

    public BrokenReader(Supplier<Throwable> exceptionSupplier) {
        this.exceptionSupplier = exceptionSupplier;
    }

    public BrokenReader(final Throwable exception) {
        this((Supplier<Throwable>) new Supplier() { // from class: org.apache.commons.io.input.BrokenReader$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return BrokenReader.lambda$new$2(exception);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Throwable lambda$new$2(Throwable exception) {
        return exception;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        throw rethrow();
    }

    @Override // java.io.Reader
    public void mark(int readAheadLimit) throws IOException {
        throw rethrow();
    }

    @Override // java.io.Reader
    public int read(char[] cbuf, int off, int len) throws IOException {
        throw rethrow();
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        throw rethrow();
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
        throw rethrow();
    }

    private RuntimeException rethrow() {
        return Erase.rethrow(this.exceptionSupplier.get());
    }

    @Override // java.io.Reader
    public long skip(long n) throws IOException {
        throw rethrow();
    }
}
