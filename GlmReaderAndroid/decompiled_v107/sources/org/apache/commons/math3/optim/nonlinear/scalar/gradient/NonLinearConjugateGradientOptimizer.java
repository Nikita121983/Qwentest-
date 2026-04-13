package org.apache.commons.math3.optim.nonlinear.scalar.gradient;

import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.GradientMultivariateOptimizer;
import org.apache.commons.math3.optim.nonlinear.scalar.LineSearch;

/* loaded from: classes10.dex */
public class NonLinearConjugateGradientOptimizer extends GradientMultivariateOptimizer {
    private final LineSearch line;
    private final Preconditioner preconditioner;
    private final Formula updateFormula;

    /* loaded from: classes10.dex */
    public enum Formula {
        FLETCHER_REEVES,
        POLAK_RIBIERE
    }

    @Deprecated
    /* loaded from: classes10.dex */
    public static class BracketingStep implements OptimizationData {
        private final double initialStep;

        public BracketingStep(double step) {
            this.initialStep = step;
        }

        public double getBracketingStep() {
            return this.initialStep;
        }
    }

    public NonLinearConjugateGradientOptimizer(Formula updateFormula, ConvergenceChecker<PointValuePair> checker) {
        this(updateFormula, checker, 1.0E-8d, 1.0E-8d, 1.0E-8d, new IdentityPreconditioner());
    }

    @Deprecated
    public NonLinearConjugateGradientOptimizer(Formula updateFormula, ConvergenceChecker<PointValuePair> checker, UnivariateSolver lineSearchSolver) {
        this(updateFormula, checker, lineSearchSolver, new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(Formula updateFormula, ConvergenceChecker<PointValuePair> checker, double relativeTolerance, double absoluteTolerance, double initialBracketingRange) {
        this(updateFormula, checker, relativeTolerance, absoluteTolerance, initialBracketingRange, new IdentityPreconditioner());
    }

    @Deprecated
    public NonLinearConjugateGradientOptimizer(Formula updateFormula, ConvergenceChecker<PointValuePair> checker, UnivariateSolver lineSearchSolver, Preconditioner preconditioner) {
        this(updateFormula, checker, lineSearchSolver.getRelativeAccuracy(), lineSearchSolver.getAbsoluteAccuracy(), lineSearchSolver.getAbsoluteAccuracy(), preconditioner);
    }

    public NonLinearConjugateGradientOptimizer(Formula updateFormula, ConvergenceChecker<PointValuePair> checker, double relativeTolerance, double absoluteTolerance, double initialBracketingRange, Preconditioner preconditioner) {
        super(checker);
        this.updateFormula = updateFormula;
        this.preconditioner = preconditioner;
        this.line = new LineSearch(this, relativeTolerance, absoluteTolerance, initialBracketingRange);
    }

    @Override // org.apache.commons.math3.optim.nonlinear.scalar.GradientMultivariateOptimizer, org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer, org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    public PointValuePair optimize(OptimizationData... optData) throws TooManyEvaluationsException {
        return super.optimize(optData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.optim.BaseOptimizer
    public PointValuePair doOptimize() {
        double[] steepestDescent;
        double[] searchDirection;
        double beta;
        NonLinearConjugateGradientOptimizer nonLinearConjugateGradientOptimizer = this;
        ConvergenceChecker<PointValuePair> checker = nonLinearConjugateGradientOptimizer.getConvergenceChecker();
        double[] point = nonLinearConjugateGradientOptimizer.getStartPoint();
        GoalType goal = nonLinearConjugateGradientOptimizer.getGoalType();
        int n = point.length;
        double[] r = nonLinearConjugateGradientOptimizer.computeObjectiveGradient(point);
        if (goal == GoalType.MINIMIZE) {
            for (int i = 0; i < n; i++) {
                r[i] = -r[i];
            }
        }
        double[] steepestDescent2 = nonLinearConjugateGradientOptimizer.preconditioner.precondition(point, r);
        double[] searchDirection2 = (double[]) steepestDescent2.clone();
        double delta = 0.0d;
        for (int i2 = 0; i2 < n; i2++) {
            delta += r[i2] * searchDirection2[i2];
        }
        PointValuePair current = null;
        while (true) {
            nonLinearConjugateGradientOptimizer.incrementIterationCount();
            double objective = nonLinearConjugateGradientOptimizer.computeObjectiveValue(point);
            PointValuePair previous = current;
            current = new PointValuePair(point, objective);
            if (previous != null && checker.converged(nonLinearConjugateGradientOptimizer.getIterations(), previous, current)) {
                return current;
            }
            double step = nonLinearConjugateGradientOptimizer.line.search(point, searchDirection2).getPoint();
            ConvergenceChecker<PointValuePair> checker2 = checker;
            int i3 = 0;
            while (true) {
                double[] r2 = r;
                if (i3 < point.length) {
                    point[i3] = point[i3] + (searchDirection2[i3] * step);
                    i3++;
                    r = r2;
                } else {
                    r = nonLinearConjugateGradientOptimizer.computeObjectiveGradient(point);
                    if (goal != GoalType.MINIMIZE) {
                        steepestDescent = steepestDescent2;
                        searchDirection = searchDirection2;
                    } else {
                        int i4 = 0;
                        while (i4 < n) {
                            r[i4] = -r[i4];
                            i4++;
                            steepestDescent2 = steepestDescent2;
                            searchDirection2 = searchDirection2;
                        }
                        steepestDescent = steepestDescent2;
                        searchDirection = searchDirection2;
                    }
                    double deltaOld = delta;
                    double[] newSteepestDescent = nonLinearConjugateGradientOptimizer.preconditioner.precondition(point, r);
                    delta = 0.0d;
                    for (int i5 = 0; i5 < n; i5++) {
                        delta += r[i5] * newSteepestDescent[i5];
                    }
                    switch (nonLinearConjugateGradientOptimizer.updateFormula) {
                        case FLETCHER_REEVES:
                            beta = delta / deltaOld;
                            break;
                        case POLAK_RIBIERE:
                            double deltaMid = 0.0d;
                            for (int i6 = 0; i6 < r.length; i6++) {
                                deltaMid += r[i6] * steepestDescent[i6];
                            }
                            beta = (delta - deltaMid) / deltaOld;
                            break;
                        default:
                            throw new MathInternalError();
                    }
                    if (getIterations() % n != 0 && beta >= 0.0d) {
                        double beta2 = beta;
                        for (int i7 = 0; i7 < n; i7++) {
                            searchDirection[i7] = newSteepestDescent[i7] + (searchDirection[i7] * beta2);
                        }
                        searchDirection2 = searchDirection;
                        nonLinearConjugateGradientOptimizer = this;
                        steepestDescent2 = newSteepestDescent;
                        checker = checker2;
                    }
                    searchDirection2 = (double[]) newSteepestDescent.clone();
                    nonLinearConjugateGradientOptimizer = this;
                    steepestDescent2 = newSteepestDescent;
                    checker = checker2;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.optim.nonlinear.scalar.GradientMultivariateOptimizer, org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer, org.apache.commons.math3.optim.BaseMultivariateOptimizer, org.apache.commons.math3.optim.BaseOptimizer
    public void parseOptimizationData(OptimizationData... optData) {
        super.parseOptimizationData(optData);
        checkParameters();
    }

    /* loaded from: classes10.dex */
    public static class IdentityPreconditioner implements Preconditioner {
        @Override // org.apache.commons.math3.optim.nonlinear.scalar.gradient.Preconditioner
        public double[] precondition(double[] variables, double[] r) {
            return (double[]) r.clone();
        }
    }

    private void checkParameters() {
        if (getLowerBound() != null || getUpperBound() != null) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT, new Object[0]);
        }
    }
}
