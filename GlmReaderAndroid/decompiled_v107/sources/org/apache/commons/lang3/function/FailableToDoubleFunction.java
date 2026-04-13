package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface FailableToDoubleFunction<T, E extends Throwable> {
    public static final FailableToDoubleFunction NOP = new FailableToDoubleFunction() { // from class: org.apache.commons.lang3.function.FailableToDoubleFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableToDoubleFunction
        public final double applyAsDouble(Object obj) {
            return FailableToDoubleFunction.lambda$static$0(obj);
        }
    };

    double applyAsDouble(T t) throws Throwable;

    static /* synthetic */ double lambda$static$0(Object t) throws Throwable {
        return 0.0d;
    }

    static <T, E extends Throwable> FailableToDoubleFunction<T, E> nop() {
        return NOP;
    }
}
