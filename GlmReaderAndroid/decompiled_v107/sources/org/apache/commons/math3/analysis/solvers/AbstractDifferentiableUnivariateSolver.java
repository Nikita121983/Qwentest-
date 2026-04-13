package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

@Deprecated
/* loaded from: classes10.dex */
public abstract class AbstractDifferentiableUnivariateSolver extends BaseAbstractUnivariateSolver<DifferentiableUnivariateFunction> implements DifferentiableUnivariateSolver {
    private UnivariateFunction functionDerivative;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDifferentiableUnivariateSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    protected AbstractDifferentiableUnivariateSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double computeDerivativeObjectiveValue(double point) throws TooManyEvaluationsException {
        incrementEvaluationCount();
        return this.functionDerivative.value(point);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    public void setup(int maxEval, DifferentiableUnivariateFunction f, double min, double max, double startValue) {
        super.setup(maxEval, (int) f, min, max, startValue);
        this.functionDerivative = f.derivative();
    }
}
