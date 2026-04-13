package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.FieldODEStateAndDerivative;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class FieldStepNormalizer<T extends RealFieldElement<T>> implements FieldStepHandler<T> {
    private final StepNormalizerBounds bounds;
    private FieldODEStateAndDerivative<T> first;
    private boolean forward;
    private double h;
    private final FieldFixedStepHandler<T> handler;
    private FieldODEStateAndDerivative<T> last;
    private final StepNormalizerMode mode;

    public FieldStepNormalizer(double h, FieldFixedStepHandler<T> handler) {
        this(h, handler, StepNormalizerMode.INCREMENT, StepNormalizerBounds.FIRST);
    }

    public FieldStepNormalizer(double h, FieldFixedStepHandler<T> handler, StepNormalizerMode mode) {
        this(h, handler, mode, StepNormalizerBounds.FIRST);
    }

    public FieldStepNormalizer(double h, FieldFixedStepHandler<T> handler, StepNormalizerBounds bounds) {
        this(h, handler, StepNormalizerMode.INCREMENT, bounds);
    }

    public FieldStepNormalizer(double h, FieldFixedStepHandler<T> handler, StepNormalizerMode mode, StepNormalizerBounds bounds) {
        this.h = FastMath.abs(h);
        this.handler = handler;
        this.mode = mode;
        this.bounds = bounds;
        this.first = null;
        this.last = null;
        this.forward = true;
    }

    @Override // org.apache.commons.math3.ode.sampling.FieldStepHandler
    public void init(FieldODEStateAndDerivative<T> initialState, T finalTime) {
        this.first = null;
        this.last = null;
        this.forward = true;
        this.handler.init(initialState, finalTime);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [org.apache.commons.math3.RealFieldElement] */
    @Override // org.apache.commons.math3.ode.sampling.FieldStepHandler
    public void handleStep(FieldStepInterpolator<T> fieldStepInterpolator, boolean isLast) throws MaxCountExceededException {
        T t;
        double floor;
        if (this.last == null) {
            this.first = fieldStepInterpolator.getPreviousState();
            this.last = this.first;
            this.forward = fieldStepInterpolator.isForward();
            if (!this.forward) {
                this.h = -this.h;
            }
        }
        if (this.mode == StepNormalizerMode.INCREMENT) {
            t = this.last.getTime();
            floor = this.h;
        } else {
            t = (T) this.last.getTime().getField().getZero();
            floor = (FastMath.floor(this.last.getTime().getReal() / this.h) + 1.0d) * this.h;
        }
        RealFieldElement realFieldElement = (RealFieldElement) t.add(floor);
        if (this.mode == StepNormalizerMode.MULTIPLES && Precision.equals(realFieldElement.getReal(), this.last.getTime().getReal(), 1)) {
            realFieldElement = (RealFieldElement) realFieldElement.add(this.h);
        }
        boolean nextInStep = isNextInStep(realFieldElement, fieldStepInterpolator);
        while (true) {
            if (!nextInStep) {
                break;
            }
            doNormalizedStep(false);
            this.last = fieldStepInterpolator.getInterpolatedState(realFieldElement);
            realFieldElement = (RealFieldElement) realFieldElement.add(this.h);
            nextInStep = isNextInStep(realFieldElement, fieldStepInterpolator);
        }
        if (isLast) {
            boolean addLast = this.bounds.lastIncluded() && this.last.getTime().getReal() != fieldStepInterpolator.getCurrentState().getTime().getReal();
            doNormalizedStep(addLast ? false : true);
            if (addLast) {
                this.last = fieldStepInterpolator.getCurrentState();
                doNormalizedStep(true);
            }
        }
    }

    private boolean isNextInStep(T nextTime, FieldStepInterpolator<T> interpolator) {
        boolean z = this.forward;
        double real = nextTime.getReal();
        if (z) {
            if (real <= interpolator.getCurrentState().getTime().getReal()) {
                return true;
            }
        } else if (real >= interpolator.getCurrentState().getTime().getReal()) {
            return true;
        }
        return false;
    }

    private void doNormalizedStep(boolean isLast) {
        if (!this.bounds.firstIncluded() && this.first.getTime().getReal() == this.last.getTime().getReal()) {
            return;
        }
        this.handler.handleStep(this.last, isLast);
    }
}
