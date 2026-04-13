package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes9.dex */
public final class NotPredicate<T> extends AbstractPredicate<T> implements PredicateDecorator<T>, Serializable {
    private static final long serialVersionUID = -2654603322338049674L;
    private final Predicate<? super T> iPredicate;

    public static <T> Predicate<T> notPredicate(Predicate<? super T> predicate) {
        return new NotPredicate((Predicate) Objects.requireNonNull(predicate, "predicate"));
    }

    public NotPredicate(Predicate<? super T> predicate) {
        this.iPredicate = predicate;
    }

    @Override // org.apache.commons.collections4.functors.PredicateDecorator
    public Predicate<? super T>[] getPredicates() {
        return new Predicate[]{this.iPredicate};
    }

    @Override // org.apache.commons.collections4.Predicate, java.util.function.Predicate
    public boolean test(T object) {
        return !this.iPredicate.test(object);
    }
}
