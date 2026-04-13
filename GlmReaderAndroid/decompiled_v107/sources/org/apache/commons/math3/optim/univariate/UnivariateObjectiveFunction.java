package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.optim.OptimizationData;

/* loaded from: classes10.dex */
public class UnivariateObjectiveFunction implements OptimizationData {
    private final UnivariateFunction function;

    public UnivariateObjectiveFunction(UnivariateFunction f) {
        this.function = f;
    }

    public UnivariateFunction getObjectiveFunction() {
        return this.function;
    }
}
