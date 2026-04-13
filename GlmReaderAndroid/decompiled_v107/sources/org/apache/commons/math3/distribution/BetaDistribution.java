package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class BetaDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = -1221965979403477668L;
    private final double alpha;
    private final double beta;
    private final double solverAbsoluteAccuracy;
    private double z;

    public BetaDistribution(double alpha, double beta) {
        this(alpha, beta, 1.0E-9d);
    }

    public BetaDistribution(double alpha, double beta, double inverseCumAccuracy) {
        this(new Well19937c(), alpha, beta, inverseCumAccuracy);
    }

    public BetaDistribution(RandomGenerator rng, double alpha, double beta) {
        this(rng, alpha, beta, 1.0E-9d);
    }

    public BetaDistribution(RandomGenerator rng, double alpha, double beta, double inverseCumAccuracy) {
        super(rng);
        this.alpha = alpha;
        this.beta = beta;
        this.z = Double.NaN;
        this.solverAbsoluteAccuracy = inverseCumAccuracy;
    }

    public double getAlpha() {
        return this.alpha;
    }

    public double getBeta() {
        return this.beta;
    }

    private void recomputeZ() {
        if (Double.isNaN(this.z)) {
            this.z = (Gamma.logGamma(this.alpha) + Gamma.logGamma(this.beta)) - Gamma.logGamma(this.alpha + this.beta);
        }
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
        recomputeZ();
        if (x < 0.0d || x > 1.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        if (x == 0.0d) {
            if (this.alpha >= 1.0d) {
                return Double.NEGATIVE_INFINITY;
            }
            throw new NumberIsTooSmallException(LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_0_FOR_SOME_ALPHA, Double.valueOf(this.alpha), 1, false);
        }
        if (x == 1.0d) {
            if (this.beta >= 1.0d) {
                return Double.NEGATIVE_INFINITY;
            }
            throw new NumberIsTooSmallException(LocalizedFormats.CANNOT_COMPUTE_BETA_DENSITY_AT_1_FOR_SOME_BETA, Double.valueOf(this.beta), 1, false);
        }
        double logX = FastMath.log(x);
        double log1mX = FastMath.log1p(-x);
        return (((this.alpha - 1.0d) * logX) + ((this.beta - 1.0d) * log1mX)) - this.z;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double x) {
        if (x <= 0.0d) {
            return 0.0d;
        }
        if (x >= 1.0d) {
            return 1.0d;
        }
        return Beta.regularizedBeta(x, this.alpha, this.beta);
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution
    protected double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        double a = getAlpha();
        return a / (getBeta() + a);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalVariance() {
        double a = getAlpha();
        double b = getBeta();
        double alphabetasum = a + b;
        return (a * b) / ((alphabetasum * alphabetasum) * (1.0d + alphabetasum));
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportLowerBound() {
        return 0.0d;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportUpperBound() {
        return 1.0d;
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

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public double sample() {
        return ChengBetaSampler.sample(this.random, this.alpha, this.beta);
    }

    /* loaded from: classes10.dex */
    private static final class ChengBetaSampler {
        private ChengBetaSampler() {
        }

        static double sample(RandomGenerator random, double alpha, double beta) {
            double a = FastMath.min(alpha, beta);
            double b = FastMath.max(alpha, beta);
            if (a > 1.0d) {
                return algorithmBB(random, alpha, a, b);
            }
            return algorithmBC(random, alpha, b, a);
        }

        private static double algorithmBB(RandomGenerator random, double a0, double a, double b) {
            double w;
            double r;
            double t;
            double alpha = a + b;
            double beta = FastMath.sqrt((alpha - 2.0d) / (((2.0d * a) * b) - alpha));
            double gamma = (1.0d / beta) + a;
            do {
                double u1 = random.nextDouble();
                double u2 = random.nextDouble();
                double v = (FastMath.log(u1) - FastMath.log1p(-u1)) * beta;
                w = FastMath.exp(v) * a;
                double z = u1 * u1 * u2;
                r = (gamma * v) - 1.3862944d;
                double s = (a + r) - w;
                if (s + 2.609438d >= 5.0d * z) {
                    break;
                }
                t = FastMath.log(z);
                if (s >= t) {
                    break;
                }
            } while (r + ((FastMath.log(alpha) - FastMath.log(b + w)) * alpha) < t);
            double w2 = FastMath.min(w, Double.MAX_VALUE);
            return Precision.equals(a, a0) ? w2 / (b + w2) : b / (b + w2);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x00bb  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x009f A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static double algorithmBC(org.apache.commons.math3.random.RandomGenerator r30, double r31, double r33, double r35) {
            /*
                r0 = r33
                double r2 = r0 + r35
                r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
                double r6 = r4 / r35
                double r4 = r4 + r0
                double r4 = r4 - r35
                r8 = 4586165625342794696(0x3fa5555673aa1bc8, double:0.0416667)
                double r8 = r8 * r35
                r10 = 4579160027523720458(0x3f8c71c89a38250a, double:0.0138889)
                double r8 = r8 + r10
                double r8 = r8 * r4
                double r10 = r0 * r6
                r12 = 4605180820967230355(0x3fe8e38eb0318b93, double:0.777778)
                double r10 = r10 - r12
                double r8 = r8 / r10
                r10 = 4598175219545276416(0x3fd0000000000000, double:0.25)
                double r12 = r10 / r4
                r14 = 4602678819172646912(0x3fe0000000000000, double:0.5)
                double r12 = r12 + r14
                double r12 = r12 * r35
                double r12 = r12 + r10
            L2c:
                r16 = r10
                double r10 = r30.nextDouble()
                double r18 = r30.nextDouble()
                double r20 = r10 * r18
                double r22 = r10 * r20
                int r24 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
                if (r24 >= 0) goto L4e
                double r24 = r18 * r16
                double r24 = r24 + r22
                double r24 = r24 - r20
                int r24 = (r24 > r8 ? 1 : (r24 == r8 ? 0 : -1))
                if (r24 < 0) goto L4b
                r10 = r16
                goto L2c
            L4b:
                r24 = r2
                goto L70
            L4e:
                int r24 = (r22 > r16 ? 1 : (r22 == r16 ? 0 : -1))
                if (r24 > 0) goto L65
                double r14 = org.apache.commons.math3.util.FastMath.log(r10)
                r24 = r2
                double r2 = -r10
                double r2 = org.apache.commons.math3.util.FastMath.log1p(r2)
                double r14 = r14 - r2
                double r14 = r14 * r6
                double r2 = org.apache.commons.math3.util.FastMath.exp(r14)
                double r2 = r2 * r0
                goto La0
            L65:
                r24 = r2
                int r2 = (r22 > r12 ? 1 : (r22 == r12 ? 0 : -1))
                if (r2 < 0) goto L70
                r10 = r16
                r2 = r24
                goto L2c
            L70:
                double r2 = org.apache.commons.math3.util.FastMath.log(r10)
                double r14 = -r10
                double r14 = org.apache.commons.math3.util.FastMath.log1p(r14)
                double r2 = r2 - r14
                double r2 = r2 * r6
                double r14 = org.apache.commons.math3.util.FastMath.exp(r2)
                double r14 = r14 * r0
                double r26 = org.apache.commons.math3.util.FastMath.log(r24)
                double r28 = r35 + r14
                double r28 = org.apache.commons.math3.util.FastMath.log(r28)
                double r26 = r26 - r28
                double r26 = r26 + r2
                double r26 = r26 * r24
                r28 = 4608922134115912717(0x3ff62e43096a0c0d, double:1.3862944)
                double r26 = r26 - r28
                double r28 = org.apache.commons.math3.util.FastMath.log(r22)
                int r26 = (r26 > r28 ? 1 : (r26 == r28 ? 0 : -1))
                if (r26 < 0) goto Lbb
                r2 = r14
            La0:
                r10 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
                double r2 = org.apache.commons.math3.util.FastMath.min(r2, r10)
                r10 = r31
                boolean r14 = org.apache.commons.math3.util.Precision.equals(r0, r10)
                if (r14 == 0) goto Lb6
                double r14 = r35 + r2
                double r14 = r2 / r14
                goto Lba
            Lb6:
                double r14 = r35 + r2
                double r14 = r35 / r14
            Lba:
                return r14
            Lbb:
                r10 = r16
                r2 = r24
                r14 = 4602678819172646912(0x3fe0000000000000, double:0.5)
                goto L2c
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.distribution.BetaDistribution.ChengBetaSampler.algorithmBC(org.apache.commons.math3.random.RandomGenerator, double, double, double):double");
        }
    }
}
