package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface FailableLongToDoubleFunction<E extends Throwable> {
    public static final FailableLongToDoubleFunction NOP = new FailableLongToDoubleFunction() { // from class: org.apache.commons.lang3.function.FailableLongToDoubleFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableLongToDoubleFunction
        public final double applyAsDouble(long j) {
            return FailableLongToDoubleFunction.lambda$static$0(j);
        }
    };

    double applyAsDouble(long j) throws Throwable;

    static /* synthetic */ double lambda$static$0(long t) throws Throwable {
        return 0.0d;
    }

    static <E extends Throwable> FailableLongToDoubleFunction<E> nop() {
        return NOP;
    }
}
