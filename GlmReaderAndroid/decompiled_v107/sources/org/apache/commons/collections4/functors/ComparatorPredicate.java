package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import org.apache.commons.collections4.Predicate;

/* loaded from: classes9.dex */
public class ComparatorPredicate<T> extends AbstractPredicate<T> implements Serializable {
    private static final long serialVersionUID = -1863209236504077399L;
    private final Comparator<T> comparator;
    private final Criterion criterion;
    private final T object;

    /* loaded from: classes9.dex */
    public enum Criterion {
        EQUAL,
        GREATER,
        LESS,
        GREATER_OR_EQUAL,
        LESS_OR_EQUAL
    }

    public static <T> Predicate<T> comparatorPredicate(T object, Comparator<T> comparator) {
        return comparatorPredicate(object, comparator, Criterion.EQUAL);
    }

    public static <T> Predicate<T> comparatorPredicate(T object, Comparator<T> comparator, Criterion criterion) {
        return new ComparatorPredicate(object, (Comparator) Objects.requireNonNull(comparator, "comparator"), (Criterion) Objects.requireNonNull(criterion, "criterion"));
    }

    public ComparatorPredicate(T object, Comparator<T> comparator, Criterion criterion) {
        this.object = object;
        this.comparator = comparator;
        this.criterion = criterion;
    }

    @Override // org.apache.commons.collections4.Predicate, java.util.function.Predicate
    public boolean test(T target) {
        int comparison = this.comparator.compare(this.object, target);
        switch (this.criterion) {
            case EQUAL:
                return comparison == 0;
            case GREATER:
                return comparison > 0;
            case LESS:
                return comparison < 0;
            case GREATER_OR_EQUAL:
                return comparison >= 0;
            case LESS_OR_EQUAL:
                return comparison <= 0;
            default:
                throw new IllegalStateException("The current criterion '" + this.criterion + "' is invalid.");
        }
    }
}
