package org.apache.commons.collections4.bloomfilter;

import java.util.Objects;
import org.apache.commons.collections4.bloomfilter.CellExtractor;

/* loaded from: classes9.dex */
public interface CountingBloomFilter extends BloomFilter<CountingBloomFilter>, CellExtractor {
    boolean add(CellExtractor cellExtractor);

    int getMaxCell();

    int getMaxInsert(CellExtractor cellExtractor);

    boolean isValid();

    boolean subtract(CellExtractor cellExtractor);

    default int getMaxInsert(BitMapExtractor bitMapExtractor) {
        if (!contains(bitMapExtractor)) {
            return 0;
        }
        final long[] bitMaps = bitMapExtractor.asBitMapArray();
        final int[] max = {Integer.MAX_VALUE};
        processCells(new CellExtractor.CellPredicate() { // from class: org.apache.commons.collections4.bloomfilter.CountingBloomFilter$$ExternalSyntheticLambda0
            @Override // org.apache.commons.collections4.bloomfilter.CellExtractor.CellPredicate
            public final boolean test(int i, int i2) {
                return CountingBloomFilter.lambda$getMaxInsert$0(bitMaps, max, i, i2);
            }
        });
        return max[0];
    }

    static /* synthetic */ boolean lambda$getMaxInsert$0(long[] bitMaps, int[] max, int x, int y) {
        if ((bitMaps[BitMaps.getLongIndex(x)] & BitMaps.getLongBit(x)) != 0) {
            max[0] = max[0] <= y ? max[0] : y;
            return true;
        }
        return true;
    }

    default int getMaxInsert(BloomFilter<?> bloomFilter) {
        return getMaxInsert((BitMapExtractor) bloomFilter);
    }

    default int getMaxInsert(Hasher hasher) {
        return getMaxInsert(hasher.indices(getShape()));
    }

    default int getMaxInsert(IndexExtractor indexExtractor) {
        return getMaxInsert(CellExtractor.from(indexExtractor.uniqueIndices()));
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    default boolean merge(BitMapExtractor bitMapExtractor) {
        return merge(IndexExtractor.fromBitMapExtractor(bitMapExtractor));
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    default boolean merge(BloomFilter<?> other) {
        Objects.requireNonNull(other, "other");
        return merge((IndexExtractor) other);
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    default boolean merge(Hasher hasher) {
        Objects.requireNonNull(hasher, "hasher");
        return merge(hasher.indices(getShape()));
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    default boolean merge(IndexExtractor indexExtractor) {
        Objects.requireNonNull(indexExtractor, "indexExtractor");
        try {
            return add(CellExtractor.from(indexExtractor.uniqueIndices()));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("Filter only accepts values in the [0,%d) range", Integer.valueOf(getShape().getNumberOfBits())), e);
        }
    }

    default boolean remove(BitMapExtractor bitMapExtractor) {
        return remove(IndexExtractor.fromBitMapExtractor(bitMapExtractor));
    }

    default boolean remove(BloomFilter<?> other) {
        return remove((IndexExtractor) other);
    }

    default boolean remove(Hasher hasher) {
        Objects.requireNonNull(hasher, "hasher");
        return remove(hasher.indices(getShape()));
    }

    default boolean remove(IndexExtractor indexExtractor) {
        Objects.requireNonNull(indexExtractor, "indexExtractor");
        try {
            return subtract(CellExtractor.from(indexExtractor.uniqueIndices()));
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("Filter only accepts values in the [0,%d) range", Integer.valueOf(getShape().getNumberOfBits())));
        }
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter, org.apache.commons.collections4.bloomfilter.IndexExtractor
    default IndexExtractor uniqueIndices() {
        return this;
    }
}
