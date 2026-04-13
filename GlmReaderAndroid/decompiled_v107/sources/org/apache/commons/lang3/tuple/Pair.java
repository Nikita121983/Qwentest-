package org.apache.commons.lang3.tuple;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.function.FailableBiConsumer;
import org.apache.commons.lang3.function.FailableBiFunction;

/* loaded from: classes9.dex */
public abstract class Pair<L, R> implements Map.Entry<L, R>, Comparable<Pair<L, R>>, Serializable {
    public static final Pair<?, ?>[] EMPTY_ARRAY = new Pair[0];
    private static final long serialVersionUID = 4954918890077093841L;

    public abstract L getLeft();

    public abstract R getRight();

    /* JADX WARN: Multi-variable type inference failed */
    public static <L, R> Pair<L, R>[] emptyArray() {
        return (Pair<L, R>[]) EMPTY_ARRAY;
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
        return ImmutablePair.of((Object) left, (Object) right);
    }

    public static <L, R> Pair<L, R> of(Map.Entry<L, R> pair) {
        return ImmutablePair.of((Map.Entry) pair);
    }

    public static <L, R> Pair<L, R> ofNonNull(L left, R right) {
        return ImmutablePair.ofNonNull((Object) left, (Object) right);
    }

    public <E extends Throwable> void accept(FailableBiConsumer<L, R, E> consumer) throws Throwable {
        consumer.accept(getKey(), getValue());
    }

    public <V, E extends Throwable> V apply(FailableBiFunction<L, R, V, E> function) throws Throwable {
        return function.apply(getKey(), getValue());
    }

    @Override // java.lang.Comparable
    public int compareTo(Pair<L, R> other) {
        return new CompareToBuilder().append(getLeft(), other.getLeft()).append(getRight(), other.getRight()).toComparison();
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry<?, ?> other = (Map.Entry) obj;
        return Objects.equals(getKey(), other.getKey()) && Objects.equals(getValue(), other.getValue());
    }

    @Override // java.util.Map.Entry
    public final L getKey() {
        return getLeft();
    }

    @Override // java.util.Map.Entry
    public R getValue() {
        return getRight();
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
    }

    public String toString() {
        return "(" + getLeft() + ',' + getRight() + ')';
    }

    public String toString(String format) {
        return String.format(format, getLeft(), getRight());
    }
}
