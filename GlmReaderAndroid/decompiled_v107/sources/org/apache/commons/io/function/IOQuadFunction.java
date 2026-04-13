package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface IOQuadFunction<T, U, V, W, R> {
    R apply(T t, U u, V v, W w) throws IOException;

    default <X> IOQuadFunction<T, U, V, W, X> andThen(final IOFunction<? super R, ? extends X> after) {
        Objects.requireNonNull(after);
        return new IOQuadFunction() { // from class: org.apache.commons.io.function.IOQuadFunction$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOQuadFunction
            public final Object apply(Object obj, Object obj2, Object obj3, Object obj4) {
                Object apply;
                apply = after.apply(IOQuadFunction.this.apply(obj, obj2, obj3, obj4));
                return apply;
            }
        };
    }
}
