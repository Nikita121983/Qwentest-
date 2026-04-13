package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.functions.XYNumericFunction;

/* loaded from: classes10.dex */
public final class Sumxmy2 extends XYNumericFunction {
    private static final XYNumericFunction.Accumulator XMinusYSquaredAccumulator = new XYNumericFunction.Accumulator() { // from class: org.apache.poi.ss.formula.functions.Sumxmy2$$ExternalSyntheticLambda0
        @Override // org.apache.poi.ss.formula.functions.XYNumericFunction.Accumulator
        public final double accumulate(double d, double d2) {
            return Sumxmy2.lambda$static$0(d, d2);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ double lambda$static$0(double x, double y) {
        double xmy = x - y;
        return xmy * xmy;
    }

    @Override // org.apache.poi.ss.formula.functions.XYNumericFunction
    protected XYNumericFunction.Accumulator createAccumulator() {
        return XMinusYSquaredAccumulator;
    }
}
