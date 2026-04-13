package org.apache.commons.collections4;

import java.util.function.Supplier;

@Deprecated
/* loaded from: classes9.dex */
public interface Factory<T> extends Supplier<T> {
    T create();

    @Override // java.util.function.Supplier
    default T get() {
        return create();
    }
}
