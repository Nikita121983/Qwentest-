package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import org.apache.commons.collections4.ComparatorUtils;

/* loaded from: classes9.dex */
public class NullComparator<E> implements Comparator<E>, Serializable {
    private static final long serialVersionUID = -5820772575483504339L;
    private final Comparator<? super E> nonNullComparator;
    private final boolean nullsAreHigh;

    public NullComparator() {
        this(ComparatorUtils.NATURAL_COMPARATOR, true);
    }

    public NullComparator(boolean nullsAreHigh) {
        this(ComparatorUtils.NATURAL_COMPARATOR, nullsAreHigh);
    }

    public NullComparator(Comparator<? super E> nonNullComparator) {
        this(nonNullComparator, true);
    }

    public NullComparator(Comparator<? super E> nonNullComparator, boolean nullsAreHigh) {
        this.nonNullComparator = (Comparator) Objects.requireNonNull(nonNullComparator, "nonNullComparator");
        this.nullsAreHigh = nullsAreHigh;
    }

    @Override // java.util.Comparator
    public int compare(E o1, E o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return this.nullsAreHigh ? 1 : -1;
        }
        if (o2 == null) {
            return this.nullsAreHigh ? -1 : 1;
        }
        return this.nonNullComparator.compare(o1, o2);
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!obj.getClass().equals(getClass())) {
            return false;
        }
        NullComparator<?> other = (NullComparator) obj;
        if (this.nullsAreHigh != other.nullsAreHigh || !this.nonNullComparator.equals(other.nonNullComparator)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.nullsAreHigh ? -1 : 1) * this.nonNullComparator.hashCode();
    }
}
