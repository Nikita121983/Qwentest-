package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes9.dex */
public final class BooleanComparator implements Comparator<Boolean>, Serializable {
    private static final long serialVersionUID = 1830042991606340609L;
    private final boolean trueFirst;
    private static final BooleanComparator TRUE_FIRST = new BooleanComparator(true);
    private static final BooleanComparator FALSE_FIRST = new BooleanComparator(false);

    public static BooleanComparator booleanComparator(boolean trueFirst) {
        return trueFirst ? TRUE_FIRST : FALSE_FIRST;
    }

    public static BooleanComparator getFalseFirstComparator() {
        return FALSE_FIRST;
    }

    public static BooleanComparator getTrueFirstComparator() {
        return TRUE_FIRST;
    }

    public BooleanComparator() {
        this(false);
    }

    public BooleanComparator(boolean trueFirst) {
        this.trueFirst = trueFirst;
    }

    @Override // java.util.Comparator
    public int compare(Boolean b1, Boolean b2) {
        boolean v1 = b1.booleanValue();
        boolean v2 = b2.booleanValue();
        if (v1 ^ v2) {
            return this.trueFirst ^ v1 ? 1 : -1;
        }
        return 0;
    }

    @Override // java.util.Comparator
    public boolean equals(Object object) {
        return this == object || ((object instanceof BooleanComparator) && this.trueFirst == ((BooleanComparator) object).trueFirst);
    }

    public int hashCode() {
        int hash = "BooleanComparator".hashCode();
        return this.trueFirst ? hash * (-1) : hash;
    }

    public boolean sortsTrueFirst() {
        return this.trueFirst;
    }
}
