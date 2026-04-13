package org.apache.commons.math3.optim.linear;

import java.util.Collection;
import java.util.Collections;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;

/* loaded from: classes10.dex */
public abstract class LinearOptimizer extends MultivariateOptimizer {
    private LinearObjectiveFunction function;
    private Collection<LinearConstraint> linearConstraints;
    private boolean nonNegative;

    /* JADX INFO: Access modifiers changed from: protected */
    public LinearOptimizer() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isRestrictedToNonNegative() {
        return this.nonNegative;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LinearObjectiveFunction getFunction() {
        return this.function;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Collection<LinearConstraint> getConstraints() {
        return Collections.unmodifiableCollection(this.linearConstraints);
    }

    @Override // org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer, org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    public PointValuePair optimize(OptimizationData... optData) throws TooManyIterationsException {
        return super.optimize(optData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer, org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    public void parseOptimizationData(OptimizationData... optData) {
        super.parseOptimizationData(optData);
        for (OptimizationData data : optData) {
            if (data instanceof LinearObjectiveFunction) {
                this.function = (LinearObjectiveFunction) data;
            } else if (data instanceof LinearConstraintSet) {
                this.linearConstraints = ((LinearConstraintSet) data).getConstraints();
            } else if (data instanceof NonNegativeConstraint) {
                this.nonNegative = ((NonNegativeConstraint) data).isRestrictedToNonNegative();
            }
        }
    }
}
