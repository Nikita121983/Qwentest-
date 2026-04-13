package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface FailableIntToDoubleFunction<E extends Throwable> {
    public static final FailableIntToDoubleFunction NOP = new FailableIntToDoubleFunction() { // from class: org.apache.commons.lang3.function.FailableIntToDoubleFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableIntToDoubleFunction
        public final double applyAsDouble(int i) {
            return FailableIntToDoubleFunction.lambda$static$0(i);
        }
    };

    double applyAsDouble(int i) throws Throwable;

    static /* synthetic */ double lambda$static$0(int t) throws Throwable {
        return 0.0d;
    }

    static <E extends Throwable> FailableIntToDoubleFunction<E> nop() {
        return NOP;
    }
}
