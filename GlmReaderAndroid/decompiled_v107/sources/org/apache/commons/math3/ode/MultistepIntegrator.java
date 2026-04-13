package org.apache.commons.math3.ode;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public abstract class MultistepIntegrator extends AdaptiveStepsizeIntegrator {
    private double exp;
    private double maxGrowth;
    private double minReduction;
    private final int nSteps;
    protected Array2DRowRealMatrix nordsieck;
    private double safety;
    protected double[] scaled;
    private FirstOrderIntegrator starter;

    @Deprecated
    /* loaded from: classes10.dex */
    public interface NordsieckTransformer {
        Array2DRowRealMatrix initializeHighOrderDerivatives(double d, double[] dArr, double[][] dArr2, double[][] dArr3);
    }

    protected abstract Array2DRowRealMatrix initializeHighOrderDerivatives(double d, double[] dArr, double[][] dArr2, double[][] dArr3);

    /* JADX INFO: Access modifiers changed from: protected */
    public MultistepIntegrator(String name, int nSteps, int order, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(name, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        if (nSteps < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.INTEGRATION_METHOD_NEEDS_AT_LEAST_TWO_PREVIOUS_POINTS, Integer.valueOf(nSteps), 2, true);
        }
        this.starter = new DormandPrince853Integrator(minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
        this.nSteps = nSteps;
        this.exp = (-1.0d) / order;
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(FastMath.pow(2.0d, -this.exp));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MultistepIntegrator(String name, int nSteps, int order, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) {
        super(name, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.starter = new DormandPrince853Integrator(minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
        this.nSteps = nSteps;
        this.exp = (-1.0d) / order;
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(FastMath.pow(2.0d, -this.exp));
    }

    public ODEIntegrator getStarterIntegrator() {
        return this.starter;
    }

    public void setStarterIntegrator(FirstOrderIntegrator starterIntegrator) {
        this.starter = starterIntegrator;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void start(double t0, double[] y0, double t) throws DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException, NoBracketingException {
        this.starter.clearEventHandlers();
        this.starter.clearStepHandlers();
        this.starter.addStepHandler(new NordsieckInitializer((this.nSteps + 3) / 2, y0.length));
        try {
            if (this.starter instanceof AbstractIntegrator) {
                try {
                    ((AbstractIntegrator) this.starter).integrate(getExpandable(), t);
                } catch (InitializationCompletedMarkerException e) {
                    getCounter().increment(this.starter.getEvaluations());
                    this.starter.clearStepHandlers();
                    return;
                }
            } else {
                try {
                    this.starter.integrate(new FirstOrderDifferentialEquations() { // from class: org.apache.commons.math3.ode.MultistepIntegrator.1
                        @Override // org.apache.commons.math3.ode.FirstOrderDifferentialEquations
                        public int getDimension() {
                            return MultistepIntegrator.this.getExpandable().getTotalDimension();
                        }

                        @Override // org.apache.commons.math3.ode.FirstOrderDifferentialEquations
                        public void computeDerivatives(double t2, double[] y, double[] yDot) {
                            MultistepIntegrator.this.getExpandable().computeDerivatives(t2, y, yDot);
                        }
                    }, t0, y0, t, new double[y0.length]);
                } catch (InitializationCompletedMarkerException e2) {
                    getCounter().increment(this.starter.getEvaluations());
                    this.starter.clearStepHandlers();
                    return;
                }
            }
            throw new MathIllegalStateException(LocalizedFormats.MULTISTEP_STARTER_STOPPED_EARLY, new Object[0]);
        } catch (InitializationCompletedMarkerException e3) {
        }
    }

    public double getMinReduction() {
        return this.minReduction;
    }

    public void setMinReduction(double minReduction) {
        this.minReduction = minReduction;
    }

    public double getMaxGrowth() {
        return this.maxGrowth;
    }

    public void setMaxGrowth(double maxGrowth) {
        this.maxGrowth = maxGrowth;
    }

    public double getSafety() {
        return this.safety;
    }

    public void setSafety(double safety) {
        this.safety = safety;
    }

    public int getNSteps() {
        return this.nSteps;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double computeStepGrowShrinkFactor(double error) {
        return FastMath.min(this.maxGrowth, FastMath.max(this.minReduction, this.safety * FastMath.pow(error, this.exp)));
    }

    /* loaded from: classes10.dex */
    private class NordsieckInitializer implements StepHandler {
        private int count = 0;
        private final double[] t;
        private final double[][] y;
        private final double[][] yDot;

        NordsieckInitializer(int nbStartPoints, int n) {
            this.t = new double[nbStartPoints];
            this.y = (double[][]) Array.newInstance((Class<?>) Double.TYPE, nbStartPoints, n);
            this.yDot = (double[][]) Array.newInstance((Class<?>) Double.TYPE, nbStartPoints, n);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r16v0 */
        /* JADX WARN: Type inference failed for: r16v1 */
        /* JADX WARN: Type inference failed for: r16v2 */
        @Override // org.apache.commons.math3.ode.sampling.StepHandler
        public void handleStep(StepInterpolator interpolator, boolean isLast) throws MaxCountExceededException {
            ?? r16;
            double prev = interpolator.getPreviousTime();
            double curr = interpolator.getCurrentTime();
            boolean z = false;
            if (this.count != 0) {
                r16 = 0;
            } else {
                interpolator.setInterpolatedTime(prev);
                this.t[0] = prev;
                ExpandableStatefulODE expandable = MultistepIntegrator.this.getExpandable();
                EquationsMapper primary = expandable.getPrimaryMapper();
                primary.insertEquationData(interpolator.getInterpolatedState(), this.y[this.count]);
                primary.insertEquationData(interpolator.getInterpolatedDerivatives(), this.yDot[this.count]);
                int index = 0;
                EquationsMapper[] arr$ = expandable.getSecondaryMappers();
                int len$ = arr$.length;
                int i$ = 0;
                while (i$ < len$) {
                    EquationsMapper secondary = arr$[i$];
                    secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index), this.y[this.count]);
                    secondary.insertEquationData(interpolator.getInterpolatedSecondaryDerivatives(index), this.yDot[this.count]);
                    index++;
                    i$++;
                    z = z;
                }
                r16 = z;
            }
            this.count++;
            interpolator.setInterpolatedTime(curr);
            this.t[this.count] = curr;
            ExpandableStatefulODE expandable2 = MultistepIntegrator.this.getExpandable();
            EquationsMapper primary2 = expandable2.getPrimaryMapper();
            primary2.insertEquationData(interpolator.getInterpolatedState(), this.y[this.count]);
            primary2.insertEquationData(interpolator.getInterpolatedDerivatives(), this.yDot[this.count]);
            int index2 = 0;
            EquationsMapper[] arr$2 = expandable2.getSecondaryMappers();
            for (EquationsMapper secondary2 : arr$2) {
                secondary2.insertEquationData(interpolator.getInterpolatedSecondaryState(index2), this.y[this.count]);
                secondary2.insertEquationData(interpolator.getInterpolatedSecondaryDerivatives(index2), this.yDot[this.count]);
                index2++;
            }
            if (this.count == this.t.length - 1) {
                MultistepIntegrator.this.stepStart = this.t[r16];
                MultistepIntegrator.this.stepSize = (this.t[this.t.length - 1] - this.t[r16]) / (this.t.length - 1);
                MultistepIntegrator.this.scaled = (double[]) this.yDot[r16].clone();
                for (int j = 0; j < MultistepIntegrator.this.scaled.length; j++) {
                    double[] dArr = MultistepIntegrator.this.scaled;
                    dArr[j] = dArr[j] * MultistepIntegrator.this.stepSize;
                }
                MultistepIntegrator.this.nordsieck = MultistepIntegrator.this.initializeHighOrderDerivatives(MultistepIntegrator.this.stepSize, this.t, this.y, this.yDot);
                throw new InitializationCompletedMarkerException();
            }
        }

        @Override // org.apache.commons.math3.ode.sampling.StepHandler
        public void init(double t0, double[] y0, double time) {
        }
    }

    /* loaded from: classes10.dex */
    private static class InitializationCompletedMarkerException extends RuntimeException {
        private static final long serialVersionUID = -1914085471038046418L;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        InitializationCompletedMarkerException() {
            super((Throwable) null);
        }
    }
}
