package org.apache.commons.collections4;

import java.util.function.Function;

@FunctionalInterface
@Deprecated
/* loaded from: classes9.dex */
public interface Transformer<T, R> extends Function<T, R> {
    R transform(T t);

    @Override // java.util.function.Function
    default R apply(T t) {
        return transform(t);
    }
}
