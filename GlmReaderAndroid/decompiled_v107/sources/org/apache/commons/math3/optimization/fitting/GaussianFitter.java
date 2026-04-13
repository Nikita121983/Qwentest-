package org.apache.commons.math3.optimization.fitting;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.function.Gaussian;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
import org.apache.commons.math3.util.FastMath;

@Deprecated
/* loaded from: classes10.dex */
public class GaussianFitter extends CurveFitter<Gaussian.Parametric> {
    public GaussianFitter(DifferentiableMultivariateVectorOptimizer optimizer) {
        super(optimizer);
    }

    public double[] fit(double[] initialGuess) {
        Gaussian.Parametric f = new Gaussian.Parametric() { // from class: org.apache.commons.math3.optimization.fitting.GaussianFitter.1
            @Override // org.apache.commons.math3.analysis.function.Gaussian.Parametric, org.apache.commons.math3.analysis.ParametricUnivariateFunction
            public double value(double x, double... p) {
                try {
                    double v = super.value(x, p);
                    return v;
                } catch (NotStrictlyPositiveException e) {
                    return Double.POSITIVE_INFINITY;
                }
            }

            @Override // org.apache.commons.math3.analysis.function.Gaussian.Parametric, org.apache.commons.math3.analysis.ParametricUnivariateFunction
            public double[] gradient(double x, double... p) {
                double[] v = {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
                try {
                    double[] v2 = super.gradient(x, p);
                    return v2;
                } catch (NotStrictlyPositiveException e) {
                    return v;
                }
            }
        };
        return fit(f, initialGuess);
    }

    public double[] fit() {
        double[] guess = new ParameterGuesser(getObservations()).guess();
        return fit(guess);
    }

    /* loaded from: classes10.dex */
    public static class ParameterGuesser {
        private final double mean;
        private final double norm;
        private final double sigma;

        public ParameterGuesser(WeightedObservedPoint[] observations) {
            if (observations == null) {
                throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
            }
            if (observations.length < 3) {
                throw new NumberIsTooSmallException(Integer.valueOf(observations.length), 3, true);
            }
            WeightedObservedPoint[] sorted = sortObservations(observations);
            double[] params = basicGuess(sorted);
            this.norm = params[0];
            this.mean = params[1];
            this.sigma = params[2];
        }

        public double[] guess() {
            return new double[]{this.norm, this.mean, this.sigma};
        }

        private WeightedObservedPoint[] sortObservations(WeightedObservedPoint[] unsorted) {
            WeightedObservedPoint[] observations = (WeightedObservedPoint[]) unsorted.clone();
            Comparator<WeightedObservedPoint> cmp = new Comparator<WeightedObservedPoint>() { // from class: org.apache.commons.math3.optimization.fitting.GaussianFitter.ParameterGuesser.1
                @Override // java.util.Comparator
                public int compare(WeightedObservedPoint p1, WeightedObservedPoint p2) {
                    if (p1 == null && p2 == null) {
                        return 0;
                    }
                    if (p1 == null) {
                        return -1;
                    }
                    if (p2 == null) {
                        return 1;
                    }
                    int cmpX = Double.compare(p1.getX(), p2.getX());
                    if (cmpX < 0) {
                        return -1;
                    }
                    if (cmpX > 0) {
                        return 1;
                    }
                    int cmpY = Double.compare(p1.getY(), p2.getY());
                    if (cmpY < 0) {
                        return -1;
                    }
                    if (cmpY > 0) {
                        return 1;
                    }
                    int cmpW = Double.compare(p1.getWeight(), p2.getWeight());
                    if (cmpW < 0) {
                        return -1;
                    }
                    if (cmpW <= 0) {
                        return 0;
                    }
                    return 1;
                }
            };
            Arrays.sort(observations, cmp);
            return observations;
        }

        private double[] basicGuess(WeightedObservedPoint[] points) {
            double fwhmX2;
            int maxYIdx = findMaxY(points);
            double n = points[maxYIdx].getY();
            double m = points[maxYIdx].getX();
            double halfY = n + ((m - n) / 2.0d);
            WeightedObservedPoint[] weightedObservedPointArr = points;
            try {
                double fwhmX1 = interpolateXAtY(weightedObservedPointArr, maxYIdx, -1, halfY);
                weightedObservedPointArr = points;
                double fwhmX22 = interpolateXAtY(weightedObservedPointArr, maxYIdx, 1, halfY);
                fwhmX2 = fwhmX22 - fwhmX1;
            } catch (OutOfRangeException e) {
                fwhmX2 = weightedObservedPointArr[weightedObservedPointArr.length - 1].getX() - weightedObservedPointArr[0].getX();
            }
            double s = fwhmX2 / (FastMath.sqrt(FastMath.log(2.0d) * 2.0d) * 2.0d);
            return new double[]{n, m, s};
        }

        private int findMaxY(WeightedObservedPoint[] points) {
            int maxYIdx = 0;
            for (int i = 1; i < points.length; i++) {
                if (points[i].getY() > points[maxYIdx].getY()) {
                    maxYIdx = i;
                }
            }
            return maxYIdx;
        }

        private double interpolateXAtY(WeightedObservedPoint[] points, int startIdx, int idxStep, double y) throws OutOfRangeException {
            if (idxStep == 0) {
                throw new ZeroException();
            }
            WeightedObservedPoint[] twoPoints = getInterpolationPointsForY(points, startIdx, idxStep, y);
            WeightedObservedPoint p1 = twoPoints[0];
            WeightedObservedPoint p2 = twoPoints[1];
            if (p1.getY() == y) {
                return p1.getX();
            }
            if (p2.getY() == y) {
                return p2.getX();
            }
            return p1.getX() + (((y - p1.getY()) * (p2.getX() - p1.getX())) / (p2.getY() - p1.getY()));
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x0038 A[LOOP:0: B:3:0x0003->B:8:0x0038, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0025 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private org.apache.commons.math3.optimization.fitting.WeightedObservedPoint[] getInterpolationPointsForY(org.apache.commons.math3.optimization.fitting.WeightedObservedPoint[] r11, int r12, int r13, double r14) throws org.apache.commons.math3.exception.OutOfRangeException {
            /*
                r10 = this;
                if (r13 == 0) goto L52
                r0 = r12
            L3:
                int r1 = r0 + r13
                if (r13 >= 0) goto Lc
                if (r1 < 0) goto La
                goto Lf
            La:
                r4 = r14
                goto L3c
            Lc:
                int r2 = r11.length
                if (r1 >= r2) goto L3b
            Lf:
                r1 = r11[r0]
                int r2 = r0 + r13
                r2 = r11[r2]
                double r6 = r1.getY()
                double r8 = r2.getY()
                r3 = r10
                r4 = r14
                boolean r14 = r3.isBetween(r4, r6, r8)
                if (r14 == 0) goto L38
                r14 = 1
                r15 = 0
                r3 = 2
                if (r13 >= 0) goto L31
                org.apache.commons.math3.optimization.fitting.WeightedObservedPoint[] r3 = new org.apache.commons.math3.optimization.fitting.WeightedObservedPoint[r3]
                r3[r15] = r2
                r3[r14] = r1
                return r3
            L31:
                org.apache.commons.math3.optimization.fitting.WeightedObservedPoint[] r3 = new org.apache.commons.math3.optimization.fitting.WeightedObservedPoint[r3]
                r3[r15] = r1
                r3[r14] = r2
                return r3
            L38:
                int r0 = r0 + r13
                r14 = r4
                goto L3
            L3b:
                r4 = r14
            L3c:
                org.apache.commons.math3.exception.OutOfRangeException r14 = new org.apache.commons.math3.exception.OutOfRangeException
                java.lang.Double r15 = java.lang.Double.valueOf(r4)
                r0 = -4503599627370496(0xfff0000000000000, double:-Infinity)
                java.lang.Double r0 = java.lang.Double.valueOf(r0)
                r1 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
                java.lang.Double r1 = java.lang.Double.valueOf(r1)
                r14.<init>(r15, r0, r1)
                throw r14
            L52:
                r4 = r14
                org.apache.commons.math3.exception.ZeroException r14 = new org.apache.commons.math3.exception.ZeroException
                r14.<init>()
                throw r14
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.fitting.GaussianFitter.ParameterGuesser.getInterpolationPointsForY(org.apache.commons.math3.optimization.fitting.WeightedObservedPoint[], int, int, double):org.apache.commons.math3.optimization.fitting.WeightedObservedPoint[]");
        }

        private boolean isBetween(double value, double boundary1, double boundary2) {
            return (value >= boundary1 && value <= boundary2) || (value >= boundary2 && value <= boundary1);
        }
    }
}
