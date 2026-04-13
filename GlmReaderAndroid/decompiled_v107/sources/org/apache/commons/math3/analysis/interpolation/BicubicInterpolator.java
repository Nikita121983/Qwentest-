package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class BicubicInterpolator implements BivariateGridInterpolator {
    @Override // org.apache.commons.math3.analysis.interpolation.BivariateGridInterpolator
    public BicubicInterpolatingFunction interpolate(final double[] xval, final double[] yval, double[][] fval) throws NoDataException, DimensionMismatchException, NonMonotonicSequenceException, NumberIsTooSmallException {
        double[][] d2FdXdY;
        if (xval.length == 0 || yval.length == 0 || fval.length == 0) {
            throw new NoDataException();
        }
        if (xval.length != fval.length) {
            throw new DimensionMismatchException(xval.length, fval.length);
        }
        MathArrays.checkOrder(xval);
        MathArrays.checkOrder(yval);
        int xLen = xval.length;
        int yLen = yval.length;
        double[][] dFdX = (double[][]) Array.newInstance((Class<?>) Double.TYPE, xLen, yLen);
        double[][] dFdY = (double[][]) Array.newInstance((Class<?>) Double.TYPE, xLen, yLen);
        double[][] d2FdXdY2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, xLen, yLen);
        int i = 1;
        while (i < xLen - 1) {
            int nI = i + 1;
            int pI = i - 1;
            double nX = xval[nI];
            double pX = xval[pI];
            double deltaX = nX - pX;
            int j = 1;
            while (true) {
                d2FdXdY = d2FdXdY2;
                if (j < yLen - 1) {
                    int nJ = j + 1;
                    int pJ = j - 1;
                    double nY = yval[nJ];
                    double pY = yval[pJ];
                    double deltaY = nY - pY;
                    dFdX[i][j] = (fval[nI][j] - fval[pI][j]) / deltaX;
                    dFdY[i][j] = (fval[i][nJ] - fval[i][pJ]) / deltaY;
                    double deltaXY = deltaX * deltaY;
                    d2FdXdY[i][j] = (((fval[nI][nJ] - fval[nI][pJ]) - fval[pI][nJ]) + fval[pI][pJ]) / deltaXY;
                    j++;
                    d2FdXdY2 = d2FdXdY;
                }
            }
            i++;
            d2FdXdY2 = d2FdXdY;
        }
        return new BicubicInterpolatingFunction(xval, yval, fval, dFdX, dFdY, d2FdXdY2) { // from class: org.apache.commons.math3.analysis.interpolation.BicubicInterpolator.1
            @Override // org.apache.commons.math3.analysis.interpolation.BicubicInterpolatingFunction
            public boolean isValidPoint(double x, double y) {
                return x >= xval[1] && x <= xval[xval.length + (-2)] && y >= yval[1] && y <= yval[yval.length + (-2)];
            }
        };
    }
}
