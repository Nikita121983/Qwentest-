package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class MidPointIntegrator extends BaseAbstractUnivariateIntegrator {
    public static final int MIDPOINT_MAX_ITERATIONS_COUNT = 64;

    public MidPointIntegrator(double relativeAccuracy, double absoluteAccuracy, int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(relativeAccuracy, absoluteAccuracy, minimalIterationCount, maximalIterationCount);
        if (maximalIterationCount > 64) {
            throw new NumberIsTooLargeException(Integer.valueOf(maximalIterationCount), 64, false);
        }
    }

    public MidPointIntegrator(int minimalIterationCount, int maximalIterationCount) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(minimalIterationCount, maximalIterationCount);
        if (maximalIterationCount > 64) {
            throw new NumberIsTooLargeException(Integer.valueOf(maximalIterationCount), 64, false);
        }
    }

    public MidPointIntegrator() {
        super(3, 64);
    }

    private double stage(int n, double previousStageResult, double min, double diffMaxMin) throws TooManyEvaluationsException {
        long np = 1 << (n - 1);
        double sum = 0.0d;
        double spacing = diffMaxMin / np;
        double x = min + (spacing * 0.5d);
        for (long i = 0; i < np; i++) {
            sum += computeObjectiveValue(x);
            x += spacing;
        }
        return (previousStageResult + (sum * spacing)) * 0.5d;
    }

    @Override // org.apache.commons.math3.analysis.integration.BaseAbstractUnivariateIntegrator
    protected double doIntegrate() throws MathIllegalArgumentException, TooManyEvaluationsException, MaxCountExceededException {
        double t;
        double min = getMin();
        double diff = getMax() - min;
        double midPoint = min + (diff * 0.5d);
        MidPointIntegrator midPointIntegrator = this;
        double oldt = midPointIntegrator.computeObjectiveValue(midPoint) * diff;
        while (true) {
            midPointIntegrator.incrementCount();
            int i = midPointIntegrator.getIterations();
            t = midPointIntegrator.stage(i, oldt, min, diff);
            if (i >= getMinimalIterationCount()) {
                double delta = FastMath.abs(t - oldt);
                double rLimit = getRelativeAccuracy() * (FastMath.abs(oldt) + FastMath.abs(t)) * 0.5d;
                if (delta <= rLimit || delta <= getAbsoluteAccuracy()) {
                    break;
                }
            }
            oldt = t;
            midPointIntegrator = this;
        }
        return t;
    }
}
