package org.apache.commons.lang3.function;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/* loaded from: classes9.dex */
public class Consumers {
    private static final Consumer NOP;

    static {
        final Function identity = Function.identity();
        Objects.requireNonNull(identity);
        NOP = new Consumer() { // from class: org.apache.commons.lang3.function.Consumers$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                identity.apply(obj);
            }
        };
    }

    public static <T> void accept(Consumer<T> consumer, T object) {
        if (consumer != null) {
            consumer.accept(object);
        }
    }

    public static <T> Consumer<T> nop() {
        return NOP;
    }

    private Consumers() {
    }
}
