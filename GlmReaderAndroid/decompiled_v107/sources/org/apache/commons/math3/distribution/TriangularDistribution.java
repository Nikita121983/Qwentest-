package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class TriangularDistribution extends AbstractRealDistribution {
    private static final long serialVersionUID = 20120112;
    private final double a;
    private final double b;
    private final double c;
    private final double solverAbsoluteAccuracy;

    public TriangularDistribution(double a, double c, double b) throws NumberIsTooLargeException, NumberIsTooSmallException {
        this(new Well19937c(), a, c, b);
    }

    public TriangularDistribution(RandomGenerator rng, double a, double c, double b) throws NumberIsTooLargeException, NumberIsTooSmallException {
        super(rng);
        if (a >= b) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Double.valueOf(a), Double.valueOf(b), false);
        }
        if (c < a) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_TOO_SMALL, Double.valueOf(c), Double.valueOf(a), true);
        }
        if (c > b) {
            throw new NumberIsTooLargeException(LocalizedFormats.NUMBER_TOO_LARGE, Double.valueOf(c), Double.valueOf(b), true);
        }
        this.a = a;
        this.c = c;
        this.b = b;
        this.solverAbsoluteAccuracy = FastMath.max(FastMath.ulp(a), FastMath.ulp(b));
    }

    public double getMode() {
        return this.c;
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double density(double x) {
        if (x < this.a) {
            return 0.0d;
        }
        if (this.a <= x && x < this.c) {
            double divident = (x - this.a) * 2.0d;
            double divisor = (this.b - this.a) * (this.c - this.a);
            return divident / divisor;
        }
        if (x == this.c) {
            return 2.0d / (this.b - this.a);
        }
        if (this.c >= x || x > this.b) {
            return 0.0d;
        }
        double divident2 = (this.b - x) * 2.0d;
        double divisor2 = (this.b - this.a) * (this.b - this.c);
        return divident2 / divisor2;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double x) {
        if (x < this.a) {
            return 0.0d;
        }
        if (this.a <= x && x < this.c) {
            double divident = (x - this.a) * (x - this.a);
            double divisor = (this.b - this.a) * (this.c - this.a);
            return divident / divisor;
        }
        double divident2 = this.c;
        if (x == divident2) {
            return (this.c - this.a) / (this.b - this.a);
        }
        if (this.c >= x || x > this.b) {
            return 1.0d;
        }
        double divident3 = (this.b - x) * (this.b - x);
        double divisor2 = (this.b - this.a) * (this.b - this.c);
        return 1.0d - (divident3 / divisor2);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        return ((this.a + this.b) + this.c) / 3.0d;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalVariance() {
        return ((((((this.a * this.a) + (this.b * this.b)) + (this.c * this.c)) - (this.a * this.b)) - (this.a * this.c)) - (this.b * this.c)) / 18.0d;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportLowerBound() {
        return this.a;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportUpperBound() {
        return this.b;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportConnected() {
        return true;
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        if (p < 0.0d || p > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(p), 0, 1);
        }
        if (p == 0.0d) {
            return this.a;
        }
        if (p == 1.0d) {
            return this.b;
        }
        if (p < (this.c - this.a) / (this.b - this.a)) {
            return this.a + FastMath.sqrt((this.b - this.a) * p * (this.c - this.a));
        }
        return this.b - FastMath.sqrt(((1.0d - p) * (this.b - this.a)) * (this.b - this.c));
    }
}
