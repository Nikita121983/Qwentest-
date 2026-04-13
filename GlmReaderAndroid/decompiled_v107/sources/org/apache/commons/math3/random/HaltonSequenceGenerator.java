package org.apache.commons.math3.random;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class HaltonSequenceGenerator implements RandomVectorGenerator {
    private static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173};
    private static final int[] WEIGHTS = {1, 2, 3, 3, 8, 11, 12, 14, 7, 18, 12, 13, 17, 18, 29, 14, 18, 43, 41, 44, 40, 30, 47, 65, 71, 28, 40, 60, 79, 89, 56, 50, 52, 61, 108, 56, 66, 63, 60, 66};
    private final int[] base;
    private int count;
    private final int dimension;
    private final int[] weight;

    public HaltonSequenceGenerator(int dimension) throws OutOfRangeException {
        this(dimension, PRIMES, WEIGHTS);
    }

    public HaltonSequenceGenerator(int dimension, int[] bases, int[] weights) throws NullArgumentException, OutOfRangeException, DimensionMismatchException {
        this.count = 0;
        MathUtils.checkNotNull(bases);
        if (dimension < 1 || dimension > bases.length) {
            throw new OutOfRangeException(Integer.valueOf(dimension), 1, Integer.valueOf(PRIMES.length));
        }
        if (weights != null && weights.length != bases.length) {
            throw new DimensionMismatchException(weights.length, bases.length);
        }
        this.dimension = dimension;
        this.base = (int[]) bases.clone();
        this.weight = weights == null ? null : (int[]) weights.clone();
        this.count = 0;
    }

    @Override // org.apache.commons.math3.random.RandomVectorGenerator
    public double[] nextVector() {
        double[] v = new double[this.dimension];
        for (int i = 0; i < this.dimension; i++) {
            int index = this.count;
            double f = 1.0d / this.base[i];
            while (index > 0) {
                int digit = scramble(i, 0, this.base[i], index % this.base[i]);
                v[i] = v[i] + (digit * f);
                index /= this.base[i];
                f /= this.base[i];
            }
        }
        int i2 = this.count;
        this.count = i2 + 1;
        return v;
    }

    protected int scramble(int i, int j, int b, int digit) {
        return this.weight != null ? (this.weight[i] * digit) % b : digit;
    }

    public double[] skipTo(int index) throws NotPositiveException {
        this.count = index;
        return nextVector();
    }

    public int getNextIndex() {
        return this.count;
    }
}
