package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class SecantSolver extends AbstractUnivariateSolver {
    protected static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public SecantSolver() {
        super(1.0E-6d);
    }

    public SecantSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
    }

    public SecantSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    protected final double doSolve() throws TooManyEvaluationsException, NoBracketingException {
        SecantSolver secantSolver = this;
        double x0 = secantSolver.getMin();
        double x1 = secantSolver.getMax();
        double f0 = secantSolver.computeObjectiveValue(x0);
        double f1 = secantSolver.computeObjectiveValue(x1);
        double d = 0.0d;
        if (f0 == 0.0d) {
            return x0;
        }
        if (f1 == 0.0d) {
            return x1;
        }
        secantSolver.verifyBracketing(x0, x1);
        double ftol = secantSolver.getFunctionValueAccuracy();
        double atol = secantSolver.getAbsoluteAccuracy();
        double rtol = secantSolver.getRelativeAccuracy();
        while (true) {
            double d2 = d;
            double x = x1 - (((x1 - x0) * f1) / (f1 - f0));
            double fx = secantSolver.computeObjectiveValue(x);
            if (fx == d2) {
                return x;
            }
            double x02 = x1;
            f0 = f1;
            x1 = x;
            f1 = fx;
            if (FastMath.abs(f1) <= ftol) {
                return x1;
            }
            if (FastMath.abs(x1 - x02) < FastMath.max(rtol * FastMath.abs(x1), atol)) {
                return x1;
            }
            secantSolver = this;
            d = d2;
            x0 = x02;
        }
    }
}
