package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.util.Incrementor;

/* loaded from: classes10.dex */
public abstract class BaseOptimizer<PAIR> {
    private final ConvergenceChecker<PAIR> checker;
    protected final Incrementor evaluations;
    protected final Incrementor iterations;

    protected abstract PAIR doOptimize();

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseOptimizer(ConvergenceChecker<PAIR> checker) {
        this(checker, 0, Integer.MAX_VALUE);
    }

    protected BaseOptimizer(ConvergenceChecker<PAIR> checker, int maxEval, int maxIter) {
        this.checker = checker;
        this.evaluations = new Incrementor(maxEval, new MaxEvalCallback());
        this.iterations = new Incrementor(maxIter, new MaxIterCallback());
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public int getMaxIterations() {
        return this.iterations.getMaximalCount();
    }

    public int getIterations() {
        return this.iterations.getCount();
    }

    public ConvergenceChecker<PAIR> getConvergenceChecker() {
        return this.checker;
    }

    public PAIR optimize(OptimizationData... optData) throws TooManyEvaluationsException, TooManyIterationsException {
        parseOptimizationData(optData);
        this.evaluations.resetCount();
        this.iterations.resetCount();
        return doOptimize();
    }

    public PAIR optimize() throws TooManyEvaluationsException, TooManyIterationsException {
        this.evaluations.resetCount();
        this.iterations.resetCount();
        return doOptimize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void incrementEvaluationCount() throws TooManyEvaluationsException {
        this.evaluations.incrementCount();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void incrementIterationCount() throws TooManyIterationsException {
        this.iterations.incrementCount();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void parseOptimizationData(OptimizationData... optData) {
        for (OptimizationData data : optData) {
            if (data instanceof MaxEval) {
                this.evaluations.setMaximalCount(((MaxEval) data).getMaxEval());
            } else if (data instanceof MaxIter) {
                this.iterations.setMaximalCount(((MaxIter) data).getMaxIter());
            }
        }
    }

    /* loaded from: classes10.dex */
    private static class MaxEvalCallback implements Incrementor.MaxCountExceededCallback {
        private MaxEvalCallback() {
        }

        @Override // org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback
        public void trigger(int max) {
            throw new TooManyEvaluationsException(Integer.valueOf(max));
        }
    }

    /* loaded from: classes10.dex */
    private static class MaxIterCallback implements Incrementor.MaxCountExceededCallback {
        private MaxIterCallback() {
        }

        @Override // org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback
        public void trigger(int max) {
            throw new TooManyIterationsException(Integer.valueOf(max));
        }
    }
}
