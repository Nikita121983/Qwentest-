package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class ZipfDistribution extends AbstractIntegerDistribution {
    private static final long serialVersionUID = -140627372283420404L;
    private final double exponent;
    private final int numberOfElements;
    private double numericalMean;
    private boolean numericalMeanIsCalculated;
    private double numericalVariance;
    private boolean numericalVarianceIsCalculated;
    private transient ZipfRejectionInversionSampler sampler;

    public ZipfDistribution(int numberOfElements, double exponent) {
        this(new Well19937c(), numberOfElements, exponent);
    }

    public ZipfDistribution(RandomGenerator rng, int numberOfElements, double exponent) throws NotStrictlyPositiveException {
        super(rng);
        this.numericalMean = Double.NaN;
        this.numericalMeanIsCalculated = false;
        this.numericalVariance = Double.NaN;
        this.numericalVarianceIsCalculated = false;
        if (numberOfElements <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, Integer.valueOf(numberOfElements));
        }
        if (exponent <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.EXPONENT, Double.valueOf(exponent));
        }
        this.numberOfElements = numberOfElements;
        this.exponent = exponent;
    }

    public int getNumberOfElements() {
        return this.numberOfElements;
    }

    public double getExponent() {
        return this.exponent;
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public double probability(int x) {
        if (x <= 0 || x > this.numberOfElements) {
            return 0.0d;
        }
        return (1.0d / FastMath.pow(x, this.exponent)) / generalizedHarmonic(this.numberOfElements, this.exponent);
    }

    @Override // org.apache.commons.math3.distribution.AbstractIntegerDistribution
    public double logProbability(int x) {
        if (x <= 0 || x > this.numberOfElements) {
            return Double.NEGATIVE_INFINITY;
        }
        return ((-FastMath.log(x)) * this.exponent) - FastMath.log(generalizedHarmonic(this.numberOfElements, this.exponent));
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public double cumulativeProbability(int x) {
        if (x <= 0) {
            return 0.0d;
        }
        if (x >= this.numberOfElements) {
            return 1.0d;
        }
        return generalizedHarmonic(x, this.exponent) / generalizedHarmonic(this.numberOfElements, this.exponent);
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public double getNumericalMean() {
        if (!this.numericalMeanIsCalculated) {
            this.numericalMean = calculateNumericalMean();
            this.numericalMeanIsCalculated = true;
        }
        return this.numericalMean;
    }

    protected double calculateNumericalMean() {
        int N = getNumberOfElements();
        double s = getExponent();
        double Hs1 = generalizedHarmonic(N, s - 1.0d);
        double Hs = generalizedHarmonic(N, s);
        return Hs1 / Hs;
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public double getNumericalVariance() {
        if (!this.numericalVarianceIsCalculated) {
            this.numericalVariance = calculateNumericalVariance();
            this.numericalVarianceIsCalculated = true;
        }
        return this.numericalVariance;
    }

    protected double calculateNumericalVariance() {
        int N = getNumberOfElements();
        double s = getExponent();
        double Hs2 = generalizedHarmonic(N, s - 2.0d);
        double Hs1 = generalizedHarmonic(N, s - 1.0d);
        double Hs = generalizedHarmonic(N, s);
        return (Hs2 / Hs) - ((Hs1 * Hs1) / (Hs * Hs));
    }

    private double generalizedHarmonic(int n, double m) {
        double value = 0.0d;
        for (int k = n; k > 0; k--) {
            value += 1.0d / FastMath.pow(k, m);
        }
        return value;
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public int getSupportLowerBound() {
        return 1;
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public int getSupportUpperBound() {
        return getNumberOfElements();
    }

    @Override // org.apache.commons.math3.distribution.IntegerDistribution
    public boolean isSupportConnected() {
        return true;
    }

    @Override // org.apache.commons.math3.distribution.AbstractIntegerDistribution, org.apache.commons.math3.distribution.IntegerDistribution
    public int sample() {
        if (this.sampler == null) {
            this.sampler = new ZipfRejectionInversionSampler(this.numberOfElements, this.exponent);
        }
        return this.sampler.sample(this.random);
    }

    /* loaded from: classes10.dex */
    static final class ZipfRejectionInversionSampler {
        private final double exponent;
        private final double hIntegralNumberOfElements;
        private final int numberOfElements;
        private final double hIntegralX1 = hIntegral(1.5d) - 1.0d;
        private final double s = 2.0d - hIntegralInverse(hIntegral(2.5d) - h(2.0d));

        ZipfRejectionInversionSampler(int numberOfElements, double exponent) {
            this.exponent = exponent;
            this.numberOfElements = numberOfElements;
            this.hIntegralNumberOfElements = hIntegral(numberOfElements + 0.5d);
        }

        int sample(RandomGenerator random) {
            double u;
            int k;
            do {
                u = this.hIntegralNumberOfElements + (random.nextDouble() * (this.hIntegralX1 - this.hIntegralNumberOfElements));
                double x = hIntegralInverse(u);
                k = (int) (x + 0.5d);
                if (k < 1) {
                    k = 1;
                } else if (k > this.numberOfElements) {
                    k = this.numberOfElements;
                }
                if (k - x <= this.s) {
                    break;
                }
            } while (u < hIntegral(k + 0.5d) - h(k));
            return k;
        }

        private double hIntegral(double x) {
            double logX = FastMath.log(x);
            return helper2((1.0d - this.exponent) * logX) * logX;
        }

        private double h(double x) {
            return FastMath.exp((-this.exponent) * FastMath.log(x));
        }

        private double hIntegralInverse(double x) {
            double t = (1.0d - this.exponent) * x;
            if (t < -1.0d) {
                t = -1.0d;
            }
            return FastMath.exp(helper1(t) * x);
        }

        static double helper1(double x) {
            if (FastMath.abs(x) > 1.0E-8d) {
                return FastMath.log1p(x) / x;
            }
            return 1.0d - ((0.5d - ((0.3333333333333333d - (0.25d * x)) * x)) * x);
        }

        static double helper2(double x) {
            if (FastMath.abs(x) > 1.0E-8d) {
                return FastMath.expm1(x) / x;
            }
            return (0.5d * x * ((0.3333333333333333d * x * ((0.25d * x) + 1.0d)) + 1.0d)) + 1.0d;
        }
    }
}
