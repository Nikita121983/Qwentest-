package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

/* loaded from: classes10.dex */
class MidpointStepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public MidpointStepInterpolator() {
    }

    MidpointStepInterpolator(MidpointStepInterpolator interpolator) {
        super(interpolator);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new MidpointStepInterpolator(this);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
        double coeffDot2 = 2.0d * theta;
        double coeffDot1 = 1.0d - coeffDot2;
        char c = 0;
        char c2 = 1;
        if (this.previousState != null && theta <= 0.5d) {
            double coeff1 = theta * oneMinusThetaH;
            double coeff2 = theta * theta * this.h;
            int i = 0;
            while (i < this.interpolatedState.length) {
                double yDot1 = this.yDotK[c][i];
                double yDot2 = this.yDotK[1][i];
                this.interpolatedState[i] = this.previousState[i] + (coeff1 * yDot1) + (coeff2 * yDot2);
                this.interpolatedDerivatives[i] = (coeffDot1 * yDot1) + (coeffDot2 * yDot2);
                i++;
                c = c;
            }
            return;
        }
        double coeff12 = oneMinusThetaH * theta;
        double coeff22 = (theta + 1.0d) * oneMinusThetaH;
        int i2 = 0;
        while (i2 < this.interpolatedState.length) {
            double yDot12 = this.yDotK[0][i2];
            double yDot22 = this.yDotK[c2][i2];
            this.interpolatedState[i2] = (this.currentState[i2] + (coeff12 * yDot12)) - (coeff22 * yDot22);
            this.interpolatedDerivatives[i2] = (coeffDot1 * yDot12) + (coeffDot2 * yDot22);
            i2++;
            c2 = 1;
        }
    }
}
