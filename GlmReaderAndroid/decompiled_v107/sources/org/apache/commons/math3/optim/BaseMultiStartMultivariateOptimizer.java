package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.random.RandomVectorGenerator;

/* loaded from: classes10.dex */
public abstract class BaseMultiStartMultivariateOptimizer<PAIR> extends BaseMultivariateOptimizer<PAIR> {
    private RandomVectorGenerator generator;
    private int initialGuessIndex;
    private int maxEvalIndex;
    private OptimizationData[] optimData;
    private final BaseMultivariateOptimizer<PAIR> optimizer;
    private int starts;
    private int totalEvaluations;

    protected abstract void clear();

    public abstract PAIR[] getOptima();

    protected abstract void store(PAIR pair);

    public BaseMultiStartMultivariateOptimizer(BaseMultivariateOptimizer<PAIR> optimizer, int starts, RandomVectorGenerator generator) {
        super(optimizer.getConvergenceChecker());
        this.maxEvalIndex = -1;
        this.initialGuessIndex = -1;
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

    @Override // org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    public PAIR optimize(OptimizationData... optimizationDataArr) {
        this.optimData = optimizationDataArr;
        return (PAIR) super.optimize(optimizationDataArr);
    }

    @Override // org.apache.commons.math3.optim.BaseOptimizer
    protected PAIR doOptimize() {
        for (int i = 0; i < this.optimData.length; i++) {
            if (this.optimData[i] instanceof MaxEval) {
                this.optimData[i] = null;
                this.maxEvalIndex = i;
            }
            if (this.optimData[i] instanceof InitialGuess) {
                this.optimData[i] = null;
                this.initialGuessIndex = i;
            }
        }
        int i2 = this.maxEvalIndex;
        if (i2 == -1) {
            throw new MathIllegalStateException();
        }
        if (this.initialGuessIndex == -1) {
            throw new MathIllegalStateException();
        }
        RuntimeException lastException = null;
        this.totalEvaluations = 0;
        clear();
        int maxEval = getMaxEvaluations();
        double[] min = getLowerBound();
        double[] max = getUpperBound();
        double[] startPoint = getStartPoint();
        for (int i3 = 0; i3 < this.starts; i3++) {
            try {
                this.optimData[this.maxEvalIndex] = new MaxEval(maxEval - this.totalEvaluations);
                double[] s = null;
                if (i3 == 0) {
                    s = startPoint;
                } else {
                    int k = 0;
                    while (s == null) {
                        int attempts = k + 1;
                        if (k >= getMaxEvaluations()) {
                            throw new TooManyEvaluationsException(Integer.valueOf(getMaxEvaluations()));
                            break;
                        }
                        double[] s2 = this.generator.nextVector();
                        s = s2;
                        for (int k2 = 0; s != null && k2 < s.length; k2++) {
                            if ((min != null && s[k2] < min[k2]) || (max != null && s[k2] > max[k2])) {
                                s = null;
                            }
                        }
                        k = attempts;
                    }
                }
                this.optimData[this.initialGuessIndex] = new InitialGuess(s);
                PAIR result = this.optimizer.optimize(this.optimData);
                store(result);
            } catch (RuntimeException mue) {
                lastException = mue;
            }
            this.totalEvaluations += this.optimizer.getEvaluations();
        }
        PAIR[] optima = getOptima();
        if (optima.length == 0) {
            throw lastException;
        }
        return optima[0];
    }
}
