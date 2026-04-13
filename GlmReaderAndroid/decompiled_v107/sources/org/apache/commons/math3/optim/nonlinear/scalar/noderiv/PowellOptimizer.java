package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.LineSearch;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.optim.univariate.UnivariatePointValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

/* loaded from: classes10.dex */
public class PowellOptimizer extends MultivariateOptimizer {
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
        this.line = new LineSearch(this, lineRel, lineAbs, 1.0d);
    }

    public PowellOptimizer(double rel, double abs) {
        this(rel, abs, null);
    }

    public PowellOptimizer(double rel, double abs, double lineRel, double lineAbs) {
        this(rel, abs, lineRel, lineAbs, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.optim.BaseOptimizer
    public PointValuePair doOptimize() {
        double d;
        PointValuePair pointValuePair;
        PointValuePair pointValuePair2;
        checkParameters();
        GoalType goalType = getGoalType();
        double[] startPoint = getStartPoint();
        int length = startPoint.length;
        boolean z = true;
        boolean z2 = false;
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, length);
        for (int i = 0; i < length; i++) {
            dArr[i][i] = 1.0d;
        }
        ConvergenceChecker<PointValuePair> convergenceChecker = getConvergenceChecker();
        double[] dArr2 = startPoint;
        double computeObjectiveValue = computeObjectiveValue(dArr2);
        double[] dArr3 = (double[]) dArr2.clone();
        while (true) {
            incrementIterationCount();
            d = computeObjectiveValue;
            double d2 = 0.0d;
            int i2 = 0;
            boolean z3 = z;
            int i3 = 0;
            while (i3 < length) {
                boolean z4 = z2;
                double[] copyOf = MathArrays.copyOf(dArr[i3]);
                double d3 = computeObjectiveValue;
                double[] dArr4 = startPoint;
                UnivariatePointValuePair search = this.line.search(dArr2, copyOf);
                computeObjectiveValue = search.getValue();
                double[][] dArr5 = dArr;
                int i4 = i3;
                dArr2 = newPointAndDirection(dArr2, copyOf, search.getPoint())[z4 ? 1 : 0];
                if (d3 - computeObjectiveValue > d2) {
                    d2 = d3 - computeObjectiveValue;
                    i2 = i4;
                }
                z2 = z4 ? 1 : 0;
                dArr = dArr5;
                i3 = i4 + 1;
                startPoint = dArr4;
            }
            double[] dArr6 = startPoint;
            double[][] dArr7 = dArr;
            boolean z5 = z2;
            boolean z6 = (d - computeObjectiveValue) * 2.0d <= (this.relativeThreshold * (FastMath.abs(d) + FastMath.abs(computeObjectiveValue))) + this.absoluteThreshold ? z3 ? 1 : 0 : z5 ? 1 : 0;
            pointValuePair = new PointValuePair(dArr3, d);
            pointValuePair2 = new PointValuePair(dArr2, computeObjectiveValue);
            if (!z6 && convergenceChecker != null) {
                z6 = convergenceChecker.converged(getIterations(), pointValuePair, pointValuePair2);
            }
            if (z6) {
                break;
            }
            double[] dArr8 = new double[length];
            GoalType goalType2 = goalType;
            double[] dArr9 = new double[length];
            for (int i5 = 0; i5 < length; i5++) {
                dArr8[i5] = dArr2[i5] - dArr3[i5];
                dArr9[i5] = (dArr2[i5] * 2.0d) - dArr3[i5];
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
                    dArr2 = newPointAndDirection[z5 ? 1 : 0];
                    int i6 = length - 1;
                    dArr7[i2] = dArr7[i6];
                    dArr7[i6] = newPointAndDirection[z3 ? 1 : 0];
                }
            }
            z = z3 ? 1 : 0;
            startPoint = dArr6;
            z2 = z5 ? 1 : 0;
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

    private void checkParameters() {
        if (getLowerBound() != null || getUpperBound() != null) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT, new Object[0]);
        }
    }
}
