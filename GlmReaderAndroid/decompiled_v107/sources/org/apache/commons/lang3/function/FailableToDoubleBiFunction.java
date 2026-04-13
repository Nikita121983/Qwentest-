package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface FailableToDoubleBiFunction<T, U, E extends Throwable> {
    public static final FailableToDoubleBiFunction NOP = new FailableToDoubleBiFunction() { // from class: org.apache.commons.lang3.function.FailableToDoubleBiFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableToDoubleBiFunction
        public final double applyAsDouble(Object obj, Object obj2) {
            return FailableToDoubleBiFunction.lambda$static$0(obj, obj2);
        }
    };

    double applyAsDouble(T t, U u) throws Throwable;

    static /* synthetic */ double lambda$static$0(Object t, Object u) throws Throwable {
        return 0.0d;
    }

    static <T, U, E extends Throwable> FailableToDoubleBiFunction<T, U, E> nop() {
        return NOP;
    }
}
