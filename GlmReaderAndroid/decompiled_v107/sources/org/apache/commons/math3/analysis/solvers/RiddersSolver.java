package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class RiddersSolver extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public RiddersSolver() {
        this(1.0E-6d);
    }

    public RiddersSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    public RiddersSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    protected double doSolve() throws TooManyEvaluationsException, NoBracketingException {
        RiddersSolver riddersSolver = this;
        double min = riddersSolver.getMin();
        double max = riddersSolver.getMax();
        double x1 = min;
        double y1 = riddersSolver.computeObjectiveValue(x1);
        double x2 = max;
        double y2 = riddersSolver.computeObjectiveValue(x2);
        if (y1 == 0.0d) {
            return min;
        }
        if (y2 == 0.0d) {
            return max;
        }
        riddersSolver.verifyBracketing(min, max);
        double absoluteAccuracy = riddersSolver.getAbsoluteAccuracy();
        double functionValueAccuracy = riddersSolver.getFunctionValueAccuracy();
        double relativeAccuracy = riddersSolver.getRelativeAccuracy();
        double oldx = Double.POSITIVE_INFINITY;
        while (true) {
            double min2 = min;
            double min3 = (x1 + x2) * 0.5d;
            double y3 = riddersSolver.computeObjectiveValue(min3);
            if (FastMath.abs(y3) <= functionValueAccuracy) {
                return min3;
            }
            double delta = 1.0d - ((y1 * y2) / (y3 * y3));
            double correction = ((FastMath.signum(y2) * FastMath.signum(y3)) * (min3 - x1)) / FastMath.sqrt(delta);
            double x3 = min3 - correction;
            double y = riddersSolver.computeObjectiveValue(x3);
            double tolerance = FastMath.max(relativeAccuracy * FastMath.abs(x3), absoluteAccuracy);
            if (FastMath.abs(x3 - oldx) <= tolerance || FastMath.abs(y) <= functionValueAccuracy) {
                return x3;
            }
            if (correction > 0.0d) {
                if (FastMath.signum(y1) + FastMath.signum(y) == 0.0d) {
                    x2 = x3;
                    y2 = y;
                } else {
                    x1 = x3;
                    x2 = min3;
                    y1 = y;
                    y2 = y3;
                }
            } else if (FastMath.signum(y2) + FastMath.signum(y) == 0.0d) {
                x1 = x3;
                y1 = y;
            } else {
                x1 = min3;
                x2 = x3;
                y1 = y3;
                y2 = y;
            }
            oldx = x3;
            riddersSolver = this;
            min = min2;
        }
    }
}
