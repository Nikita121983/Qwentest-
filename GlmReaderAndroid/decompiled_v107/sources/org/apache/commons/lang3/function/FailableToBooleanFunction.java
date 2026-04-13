package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface FailableToBooleanFunction<T, E extends Throwable> {
    public static final FailableToBooleanFunction NOP = new FailableToBooleanFunction() { // from class: org.apache.commons.lang3.function.FailableToBooleanFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableToBooleanFunction
        public final boolean applyAsBoolean(Object obj) {
            return FailableToBooleanFunction.lambda$static$0(obj);
        }
    };

    boolean applyAsBoolean(T t) throws Throwable;

    static /* synthetic */ boolean lambda$static$0(Object t) throws Throwable {
        return false;
    }

    static <T, E extends Throwable> FailableToBooleanFunction<T, E> nop() {
        return NOP;
    }
}
