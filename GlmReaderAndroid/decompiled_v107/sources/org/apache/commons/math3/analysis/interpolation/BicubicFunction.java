package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

/* compiled from: BicubicInterpolatingFunction.java */
/* loaded from: classes10.dex */
class BicubicFunction implements BivariateFunction {
    private static final short N = 4;
    private final double[][] a = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 4, 4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public BicubicFunction(double[] coeff) {
        for (int j = 0; j < 4; j++) {
            double[] aJ = this.a[j];
            for (int i = 0; i < 4; i++) {
                aJ[i] = coeff[(i * 4) + j];
            }
        }
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

    private double apply(double[] pX, double[] pY, double[][] coeff) {
        double result = 0.0d;
        for (int i = 0; i < 4; i++) {
            double r = MathArrays.linearCombination(coeff[i], pY);
            result += pX[i] * r;
        }
        return result;
    }
}
