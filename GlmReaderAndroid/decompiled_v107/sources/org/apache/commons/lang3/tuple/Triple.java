package org.apache.commons.lang3.tuple;

import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;

/* loaded from: classes9.dex */
public abstract class Triple<L, M, R> implements Comparable<Triple<L, M, R>>, Serializable {
    public static final Triple<?, ?, ?>[] EMPTY_ARRAY = new Triple[0];
    private static final long serialVersionUID = 1;

    public abstract L getLeft();

    public abstract M getMiddle();

    public abstract R getRight();

    /* JADX WARN: Multi-variable type inference failed */
    public static <L, M, R> Triple<L, M, R>[] emptyArray() {
        return (Triple<L, M, R>[]) EMPTY_ARRAY;
    }

    public static <L, M, R> Triple<L, M, R> of(L left, M middle, R right) {
        return ImmutableTriple.of((Object) left, (Object) middle, (Object) right);
    }

    public static <L, M, R> Triple<L, M, R> ofNonNull(L left, M middle, R right) {
        return ImmutableTriple.ofNonNull((Object) left, (Object) middle, (Object) right);
    }

    @Override // java.lang.Comparable
    public int compareTo(Triple<L, M, R> other) {
        return new CompareToBuilder().append(getLeft(), other.getLeft()).append(getMiddle(), other.getMiddle()).append(getRight(), other.getRight()).toComparison();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Triple)) {
            return false;
        }
        Triple<?, ?, ?> other = (Triple) obj;
        return Objects.equals(getLeft(), other.getLeft()) && Objects.equals(getMiddle(), other.getMiddle()) && Objects.equals(getRight(), other.getRight());
    }

    public int hashCode() {
        return (Objects.hashCode(getLeft()) ^ Objects.hashCode(getMiddle())) ^ Objects.hashCode(getRight());
    }

    public String toString() {
        return "(" + getLeft() + CollectionUtils.COMMA + getMiddle() + CollectionUtils.COMMA + getRight() + ")";
    }

    public String toString(String format) {
        return String.format(format, getLeft(), getMiddle(), getRight());
    }
}
