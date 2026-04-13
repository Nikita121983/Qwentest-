package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
/* loaded from: classes10.dex */
public class TricubicSplineInterpolator implements TrivariateGridInterpolator {
    @Override // org.apache.commons.math3.analysis.interpolation.TrivariateGridInterpolator
    public TricubicSplineInterpolatingFunction interpolate(double[] dArr, double[] dArr2, double[] dArr3, double[][][] dArr4) throws NoDataException, NumberIsTooSmallException, DimensionMismatchException, NonMonotonicSequenceException {
        if (dArr.length == 0 || dArr2.length == 0 || dArr3.length == 0 || dArr4.length == 0) {
            throw new NoDataException();
        }
        if (dArr.length != dArr4.length) {
            throw new DimensionMismatchException(dArr.length, dArr4.length);
        }
        MathArrays.checkOrder(dArr);
        MathArrays.checkOrder(dArr2);
        MathArrays.checkOrder(dArr3);
        int length = dArr.length;
        int length2 = dArr2.length;
        int length3 = dArr3.length;
        char c = 2;
        boolean z = true;
        double[][][] dArr5 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, length3, length, length2);
        double[][][] dArr6 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, length2, length3, length);
        for (int i = 0; i < length; i++) {
            if (dArr4[i].length != length2) {
                throw new DimensionMismatchException(dArr4[i].length, length2);
            }
            for (int i2 = 0; i2 < length2; i2++) {
                if (dArr4[i][i2].length != length3) {
                    throw new DimensionMismatchException(dArr4[i][i2].length, length3);
                }
                for (int i3 = 0; i3 < length3; i3++) {
                    double d = dArr4[i][i2][i3];
                    dArr5[i3][i][i2] = d;
                    dArr6[i2][i3][i] = d;
                }
            }
        }
        BicubicSplineInterpolator bicubicSplineInterpolator = new BicubicSplineInterpolator(true);
        BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr = new BicubicSplineInterpolatingFunction[length];
        for (int i4 = 0; i4 < length; i4++) {
            bicubicSplineInterpolatingFunctionArr[i4] = bicubicSplineInterpolator.interpolate(dArr2, dArr3, dArr4[i4]);
        }
        BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr2 = new BicubicSplineInterpolatingFunction[length2];
        int i5 = 0;
        while (i5 < length2) {
            bicubicSplineInterpolatingFunctionArr2[i5] = bicubicSplineInterpolator.interpolate(dArr3, dArr, dArr6[i5]);
            i5++;
            c = c;
        }
        char c2 = c;
        BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr3 = new BicubicSplineInterpolatingFunction[length3];
        int i6 = 0;
        while (i6 < length3) {
            bicubicSplineInterpolatingFunctionArr3[i6] = bicubicSplineInterpolator.interpolate(dArr, dArr2, dArr5[i6]);
            i6++;
            z = z;
        }
        boolean z2 = z;
        int[] iArr = new int[3];
        iArr[c2] = length3;
        iArr[z2 ? 1 : 0] = length2;
        iArr[0] = length;
        double[][][] dArr7 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, iArr);
        int[] iArr2 = new int[3];
        iArr2[c2] = length3;
        iArr2[z2 ? 1 : 0] = length2;
        iArr2[0] = length;
        double[][][] dArr8 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, iArr2);
        int[] iArr3 = new int[3];
        iArr3[c2] = length3;
        iArr3[z2 ? 1 : 0] = length2;
        iArr3[0] = length;
        double[][][] dArr9 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, iArr3);
        int i7 = 0;
        while (i7 < length3) {
            double[][][] dArr10 = dArr9;
            BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction = bicubicSplineInterpolatingFunctionArr3[i7];
            int i8 = 0;
            while (i8 < length) {
                int i9 = i8;
                double d2 = dArr[i9];
                int i10 = 0;
                while (i10 < length2) {
                    int i11 = i10;
                    double d3 = dArr2[i11];
                    dArr7[i9][i11][i7] = bicubicSplineInterpolatingFunction.partialDerivativeX(d2, d3);
                    dArr8[i9][i11][i7] = bicubicSplineInterpolatingFunction.partialDerivativeY(d2, d3);
                    dArr10[i9][i11][i7] = bicubicSplineInterpolatingFunction.partialDerivativeXY(d2, d3);
                    i10 = i11 + 1;
                }
                i8 = i9 + 1;
            }
            i7++;
            dArr9 = dArr10;
        }
        double[][][] dArr11 = dArr9;
        int[] iArr4 = new int[3];
        iArr4[c2] = length3;
        iArr4[z2 ? 1 : 0] = length2;
        iArr4[0] = length;
        double[][][] dArr12 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, iArr4);
        int[] iArr5 = new int[3];
        iArr5[c2] = length3;
        iArr5[z2 ? 1 : 0] = length2;
        iArr5[0] = length;
        double[][][] dArr13 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, iArr5);
        int i12 = 0;
        while (i12 < length) {
            BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction2 = bicubicSplineInterpolatingFunctionArr[i12];
            int i13 = 0;
            while (i13 < length2) {
                double[][][] dArr14 = dArr13;
                double[][][] dArr15 = dArr12;
                double d4 = dArr2[i13];
                int i14 = i12;
                int i15 = 0;
                while (i15 < length3) {
                    int i16 = i13;
                    double d5 = dArr3[i15];
                    dArr15[i14][i16][i15] = bicubicSplineInterpolatingFunction2.partialDerivativeY(d4, d5);
                    dArr14[i14][i16][i15] = bicubicSplineInterpolatingFunction2.partialDerivativeXY(d4, d5);
                    i15++;
                    bicubicSplineInterpolator = bicubicSplineInterpolator;
                    i13 = i16;
                }
                i13++;
                dArr12 = dArr15;
                dArr13 = dArr14;
                i12 = i14;
            }
            i12++;
        }
        double[][][] dArr16 = dArr13;
        double[][][] dArr17 = dArr12;
        int[] iArr6 = new int[3];
        iArr6[c2] = length3;
        iArr6[z2 ? 1 : 0] = length2;
        iArr6[0] = length;
        double[][][] dArr18 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, iArr6);
        int i17 = 0;
        while (i17 < length2) {
            BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction3 = bicubicSplineInterpolatingFunctionArr2[i17];
            int i18 = 0;
            while (i18 < length3) {
                double d6 = dArr3[i18];
                double[][][] dArr19 = dArr18;
                int i19 = 0;
                while (i19 < length) {
                    int i20 = i19;
                    int i21 = i17;
                    dArr19[i20][i21][i18] = bicubicSplineInterpolatingFunction3.partialDerivativeXY(d6, dArr[i20]);
                    i19 = i20 + 1;
                    i17 = i21;
                }
                i18++;
                dArr18 = dArr19;
            }
            i17++;
        }
        double[][][] dArr20 = dArr18;
        int[] iArr7 = new int[3];
        iArr7[c2] = length3;
        iArr7[z2 ? 1 : 0] = length2;
        iArr7[0] = length;
        double[][][] dArr21 = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, iArr7);
        for (int i22 = 0; i22 < length; i22++) {
            int nextIndex = nextIndex(i22, length);
            int previousIndex = previousIndex(i22);
            int i23 = 0;
            while (i23 < length2) {
                int nextIndex2 = nextIndex(i23, length2);
                int previousIndex2 = previousIndex(i23);
                double[][][] dArr22 = dArr21;
                for (int i24 = 0; i24 < length3; i24++) {
                    int nextIndex3 = nextIndex(i24, length3);
                    int previousIndex3 = previousIndex(i24);
                    dArr22[i22][i23][i24] = (((((((dArr4[nextIndex][nextIndex2][nextIndex3] - dArr4[nextIndex][previousIndex2][nextIndex3]) - dArr4[previousIndex][nextIndex2][nextIndex3]) + dArr4[previousIndex][previousIndex2][nextIndex3]) - dArr4[nextIndex][nextIndex2][previousIndex3]) + dArr4[nextIndex][previousIndex2][previousIndex3]) + dArr4[previousIndex][nextIndex2][previousIndex3]) - dArr4[previousIndex][previousIndex2][previousIndex3]) / (((dArr[nextIndex] - dArr[previousIndex]) * (dArr2[nextIndex2] - dArr2[previousIndex2])) * (dArr3[nextIndex3] - dArr3[previousIndex3]));
                }
                i23++;
                dArr21 = dArr22;
            }
        }
        return new TricubicSplineInterpolatingFunction(dArr, dArr2, dArr3, dArr4, dArr7, dArr8, dArr17, dArr11, dArr20, dArr16, dArr21);
    }

    private int nextIndex(int i, int max) {
        int index = i + 1;
        return index < max ? index : index - 1;
    }

    private int previousIndex(int i) {
        int index = i - 1;
        if (index >= 0) {
            return index;
        }
        return 0;
    }
}
