package org.apache.commons.collections4.bloomfilter;

import java.util.Objects;
import java.util.function.LongPredicate;
import org.apache.commons.collections4.bloomfilter.BloomFilter;

/* loaded from: classes9.dex */
public interface BloomFilter<T extends BloomFilter<T>> extends IndexExtractor, BitMapExtractor {
    public static final int SPARSE = 1;

    int cardinality();

    int characteristics();

    void clear();

    boolean contains(IndexExtractor indexExtractor);

    T copy();

    Shape getShape();

    boolean merge(BitMapExtractor bitMapExtractor);

    boolean merge(IndexExtractor indexExtractor);

    static /* synthetic */ boolean lambda$contains$0(long x, long y) {
        return (x & y) == y;
    }

    default boolean contains(BitMapExtractor bitMapExtractor) {
        return processBitMapPairs(bitMapExtractor, new LongBiPredicate() { // from class: org.apache.commons.collections4.bloomfilter.BloomFilter$$ExternalSyntheticLambda1
            @Override // org.apache.commons.collections4.bloomfilter.LongBiPredicate
            public final boolean test(long j, long j2) {
                return BloomFilter.lambda$contains$0(j, j2);
            }
        });
    }

    default boolean contains(BloomFilter<?> other) {
        Objects.requireNonNull(other, "other");
        return (characteristics() & 1) != 0 ? contains((IndexExtractor) other) : contains((BitMapExtractor) other);
    }

    default boolean contains(Hasher hasher) {
        Objects.requireNonNull(hasher, "Hasher");
        Shape shape = getShape();
        return contains(hasher.indices(shape));
    }

    default int estimateIntersection(BloomFilter<?> other) {
        long estimate;
        Objects.requireNonNull(other, "other");
        double eThis = getShape().estimateN(cardinality());
        double eOther = getShape().estimateN(other.cardinality());
        if (Double.isInfinite(eThis) && Double.isInfinite(eOther)) {
            return Integer.MAX_VALUE;
        }
        if (Double.isInfinite(eThis)) {
            estimate = Math.round(eOther);
        } else if (Double.isInfinite(eOther)) {
            estimate = Math.round(eThis);
        } else {
            T union = copy();
            union.merge(other);
            double eUnion = getShape().estimateN(union.cardinality());
            if (Double.isInfinite(eUnion)) {
                throw new IllegalArgumentException("The estimated N for the union of the filters is infinite");
            }
            long estimate2 = Math.round((eThis + eOther) - eUnion);
            estimate = estimate2 >= 0 ? estimate2 : 0L;
        }
        if (estimate > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) estimate;
    }

    default int estimateN() {
        double d = getShape().estimateN(cardinality());
        if (Double.isInfinite(d)) {
            return Integer.MAX_VALUE;
        }
        if (Double.isNaN(d)) {
            throw new IllegalArgumentException("Cardinality too large: " + cardinality());
        }
        long l = Math.round(d);
        if (l > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) l;
    }

    default int estimateUnion(BloomFilter<?> other) {
        Objects.requireNonNull(other, "other");
        T copy = copy();
        copy.merge(other);
        return copy.estimateN();
    }

    static /* synthetic */ boolean lambda$isEmpty$1(long y) {
        return y == 0;
    }

    default boolean isEmpty() {
        return processBitMaps(new LongPredicate() { // from class: org.apache.commons.collections4.bloomfilter.BloomFilter$$ExternalSyntheticLambda0
            @Override // java.util.function.LongPredicate
            public final boolean test(long j) {
                return BloomFilter.lambda$isEmpty$1(j);
            }
        });
    }

    default boolean isFull() {
        return cardinality() == getShape().getNumberOfBits();
    }

    default boolean merge(BloomFilter<?> other) {
        return (characteristics() & 1) != 0 ? merge((IndexExtractor) other) : merge((BitMapExtractor) other);
    }

    default boolean merge(Hasher hasher) {
        Objects.requireNonNull(hasher, "hasher");
        return merge(hasher.indices(getShape()));
    }

    @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor
    default IndexExtractor uniqueIndices() {
        return this;
    }
}
