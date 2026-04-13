package org.apache.commons.math3.fitting;

import org.apache.commons.math3.analysis.function.HarmonicOscillator;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.nonlinear.vector.MultivariateVectorOptimizer;
import org.apache.commons.math3.util.FastMath;

@Deprecated
/* loaded from: classes10.dex */
public class HarmonicFitter extends CurveFitter<HarmonicOscillator.Parametric> {
    public HarmonicFitter(MultivariateVectorOptimizer optimizer) {
        super(optimizer);
    }

    public double[] fit(double[] initialGuess) {
        return fit(new HarmonicOscillator.Parametric(), initialGuess);
    }

    public double[] fit() {
        return fit(new ParameterGuesser(getObservations()).guess());
    }

    /* loaded from: classes10.dex */
    public static class ParameterGuesser {
        private final double a;
        private final double omega;
        private final double phi;

        public ParameterGuesser(WeightedObservedPoint[] observations) {
            if (observations.length < 4) {
                throw new NumberIsTooSmallException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(observations.length), 4, true);
            }
            WeightedObservedPoint[] sorted = sortObservations(observations);
            double[] aOmega = guessAOmega(sorted);
            this.a = aOmega[0];
            this.omega = aOmega[1];
            this.phi = guessPhi(sorted);
        }

        public double[] guess() {
            return new double[]{this.a, this.omega, this.phi};
        }

        private WeightedObservedPoint[] sortObservations(WeightedObservedPoint[] unsorted) {
            WeightedObservedPoint[] observations = (WeightedObservedPoint[]) unsorted.clone();
            WeightedObservedPoint curr = observations[0];
            for (int j = 1; j < observations.length; j++) {
                WeightedObservedPoint prec = curr;
                curr = observations[j];
                if (curr.getX() < prec.getX()) {
                    int i = j - 1;
                    WeightedObservedPoint mI = observations[i];
                    while (i >= 0 && curr.getX() < mI.getX()) {
                        observations[i + 1] = mI;
                        int i2 = i - 1;
                        if (i == 0) {
                            i = i2;
                        } else {
                            mI = observations[i2];
                            i = i2;
                        }
                    }
                    observations[i + 1] = curr;
                    curr = observations[j];
                }
            }
            return observations;
        }

        private double[] guessAOmega(WeightedObservedPoint[] observations) {
            double[] aOmega;
            char c;
            double[] aOmega2 = new double[2];
            double sx2 = 0.0d;
            double sy2 = 0.0d;
            double sxy = 0.0d;
            double sxz = 0.0d;
            double syz = 0.0d;
            double currentX = observations[0].getX();
            double currentY = observations[0].getY();
            double f2Integral = 0.0d;
            double fPrime2Integral = 0.0d;
            int i = 1;
            while (true) {
                aOmega = aOmega2;
                if (i >= observations.length) {
                    break;
                }
                double previousX = currentX;
                double previousY = currentY;
                currentX = observations[i].getX();
                currentY = observations[i].getY();
                double dx = currentX - previousX;
                double dy = currentY - previousY;
                double f2StepIntegral = ((((previousY * previousY) + (previousY * currentY)) + (currentY * currentY)) * dx) / 3.0d;
                double fPrime2StepIntegral = (dy * dy) / dx;
                double x = currentX - currentX;
                f2Integral += f2StepIntegral;
                fPrime2Integral += fPrime2StepIntegral;
                sx2 += x * x;
                sy2 += f2Integral * f2Integral;
                sxy += x * f2Integral;
                sxz += x * fPrime2Integral;
                syz += f2Integral * fPrime2Integral;
                i++;
                aOmega2 = aOmega;
            }
            double c1 = (sy2 * sxz) - (sxy * syz);
            double c2 = (sxy * sxz) - (sx2 * syz);
            double c3 = (sx2 * sy2) - (sxy * sxy);
            if (c1 / c2 < 0.0d) {
                c = 0;
            } else {
                if (c2 / c3 >= 0.0d) {
                    if (c2 != 0.0d) {
                        aOmega[0] = FastMath.sqrt(c1 / c2);
                        aOmega[1] = FastMath.sqrt(c2 / c3);
                        return aOmega;
                    }
                    throw new MathIllegalStateException(LocalizedFormats.ZERO_DENOMINATOR, new Object[0]);
                }
                c = 0;
            }
            int last = observations.length - 1;
            double xRange = observations[last].getX() - observations[c].getX();
            if (xRange == 0.0d) {
                throw new ZeroException();
            }
            aOmega[1] = 6.283185307179586d / xRange;
            double yMin = Double.POSITIVE_INFINITY;
            double yMax = Double.NEGATIVE_INFINITY;
            int i2 = 1;
            while (true) {
                int last2 = last;
                if (i2 >= observations.length) {
                    break;
                }
                double y = observations[i2].getY();
                if (y < yMin) {
                    yMin = y;
                }
                if (y > yMax) {
                    yMax = y;
                }
                i2++;
                last = last2;
            }
            aOmega[0] = (yMax - yMin) * 0.5d;
            return aOmega;
        }

        private double guessPhi(WeightedObservedPoint[] observations) {
            double fcMean = 0.0d;
            double fsMean = 0.0d;
            double currentX = observations[0].getX();
            double currentY = observations[0].getY();
            int i = 1;
            while (i < observations.length) {
                double previousX = currentX;
                double previousY = currentY;
                double currentX2 = observations[i].getX();
                currentY = observations[i].getY();
                double currentYPrime = (currentY - previousY) / (currentX2 - previousX);
                int i2 = i;
                double omegaX = this.omega * currentX2;
                double cosine = FastMath.cos(omegaX);
                double sine = FastMath.sin(omegaX);
                fcMean += ((this.omega * currentY) * cosine) - (currentYPrime * sine);
                fsMean += (this.omega * currentY * sine) + (currentYPrime * cosine);
                i = i2 + 1;
                currentX = currentX2;
            }
            return FastMath.atan2(-fsMean, fcMean);
        }
    }
}
