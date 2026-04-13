package org.apache.commons.lang3.mutable;

import java.util.function.Supplier;

/* loaded from: classes9.dex */
public interface Mutable<T> extends Supplier<T> {
    @Deprecated
    T getValue();

    void setValue(T t);

    @Override // java.util.function.Supplier
    default T get() {
        return getValue();
    }
}
