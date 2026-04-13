package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.TrivariateFunction;
import org.apache.commons.math3.exception.OutOfRangeException;

/* compiled from: TricubicInterpolatingFunction.java */
/* loaded from: classes10.dex */
class TricubicFunction implements TrivariateFunction {
    private static final short N = 4;
    private final double[][][] a = (double[][][]) Array.newInstance((Class<?>) Double.TYPE, 4, 4, 4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public TricubicFunction(double[] aV) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    this.a[i][j][k] = aV[(((k * 4) + j) * 4) + i];
                }
            }
        }
    }

    @Override // org.apache.commons.math3.analysis.TrivariateFunction
    public double value(double x, double y, double z) throws OutOfRangeException {
        if (x < 0.0d || x > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(x), 0, 1);
        }
        if (y < 0.0d || y > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(y), 0, 1);
        }
        if (z < 0.0d || z > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(z), 0, 1);
        }
        double x2 = x * x;
        double x3 = x2 * x;
        int i = 4;
        double[] pX = {1.0d, x, x2, x3};
        double y2 = y * y;
        double y3 = y2 * y;
        double[] pY = {1.0d, y, y2, y3};
        double z2 = z * z;
        double z3 = z2 * z;
        double[] pZ = {1.0d, z, z2, z3};
        double result = 0.0d;
        int i2 = 0;
        while (i2 < i) {
            int j = 0;
            while (j < i) {
                double x22 = x2;
                int k = 0;
                while (k < i) {
                    result += this.a[i2][j][k] * pX[i2] * pY[j] * pZ[k];
                    k++;
                    i = 4;
                }
                j++;
                x2 = x22;
                i = 4;
            }
            i2++;
            x2 = x2;
            i = 4;
        }
        return result;
    }
}
