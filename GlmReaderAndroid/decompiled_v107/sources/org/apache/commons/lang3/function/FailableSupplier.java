package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface FailableSupplier<T, E extends Throwable> {
    public static final FailableSupplier NUL = new FailableSupplier() { // from class: org.apache.commons.lang3.function.FailableSupplier$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableSupplier
        public final Object get() {
            return FailableSupplier.lambda$static$0();
        }
    };

    T get() throws Throwable;

    static /* synthetic */ Object lambda$static$0() throws Throwable {
        return null;
    }

    static <T, E extends Exception> FailableSupplier<T, E> nul() {
        return NUL;
    }
}
