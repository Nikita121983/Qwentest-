package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public class Log {
    private static final double LOG_10_TO_BASE_e = Math.log(10.0d);
    private static final double TEN = 10.0d;

    public static ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        double d1;
        if (args.length != 1 && args.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            double d0 = NumericFunction.singleOperandEvaluate(args[0], srcRowIndex, srcColumnIndex);
            if (args.length == 1) {
                d1 = Math.log(d0) / LOG_10_TO_BASE_e;
            } else {
                double d12 = NumericFunction.singleOperandEvaluate(args[1], srcRowIndex, srcColumnIndex);
                double logE = Math.log(d0);
                d1 = Double.compare(d12, 2.718281828459045d) == 0 ? logE : logE / Math.log(d12);
            }
            NumericFunction.checkValue(d1);
            return new NumberEval(d1);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
