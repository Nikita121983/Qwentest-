package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

/* loaded from: classes10.dex */
class ClassicalRungeKuttaStepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public ClassicalRungeKuttaStepInterpolator() {
    }

    ClassicalRungeKuttaStepInterpolator(ClassicalRungeKuttaStepInterpolator interpolator) {
        super(interpolator);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new ClassicalRungeKuttaStepInterpolator(this);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
        double oneMinusTheta = 1.0d - theta;
        double oneMinus2Theta = 1.0d - (theta * 2.0d);
        double coeffDot1 = oneMinusTheta * oneMinus2Theta;
        double coeffDot23 = theta * 2.0d * oneMinusTheta;
        double coeffDot4 = (-theta) * oneMinus2Theta;
        if (this.previousState != null && theta <= 0.5d) {
            double fourTheta2 = theta * 4.0d * theta;
            char c = 3;
            double s = (this.h * theta) / 6.0d;
            double coeff1 = ((6.0d - (9.0d * theta)) + fourTheta2) * s;
            double coeff23 = ((theta * 6.0d) - fourTheta2) * s;
            double coeff4 = (((-3.0d) * theta) + fourTheta2) * s;
            int i = 0;
            while (true) {
                char c2 = c;
                if (i < this.interpolatedState.length) {
                    double yDot1 = this.yDotK[0][i];
                    double yDot23 = this.yDotK[1][i] + this.yDotK[2][i];
                    double yDot4 = this.yDotK[c2][i];
                    this.interpolatedState[i] = this.previousState[i] + (coeff1 * yDot1) + (coeff23 * yDot23) + (coeff4 * yDot4);
                    this.interpolatedDerivatives[i] = (coeffDot1 * yDot1) + (coeffDot23 * yDot23) + (coeffDot4 * yDot4);
                    i++;
                    c = c2;
                } else {
                    return;
                }
            }
        } else {
            double fourTheta = theta * 4.0d;
            double s2 = oneMinusThetaH / 6.0d;
            double coeff12 = ((((-fourTheta) + 5.0d) * theta) - 1.0d) * s2;
            double coeff232 = (((fourTheta - 2.0d) * theta) - 2.0d) * s2;
            double coeff42 = ((((-fourTheta) - 1.0d) * theta) - 1.0d) * s2;
            int i2 = 0;
            while (true) {
                double fourTheta3 = fourTheta;
                if (i2 < this.interpolatedState.length) {
                    double yDot12 = this.yDotK[0][i2];
                    double yDot232 = this.yDotK[1][i2] + this.yDotK[2][i2];
                    double yDot42 = this.yDotK[3][i2];
                    this.interpolatedState[i2] = this.currentState[i2] + (coeff12 * yDot12) + (coeff232 * yDot232) + (coeff42 * yDot42);
                    this.interpolatedDerivatives[i2] = (coeffDot1 * yDot12) + (coeffDot23 * yDot232) + (coeffDot4 * yDot42);
                    i2++;
                    fourTheta = fourTheta3;
                } else {
                    return;
                }
            }
        }
    }
}
