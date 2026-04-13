package org.apache.commons.math3.optimization.direct;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.MultivariateOptimizer;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.univariate.BracketFinder;
import org.apache.commons.math3.optimization.univariate.BrentOptimizer;
import org.apache.commons.math3.optimization.univariate.SimpleUnivariateValueChecker;
import org.apache.commons.math3.optimization.univariate.UnivariatePointValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
/* loaded from: classes10.dex */
public class PowellOptimizer extends BaseAbstractMultivariateOptimizer<MultivariateFunction> implements MultivariateOptimizer {
    private static final double MIN_RELATIVE_TOLERANCE = FastMath.ulp(1.0d) * 2.0d;
    private final double absoluteThreshold;
    private final LineSearch line;
    private final double relativeThreshold;

    public PowellOptimizer(double rel, double abs, ConvergenceChecker<PointValuePair> checker) {
        this(rel, abs, FastMath.sqrt(rel), FastMath.sqrt(abs), checker);
    }

    public PowellOptimizer(double rel, double abs, double lineRel, double lineAbs, ConvergenceChecker<PointValuePair> checker) {
        super(checker);
        if (rel < MIN_RELATIVE_TOLERANCE) {
            throw new NumberIsTooSmallException(Double.valueOf(rel), Double.valueOf(MIN_RELATIVE_TOLERANCE), true);
        }
        if (abs <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(abs));
        }
        this.relativeThreshold = rel;
        this.absoluteThreshold = abs;
        this.line = new LineSearch(lineRel, lineAbs);
    }

    public PowellOptimizer(double rel, double abs) {
        this(rel, abs, null);
    }

    public PowellOptimizer(double rel, double abs, double lineRel, double lineAbs) {
        this(rel, abs, lineRel, lineAbs, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateOptimizer
    protected PointValuePair doOptimize() {
        double d;
        PointValuePair pointValuePair;
        PointValuePair pointValuePair2;
        GoalType goalType = getGoalType();
        double[] startPoint = getStartPoint();
        int length = startPoint.length;
        int i = 1;
        boolean z = false;
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, length);
        for (int i2 = 0; i2 < length; i2++) {
            dArr[i2][i2] = 1.0d;
        }
        ConvergenceChecker<PointValuePair> convergenceChecker = getConvergenceChecker();
        double[] dArr2 = startPoint;
        double computeObjectiveValue = computeObjectiveValue(dArr2);
        double[] dArr3 = (double[]) dArr2.clone();
        int i3 = 0;
        while (true) {
            i3 += i;
            d = computeObjectiveValue;
            double d2 = 0.0d;
            int i4 = 0;
            int i5 = i;
            int i6 = 0;
            while (i6 < length) {
                boolean z2 = z;
                double[] copyOf = MathArrays.copyOf(dArr[i6]);
                double d3 = computeObjectiveValue;
                double[] dArr4 = startPoint;
                UnivariatePointValuePair search = this.line.search(dArr2, copyOf);
                computeObjectiveValue = search.getValue();
                double[][] dArr5 = dArr;
                int i7 = i6;
                dArr2 = newPointAndDirection(dArr2, copyOf, search.getPoint())[z2 ? 1 : 0];
                if (d3 - computeObjectiveValue > d2) {
                    d2 = d3 - computeObjectiveValue;
                    i4 = i7;
                }
                z = z2 ? 1 : 0;
                dArr = dArr5;
                i6 = i7 + 1;
                startPoint = dArr4;
            }
            double[] dArr6 = startPoint;
            double[][] dArr7 = dArr;
            boolean z3 = z;
            int i8 = (d - computeObjectiveValue) * 2.0d <= (this.relativeThreshold * (FastMath.abs(d) + FastMath.abs(computeObjectiveValue))) + this.absoluteThreshold ? i5 : z3 ? 1 : 0;
            pointValuePair = new PointValuePair(dArr3, d);
            pointValuePair2 = new PointValuePair(dArr2, computeObjectiveValue);
            int i9 = i8;
            i9 = i8;
            if (i8 == 0 && convergenceChecker != null) {
                i9 = convergenceChecker.converged(i3, pointValuePair, pointValuePair2);
            }
            if (i9 != 0) {
                break;
            }
            double[] dArr8 = new double[length];
            GoalType goalType2 = goalType;
            double[] dArr9 = new double[length];
            for (int i10 = 0; i10 < length; i10++) {
                dArr8[i10] = dArr2[i10] - dArr3[i10];
                dArr9[i10] = (dArr2[i10] * 2.0d) - dArr3[i10];
            }
            dArr3 = (double[]) dArr2.clone();
            double computeObjectiveValue2 = computeObjectiveValue(dArr9);
            if (d > computeObjectiveValue2) {
                double d4 = (d - computeObjectiveValue) - d2;
                double d5 = ((d + computeObjectiveValue2) - (computeObjectiveValue * 2.0d)) * 2.0d * d4 * d4;
                double d6 = d - computeObjectiveValue2;
                if (d5 - ((d2 * d6) * d6) < 0.0d) {
                    UnivariatePointValuePair search2 = this.line.search(dArr2, dArr8);
                    computeObjectiveValue = search2.getValue();
                    double[][] newPointAndDirection = newPointAndDirection(dArr2, dArr8, search2.getPoint());
                    dArr2 = newPointAndDirection[z3 ? 1 : 0];
                    int i11 = length - 1;
                    dArr7[i4] = dArr7[i11];
                    dArr7[i11] = newPointAndDirection[i5];
                }
            }
            i = i5;
            startPoint = dArr6;
            z = z3 ? 1 : 0;
            dArr = dArr7;
            goalType = goalType2;
        }
        return goalType == GoalType.MINIMIZE ? computeObjectiveValue < d ? pointValuePair2 : pointValuePair : computeObjectiveValue > d ? pointValuePair2 : pointValuePair;
    }

    private double[][] newPointAndDirection(double[] p, double[] d, double optimum) {
        int n = p.length;
        double[] nP = new double[n];
        double[] nD = new double[n];
        for (int i = 0; i < n; i++) {
            nD[i] = d[i] * optimum;
            nP[i] = p[i] + nD[i];
        }
        double[][] result = {nP, nD};
        return result;
    }

    /* loaded from: classes10.dex */
    private class LineSearch extends BrentOptimizer {
        private static final double ABS_TOL_UNUSED = Double.MIN_VALUE;
        private static final double REL_TOL_UNUSED = 1.0E-15d;
        private final BracketFinder bracket;

        LineSearch(double rel, double abs) {
            super(1.0E-15d, Double.MIN_VALUE, new SimpleUnivariateValueChecker(rel, abs));
            this.bracket = new BracketFinder();
        }

        public UnivariatePointValuePair search(final double[] p, final double[] d) {
            final int n = p.length;
            UnivariateFunction f = new UnivariateFunction() { // from class: org.apache.commons.math3.optimization.direct.PowellOptimizer.LineSearch.1
                @Override // org.apache.commons.math3.analysis.UnivariateFunction
                public double value(double alpha) {
                    double[] x = new double[n];
                    for (int i = 0; i < n; i++) {
                        x[i] = p[i] + (d[i] * alpha);
                    }
                    double obj = PowellOptimizer.this.computeObjectiveValue(x);
                    return obj;
                }
            };
            GoalType goal = PowellOptimizer.this.getGoalType();
            this.bracket.search(f, goal, 0.0d, 1.0d);
            return optimize(Integer.MAX_VALUE, f, goal, this.bracket.getLo(), this.bracket.getHi(), this.bracket.getMid());
        }
    }
}
