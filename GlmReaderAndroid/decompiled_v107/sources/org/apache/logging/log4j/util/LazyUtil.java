package org.apache.logging.log4j.util;

import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes10.dex */
final class LazyUtil {
    private static final Object NULL = new Object() { // from class: org.apache.logging.log4j.util.LazyUtil.1
        public String toString() {
            return "null";
        }
    };

    LazyUtil() {
    }

    static Object wrapNull(final Object value) {
        return value == null ? NULL : value;
    }

    static <T> T unwrapNull(Object obj) {
        if (obj == NULL) {
            return null;
        }
        return (T) Cast.cast(obj);
    }

    /* loaded from: classes10.dex */
    static class Constant<T> implements Lazy<T> {
        private final T value;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Constant(final T value) {
            this.value = value;
        }

        @Override // org.apache.logging.log4j.util.Lazy
        public T value() {
            return this.value;
        }

        @Override // org.apache.logging.log4j.util.Lazy
        public boolean isInitialized() {
            return true;
        }

        @Override // org.apache.logging.log4j.util.Lazy
        public void set(final T newValue) {
            throw new UnsupportedOperationException();
        }

        public String toString() {
            return String.valueOf(this.value);
        }
    }

    /* loaded from: classes10.dex */
    static class WeakConstant<T> implements Lazy<T> {
        private final WeakReference<T> reference;

        /* JADX INFO: Access modifiers changed from: package-private */
        public WeakConstant(final T value) {
            this.reference = new WeakReference<>(value);
        }

        @Override // org.apache.logging.log4j.util.Lazy
        public T value() {
            return this.reference.get();
        }

        @Override // org.apache.logging.log4j.util.Lazy
        public boolean isInitialized() {
            return true;
        }

        @Override // org.apache.logging.log4j.util.Lazy
        public void set(final T newValue) {
            throw new UnsupportedOperationException();
        }

        public String toString() {
            return String.valueOf(value());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class SafeLazy<T> implements Lazy<T> {
        private final Lock lock = new ReentrantLock();
        private final java.util.function.Supplier<T> supplier;
        private volatile Object value;

        /* JADX INFO: Access modifiers changed from: package-private */
        public SafeLazy(final java.util.function.Supplier<T> supplier) {
            this.supplier = supplier;
        }

        @Override // org.apache.logging.log4j.util.Lazy
        public T value() {
            Object obj = this.value;
            if (obj == null) {
                this.lock.lock();
                try {
                    obj = this.value;
                    if (obj == null) {
                        obj = this.supplier.get();
                        this.value = LazyUtil.wrapNull(obj);
                    }
                } finally {
                    this.lock.unlock();
                }
            }
            return (T) LazyUtil.unwrapNull(obj);
        }

        @Override // org.apache.logging.log4j.util.Lazy
        public void set(final T newValue) {
            this.value = newValue;
        }

        public void reset() {
            this.value = null;
        }

        @Override // org.apache.logging.log4j.util.Lazy
        public boolean isInitialized() {
            return this.value != null;
        }

        public String toString() {
            return isInitialized() ? String.valueOf(this.value) : "Lazy value not initialized";
        }
    }

    /* loaded from: classes10.dex */
    static class PureLazy<T> implements Lazy<T> {
        private final java.util.function.Supplier<T> supplier;
        private Object value;

        public PureLazy(final java.util.function.Supplier<T> supplier) {
            this.supplier = supplier;
        }

        @Override // org.apache.logging.log4j.util.Lazy
        public T value() {
            Object obj = this.value;
            if (obj == null) {
                obj = this.supplier.get();
                this.value = LazyUtil.wrapNull(obj);
            }
            return (T) LazyUtil.unwrapNull(obj);
        }

        @Override // org.apache.logging.log4j.util.Lazy
        public boolean isInitialized() {
            return this.value != null;
        }

        @Override // org.apache.logging.log4j.util.Lazy
        public void set(final T newValue) {
            this.value = newValue;
        }
    }
}
