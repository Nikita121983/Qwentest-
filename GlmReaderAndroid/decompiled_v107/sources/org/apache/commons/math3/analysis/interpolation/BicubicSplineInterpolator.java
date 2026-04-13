package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
/* loaded from: classes10.dex */
public class BicubicSplineInterpolator implements BivariateGridInterpolator {
    private final boolean initializeDerivatives;

    public BicubicSplineInterpolator() {
        this(false);
    }

    public BicubicSplineInterpolator(boolean initializeDerivatives) {
        this.initializeDerivatives = initializeDerivatives;
    }

    @Override // org.apache.commons.math3.analysis.interpolation.BivariateGridInterpolator
    public BicubicSplineInterpolatingFunction interpolate(double[] dArr, double[] dArr2, double[][] dArr3) throws NoDataException, DimensionMismatchException, NonMonotonicSequenceException, NumberIsTooSmallException {
        if (dArr.length == 0 || dArr2.length == 0 || dArr3.length == 0) {
            throw new NoDataException();
        }
        if (dArr.length != dArr3.length) {
            throw new DimensionMismatchException(dArr.length, dArr3.length);
        }
        MathArrays.checkOrder(dArr);
        MathArrays.checkOrder(dArr2);
        int length = dArr.length;
        int length2 = dArr2.length;
        boolean z = true;
        boolean z2 = false;
        double[][] dArr4 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length2, length);
        for (int i = 0; i < length; i++) {
            if (dArr3[i].length != length2) {
                throw new DimensionMismatchException(dArr3[i].length, length2);
            }
            for (int i2 = 0; i2 < length2; i2++) {
                dArr4[i2][i] = dArr3[i][i2];
            }
        }
        SplineInterpolator splineInterpolator = new SplineInterpolator();
        PolynomialSplineFunction[] polynomialSplineFunctionArr = new PolynomialSplineFunction[length2];
        for (int i3 = 0; i3 < length2; i3++) {
            polynomialSplineFunctionArr[i3] = splineInterpolator.interpolate(dArr, dArr4[i3]);
        }
        PolynomialSplineFunction[] polynomialSplineFunctionArr2 = new PolynomialSplineFunction[length];
        for (int i4 = 0; i4 < length; i4++) {
            polynomialSplineFunctionArr2[i4] = splineInterpolator.interpolate(dArr2, dArr3[i4]);
        }
        double[][] dArr5 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, length2);
        int i5 = 0;
        while (i5 < length2) {
            UnivariateFunction derivative = polynomialSplineFunctionArr[i5].derivative();
            boolean z3 = z;
            int i6 = 0;
            while (i6 < length) {
                int i7 = i5;
                dArr5[i6][i7] = derivative.value(dArr[i6]);
                i6++;
                z2 = z2;
                i5 = i7;
            }
            i5++;
            z = z3;
        }
        boolean z4 = z;
        boolean z5 = z2;
        int[] iArr = new int[2];
        iArr[z4 ? 1 : 0] = length2;
        iArr[z5 ? 1 : 0] = length;
        double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr);
        for (int i8 = 0; i8 < length; i8++) {
            UnivariateFunction derivative2 = polynomialSplineFunctionArr2[i8].derivative();
            for (int i9 = 0; i9 < length2; i9++) {
                dArr6[i8][i9] = derivative2.value(dArr2[i9]);
            }
        }
        int[] iArr2 = new int[2];
        iArr2[z4 ? 1 : 0] = length2;
        iArr2[z5 ? 1 : 0] = length;
        double[][] dArr7 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr2);
        for (int i10 = 0; i10 < length; i10++) {
            int nextIndex = nextIndex(i10, length);
            int previousIndex = previousIndex(i10);
            for (int i11 = 0; i11 < length2; i11++) {
                int nextIndex2 = nextIndex(i11, length2);
                int previousIndex2 = previousIndex(i11);
                dArr7[i10][i11] = (((dArr3[nextIndex][nextIndex2] - dArr3[nextIndex][previousIndex2]) - dArr3[previousIndex][nextIndex2]) + dArr3[previousIndex][previousIndex2]) / ((dArr[nextIndex] - dArr[previousIndex]) * (dArr2[nextIndex2] - dArr2[previousIndex2]));
            }
        }
        return new BicubicSplineInterpolatingFunction(dArr, dArr2, dArr3, dArr5, dArr6, dArr7, this.initializeDerivatives);
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
