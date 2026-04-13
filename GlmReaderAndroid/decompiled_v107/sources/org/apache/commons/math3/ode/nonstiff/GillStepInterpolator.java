package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
class GillStepInterpolator extends RungeKuttaStepInterpolator {
    private static final double ONE_MINUS_INV_SQRT_2 = 1.0d - FastMath.sqrt(0.5d);
    private static final double ONE_PLUS_INV_SQRT_2 = FastMath.sqrt(0.5d) + 1.0d;
    private static final long serialVersionUID = 20111120;

    public GillStepInterpolator() {
    }

    GillStepInterpolator(GillStepInterpolator interpolator) {
        super(interpolator);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new GillStepInterpolator(this);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
        double twoTheta = theta * 2.0d;
        double fourTheta2 = twoTheta * twoTheta;
        double coeffDot1 = ((twoTheta - 3.0d) * theta) + 1.0d;
        double cDot23 = (1.0d - theta) * twoTheta;
        double coeffDot2 = ONE_MINUS_INV_SQRT_2 * cDot23;
        double coeffDot3 = ONE_PLUS_INV_SQRT_2 * cDot23;
        double coeffDot4 = (twoTheta - 1.0d) * theta;
        if (this.previousState != null && theta <= 0.5d) {
            double s = (this.h * theta) / 6.0d;
            double c23 = ((theta * 6.0d) - fourTheta2) * s;
            double coeff1 = ((6.0d - (9.0d * theta)) + fourTheta2) * s;
            double coeff2 = ONE_MINUS_INV_SQRT_2 * c23;
            double coeff3 = ONE_PLUS_INV_SQRT_2 * c23;
            double coeff4 = (((-3.0d) * theta) + fourTheta2) * s;
            int i = 0;
            while (i < this.interpolatedState.length) {
                double yDot1 = this.yDotK[0][i];
                double yDot2 = this.yDotK[1][i];
                double yDot3 = this.yDotK[2][i];
                double yDot4 = this.yDotK[3][i];
                int i2 = i;
                this.interpolatedState[i2] = this.previousState[i2] + (coeff1 * yDot1) + (coeff2 * yDot2) + (coeff3 * yDot3) + (coeff4 * yDot4);
                this.interpolatedDerivatives[i2] = (coeffDot1 * yDot1) + (coeffDot2 * yDot2) + (coeffDot3 * yDot3) + (coeffDot4 * yDot4);
                i = i2 + 1;
            }
            return;
        }
        double s2 = oneMinusThetaH / 6.0d;
        double c232 = ((twoTheta + 2.0d) - fourTheta2) * s2;
        double coeff12 = ((1.0d - (5.0d * theta)) + fourTheta2) * s2;
        double coeff22 = ONE_MINUS_INV_SQRT_2 * c232;
        double coeff32 = ONE_PLUS_INV_SQRT_2 * c232;
        double coeff42 = (theta + 1.0d + fourTheta2) * s2;
        int i3 = 0;
        while (i3 < this.interpolatedState.length) {
            double yDot12 = this.yDotK[0][i3];
            double yDot22 = this.yDotK[1][i3];
            double yDot32 = this.yDotK[2][i3];
            double yDot42 = this.yDotK[3][i3];
            int i4 = i3;
            this.interpolatedState[i4] = (((this.currentState[i4] - (coeff12 * yDot12)) - (coeff22 * yDot22)) - (coeff32 * yDot32)) - (coeff42 * yDot42);
            this.interpolatedDerivatives[i4] = (coeffDot1 * yDot12) + (coeffDot2 * yDot22) + (coeffDot3 * yDot32) + (coeffDot4 * yDot42);
            i3 = i4 + 1;
        }
    }
}
