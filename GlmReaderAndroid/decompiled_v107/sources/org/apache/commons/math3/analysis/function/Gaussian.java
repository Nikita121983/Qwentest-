package org.apache.commons.math3.analysis.function;

import java.util.Arrays;
import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class Gaussian implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
    private final double i2s2;
    private final double is;
    private final double mean;
    private final double norm;

    public Gaussian(double norm, double mean, double sigma) throws NotStrictlyPositiveException {
        if (sigma <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(sigma));
        }
        this.norm = norm;
        this.mean = mean;
        this.is = 1.0d / sigma;
        this.i2s2 = this.is * 0.5d * this.is;
    }

    public Gaussian(double mean, double sigma) throws NotStrictlyPositiveException {
        this(1.0d / (FastMath.sqrt(6.283185307179586d) * sigma), mean, sigma);
    }

    public Gaussian() {
        this(0.0d, 1.0d);
    }

    @Override // org.apache.commons.math3.analysis.UnivariateFunction
    public double value(double x) {
        return value(x - this.mean, this.norm, this.i2s2);
    }

    @Override // org.apache.commons.math3.analysis.DifferentiableUnivariateFunction
    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    /* loaded from: classes10.dex */
    public static class Parametric implements ParametricUnivariateFunction {
        @Override // org.apache.commons.math3.analysis.ParametricUnivariateFunction
        public double value(double x, double... param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            validateParameters(param);
            double diff = x - param[1];
            double i2s2 = 1.0d / ((param[2] * 2.0d) * param[2]);
            return Gaussian.value(diff, param[0], i2s2);
        }

        @Override // org.apache.commons.math3.analysis.ParametricUnivariateFunction
        public double[] gradient(double x, double... param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            validateParameters(param);
            double norm = param[0];
            double diff = x - param[1];
            double sigma = param[2];
            double i2s2 = 1.0d / ((sigma * 2.0d) * sigma);
            double n = Gaussian.value(diff, 1.0d, i2s2);
            double m = norm * n * 2.0d * i2s2 * diff;
            double s = (m * diff) / sigma;
            return new double[]{n, m, s};
        }

        private void validateParameters(double[] param) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            if (param == null) {
                throw new NullArgumentException();
            }
            if (param.length != 3) {
                throw new DimensionMismatchException(param.length, 3);
            }
            if (param[2] <= 0.0d) {
                throw new NotStrictlyPositiveException(Double.valueOf(param[2]));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double value(double xMinusMean, double norm, double i2s2) {
        return FastMath.exp((-xMinusMean) * xMinusMean * i2s2) * norm;
    }

    @Override // org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction
    public DerivativeStructure value(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        int i;
        double[] dArr;
        boolean z;
        double value = this.is * (derivativeStructure.getValue() - this.mean);
        int i2 = 1;
        double[] dArr2 = new double[derivativeStructure.getOrder() + 1];
        double[] dArr3 = new double[dArr2.length];
        boolean z2 = false;
        dArr3[0] = 1.0d;
        double d = value * value;
        double exp = this.norm * FastMath.exp((-0.5d) * d);
        if (exp <= Precision.SAFE_MIN) {
            Arrays.fill(dArr2, 0.0d);
        } else {
            dArr2[0] = exp;
            int i3 = 1;
            while (i3 < dArr2.length) {
                double d2 = 0.0d;
                dArr3[i3] = -dArr3[i3 - 1];
                int i4 = i3;
                while (i4 >= 0) {
                    d2 = (d2 * d) + dArr3[i4];
                    if (i4 > 2) {
                        z = z2;
                        i = i2;
                        dArr = dArr3;
                        dArr[i4 - 2] = ((i4 - 1) * dArr[i4 - 1]) - dArr[i4 - 3];
                    } else {
                        i = i2;
                        dArr = dArr3;
                        z = z2;
                        if (i4 == 2) {
                            dArr[z ? 1 : 0] = dArr[i];
                        }
                    }
                    i4 -= 2;
                    z2 = z;
                    dArr3 = dArr;
                    i2 = i;
                }
                int i5 = i2;
                double[] dArr4 = dArr3;
                boolean z3 = z2;
                if ((i3 & 1) == i5) {
                    d2 *= value;
                }
                exp *= this.is;
                dArr2[i3] = exp * d2;
                i3++;
                i2 = i5;
                z2 = z3;
                dArr3 = dArr4;
            }
        }
        return derivativeStructure.compose(dArr2);
    }
}
