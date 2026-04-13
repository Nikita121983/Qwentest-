package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrixPreservingVisitor;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class AdamsMoultonIntegrator extends AdamsIntegrator {
    private static final String METHOD_NAME = "Adams-Moulton";

    public AdamsMoultonIntegrator(int nSteps, double minStep, double maxStep, double scalAbsoluteTolerance, double scalRelativeTolerance) throws NumberIsTooSmallException {
        super(METHOD_NAME, nSteps, nSteps + 1, minStep, maxStep, scalAbsoluteTolerance, scalRelativeTolerance);
    }

    public AdamsMoultonIntegrator(int nSteps, double minStep, double maxStep, double[] vecAbsoluteTolerance, double[] vecRelativeTolerance) throws IllegalArgumentException {
        super(METHOD_NAME, nSteps, nSteps + 1, minStep, maxStep, vecAbsoluteTolerance, vecRelativeTolerance);
    }

    @Override // org.apache.commons.math3.ode.nonstiff.AdamsIntegrator, org.apache.commons.math3.ode.nonstiff.AdaptiveStepsizeIntegrator, org.apache.commons.math3.ode.AbstractIntegrator
    public void integrate(ExpandableStatefulODE equations, double t) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        double hNew;
        sanityChecks(equations, t);
        setEquations(equations);
        boolean forward = t > equations.getTime();
        double[] y0 = equations.getCompleteState();
        double[] y = (double[]) y0.clone();
        double[] yDot = new double[y.length];
        double[] yTmp = new double[y.length];
        double[] predictedScaled = new double[y.length];
        Array2DRowRealMatrix nordsieckTmp = null;
        NordsieckStepInterpolator interpolator = new NordsieckStepInterpolator();
        interpolator.reinitialize(y, forward, equations.getPrimaryMapper(), equations.getSecondaryMappers());
        initIntegration(equations.getTime(), y0, t);
        double[] yDot2 = y;
        start(equations.getTime(), yDot2, t);
        interpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
        interpolator.storeTime(this.stepStart);
        double hNew2 = this.stepSize;
        interpolator.rescale(hNew2);
        this.isLastStep = false;
        while (true) {
            double error = 10.0d;
            while (true) {
                hNew = hNew2;
                if (error < 1.0d) {
                    break;
                }
                this.stepSize = hNew;
                double stepEnd = this.stepStart + this.stepSize;
                interpolator.setInterpolatedTime(stepEnd);
                ExpandableStatefulODE expandable = getExpandable();
                EquationsMapper primary = expandable.getPrimaryMapper();
                primary.insertEquationData(interpolator.getInterpolatedState(), yTmp);
                EquationsMapper[] arr$ = expandable.getSecondaryMappers();
                int index = 0;
                int i$ = 0;
                for (int len$ = arr$.length; i$ < len$; len$ = len$) {
                    EquationsMapper secondary = arr$[i$];
                    secondary.insertEquationData(interpolator.getInterpolatedSecondaryState(index), yTmp);
                    index++;
                    i$++;
                }
                computeDerivatives(stepEnd, yTmp, yDot);
                for (int j = 0; j < y0.length; j++) {
                    predictedScaled[j] = this.stepSize * yDot[j];
                }
                nordsieckTmp = updateHighOrderDerivativesPhase1(this.nordsieck);
                updateHighOrderDerivativesPhase2(this.scaled, predictedScaled, nordsieckTmp);
                double error2 = nordsieckTmp.walkInOptimizedOrder(new Corrector(yDot2, predictedScaled, yTmp));
                if (error2 >= 1.0d) {
                    double factor = computeStepGrowShrinkFactor(error2);
                    hNew2 = filterStep(this.stepSize * factor, forward, false);
                    interpolator.rescale(hNew2);
                } else {
                    hNew2 = hNew;
                }
                error = error2;
            }
            double error3 = error;
            double stepEnd2 = this.stepStart + this.stepSize;
            computeDerivatives(stepEnd2, yTmp, yDot);
            double[] correctedScaled = new double[y0.length];
            for (int j2 = 0; j2 < y0.length; j2++) {
                correctedScaled[j2] = this.stepSize * yDot[j2];
            }
            updateHighOrderDerivativesPhase2(predictedScaled, correctedScaled, nordsieckTmp);
            System.arraycopy(yTmp, 0, yDot2, 0, yDot2.length);
            interpolator.reinitialize(stepEnd2, this.stepSize, correctedScaled, nordsieckTmp);
            interpolator.storeTime(this.stepStart);
            interpolator.shift();
            interpolator.storeTime(stepEnd2);
            double[] y2 = yDot2;
            double[] y3 = yDot;
            double[] yTmp2 = yTmp;
            double[] predictedScaled2 = predictedScaled;
            double acceptStep = acceptStep(interpolator, y2, y3, t);
            yDot2 = y2;
            this.stepStart = acceptStep;
            this.scaled = correctedScaled;
            this.nordsieck = nordsieckTmp;
            if (this.isLastStep) {
                hNew2 = hNew;
            } else {
                interpolator.storeTime(this.stepStart);
                if (this.resetOccurred) {
                    start(this.stepStart, yDot2, t);
                    interpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
                }
                double factor2 = computeStepGrowShrinkFactor(error3);
                double scaledH = this.stepSize * factor2;
                double nextT = this.stepStart + scaledH;
                boolean nextIsLast = !forward ? nextT > t : nextT < t;
                double hNew3 = filterStep(scaledH, forward, nextIsLast);
                double filteredNextT = this.stepStart + hNew3;
                boolean filteredNextIsLast = !forward ? filteredNextT > t : filteredNextT < t;
                hNew2 = filteredNextIsLast ? t - this.stepStart : hNew3;
                interpolator.rescale(hNew2);
            }
            if (this.isLastStep) {
                equations.setTime(this.stepStart);
                equations.setCompleteState(yDot2);
                resetInternalState();
                return;
            } else {
                yTmp = yTmp2;
                predictedScaled = predictedScaled2;
                yDot = y3;
            }
        }
    }

    /* loaded from: classes10.dex */
    private class Corrector implements RealMatrixPreservingVisitor {
        private final double[] after;
        private final double[] before;
        private final double[] previous;
        private final double[] scaled;

        Corrector(double[] previous, double[] scaled, double[] state) {
            this.previous = previous;
            this.scaled = scaled;
            this.after = state;
            this.before = (double[]) state.clone();
        }

        @Override // org.apache.commons.math3.linear.RealMatrixPreservingVisitor
        public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {
            Arrays.fill(this.after, 0.0d);
        }

        @Override // org.apache.commons.math3.linear.RealMatrixPreservingVisitor
        public void visit(int row, int column, double value) {
            if ((row & 1) == 0) {
                double[] dArr = this.after;
                dArr[column] = dArr[column] - value;
            } else {
                double[] dArr2 = this.after;
                dArr2[column] = dArr2[column] + value;
            }
        }

        @Override // org.apache.commons.math3.linear.RealMatrixPreservingVisitor
        public double end() {
            double error = 0.0d;
            for (int i = 0; i < this.after.length; i++) {
                double[] dArr = this.after;
                dArr[i] = dArr[i] + this.previous[i] + this.scaled[i];
                if (i < AdamsMoultonIntegrator.this.mainSetDimension) {
                    double yScale = FastMath.max(FastMath.abs(this.previous[i]), FastMath.abs(this.after[i]));
                    double tol = AdamsMoultonIntegrator.this.vecAbsoluteTolerance == null ? AdamsMoultonIntegrator.this.scalAbsoluteTolerance + (AdamsMoultonIntegrator.this.scalRelativeTolerance * yScale) : AdamsMoultonIntegrator.this.vecAbsoluteTolerance[i] + (AdamsMoultonIntegrator.this.vecRelativeTolerance[i] * yScale);
                    double ratio = (this.after[i] - this.before[i]) / tol;
                    error += ratio * ratio;
                }
            }
            return FastMath.sqrt(error / AdamsMoultonIntegrator.this.mainSetDimension);
        }
    }
}
