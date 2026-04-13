package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface FailableConsumer<T, E extends Throwable> {
    public static final FailableConsumer NOP;

    void accept(T t) throws Throwable;

    static {
        final Function identity = Function.identity();
        Objects.requireNonNull(identity);
        NOP = new FailableConsumer() { // from class: org.apache.commons.lang3.function.FailableConsumer$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.FailableConsumer
            public final void accept(Object obj) {
                identity.apply(obj);
            }
        };
    }

    static <T, E extends Throwable> FailableConsumer<T, E> nop() {
        return NOP;
    }

    default FailableConsumer<T, E> andThen(final FailableConsumer<? super T, E> after) {
        Objects.requireNonNull(after);
        return new FailableConsumer() { // from class: org.apache.commons.lang3.function.FailableConsumer$$ExternalSyntheticLambda1
            @Override // org.apache.commons.lang3.function.FailableConsumer
            public final void accept(Object obj) {
                FailableConsumer.lambda$andThen$0(FailableConsumer.this, after, obj);
            }
        };
    }

    static /* synthetic */ void lambda$andThen$0(FailableConsumer _this, FailableConsumer after, Object t) throws Throwable {
        _this.accept(t);
        after.accept(t);
    }
}
