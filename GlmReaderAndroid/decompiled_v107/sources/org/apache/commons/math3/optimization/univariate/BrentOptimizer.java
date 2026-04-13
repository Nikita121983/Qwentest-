package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

@Deprecated
/* loaded from: classes10.dex */
public class BrentOptimizer extends BaseAbstractUnivariateOptimizer {
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

    @Override // org.apache.commons.math3.optimization.univariate.BaseAbstractUnivariateOptimizer
    protected UnivariatePointValuePair doOptimize() {
        double a;
        double b;
        double x;
        double v;
        double e;
        ConvergenceChecker<UnivariatePointValuePair> checker;
        double u;
        double fu;
        double x2;
        double fu2;
        double q;
        double p;
        double e2;
        boolean isMinim = getGoalType() == GoalType.MINIMIZE;
        double lo = getMin();
        double mid = getStartValue();
        double hi = getMax();
        ConvergenceChecker<UnivariatePointValuePair> checker2 = getConvergenceChecker();
        if (lo < hi) {
            a = lo;
            b = hi;
        } else {
            a = hi;
            b = lo;
        }
        double d = 0.0d;
        double e3 = 0.0d;
        double u2 = mid;
        double lo2 = lo;
        double fx = computeObjectiveValue(u2);
        if (!isMinim) {
            fx = -fx;
        }
        double fv = fx;
        double fw = fx;
        UnivariatePointValuePair current = new UnivariatePointValuePair(u2, isMinim ? fx : -fx);
        UnivariatePointValuePair best = current;
        int iter = 0;
        double fx2 = fx;
        double fu3 = mid;
        double a2 = a;
        double w = mid;
        UnivariatePointValuePair previous = null;
        while (true) {
            double m = (a2 + b) * 0.5d;
            double b2 = b;
            double b3 = this.relativeThreshold;
            double tol1 = (b3 * FastMath.abs(u2)) + this.absoluteThreshold;
            double tol2 = tol1 * 2.0d;
            boolean stop = FastMath.abs(u2 - m) <= tol2 - ((b2 - a2) * 0.5d);
            if (stop) {
                return best(best, best(previous, current, isMinim), isMinim);
            }
            if (FastMath.abs(e3) > tol1) {
                double r = (u2 - w) * (fx2 - fv);
                double q2 = (u2 - fu3) * (fx2 - fw);
                v = fu3;
                double v2 = ((u2 - fu3) * q2) - ((u2 - w) * r);
                double p2 = q2 - r;
                x = u2;
                double x3 = p2 * 2.0d;
                if (x3 > 0.0d) {
                    double p3 = -v2;
                    q = x3;
                    p = p3;
                } else {
                    q = -x3;
                    p = v2;
                }
                double r2 = e3;
                double e4 = d;
                if (p > (a2 - x) * q && p < (b2 - x) * q && FastMath.abs(p) < FastMath.abs(0.5d * q * r2)) {
                    double d2 = p / q;
                    double u3 = x + d2;
                    if (u3 - a2 >= tol2 && b2 - u3 >= tol2) {
                        e3 = e4;
                        d = d2;
                    } else if (x <= m) {
                        e3 = e4;
                        d = tol1;
                    } else {
                        double d3 = -tol1;
                        e3 = e4;
                        d = d3;
                    }
                } else {
                    if (x < m) {
                        e2 = b2 - x;
                    } else {
                        e2 = a2 - x;
                    }
                    e3 = e2;
                    d = GOLDEN_SECTION * e2;
                }
            } else {
                x = u2;
                v = fu3;
                if (x < m) {
                    e = b2 - x;
                } else {
                    e = a2 - x;
                }
                e3 = e;
                d = GOLDEN_SECTION * e;
            }
            double e5 = FastMath.abs(d);
            if (e5 >= tol1) {
                u2 = x + d;
            } else if (d >= 0.0d) {
                u2 = x + tol1;
            } else {
                u2 = x - tol1;
            }
            double fu4 = computeObjectiveValue(u2);
            if (!isMinim) {
                fu4 = -fu4;
            }
            previous = current;
            double lo3 = lo2;
            current = new UnivariatePointValuePair(u2, isMinim ? fu4 : -fu4);
            best = best(best, best(previous, current, isMinim), isMinim);
            if (checker2 != null && checker2.converged(iter, previous, current)) {
                return best;
            }
            if (fu4 <= fx2) {
                if (u2 < x) {
                    b = x;
                } else {
                    double b4 = x;
                    a2 = b4;
                    b = b2;
                }
                double v3 = w;
                fv = fw;
                w = x;
                fw = fx2;
                fx2 = fu4;
                fu3 = v3;
                checker = checker2;
            } else {
                if (u2 < x) {
                    a2 = u2;
                    b = b2;
                } else {
                    b = u2;
                }
                if (fu4 > fw) {
                    checker = checker2;
                    u = u2;
                    x2 = x;
                    if (Precision.equals(w, x2)) {
                        fu = fu4;
                    } else {
                        if (fu4 > fv) {
                            fu2 = fu4;
                            double v4 = v;
                            if (!Precision.equals(v4, x2) && !Precision.equals(v4, w)) {
                                fu3 = v4;
                                u2 = x2;
                            }
                        } else {
                            fu2 = fu4;
                        }
                        fv = fu2;
                        fu3 = u;
                        u2 = x2;
                    }
                } else {
                    checker = checker2;
                    u = u2;
                    fu = fu4;
                    x2 = x;
                }
                double v5 = w;
                fv = fw;
                w = u;
                fw = fu;
                fu3 = v5;
                u2 = x2;
            }
            iter++;
            checker2 = checker;
            lo2 = lo3;
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
