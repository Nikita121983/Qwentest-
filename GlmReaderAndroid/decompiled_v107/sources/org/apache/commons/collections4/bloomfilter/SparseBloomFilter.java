package org.apache.commons.collections4.bloomfilter;

import java.util.Iterator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* loaded from: classes9.dex */
public final class SparseBloomFilter implements BloomFilter<SparseBloomFilter> {
    private final TreeSet<Integer> indices;
    private final Shape shape;

    public SparseBloomFilter(Shape shape) {
        Objects.requireNonNull(shape, "shape");
        this.shape = shape;
        this.indices = new TreeSet<>();
    }

    private SparseBloomFilter(SparseBloomFilter source) {
        this.shape = source.shape;
        this.indices = new TreeSet<>((SortedSet) source.indices);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean add(int idx) {
        this.indices.add(Integer.valueOf(idx));
        return true;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BitMapExtractor
    public long[] asBitMapArray() {
        long[] result = BitMaps.newBitMap(this.shape);
        Iterator<Integer> it = this.indices.iterator();
        while (it.hasNext()) {
            int i = it.next().intValue();
            BitMaps.set(result, i);
        }
        return result;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public int cardinality() {
        return this.indices.size();
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public int characteristics() {
        return 1;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public void clear() {
        this.indices.clear();
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean contains(BitMapExtractor bitMapExtractor) {
        return contains(IndexExtractor.fromBitMapExtractor(bitMapExtractor));
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean contains(IndexExtractor indexExtractor) {
        final TreeSet<Integer> treeSet = this.indices;
        Objects.requireNonNull(treeSet);
        return indexExtractor.processIndices(new IntPredicate() { // from class: org.apache.commons.collections4.bloomfilter.SparseBloomFilter$$ExternalSyntheticLambda2
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                boolean contains;
                contains = treeSet.contains(Integer.valueOf(i));
                return contains;
            }
        });
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public SparseBloomFilter copy() {
        return new SparseBloomFilter(this);
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public Shape getShape() {
        return this.shape;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean isEmpty() {
        return this.indices.isEmpty();
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean merge(BitMapExtractor bitMapExtractor) {
        Objects.requireNonNull(bitMapExtractor, "bitMapExtractor");
        return merge(IndexExtractor.fromBitMapExtractor(bitMapExtractor));
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean merge(BloomFilter<?> other) {
        Objects.requireNonNull(other, "other");
        IndexExtractor indexExtractor = (other.characteristics() & 1) != 0 ? other : IndexExtractor.fromBitMapExtractor(other);
        merge(indexExtractor);
        return true;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean merge(Hasher hasher) {
        Objects.requireNonNull(hasher, "hasher");
        merge(hasher.indices(this.shape));
        return true;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean merge(IndexExtractor indexExtractor) {
        Objects.requireNonNull(indexExtractor, "indexExtractor");
        indexExtractor.processIndices(new IntPredicate() { // from class: org.apache.commons.collections4.bloomfilter.SparseBloomFilter$$ExternalSyntheticLambda1
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                boolean add;
                add = SparseBloomFilter.this.add(i);
                return add;
            }
        });
        if (!this.indices.isEmpty()) {
            if (this.indices.last().intValue() >= this.shape.getNumberOfBits()) {
                throw new IllegalArgumentException(String.format("Value in list %s is greater than maximum value (%s)", this.indices.last(), Integer.valueOf(this.shape.getNumberOfBits() - 1)));
            }
            if (this.indices.first().intValue() < 0) {
                throw new IllegalArgumentException(String.format("Value in list %s is less than 0", this.indices.first()));
            }
        }
        return true;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BitMapExtractor
    public boolean processBitMaps(LongPredicate consumer) {
        Objects.requireNonNull(consumer, "consumer");
        int limit = BitMaps.numberOfBitMaps(this.shape);
        long bitMap = 0;
        int idx = 0;
        Iterator<Integer> it = this.indices.iterator();
        while (it.hasNext()) {
            int i = it.next().intValue();
            while (BitMaps.getLongIndex(i) != idx) {
                if (!consumer.test(bitMap)) {
                    return false;
                }
                bitMap = 0;
                idx++;
            }
            bitMap |= BitMaps.getLongBit(i);
        }
        if (!consumer.test(bitMap)) {
            return false;
        }
        for (int idx2 = idx + 1; idx2 < limit; idx2++) {
            if (!consumer.test(0L)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor, org.apache.commons.collections4.bloomfilter.CellExtractor
    public boolean processIndices(final IntPredicate consumer) {
        Objects.requireNonNull(consumer, "consumer");
        Stream stream = this.indices.stream();
        Objects.requireNonNull(consumer);
        return stream.allMatch(new Predicate() { // from class: org.apache.commons.collections4.bloomfilter.SparseBloomFilter$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return consumer.test(((Integer) obj).intValue());
            }
        });
    }
}
