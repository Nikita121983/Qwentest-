package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface FailableIntToFloatFunction<E extends Throwable> {
    public static final FailableIntToFloatFunction NOP = new FailableIntToFloatFunction() { // from class: org.apache.commons.lang3.function.FailableIntToFloatFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableIntToFloatFunction
        public final float applyAsFloat(int i) {
            return FailableIntToFloatFunction.lambda$static$0(i);
        }
    };

    float applyAsFloat(int i) throws Throwable;

    static /* synthetic */ float lambda$static$0(int t) throws Throwable {
        return 0.0f;
    }

    static <E extends Throwable> FailableIntToFloatFunction<E> nop() {
        return NOP;
    }
}
