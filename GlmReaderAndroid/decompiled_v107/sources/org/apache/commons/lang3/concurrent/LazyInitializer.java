package org.apache.commons.lang3.concurrent;

import org.apache.commons.lang3.concurrent.AbstractConcurrentInitializer;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableSupplier;

/* loaded from: classes9.dex */
public class LazyInitializer<T> extends AbstractConcurrentInitializer<T, ConcurrentException> {
    private static final Object NO_INIT = new Object();
    private volatile T object;

    /* loaded from: classes9.dex */
    public static class Builder<I extends LazyInitializer<T>, T> extends AbstractConcurrentInitializer.AbstractBuilder<I, T, Builder<I, T>, ConcurrentException> {
        @Override // org.apache.commons.lang3.function.FailableSupplier
        public I get() {
            return (I) new LazyInitializer(getInitializer(), getCloser());
        }
    }

    public static <T> Builder<LazyInitializer<T>, T> builder() {
        return new Builder<>();
    }

    public LazyInitializer() {
        this.object = (T) NO_INIT;
    }

    private LazyInitializer(FailableSupplier<T, ConcurrentException> failableSupplier, FailableConsumer<T, ConcurrentException> failableConsumer) {
        super(failableSupplier, failableConsumer);
        this.object = (T) NO_INIT;
    }

    @Override // org.apache.commons.lang3.function.FailableSupplier
    public T get() throws ConcurrentException {
        T result = this.object;
        if (result == NO_INIT) {
            synchronized (this) {
                result = this.object;
                if (result == NO_INIT) {
                    T initialize = initialize();
                    result = initialize;
                    this.object = initialize;
                }
            }
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.lang3.concurrent.AbstractConcurrentInitializer
    public ConcurrentException getTypedException(Exception e) {
        return new ConcurrentException(e);
    }

    @Override // org.apache.commons.lang3.concurrent.AbstractConcurrentInitializer
    public boolean isInitialized() {
        return this.object != NO_INIT;
    }
}
