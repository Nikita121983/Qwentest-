package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class Logit implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
    private final double hi;
    private final double lo;

    public Logit() {
        this(0.0d, 1.0d);
    }

    public Logit(double lo, double hi) {
        this.lo = lo;
        this.hi = hi;
    }

    @Override // org.apache.commons.math3.analysis.UnivariateFunction
    public double value(double x) throws OutOfRangeException {
        return value(x, this.lo, this.hi);
    }

    @Override // org.apache.commons.math3.analysis.DifferentiableUnivariateFunction
    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    /* loaded from: classes10.dex */
    public static class Parametric implements ParametricUnivariateFunction {
        @Override // org.apache.commons.math3.analysis.ParametricUnivariateFunction
        public double value(double x, double... param) throws NullArgumentException, DimensionMismatchException {
            validateParameters(param);
            return Logit.value(x, param[0], param[1]);
        }

        @Override // org.apache.commons.math3.analysis.ParametricUnivariateFunction
        public double[] gradient(double x, double... param) throws NullArgumentException, DimensionMismatchException {
            validateParameters(param);
            double lo = param[0];
            double hi = param[1];
            return new double[]{1.0d / (lo - x), 1.0d / (hi - x)};
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
    public static double value(double x, double lo, double hi) throws OutOfRangeException {
        if (x < lo || x > hi) {
            throw new OutOfRangeException(Double.valueOf(x), Double.valueOf(lo), Double.valueOf(hi));
        }
        return FastMath.log((x - lo) / (hi - x));
    }

    @Override // org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction
    public DerivativeStructure value(DerivativeStructure t) throws OutOfRangeException {
        double x = t.getValue();
        if (x < this.lo || x > this.hi) {
            throw new OutOfRangeException(Double.valueOf(x), Double.valueOf(this.lo), Double.valueOf(this.hi));
        }
        double[] f = new double[t.getOrder() + 1];
        f[0] = FastMath.log((x - this.lo) / (this.hi - x));
        if (Double.isInfinite(f[0])) {
            if (f.length > 1) {
                f[1] = Double.POSITIVE_INFINITY;
            }
            for (int i = 2; i < f.length; i++) {
                f[i] = f[i - 2];
            }
        } else {
            double invL = 1.0d / (x - this.lo);
            double xL = invL;
            double invH = 1.0d / (this.hi - x);
            double xH = invH;
            for (int i2 = 1; i2 < f.length; i2++) {
                f[i2] = xL + xH;
                xL *= (-i2) * invL;
                xH *= i2 * invH;
            }
        }
        return t.compose(f);
    }
}
