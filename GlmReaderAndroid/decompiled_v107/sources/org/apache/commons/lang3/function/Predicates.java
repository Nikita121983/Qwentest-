package org.apache.commons.lang3.function;

import java.util.function.Predicate;

/* loaded from: classes9.dex */
public class Predicates {
    private static final Predicate<?> TRUE = new Predicate() { // from class: org.apache.commons.lang3.function.Predicates$$ExternalSyntheticLambda0
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return Predicates.lambda$static$0(obj);
        }
    };
    private static final Predicate<?> FALSE = new Predicate() { // from class: org.apache.commons.lang3.function.Predicates$$ExternalSyntheticLambda1
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return Predicates.lambda$static$1(obj);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$0(Object t) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$1(Object t) {
        return false;
    }

    public static <T> Predicate<T> falsePredicate() {
        return (Predicate<T>) FALSE;
    }

    public static <T> Predicate<T> truePredicate() {
        return (Predicate<T>) TRUE;
    }

    private Predicates() {
    }
}
