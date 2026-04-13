package org.apache.commons.math3.analysis.function;

import java.util.Arrays;
import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class Sigmoid implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
    private final double hi;
    private final double lo;

    public Sigmoid() {
        this(0.0d, 1.0d);
    }

    public Sigmoid(double lo, double hi) {
        this.lo = lo;
        this.hi = hi;
    }

    @Override // org.apache.commons.math3.analysis.DifferentiableUnivariateFunction
    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    @Override // org.apache.commons.math3.analysis.UnivariateFunction
    public double value(double x) {
        return value(x, this.lo, this.hi);
    }

    /* loaded from: classes10.dex */
    public static class Parametric implements ParametricUnivariateFunction {
        @Override // org.apache.commons.math3.analysis.ParametricUnivariateFunction
        public double value(double x, double... param) throws NullArgumentException, DimensionMismatchException {
            validateParameters(param);
            return Sigmoid.value(x, param[0], param[1]);
        }

        @Override // org.apache.commons.math3.analysis.ParametricUnivariateFunction
        public double[] gradient(double x, double... param) throws NullArgumentException, DimensionMismatchException {
            validateParameters(param);
            double invExp1 = 1.0d / (FastMath.exp(-x) + 1.0d);
            return new double[]{1.0d - invExp1, invExp1};
        }

        private void validateParameters(double[] param) throws NullArgumentException, DimensionMismatchException {
            if (param == null) {
                throw new NullArgumentException();
            }
            if (param.length != 2) {
                throw new DimensionMismatchException(param.length, 2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double value(double x, double lo, double hi) {
        return ((hi - lo) / (FastMath.exp(-x) + 1.0d)) + lo;
    }

    @Override // org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction
    public DerivativeStructure value(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        double d;
        boolean z;
        int i = 1;
        double[] dArr = new double[derivativeStructure.getOrder() + 1];
        double exp = FastMath.exp(-derivativeStructure.getValue());
        boolean z2 = false;
        if (Double.isInfinite(exp)) {
            dArr[0] = this.lo;
            Arrays.fill(dArr, 1, dArr.length, 0.0d);
        } else {
            double[] dArr2 = new double[dArr.length];
            double d2 = 1.0d;
            double d3 = 1.0d / (exp + 1.0d);
            double d4 = this.hi - this.lo;
            int i2 = 0;
            while (i2 < dArr.length) {
                double d5 = 0.0d;
                dArr2[i2] = d2;
                int i3 = i2;
                while (i3 >= 0) {
                    d5 = (d5 * exp) + dArr2[i3];
                    if (i3 > i) {
                        z = z2;
                        d = exp;
                        dArr2[i3 - 1] = (((i2 - i3) + 2) * dArr2[i3 - 2]) - ((i3 - 1) * dArr2[i3 - 1]);
                    } else {
                        d = exp;
                        z = z2;
                        dArr2[z ? 1 : 0] = 0.0d;
                    }
                    i3--;
                    z2 = z;
                    exp = d;
                    i = 1;
                }
                d4 *= d3;
                dArr[i2] = d4 * d5;
                i2++;
                exp = exp;
                i = 1;
                d2 = 1.0d;
            }
            boolean z3 = z2;
            dArr[z3 ? 1 : 0] = dArr[z3 ? 1 : 0] + this.lo;
        }
        return derivativeStructure.compose(dArr);
    }
}
