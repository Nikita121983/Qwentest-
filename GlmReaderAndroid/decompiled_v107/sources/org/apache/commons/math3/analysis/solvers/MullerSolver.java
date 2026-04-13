package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class MullerSolver extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public MullerSolver() {
        this(1.0E-6d);
    }

    public MullerSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    public MullerSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    protected double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        double min = getMin();
        double max = getMax();
        double initial = getStartValue();
        double functionValueAccuracy = getFunctionValueAccuracy();
        verifySequence(min, initial, max);
        double fMin = computeObjectiveValue(min);
        if (FastMath.abs(fMin) < functionValueAccuracy) {
            return min;
        }
        double fMax = computeObjectiveValue(max);
        if (FastMath.abs(fMax) < functionValueAccuracy) {
            return max;
        }
        double fInitial = computeObjectiveValue(initial);
        if (FastMath.abs(fInitial) < functionValueAccuracy) {
            return initial;
        }
        verifyBracketing(min, max);
        if (!isBracketing(min, initial)) {
            return solve(initial, max, fInitial, fMax);
        }
        return solve(min, initial, fMin, fInitial);
    }

    private double solve(double min, double max, double fMin, double fMax) throws TooManyEvaluationsException {
        double x;
        double relativeAccuracy = getRelativeAccuracy();
        double absoluteAccuracy = getAbsoluteAccuracy();
        double functionValueAccuracy = getFunctionValueAccuracy();
        double x2 = max;
        double x0 = (min + x2) * 0.5d;
        double y1 = computeObjectiveValue(x0);
        double x1 = x0;
        double xm = min;
        double y2 = fMax;
        double oldx = Double.POSITIVE_INFINITY;
        double y12 = y1;
        double y13 = fMin;
        while (true) {
            double y0 = y12 - y13;
            double d01 = y0 / (x1 - xm);
            double d12 = (y2 - y12) / (x2 - x1);
            double d012 = (d12 - d01) / (x2 - xm);
            double c1 = d01 + ((x1 - xm) * d012);
            double delta = (c1 * c1) - ((4.0d * y12) * d012);
            double xplus = x1 + ((y12 * (-2.0d)) / (c1 + FastMath.sqrt(delta)));
            double xminus = x1 + (((-2.0d) * y12) / (c1 - FastMath.sqrt(delta)));
            x = isSequence(xm, xplus, x2) ? xplus : xminus;
            double x02 = xm;
            double y = computeObjectiveValue(x);
            if (FastMath.abs(x - oldx) > FastMath.max(relativeAccuracy * FastMath.abs(x), absoluteAccuracy) && FastMath.abs(y) > functionValueAccuracy) {
                boolean bisect = (x < x1 && x1 - x02 > (x2 - x02) * 0.95d) || (x > x1 && x2 - x1 > (x2 - x02) * 0.95d) || x == x1;
                if (!bisect) {
                    double x03 = x < x1 ? x02 : x1;
                    double y02 = x < x1 ? y13 : y12;
                    double x22 = x > x1 ? x2 : x1;
                    double y22 = x > x1 ? y2 : y12;
                    x1 = x;
                    oldx = x;
                    xm = x03;
                    y12 = y;
                    y2 = y22;
                    x2 = x22;
                    y13 = y02;
                } else {
                    double tolerance = (x02 + x2) * 0.5d;
                    double ym = computeObjectiveValue(tolerance);
                    if (FastMath.signum(y13) + FastMath.signum(ym) == 0.0d) {
                        x2 = tolerance;
                        y2 = ym;
                    } else {
                        x02 = tolerance;
                        y13 = ym;
                    }
                    x1 = (x02 + x2) * 0.5d;
                    y12 = computeObjectiveValue(x1);
                    oldx = Double.POSITIVE_INFINITY;
                    xm = x02;
                }
            }
        }
        return x;
    }
}
