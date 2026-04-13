package org.apache.logging.log4j.util;

import java.util.Objects;
import java.util.function.Function;
import org.apache.logging.log4j.util.LazyUtil;

/* loaded from: classes10.dex */
public interface Lazy<T> extends java.util.function.Supplier<T> {
    boolean isInitialized();

    void set(final T newValue);

    T value();

    @Override // java.util.function.Supplier
    default T get() {
        return value();
    }

    default <R> Lazy<R> map(final Function<? super T, ? extends R> function) {
        return lazy(new java.util.function.Supplier() { // from class: org.apache.logging.log4j.util.Lazy$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                Object apply;
                apply = function.apply(Lazy.this.value());
                return apply;
            }
        });
    }

    static <T> Lazy<T> lazy(final java.util.function.Supplier<T> supplier) {
        Objects.requireNonNull(supplier);
        return new LazyUtil.SafeLazy(supplier);
    }

    static <T> Lazy<T> value(final T value) {
        return new LazyUtil.Constant(value);
    }

    static <T> Lazy<T> weak(final T value) {
        return new LazyUtil.WeakConstant(value);
    }

    static <T> Lazy<T> pure(final java.util.function.Supplier<T> supplier) {
        Objects.requireNonNull(supplier);
        return new LazyUtil.PureLazy(supplier);
    }
}
