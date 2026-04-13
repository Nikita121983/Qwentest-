package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optimization.BaseMultivariateVectorOptimizer;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.InitialGuess;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.optimization.SimpleVectorValueChecker;
import org.apache.commons.math3.optimization.Target;
import org.apache.commons.math3.optimization.Weight;
import org.apache.commons.math3.util.Incrementor;

@Deprecated
/* loaded from: classes10.dex */
public abstract class BaseAbstractMultivariateVectorOptimizer<FUNC extends MultivariateVectorFunction> implements BaseMultivariateVectorOptimizer<FUNC> {
    private ConvergenceChecker<PointVectorValuePair> checker;
    protected final Incrementor evaluations;
    private FUNC function;
    private double[] start;
    private double[] target;

    @Deprecated
    private double[] weight;
    private RealMatrix weightMatrix;

    protected abstract PointVectorValuePair doOptimize();

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public BaseAbstractMultivariateVectorOptimizer() {
        this(new SimpleVectorValueChecker());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseAbstractMultivariateVectorOptimizer(ConvergenceChecker<PointVectorValuePair> checker) {
        this.evaluations = new Incrementor();
        this.checker = checker;
    }

    @Override // org.apache.commons.math3.optimization.BaseOptimizer
    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    @Override // org.apache.commons.math3.optimization.BaseOptimizer
    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    @Override // org.apache.commons.math3.optimization.BaseOptimizer
    public ConvergenceChecker<PointVectorValuePair> getConvergenceChecker() {
        return this.checker;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double[] computeObjectiveValue(double[] point) {
        try {
            this.evaluations.incrementCount();
            return this.function.value(point);
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
    }

    @Override // org.apache.commons.math3.optimization.BaseMultivariateVectorOptimizer
    @Deprecated
    public PointVectorValuePair optimize(int maxEval, FUNC f, double[] t, double[] w, double[] startPoint) {
        return optimizeInternal(maxEval, f, t, w, startPoint);
    }

    protected PointVectorValuePair optimize(int maxEval, FUNC f, OptimizationData... optData) throws TooManyEvaluationsException, DimensionMismatchException {
        return optimizeInternal(maxEval, f, optData);
    }

    @Deprecated
    protected PointVectorValuePair optimizeInternal(int maxEval, FUNC f, double[] t, double[] w, double[] startPoint) {
        if (f == null) {
            throw new NullArgumentException();
        }
        if (t == null) {
            throw new NullArgumentException();
        }
        if (w == null) {
            throw new NullArgumentException();
        }
        if (startPoint == null) {
            throw new NullArgumentException();
        }
        if (t.length == w.length) {
            return optimizeInternal(maxEval, f, new Target(t), new Weight(w), new InitialGuess(startPoint));
        }
        throw new DimensionMismatchException(t.length, w.length);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PointVectorValuePair optimizeInternal(int maxEval, FUNC f, OptimizationData... optData) throws TooManyEvaluationsException, DimensionMismatchException {
        this.evaluations.setMaximalCount(maxEval);
        this.evaluations.resetCount();
        this.function = f;
        parseOptimizationData(optData);
        checkParameters();
        setUp();
        return doOptimize();
    }

    public double[] getStartPoint() {
        return (double[]) this.start.clone();
    }

    public RealMatrix getWeight() {
        return this.weightMatrix.copy();
    }

    public double[] getTarget() {
        return (double[]) this.target.clone();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FUNC getObjectiveFunction() {
        return this.function;
    }

    @Deprecated
    protected double[] getTargetRef() {
        return this.target;
    }

    @Deprecated
    protected double[] getWeightRef() {
        return this.weight;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setUp() {
        int dim = this.target.length;
        this.weight = new double[dim];
        for (int i = 0; i < dim; i++) {
            this.weight[i] = this.weightMatrix.getEntry(i, i);
        }
    }

    private void parseOptimizationData(OptimizationData... optData) {
        for (OptimizationData data : optData) {
            if (data instanceof Target) {
                this.target = ((Target) data).getTarget();
            } else if (data instanceof Weight) {
                this.weightMatrix = ((Weight) data).getWeight();
            } else if (data instanceof InitialGuess) {
                this.start = ((InitialGuess) data).getInitialGuess();
            }
        }
    }

    private void checkParameters() {
        if (this.target.length != this.weightMatrix.getColumnDimension()) {
            throw new DimensionMismatchException(this.target.length, this.weightMatrix.getColumnDimension());
        }
    }
}
