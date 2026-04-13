package org.apache.commons.collections4.bloomfilter;

import java.util.function.LongBinaryOperator;
import java.util.function.LongPredicate;

/* loaded from: classes9.dex */
public final class SetOperations {
    public static int andCardinality(BitMapExtractor first, BitMapExtractor second) {
        return cardinality(first, second, new LongBinaryOperator() { // from class: org.apache.commons.collections4.bloomfilter.SetOperations$$ExternalSyntheticLambda5
            @Override // java.util.function.LongBinaryOperator
            public final long applyAsLong(long j, long j2) {
                return SetOperations.lambda$andCardinality$0(j, j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ long lambda$andCardinality$0(long x, long y) {
        return x & y;
    }

    public static int cardinality(BitMapExtractor bitMapExtractor) {
        final int[] cardinality = new int[1];
        bitMapExtractor.processBitMaps(new LongPredicate() { // from class: org.apache.commons.collections4.bloomfilter.SetOperations$$ExternalSyntheticLambda2
            @Override // java.util.function.LongPredicate
            public final boolean test(long j) {
                return SetOperations.lambda$cardinality$1(cardinality, j);
            }
        });
        return cardinality[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$cardinality$1(int[] cardinality, long l) {
        cardinality[0] = cardinality[0] + Long.bitCount(l);
        return true;
    }

    private static int cardinality(BitMapExtractor first, BitMapExtractor second, final LongBinaryOperator op) {
        final int[] cardinality = new int[1];
        first.processBitMapPairs(second, new LongBiPredicate() { // from class: org.apache.commons.collections4.bloomfilter.SetOperations$$ExternalSyntheticLambda1
            @Override // org.apache.commons.collections4.bloomfilter.LongBiPredicate
            public final boolean test(long j, long j2) {
                return SetOperations.lambda$cardinality$2(cardinality, op, j, j2);
            }
        });
        return cardinality[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$cardinality$2(int[] cardinality, LongBinaryOperator op, long x, long y) {
        cardinality[0] = cardinality[0] + Long.bitCount(op.applyAsLong(x, y));
        return true;
    }

    public static double cosineDistance(BitMapExtractor first, BitMapExtractor second) {
        return 1.0d - cosineSimilarity(first, second);
    }

    public static double cosineSimilarity(BitMapExtractor first, BitMapExtractor second) {
        int numerator = andCardinality(first, second);
        if (numerator == 0) {
            return 0.0d;
        }
        return numerator / Math.sqrt(cardinality(first) * cardinality(second));
    }

    public static double cosineSimilarity(BloomFilter<?> first, BloomFilter<?> second) {
        int numerator = andCardinality(first, second);
        if (numerator == 0) {
            return 0.0d;
        }
        return numerator / Math.sqrt(first.cardinality() * second.cardinality());
    }

    public static int hammingDistance(BitMapExtractor first, BitMapExtractor second) {
        return xorCardinality(first, second);
    }

    public static double jaccardDistance(BitMapExtractor first, BitMapExtractor second) {
        return 1.0d - jaccardSimilarity(first, second);
    }

    public static double jaccardSimilarity(BitMapExtractor first, BitMapExtractor second) {
        final int[] cardinality = new int[2];
        first.processBitMapPairs(second, new LongBiPredicate() { // from class: org.apache.commons.collections4.bloomfilter.SetOperations$$ExternalSyntheticLambda3
            @Override // org.apache.commons.collections4.bloomfilter.LongBiPredicate
            public final boolean test(long j, long j2) {
                return SetOperations.lambda$jaccardSimilarity$3(cardinality, j, j2);
            }
        });
        int intersection = cardinality[0];
        if (intersection == 0) {
            return 0.0d;
        }
        return intersection / cardinality[1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$jaccardSimilarity$3(int[] cardinality, long x, long y) {
        cardinality[0] = cardinality[0] + Long.bitCount(x & y);
        cardinality[1] = cardinality[1] + Long.bitCount(x | y);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ long lambda$orCardinality$4(long x, long y) {
        return x | y;
    }

    public static int orCardinality(BitMapExtractor first, BitMapExtractor second) {
        return cardinality(first, second, new LongBinaryOperator() { // from class: org.apache.commons.collections4.bloomfilter.SetOperations$$ExternalSyntheticLambda4
            @Override // java.util.function.LongBinaryOperator
            public final long applyAsLong(long j, long j2) {
                return SetOperations.lambda$orCardinality$4(j, j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ long lambda$xorCardinality$5(long x, long y) {
        return x ^ y;
    }

    public static int xorCardinality(BitMapExtractor first, BitMapExtractor second) {
        return cardinality(first, second, new LongBinaryOperator() { // from class: org.apache.commons.collections4.bloomfilter.SetOperations$$ExternalSyntheticLambda0
            @Override // java.util.function.LongBinaryOperator
            public final long applyAsLong(long j, long j2) {
                return SetOperations.lambda$xorCardinality$5(j, j2);
            }
        });
    }

    private SetOperations() {
    }
}
