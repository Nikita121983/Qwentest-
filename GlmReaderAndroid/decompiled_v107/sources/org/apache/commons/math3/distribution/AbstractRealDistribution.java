package org.apache.commons.math3.distribution;

import java.io.Serializable;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomDataImpl;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public abstract class AbstractRealDistribution implements RealDistribution, Serializable {
    public static final double SOLVER_DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private static final long serialVersionUID = -38038050983108802L;
    protected final RandomGenerator random;

    @Deprecated
    protected RandomDataImpl randomData;
    private double solverAbsoluteAccuracy;

    @Deprecated
    protected AbstractRealDistribution() {
        this.randomData = new RandomDataImpl();
        this.solverAbsoluteAccuracy = 1.0E-6d;
        this.random = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractRealDistribution(RandomGenerator rng) {
        this.randomData = new RandomDataImpl();
        this.solverAbsoluteAccuracy = 1.0E-6d;
        this.random = rng;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    @Deprecated
    public double cumulativeProbability(double x0, double x1) throws NumberIsTooLargeException {
        return probability(x0, x1);
    }

    public double probability(double x0, double x1) {
        if (x0 > x1) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, Double.valueOf(x0), Double.valueOf(x1), true);
        }
        return cumulativeProbability(x1) - cumulativeProbability(x0);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double inverseCumulativeProbability(final double p) throws OutOfRangeException {
        double lowerBound;
        double upperBound;
        if (p < 0.0d || p > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(p), 0, 1);
        }
        double lowerBound2 = getSupportLowerBound();
        if (p == 0.0d) {
            return lowerBound2;
        }
        double upperBound2 = getSupportUpperBound();
        if (p == 1.0d) {
            return upperBound2;
        }
        double mu = getNumericalMean();
        double sig = FastMath.sqrt(getNumericalVariance());
        boolean chebyshevApplies = (Double.isInfinite(mu) || Double.isNaN(mu) || Double.isInfinite(sig) || Double.isNaN(sig)) ? false : true;
        if (lowerBound2 != Double.NEGATIVE_INFINITY) {
            lowerBound = lowerBound2;
        } else if (chebyshevApplies) {
            lowerBound = mu - (FastMath.sqrt((1.0d - p) / p) * sig);
        } else {
            double lowerBound3 = -1.0d;
            while (cumulativeProbability(lowerBound3) >= p) {
                lowerBound3 *= 2.0d;
            }
            lowerBound = lowerBound3;
        }
        if (upperBound2 != Double.POSITIVE_INFINITY) {
            upperBound = upperBound2;
        } else if (chebyshevApplies) {
            upperBound = mu + (FastMath.sqrt(p / (1.0d - p)) * sig);
        } else {
            double upperBound3 = 1.0d;
            while (cumulativeProbability(upperBound3) < p) {
                upperBound3 *= 2.0d;
            }
            upperBound = upperBound3;
        }
        UnivariateFunction toSolve = new UnivariateFunction() { // from class: org.apache.commons.math3.distribution.AbstractRealDistribution.1
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double x) {
                return AbstractRealDistribution.this.cumulativeProbability(x) - p;
            }
        };
        double x = UnivariateSolverUtils.solve(toSolve, lowerBound, upperBound, getSolverAbsoluteAccuracy());
        if (!isSupportConnected()) {
            double dx = getSolverAbsoluteAccuracy();
            if (x - dx >= getSupportLowerBound()) {
                double px = cumulativeProbability(x);
                if (cumulativeProbability(x - dx) == px) {
                    double upperBound4 = x;
                    while (upperBound4 - lowerBound > dx) {
                        double upperBound5 = upperBound4;
                        upperBound4 = (lowerBound + upperBound4) * 0.5d;
                        if (cumulativeProbability(upperBound4) < px) {
                            lowerBound = upperBound4;
                            upperBound4 = upperBound5;
                        }
                    }
                    return upperBound4;
                }
            }
        }
        return x;
    }

    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public void reseedRandomGenerator(long seed) {
        this.random.setSeed(seed);
        this.randomData.reSeed(seed);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double sample() {
        return inverseCumulativeProbability(this.random.nextDouble());
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double[] sample(int sampleSize) {
        if (sampleSize <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(sampleSize));
        }
        double[] out = new double[sampleSize];
        for (int i = 0; i < sampleSize; i++) {
            out[i] = sample();
        }
        return out;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double probability(double x) {
        return 0.0d;
    }

    public double logDensity(double x) {
        return FastMath.log(density(x));
    }
}
