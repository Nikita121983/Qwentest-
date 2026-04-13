package org.apache.commons.math3.analysis;

/* loaded from: classes10.dex */
public interface ParametricUnivariateFunction {
    double[] gradient(double d, double... dArr);

    double value(double d, double... dArr);
}
