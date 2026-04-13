package org.apache.commons.math3.optimization.direct;

import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.optimization.PointValuePair;

@Deprecated
/* loaded from: classes10.dex */
public class NelderMeadSimplex extends AbstractSimplex {
    private static final double DEFAULT_GAMMA = 0.5d;
    private static final double DEFAULT_KHI = 2.0d;
    private static final double DEFAULT_RHO = 1.0d;
    private static final double DEFAULT_SIGMA = 0.5d;
    private final double gamma;
    private final double khi;
    private final double rho;
    private final double sigma;

    public NelderMeadSimplex(int n) {
        this(n, 1.0d);
    }

    public NelderMeadSimplex(int n, double sideLength) {
        this(n, sideLength, 1.0d, DEFAULT_KHI, 0.5d, 0.5d);
    }

    public NelderMeadSimplex(int n, double sideLength, double rho, double khi, double gamma, double sigma) {
        super(n, sideLength);
        this.rho = rho;
        this.khi = khi;
        this.gamma = gamma;
        this.sigma = sigma;
    }

    public NelderMeadSimplex(int n, double rho, double khi, double gamma, double sigma) {
        this(n, 1.0d, rho, khi, gamma, sigma);
    }

    public NelderMeadSimplex(double[] steps) {
        this(steps, 1.0d, DEFAULT_KHI, 0.5d, 0.5d);
    }

    public NelderMeadSimplex(double[] steps, double rho, double khi, double gamma, double sigma) {
        super(steps);
        this.rho = rho;
        this.khi = khi;
        this.gamma = gamma;
        this.sigma = sigma;
    }

    public NelderMeadSimplex(double[][] referenceSimplex) {
        this(referenceSimplex, 1.0d, DEFAULT_KHI, 0.5d, 0.5d);
    }

    public NelderMeadSimplex(double[][] referenceSimplex, double rho, double khi, double gamma, double sigma) {
        super(referenceSimplex);
        this.rho = rho;
        this.khi = khi;
        this.gamma = gamma;
        this.sigma = sigma;
    }

    @Override // org.apache.commons.math3.optimization.direct.AbstractSimplex
    public void iterate(MultivariateFunction evaluationFunction, Comparator<PointValuePair> comparator) {
        int n = getDimension();
        PointValuePair best = getPoint(0);
        PointValuePair secondBest = getPoint(n - 1);
        PointValuePair worst = getPoint(n);
        double[] xWorst = worst.getPointRef();
        double[] centroid = new double[n];
        for (int i = 0; i < n; i++) {
            double[] x = getPoint(i).getPointRef();
            for (int j = 0; j < n; j++) {
                centroid[j] = centroid[j] + x[j];
            }
        }
        double scaling = 1.0d / n;
        for (int j2 = 0; j2 < n; j2++) {
            centroid[j2] = centroid[j2] * scaling;
        }
        double[] xR = new double[n];
        int j3 = 0;
        while (j3 < n) {
            xR[j3] = centroid[j3] + (this.rho * (centroid[j3] - xWorst[j3]));
            j3++;
            best = best;
        }
        PointValuePair reflected = new PointValuePair(xR, evaluationFunction.value(xR), false);
        PointValuePair best2 = best;
        if (comparator.compare(best2, reflected) <= 0 && comparator.compare(reflected, secondBest) < 0) {
            replaceWorstPoint(reflected, comparator);
            return;
        }
        if (comparator.compare(reflected, best2) >= 0) {
            if (comparator.compare(reflected, worst) < 0) {
                double[] xC = new double[n];
                for (int j4 = 0; j4 < n; j4++) {
                    xC[j4] = centroid[j4] + (this.gamma * (xR[j4] - centroid[j4]));
                }
                PointValuePair outContracted = new PointValuePair(xC, evaluationFunction.value(xC), false);
                if (comparator.compare(outContracted, reflected) <= 0) {
                    replaceWorstPoint(outContracted, comparator);
                    return;
                }
            } else {
                double[] xC2 = new double[n];
                for (int j5 = 0; j5 < n; j5++) {
                    xC2[j5] = centroid[j5] - (this.gamma * (centroid[j5] - xWorst[j5]));
                }
                PointValuePair inContracted = new PointValuePair(xC2, evaluationFunction.value(xC2), false);
                if (comparator.compare(inContracted, worst) < 0) {
                    replaceWorstPoint(inContracted, comparator);
                    return;
                }
            }
            double[] xSmallest = getPoint(0).getPointRef();
            int i2 = 1;
            while (i2 <= n) {
                double[] x2 = getPoint(i2).getPoint();
                for (int j6 = 0; j6 < n; j6++) {
                    x2[j6] = xSmallest[j6] + (this.sigma * (x2[j6] - xSmallest[j6]));
                }
                setPoint(i2, new PointValuePair(x2, Double.NaN, false));
                i2++;
                n = n;
            }
            evaluate(evaluationFunction, comparator);
            return;
        }
        double[] xE = new double[n];
        int j7 = 0;
        while (j7 < n) {
            xE[j7] = centroid[j7] + (this.khi * (xR[j7] - centroid[j7]));
            j7++;
            secondBest = secondBest;
            best2 = best2;
        }
        PointValuePair expanded = new PointValuePair(xE, evaluationFunction.value(xE), false);
        if (comparator.compare(expanded, reflected) < 0) {
            replaceWorstPoint(expanded, comparator);
        } else {
            replaceWorstPoint(reflected, comparator);
        }
    }
}
