package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.functions.XYNumericFunction;

/* loaded from: classes10.dex */
public final class Sumx2py2 extends XYNumericFunction {
    private static final XYNumericFunction.Accumulator XSquaredPlusYSquaredAccumulator = new XYNumericFunction.Accumulator() { // from class: org.apache.poi.ss.formula.functions.Sumx2py2$$ExternalSyntheticLambda0
        @Override // org.apache.poi.ss.formula.functions.XYNumericFunction.Accumulator
        public final double accumulate(double d, double d2) {
            return Sumx2py2.lambda$static$0(d, d2);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ double lambda$static$0(double x, double y) {
        return (x * x) + (y * y);
    }

    @Override // org.apache.poi.ss.formula.functions.XYNumericFunction
    protected XYNumericFunction.Accumulator createAccumulator() {
        return XSquaredPlusYSquaredAccumulator;
    }
}
