package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.OutOfRangeException;

/* compiled from: BicubicSplineInterpolatingFunction.java */
/* loaded from: classes10.dex */
class BicubicSplineFunction implements BivariateFunction {
    private static final short N = 4;
    private final double[][] a;
    private final BivariateFunction partialDerivativeX;
    private final BivariateFunction partialDerivativeXX;
    private final BivariateFunction partialDerivativeXY;
    private final BivariateFunction partialDerivativeY;
    private final BivariateFunction partialDerivativeYY;

    BicubicSplineFunction(double[] coeff) {
        this(coeff, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BicubicSplineFunction(double[] coeff, boolean initializeDerivatives) {
        this.a = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 4, 4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.a[i][j] = coeff[(i * 4) + j];
            }
        }
        if (!initializeDerivatives) {
            this.partialDerivativeX = null;
            this.partialDerivativeY = null;
            this.partialDerivativeXX = null;
            this.partialDerivativeYY = null;
            this.partialDerivativeXY = null;
            return;
        }
        final double[][] aX = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 4, 4);
        final double[][] aY = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 4, 4);
        final double[][] aXX = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 4, 4);
        final double[][] aYY = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 4, 4);
        final double[][] aXY = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 4, 4);
        for (int i2 = 0; i2 < 4; i2++) {
            for (int j2 = 0; j2 < 4; j2++) {
                double c = this.a[i2][j2];
                aX[i2][j2] = i2 * c;
                aY[i2][j2] = j2 * c;
                aXX[i2][j2] = (i2 - 1) * aX[i2][j2];
                aYY[i2][j2] = (j2 - 1) * aY[i2][j2];
                aXY[i2][j2] = j2 * aX[i2][j2];
            }
        }
        this.partialDerivativeX = new BivariateFunction() { // from class: org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.1
            @Override // org.apache.commons.math3.analysis.BivariateFunction
            public double value(double x, double y) {
                double x2 = x * x;
                double[] pX = {0.0d, 1.0d, x, x2};
                double y2 = y * y;
                double y3 = y2 * y;
                double[] pY = {1.0d, y, y2, y3};
                return BicubicSplineFunction.this.apply(pX, pY, aX);
            }
        };
        this.partialDerivativeY = new BivariateFunction() { // from class: org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.2
            @Override // org.apache.commons.math3.analysis.BivariateFunction
            public double value(double x, double y) {
                double x2 = x * x;
                double x3 = x2 * x;
                double[] pX = {1.0d, x, x2, x3};
                double y2 = y * y;
                double[] pY = {0.0d, 1.0d, y, y2};
                return BicubicSplineFunction.this.apply(pX, pY, aY);
            }
        };
        this.partialDerivativeXX = new BivariateFunction() { // from class: org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.3
            @Override // org.apache.commons.math3.analysis.BivariateFunction
            public double value(double x, double y) {
                double[] pX = {0.0d, 0.0d, 1.0d, x};
                double y2 = y * y;
                double y3 = y2 * y;
                double[] pY = {1.0d, y, y2, y3};
                return BicubicSplineFunction.this.apply(pX, pY, aXX);
            }
        };
        this.partialDerivativeYY = new BivariateFunction() { // from class: org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.4
            @Override // org.apache.commons.math3.analysis.BivariateFunction
            public double value(double x, double y) {
                double x2 = x * x;
                double x3 = x2 * x;
                double[] pX = {1.0d, x, x2, x3};
                double[] pY = {0.0d, 0.0d, 1.0d, y};
                return BicubicSplineFunction.this.apply(pX, pY, aYY);
            }
        };
        this.partialDerivativeXY = new BivariateFunction() { // from class: org.apache.commons.math3.analysis.interpolation.BicubicSplineFunction.5
            @Override // org.apache.commons.math3.analysis.BivariateFunction
            public double value(double x, double y) {
                double x2 = x * x;
                double[] pX = {0.0d, 1.0d, x, x2};
                double y2 = y * y;
                double[] pY = {0.0d, 1.0d, y, y2};
                return BicubicSplineFunction.this.apply(pX, pY, aXY);
            }
        };
    }

    @Override // org.apache.commons.math3.analysis.BivariateFunction
    public double value(double x, double y) {
        if (x < 0.0d || x > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(x), 0, 1);
        }
        if (y < 0.0d || y > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(y), 0, 1);
        }
        double x2 = x * x;
        double x3 = x2 * x;
        double[] pX = {1.0d, x, x2, x3};
        double y2 = y * y;
        double y3 = y2 * y;
        double[] pY = {1.0d, y, y2, y3};
        return apply(pX, pY, this.a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double apply(double[] pX, double[] pY, double[][] coeff) {
        double result = 0.0d;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result += coeff[i][j] * pX[i] * pY[j];
            }
        }
        return result;
    }

    public BivariateFunction partialDerivativeX() {
        return this.partialDerivativeX;
    }

    public BivariateFunction partialDerivativeY() {
        return this.partialDerivativeY;
    }

    public BivariateFunction partialDerivativeXX() {
        return this.partialDerivativeXX;
    }

    public BivariateFunction partialDerivativeYY() {
        return this.partialDerivativeYY;
    }

    public BivariateFunction partialDerivativeXY() {
        return this.partialDerivativeXY;
    }
}
