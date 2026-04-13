package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

/* loaded from: classes10.dex */
public abstract class AbstractPolynomialSolver extends BaseAbstractUnivariateSolver<PolynomialFunction> implements PolynomialSolver {
    private PolynomialFunction polynomialFunction;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractPolynomialSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractPolynomialSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractPolynomialSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    public void setup(int maxEval, PolynomialFunction f, double min, double max, double startValue) {
        super.setup(maxEval, (int) f, min, max, startValue);
        this.polynomialFunction = f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double[] getCoefficients() {
        return this.polynomialFunction.getCoefficients();
    }
}
