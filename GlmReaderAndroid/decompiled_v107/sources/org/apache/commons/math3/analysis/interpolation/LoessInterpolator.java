package org.apache.commons.math3.analysis.interpolation;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class LoessInterpolator implements UnivariateInterpolator, Serializable {
    public static final double DEFAULT_ACCURACY = 1.0E-12d;
    public static final double DEFAULT_BANDWIDTH = 0.3d;
    public static final int DEFAULT_ROBUSTNESS_ITERS = 2;
    private static final long serialVersionUID = 5204927143605193821L;
    private final double accuracy;
    private final double bandwidth;
    private final int robustnessIters;

    public LoessInterpolator() {
        this.bandwidth = 0.3d;
        this.robustnessIters = 2;
        this.accuracy = 1.0E-12d;
    }

    public LoessInterpolator(double bandwidth, int robustnessIters) {
        this(bandwidth, robustnessIters, 1.0E-12d);
    }

    public LoessInterpolator(double bandwidth, int robustnessIters, double accuracy) throws OutOfRangeException, NotPositiveException {
        if (bandwidth < 0.0d || bandwidth > 1.0d) {
            throw new OutOfRangeException(LocalizedFormats.BANDWIDTH, Double.valueOf(bandwidth), 0, 1);
        }
        this.bandwidth = bandwidth;
        if (robustnessIters < 0) {
            throw new NotPositiveException(LocalizedFormats.ROBUSTNESS_ITERATIONS, Integer.valueOf(robustnessIters));
        }
        this.robustnessIters = robustnessIters;
        this.accuracy = accuracy;
    }

    @Override // org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator
    public final PolynomialSplineFunction interpolate(double[] xval, double[] yval) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        return new SplineInterpolator().interpolate(xval, smooth(xval, yval));
    }

    public final double[] smooth(double[] dArr, double[] dArr2, double[] dArr3) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        int i;
        double d;
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        int length = dArr.length;
        if (length == 0) {
            throw new NoDataException();
        }
        checkAllFiniteReal(dArr);
        checkAllFiniteReal(dArr2);
        checkAllFiniteReal(dArr3);
        MathArrays.checkOrder(dArr);
        int i2 = 0;
        boolean z = true;
        if (length == 1) {
            return new double[]{dArr2[0]};
        }
        if (length == 2) {
            return new double[]{dArr2[0], dArr2[1]};
        }
        int i3 = (int) (this.bandwidth * length);
        if (i3 < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.BANDWIDTH, Integer.valueOf(i3), 2, true);
        }
        double[] dArr4 = new double[length];
        double[] dArr5 = new double[length];
        double[] dArr6 = new double[length];
        double[] dArr7 = new double[length];
        double d2 = 1.0d;
        Arrays.fill(dArr7, 1.0d);
        int i4 = 0;
        while (i4 <= this.robustnessIters) {
            int[] iArr = {i2, i3 - 1};
            double d3 = d2;
            int i5 = 0;
            while (i5 < length) {
                double d4 = dArr[i5];
                if (i5 > 0) {
                    updateBandwidthInterval(dArr, dArr3, i5, iArr);
                }
                int i6 = iArr[i2];
                boolean z2 = z;
                int i7 = iArr[z2 ? 1 : 0];
                if (dArr[i5] - dArr[i6] > dArr[i7] - dArr[i5]) {
                    i = i6;
                } else {
                    i = i7;
                }
                double d5 = 0.0d;
                double d6 = 0.0d;
                double d7 = 0.0d;
                double d8 = 0.0d;
                double d9 = 0.0d;
                double abs = FastMath.abs(d3 / (dArr[i] - d4));
                int i8 = i6;
                while (i8 <= i7) {
                    double d10 = dArr[i8];
                    double d11 = dArr2[i8];
                    double tricube = tricube((i8 < i5 ? d4 - d10 : d10 - d4) * abs) * dArr7[i8] * dArr3[i8];
                    double d12 = d10 * tricube;
                    d5 += tricube;
                    d6 += d12;
                    d7 += d10 * d12;
                    d8 += d11 * tricube;
                    d9 += d11 * d12;
                    i8++;
                }
                double d13 = d6 / d5;
                double d14 = d8 / d5;
                double d15 = d9 / d5;
                double d16 = d7 / d5;
                if (FastMath.sqrt(FastMath.abs(d16 - (d13 * d13))) < this.accuracy) {
                    d = 0.0d;
                } else {
                    d = (d15 - (d13 * d14)) / (d16 - (d13 * d13));
                }
                dArr4[i5] = (d * d4) + (d14 - (d * d13));
                dArr5[i5] = FastMath.abs(dArr2[i5] - dArr4[i5]);
                i5++;
                z = z2 ? 1 : 0;
                i2 = 0;
            }
            boolean z3 = z;
            if (i4 == this.robustnessIters) {
                break;
            }
            System.arraycopy(dArr5, 0, dArr6, 0, length);
            Arrays.sort(dArr6);
            double d17 = dArr6[length / 2];
            if (FastMath.abs(d17) < this.accuracy) {
                break;
            }
            for (int i9 = 0; i9 < length; i9++) {
                double d18 = dArr5[i9] / (6.0d * d17);
                if (d18 >= d3) {
                    dArr7[i9] = 0.0d;
                } else {
                    double d19 = d3 - (d18 * d18);
                    dArr7[i9] = d19 * d19;
                }
            }
            i4++;
            z = z3;
            d2 = d3;
            i2 = 0;
        }
        return dArr4;
    }

    public final double[] smooth(double[] xval, double[] yval) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        if (xval.length != yval.length) {
            throw new DimensionMismatchException(xval.length, yval.length);
        }
        double[] unitWeights = new double[xval.length];
        Arrays.fill(unitWeights, 1.0d);
        return smooth(xval, yval, unitWeights);
    }

    private static void updateBandwidthInterval(double[] xval, double[] weights, int i, int[] bandwidthInterval) {
        int left = bandwidthInterval[0];
        int right = bandwidthInterval[1];
        int nextRight = nextNonzero(weights, right);
        if (nextRight < xval.length && xval[nextRight] - xval[i] < xval[i] - xval[left]) {
            int nextLeft = nextNonzero(weights, bandwidthInterval[0]);
            bandwidthInterval[0] = nextLeft;
            bandwidthInterval[1] = nextRight;
        }
    }

    private static int nextNonzero(double[] weights, int i) {
        int j = i + 1;
        while (j < weights.length && weights[j] == 0.0d) {
            j++;
        }
        return j;
    }

    private static double tricube(double x) {
        double absX = FastMath.abs(x);
        if (absX < 1.0d) {
            double tmp = 1.0d - ((absX * absX) * absX);
            return tmp * tmp * tmp;
        }
        return 0.0d;
    }

    private static void checkAllFiniteReal(double[] values) {
        for (double d : values) {
            MathUtils.checkFinite(d);
        }
    }
}
