package org.apache.commons.math3.optim.nonlinear.scalar;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optim.BaseMultivariateOptimizer;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;

/* loaded from: classes10.dex */
public abstract class MultivariateOptimizer extends BaseMultivariateOptimizer<PointValuePair> {
    private MultivariateFunction function;
    private GoalType goal;

    /* JADX INFO: Access modifiers changed from: protected */
    public MultivariateOptimizer(ConvergenceChecker<PointValuePair> checker) {
        super(checker);
    }

    @Override // org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    public PointValuePair optimize(OptimizationData... optData) throws TooManyEvaluationsException {
        return (PointValuePair) super.optimize(optData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    public void parseOptimizationData(OptimizationData... optData) {
        super.parseOptimizationData(optData);
        for (OptimizationData data : optData) {
            if (data instanceof GoalType) {
                this.goal = (GoalType) data;
            } else if (data instanceof ObjectiveFunction) {
                this.function = ((ObjectiveFunction) data).getObjectiveFunction();
            }
        }
    }

    public GoalType getGoalType() {
        return this.goal;
    }

    public double computeObjectiveValue(double[] params) {
        super.incrementEvaluationCount();
        return this.function.value(params);
    }
}
