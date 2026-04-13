package org.apache.commons.lang3.concurrent;

import androidx.lifecycle.AtomicReference$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.lang3.concurrent.AbstractConcurrentInitializer;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableSupplier;

/* loaded from: classes9.dex */
public class AtomicSafeInitializer<T> extends AbstractConcurrentInitializer<T, ConcurrentException> {
    private static final Object NO_INIT = new Object();
    private final AtomicReference<AtomicSafeInitializer<T>> factory;
    private final AtomicReference<T> reference;

    /* loaded from: classes9.dex */
    public static class Builder<I extends AtomicSafeInitializer<T>, T> extends AbstractConcurrentInitializer.AbstractBuilder<I, T, Builder<I, T>, ConcurrentException> {
        @Override // org.apache.commons.lang3.function.FailableSupplier
        public I get() {
            return (I) new AtomicSafeInitializer(getInitializer(), getCloser());
        }
    }

    public static <T> Builder<AtomicSafeInitializer<T>, T> builder() {
        return new Builder<>();
    }

    public AtomicSafeInitializer() {
        this.factory = new AtomicReference<>();
        this.reference = new AtomicReference<>(getNoInit());
    }

    private AtomicSafeInitializer(FailableSupplier<T, ConcurrentException> initializer, FailableConsumer<T, ConcurrentException> closer) {
        super(initializer, closer);
        this.factory = new AtomicReference<>();
        this.reference = new AtomicReference<>(getNoInit());
    }

    @Override // org.apache.commons.lang3.function.FailableSupplier
    public final T get() throws ConcurrentException {
        while (true) {
            T result = this.reference.get();
            if (result != getNoInit()) {
                return result;
            }
            if (AtomicReference$$ExternalSyntheticBackportWithForwarding0.m(this.factory, null, this)) {
                this.reference.set(initialize());
            }
        }
    }

    private T getNoInit() {
        return (T) NO_INIT;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.concurrent.AbstractConcurrentInitializer
    public ConcurrentException getTypedException(Exception e) {
        return new ConcurrentException(e);
    }

    @Override // org.apache.commons.lang3.concurrent.AbstractConcurrentInitializer
    public boolean isInitialized() {
        return this.reference.get() != NO_INIT;
    }
}
