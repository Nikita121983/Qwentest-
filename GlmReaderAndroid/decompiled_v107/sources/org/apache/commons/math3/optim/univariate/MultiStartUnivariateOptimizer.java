package org.apache.commons.math3.optim.univariate;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.random.RandomGenerator;

/* loaded from: classes10.dex */
public class MultiStartUnivariateOptimizer extends UnivariateOptimizer {
    private RandomGenerator generator;
    private int maxEvalIndex;
    private OptimizationData[] optimData;
    private UnivariatePointValuePair[] optima;
    private final UnivariateOptimizer optimizer;
    private int searchIntervalIndex;
    private int starts;
    private int totalEvaluations;

    public MultiStartUnivariateOptimizer(UnivariateOptimizer optimizer, int starts, RandomGenerator generator) {
        super(optimizer.getConvergenceChecker());
        this.maxEvalIndex = -1;
        this.searchIntervalIndex = -1;
        if (starts < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(starts));
        }
        this.optimizer = optimizer;
        this.starts = starts;
        this.generator = generator;
    }

    @Override // org.apache.commons.math3.optim.BaseOptimizer
    public int getEvaluations() {
        return this.totalEvaluations;
    }

    public UnivariatePointValuePair[] getOptima() {
        if (this.optima == null) {
            throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]);
        }
        return (UnivariatePointValuePair[]) this.optima.clone();
    }

    @Override // org.apache.commons.math3.optim.univariate.UnivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    public UnivariatePointValuePair optimize(OptimizationData... optData) {
        this.optimData = optData;
        return super.optimize(optData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.optim.BaseOptimizer
    public UnivariatePointValuePair doOptimize() {
        boolean z;
        for (int i = 0; i < this.optimData.length; i++) {
            if (this.optimData[i] instanceof MaxEval) {
                this.optimData[i] = null;
                this.maxEvalIndex = i;
            } else if (this.optimData[i] instanceof SearchInterval) {
                this.optimData[i] = null;
                this.searchIntervalIndex = i;
            }
        }
        if (this.maxEvalIndex == -1) {
            throw new MathIllegalStateException();
        }
        if (this.searchIntervalIndex != -1) {
            this.optima = new UnivariatePointValuePair[this.starts];
            boolean z2 = false;
            this.totalEvaluations = 0;
            int maxEvaluations = getMaxEvaluations();
            double min = getMin();
            double max = getMax();
            double startValue = getStartValue();
            RuntimeException runtimeException = null;
            int i2 = 0;
            while (i2 < this.starts) {
                try {
                    this.optimData[this.maxEvalIndex] = new MaxEval(maxEvaluations - this.totalEvaluations);
                    this.optimData[this.searchIntervalIndex] = new SearchInterval(min, max, i2 == 0 ? startValue : (this.generator.nextDouble() * (max - min)) + min);
                    z = z2;
                    try {
                        this.optima[i2] = this.optimizer.optimize(this.optimData);
                    } catch (RuntimeException e) {
                        e = e;
                        this.optima[i2] = null;
                        runtimeException = e;
                        this.totalEvaluations += this.optimizer.getEvaluations();
                        i2++;
                        z2 = z;
                    }
                } catch (RuntimeException e2) {
                    e = e2;
                    z = z2;
                }
                this.totalEvaluations += this.optimizer.getEvaluations();
                i2++;
                z2 = z;
            }
            boolean z3 = z2;
            sortPairs(getGoalType());
            if (this.optima[z3 ? 1 : 0] == null) {
                throw runtimeException;
            }
            return this.optima[z3 ? 1 : 0];
        }
        throw new MathIllegalStateException();
    }

    private void sortPairs(final GoalType goal) {
        Arrays.sort(this.optima, new Comparator<UnivariatePointValuePair>() { // from class: org.apache.commons.math3.optim.univariate.MultiStartUnivariateOptimizer.1
            @Override // java.util.Comparator
            public int compare(UnivariatePointValuePair o1, UnivariatePointValuePair o2) {
                if (o1 == null) {
                    return o2 == null ? 0 : 1;
                }
                if (o2 == null) {
                    return -1;
                }
                double v1 = o1.getValue();
                double v2 = o2.getValue();
                return goal == GoalType.MINIMIZE ? Double.compare(v1, v2) : Double.compare(v2, v1);
            }
        });
    }
}
