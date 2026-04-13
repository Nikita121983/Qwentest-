package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.IntegerSequence;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public abstract class BaseAbstractUnivariateSolver<FUNC extends UnivariateFunction> implements BaseUnivariateSolver<FUNC> {
    private static final double DEFAULT_FUNCTION_VALUE_ACCURACY = 1.0E-15d;
    private static final double DEFAULT_RELATIVE_ACCURACY = 1.0E-14d;
    private final double absoluteAccuracy;
    private IntegerSequence.Incrementor evaluations;
    private FUNC function;
    private final double functionValueAccuracy;
    private final double relativeAccuracy;
    private double searchMax;
    private double searchMin;
    private double searchStart;

    protected abstract double doSolve() throws TooManyEvaluationsException, NoBracketingException;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseAbstractUnivariateSolver(double absoluteAccuracy) {
        this(DEFAULT_RELATIVE_ACCURACY, absoluteAccuracy, 1.0E-15d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseAbstractUnivariateSolver(double relativeAccuracy, double absoluteAccuracy) {
        this(relativeAccuracy, absoluteAccuracy, 1.0E-15d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseAbstractUnivariateSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
        this.absoluteAccuracy = absoluteAccuracy;
        this.relativeAccuracy = relativeAccuracy;
        this.functionValueAccuracy = functionValueAccuracy;
        this.evaluations = IntegerSequence.Incrementor.create();
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseUnivariateSolver
    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseUnivariateSolver
    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public double getMin() {
        return this.searchMin;
    }

    public double getMax() {
        return this.searchMax;
    }

    public double getStartValue() {
        return this.searchStart;
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseUnivariateSolver
    public double getAbsoluteAccuracy() {
        return this.absoluteAccuracy;
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseUnivariateSolver
    public double getRelativeAccuracy() {
        return this.relativeAccuracy;
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseUnivariateSolver
    public double getFunctionValueAccuracy() {
        return this.functionValueAccuracy;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double computeObjectiveValue(double point) throws TooManyEvaluationsException {
        incrementEvaluationCount();
        return this.function.value(point);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setup(int maxEval, FUNC f, double min, double max, double startValue) throws NullArgumentException {
        MathUtils.checkNotNull(f);
        this.searchMin = min;
        this.searchMax = max;
        this.searchStart = startValue;
        this.function = f;
        this.evaluations = this.evaluations.withMaximalCount(maxEval).withStart(0);
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseUnivariateSolver
    public double solve(int maxEval, FUNC f, double min, double max, double startValue) throws TooManyEvaluationsException, NoBracketingException {
        setup(maxEval, f, min, max, startValue);
        return doSolve();
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseUnivariateSolver
    public double solve(int maxEval, FUNC f, double min, double max) {
        return solve(maxEval, f, min, max, min + ((max - min) * 0.5d));
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseUnivariateSolver
    public double solve(int maxEval, FUNC f, double startValue) throws TooManyEvaluationsException, NoBracketingException {
        return solve(maxEval, f, Double.NaN, Double.NaN, startValue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isBracketing(double lower, double upper) {
        return UnivariateSolverUtils.isBracketing(this.function, lower, upper);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isSequence(double start, double mid, double end) {
        return UnivariateSolverUtils.isSequence(start, mid, end);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void verifyInterval(double lower, double upper) throws NumberIsTooLargeException {
        UnivariateSolverUtils.verifyInterval(lower, upper);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void verifySequence(double lower, double initial, double upper) throws NumberIsTooLargeException {
        UnivariateSolverUtils.verifySequence(lower, initial, upper);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void verifyBracketing(double lower, double upper) throws NullArgumentException, NoBracketingException {
        UnivariateSolverUtils.verifyBracketing(this.function, lower, upper);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void incrementEvaluationCount() throws TooManyEvaluationsException {
        try {
            this.evaluations.increment();
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
    }
}
