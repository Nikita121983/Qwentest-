package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class TDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = -5852615386664158222L;
    private final double degreesOfFreedom;
    private final double factor;
    private final double solverAbsoluteAccuracy;

    public TDistribution(double degreesOfFreedom) throws NotStrictlyPositiveException {
        this(degreesOfFreedom, 1.0E-9d);
    }

    public TDistribution(double degreesOfFreedom, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        this(new Well19937c(), degreesOfFreedom, inverseCumAccuracy);
    }

    public TDistribution(RandomGenerator rng, double degreesOfFreedom) throws NotStrictlyPositiveException {
        this(rng, degreesOfFreedom, 1.0E-9d);
    }

    public TDistribution(RandomGenerator rng, double degreesOfFreedom, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        if (degreesOfFreedom <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DEGREES_OF_FREEDOM, Double.valueOf(degreesOfFreedom));
        }
        this.degreesOfFreedom = degreesOfFreedom;
        this.solverAbsoluteAccuracy = inverseCumAccuracy;
        double nPlus1Over2 = (1.0d + degreesOfFreedom) / 2.0d;
        this.factor = (Gamma.logGamma(nPlus1Over2) - ((FastMath.log(3.141592653589793d) + FastMath.log(degreesOfFreedom)) * 0.5d)) - Gamma.logGamma(degreesOfFreedom / 2.0d);
    }

    public double getDegreesOfFreedom() {
        return this.degreesOfFreedom;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double density(double x) {
        return FastMath.exp(logDensity(x));
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    public double logDensity(double x) {
        double n = this.degreesOfFreedom;
        double nPlus1Over2 = (n + 1.0d) / 2.0d;
        return this.factor - (FastMath.log(((x * x) / n) + 1.0d) * nPlus1Over2);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double x) {
        if (x == 0.0d) {
            return 0.5d;
        }
        double t = Beta.regularizedBeta(this.degreesOfFreedom / (this.degreesOfFreedom + (x * x)), this.degreesOfFreedom * 0.5d, 0.5d);
        if (x < 0.0d) {
            double ret = t * 0.5d;
            return ret;
        }
        double ret2 = 1.0d - (0.5d * t);
        return ret2;
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        double df = getDegreesOfFreedom();
        if (df > 1.0d) {
            return 0.0d;
        }
        return Double.NaN;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalVariance() {
        double df = getDegreesOfFreedom();
        if (df > 2.0d) {
            return df / (df - 2.0d);
        }
        if (df > 1.0d && df <= 2.0d) {
            return Double.POSITIVE_INFINITY;
        }
        return Double.NaN;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportLowerBound() {
        return Double.NEGATIVE_INFINITY;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportLowerBoundInclusive() {
        return false;
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
