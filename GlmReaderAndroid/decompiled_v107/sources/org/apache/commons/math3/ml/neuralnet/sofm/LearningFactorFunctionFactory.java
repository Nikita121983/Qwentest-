package org.apache.commons.math3.ml.neuralnet.sofm;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction;
import org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction;

/* loaded from: classes10.dex */
public class LearningFactorFunctionFactory {
    private LearningFactorFunctionFactory() {
    }

    public static LearningFactorFunction exponentialDecay(final double initValue, final double valueAtNumCall, final long numCall) {
        if (initValue <= 0.0d || initValue > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(initValue), 0, 1);
        }
        return new LearningFactorFunction() { // from class: org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunctionFactory.1
            private final ExponentialDecayFunction decay;

            {
                this.decay = new ExponentialDecayFunction(initValue, valueAtNumCall, numCall);
            }

            @Override // org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunction
            public double value(long n) {
                return this.decay.value(n);
            }
        };
    }

    public static LearningFactorFunction quasiSigmoidDecay(final double initValue, final double slope, final long numCall) {
        if (initValue <= 0.0d || initValue > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(initValue), 0, 1);
        }
        return new LearningFactorFunction() { // from class: org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunctionFactory.2
            private final QuasiSigmoidDecayFunction decay;

            {
                this.decay = new QuasiSigmoidDecayFunction(initValue, slope, numCall);
            }

            @Override // org.apache.commons.math3.ml.neuralnet.sofm.LearningFactorFunction
            public double value(long n) {
                return this.decay.value(n);
            }
        };
    }
}
