package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;

/* loaded from: classes10.dex */
public abstract class AbstractUnivariateSolver extends BaseAbstractUnivariateSolver<UnivariateFunction> implements UnivariateSolver {
    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractUnivariateSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractUnivariateSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractUnivariateSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
    }
}
