package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class MullerSolver2 extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public MullerSolver2() {
        this(1.0E-6d);
    }

    public MullerSolver2(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    public MullerSolver2(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    protected double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        double denominator;
        double x;
        double x2;
        double x3;
        MullerSolver2 mullerSolver2 = this;
        double min = mullerSolver2.getMin();
        double max = mullerSolver2.getMax();
        mullerSolver2.verifyInterval(min, max);
        double relativeAccuracy = mullerSolver2.getRelativeAccuracy();
        double absoluteAccuracy = mullerSolver2.getAbsoluteAccuracy();
        double functionValueAccuracy = mullerSolver2.getFunctionValueAccuracy();
        double x0 = min;
        double y0 = mullerSolver2.computeObjectiveValue(x0);
        if (FastMath.abs(y0) < functionValueAccuracy) {
            return x0;
        }
        double x1 = max;
        double y1 = mullerSolver2.computeObjectiveValue(x1);
        if (FastMath.abs(y1) < functionValueAccuracy) {
            return x1;
        }
        if (y0 * y1 > 0.0d) {
            throw new NoBracketingException(x0, x1, y0, y1);
        }
        double x22 = (x0 + x1) * 0.5d;
        double y2 = mullerSolver2.computeObjectiveValue(x22);
        double oldx = Double.POSITIVE_INFINITY;
        while (true) {
            double q = (x22 - x1) / (x1 - x0);
            double a = ((y2 - ((q + 1.0d) * y1)) + (q * y0)) * q;
            double b = ((((q * 2.0d) + 1.0d) * y2) - (((q + 1.0d) * (q + 1.0d)) * y1)) + (q * q * y0);
            double c = (q + 1.0d) * y2;
            double delta = (b * b) - ((4.0d * a) * c);
            if (delta >= 0.0d) {
                double dplus = b + FastMath.sqrt(delta);
                double dminus = b - FastMath.sqrt(delta);
                denominator = FastMath.abs(dplus) > FastMath.abs(dminus) ? dplus : dminus;
            } else {
                denominator = FastMath.sqrt((b * b) - delta);
            }
            if (denominator != 0.0d) {
                double x4 = x22 - (((2.0d * c) * (x22 - x1)) / denominator);
                while (true) {
                    if (x4 != x1 && x4 != x22) {
                        break;
                    }
                    x4 += absoluteAccuracy;
                }
                double d = x4;
                x = x22;
                x2 = d;
            } else {
                double x5 = FastMath.random();
                double x6 = min + (x5 * (max - min));
                oldx = Double.POSITIVE_INFINITY;
                x = x22;
                x2 = x6;
            }
            double y = mullerSolver2.computeObjectiveValue(x2);
            x3 = x2;
            double tolerance = FastMath.max(relativeAccuracy * FastMath.abs(x2), absoluteAccuracy);
            if (FastMath.abs(x3 - oldx) <= tolerance || FastMath.abs(y) <= functionValueAccuracy) {
                break;
            }
            x0 = x1;
            y0 = y1;
            x1 = x;
            y1 = y2;
            y2 = y;
            oldx = x3;
            mullerSolver2 = this;
            x22 = x3;
        }
        return x3;
    }
}
