package org.apache.commons.math3.analysis;

@Deprecated
/* loaded from: classes10.dex */
public interface DifferentiableMultivariateFunction extends MultivariateFunction {
    MultivariateVectorFunction gradient();

    MultivariateFunction partialDerivative(int i);
}
