package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
class LutherStepInterpolator extends RungeKuttaStepInterpolator {
    private static final double Q = FastMath.sqrt(21.0d);
    private static final long serialVersionUID = 20140416;

    public LutherStepInterpolator() {
    }

    LutherStepInterpolator(LutherStepInterpolator interpolator) {
        super(interpolator);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected StepInterpolator doCopy() {
        return new LutherStepInterpolator(this);
    }

    @Override // org.apache.commons.math3.ode.sampling.AbstractStepInterpolator
    protected void computeInterpolatedStateAndDerivatives(double theta, double oneMinusThetaH) {
        double coeffDot1 = (((((((theta * 21.0d) - 47.0d) * theta) + 36.0d) * theta) - 10.8d) * theta) + 1.0d;
        double coeffDot3 = ((((((theta * 112.0d) - 202.66666666666666d) * theta) + 106.66666666666667d) * theta) - 13.866666666666667d) * theta;
        double coeffDot4 = (((((((theta * (-567.0d)) / 5.0d) + 194.4d) * theta) - 97.2d) * theta) + 12.96d) * theta;
        double coeffDot5 = ((((Q * 343.0d) + 833.0d) / 150.0d) + (((((-637.0d) - (Q * 357.0d)) / 30.0d) + (((((Q * 287.0d) + 392.0d) / 15.0d) + ((((-49.0d) - (Q * 49.0d)) * theta) / 5.0d)) * theta)) * theta)) * theta;
        double coeffDot6 = (((833.0d - (Q * 343.0d)) / 150.0d) + (((((Q * 357.0d) - 637.0d) / 30.0d) + ((((392.0d - (Q * 287.0d)) / 15.0d) + ((((Q * 49.0d) - 49.0d) * theta) / 5.0d)) * theta)) * theta)) * theta;
        double coeffDot7 = ((((3.0d * theta) - 3.0d) * theta) + 0.6d) * theta;
        if (this.previousState != null && theta <= 0.5d) {
            double coeff1 = ((((((((21.0d * theta) / 5.0d) - 11.75d) * theta) + 12.0d) * theta) - 5.4d) * theta) + 1.0d;
            double coeff3 = (((((((112.0d * theta) / 5.0d) - 50.666666666666664d) * theta) + 35.55555555555556d) * theta) - 6.933333333333334d) * theta;
            double coeff4 = ((((((((-567.0d) * theta) / 25.0d) + 48.6d) * theta) - 32.4d) * theta) + 6.48d) * theta;
            double coeff5 = ((((Q * 343.0d) + 833.0d) / 300.0d) + (((((-637.0d) - (Q * 357.0d)) / 90.0d) + (((((Q * 287.0d) + 392.0d) / 60.0d) + ((((-49.0d) - (Q * 49.0d)) * theta) / 25.0d)) * theta)) * theta)) * theta;
            double coeff6 = (((833.0d - (Q * 343.0d)) / 300.0d) + (((((Q * 357.0d) - 637.0d) / 90.0d) + ((((392.0d - (Q * 287.0d)) / 60.0d) + ((((Q * 49.0d) - 49.0d) * theta) / 25.0d)) * theta)) * theta)) * theta;
            double coeff7 = ((((0.75d * theta) - 1.0d) * theta) + 0.3d) * theta;
            int i = 0;
            while (i < this.interpolatedState.length) {
                double yDot1 = this.yDotK[0][i];
                double yDot2 = this.yDotK[1][i];
                double yDot3 = this.yDotK[2][i];
                double yDot4 = this.yDotK[3][i];
                double yDot5 = this.yDotK[4][i];
                double yDot6 = this.yDotK[5][i];
                double yDot7 = this.yDotK[6][i];
                int i2 = i;
                this.interpolatedState[i2] = this.previousState[i2] + (this.h * theta * ((coeff1 * yDot1) + (yDot2 * 0.0d) + (coeff3 * yDot3) + (coeff4 * yDot4) + (coeff5 * yDot5) + (coeff6 * yDot6) + (coeff7 * yDot7)));
                this.interpolatedDerivatives[i2] = (coeffDot1 * yDot1) + (yDot2 * 0.0d) + (coeffDot3 * yDot3) + (coeffDot4 * yDot4) + (coeffDot5 * yDot5) + (coeffDot6 * yDot6) + (coeffDot7 * yDot7);
                i = i2 + 1;
            }
            return;
        }
        double coeff12 = (((((((((-21.0d) * theta) / 5.0d) + 7.55d) * theta) - 4.45d) * theta) + 0.95d) * theta) - 0.05d;
        double coeff32 = (((((((((-112.0d) * theta) / 5.0d) + 28.266666666666666d) * theta) - 7.288888888888889d) * theta) - 0.35555555555555557d) * theta) - 0.35555555555555557d;
        double coeff42 = (((((567.0d * theta) / 25.0d) - 25.92d) * theta) + 6.48d) * theta * theta;
        double coeff52 = (((((((Q * 1029.0d) + 2254.0d) / 900.0d) + (((((-1372.0d) - (Q * 847.0d)) / 300.0d) + ((((Q * 49.0d) + 49.0d) * theta) / 25.0d)) * theta)) * theta) - 0.2722222222222222d) * theta) - 0.2722222222222222d;
        double coeff62 = ((((((2254.0d - (Q * 1029.0d)) / 900.0d) + (((((Q * 847.0d) - 1372.0d) / 300.0d) + (((49.0d - (Q * 49.0d)) * theta) / 25.0d)) * theta)) * theta) - 0.2722222222222222d) * theta) - 0.2722222222222222d;
        double coeff72 = ((((((-0.75d) * theta) + 0.25d) * theta) - 0.05d) * theta) - 0.05d;
        int i3 = 0;
        while (i3 < this.interpolatedState.length) {
            double yDot12 = this.yDotK[0][i3];
            double yDot22 = this.yDotK[1][i3];
            double yDot32 = this.yDotK[2][i3];
            double yDot42 = this.yDotK[3][i3];
            double yDot52 = this.yDotK[4][i3];
            double yDot62 = this.yDotK[5][i3];
            double yDot72 = this.yDotK[6][i3];
            double coeff13 = coeff12;
            this.interpolatedState[i3] = this.currentState[i3] + (((coeff13 * yDot12) + (yDot22 * 0.0d) + (coeff32 * yDot32) + (coeff42 * yDot42) + (coeff52 * yDot52) + (coeff62 * yDot62) + (coeff72 * yDot72)) * oneMinusThetaH);
            this.interpolatedDerivatives[i3] = (coeffDot1 * yDot12) + (yDot22 * 0.0d) + (coeffDot3 * yDot32) + (coeffDot4 * yDot42) + (coeffDot5 * yDot52) + (coeffDot6 * yDot62) + (coeffDot7 * yDot72);
            i3++;
            coeff12 = coeff13;
        }
    }
}
