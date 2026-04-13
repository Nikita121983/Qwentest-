package org.apache.commons.math3.ml.neuralnet.sofm.util;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class ExponentialDecayFunction {
    private final double a;
    private final double oneOverB;

    public ExponentialDecayFunction(double initValue, double valueAtNumCall, long numCall) {
        if (initValue <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(initValue));
        }
        if (valueAtNumCall <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(valueAtNumCall));
        }
        if (valueAtNumCall >= initValue) {
            throw new NumberIsTooLargeException(Double.valueOf(valueAtNumCall), Double.valueOf(initValue), false);
        }
        if (numCall <= 0) {
            throw new NotStrictlyPositiveException(Long.valueOf(numCall));
        }
        this.a = initValue;
        this.oneOverB = (-FastMath.log(valueAtNumCall / initValue)) / numCall;
    }

    public double value(long numCall) {
        return this.a * FastMath.exp((-numCall) * this.oneOverB);
    }
}
