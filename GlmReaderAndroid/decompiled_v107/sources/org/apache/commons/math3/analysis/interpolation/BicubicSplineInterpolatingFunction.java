package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
/* loaded from: classes10.dex */
public class BicubicSplineInterpolatingFunction implements BivariateFunction {
    private static final double[][] AINV = {new double[]{1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d}, new double[]{-3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d}, new double[]{9.0d, -9.0d, -9.0d, 9.0d, 6.0d, 3.0d, -6.0d, -3.0d, 6.0d, -6.0d, 3.0d, -3.0d, 4.0d, 2.0d, 2.0d, 1.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, -3.0d, -3.0d, 3.0d, 3.0d, -4.0d, 4.0d, -2.0d, 2.0d, -2.0d, -2.0d, -1.0d, -1.0d}, new double[]{2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, -4.0d, -2.0d, 4.0d, 2.0d, -3.0d, 3.0d, -3.0d, 3.0d, -2.0d, -1.0d, -2.0d, -1.0d}, new double[]{4.0d, -4.0d, -4.0d, 4.0d, 2.0d, 2.0d, -2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 1.0d, 1.0d, 1.0d, 1.0d}};
    private static final int NUM_COEFF = 16;
    private final BivariateFunction[][][] partialDerivatives;
    private final BicubicSplineFunction[][] splines;
    private final double[] xval;
    private final double[] yval;

    public BicubicSplineInterpolatingFunction(double[] x, double[] y, double[][] f, double[][] dFdX, double[][] dFdY, double[][] d2FdXdY) throws DimensionMismatchException, NoDataException, NonMonotonicSequenceException {
        this(x, y, f, dFdX, dFdY, d2FdXdY, false);
    }

    public BicubicSplineInterpolatingFunction(double[] dArr, double[] dArr2, double[][] dArr3, double[][] dArr4, double[][] dArr5, double[][] dArr6, boolean z) throws DimensionMismatchException, NoDataException, NonMonotonicSequenceException {
        int length = dArr.length;
        int length2 = dArr2.length;
        if (length == 0 || length2 == 0 || dArr3.length == 0 || dArr3[0].length == 0) {
            throw new NoDataException();
        }
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
        boolean z2 = true;
        this.splines = (BicubicSplineFunction[][]) Array.newInstance((Class<?>) BicubicSplineFunction.class, i, i2);
        int i3 = 0;
        while (true) {
            char c2 = c;
            if (i3 >= i) {
                boolean z3 = z2;
                if (z) {
                    int[] iArr = new int[3];
                    iArr[c2] = i2;
                    iArr[z3 ? 1 : 0] = i;
                    iArr[0] = 5;
                    this.partialDerivatives = (BivariateFunction[][][]) Array.newInstance((Class<?>) BivariateFunction.class, iArr);
                    for (int i4 = 0; i4 < i; i4++) {
                        for (int i5 = 0; i5 < i2; i5++) {
                            BicubicSplineFunction bicubicSplineFunction = this.splines[i4][i5];
                            this.partialDerivatives[0][i4][i5] = bicubicSplineFunction.partialDerivativeX();
                            this.partialDerivatives[z3 ? 1 : 0][i4][i5] = bicubicSplineFunction.partialDerivativeY();
                            this.partialDerivatives[c2][i4][i5] = bicubicSplineFunction.partialDerivativeXX();
                            this.partialDerivatives[3][i4][i5] = bicubicSplineFunction.partialDerivativeYY();
                            this.partialDerivatives[4][i4][i5] = bicubicSplineFunction.partialDerivativeXY();
                        }
                    }
                    return;
                }
                this.partialDerivatives = null;
                return;
            }
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
            int i6 = i3 + 1;
            boolean z4 = z2;
            for (int i7 = 0; i7 < i2; i7++) {
                int i8 = i7 + 1;
                double d = dArr3[i3][i7];
                double d2 = dArr3[i6][i7];
                double d3 = dArr3[i3][i8];
                double d4 = dArr3[i6][i8];
                double d5 = dArr4[i3][i7];
                double d6 = dArr4[i6][i7];
                double d7 = dArr4[i3][i8];
                double d8 = dArr4[i6][i8];
                double d9 = dArr5[i3][i7];
                double d10 = dArr5[i6][i7];
                double d11 = dArr5[i3][i8];
                double d12 = dArr5[i6][i8];
                double d13 = dArr6[i3][i7];
                double d14 = dArr6[i6][i7];
                double d15 = dArr6[i3][i8];
                double d16 = dArr6[i6][i8];
                double[] dArr7 = new double[16];
                dArr7[0] = d;
                dArr7[z4 ? 1 : 0] = d2;
                dArr7[c2] = d3;
                dArr7[3] = d4;
                dArr7[4] = d5;
                dArr7[5] = d6;
                dArr7[6] = d7;
                dArr7[7] = d8;
                dArr7[8] = d9;
                dArr7[9] = d10;
                dArr7[10] = d11;
                dArr7[11] = d12;
                dArr7[12] = d13;
                dArr7[13] = d14;
                dArr7[14] = d15;
                dArr7[15] = d16;
                this.splines[i3][i7] = new BicubicSplineFunction(computeSplineCoefficients(dArr7), z);
            }
            i3++;
            c = c2;
            z2 = z4 ? 1 : 0;
        }
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

    public double partialDerivativeX(double x, double y) throws OutOfRangeException {
        return partialDerivative(0, x, y);
    }

    public double partialDerivativeY(double x, double y) throws OutOfRangeException {
        return partialDerivative(1, x, y);
    }

    public double partialDerivativeXX(double x, double y) throws OutOfRangeException {
        return partialDerivative(2, x, y);
    }

    public double partialDerivativeYY(double x, double y) throws OutOfRangeException {
        return partialDerivative(3, x, y);
    }

    public double partialDerivativeXY(double x, double y) throws OutOfRangeException {
        return partialDerivative(4, x, y);
    }

    private double partialDerivative(int which, double x, double y) throws OutOfRangeException {
        int i = searchIndex(x, this.xval);
        int j = searchIndex(y, this.yval);
        double xN = (x - this.xval[i]) / (this.xval[i + 1] - this.xval[i]);
        double yN = (y - this.yval[j]) / (this.yval[j + 1] - this.yval[j]);
        return this.partialDerivatives[which][i][j].value(xN, yN);
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
