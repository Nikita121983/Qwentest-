package org.apache.commons.collections4.bloomfilter;

import java.util.Objects;
import java.util.function.IntPredicate;
import kotlin.UByte;
import org.apache.commons.collections4.bloomfilter.EnhancedDoubleHasher;

/* loaded from: classes9.dex */
public class EnhancedDoubleHasher implements Hasher {
    private final long increment;
    private final long initial;

    private static long toLong(byte[] byteArray, int offset, int len) {
        long val = 0;
        int shift = 64;
        int end = Math.min(len, 8) + offset;
        for (int i = offset; i < end; i++) {
            shift -= 8;
            val |= (byteArray[i] & UByte.MAX_VALUE) << shift;
        }
        return val;
    }

    public EnhancedDoubleHasher(byte[] buffer) {
        if (buffer.length == 0) {
            throw new IllegalArgumentException("buffer length must be greater than 0");
        }
        int segment = buffer.length / 2;
        this.initial = toLong(buffer, 0, segment);
        this.increment = toLong(buffer, segment, buffer.length - segment);
    }

    public EnhancedDoubleHasher(long initial, long increment) {
        this.initial = initial;
        this.increment = increment;
    }

    long getIncrement() {
        return this.increment;
    }

    long getInitial() {
        return this.initial;
    }

    @Override // org.apache.commons.collections4.bloomfilter.Hasher
    public IndexExtractor indices(Shape shape) {
        Objects.requireNonNull(shape, "shape");
        return new AnonymousClass1(this, shape);
    }

    /* renamed from: org.apache.commons.collections4.bloomfilter.EnhancedDoubleHasher$1, reason: invalid class name */
    /* loaded from: classes9.dex */
    class AnonymousClass1 implements IndexExtractor {
        final /* synthetic */ EnhancedDoubleHasher this$0;
        final /* synthetic */ Shape val$shape;

        AnonymousClass1(EnhancedDoubleHasher this$0, Shape shape) {
            this.val$shape = shape;
            this.this$0 = this$0;
        }

        @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor
        public int[] asIndexArray() {
            final int[] result = new int[this.val$shape.getNumberOfHashFunctions()];
            final int[] idx = new int[1];
            processIndices(new IntPredicate() { // from class: org.apache.commons.collections4.bloomfilter.EnhancedDoubleHasher$1$$ExternalSyntheticLambda0
                @Override // java.util.function.IntPredicate
                public final boolean test(int i) {
                    return EnhancedDoubleHasher.AnonymousClass1.lambda$asIndexArray$0(result, idx, i);
                }
            });
            return result;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ boolean lambda$asIndexArray$0(int[] result, int[] idx, int i) {
            int i2 = idx[0];
            idx[0] = i2 + 1;
            result[i2] = i;
            return true;
        }

        @Override // org.apache.commons.collections4.bloomfilter.IndexExtractor, org.apache.commons.collections4.bloomfilter.CellExtractor
        public boolean processIndices(IntPredicate consumer) {
            Objects.requireNonNull(consumer, "consumer");
            int bits = this.val$shape.getNumberOfBits();
            int index = BitMaps.mod(this.this$0.initial, bits);
            if (!consumer.test(index)) {
                return false;
            }
            int inc = BitMaps.mod(this.this$0.increment, bits);
            int k = this.val$shape.getNumberOfHashFunctions();
            if (k >= bits) {
                int tet = 1;
                for (int i = 1; i < k; i++) {
                    int index2 = index - inc;
                    index = index2 < 0 ? index2 + bits : index2;
                    if (!consumer.test(index)) {
                        return false;
                    }
                    int inc2 = inc - tet;
                    inc = inc2 < 0 ? inc2 + bits : inc2;
                    tet++;
                    if (tet == bits) {
                        tet = 0;
                    }
                }
                return true;
            }
            for (int i2 = 1; i2 < k; i2++) {
                int index3 = index - inc;
                index = index3 < 0 ? index3 + bits : index3;
                if (!consumer.test(index)) {
                    return false;
                }
                int inc3 = inc - i2;
                inc = inc3 < 0 ? inc3 + bits : inc3;
            }
            return true;
        }
    }
}
