package org.apache.commons.collections4.bloomfilter;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.stream.IntStream;
import org.apache.commons.collections4.bloomfilter.CellExtractor;

/* loaded from: classes9.dex */
public final class ArrayCountingBloomFilter implements CountingBloomFilter {
    private final int[] cells;
    private final Shape shape;
    private int state;

    private ArrayCountingBloomFilter(ArrayCountingBloomFilter source) {
        this.shape = source.shape;
        this.state = source.state;
        this.cells = (int[]) source.cells.clone();
    }

    public ArrayCountingBloomFilter(Shape shape) {
        Objects.requireNonNull(shape, "shape");
        this.shape = shape;
        this.cells = new int[shape.getNumberOfBits()];
    }

    @Override // org.apache.commons.collections4.bloomfilter.CountingBloomFilter
    public boolean add(CellExtractor other) {
        Objects.requireNonNull(other, "other");
        other.processCells(new CellExtractor.CellPredicate() { // from class: org.apache.commons.collections4.bloomfilter.ArrayCountingBloomFilter$$ExternalSyntheticLambda2
            @Override // org.apache.commons.collections4.bloomfilter.CellExtractor.CellPredicate
            public final boolean test(int i, int i2) {
                boolean add;
                add = ArrayCountingBloomFilter.this.add(i, i2);
                return add;
            }
        });
        return isValid();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean add(int idx, int addend) {
        try {
            int updated = this.cells[idx] + addend;
            this.state |= updated;
            this.cells[idx] = updated;
            return true;
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("Filter only accepts values in the [0,%d) range", Integer.valueOf(getShape().getNumberOfBits())), e);
        }
    }

    @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor
    public int[] asIndexArray() {
        return IntStream.range(0, this.cells.length).filter(new IntPredicate() { // from class: org.apache.commons.collections4.bloomfilter.ArrayCountingBloomFilter$$ExternalSyntheticLambda3
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return ArrayCountingBloomFilter.this.m2033xa6be2796(i);
            }
        }).toArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$asIndexArray$0$org-apache-commons-collections4-bloomfilter-ArrayCountingBloomFilter, reason: not valid java name */
    public /* synthetic */ boolean m2033xa6be2796(int i) {
        return this.cells[i] > 0;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public int cardinality() {
        return (int) IntStream.range(0, this.cells.length).filter(new IntPredicate() { // from class: org.apache.commons.collections4.bloomfilter.ArrayCountingBloomFilter$$ExternalSyntheticLambda4
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return ArrayCountingBloomFilter.this.m2034x217effda(i);
            }
        }).count();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$cardinality$1$org-apache-commons-collections4-bloomfilter-ArrayCountingBloomFilter, reason: not valid java name */
    public /* synthetic */ boolean m2034x217effda(int i) {
        return this.cells[i] > 0;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public int characteristics() {
        return 1;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public void clear() {
        Arrays.fill(this.cells, 0);
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean contains(BitMapExtractor bitMapExtractor) {
        return contains(IndexExtractor.fromBitMapExtractor(bitMapExtractor));
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public boolean contains(IndexExtractor indexExtractor) {
        return indexExtractor.processIndices(new IntPredicate() { // from class: org.apache.commons.collections4.bloomfilter.ArrayCountingBloomFilter$$ExternalSyntheticLambda1
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return ArrayCountingBloomFilter.this.m2035x4c2d03ee(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$contains$2$org-apache-commons-collections4-bloomfilter-ArrayCountingBloomFilter, reason: not valid java name */
    public /* synthetic */ boolean m2035x4c2d03ee(int idx) {
        return this.cells[idx] != 0;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public CountingBloomFilter copy() {
        return new ArrayCountingBloomFilter(this);
    }

    @Override // org.apache.commons.collections4.bloomfilter.CountingBloomFilter
    public int getMaxCell() {
        return Integer.MAX_VALUE;
    }

    @Override // org.apache.commons.collections4.bloomfilter.CountingBloomFilter
    public int getMaxInsert(CellExtractor cellExtractor) {
        final int[] max = {Integer.MAX_VALUE};
        cellExtractor.processCells(new CellExtractor.CellPredicate() { // from class: org.apache.commons.collections4.bloomfilter.ArrayCountingBloomFilter$$ExternalSyntheticLambda0
            @Override // org.apache.commons.collections4.bloomfilter.CellExtractor.CellPredicate
            public final boolean test(int i, int i2) {
                return ArrayCountingBloomFilter.this.m2036x8ea261a5(max, i, i2);
            }
        });
        return max[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getMaxInsert$3$org-apache-commons-collections4-bloomfilter-ArrayCountingBloomFilter, reason: not valid java name */
    public /* synthetic */ boolean m2036x8ea261a5(int[] max, int x, int y) {
        int count = this.cells[x] / y;
        if (count < max[0]) {
            max[0] = count;
        }
        return max[0] > 0;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BloomFilter
    public Shape getShape() {
        return this.shape;
    }

    @Override // org.apache.commons.collections4.bloomfilter.CountingBloomFilter
    public boolean isValid() {
        return this.state >= 0;
    }

    @Override // org.apache.commons.collections4.bloomfilter.BitMapExtractor
    public boolean processBitMaps(LongPredicate consumer) {
        Objects.requireNonNull(consumer, "consumer");
        int blocksm1 = BitMaps.numberOfBitMaps(this.cells.length) - 1;
        int i = 0;
        for (int j = 0; j < blocksm1; j++) {
            long value = 0;
            int k = 0;
            while (k < 64) {
                int i2 = i + 1;
                if (this.cells[i] != 0) {
                    value |= BitMaps.getLongBit(k);
                }
                k++;
                i = i2;
            }
            if (!consumer.test(value)) {
                return false;
            }
        }
        long value2 = 0;
        int k2 = 0;
        while (i < this.cells.length) {
            int i3 = i + 1;
            if (this.cells[i] != 0) {
                value2 |= BitMaps.getLongBit(k2);
            }
            k2++;
            i = i3;
        }
        return consumer.test(value2);
    }

    @Override // org.apache.commons.collections4.bloomfilter.CellExtractor
    public boolean processCells(CellExtractor.CellPredicate consumer) {
        Objects.requireNonNull(consumer, "consumer");
        for (int i = 0; i < this.cells.length; i++) {
            if (this.cells[i] != 0 && !consumer.test(i, this.cells[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor, org.apache.commons.collections4.bloomfilter.CellExtractor
    public boolean processIndices(IntPredicate consumer) {
        Objects.requireNonNull(consumer, "consumer");
        for (int i = 0; i < this.cells.length; i++) {
            if (this.cells[i] != 0 && !consumer.test(i)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.commons.collections4.bloomfilter.CountingBloomFilter
    public boolean subtract(CellExtractor other) {
        Objects.requireNonNull(other, "other");
        other.processCells(new CellExtractor.CellPredicate() { // from class: org.apache.commons.collections4.bloomfilter.ArrayCountingBloomFilter$$ExternalSyntheticLambda5
            @Override // org.apache.commons.collections4.bloomfilter.CellExtractor.CellPredicate
            public final boolean test(int i, int i2) {
                boolean subtract;
                subtract = ArrayCountingBloomFilter.this.subtract(i, i2);
                return subtract;
            }
        });
        return isValid();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean subtract(int idx, int subtrahend) {
        try {
            int updated = this.cells[idx] - subtrahend;
            this.state |= updated;
            this.cells[idx] = updated;
            return true;
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("Filter only accepts values in the [0,%d) range", Integer.valueOf(getShape().getNumberOfBits())), e);
        }
    }
}
