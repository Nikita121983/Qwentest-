package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class Sinc implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
    private static final double SHORTCUT = 0.006d;
    private final boolean normalized;

    public Sinc() {
        this(false);
    }

    public Sinc(boolean normalized) {
        this.normalized = normalized;
    }

    @Override // org.apache.commons.math3.analysis.UnivariateFunction
    public double value(double x) {
        double scaledX = this.normalized ? 3.141592653589793d * x : x;
        if (FastMath.abs(scaledX) <= SHORTCUT) {
            double scaledX2 = scaledX * scaledX;
            return (((scaledX2 - 20.0d) * scaledX2) + 120.0d) / 120.0d;
        }
        return FastMath.sin(scaledX) / scaledX;
    }

    @Override // org.apache.commons.math3.analysis.DifferentiableUnivariateFunction
    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    @Override // org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction
    public DerivativeStructure value(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        double d;
        int i;
        double d2;
        double d3;
        int i2;
        double d4 = 1.0d;
        double value = (this.normalized ? 3.141592653589793d : 1.0d) * derivativeStructure.getValue();
        double d5 = value * value;
        int i3 = 1;
        double[] dArr = new double[derivativeStructure.getOrder() + 1];
        if (FastMath.abs(value) <= SHORTCUT) {
            int i4 = 0;
            while (i4 < dArr.length) {
                int i5 = i4 / 2;
                if ((i4 & 1) == 0) {
                    d3 = d4;
                    i2 = i4;
                    dArr[i2] = ((i5 & 1) == 0 ? i3 : -1) * ((d4 / (i4 + 1)) - (((d3 / ((i4 * 2) + 6)) - (d5 / ((i4 * 24) + 120))) * d5));
                } else {
                    d3 = d4;
                    i2 = i4;
                    dArr[i2] = ((i5 & 1) == 0 ? -value : value) * ((d3 / (i2 + 2)) - (((d3 / ((i2 * 6) + 24)) - (d5 / ((i2 * 120) + 720))) * d5));
                }
                i4 = i2 + 1;
                d4 = d3;
                i3 = 1;
            }
            d = 3.141592653589793d;
        } else {
            d = 3.141592653589793d;
            double d6 = 1.0d / value;
            double cos = FastMath.cos(value);
            double sin = FastMath.sin(value);
            boolean z = false;
            dArr[0] = d6 * sin;
            double[] dArr2 = new double[dArr.length];
            dArr2[0] = 1.0d;
            double d7 = d6;
            int i6 = 1;
            while (i6 < dArr.length) {
                double d8 = 0.0d;
                double d9 = 0.0d;
                if ((i6 & 1) == 0) {
                    dArr2[i6] = 0.0d;
                    i = i6;
                } else {
                    dArr2[i6] = dArr2[i6 - 1];
                    d9 = dArr2[i6];
                    i = i6 - 1;
                }
                boolean z2 = z;
                int i7 = i;
                while (true) {
                    d2 = cos;
                    if (i7 > 1) {
                        dArr2[i7] = ((i7 - i6) * dArr2[i7]) - dArr2[i7 - 1];
                        d8 = (d8 * d5) + dArr2[i7];
                        dArr2[i7 - 1] = (((i7 - 1) - i6) * dArr2[i7 - 1]) + dArr2[i7 - 2];
                        d9 = (d9 * d5) + dArr2[i7 - 1];
                        i7 -= 2;
                        cos = d2;
                    }
                }
                dArr2[z2 ? 1 : 0] = (-i6) * dArr2[z2 ? 1 : 0];
                d7 *= d6;
                dArr[i6] = ((((d8 * d5) + dArr2[z2 ? 1 : 0]) * sin) + (d9 * value * d2)) * d7;
                i6++;
                z = z2 ? 1 : 0;
                cos = d2;
            }
        }
        if (this.normalized) {
            double d10 = 3.141592653589793d;
            for (int i8 = 1; i8 < dArr.length; i8++) {
                dArr[i8] = dArr[i8] * d10;
                d10 *= d;
            }
        }
        return derivativeStructure.compose(dArr);
    }
}
