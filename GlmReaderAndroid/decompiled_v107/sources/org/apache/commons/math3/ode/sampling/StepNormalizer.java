package org.apache.commons.math3.ode.sampling;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class StepNormalizer implements StepHandler {
    private final StepNormalizerBounds bounds;
    private double firstTime;
    private boolean forward;
    private double h;
    private final FixedStepHandler handler;
    private double[] lastDerivatives;
    private double[] lastState;
    private double lastTime;
    private final StepNormalizerMode mode;

    public StepNormalizer(double h, FixedStepHandler handler) {
        this(h, handler, StepNormalizerMode.INCREMENT, StepNormalizerBounds.FIRST);
    }

    public StepNormalizer(double h, FixedStepHandler handler, StepNormalizerMode mode) {
        this(h, handler, mode, StepNormalizerBounds.FIRST);
    }

    public StepNormalizer(double h, FixedStepHandler handler, StepNormalizerBounds bounds) {
        this(h, handler, StepNormalizerMode.INCREMENT, bounds);
    }

    public StepNormalizer(double h, FixedStepHandler handler, StepNormalizerMode mode, StepNormalizerBounds bounds) {
        this.h = FastMath.abs(h);
        this.handler = handler;
        this.mode = mode;
        this.bounds = bounds;
        this.firstTime = Double.NaN;
        this.lastTime = Double.NaN;
        this.lastState = null;
        this.lastDerivatives = null;
        this.forward = true;
    }

    @Override // org.apache.commons.math3.ode.sampling.StepHandler
    public void init(double t0, double[] y0, double t) {
        this.firstTime = Double.NaN;
        this.lastTime = Double.NaN;
        this.lastState = null;
        this.lastDerivatives = null;
        this.forward = true;
        this.handler.init(t0, y0, t);
    }

    @Override // org.apache.commons.math3.ode.sampling.StepHandler
    public void handleStep(StepInterpolator interpolator, boolean isLast) throws MaxCountExceededException {
        if (this.lastState == null) {
            this.firstTime = interpolator.getPreviousTime();
            this.lastTime = interpolator.getPreviousTime();
            interpolator.setInterpolatedTime(this.lastTime);
            this.lastState = (double[]) interpolator.getInterpolatedState().clone();
            this.lastDerivatives = (double[]) interpolator.getInterpolatedDerivatives().clone();
            this.forward = interpolator.getCurrentTime() >= this.lastTime;
            if (!this.forward) {
                this.h = -this.h;
            }
        }
        double nextTime = this.mode == StepNormalizerMode.INCREMENT ? this.lastTime + this.h : (FastMath.floor(this.lastTime / this.h) + 1.0d) * this.h;
        if (this.mode == StepNormalizerMode.MULTIPLES && Precision.equals(nextTime, this.lastTime, 1)) {
            nextTime += this.h;
        }
        boolean nextInStep = isNextInStep(nextTime, interpolator);
        while (nextInStep) {
            doNormalizedStep(false);
            storeStep(interpolator, nextTime);
            nextTime += this.h;
            nextInStep = isNextInStep(nextTime, interpolator);
        }
        if (isLast) {
            boolean addLast = this.bounds.lastIncluded() && this.lastTime != interpolator.getCurrentTime();
            doNormalizedStep(addLast ? false : true);
            if (addLast) {
                storeStep(interpolator, interpolator.getCurrentTime());
                doNormalizedStep(true);
            }
        }
    }

    private boolean isNextInStep(double nextTime, StepInterpolator interpolator) {
        boolean z = this.forward;
        double currentTime = interpolator.getCurrentTime();
        if (z) {
            if (nextTime <= currentTime) {
                return true;
            }
        } else if (nextTime >= currentTime) {
            return true;
        }
        return false;
    }

    private void doNormalizedStep(boolean isLast) {
        if (!this.bounds.firstIncluded() && this.firstTime == this.lastTime) {
            return;
        }
        this.handler.handleStep(this.lastTime, this.lastState, this.lastDerivatives, isLast);
    }

    private void storeStep(StepInterpolator interpolator, double t) throws MaxCountExceededException {
        this.lastTime = t;
        interpolator.setInterpolatedTime(this.lastTime);
        System.arraycopy(interpolator.getInterpolatedState(), 0, this.lastState, 0, this.lastState.length);
        System.arraycopy(interpolator.getInterpolatedDerivatives(), 0, this.lastDerivatives, 0, this.lastDerivatives.length);
    }
}
