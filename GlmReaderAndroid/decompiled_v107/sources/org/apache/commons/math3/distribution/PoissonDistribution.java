package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class PoissonDistribution extends AbstractIntegerDistribution {
    public static final double DEFAULT_EPSILON = 1.0E-12d;
    public static final int DEFAULT_MAX_ITERATIONS = 10000000;
    private static final long serialVersionUID = -3349935121172596109L;
    private final double epsilon;
    private final ExponentialDistribution exponential;
    private final int maxIterations;
    private final double mean;
    private final NormalDistribution normal;

    public PoissonDistribution(double p) throws NotStrictlyPositiveException {
        this(p, 1.0E-12d, DEFAULT_MAX_ITERATIONS);
    }

    public PoissonDistribution(double p, double epsilon, int maxIterations) throws NotStrictlyPositiveException {
        this(new Well19937c(), p, epsilon, maxIterations);
    }

    public PoissonDistribution(RandomGenerator rng, double p, double epsilon, int maxIterations) throws NotStrictlyPositiveException {
        super(rng);
        if (p <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, Double.valueOf(p));
        }
        this.mean = p;
        this.epsilon = epsilon;
        this.maxIterations = maxIterations;
        this.normal = new NormalDistribution(rng, p, FastMath.sqrt(p), 1.0E-9d);
        this.exponential = new ExponentialDistribution(rng, 1.0d, 1.0E-9d);
    }

    public PoissonDistribution(double p, double epsilon) throws NotStrictlyPositiveException {
        this(p, epsilon, DEFAULT_MAX_ITERATIONS);
    }

    public PoissonDistribution(double p, int maxIterations) {
        this(p, 1.0E-12d, maxIterations);
    }

    public double getMean() {
        return this.mean;
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public double probability(int x) {
        double logProbability = logProbability(x);
        if (logProbability == Double.NEGATIVE_INFINITY) {
            return 0.0d;
        }
        return FastMath.exp(logProbability);
    }

    @Override // org.apache.commons.math3.distribution.AbstractIntegerDistribution
    public double logProbability(int x) {
        if (x < 0 || x == Integer.MAX_VALUE) {
            return Double.NEGATIVE_INFINITY;
        }
        if (x == 0) {
            double ret = -this.mean;
            return ret;
        }
        double ret2 = x;
        return (((-SaddlePointExpansion.getStirlingError(ret2)) - SaddlePointExpansion.getDeviancePart(x, this.mean)) - (FastMath.log(6.283185307179586d) * 0.5d)) - (FastMath.log(x) * 0.5d);
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public double cumulativeProbability(int x) {
        if (x < 0) {
            return 0.0d;
        }
        if (x == Integer.MAX_VALUE) {
            return 1.0d;
        }
        return Gamma.regularizedGammaQ(x + 1.0d, this.mean, this.epsilon, this.maxIterations);
    }

    public double normalApproximateProbability(int x) {
        return this.normal.cumulativeProbability(x + 0.5d);
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public double getNumericalMean() {
        return getMean();
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public double getNumericalVariance() {
        return getMean();
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public int getSupportLowerBound() {
        return 0;
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public int getSupportUpperBound() {
        return Integer.MAX_VALUE;
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public boolean isSupportConnected() {
        return true;
    }

    @Override // org.apache.commons.math3.distribution.AbstractIntegerDistribution, org.apache.commons.math3.distribution.IntegerDistribution
    public int sample() {
        return (int) FastMath.min(nextPoisson(this.mean), 2147483647L);
    }

    private long nextPoisson(double meanPoisson) {
        double delta;
        double aSum;
        double y;
        double x;
        double v;
        double y2;
        if (meanPoisson < 40.0d) {
            double p = FastMath.exp(-meanPoisson);
            long n = 0;
            double r = 1.0d;
            while (n < 1000.0d * meanPoisson) {
                double rnd = this.random.nextDouble();
                r *= rnd;
                if (r >= p) {
                    n++;
                } else {
                    return n;
                }
            }
            return n;
        }
        double lambda = FastMath.floor(meanPoisson);
        double lambdaFractional = meanPoisson - lambda;
        double logLambda = FastMath.log(lambda);
        double logLambdaFactorial = CombinatoricsUtils.factorialLog((int) lambda);
        long y22 = lambdaFractional < Double.MIN_VALUE ? 0L : nextPoisson(lambdaFractional);
        double delta2 = FastMath.sqrt(FastMath.log(((32.0d * lambda) / 3.141592653589793d) + 1.0d) * lambda);
        double u = 2.0d;
        double halfDelta = delta2 / 2.0d;
        double twolpd = (lambda * 2.0d) + delta2;
        double a1 = FastMath.sqrt(3.141592653589793d * twolpd) * FastMath.exp(1.0d / (lambda * 8.0d));
        double pivot = -delta2;
        double a2 = (twolpd / delta2) * FastMath.exp((pivot * (delta2 + 1.0d)) / twolpd);
        double aSum2 = a1 + a2 + 1.0d;
        double p1 = a1 / aSum2;
        double p2 = a2 / aSum2;
        double c1 = 1.0d / (8.0d * lambda);
        int a = 0;
        double t = 0.0d;
        while (true) {
            double d = u;
            double u2 = this.random.nextDouble();
            if (u2 <= p1) {
                delta = delta2;
                double n2 = this.random.nextGaussian();
                x = (FastMath.sqrt(lambda + halfDelta) * n2) - 0.5d;
                if (x <= delta && x >= (-lambda)) {
                    double y3 = x < 0.0d ? FastMath.floor(x) : FastMath.ceil(x);
                    y2 = y3;
                    double e = this.exponential.sample();
                    aSum = aSum2;
                    double v2 = ((-e) - ((n2 * n2) / d)) + c1;
                    v = v2;
                }
                u = d;
                delta2 = delta;
                aSum2 = aSum2;
            } else {
                delta = delta2;
                aSum = aSum2;
                if (u2 > p1 + p2) {
                    y = lambda;
                    break;
                }
                double y4 = twolpd / delta;
                double x2 = delta + (y4 * this.exponential.sample());
                double y5 = FastMath.ceil(x2);
                x = x2;
                v = (-this.exponential.sample()) - (((x + 1.0d) * delta) / twolpd);
                y2 = y5;
            }
            int a3 = x < 0.0d ? 1 : 0;
            double t2 = ((y2 + 1.0d) * y2) / (lambda * d);
            double lambda2 = lambda;
            if (v < (-t2) && a3 == 0) {
                y = lambda2 + y2;
                break;
            }
            double qr = t2 * ((((y2 * d) + 1.0d) / (6.0d * lambda2)) - 1.0d);
            double d2 = t2 * t2;
            double t3 = a3;
            double qa = qr - (d2 / ((lambda2 + (t3 * (y2 + 1.0d))) * 3.0d));
            if (v < qa) {
                y = lambda2 + y2;
                break;
            }
            if (v > qr || v >= ((y2 * logLambda) - CombinatoricsUtils.factorialLog((int) (y2 + lambda2))) + logLambdaFactorial) {
                lambda = lambda2;
                u = d;
                t = t2;
                aSum2 = aSum;
                a = a3;
                delta2 = delta;
            } else {
                y = lambda2 + y2;
                break;
            }
        }
        return ((long) y) + y22;
    }
}
