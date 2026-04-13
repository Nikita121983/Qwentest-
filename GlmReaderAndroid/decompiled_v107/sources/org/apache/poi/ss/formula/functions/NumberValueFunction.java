package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;

/* loaded from: classes10.dex */
public final class NumberValueFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new NumberValueFunction();

    private NumberValueFunction() {
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00fd  */
    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.poi.ss.formula.eval.ValueEval evaluate(org.apache.poi.ss.formula.eval.ValueEval[] r21, org.apache.poi.ss.formula.OperationEvaluationContext r22) {
        /*
            Method dump skipped, instructions count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.NumberValueFunction.evaluate(org.apache.poi.ss.formula.eval.ValueEval[], org.apache.poi.ss.formula.OperationEvaluationContext):org.apache.poi.ss.formula.eval.ValueEval");
    }

    private static void checkValue(double result) throws EvaluationException {
        if (Double.isNaN(result) || Double.isInfinite(result)) {
            throw new EvaluationException(ErrorEval.NUM_ERROR);
        }
    }
}
