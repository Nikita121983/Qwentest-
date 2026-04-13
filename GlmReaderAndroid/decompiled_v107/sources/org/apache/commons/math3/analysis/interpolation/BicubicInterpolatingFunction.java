package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class BicubicInterpolatingFunction implements BivariateFunction {
    private static final double[][] AINV = {new double[]{1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d}, new double[]{-3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d}, new double[]{9.0d, -9.0d, -9.0d, 9.0d, 6.0d, 3.0d, -6.0d, -3.0d, 6.0d, -6.0d, 3.0d, -3.0d, 4.0d, 2.0d, 2.0d, 1.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, -3.0d, -3.0d, 3.0d, 3.0d, -4.0d, 4.0d, -2.0d, 2.0d, -2.0d, -2.0d, -1.0d, -1.0d}, new double[]{2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, -4.0d, -2.0d, 4.0d, 2.0d, -3.0d, 3.0d, -3.0d, 3.0d, -2.0d, -1.0d, -2.0d, -1.0d}, new double[]{4.0d, -4.0d, -4.0d, 4.0d, 2.0d, 2.0d, -2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 1.0d, 1.0d, 1.0d, 1.0d}};
    private static final int NUM_COEFF = 16;
    private final BicubicFunction[][] splines;
    private final double[] xval;
    private final double[] yval;

    public BicubicInterpolatingFunction(double[] dArr, double[] dArr2, double[][] dArr3, double[][] dArr4, double[][] dArr5, double[][] dArr6) throws DimensionMismatchException, NoDataException, NonMonotonicSequenceException {
        int length = dArr.length;
        int length2 = dArr2.length;
        if (length != 0 && length2 != 0 && dArr3.length != 0) {
            boolean z = false;
            if (dArr3[0].length != 0) {
                if (length != dArr3.length) {
                    throw new DimensionMismatchException(length, dArr3.length);
                }
                if (length != dArr4.length) {
                    throw new DimensionMismatchException(length, dArr4.length);
                }
                if (length != dArr5.length) {
                    throw new DimensionMismatchException(length, dArr5.length);
                }
                if (length != dArr6.length) {
                    throw new DimensionMismatchException(length, dArr6.length);
                }
                MathArrays.checkOrder(dArr);
                MathArrays.checkOrder(dArr2);
                this.xval = (double[]) dArr.clone();
                this.yval = (double[]) dArr2.clone();
                int i = length - 1;
                int i2 = length2 - 1;
                char c = 2;
                char c2 = 1;
                this.splines = (BicubicFunction[][]) Array.newInstance((Class<?>) BicubicFunction.class, i, i2);
                int i3 = 0;
                while (i3 < i) {
                    if (dArr3[i3].length != length2) {
                        throw new DimensionMismatchException(dArr3[i3].length, length2);
                    }
                    if (dArr4[i3].length != length2) {
                        throw new DimensionMismatchException(dArr4[i3].length, length2);
                    }
                    if (dArr5[i3].length != length2) {
                        throw new DimensionMismatchException(dArr5[i3].length, length2);
                    }
                    if (dArr6[i3].length != length2) {
                        throw new DimensionMismatchException(dArr6[i3].length, length2);
                    }
                    int i4 = i3 + 1;
                    boolean z2 = z;
                    double d = this.xval[i4] - this.xval[i3];
                    int i5 = 0;
                    while (i5 < i2) {
                        int i6 = i5 + 1;
                        char c3 = c;
                        double d2 = this.yval[i6] - this.yval[i5];
                        double d3 = d * d2;
                        double d4 = dArr3[i3][i5];
                        double d5 = dArr3[i4][i5];
                        double d6 = dArr3[i3][i6];
                        double d7 = dArr3[i4][i6];
                        double d8 = dArr4[i3][i5] * d;
                        double d9 = dArr4[i4][i5] * d;
                        double d10 = dArr4[i3][i6] * d;
                        double d11 = dArr4[i4][i6] * d;
                        double d12 = dArr5[i3][i5] * d2;
                        double d13 = dArr5[i4][i5] * d2;
                        double d14 = dArr5[i3][i6] * d2;
                        double d15 = dArr5[i4][i6] * d2;
                        double d16 = dArr6[i3][i5] * d3;
                        double d17 = dArr6[i4][i5] * d3;
                        double d18 = dArr6[i3][i6] * d3;
                        double d19 = dArr6[i4][i6] * d3;
                        double[] dArr7 = new double[16];
                        dArr7[z2 ? 1 : 0] = d4;
                        dArr7[c2] = d5;
                        dArr7[c3] = d6;
                        dArr7[3] = d7;
                        dArr7[4] = d8;
                        dArr7[5] = d9;
                        dArr7[6] = d10;
                        dArr7[7] = d11;
                        dArr7[8] = d12;
                        dArr7[9] = d13;
                        dArr7[10] = d14;
                        dArr7[11] = d15;
                        dArr7[12] = d16;
                        dArr7[13] = d17;
                        dArr7[14] = d18;
                        dArr7[15] = d19;
                        this.splines[i3][i5] = new BicubicFunction(computeSplineCoefficients(dArr7));
                        i5++;
                        c = c3;
                        c2 = 1;
                    }
                    i3++;
                    z = z2 ? 1 : 0;
                    c2 = 1;
                }
                return;
            }
        }
        throw new NoDataException();
    }

    @Override // org.apache.commons.math3.analysis.BivariateFunction
    public double value(double x, double y) throws OutOfRangeException {
        int i = searchIndex(x, this.xval);
        int j = searchIndex(y, this.yval);
        double xN = (x - this.xval[i]) / (this.xval[i + 1] - this.xval[i]);
        double yN = (y - this.yval[j]) / (this.yval[j + 1] - this.yval[j]);
        return this.splines[i][j].value(xN, yN);
    }

    public boolean isValidPoint(double x, double y) {
        return x >= this.xval[0] && x <= this.xval[this.xval.length - 1] && y >= this.yval[0] && y <= this.yval[this.yval.length - 1];
    }

    private int searchIndex(double c, double[] val) {
        int r = Arrays.binarySearch(val, c);
        if (r == -1 || r == (-val.length) - 1) {
            throw new OutOfRangeException(Double.valueOf(c), Double.valueOf(val[0]), Double.valueOf(val[val.length - 1]));
        }
        if (r < 0) {
            return (-r) - 2;
        }
        int last = val.length - 1;
        if (r == last) {
            return last - 1;
        }
        return r;
    }

    private double[] computeSplineCoefficients(double[] beta) {
        double[] a = new double[16];
        for (int i = 0; i < 16; i++) {
            double result = 0.0d;
            double[] row = AINV[i];
            for (int j = 0; j < 16; j++) {
                result += row[j] * beta[j];
            }
            a[i] = result;
        }
        return a;
    }
}
