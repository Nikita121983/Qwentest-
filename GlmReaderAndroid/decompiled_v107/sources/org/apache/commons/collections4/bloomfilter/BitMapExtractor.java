package org.apache.commons.collections4.bloomfilter;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import org.apache.commons.collections4.bloomfilter.BitMapExtractor;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface BitMapExtractor {
    boolean processBitMaps(LongPredicate longPredicate);

    static BitMapExtractor fromBitMapArray(final long... bitMaps) {
        return new BitMapExtractor() { // from class: org.apache.commons.collections4.bloomfilter.BitMapExtractor.1
            @Override // org.apache.commons.collections4.bloomfilter.BitMapExtractor
            public long[] asBitMapArray() {
                return Arrays.copyOf(bitMaps, bitMaps.length);
            }

            @Override // org.apache.commons.collections4.bloomfilter.BitMapExtractor
            public boolean processBitMapPairs(BitMapExtractor other, LongBiPredicate func) {
                CountingLongPredicate p = new CountingLongPredicate(bitMaps, func);
                return other.processBitMaps(p) && p.processRemaining();
            }

            @Override // org.apache.commons.collections4.bloomfilter.BitMapExtractor
            public boolean processBitMaps(LongPredicate predicate) {
                for (long word : bitMaps) {
                    if (!predicate.test(word)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    static BitMapExtractor fromIndexExtractor(IndexExtractor extractor, int numberOfBits) {
        Objects.requireNonNull(extractor, "extractor");
        final long[] result = BitMaps.newBitMap(numberOfBits);
        extractor.processIndices(new IntPredicate() { // from class: org.apache.commons.collections4.bloomfilter.BitMapExtractor$$ExternalSyntheticLambda1
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return BitMapExtractor.lambda$fromIndexExtractor$0(result, i);
            }
        });
        return fromBitMapArray(result);
    }

    static /* synthetic */ boolean lambda$fromIndexExtractor$0(long[] result, int i) {
        BitMaps.set(result, i);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.commons.collections4.bloomfilter.BitMapExtractor$1Bits, reason: invalid class name */
    /* loaded from: classes9.dex */
    public final class C1Bits {
        private long[] data = new long[16];
        private int size;

        C1Bits() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean add(long bits) {
            if (this.size == this.data.length) {
                this.data = Arrays.copyOf(this.data, this.size * 2);
            }
            long[] jArr = this.data;
            int i = this.size;
            this.size = i + 1;
            jArr[i] = bits;
            return true;
        }

        long[] toArray() {
            return this.size == this.data.length ? this.data : Arrays.copyOf(this.data, this.size);
        }
    }

    default long[] asBitMapArray() {
        final C1Bits bits = new C1Bits();
        Objects.requireNonNull(bits);
        processBitMaps(new LongPredicate() { // from class: org.apache.commons.collections4.bloomfilter.BitMapExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.LongPredicate
            public final boolean test(long j) {
                return BitMapExtractor.C1Bits.this.add(j);
            }
        });
        return bits.toArray();
    }

    default boolean processBitMapPairs(BitMapExtractor other, LongBiPredicate func) {
        CountingLongPredicate p = new CountingLongPredicate(asBitMapArray(), func);
        return other.processBitMaps(p) && p.processRemaining();
    }
}
