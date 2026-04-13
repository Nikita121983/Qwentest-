package org.apache.commons.math3.distribution;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;

/* loaded from: classes10.dex */
public abstract class AbstractMultivariateRealDistribution implements MultivariateRealDistribution {
    private final int dimension;
    protected final RandomGenerator random;

    @Override // org.apache.commons.math3.distribution.MultivariateRealDistribution
    public abstract double[] sample();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMultivariateRealDistribution(RandomGenerator rng, int n) {
        this.random = rng;
        this.dimension = n;
    }

    @Override // org.apache.commons.math3.distribution.MultivariateRealDistribution
    public void reseedRandomGenerator(long seed) {
        this.random.setSeed(seed);
    }

    @Override // org.apache.commons.math3.distribution.MultivariateRealDistribution
    public int getDimension() {
        return this.dimension;
    }

    @Override // org.apache.commons.math3.distribution.MultivariateRealDistribution
    public double[][] sample(int sampleSize) {
        if (sampleSize <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(sampleSize));
        }
        double[][] out = (double[][]) Array.newInstance((Class<?>) Double.TYPE, sampleSize, this.dimension);
        for (int i = 0; i < sampleSize; i++) {
            out[i] = sample();
        }
        return out;
    }
}
