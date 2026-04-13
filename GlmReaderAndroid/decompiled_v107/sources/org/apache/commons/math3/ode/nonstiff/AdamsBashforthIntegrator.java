package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class AdamsBashforthIntegrator extends AdamsIntegrator {
    private static final String METHOD_NAME = "Adams-Bashforth";

    public AdamsBashforthIntegrator(int nSteps, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(METHOD_NAME, nSteps, nSteps, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
    }

    public AdamsBashforthIntegrator(int nSteps, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
        super(METHOD_NAME, nSteps, nSteps, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
    }

    private double errorEstimation(double[] previousState, double[] predictedState, double[] predictedScaled, RealMatrix predictedNordsieck) {
        double error = 0.0d;
        for (int i = 0; i < this.mainSetDimension; i++) {
            double yScale = FastMath.abs(predictedState[i]);
            double tol = this.vecAbsoluteTolerance == null ? this.scalAbsoluteTolerance + (this.scalRelativeTolerance * yScale) : this.vecAbsoluteTolerance[i] + (this.vecRelativeTolerance[i] * yScale);
            double variation = 0.0d;
            int sign = predictedNordsieck.getRowDimension() % 2 == 0 ? -1 : 1;
            for (int k = predictedNordsieck.getRowDimension() - 1; k >= 0; k--) {
                variation += sign * predictedNordsieck.getEntry(k, i);
                sign = -sign;
            }
            double ratio = ((predictedState[i] - previousState[i]) + (variation - predictedScaled[i])) / tol;
            error += ratio * ratio;
        }
        int i2 = this.mainSetDimension;
        return FastMath.sqrt(error / i2);
    }

    @Override // org.apache.commons.math3.ode.nonstiff.AdamsIntegrator, org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator, org.apache.commons.math3.ode.AbstractIntegrator
    public void integrate(ExpandableStatefulODE equations, double t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        Array2DRowRealMatrix predictedNordsieck;
        double error;
        sanityChecks(equations, t);
        setEquations(equations);
        boolean forward = t > equations.getTime();
        double[] yDot = equations.getCompleteState();
        double[] yDot2 = new double[yDot.length];
        NordsieckStepInterpolator interpolator = new NordsieckStepInterpolator();
        interpolator.reinitialize(yDot, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
        initIntegration(equations.getTime(), yDot, t);
        start(equations.getTime(), yDot, t);
        interpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
        interpolator.storeTime(this.stepStart);
        double hNew = this.stepSize;
        interpolator.rescale(hNew);
        this.isLastStep = false;
        while (true) {
            interpolator.shift();
            double[] predictedY = new double[yDot.length];
            double[] predictedScaled = new double[yDot.length];
            Array2DRowRealMatrix predictedNordsieck2 = null;
            double error2 = 10.0d;
            while (true) {
                predictedNordsieck = predictedNordsieck2;
                if (error2 < 1.0d) {
                    break;
                }
                double stepEnd = this.stepStart + hNew;
                interpolator.storeTime(stepEnd);
                ExpandableStatefulODE expandable = getExpandable();
                EquationsMapper primary = expandable.getPrimaryMapper();
                primary.insertEquationData(interpolator.getInterpolatedState(), predictedY);
                EquationsMapper[] arr$ = expandable.getSecondaryMappers();
                int i$ = 0;
                int index = 0;
                for (int len$ = arr$.length; i$ < len$; len$ = len$) {
                    int i$2 = i$;
                    EquationsMapper secondary = arr$[i$2];
                    secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index), predictedY);
                    index++;
                    i$ = i$2 + 1;
                }
                computeDerivatives(stepEnd, predictedY, yDot2);
                for (int j = 0; j < predictedScaled.length; j++) {
                    predictedScaled[j] = yDot2[j] * hNew;
                }
                predictedNordsieck2 = updateHighOrderDerivativesPhase1(this.nordsieck);
                updateHighOrderDerivativesPhase2(this.scaled, predictedScaled, predictedNordsieck2);
                double error3 = errorEstimation(yDot, predictedY, predictedScaled, predictedNordsieck2);
                if (error3 < 1.0d) {
                    error = error3;
                } else {
                    double factor = computeStepGrowShrinkFactor(error3);
                    error = error3;
                    double error4 = hNew * factor;
                    hNew = filterStep(error4, forward, false);
                    interpolator.rescale(hNew);
                }
                error2 = error;
            }
            double error5 = error2;
            this.stepSize = hNew;
            double stepEnd2 = this.stepSize + this.stepStart;
            interpolator.reinitialize(stepEnd2, this.stepSize, predictedScaled, predictedNordsieck);
            interpolator.storeTime(stepEnd2);
            double[] yDot3 = yDot2;
            System.arraycopy(predictedY, 0, yDot, 0, yDot.length);
            double hNew2 = hNew;
            double[] y = yDot;
            NordsieckStepInterpolator interpolator2 = interpolator;
            yDot = y;
            this.stepStart = acceptStep(interpolator2, y, yDot3, t);
            this.scaled = predictedScaled;
            this.nordsieck = predictedNordsieck;
            double d = this.stepSize;
            double[] predictedScaled2 = this.scaled;
            Array2DRowRealMatrix predictedNordsieck3 = this.nordsieck;
            interpolator = interpolator2;
            interpolator.reinitialize(stepEnd2, d, predictedScaled2, predictedNordsieck3);
            if (this.isLastStep) {
                hNew = hNew2;
            } else {
                interpolator.storeTime(this.stepStart);
                if (this.resetOccurred) {
                    start(this.stepStart, yDot, t);
                    interpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
                }
                double factor2 = computeStepGrowShrinkFactor(error5);
                double scaledH = this.stepSize * factor2;
                double nextT = this.stepStart + scaledH;
                boolean nextIsLast = !forward ? nextT > t : nextT < t;
                double hNew3 = filterStep(scaledH, forward, nextIsLast);
                double factor3 = this.stepStart;
                double filteredNextT = factor3 + hNew3;
                boolean filteredNextIsLast = !forward ? filteredNextT > t : filteredNextT < t;
                hNew = filteredNextIsLast ? t - this.stepStart : hNew3;
                interpolator.rescale(hNew);
            }
            if (this.isLastStep) {
                equations.setTime(this.stepStart);
                equations.setCompleteState(yDot);
                resetInternalState();
                return;
            }
            yDot2 = yDot3;
        }
    }
}
