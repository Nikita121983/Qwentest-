package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.ResizableDoubleArray;

/* loaded from: classes10.dex */
public class ExponentialDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final double[] EXPONENTIAL_SA_QI;
    private static final long serialVersionUID = 2401296428283614780L;
    private final double logMean;
    private final double mean;
    private final double solverAbsoluteAccuracy;

    static {
        double LN2 = FastMath.log(2.0d);
        double qi = 0.0d;
        int i = 1;
        ResizableDoubleArray ra = new ResizableDoubleArray(20);
        while (qi < 1.0d) {
            qi += FastMath.pow(LN2, i) / CombinatoricsUtils.factorial(i);
            ra.addElement(qi);
            i++;
        }
        EXPONENTIAL_SA_QI = ra.getElements();
    }

    public ExponentialDistribution(double mean) {
        this(mean, 1.0E-9d);
    }

    public ExponentialDistribution(double mean, double inverseCumAccuracy) {
        this(new Well19937c(), mean, inverseCumAccuracy);
    }

    public ExponentialDistribution(RandomGenerator rng, double mean) throws NotStrictlyPositiveException {
        this(rng, mean, 1.0E-9d);
    }

    public ExponentialDistribution(RandomGenerator rng, double mean, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        if (mean <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, Double.valueOf(mean));
        }
        this.mean = mean;
        this.logMean = FastMath.log(mean);
        this.solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    public double getMean() {
        return this.mean;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double density(double x) {
        double logDensity = logDensity(x);
        if (logDensity == Double.NEGATIVE_INFINITY) {
            return 0.0d;
        }
        return FastMath.exp(logDensity);
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    public double logDensity(double x) {
        if (x < 0.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        return ((-x) / this.mean) - this.logMean;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double x) {
        if (x <= 0.0d) {
            return 0.0d;
        }
        double ret = -x;
        return 1.0d - FastMath.exp(ret / this.mean);
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        if (p < 0.0d || p > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(p), Double.valueOf(0.0d), Double.valueOf(1.0d));
        }
        if (p == 1.0d) {
            return Double.POSITIVE_INFINITY;
        }
        double ret = this.mean;
        return (-ret) * FastMath.log(1.0d - p);
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public double sample() {
        double a = 0.0d;
        double u = this.random.nextDouble();
        while (u < 0.5d) {
            a += EXPONENTIAL_SA_QI[0];
            u *= 2.0d;
        }
        double u2 = u + (u - 1.0d);
        if (u2 <= EXPONENTIAL_SA_QI[0]) {
            return this.mean * (a + u2);
        }
        int i = 0;
        double umin = this.random.nextDouble();
        do {
            i++;
            double u22 = this.random.nextDouble();
            if (u22 < umin) {
                umin = u22;
            }
        } while (u2 > EXPONENTIAL_SA_QI[i]);
        return this.mean * ((EXPONENTIAL_SA_QI[0] * umin) + a);
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        return getMean();
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalVariance() {
        double m = getMean();
        return m * m;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportLowerBound() {
        return 0.0d;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportConnected() {
        return true;
    }
}
