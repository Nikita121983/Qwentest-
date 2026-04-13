package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Predicate;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface IOPredicate<T> {
    boolean test(T t) throws IOException;

    static <T> IOPredicate<T> alwaysFalse() {
        return (IOPredicate<T>) Constants.IO_PREDICATE_FALSE;
    }

    static <T> IOPredicate<T> alwaysTrue() {
        return (IOPredicate<T>) Constants.IO_PREDICATE_TRUE;
    }

    static <T> IOPredicate<T> isEqual(final Object target) {
        return target == null ? new IOPredicate() { // from class: org.apache.commons.io.function.IOPredicate$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOPredicate
            public final boolean test(Object obj) {
                boolean isNull;
                isNull = Objects.isNull(obj);
                return isNull;
            }
        } : new IOPredicate() { // from class: org.apache.commons.io.function.IOPredicate$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOPredicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = target.equals(obj);
                return equals;
            }
        };
    }

    default IOPredicate<T> and(final IOPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return new IOPredicate() { // from class: org.apache.commons.io.function.IOPredicate$$ExternalSyntheticLambda4
            @Override // org.apache.commons.io.function.IOPredicate
            public final boolean test(Object obj) {
                return IOPredicate.lambda$and$1(IOPredicate.this, other, obj);
            }
        };
    }

    static /* synthetic */ boolean lambda$and$1(IOPredicate _this, IOPredicate other, Object t) throws IOException {
        return _this.test(t) && other.test(t);
    }

    default Predicate<T> asPredicate() {
        return new Predicate() { // from class: org.apache.commons.io.function.IOPredicate$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean test;
                test = Uncheck.test(IOPredicate.this, obj);
                return test;
            }
        };
    }

    static /* synthetic */ boolean lambda$negate$3(IOPredicate _this, Object t) throws IOException {
        return !_this.test(t);
    }

    default IOPredicate<T> negate() {
        return new IOPredicate() { // from class: org.apache.commons.io.function.IOPredicate$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOPredicate
            public final boolean test(Object obj) {
                return IOPredicate.lambda$negate$3(IOPredicate.this, obj);
            }
        };
    }

    default IOPredicate<T> or(final IOPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return new IOPredicate() { // from class: org.apache.commons.io.function.IOPredicate$$ExternalSyntheticLambda3
            @Override // org.apache.commons.io.function.IOPredicate
            public final boolean test(Object obj) {
                return IOPredicate.lambda$or$4(IOPredicate.this, other, obj);
            }
        };
    }

    static /* synthetic */ boolean lambda$or$4(IOPredicate _this, IOPredicate other, Object t) throws IOException {
        return _this.test(t) || other.test(t);
    }
}
