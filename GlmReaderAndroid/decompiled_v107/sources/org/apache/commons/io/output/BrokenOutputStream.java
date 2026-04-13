package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.commons.io.function.Erase;

/* loaded from: classes9.dex */
public class BrokenOutputStream extends OutputStream {
    public static final BrokenOutputStream INSTANCE = new BrokenOutputStream();
    private final Function<String, Throwable> exceptionFunction;

    public BrokenOutputStream() {
        this((Function<String, Throwable>) new Function() { // from class: org.apache.commons.io.output.BrokenOutputStream$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return BrokenOutputStream.lambda$new$0((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Throwable lambda$new$0(String m) {
        return new IOException("Broken output stream: " + m);
    }

    public BrokenOutputStream(Function<String, Throwable> exceptionFunction) {
        this.exceptionFunction = exceptionFunction;
    }

    @Deprecated
    public BrokenOutputStream(final IOException exception) {
        this((Function<String, Throwable>) new Function() { // from class: org.apache.commons.io.output.BrokenOutputStream$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return BrokenOutputStream.lambda$new$1(exception, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Throwable lambda$new$1(IOException exception, String m) {
        return exception;
    }

    @Deprecated
    public BrokenOutputStream(final Supplier<Throwable> exceptionSupplier) {
        this.exceptionFunction = new Function() { // from class: org.apache.commons.io.output.BrokenOutputStream$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return BrokenOutputStream.lambda$new$2(exceptionSupplier, (String) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Throwable lambda$new$2(Supplier exceptionSupplier, String m) {
        return (Throwable) exceptionSupplier.get();
    }

    public BrokenOutputStream(final Throwable exception) {
        this((Function<String, Throwable>) new Function() { // from class: org.apache.commons.io.output.BrokenOutputStream$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return BrokenOutputStream.lambda$new$3(exception, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Throwable lambda$new$3(Throwable exception, String m) {
        return exception;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        throw rethrow("close()");
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        throw rethrow("flush()");
    }

    private RuntimeException rethrow(String method) {
        return Erase.rethrow(this.exceptionFunction.apply(method));
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        throw rethrow("write(int)");
    }
}
