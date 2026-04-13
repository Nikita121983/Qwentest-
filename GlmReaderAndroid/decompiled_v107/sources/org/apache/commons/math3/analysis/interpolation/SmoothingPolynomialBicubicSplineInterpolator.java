package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.fitting.PolynomialFitter;
import org.apache.commons.math3.optim.SimpleVectorValueChecker;
import org.apache.commons.math3.optim.nonlinear.vector.jacobian.GaussNewtonOptimizer;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Precision;

@Deprecated
/* loaded from: classes10.dex */
public class SmoothingPolynomialBicubicSplineInterpolator extends BicubicSplineInterpolator {
    private final int xDegree;
    private final PolynomialFitter xFitter;
    private final int yDegree;
    private final PolynomialFitter yFitter;

    public SmoothingPolynomialBicubicSplineInterpolator() {
        this(3);
    }

    public SmoothingPolynomialBicubicSplineInterpolator(int degree) throws NotPositiveException {
        this(degree, degree);
    }

    public SmoothingPolynomialBicubicSplineInterpolator(int xDegree, int yDegree) throws NotPositiveException {
        if (xDegree < 0) {
            throw new NotPositiveException(Integer.valueOf(xDegree));
        }
        if (yDegree < 0) {
            throw new NotPositiveException(Integer.valueOf(yDegree));
        }
        this.xDegree = xDegree;
        this.yDegree = yDegree;
        SimpleVectorValueChecker checker = new SimpleVectorValueChecker(Precision.EPSILON * 100.0d, Precision.SAFE_MIN * 100.0d);
        this.xFitter = new PolynomialFitter(new GaussNewtonOptimizer(false, checker));
        this.yFitter = new PolynomialFitter(new GaussNewtonOptimizer(false, checker));
    }

    @Override // org.apache.commons.math3.analysis.interpolation.BicubicSplineInterpolator, org.apache.commons.math3.analysis.interpolation.BivariateGridInterpolator
    public BicubicSplineInterpolatingFunction interpolate(double[] xval, double[] yval, double[][] fval) throws NoDataException, NullArgumentException, DimensionMismatchException, NonMonotonicSequenceException {
        int i;
        if (xval.length == 0 || yval.length == 0 || fval.length == 0) {
            throw new NoDataException();
        }
        if (xval.length != fval.length) {
            throw new DimensionMismatchException(xval.length, fval.length);
        }
        int xLen = xval.length;
        int yLen = yval.length;
        for (int i2 = 0; i2 < xLen; i2++) {
            if (fval[i2].length != yLen) {
                throw new DimensionMismatchException(fval[i2].length, yLen);
            }
        }
        MathArrays.checkOrder(xval);
        MathArrays.checkOrder(yval);
        PolynomialFunction[] yPolyX = new PolynomialFunction[yLen];
        int j = 0;
        while (true) {
            i = 1;
            if (j >= yLen) {
                break;
            }
            this.xFitter.clearObservations();
            for (int i3 = 0; i3 < xLen; i3++) {
                this.xFitter.addObservedPoint(1.0d, xval[i3], fval[i3][j]);
            }
            yPolyX[j] = new PolynomialFunction(this.xFitter.fit(new double[this.xDegree + 1]));
            j++;
        }
        double[][] fval_1 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, xLen, yLen);
        for (int j2 = 0; j2 < yLen; j2++) {
            PolynomialFunction f = yPolyX[j2];
            int i4 = 0;
            while (i4 < xLen) {
                fval_1[i4][j2] = f.value(xval[i4]);
                i4++;
                i = i;
                fval_1 = fval_1;
            }
        }
        int i5 = i;
        double[][] fval_12 = fval_1;
        PolynomialFunction[] xPolyY = new PolynomialFunction[xLen];
        for (int i6 = 0; i6 < xLen; i6++) {
            this.yFitter.clearObservations();
            for (int j3 = 0; j3 < yLen; j3++) {
                this.yFitter.addObservedPoint(1.0d, yval[j3], fval_12[i6][j3]);
            }
            xPolyY[i6] = new PolynomialFunction(this.yFitter.fit(new double[this.yDegree + i5]));
        }
        int[] iArr = new int[2];
        iArr[i5] = yLen;
        iArr[0] = xLen;
        double[][] fval_2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr);
        for (int i7 = 0; i7 < xLen; i7++) {
            PolynomialFunction f2 = xPolyY[i7];
            for (int j4 = 0; j4 < yLen; j4++) {
                fval_2[i7][j4] = f2.value(yval[j4]);
            }
        }
        return super.interpolate(xval, yval, fval_2);
    }
}
