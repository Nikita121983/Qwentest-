package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.sampling.FieldStepHandler;
import org.apache.commons.math3.ode.sampling.FieldStepInterpolator;

/* loaded from: classes10.dex */
public class ContinuousOutputFieldModel<T extends RealFieldElement<T>> implements FieldStepHandler<T> {
    private List<FieldStepInterpolator<T>> steps = new ArrayList();
    private T initialTime = null;
    private T finalTime = null;
    private boolean forward = true;
    private int index = 0;

    public void append(ContinuousOutputFieldModel<T> model) throws MathIllegalArgumentException, MaxCountExceededException {
        if (model.steps.size() == 0) {
            return;
        }
        if (this.steps.size() == 0) {
            this.initialTime = model.initialTime;
            this.forward = model.forward;
        } else {
            FieldODEStateAndDerivative<T> s1 = this.steps.get(0).getPreviousState();
            FieldODEStateAndDerivative<T> s2 = model.steps.get(0).getPreviousState();
            checkDimensionsEquality(s1.getStateDimension(), s2.getStateDimension());
            checkDimensionsEquality(s1.getNumberOfSecondaryStates(), s2.getNumberOfSecondaryStates());
            for (int i = 0; i < s1.getNumberOfSecondaryStates(); i++) {
                checkDimensionsEquality(s1.getSecondaryStateDimension(i), s2.getSecondaryStateDimension(i));
            }
            if (this.forward ^ model.forward) {
                throw new MathIllegalArgumentException(LocalizedFormats.PROPAGATION_DIRECTION_MISMATCH, new Object[0]);
            }
            FieldStepInterpolator<T> lastInterpolator = this.steps.get(this.index);
            T current = lastInterpolator.getCurrentState().getTime();
            T previous = lastInterpolator.getPreviousState().getTime();
            RealFieldElement realFieldElement = (RealFieldElement) current.subtract(previous);
            RealFieldElement realFieldElement2 = (RealFieldElement) model.getInitialTime().subtract(current);
            if (((RealFieldElement) ((RealFieldElement) realFieldElement2.abs()).subtract((RealFieldElement) ((RealFieldElement) realFieldElement.abs()).multiply(0.001d))).getReal() > 0.0d) {
                throw new MathIllegalArgumentException(LocalizedFormats.HOLE_BETWEEN_MODELS_TIME_RANGES, Double.valueOf(((RealFieldElement) realFieldElement2.abs()).getReal()));
            }
        }
        for (FieldStepInterpolator<T> interpolator : model.steps) {
            this.steps.add(interpolator);
        }
        this.index = this.steps.size() - 1;
        this.finalTime = this.steps.get(this.index).getCurrentState().getTime();
    }

    private void checkDimensionsEquality(int d1, int d2) throws DimensionMismatchException {
        if (d1 != d2) {
            throw new DimensionMismatchException(d2, d1);
        }
    }

    @Override // org.apache.commons.math3.ode.sampling.FieldStepHandler
    public void init(FieldODEStateAndDerivative<T> initialState, T t) {
        this.initialTime = initialState.getTime();
        this.finalTime = t;
        this.forward = true;
        this.index = 0;
        this.steps.clear();
    }

    @Override // org.apache.commons.math3.ode.sampling.FieldStepHandler
    public void handleStep(FieldStepInterpolator<T> interpolator, boolean isLast) throws MaxCountExceededException {
        if (this.steps.size() == 0) {
            this.initialTime = interpolator.getPreviousState().getTime();
            this.forward = interpolator.isForward();
        }
        this.steps.add(interpolator);
        if (isLast) {
            this.finalTime = interpolator.getCurrentState().getTime();
            this.index = this.steps.size() - 1;
        }
    }

    public T getInitialTime() {
        return this.initialTime;
    }

    public T getFinalTime() {
        return this.finalTime;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x01f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.commons.math3.ode.FieldODEStateAndDerivative<T> getInterpolatedState(T r25) {
        /*
            Method dump skipped, instructions count: 572
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.ContinuousOutputFieldModel.getInterpolatedState(org.apache.commons.math3.RealFieldElement):org.apache.commons.math3.ode.FieldODEStateAndDerivative");
    }

    private int locatePoint(T time, FieldStepInterpolator<T> interval) {
        if (this.forward) {
            if (((RealFieldElement) time.subtract(interval.getPreviousState().getTime())).getReal() < 0.0d) {
                return -1;
            }
            return ((RealFieldElement) time.subtract(interval.getCurrentState().getTime())).getReal() > 0.0d ? 1 : 0;
        }
        if (((RealFieldElement) time.subtract(interval.getPreviousState().getTime())).getReal() > 0.0d) {
            return -1;
        }
        return ((RealFieldElement) time.subtract(interval.getCurrentState().getTime())).getReal() < 0.0d ? 1 : 0;
    }
}
