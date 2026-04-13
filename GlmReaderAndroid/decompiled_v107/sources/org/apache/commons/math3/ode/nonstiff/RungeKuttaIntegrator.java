package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public abstract class RungeKuttaIntegrator extends AbstractIntegrator {
    private final double[][] a;
    private final double[] b;
    private final double[] c;
    private final RungeKuttaStepInterpolator prototype;
    private final double step;

    /* JADX INFO: Access modifiers changed from: protected */
    public RungeKuttaIntegrator(String name, double[] c, double[][] a, double[] b, RungeKuttaStepInterpolator prototype, double step) {
        super(name);
        this.c = c;
        this.a = a;
        this.b = b;
        this.prototype = prototype;
        this.step = FastMath.abs(step);
    }

    @Override // org.apache.commons.math3.ode.AbstractIntegrator
    public void integrate(ExpandableStatefulODE equations, double t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        double[] y0;
        sanityChecks(equations, t);
        setEquations(equations);
        int i = 0;
        boolean forward = t > equations.getTime();
        double[] y02 = equations.getCompleteState();
        double[] y03 = (double[]) y02.clone();
        int stages = this.c.length + 1;
        double[][] yDotK = new double[stages];
        for (int i2 = 0; i2 < stages; i2++) {
            yDotK[i2] = new double[y02.length];
        }
        double[] yTmp = (double[]) y02.clone();
        double[] yDotTmp = new double[y02.length];
        RungeKuttaStepInterpolator interpolator = (RungeKuttaStepInterpolator) this.prototype.copy();
        interpolator.reinitialize(this, yTmp, yDotK, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
        boolean forward2 = forward;
        interpolator.storeTime(equations.getTime());
        this.stepStart = equations.getTime();
        if (forward2) {
            if (this.stepStart + this.step >= t) {
                this.stepSize = t - this.stepStart;
            } else {
                this.stepSize = this.step;
            }
        } else if (this.stepStart - this.step <= t) {
            this.stepSize = t - this.stepStart;
        } else {
            this.stepSize = -this.step;
        }
        RungeKuttaStepInterpolator interpolator2 = interpolator;
        initIntegration(equations.getTime(), y02, t);
        double[] y04 = y02;
        this.isLastStep = false;
        while (true) {
            interpolator2.shift();
            computeDerivatives(this.stepStart, y03, yDotK[i]);
            int k = 1;
            while (k < stages) {
                int j = 0;
                while (j < y04.length) {
                    double sum = this.a[k - 1][i] * yDotK[i][j];
                    for (int l = 1; l < k; l++) {
                        sum += this.a[k - 1][l] * yDotK[l][j];
                    }
                    yTmp[j] = y03[j] + (this.stepSize * sum);
                    j++;
                    i = i;
                }
                int k2 = k;
                computeDerivatives(this.stepStart + (this.c[k - 1] * this.stepSize), yTmp, yDotK[k2]);
                k = k2 + 1;
                i = i;
            }
            int i3 = i;
            int j2 = 0;
            while (j2 < y04.length) {
                double sum2 = this.b[i3] * yDotK[i3][j2];
                for (int l2 = 1; l2 < stages; l2++) {
                    sum2 += this.b[l2] * yDotK[l2][j2];
                }
                int j3 = j2;
                yTmp[j3] = y03[j2] + (this.stepSize * sum2);
                j2 = j3 + 1;
            }
            interpolator2.storeTime(this.stepStart + this.stepSize);
            System.arraycopy(yTmp, i3, y03, i3, y04.length);
            System.arraycopy(yDotK[stages - 1], i3, yDotTmp, i3, y04.length);
            double[] y05 = y04;
            RungeKuttaStepInterpolator interpolator3 = interpolator2;
            double[] y = y03;
            double[] yDotTmp2 = yDotTmp;
            this.stepStart = acceptStep(interpolator3, y, yDotTmp2, t);
            if (this.isLastStep) {
                y0 = y05;
            } else {
                interpolator3.storeTime(this.stepStart);
                double nextT = this.stepStart + this.stepSize;
                if (((!forward2 ? nextT <= t : nextT >= t) ? i3 : 1) == 0) {
                    y0 = y05;
                } else {
                    y0 = y05;
                    this.stepSize = t - this.stepStart;
                }
            }
            if (!this.isLastStep) {
                interpolator2 = interpolator3;
                yDotTmp = yDotTmp2;
                y04 = y0;
                i = 0;
                y03 = y;
            } else {
                equations.setTime(this.stepStart);
                equations.setCompleteState(y);
                this.stepStart = Double.NaN;
                this.stepSize = Double.NaN;
                return;
            }
        }
    }

    public double[] singleStep(FirstOrderDifferentialEquations equations, double t0, double[] y0, double t) {
        double[] y = (double[]) y0.clone();
        int stages = this.c.length + 1;
        double[][] yDotK = new double[stages];
        for (int i = 0; i < stages; i++) {
            yDotK[i] = new double[y0.length];
        }
        double[] yTmp = (double[]) y0.clone();
        double h = t - t0;
        char c = 0;
        equations.computeDerivatives(t0, y, yDotK[0]);
        int k = 1;
        while (k < stages) {
            for (int j = 0; j < y0.length; j++) {
                double sum = this.a[k - 1][c] * yDotK[c][j];
                int l = 1;
                while (l < k) {
                    sum += this.a[k - 1][l] * yDotK[l][j];
                    l++;
                    c = c;
                }
                yTmp[j] = y[j] + (h * sum);
            }
            equations.computeDerivatives((this.c[k - 1] * h) + t0, yTmp, yDotK[k]);
            k++;
            c = c;
        }
        char c2 = c;
        for (int j2 = 0; j2 < y0.length; j2++) {
            double sum2 = this.b[c2] * yDotK[c2][j2];
            for (int l2 = 1; l2 < stages; l2++) {
                sum2 += this.b[l2] * yDotK[l2][j2];
            }
            y[j2] = y[j2] + (h * sum2);
        }
        return y;
    }
}
