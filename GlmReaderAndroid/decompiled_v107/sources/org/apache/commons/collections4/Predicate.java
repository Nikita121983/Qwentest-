package org.apache.commons.collections4;

@Deprecated
/* loaded from: classes9.dex */
public interface Predicate<T> extends java.util.function.Predicate<T> {
    boolean evaluate(T t);

    @Override // java.util.function.Predicate
    default boolean test(T t) {
        return evaluate(t);
    }
}
