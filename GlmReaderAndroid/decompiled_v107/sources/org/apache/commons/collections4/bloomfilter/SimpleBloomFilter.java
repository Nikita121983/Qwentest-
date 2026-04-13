package org.apache.commons.collections4.bloomfilter;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;

/* loaded from: classes9.dex */
public final class SimpleBloomFilter implements BloomFilter<SimpleBloomFilter> {
    private final long[] bitMap;
    private int cardinality;
    private final Shape shape;

    public SimpleBloomFilter(Shape shape) {
        Objects.requireNonNull(shape, "shape");
        this.shape = shape;
        this.bitMap = BitMaps.newBitMap(shape);
        this.cardinality = 0;
    }

    private SimpleBloomFilter(SimpleBloomFilter source) {
        this.shape = source.shape;
        this.bitMap = (long[]) source.bitMap.clone();
        this.cardinality = source.cardinality;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BitMapExtractor
    public long[] asBitMapArray() {
        return Arrays.copyOf(this.bitMap, this.bitMap.length);
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public int cardinality() {
        int c = this.cardinality;
        if (c < 0) {
            int c2 = SetOperations.cardinality(this);
            this.cardinality = c2;
            return c2;
        }
        return c;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public int characteristics() {
        return 0;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public void clear() {
        Arrays.fill(this.bitMap, 0L);
        this.cardinality = 0;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean contains(IndexExtractor indexExtractor) {
        return indexExtractor.processIndices(new IntPredicate() { // from class: org.apache.commons.collections4.bloomfilter.SimpleBloomFilter$$ExternalSyntheticLambda0
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return SimpleBloomFilter.this.m2039x76995088(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$contains$0$org-apache-commons-collections4-bloomfilter-SimpleBloomFilter, reason: not valid java name */
    public /* synthetic */ boolean m2039x76995088(int idx) {
        return BitMaps.contains(this.bitMap, idx);
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public SimpleBloomFilter copy() {
        return new SimpleBloomFilter(this);
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public Shape getShape() {
        return this.shape;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$isEmpty$1(long y) {
        return y == 0;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean isEmpty() {
        return this.cardinality == 0 || processBitMaps(new LongPredicate() { // from class: org.apache.commons.collections4.bloomfilter.SimpleBloomFilter$$ExternalSyntheticLambda1
            @Override // java.util.function.LongPredicate
            public final boolean test(long j) {
                return SimpleBloomFilter.lambda$isEmpty$1(j);
            }
        });
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean merge(BitMapExtractor bitMapExtractor) {
        Objects.requireNonNull(bitMapExtractor, "bitMapExtractor");
        try {
            bitMapExtractor.processBitMaps(new LongPredicate() { // from class: org.apache.commons.collections4.bloomfilter.SimpleBloomFilter$$ExternalSyntheticLambda2
                @Override // java.util.function.LongPredicate
                public final boolean test(long j) {
                    return SimpleBloomFilter.this.m2040x2a80fc9(idx, j);
                }
            });
            final int[] idx = {idx[0] - 1};
            int idxLimit = BitMaps.getLongIndex(this.shape.getNumberOfBits());
            if (idxLimit == idx[0]) {
                long excess = this.bitMap[idxLimit] >> this.shape.getNumberOfBits();
                if (excess != 0) {
                    throw new IllegalArgumentException(String.format("BitMapExtractor set a bit higher than the limit for the shape: %s", Integer.valueOf(this.shape.getNumberOfBits())));
                }
            }
            this.cardinality = -1;
            return true;
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("BitMapExtractor should send at most %s maps", Integer.valueOf(this.bitMap.length)), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$merge$2$org-apache-commons-collections4-bloomfilter-SimpleBloomFilter, reason: not valid java name */
    public /* synthetic */ boolean m2040x2a80fc9(int[] idx, long value) {
        long[] jArr = this.bitMap;
        int i = idx[0];
        idx[0] = i + 1;
        jArr[i] = jArr[i] | value;
        return true;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean merge(BloomFilter<?> other) {
        Objects.requireNonNull(other, "other");
        if ((other.characteristics() & 1) != 0) {
            merge((IndexExtractor) other);
        } else {
            merge((BitMapExtractor) other);
        }
        return true;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean merge(Hasher hasher) {
        Objects.requireNonNull(hasher, "hasher");
        return merge(hasher.indices(this.shape));
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean merge(IndexExtractor indexExtractor) {
        Objects.requireNonNull(indexExtractor, "indexExtractor");
        indexExtractor.processIndices(new IntPredicate() { // from class: org.apache.commons.collections4.bloomfilter.SimpleBloomFilter$$ExternalSyntheticLambda3
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return SimpleBloomFilter.this.m2041xf637940a(i);
            }
        });
        this.cardinality = -1;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$merge$3$org-apache-commons-collections4-bloomfilter-SimpleBloomFilter, reason: not valid java name */
    public /* synthetic */ boolean m2041xf637940a(int idx) {
        if (idx < 0 || idx >= this.shape.getNumberOfBits()) {
            throw new IllegalArgumentException(String.format("IndexExtractor should only send values in the range[0,%s)", Integer.valueOf(this.shape.getNumberOfBits())));
        }
        BitMaps.set(this.bitMap, idx);
        return true;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BitMapExtractor
    public boolean processBitMapPairs(BitMapExtractor other, LongBiPredicate func) {
        CountingLongPredicate p = new CountingLongPredicate(this.bitMap, func);
        return other.processBitMaps(p) && p.processRemaining();
    }

    @Override // org.apache.commons.collections4.bloomfilter.BitMapExtractor
    public boolean processBitMaps(LongPredicate consumer) {
        Objects.requireNonNull(consumer, "consumer");
        for (long l : this.bitMap) {
            if (!consumer.test(l)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor, org.apache.commons.collections4.bloomfilter.CellExtractor
    public boolean processIndices(IntPredicate consumer) {
        Objects.requireNonNull(consumer, "consumer");
        return IndexExtractor.fromBitMapExtractor(this).processIndices(consumer);
    }
}
