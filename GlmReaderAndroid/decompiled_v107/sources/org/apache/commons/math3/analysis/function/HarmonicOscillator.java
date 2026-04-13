package org.apache.commons.math3.analysis.function;

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
public class HarmonicOscillator implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
    private final double amplitude;
    private final double omega;
    private final double phase;

    public HarmonicOscillator(double amplitude, double omega, double phase) {
        this.amplitude = amplitude;
        this.omega = omega;
        this.phase = phase;
    }

    @Override // org.apache.commons.math3.analysis.UnivariateFunction
    public double value(double x) {
        return value((this.omega * x) + this.phase, this.amplitude);
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
            return HarmonicOscillator.value((param[1] * x) + param[2], param[0]);
        }

        @Override // org.apache.commons.math3.analysis.ParametricUnivariateFunction
        public double[] gradient(double x, double... param) throws NullArgumentException, DimensionMismatchException {
            validateParameters(param);
            double amplitude = param[0];
            double omega = param[1];
            double phase = param[2];
            double xTimesOmegaPlusPhase = (omega * x) + phase;
            double a = HarmonicOscillator.value(xTimesOmegaPlusPhase, 1.0d);
            double p = (-amplitude) * FastMath.sin(xTimesOmegaPlusPhase);
            double w = p * x;
            return new double[]{a, w, p};
        }

        private void validateParameters(double[] param) throws NullArgumentException, DimensionMismatchException {
            if (param == null) {
                throw new NullArgumentException();
            }
            if (param.length != 3) {
                throw new DimensionMismatchException(param.length, 3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double value(double xTimesOmegaPlusPhase, double amplitude) {
        return FastMath.cos(xTimesOmegaPlusPhase) * amplitude;
    }

    @Override // org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction
    public DerivativeStructure value(DerivativeStructure t) throws DimensionMismatchException {
        double x = t.getValue();
        double[] f = new double[t.getOrder() + 1];
        double alpha = (this.omega * x) + this.phase;
        f[0] = this.amplitude * FastMath.cos(alpha);
        if (f.length > 1) {
            f[1] = (-this.amplitude) * this.omega * FastMath.sin(alpha);
            double mo2 = (-this.omega) * this.omega;
            for (int i = 2; i < f.length; i++) {
                f[i] = f[i - 2] * mo2;
            }
        }
        return t.compose(f);
    }
}
