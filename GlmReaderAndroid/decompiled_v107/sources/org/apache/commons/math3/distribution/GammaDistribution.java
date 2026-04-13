package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class GammaDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = 20120524;
    private final double densityPrefactor1;
    private final double densityPrefactor2;
    private final double logDensityPrefactor1;
    private final double logDensityPrefactor2;
    private final double maxLogY;
    private final double minY;
    private final double scale;
    private final double shape;
    private final double shiftedShape;
    private final double solverAbsoluteAccuracy;

    public GammaDistribution(double shape, double scale) throws NotStrictlyPositiveException {
        this(shape, scale, 1.0E-9d);
    }

    public GammaDistribution(double shape, double scale, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        this(new Well19937c(), shape, scale, inverseCumAccuracy);
    }

    public GammaDistribution(RandomGenerator rng, double shape, double scale) throws NotStrictlyPositiveException {
        this(rng, shape, scale, 1.0E-9d);
    }

    public GammaDistribution(RandomGenerator rng, double shape, double scale, double inverseCumAccuracy) throws NotStrictlyPositiveException {
        super(rng);
        if (shape <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, Double.valueOf(shape));
        }
        if (scale <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, Double.valueOf(scale));
        }
        this.shape = shape;
        this.scale = scale;
        this.solverAbsoluteAccuracy = inverseCumAccuracy;
        this.shiftedShape = shape + 4.7421875d + 0.5d;
        double aux = 2.718281828459045d / (this.shiftedShape * 6.283185307179586d);
        this.densityPrefactor2 = (FastMath.sqrt(aux) * shape) / Gamma.lanczos(shape);
        this.logDensityPrefactor2 = (FastMath.log(shape) + (FastMath.log(aux) * 0.5d)) - FastMath.log(Gamma.lanczos(shape));
        this.densityPrefactor1 = (this.densityPrefactor2 / scale) * FastMath.pow(this.shiftedShape, -shape) * FastMath.exp(shape + 4.7421875d);
        this.logDensityPrefactor1 = ((this.logDensityPrefactor2 - FastMath.log(scale)) - (FastMath.log(this.shiftedShape) * shape)) + shape + 4.7421875d;
        this.minY = (shape + 4.7421875d) - FastMath.log(Double.MAX_VALUE);
        this.maxLogY = FastMath.log(Double.MAX_VALUE) / (shape - 1.0d);
    }

    @Deprecated
    public double getAlpha() {
        return this.shape;
    }

    public double getShape() {
        return this.shape;
    }

    @Deprecated
    public double getBeta() {
        return this.scale;
    }

    public double getScale() {
        return this.scale;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double density(double x) {
        if (x < 0.0d) {
            return 0.0d;
        }
        double y = x / this.scale;
        if (y <= this.minY || FastMath.log(y) >= this.maxLogY) {
            double aux1 = (y - this.shiftedShape) / this.shiftedShape;
            double aux2 = this.shape * (FastMath.log1p(aux1) - aux1);
            double aux3 = (((-y) * 5.2421875d) / this.shiftedShape) + 4.7421875d + aux2;
            return (this.densityPrefactor2 / x) * FastMath.exp(aux3);
        }
        return this.densityPrefactor1 * FastMath.exp(-y) * FastMath.pow(y, this.shape - 1.0d);
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    public double logDensity(double x) {
        if (x < 0.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        double y = x / this.scale;
        if (y <= this.minY || FastMath.log(y) >= this.maxLogY) {
            double aux1 = (y - this.shiftedShape) / this.shiftedShape;
            double aux2 = this.shape * (FastMath.log1p(aux1) - aux1);
            double aux3 = (((-y) * 5.2421875d) / this.shiftedShape) + 4.7421875d + aux2;
            return (this.logDensityPrefactor2 - FastMath.log(x)) + aux3;
        }
        return (this.logDensityPrefactor1 - y) + (FastMath.log(y) * (this.shape - 1.0d));
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double x) {
        if (x <= 0.0d) {
            return 0.0d;
        }
        double ret = this.shape;
        return Gamma.regularizedGammaP(ret, x / this.scale);
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        return this.shape * this.scale;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalVariance() {
        return this.shape * this.scale * this.scale;
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

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public double sample() {
        if (this.shape < 1.0d) {
            while (true) {
                double u = this.random.nextDouble();
                double bGS = (this.shape / 2.718281828459045d) + 1.0d;
                double p = bGS * u;
                if (p > 1.0d) {
                    double x = FastMath.log((bGS - p) / this.shape) * (-1.0d);
                    double u2 = this.random.nextDouble();
                    if (u2 <= FastMath.pow(x, this.shape - 1.0d)) {
                        return this.scale * x;
                    }
                } else {
                    double x2 = FastMath.pow(p, 1.0d / this.shape);
                    double u22 = this.random.nextDouble();
                    if (u22 <= FastMath.exp(-x2)) {
                        return this.scale * x2;
                    }
                }
            }
        } else {
            double d = this.shape - 0.3333333333333333d;
            double c = 1.0d / (FastMath.sqrt(d) * 3.0d);
            while (true) {
                double x3 = this.random.nextGaussian();
                double v = ((c * x3) + 1.0d) * ((c * x3) + 1.0d) * ((c * x3) + 1.0d);
                if (v > 0.0d) {
                    double x22 = x3 * x3;
                    double u3 = this.random.nextDouble();
                    if (u3 < 1.0d - ((0.0331d * x22) * x22)) {
                        return this.scale * d * v;
                    }
                    if (FastMath.log(u3) < (0.5d * x22) + (((1.0d - v) + FastMath.log(v)) * d)) {
                        return this.scale * d * v;
                    }
                }
            }
        }
    }
}
