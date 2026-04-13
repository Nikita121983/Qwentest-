package org.apache.commons.lang3.function;

import java.util.function.Function;

/* loaded from: classes9.dex */
public final class Functions {
    public static <T, R> R apply(Function<T, R> function, T object) {
        if (function != null) {
            return function.apply(object);
        }
        return null;
    }

    public static <T, R> Function<T, R> function(Function<T, R> function) {
        return function;
    }

    private Functions() {
    }
}
