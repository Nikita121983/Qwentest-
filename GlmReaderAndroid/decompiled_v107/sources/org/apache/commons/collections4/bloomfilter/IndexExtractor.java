package org.apache.commons.collections4.bloomfilter;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import org.apache.commons.collections4.bloomfilter.IndexExtractor;

@FunctionalInterface
/* loaded from: classes9.dex */
public interface IndexExtractor {
    boolean processIndices(IntPredicate intPredicate);

    static IndexExtractor fromBitMapExtractor(final BitMapExtractor bitMapExtractor) {
        Objects.requireNonNull(bitMapExtractor, "bitMapExtractor");
        return new IndexExtractor() { // from class: org.apache.commons.collections4.bloomfilter.IndexExtractor$$ExternalSyntheticLambda3
            @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor, org.apache.commons.collections4.bloomfilter.CellExtractor
            public final boolean processIndices(IntPredicate intPredicate) {
                return IndexExtractor.lambda$fromBitMapExtractor$0(BitMapExtractor.this, intPredicate);
            }
        };
    }

    static /* synthetic */ boolean lambda$fromBitMapExtractor$0(BitMapExtractor bitMapExtractor, final IntPredicate consumer) {
        final LongPredicate longPredicate = new LongPredicate() { // from class: org.apache.commons.collections4.bloomfilter.IndexExtractor.1
            int wordIdx;

            @Override // java.util.function.LongPredicate
            public boolean test(long word) {
                int i = this.wordIdx;
                while (word != 0) {
                    if ((word & 1) == 1 && !consumer.test(i)) {
                        return false;
                    }
                    word >>>= 1;
                    i++;
                }
                this.wordIdx += 64;
                return true;
            }
        };
        Objects.requireNonNull(longPredicate);
        return bitMapExtractor.processBitMaps(new LongPredicate() { // from class: org.apache.commons.collections4.bloomfilter.IndexExtractor$$ExternalSyntheticLambda0
            @Override // java.util.function.LongPredicate
            public final boolean test(long j) {
                return longPredicate.test(j);
            }
        });
    }

    static IndexExtractor fromIndexArray(final int... values) {
        return new IndexExtractor() { // from class: org.apache.commons.collections4.bloomfilter.IndexExtractor.2
            @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor
            public int[] asIndexArray() {
                return (int[]) values.clone();
            }

            @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor, org.apache.commons.collections4.bloomfilter.CellExtractor
            public boolean processIndices(IntPredicate predicate) {
                for (int value : values) {
                    if (!predicate.test(value)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.commons.collections4.bloomfilter.IndexExtractor$1Indices, reason: invalid class name */
    /* loaded from: classes9.dex */
    public final class C1Indices {
        private int[] data = new int[32];
        private int size;

        C1Indices() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean add(int index) {
            this.data = IndexUtils.ensureCapacityForAdd(this.data, this.size);
            int[] iArr = this.data;
            int i = this.size;
            this.size = i + 1;
            iArr[i] = index;
            return true;
        }

        int[] toArray() {
            return this.size == this.data.length ? this.data : Arrays.copyOf(this.data, this.size);
        }
    }

    default int[] asIndexArray() {
        final C1Indices indices = new C1Indices();
        Objects.requireNonNull(indices);
        processIndices(new IntPredicate() { // from class: org.apache.commons.collections4.bloomfilter.IndexExtractor$$ExternalSyntheticLambda2
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return IndexExtractor.C1Indices.this.add(i);
            }
        });
        return indices.toArray();
    }

    default IndexExtractor uniqueIndices() {
        final BitSet bitSet = new BitSet();
        processIndices(new IntPredicate() { // from class: org.apache.commons.collections4.bloomfilter.IndexExtractor$$ExternalSyntheticLambda1
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return IndexExtractor.lambda$uniqueIndices$1(bitSet, i);
            }
        });
        return new IndexExtractor(this) { // from class: org.apache.commons.collections4.bloomfilter.IndexExtractor.3
            final /* synthetic */ IndexExtractor this$0;

            {
                this.this$0 = this;
            }

            @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor, org.apache.commons.collections4.bloomfilter.CellExtractor
            public boolean processIndices(IntPredicate predicate) {
                int idx = bitSet.nextSetBit(0);
                while (idx >= 0) {
                    if (!predicate.test(idx)) {
                        return false;
                    }
                    idx = bitSet.nextSetBit(idx + 1);
                }
                return true;
            }

            @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor
            public IndexExtractor uniqueIndices() {
                return this;
            }
        };
    }

    static /* synthetic */ boolean lambda$uniqueIndices$1(BitSet bitSet, int i) {
        bitSet.set(i);
        return true;
    }
}
