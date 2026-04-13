package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class LutherIntegrator extends RungeKuttaIntegrator {
    private static final double Q = FastMath.sqrt(21.0d);
    private static final double[] STATIC_C = {1.0d, 0.5d, 0.6666666666666666d, (7.0d - Q) / 14.0d, (Q + 7.0d) / 14.0d, 1.0d};
    private static final double[][] STATIC_A = {new double[]{1.0d}, new double[]{0.375d, 0.125d}, new double[]{0.2962962962962963d, 0.07407407407407407d, 0.2962962962962963d}, new double[]{((Q * 9.0d) - 21.0d) / 392.0d, ((Q * 8.0d) - 56.0d) / 392.0d, (336.0d - (Q * 48.0d)) / 392.0d, ((Q * 3.0d) - 63.0d) / 392.0d}, new double[]{((-1155.0d) - (Q * 255.0d)) / 1960.0d, ((-280.0d) - (Q * 40.0d)) / 1960.0d, (0.0d - (Q * 320.0d)) / 1960.0d, ((Q * 363.0d) + 63.0d) / 1960.0d, ((Q * 392.0d) + 2352.0d) / 1960.0d}, new double[]{((Q * 105.0d) + 330.0d) / 180.0d, ((Q * 0.0d) + 120.0d) / 180.0d, ((Q * 280.0d) - 200.0d) / 180.0d, (126.0d - (Q * 189.0d)) / 180.0d, ((-686.0d) - (Q * 126.0d)) / 180.0d, (490.0d - (Q * 70.0d)) / 180.0d}};
    private static final double[] STATIC_B = {0.05d, 0.0d, 0.35555555555555557d, 0.0d, 0.2722222222222222d, 0.2722222222222222d, 0.05d};

    public LutherIntegrator(double step) {
        super("Luther", STATIC_C, STATIC_A, STATIC_B, new LutherStepInterpolator(), step);
    }
}
