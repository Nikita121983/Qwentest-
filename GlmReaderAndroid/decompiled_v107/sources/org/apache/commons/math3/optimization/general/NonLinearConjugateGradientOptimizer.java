package org.apache.commons.math3.optimization.general;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleValueChecker;
import org.apache.commons.math3.util.FastMath;

@Deprecated
/* loaded from: classes10.dex */
public class NonLinearConjugateGradientOptimizer extends AbstractScalarDifferentiableOptimizer {
    private double initialStep;
    private double[] point;
    private final Preconditioner preconditioner;
    private final UnivariateSolver solver;
    private final ConjugateGradientFormula updateFormula;

    @Deprecated
    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula) {
        this(updateFormula, new SimpleValueChecker());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula, ConvergenceChecker<PointValuePair> checker) {
        this(updateFormula, checker, new BrentSolver(), new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula, ConvergenceChecker<PointValuePair> checker, UnivariateSolver lineSearchSolver) {
        this(updateFormula, checker, lineSearchSolver, new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula updateFormula, ConvergenceChecker<PointValuePair> checker, UnivariateSolver lineSearchSolver, Preconditioner preconditioner) {
        super(checker);
        this.updateFormula = updateFormula;
        this.solver = lineSearchSolver;
        this.preconditioner = preconditioner;
        this.initialStep = 1.0d;
    }

    public void setInitialStep(double initialStep) {
        if (initialStep <= 0.0d) {
            this.initialStep = 1.0d;
        } else {
            this.initialStep = initialStep;
        }
    }

    @Override // org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer
    protected PointValuePair doOptimize() {
        int iter;
        double deltaMid;
        ConvergenceChecker<PointValuePair> checker = getConvergenceChecker();
        this.point = getStartPoint();
        GoalType goal = getGoalType();
        int n = this.point.length;
        double[] r = computeObjectiveGradient(this.point);
        if (goal == GoalType.MINIMIZE) {
            for (int i = 0; i < n; i++) {
                r[i] = -r[i];
            }
        }
        double[] steepestDescent = this.preconditioner.precondition(this.point, r);
        double[] searchDirection = (double[]) steepestDescent.clone();
        double delta = 0.0d;
        for (int i2 = 0; i2 < n; i2++) {
            delta += r[i2] * searchDirection[i2];
        }
        PointValuePair current = null;
        double[] steepestDescent2 = steepestDescent;
        int iter2 = 0;
        double delta2 = delta;
        int maxEval = getMaxEvaluations();
        double[] searchDirection2 = searchDirection;
        while (true) {
            int iter3 = iter2 + 1;
            double objective = computeObjectiveValue(this.point);
            PointValuePair previous = current;
            PointValuePair current2 = new PointValuePair(this.point, objective);
            if (previous != null && checker.converged(iter3, previous, current2)) {
                return current2;
            }
            LineSearchFunction lineSearchFunction = new LineSearchFunction(searchDirection2);
            double uB = findUpperBound(lineSearchFunction, 0.0d, this.initialStep);
            int iter4 = iter3;
            double step = this.solver.solve(maxEval, lineSearchFunction, 0.0d, uB, 1.0E-15d);
            maxEval -= this.solver.getEvaluations();
            for (int i3 = 0; i3 < this.point.length; i3++) {
                double[] dArr = this.point;
                dArr[i3] = dArr[i3] + (searchDirection2[i3] * step);
            }
            double[] r2 = computeObjectiveGradient(this.point);
            if (goal != GoalType.MINIMIZE) {
                iter = iter4;
            } else {
                int i4 = 0;
                while (i4 < n) {
                    int i5 = i4;
                    r2[i5] = -r2[i5];
                    i4 = i5 + 1;
                    iter4 = iter4;
                }
                iter = iter4;
            }
            double deltaOld = delta2;
            double[] newSteepestDescent = this.preconditioner.precondition(this.point, r2);
            delta2 = 0.0d;
            for (int i6 = 0; i6 < n; i6++) {
                delta2 += r2[i6] * newSteepestDescent[i6];
            }
            if (this.updateFormula == ConjugateGradientFormula.FLETCHER_REEVES) {
                deltaMid = delta2 / deltaOld;
            } else {
                double deltaMid2 = 0.0d;
                for (int i7 = 0; i7 < r2.length; i7++) {
                    deltaMid2 += r2[i7] * steepestDescent2[i7];
                }
                deltaMid = (delta2 - deltaMid2) / deltaOld;
            }
            steepestDescent2 = newSteepestDescent;
            if (iter % n == 0 || deltaMid < 0.0d) {
                searchDirection2 = (double[]) steepestDescent2.clone();
            } else {
                for (int i8 = 0; i8 < n; i8++) {
                    searchDirection2[i8] = steepestDescent2[i8] + (searchDirection2[i8] * deltaMid);
                }
            }
            iter2 = iter;
            current = current2;
        }
    }

    private double findUpperBound(UnivariateFunction f, double a, double h) {
        double yA = f.value(a);
        double step = h;
        while (step < Double.MAX_VALUE) {
            double b = a + step;
            double yB = f.value(b);
            if (yA * yB > 0.0d) {
                step *= FastMath.max(2.0d, yA / yB);
            } else {
                return b;
            }
        }
        throw new MathIllegalStateException(LocalizedFormats.UNABLE_TO_BRACKET_OPTIMUM_IN_LINE_SEARCH, new Object[0]);
    }

    /* loaded from: classes10.dex */
    public static class IdentityPreconditioner implements Preconditioner {
        @Override // org.apache.commons.math3.optimization.general.Preconditioner
        public double[] precondition(double[] variables, double[] r) {
            return (double[]) r.clone();
        }
    }

    /* loaded from: classes10.dex */
    private class LineSearchFunction implements UnivariateFunction {
        private final double[] searchDirection;

        LineSearchFunction(double[] searchDirection) {
            this.searchDirection = searchDirection;
        }

        @Override // org.apache.commons.math3.analysis.UnivariateFunction
        public double value(double x) {
            double[] shiftedPoint = (double[]) NonLinearConjugateGradientOptimizer.this.point.clone();
            for (int i = 0; i < shiftedPoint.length; i++) {
                shiftedPoint[i] = shiftedPoint[i] + (this.searchDirection[i] * x);
            }
            double[] gradient = NonLinearConjugateGradientOptimizer.this.computeObjectiveGradient(shiftedPoint);
            double dotProduct = 0.0d;
            for (int i2 = 0; i2 < gradient.length; i2++) {
                dotProduct += gradient[i2] * this.searchDirection[i2];
            }
            return dotProduct;
        }
    }
}
