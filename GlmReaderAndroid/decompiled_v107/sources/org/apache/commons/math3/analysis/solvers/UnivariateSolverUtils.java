package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class UnivariateSolverUtils {
    private UnivariateSolverUtils() {
    }

    public static double solve(UnivariateFunction function, double x0, double x1) throws NullArgumentException, NoBracketingException {
        if (function == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
        }
        UnivariateSolver solver = new BrentSolver();
        return solver.solve(Integer.MAX_VALUE, function, x0, x1);
    }

    public static double solve(UnivariateFunction function, double x0, double x1, double absoluteAccuracy) throws NullArgumentException, NoBracketingException {
        if (function == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
        }
        UnivariateSolver solver = new BrentSolver(absoluteAccuracy);
        return solver.solve(Integer.MAX_VALUE, function, x0, x1);
    }

    public static double forceSide(int maxEval, UnivariateFunction f, BracketedUnivariateSolver<UnivariateFunction> bracketing, double baseRoot, double min, double max, AllowedSolution allowedSolution) throws NoBracketingException {
        AllowedSolution allowedSolution2 = allowedSolution;
        if (allowedSolution2 == AllowedSolution.ANY_SIDE) {
            return baseRoot;
        }
        double step = FastMath.max(bracketing.getAbsoluteAccuracy(), FastMath.abs(bracketing.getRelativeAccuracy() * baseRoot));
        double xLo = FastMath.max(min, baseRoot - step);
        double fLo = f.value(xLo);
        double xHi = FastMath.min(max, baseRoot + step);
        double fHi = f.value(xHi);
        double xLo2 = xLo;
        double fLo2 = fLo;
        double xHi2 = xHi;
        double fHi2 = fHi;
        int remainingEval = maxEval - 2;
        while (remainingEval > 0) {
            if ((fLo2 >= 0.0d && fHi2 <= 0.0d) || (fLo2 <= 0.0d && fHi2 >= 0.0d)) {
                return bracketing.solve(remainingEval, f, xLo2, xHi2, baseRoot, allowedSolution2);
            }
            boolean changeLo = false;
            boolean changeHi = false;
            if (fLo2 < fHi2) {
                if (fLo2 >= 0.0d) {
                    changeLo = true;
                } else {
                    changeHi = true;
                }
            } else if (fLo2 <= fHi2) {
                changeLo = true;
                changeHi = true;
            } else if (fLo2 <= 0.0d) {
                changeLo = true;
            } else {
                changeHi = true;
            }
            if (changeLo) {
                double xLo3 = FastMath.max(min, xLo2 - step);
                double fLo3 = f.value(xLo3);
                remainingEval--;
                xLo2 = xLo3;
                fLo2 = fLo3;
            }
            if (changeHi) {
                double xHi3 = FastMath.min(max, xHi2 + step);
                double fHi3 = f.value(xHi3);
                remainingEval--;
                xHi2 = xHi3;
                fHi2 = fHi3;
            }
            allowedSolution2 = allowedSolution;
        }
        throw new NoBracketingException(LocalizedFormats.FAILED_BRACKETING, xLo2, xHi2, fLo2, fHi2, Integer.valueOf(maxEval - remainingEval), Integer.valueOf(maxEval), Double.valueOf(baseRoot), Double.valueOf(min), Double.valueOf(max));
    }

    public static double[] bracket(UnivariateFunction function, double initial, double lowerBound, double upperBound) throws NullArgumentException, NotStrictlyPositiveException, NoBracketingException {
        return bracket(function, initial, lowerBound, upperBound, 1.0d, 1.0d, Integer.MAX_VALUE);
    }

    public static double[] bracket(UnivariateFunction function, double initial, double lowerBound, double upperBound, int maximumIterations) throws NullArgumentException, NotStrictlyPositiveException, NoBracketingException {
        return bracket(function, initial, lowerBound, upperBound, 1.0d, 1.0d, maximumIterations);
    }

    public static double[] bracket(UnivariateFunction function, double initial, double lowerBound, double upperBound, double q, double r, int maximumIterations) throws NoBracketingException {
        if (function == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
        }
        double d = 0.0d;
        if (q <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(q));
        }
        if (maximumIterations <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.INVALID_MAX_ITERATIONS, Integer.valueOf(maximumIterations));
        }
        double d2 = upperBound;
        verifySequence(lowerBound, initial, d2);
        double delta = 0.0d;
        double a = initial;
        double b = initial;
        double fa = Double.NaN;
        double fb = Double.NaN;
        int numIterations = 0;
        while (numIterations < maximumIterations && (a > lowerBound || b < d2)) {
            double previousA = a;
            double previousFa = fa;
            double previousB = b;
            double previousFb = fb;
            delta = (r * delta) + q;
            double d3 = d;
            double a2 = FastMath.max(initial - delta, lowerBound);
            double b2 = FastMath.min(initial + delta, d2);
            fa = function.value(a2);
            fb = function.value(b2);
            if (numIterations == 0) {
                if (fa * fb <= d3) {
                    return new double[]{a2, b2};
                }
            } else {
                if (fa * previousFa <= d3) {
                    return new double[]{a2, previousA};
                }
                if (fb * previousFb <= d3) {
                    return new double[]{previousB, b2};
                }
            }
            numIterations++;
            a = a2;
            b = b2;
            d = d3;
            d2 = upperBound;
        }
        throw new NoBracketingException(a, b, fa, fb);
    }

    public static double midpoint(double a, double b) {
        return (a + b) * 0.5d;
    }

    public static boolean isBracketing(UnivariateFunction function, double lower, double upper) throws NullArgumentException {
        if (function == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
        }
        double fLo = function.value(lower);
        double fHi = function.value(upper);
        return (fLo >= 0.0d && fHi <= 0.0d) || (fLo <= 0.0d && fHi >= 0.0d);
    }

    public static boolean isSequence(double start, double mid, double end) {
        return start < mid && mid < end;
    }

    public static void verifyInterval(double lower, double upper) throws NumberIsTooLargeException {
        if (lower >= upper) {
            throw new NumberIsTooLargeException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, Double.valueOf(lower), Double.valueOf(upper), false);
        }
    }

    public static void verifySequence(double lower, double initial, double upper) throws NumberIsTooLargeException {
        verifyInterval(lower, initial);
        verifyInterval(initial, upper);
    }

    public static void verifyBracketing(UnivariateFunction function, double lower, double upper) throws NullArgumentException, NoBracketingException {
        if (function == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
        }
        verifyInterval(lower, upper);
        if (!isBracketing(function, lower, upper)) {
            throw new NoBracketingException(lower, upper, function.value(lower), function.value(upper));
        }
    }
}
