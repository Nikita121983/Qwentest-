package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

/* loaded from: classes10.dex */
class ThreeEighthesStepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public ThreeEighthesStepInterpolator() {
    }

    ThreeEighthesStepInterpolator(ThreeEighthesStepInterpolator interpolator) {
        super(interpolator);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new ThreeEighthesStepInterpolator(this);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
        double coeffDot3 = 0.75d * theta;
        double coeffDot1 = (((theta * 4.0d) - 5.0d) * coeffDot3) + 1.0d;
        double coeffDot2 = (5.0d - (6.0d * theta)) * coeffDot3;
        double coeffDot4 = ((theta * 2.0d) - 1.0d) * coeffDot3;
        if (this.previousState != null && theta <= 0.5d) {
            double s = (this.h * theta) / 8.0d;
            double fourTheta2 = 4.0d * theta * theta;
            double coeff1 = ((8.0d - (15.0d * theta)) + (2.0d * fourTheta2)) * s;
            double coeff2 = s * 3.0d * ((5.0d * theta) - fourTheta2);
            double coeff3 = 3.0d * s * theta;
            double coeff4 = (((-3.0d) * theta) + fourTheta2) * s;
            int i = 0;
            while (i < this.interpolatedState.length) {
                double yDot1 = this.yDotK[0][i];
                double yDot2 = this.yDotK[1][i];
                double yDot3 = this.yDotK[2][i];
                double yDot4 = this.yDotK[3][i];
                double coeffDot32 = coeffDot3;
                this.interpolatedState[i] = this.previousState[i] + (coeff1 * yDot1) + (coeff2 * yDot2) + (coeff3 * yDot3) + (coeff4 * yDot4);
                this.interpolatedDerivatives[i] = (coeffDot1 * yDot1) + (coeffDot2 * yDot2) + (coeffDot32 * yDot3) + (coeffDot4 * yDot4);
                i++;
                coeffDot3 = coeffDot32;
            }
            return;
        }
        double coeffDot33 = oneMinusThetaH / 8.0d;
        double fourTheta22 = theta * 4.0d * theta;
        double coeff12 = ((1.0d - (7.0d * theta)) + (2.0d * fourTheta22)) * coeffDot33;
        double coeff22 = coeffDot33 * 3.0d * ((theta + 1.0d) - fourTheta22);
        double coeff32 = 3.0d * coeffDot33 * (theta + 1.0d);
        double coeff42 = (theta + 1.0d + fourTheta22) * coeffDot33;
        int i2 = 0;
        while (i2 < this.interpolatedState.length) {
            double yDot12 = this.yDotK[0][i2];
            double yDot22 = this.yDotK[1][i2];
            double yDot32 = this.yDotK[2][i2];
            double yDot42 = this.yDotK[3][i2];
            int i3 = i2;
            this.interpolatedState[i3] = (((this.currentState[i3] - (coeff12 * yDot12)) - (coeff22 * yDot22)) - (coeff32 * yDot32)) - (coeff42 * yDot42);
            this.interpolatedDerivatives[i3] = (coeffDot1 * yDot12) + (coeffDot2 * yDot22) + (coeffDot3 * yDot32) + (coeffDot4 * yDot42);
            i2 = i3 + 1;
        }
    }
}
