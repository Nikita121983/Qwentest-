package org.apache.commons.math3.analysis.polynomials;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class PolynomialFunctionLagrangeForm implements UnivariateFunction {
    private double[] coefficients;
    private boolean coefficientsComputed;
    private final double[] x;
    private final double[] y;

    public PolynomialFunctionLagrangeForm(double[] x, double[] y) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        this.x = new double[x.length];
        this.y = new double[y.length];
        System.arraycopy(x, 0, this.x, 0, x.length);
        System.arraycopy(y, 0, this.y, 0, y.length);
        this.coefficientsComputed = false;
        if (!verifyInterpolationArray(x, y, false)) {
            MathArrays.sortInPlace(this.x, this.y);
            verifyInterpolationArray(this.x, this.y, true);
        }
    }

    @Override // org.apache.commons.math3.analysis.UnivariateFunction
    public double value(double z) {
        return evaluateInternal(this.x, this.y, z);
    }

    public int degree() {
        return this.x.length - 1;
    }

    public double[] getInterpolatingPoints() {
        double[] out = new double[this.x.length];
        System.arraycopy(this.x, 0, out, 0, this.x.length);
        return out;
    }

    public double[] getInterpolatingValues() {
        double[] out = new double[this.y.length];
        System.arraycopy(this.y, 0, out, 0, this.y.length);
        return out;
    }

    public double[] getCoefficients() {
        if (!this.coefficientsComputed) {
            computeCoefficients();
        }
        double[] out = new double[this.coefficients.length];
        System.arraycopy(this.coefficients, 0, out, 0, this.coefficients.length);
        return out;
    }

    public static double evaluate(double[] x, double[] y, double z) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        if (verifyInterpolationArray(x, y, false)) {
            return evaluateInternal(x, y, z);
        }
        double[] xNew = new double[x.length];
        double[] yNew = new double[y.length];
        System.arraycopy(x, 0, xNew, 0, x.length);
        System.arraycopy(y, 0, yNew, 0, y.length);
        MathArrays.sortInPlace(xNew, yNew);
        verifyInterpolationArray(xNew, yNew, true);
        return evaluateInternal(xNew, yNew, z);
    }

    private static double evaluateInternal(double[] x, double[] y, double z) {
        double d;
        int nearest = 0;
        int n = x.length;
        double[] c = new double[n];
        double[] d2 = new double[n];
        double min_dist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n; i++) {
            c[i] = y[i];
            d2[i] = y[i];
            double dist = FastMath.abs(z - x[i]);
            if (dist < min_dist) {
                nearest = i;
                min_dist = dist;
            }
        }
        double value = y[nearest];
        for (int i2 = 1; i2 < n; i2++) {
            for (int j = 0; j < n - i2; j++) {
                double tc = x[j] - z;
                double td = x[i2 + j] - z;
                double divider = x[j] - x[i2 + j];
                double w = (c[j + 1] - d2[j]) / divider;
                c[j] = tc * w;
                d2[j] = td * w;
            }
            if (nearest < ((n - i2) + 1) * 0.5d) {
                d = c[nearest];
            } else {
                nearest--;
                d = d2[nearest];
            }
            value += d;
        }
        return value;
    }

    protected void computeCoefficients() {
        int n = degree() + 1;
        this.coefficients = new double[n];
        for (int i = 0; i < n; i++) {
            this.coefficients[i] = 0.0d;
        }
        int i2 = n + 1;
        double[] c = new double[i2];
        c[0] = 1.0d;
        for (int i3 = 0; i3 < n; i3++) {
            for (int j = i3; j > 0; j--) {
                c[j] = c[j - 1] - (c[j] * this.x[i3]);
            }
            c[0] = c[0] * (-this.x[i3]);
            c[i3 + 1] = 1.0d;
        }
        double[] tc = new double[n];
        for (int i4 = 0; i4 < n; i4++) {
            double d = 1.0d;
            for (int j2 = 0; j2 < n; j2++) {
                if (i4 != j2) {
                    d *= this.x[i4] - this.x[j2];
                }
            }
            double t = this.y[i4] / d;
            tc[n - 1] = c[n];
            double[] dArr = this.coefficients;
            int i5 = n - 1;
            dArr[i5] = dArr[i5] + (tc[n - 1] * t);
            for (int j3 = n - 2; j3 >= 0; j3--) {
                tc[j3] = c[j3 + 1] + (tc[j3 + 1] * this.x[i4]);
                double[] dArr2 = this.coefficients;
                dArr2[j3] = dArr2[j3] + (tc[j3] * t);
            }
        }
        this.coefficientsComputed = true;
    }

    public static boolean verifyInterpolationArray(double[] x, double[] y, boolean abort) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        if (x.length != y.length) {
            throw new DimensionMismatchException(x.length, y.length);
        }
        if (x.length < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.WRONG_NUMBER_OF_POINTS, 2, Integer.valueOf(x.length), true);
        }
        return MathArrays.checkOrder(x, MathArrays.OrderDirection.INCREASING, true, abort);
    }
}
