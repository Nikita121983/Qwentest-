package org.apache.commons.collections4.functors;

import java.util.Collection;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes9.dex */
public final class AnyPredicate<T> extends AbstractQuantifierPredicate<T> {
    private static final long serialVersionUID = 7429999530934647542L;

    public static <T> Predicate<T> anyPredicate(Collection<? extends Predicate<? super T>> predicates) {
        Predicate<T>[] validate = FunctorUtils.validate(predicates);
        if (validate.length == 0) {
            return FalsePredicate.falsePredicate();
        }
        if (validate.length == 1) {
            return validate[0];
        }
        return new AnyPredicate(validate);
    }

    public static <T> Predicate<T> anyPredicate(Predicate<? super T>... predicateArr) {
        FunctorUtils.validate(predicateArr);
        if (predicateArr.length == 0) {
            return FalsePredicate.falsePredicate();
        }
        if (predicateArr.length == 1) {
            return (Predicate<T>) predicateArr[0];
        }
        return new AnyPredicate((Predicate[]) FunctorUtils.copy(predicateArr));
    }

    public AnyPredicate(Predicate<? super T>... predicates) {
        super(predicates);
    }

    @Override // org.apache.commons.collections4.Predicate, java.util.function.Predicate
    public boolean test(T object) {
        for (Predicate<? super T> iPredicate : this.iPredicates) {
            if (iPredicate.test(object)) {
                return true;
            }
        }
        return false;
    }
}
