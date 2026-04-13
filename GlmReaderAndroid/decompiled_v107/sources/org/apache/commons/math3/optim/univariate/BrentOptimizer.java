package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class BrentOptimizer extends UnivariateOptimizer {
    private static final double GOLDEN_SECTION = (3.0d - FastMath.sqrt(5.0d)) * 0.5d;
    private static final double MIN_RELATIVE_TOLERANCE = FastMath.ulp(1.0d) * 2.0d;
    private final double absoluteThreshold;
    private final double relativeThreshold;

    public BrentOptimizer(double rel, double abs, ConvergenceChecker<UnivariatePointValuePair> checker) {
        super(checker);
        if (rel < MIN_RELATIVE_TOLERANCE) {
            throw new NumberIsTooSmallException(Double.valueOf(rel), Double.valueOf(MIN_RELATIVE_TOLERANCE), true);
        }
        if (abs <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(abs));
        }
        this.relativeThreshold = rel;
        this.absoluteThreshold = abs;
    }

    public BrentOptimizer(double rel, double abs) {
        this(rel, abs, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.math3.optim.BaseOptimizer
    public UnivariatePointValuePair doOptimize() {
        double a;
        double b;
        double v;
        double lo;
        double e;
        double b2;
        ConvergenceChecker<UnivariatePointValuePair> checker;
        double x;
        double a2;
        double q;
        double p;
        double e2;
        boolean isMinim = getGoalType() == GoalType.MINIMIZE;
        double lo2 = getMin();
        double mid = getStartValue();
        double hi = getMax();
        ConvergenceChecker<UnivariatePointValuePair> checker2 = getConvergenceChecker();
        if (lo2 < hi) {
            a = lo2;
            b = hi;
        } else {
            a = hi;
            b = lo2;
        }
        double d = 0.0d;
        double e3 = 0.0d;
        double a3 = mid;
        double lo3 = lo2;
        double fx = computeObjectiveValue(a3);
        if (!isMinim) {
            fx = -fx;
        }
        double fw = fx;
        double x2 = fx;
        UnivariatePointValuePair current = new UnivariatePointValuePair(a3, isMinim ? fx : -fx);
        UnivariatePointValuePair best = current;
        double u = mid;
        double fx2 = fx;
        double w = mid;
        double fv = a;
        UnivariatePointValuePair previous = null;
        while (true) {
            double m = (fv + b) * 0.5d;
            double b3 = b;
            double tol1 = (this.relativeThreshold * FastMath.abs(a3)) + this.absoluteThreshold;
            double tol2 = tol1 * 2.0d;
            boolean stop = FastMath.abs(a3 - m) <= tol2 - ((b3 - fv) * 0.5d);
            if (stop) {
                return best(best, best(previous, current, isMinim), isMinim);
            }
            if (FastMath.abs(e3) > tol1) {
                double r = (a3 - w) * (fx2 - fw);
                double q2 = (a3 - u) * (fx2 - x2);
                lo = lo3;
                double p2 = ((a3 - u) * q2) - ((a3 - w) * r);
                v = u;
                double v2 = (q2 - r) * 2.0d;
                if (v2 > 0.0d) {
                    q = v2;
                    p = -p2;
                } else {
                    q = -v2;
                    p = p2;
                }
                double r2 = e3;
                double e4 = d;
                if (p > (fv - a3) * q && p < (b3 - a3) * q && FastMath.abs(p) < FastMath.abs(0.5d * q * r2)) {
                    double d2 = p / q;
                    double u2 = a3 + d2;
                    if (u2 - fv >= tol2 && b3 - u2 >= tol2) {
                        e3 = e4;
                        d = d2;
                    } else if (a3 <= m) {
                        e3 = e4;
                        d = tol1;
                    } else {
                        double d3 = -tol1;
                        e3 = e4;
                        d = d3;
                    }
                } else {
                    if (a3 < m) {
                        e2 = b3 - a3;
                    } else {
                        e2 = fv - a3;
                    }
                    e3 = e2;
                    d = GOLDEN_SECTION * e2;
                }
            } else {
                v = u;
                lo = lo3;
                if (a3 < m) {
                    e = b3 - a3;
                } else {
                    e = fv - a3;
                }
                e3 = e;
                d = GOLDEN_SECTION * e;
            }
            double e5 = FastMath.abs(d);
            if (e5 >= tol1) {
                u = a3 + d;
            } else if (d >= 0.0d) {
                u = a3 + tol1;
            } else {
                u = a3 - tol1;
            }
            double fu = computeObjectiveValue(u);
            if (!isMinim) {
                fu = -fu;
            }
            previous = current;
            double x3 = a3;
            double x4 = isMinim ? fu : -fu;
            current = new UnivariatePointValuePair(u, x4);
            best = best(best, best(previous, current, isMinim), isMinim);
            if (checker2 != null && checker2.converged(getIterations(), previous, current)) {
                return best;
            }
            if (fu <= fx2) {
                if (u < x3) {
                    b3 = x3;
                    a2 = fv;
                } else {
                    a2 = x3;
                }
                double v3 = w;
                double fv2 = x2;
                w = x3;
                double fw2 = fx2;
                double fw3 = u;
                fx2 = fu;
                fv = a2;
                a3 = fw3;
                x2 = fw2;
                fw = fv2;
                checker = checker2;
                u = v3;
            } else {
                if (u < x3) {
                    fv = u;
                    b2 = b3;
                } else {
                    b2 = u;
                }
                if (fu > x2) {
                    x = x3;
                    if (Precision.equals(w, x)) {
                        checker = checker2;
                        b3 = b2;
                    } else {
                        if (fu > fw) {
                            checker = checker2;
                            b3 = b2;
                            double v4 = v;
                            if (!Precision.equals(v4, x) && !Precision.equals(v4, w)) {
                                u = v4;
                                a3 = x;
                            }
                        } else {
                            checker = checker2;
                            b3 = b2;
                        }
                        fw = fu;
                        a3 = x;
                    }
                } else {
                    checker = checker2;
                    b3 = b2;
                    x = x3;
                }
                double v5 = w;
                fw = x2;
                double w2 = u;
                x2 = fu;
                u = v5;
                w = w2;
                a3 = x;
            }
            b = b3;
            incrementIterationCount();
            checker2 = checker;
            lo3 = lo;
        }
    }

    private UnivariatePointValuePair best(UnivariatePointValuePair a, UnivariatePointValuePair b, boolean isMinim) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        return isMinim ? a.getValue() <= b.getValue() ? a : b : a.getValue() >= b.getValue() ? a : b;
    }
}
