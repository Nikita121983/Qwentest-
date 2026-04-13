package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class LevyDistribution extends AbstractRealDistribution {
    private static final long serialVersionUID = 20130314;
    private final double c;
    private final double halfC;
    private final double mu;

    public LevyDistribution(double mu, double c) {
        this(new Well19937c(), mu, c);
    }

    public LevyDistribution(RandomGenerator rng, double mu, double c) {
        super(rng);
        this.mu = mu;
        this.c = c;
        this.halfC = 0.5d * c;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double density(double x) {
        if (x < this.mu) {
            return Double.NaN;
        }
        double delta = x - this.mu;
        double f = this.halfC / delta;
        return (FastMath.sqrt(f / 3.141592653589793d) * FastMath.exp(-f)) / delta;
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    public double logDensity(double x) {
        if (x < this.mu) {
            return Double.NaN;
        }
        double delta = x - this.mu;
        double f = this.halfC / delta;
        return ((FastMath.log(f / 3.141592653589793d) * 0.5d) - f) - FastMath.log(delta);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double x) {
        if (x < this.mu) {
            return Double.NaN;
        }
        return Erf.erfc(FastMath.sqrt(this.halfC / (x - this.mu)));
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        if (p < 0.0d || p > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(p), 0, 1);
        }
        double t = Erf.erfcInv(p);
        return this.mu + (this.halfC / (t * t));
    }

    public double getScale() {
        return this.c;
    }

    public double getLocation() {
        return this.mu;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        return Double.POSITIVE_INFINITY;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalVariance() {
        return Double.POSITIVE_INFINITY;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportLowerBound() {
        return this.mu;
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
