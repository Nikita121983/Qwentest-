package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class LaguerreSolver extends AbstractPolynomialSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private final ComplexSolver complexSolver;

    public LaguerreSolver() {
        this(1.0E-6d);
    }

    public LaguerreSolver(double absoluteAccuracy) {
        super(absoluteAccuracy);
        this.complexSolver = new ComplexSolver();
    }

    public LaguerreSolver(double relativeAccuracy, double absoluteAccuracy) {
        super(relativeAccuracy, absoluteAccuracy);
        this.complexSolver = new ComplexSolver();
    }

    public LaguerreSolver(double relativeAccuracy, double absoluteAccuracy, double functionValueAccuracy) {
        super(relativeAccuracy, absoluteAccuracy, functionValueAccuracy);
        this.complexSolver = new ComplexSolver();
    }

    @Override // org.apache.commons.math3.analysis.solvers.BaseAbstractUnivariateSolver
    public double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        double min = getMin();
        double max = getMax();
        double initial = getStartValue();
        double functionValueAccuracy = getFunctionValueAccuracy();
        verifySequence(min, initial, max);
        double yInitial = computeObjectiveValue(initial);
        if (FastMath.abs(yInitial) > functionValueAccuracy) {
            double yMin = computeObjectiveValue(min);
            if (FastMath.abs(yMin) <= functionValueAccuracy) {
                return min;
            }
            if (yInitial * yMin >= 0.0d) {
                double yMax = computeObjectiveValue(max);
                if (FastMath.abs(yMax) <= functionValueAccuracy) {
                    return max;
                }
                if (yInitial * yMax < 0.0d) {
                    return laguerre(initial, max, yInitial, yMax);
                }
                throw new NoBracketingException(min, max, yMin, yMax);
            }
            return laguerre(min, initial, yMin, yInitial);
        }
        return initial;
    }

    @Deprecated
    public double laguerre(double lo, double hi, double fLo, double fHi) {
        Complex[] c = ComplexUtils.convertToComplex(getCoefficients());
        Complex initial = new Complex((lo + hi) * 0.5d, 0.0d);
        Complex z = this.complexSolver.solve(c, initial);
        if (this.complexSolver.isRoot(lo, hi, z)) {
            return z.getReal();
        }
        Complex[] root = this.complexSolver.solveAll(c, initial);
        for (int i = 0; i < root.length; i++) {
            if (this.complexSolver.isRoot(lo, hi, root[i])) {
                double r = root[i].getReal();
                return r;
            }
        }
        return Double.NaN;
    }

    public Complex[] solveAllComplex(double[] coefficients, double initial) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        return solveAllComplex(coefficients, initial, Integer.MAX_VALUE);
    }

    public Complex[] solveAllComplex(double[] coefficients, double initial, int maxEval) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        setup(maxEval, new PolynomialFunction(coefficients), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, initial);
        return this.complexSolver.solveAll(ComplexUtils.convertToComplex(coefficients), new Complex(initial, 0.0d));
    }

    public Complex solveComplex(double[] coefficients, double initial) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        return solveComplex(coefficients, initial, Integer.MAX_VALUE);
    }

    public Complex solveComplex(double[] coefficients, double initial, int maxEval) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
        setup(maxEval, new PolynomialFunction(coefficients), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, initial);
        return this.complexSolver.solve(ComplexUtils.convertToComplex(coefficients), new Complex(initial, 0.0d));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class ComplexSolver {
        private ComplexSolver() {
        }

        public boolean isRoot(double min, double max, Complex z) {
            if (!LaguerreSolver.this.isSequence(min, z.getReal(), max)) {
                return false;
            }
            double tolerance = FastMath.max(LaguerreSolver.this.getRelativeAccuracy() * z.abs(), LaguerreSolver.this.getAbsoluteAccuracy());
            return FastMath.abs(z.getImaginary()) <= tolerance || z.abs() <= LaguerreSolver.this.getFunctionValueAccuracy();
        }

        public Complex[] solveAll(Complex[] coefficients, Complex initial) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
            if (coefficients == null) {
                throw new NullArgumentException();
            }
            int n = coefficients.length - 1;
            if (n == 0) {
                throw new NoDataException(LocalizedFormats.POLYNOMIAL);
            }
            Complex[] c = new Complex[n + 1];
            for (int i = 0; i <= n; i++) {
                c[i] = coefficients[i];
            }
            Complex[] root = new Complex[n];
            for (int i2 = 0; i2 < n; i2++) {
                Complex[] subarray = new Complex[(n - i2) + 1];
                System.arraycopy(c, 0, subarray, 0, subarray.length);
                root[i2] = solve(subarray, initial);
                Complex newc = c[n - i2];
                for (int j = (n - i2) - 1; j >= 0; j--) {
                    Complex oldc = c[j];
                    c[j] = newc;
                    newc = oldc.add(newc.multiply(root[i2]));
                }
            }
            return root;
        }

        public Complex solve(Complex[] coefficients, Complex initial) throws NullArgumentException, NoDataException, TooManyEvaluationsException {
            long j;
            Complex oldz;
            Complex[] complexArr = coefficients;
            if (complexArr == null) {
                throw new NullArgumentException();
            }
            int n = complexArr.length - 1;
            if (n == 0) {
                throw new NoDataException(LocalizedFormats.POLYNOMIAL);
            }
            double absoluteAccuracy = LaguerreSolver.this.getAbsoluteAccuracy();
            double relativeAccuracy = LaguerreSolver.this.getRelativeAccuracy();
            double functionValueAccuracy = LaguerreSolver.this.getFunctionValueAccuracy();
            Complex nC = new Complex(n, 0.0d);
            Complex n1C = new Complex(n - 1, 0.0d);
            Complex z = initial;
            Complex oldz2 = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
            while (true) {
                Complex pv = complexArr[n];
                Complex dv = Complex.ZERO;
                Complex d2v = Complex.ZERO;
                int j2 = n - 1;
                Complex pv2 = pv;
                Complex dv2 = dv;
                Complex d2v2 = d2v;
                while (j2 >= 0) {
                    d2v2 = dv2.add(z.multiply(d2v2));
                    dv2 = pv2.add(z.multiply(dv2));
                    pv2 = coefficients[j2].add(z.multiply(pv2));
                    j2--;
                    n = n;
                }
                int n2 = n;
                double relativeAccuracy2 = relativeAccuracy;
                double functionValueAccuracy2 = functionValueAccuracy;
                Complex d2v3 = d2v2.multiply(new Complex(2.0d, 0.0d));
                double tolerance = FastMath.max(z.abs() * relativeAccuracy2, absoluteAccuracy);
                if (z.subtract(oldz2).abs() <= tolerance) {
                    return z;
                }
                if (pv2.abs() <= functionValueAccuracy2) {
                    return z;
                }
                Complex G = dv2.divide(pv2);
                Complex G2 = G.multiply(G);
                Complex H = G2.subtract(d2v3.divide(pv2));
                Complex delta = n1C.multiply(nC.multiply(H).subtract(G2));
                Complex deltaSqrt = delta.sqrt();
                Complex dplus = G.add(deltaSqrt);
                Complex dminus = G.subtract(deltaSqrt);
                Complex denominator = dplus.abs() > dminus.abs() ? dplus : dminus;
                if (denominator.equals(new Complex(0.0d, 0.0d))) {
                    z = z.add(new Complex(absoluteAccuracy, absoluteAccuracy));
                    j = 9218868437227405312L;
                    oldz = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
                } else {
                    j = 9218868437227405312L;
                    oldz = z;
                    Complex oldz3 = nC.divide(denominator);
                    z = z.subtract(oldz3);
                }
                oldz2 = oldz;
                LaguerreSolver.this.incrementEvaluationCount();
                n = n2;
                relativeAccuracy = relativeAccuracy2;
                functionValueAccuracy = functionValueAccuracy2;
                complexArr = coefficients;
            }
        }
    }
}
