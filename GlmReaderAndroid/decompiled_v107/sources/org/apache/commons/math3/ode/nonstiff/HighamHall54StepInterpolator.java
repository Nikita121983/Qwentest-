package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

/* loaded from: classes10.dex */
class HighamHall54StepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public HighamHall54StepInterpolator() {
    }

    HighamHall54StepInterpolator(HighamHall54StepInterpolator interpolator) {
        super(interpolator);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new HighamHall54StepInterpolator(this);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
        double bDot0 = ((((16.0d - (10.0d * theta)) * theta) - 7.5d) * theta) + 1.0d;
        double bDot2 = ((((67.5d * theta) - 91.125d) * theta) + 28.6875d) * theta;
        double bDot3 = (((152.0d - (120.0d * theta)) * theta) - 44.0d) * theta;
        double bDot4 = ((((62.5d * theta) - 78.125d) * theta) + 23.4375d) * theta;
        double bDot5 = ((theta * 5.0d) / 8.0d) * ((theta * 2.0d) - 1.0d);
        if (this.previousState != null && theta <= 0.5d) {
            char c = 5;
            double hTheta = this.h * theta;
            double b0 = (((((5.333333333333333d - (2.5d * theta)) * theta) - 3.75d) * theta) + 1.0d) * hTheta;
            double b2 = (((((135.0d * theta) / 8.0d) - 30.375d) * theta) + 14.34375d) * theta * hTheta;
            double b3 = (((((-30.0d) * theta) + 50.666666666666664d) * theta) - 22.0d) * theta * hTheta;
            double b4 = (((((125.0d * theta) / 8.0d) - 26.041666666666668d) * theta) + 11.71875d) * theta * hTheta;
            double b5 = (((5.0d * theta) / 12.0d) - 0.3125d) * theta * hTheta;
            int i = 0;
            while (i < this.interpolatedState.length) {
                double yDot0 = this.yDotK[0][i];
                double yDot2 = this.yDotK[2][i];
                double yDot3 = this.yDotK[3][i];
                double yDot4 = this.yDotK[4][i];
                double yDot5 = this.yDotK[c][i];
                this.interpolatedState[i] = this.previousState[i] + (b0 * yDot0) + (b2 * yDot2) + (b3 * yDot3) + (b4 * yDot4) + (b5 * yDot5);
                this.interpolatedDerivatives[i] = (bDot0 * yDot0) + (bDot2 * yDot2) + (bDot3 * yDot3) + (bDot4 * yDot4) + (bDot5 * yDot5);
                i++;
                c = c;
            }
            return;
        }
        double theta2 = theta * theta;
        double b02 = this.h * ((((((((((-5.0d) * theta) / 2.0d) + 5.333333333333333d) * theta) - 3.75d) * theta) + 1.0d) * theta) - 0.08333333333333333d);
        double b22 = this.h * (((((((135.0d * theta) / 8.0d) - 30.375d) * theta) + 14.34375d) * theta2) - 0.84375d);
        double b32 = this.h * (((((((-30.0d) * theta) + 50.666666666666664d) * theta) - 22.0d) * theta2) + 1.3333333333333333d);
        double b33 = this.h;
        double b42 = b33 * ((theta2 * (((((125.0d * theta) / 8.0d) - 26.041666666666668d) * theta) + 11.71875d)) - 1.3020833333333333d);
        double b43 = this.h;
        double b52 = b43 * (((((5.0d * theta) / 12.0d) - 0.3125d) * theta2) - 0.10416666666666667d);
        int i2 = 0;
        while (i2 < this.interpolatedState.length) {
            double yDot02 = this.yDotK[0][i2];
            double yDot22 = this.yDotK[2][i2];
            double yDot32 = this.yDotK[3][i2];
            double yDot42 = this.yDotK[4][i2];
            double yDot52 = this.yDotK[5][i2];
            int i3 = i2;
            this.interpolatedState[i3] = this.currentState[i3] + (b02 * yDot02) + (b22 * yDot22) + (b32 * yDot32) + (b42 * yDot42) + (b52 * yDot52);
            this.interpolatedDerivatives[i3] = (bDot0 * yDot02) + (bDot2 * yDot22) + (bDot3 * yDot32) + (bDot4 * yDot42) + (bDot5 * yDot52);
            i2 = i3 + 1;
        }
    }
}
