package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface IOFunction<T, R> {
    R apply(T t) throws IOException;

    static <T> IOFunction<T, T> identity() {
        return Constants.IO_FUNCTION_ID;
    }

    default IOConsumer<T> andThen(final Consumer<? super R> after) {
        Objects.requireNonNull(after, "after");
        return new IOConsumer() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda5
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                after.accept(IOFunction.this.apply(obj));
            }
        };
    }

    default <V> IOFunction<T, V> andThen(final Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after, "after");
        return new IOFunction() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda4
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                Object apply;
                apply = after.apply(IOFunction.this.apply(obj));
                return apply;
            }
        };
    }

    default IOConsumer<T> andThen(final IOConsumer<? super R> after) {
        Objects.requireNonNull(after, "after");
        return new IOConsumer() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda3
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                after.accept(IOFunction.this.apply(obj));
            }
        };
    }

    default <V> IOFunction<T, V> andThen(final IOFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after, "after");
        return new IOFunction() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                Object apply;
                apply = after.apply(IOFunction.this.apply(obj));
                return apply;
            }
        };
    }

    default Function<T, R> asFunction() {
        return new Function() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Object apply;
                apply = Uncheck.apply(IOFunction.this, obj);
                return apply;
            }
        };
    }

    default <V> IOFunction<V, R> compose(final Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before, "before");
        return new IOFunction() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                Object apply;
                apply = IOFunction.this.apply(before.apply(obj));
                return apply;
            }
        };
    }

    default <V> IOFunction<V, R> compose(final IOFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before, "before");
        return new IOFunction() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda7
            @Override // org.apache.commons.io.function.IOFunction
            public final Object apply(Object obj) {
                Object apply;
                apply = IOFunction.this.apply(before.apply(obj));
                return apply;
            }
        };
    }

    default IOSupplier<R> compose(final IOSupplier<? extends T> before) {
        Objects.requireNonNull(before, "before");
        return new IOSupplier() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda6
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Object apply;
                apply = IOFunction.this.apply(before.get());
                return apply;
            }
        };
    }

    default IOSupplier<R> compose(final Supplier<? extends T> before) {
        Objects.requireNonNull(before, "before");
        return new IOSupplier() { // from class: org.apache.commons.io.function.IOFunction$$ExternalSyntheticLambda8
            @Override // org.apache.commons.io.function.IOSupplier
            public final Object get() {
                Object apply;
                apply = IOFunction.this.apply(before.get());
                return apply;
            }
        };
    }
}
