package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;

/* loaded from: classes10.dex */
public interface LeastSquaresOptimizer {

    /* loaded from: classes10.dex */
    public interface Optimum extends LeastSquaresProblem.Evaluation {
        int getEvaluations();

        int getIterations();
    }

    Optimum optimize(LeastSquaresProblem leastSquaresProblem);
}
