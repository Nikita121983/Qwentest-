package org.apache.commons.collections4;

import java.util.function.Consumer;

@Deprecated
/* loaded from: classes9.dex */
public interface Closure<T> extends Consumer<T> {
    void execute(T t);

    @Override // java.util.function.Consumer
    default void accept(T input) {
        execute(input);
    }
}
