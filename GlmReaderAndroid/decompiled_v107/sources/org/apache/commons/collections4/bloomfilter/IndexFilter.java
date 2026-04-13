package org.apache.commons.collections4.bloomfilter;

import java.util.function.IntPredicate;

/* loaded from: classes9.dex */
public final class IndexFilter {
    private final IntPredicate consumer;
    private final int size;
    private final IntPredicate tracker;

    /* loaded from: classes9.dex */
    static class ArrayTracker implements IntPredicate {
        private int populated;
        private final int[] seen;

        ArrayTracker(Shape shape) {
            this.seen = new int[shape.getNumberOfHashFunctions()];
        }

        @Override // java.util.function.IntPredicate
        public boolean test(int number) {
            if (number < 0) {
                throw new IndexOutOfBoundsException("number may not be less than zero. " + number);
            }
            for (int i = 0; i < this.populated; i++) {
                if (this.seen[i] == number) {
                    return false;
                }
            }
            int[] iArr = this.seen;
            int i2 = this.populated;
            this.populated = i2 + 1;
            iArr[i2] = number;
            return true;
        }
    }

    /* loaded from: classes9.dex */
    static class BitMapTracker implements IntPredicate {
        private final long[] bits;

        BitMapTracker(Shape shape) {
            this.bits = BitMaps.newBitMap(shape);
        }

        @Override // java.util.function.IntPredicate
        public boolean test(int number) {
            boolean retval = !BitMaps.contains(this.bits, number);
            BitMaps.set(this.bits, number);
            return retval;
        }
    }

    public static IntPredicate create(Shape shape, IntPredicate consumer) {
        final IndexFilter indexFilter = new IndexFilter(shape, consumer);
        return new IntPredicate() { // from class: org.apache.commons.collections4.bloomfilter.IndexFilter$$ExternalSyntheticLambda0
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return IndexFilter.this.test(i);
            }
        };
    }

    private IndexFilter(Shape shape, IntPredicate consumer) {
        this.size = shape.getNumberOfBits();
        this.consumer = consumer;
        if (BitMaps.numberOfBitMaps(shape) * 8 < shape.getNumberOfHashFunctions() * 4) {
            this.tracker = new BitMapTracker(shape);
        } else {
            this.tracker = new ArrayTracker(shape);
        }
    }

    public boolean test(int number) {
        if (number >= this.size) {
            throw new IndexOutOfBoundsException(String.format("number too large %d >= %d", Integer.valueOf(number), Integer.valueOf(this.size)));
        }
        if (this.tracker.test(number)) {
            return this.consumer.test(number);
        }
        return true;
    }
}
