package org.apache.commons.math3.analysis.differentiation;

import org.apache.commons.math3.analysis.MultivariateVectorFunction;

/* loaded from: classes10.dex */
public class GradientFunction implements MultivariateVectorFunction {
    private final MultivariateDifferentiableFunction f;

    public GradientFunction(MultivariateDifferentiableFunction f) {
        this.f = f;
    }

    @Override // org.apache.commons.math3.analysis.MultivariateVectorFunction
    public double[] value(double[] point) {
        DerivativeStructure[] dsX = new DerivativeStructure[point.length];
        for (int i = 0; i < point.length; i++) {
            dsX[i] = new DerivativeStructure(point.length, 1, i, point[i]);
        }
        DerivativeStructure dsY = this.f.value(dsX);
        double[] y = new double[point.length];
        int[] orders = new int[point.length];
        for (int i2 = 0; i2 < point.length; i2++) {
            orders[i2] = 1;
            y[i2] = dsY.getPartialDerivative(orders);
            orders[i2] = 0;
        }
        return y;
    }
}
