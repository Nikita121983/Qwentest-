package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class BracketingNthOrderBrentSolver extends AbstractUnivariateSolver implements BracketedUnivariateSolver<UnivariateFunction> {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private static final int DEFAULT_MAXIMAL_ORDER = 5;
    private static final int MAXIMAL_AGING = 2;
    private static final double REDUCTION_FACTOR = 0.0625d;
    private AllowedSolution allowed;
    private final int maximalOrder;

    public BracketingNthOrderBrentSolver() {
        this(1.0E-6d, 5);
    }

    public BracketingNthOrderBrentSolver(double absoluteAccuracy, int maximalOrder) throws NumberIsTooSmallException {
        super(absoluteAccuracy);
        if (maximalOrder < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(maximalOrder), 2, true);
        }
        this.maximalOrder = maximalOrder;
        this.allowed = AllowedSolution.ANY_SIDE;
    }

    public BracketingNthOrderBrentSolver(double relativeAccuracy, double absoluteAccuracy, int maximalOrder) throws NumberIsTooSmallException {
        super(relativeAccuracy, absoluteAccuracy);
        if (maximalOrder < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(maximalOrder), 2, true);
        }
        this.maximalOrder = maximalOrder;
        this.allowed = AllowedSolution.ANY_SIDE;
    }

    public BracketingNthOrderBrentSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy, int maximalOrder) throws NumberIsTooSmallException {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
        if (maximalOrder < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(maximalOrder), 2, true);
        }
        this.maximalOrder = maximalOrder;
        this.allowed = AllowedSolution.ANY_SIDE;
    }

    public int getMaximalOrder() {
        return this.maximalOrder;
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    protected double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        int nbPoints;
        int signChangeIndex;
        double absYA;
        double targetY;
        double[] x;
        double absYA2;
        int agingB;
        double[] y;
        int agingA;
        double nextX;
        int end;
        int end2;
        double nextX2;
        double yA;
        int agingA2;
        int agingA3;
        double yA2;
        int i = 1;
        double[] x2 = new double[this.maximalOrder + 1];
        double[] y2 = new double[this.maximalOrder + 1];
        x2[0] = getMin();
        x2[1] = getStartValue();
        x2[2] = getMax();
        verifySequence(x2[0], x2[1], x2[2]);
        y2[1] = computeObjectiveValue(x2[1]);
        if (!Precision.equals(y2[1], 0.0d, 1)) {
            y2[0] = computeObjectiveValue(x2[0]);
            if (Precision.equals(y2[0], 0.0d, 1)) {
                return x2[0];
            }
            if (y2[0] * y2[1] >= 0.0d) {
                y2[2] = computeObjectiveValue(x2[2]);
                if (Precision.equals(y2[2], 0.0d, 1)) {
                    return x2[2];
                }
                if (y2[1] * y2[2] < 0.0d) {
                    nbPoints = 3;
                    signChangeIndex = 2;
                } else {
                    throw new NoBracketingException(x2[0], x2[2], y2[0], y2[2]);
                }
            } else {
                nbPoints = 2;
                signChangeIndex = 1;
            }
            double[] tmpX = new double[x2.length];
            double xA = x2[signChangeIndex - 1];
            double yA3 = y2[signChangeIndex - 1];
            double absYA3 = FastMath.abs(yA3);
            double xB = x2[signChangeIndex];
            double yB = y2[signChangeIndex];
            double absYB = FastMath.abs(yB);
            double absYA4 = xA;
            double absYA5 = absYA3;
            double absYB2 = absYB;
            double yB2 = yB;
            double yB3 = xB;
            double absYA6 = yA3;
            int nbPoints2 = nbPoints;
            int signChangeIndex2 = signChangeIndex;
            int end3 = 0;
            int agingB2 = 0;
            while (true) {
                int i2 = i;
                double[] x3 = x2;
                double xTol = getAbsoluteAccuracy() + (getRelativeAccuracy() * FastMath.max(FastMath.abs(absYA4), FastMath.abs(yB3)));
                if (yB3 - absYA4 <= xTol) {
                    absYA = absYA5;
                    break;
                }
                if (FastMath.max(absYA5, absYB2) < getFunctionValueAccuracy()) {
                    absYA = absYA5;
                    break;
                }
                if (end3 >= 2) {
                    int p = end3 - 2;
                    double weightA = (i2 << p) - 1;
                    double weightB = p + 1;
                    targetY = ((weightA * absYA6) - ((REDUCTION_FACTOR * weightB) * yB2)) / (weightA + weightB);
                } else if (agingB2 >= 2) {
                    int p2 = agingB2 - 2;
                    double weightA2 = p2 + 1;
                    double weightB2 = (i2 << p2) - 1;
                    targetY = ((weightB2 * yB2) - ((REDUCTION_FACTOR * weightA2) * absYA6)) / (weightA2 + weightB2);
                } else {
                    targetY = 0.0d;
                }
                int end4 = 0;
                int end5 = nbPoints2;
                while (true) {
                    x = x3;
                    System.arraycopy(x, end4, tmpX, end4, end5 - end4);
                    absYA2 = absYA5;
                    int start = end4;
                    int start2 = end5;
                    agingB = agingB2;
                    y = y2;
                    agingA = end3;
                    double targetY2 = targetY;
                    nextX = guessX(targetY2, tmpX, y, start, start2);
                    if (nextX <= absYA4 || nextX >= yB3) {
                        targetY = targetY2;
                        if (signChangeIndex2 - start >= start2 - signChangeIndex2) {
                            end4 = start + 1;
                            end = start2;
                        } else {
                            end = start2 - 1;
                            end4 = start;
                        }
                        nextX = Double.NaN;
                    } else {
                        targetY = targetY2;
                        end = start2;
                        end4 = start;
                    }
                    if (!Double.isNaN(nextX) || end - end4 <= i2) {
                        break;
                    }
                    end5 = end;
                    end3 = agingA;
                    y2 = y;
                    absYA5 = absYA2;
                    x3 = x;
                    agingB2 = agingB;
                    i2 = 1;
                }
                if (!Double.isNaN(nextX)) {
                    end2 = end;
                    nextX2 = nextX;
                } else {
                    end4 = signChangeIndex2 - 1;
                    int end6 = signChangeIndex2;
                    end2 = end6;
                    nextX2 = absYA4 + ((yB3 - absYA4) * 0.5d);
                }
                double nextY = computeObjectiveValue(nextX2);
                double nextX3 = nextX2;
                double[] tmpX2 = tmpX;
                if (Precision.equals(nextY, 0.0d, 1)) {
                    return nextX3;
                }
                if (nbPoints2 > 2 && end2 - end4 != nbPoints2) {
                    nbPoints2 = end2 - end4;
                    System.arraycopy(x, end4, x, 0, nbPoints2);
                    System.arraycopy(y, end4, y, 0, nbPoints2);
                    signChangeIndex2 -= end4;
                } else if (nbPoints2 == x.length) {
                    nbPoints2--;
                    if (signChangeIndex2 >= (x.length + 1) / 2) {
                        System.arraycopy(x, 1, x, 0, nbPoints2);
                        System.arraycopy(y, 1, y, 0, nbPoints2);
                        signChangeIndex2--;
                    }
                }
                System.arraycopy(x, signChangeIndex2, x, signChangeIndex2 + 1, nbPoints2 - signChangeIndex2);
                x[signChangeIndex2] = nextX3;
                System.arraycopy(y, signChangeIndex2, y, signChangeIndex2 + 1, nbPoints2 - signChangeIndex2);
                y[signChangeIndex2] = nextY;
                nbPoints2++;
                if (nextY * absYA6 <= 0.0d) {
                    absYB2 = FastMath.abs(nextY);
                    agingA2 = agingA + 1;
                    agingA3 = 0;
                    yA2 = absYA6;
                    yB2 = nextY;
                    yA = absYA2;
                    yB3 = nextX3;
                } else {
                    yA = FastMath.abs(nextY);
                    agingA2 = 0;
                    agingA3 = agingB + 1;
                    signChangeIndex2++;
                    yA2 = nextY;
                    absYA4 = nextX3;
                }
                end3 = agingA2;
                agingB2 = agingA3;
                y2 = y;
                x2 = x;
                tmpX = tmpX2;
                i = 1;
                double d = yA;
                absYA6 = yA2;
                absYA5 = d;
            }
            switch (this.allowed) {
                case ANY_SIDE:
                    return absYA < absYB2 ? absYA4 : yB3;
                case LEFT_SIDE:
                    return absYA4;
                case RIGHT_SIDE:
                    return yB3;
                case BELOW_SIDE:
                    return absYA6 <= 0.0d ? absYA4 : yB3;
                case ABOVE_SIDE:
                    return absYA6 < 0.0d ? yB3 : absYA4;
                default:
                    throw new MathInternalError();
            }
        }
        return x2[1];
    }

    private double guessX(double targetY, double[] x, double[] y, int start, int end) {
        for (int i = start; i < end - 1; i++) {
            int delta = (i + 1) - start;
            for (int j = end - 1; j > i; j--) {
                x[j] = (x[j] - x[j - 1]) / (y[j] - y[j - delta]);
            }
        }
        double x0 = 0.0d;
        for (int j2 = end - 1; j2 >= start; j2--) {
            x0 = x[j2] + ((targetY - y[j2]) * x0);
        }
        return x0;
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver
    public double solve(int maxEval, UnivariateFunction f, double min, double max, AllowedSolution allowedSolution) throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        this.allowed = allowedSolution;
        return super.solve(maxEval, f, min, max);
    }

    @Override // org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver
    public double solve(int maxEval, UnivariateFunction f, double min, double max, double startValue, AllowedSolution allowedSolution) throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        this.allowed = allowedSolution;
        return super.solve(maxEval, (int) f, min, max, startValue);
    }
}
