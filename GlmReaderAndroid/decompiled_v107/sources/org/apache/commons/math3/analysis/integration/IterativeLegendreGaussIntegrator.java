package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.gauss.GaussIntegrator;
import org.apache.commons.math3.analysis.integration.gauss.GaussIntegratorFactory;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class IterativeLegendreGaussIntegrator extends BaseAbstractUnivariateIntegrator {
    private static final GaussIntegratorFactory FACTORY = new GaussIntegratorFactory();
    private final int numberOfPoints;

    public IterativeLegendreGaussIntegrator(int n, double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException {
        super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
        if (n <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(n));
        }
        this.numberOfPoints = n;
    }

    public IterativeLegendreGaussIntegrator(int n, double relativeAccuracy, double absoluteAccuracy) throws NotStrictlyPositiveException {
        this(n, relativeAccuracy, absoluteAccuracy, 3, Integer.MAX_VALUE);
    }

    public IterativeLegendreGaussIntegrator(int n, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException {
        this(n, 1.0E-6d, 1.0E-15d, minimalIterationCount, maximalIterationCount);
    }

    @Override // org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator
    protected double doIntegrate() throws MathIllegalArgumentException, TooManyEvaluationsException, MaxCountExceededException {
        int i = 1;
        double oldt = stage(1);
        int n = 2;
        while (true) {
            double t = stage(n);
            double delta = FastMath.abs(t - oldt);
            double limit = FastMath.max(getAbsoluteAccuracy(), getRelativeAccuracy() * (FastMath.abs(oldt) + FastMath.abs(t)) * 0.5d);
            if (getIterations() + i < getMinimalIterationCount() || delta > limit) {
                double ratio = FastMath.min(4.0d, FastMath.pow(delta / limit, 0.5d / this.numberOfPoints));
                n = FastMath.max((int) (n * ratio), n + 1);
                incrementCount();
                oldt = t;
                i = 1;
            } else {
                return t;
            }
        }
    }

    private double stage(int n) throws TooManyEvaluationsException {
        UnivariateFunction f = new UnivariateFunction() { // from class: org.apache.commons.math3.analysis.integration.IterativeLegendreGaussIntegrator.1
            @Override // org.apache.commons.math3.analysis.UnivariateFunction
            public double value(double x) throws MathIllegalArgumentException, TooManyEvaluationsException {
                return IterativeLegendreGaussIntegrator.this.computeObjectiveValue(x);
            }
        };
        double min = getMin();
        double max = getMax();
        double step = (max - min) / n;
        double sum = 0.0d;
        for (int i = 0; i < n; i++) {
            double a = min + (i * step);
            double b = a + step;
            GaussIntegrator g = FACTORY.legendreHighPrecision(this.numberOfPoints, a, b);
            sum += g.integrate(f);
        }
        return sum;
    }
}
