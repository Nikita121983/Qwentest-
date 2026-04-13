package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public abstract class BaseSecantSolver extends AbstractUnivariateSolver implements BracketedUnivariateSolver<UnivariateFunction> {
    protected static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private AllowedSolution allowed;
    private final Method method;

    /* loaded from: classes10.dex */
    protected enum Method {
        REGULA_FALSI,
        ILLINOIS,
        PEGASUS
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseSecantSolver(double absoluteAccuracy, Method method) {
        super(absoluteAccuracy);
        this.allowed = AllowedSolution.ANY_SIDE;
        this.method = method;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseSecantSolver(double relativeAccuracy, double absoluteAccuracy, Method method) {
        super(relativeAccuracy, absoluteAccuracy);
        this.allowed = AllowedSolution.ANY_SIDE;
        this.method = method;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseSecantSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy, Method method) {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
        this.allowed = AllowedSolution.ANY_SIDE;
        this.method = method;
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver
    public double solve(int maxEval, UnivariateFunction f, double min, double max, AllowedSolution allowedSolution) {
        return solve(maxEval, f, min, max, min + ((max - min) * 0.5d), allowedSolution);
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver
    public double solve(int maxEval, UnivariateFunction f, double min, double max, double startValue, AllowedSolution allowedSolution) {
        this.allowed = allowedSolution;
        return super.solve(maxEval, (int) f, min, max, startValue);
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver, org.apache.commons.math3.analysis.solvers.BaseUnivariateSolver
    public double solve(int maxEval, UnivariateFunction f, double min, double max, double startValue) {
        return solve(maxEval, f, min, max, startValue, AllowedSolution.ANY_SIDE);
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    protected final double doSolve() throws ConvergenceException {
        double x0;
        double x02;
        double x03 = getMin();
        double x1 = getMax();
        double f0 = computeObjectiveValue(x03);
        double f1 = computeObjectiveValue(x1);
        double x = 0.0d;
        if (f0 == 0.0d) {
            return x03;
        }
        if (f1 == 0.0d) {
            return x1;
        }
        verifyBracketing(x03, x1);
        double ftol = getFunctionValueAccuracy();
        double atol = getAbsoluteAccuracy();
        double rtol = getRelativeAccuracy();
        boolean inverted = false;
        while (true) {
            double d = x;
            double x2 = x1 - (((x1 - x03) * f1) / (f1 - f0));
            double fx = computeObjectiveValue(x2);
            if (fx == d) {
                return x2;
            }
            if (f1 * fx < d) {
                x0 = x1;
                f0 = f1;
                inverted = !inverted;
            } else {
                double x04 = x03;
                switch (this.method) {
                    case ILLINOIS:
                        f0 *= 0.5d;
                        x0 = x04;
                        break;
                    case PEGASUS:
                        f0 *= f1 / (f1 + fx);
                        x0 = x04;
                        break;
                    case REGULA_FALSI:
                        if (x2 == x1) {
                            throw new ConvergenceException();
                        }
                        x0 = x04;
                        break;
                    default:
                        throw new MathInternalError();
                }
            }
            x1 = x2;
            f1 = fx;
            if (FastMath.abs(f1) > ftol) {
                x02 = x0;
            } else {
                x02 = x0;
                switch (this.allowed) {
                    case ANY_SIDE:
                        return x1;
                    case LEFT_SIDE:
                        if (inverted) {
                            return x1;
                        }
                        break;
                    case RIGHT_SIDE:
                        if (!inverted) {
                            return x1;
                        }
                        break;
                    case BELOW_SIDE:
                        if (f1 <= d) {
                            return x1;
                        }
                        break;
                    case ABOVE_SIDE:
                        if (f1 >= d) {
                            return x1;
                        }
                        break;
                    default:
                        throw new MathInternalError();
                }
            }
            if (FastMath.abs(x1 - x02) < FastMath.max(rtol * FastMath.abs(x1), atol)) {
                switch (this.allowed) {
                    case ANY_SIDE:
                        return x1;
                    case LEFT_SIDE:
                        return inverted ? x1 : x02;
                    case RIGHT_SIDE:
                        return inverted ? x02 : x1;
                    case BELOW_SIDE:
                        return f1 <= d ? x1 : x02;
                    case ABOVE_SIDE:
                        return f1 >= d ? x1 : x02;
                    default:
                        throw new MathInternalError();
                }
            }
            x = d;
            x03 = x02;
        }
    }
}
