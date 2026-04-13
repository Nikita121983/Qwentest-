package org.apache.commons.math3.random;

import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class UnitSphereRandomVectorGenerator implements RandomVectorGenerator {
    private final int dimension;
    private final RandomGenerator rand;

    public UnitSphereRandomVectorGenerator(int dimension, RandomGenerator rand) {
        this.dimension = dimension;
        this.rand = rand;
    }

    public UnitSphereRandomVectorGenerator(int dimension) {
        this(dimension, new MersenneTwister());
    }

    @Override // org.apache.commons.math3.random.RandomVectorGenerator
    public double[] nextVector() {
        double[] v = new double[this.dimension];
        double normSq = 0.0d;
        for (int i = 0; i < this.dimension; i++) {
            double comp = this.rand.nextGaussian();
            v[i] = comp;
            normSq += comp * comp;
        }
        double f = 1.0d / FastMath.sqrt(normSq);
        for (int i2 = 0; i2 < this.dimension; i2++) {
            v[i2] = v[i2] * f;
        }
        return v;
    }
}
