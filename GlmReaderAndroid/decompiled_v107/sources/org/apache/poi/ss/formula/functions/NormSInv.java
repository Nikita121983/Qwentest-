package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class NormSInv extends Fixed1ArgFunction implements FreeRefFunction {
    public static final NormSInv instance = new NormSInv();

    private NormSInv() {
    }

    @Override // org.apache.poi.ss.formula.functions.Function1Arg
    /* renamed from: evaluate */
    public ValueEval m2532lambda$evaluateArray$0$orgapachepoissformulafunctionsValue(int srcRowIndex, int srcColumnIndex, ValueEval arg1) {
        try {
            Double probability = evaluateValue(arg1, srcRowIndex, srcColumnIndex);
            if (probability == null) {
                return ErrorEval.VALUE_INVALID;
            }
            if (probability.doubleValue() > 0.0d && probability.doubleValue() < 1.0d) {
                return new NumberEval(NormInv.inverse(probability.doubleValue(), 0.0d, 1.0d));
            }
            return ErrorEval.NUM_ERROR;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length == 1) {
            return m2532lambda$evaluateArray$0$orgapachepoissformulafunctionsValue(ec.getRowIndex(), ec.getColumnIndex(), args[0]);
        }
        return ErrorEval.VALUE_INVALID;
    }

    private static Double evaluateValue(ValueEval arg, int srcRowIndex, int srcColumnIndex) throws EvaluationException {
        ValueEval veText = OperandResolver.getSingleValue(arg, srcRowIndex, srcColumnIndex);
        String strText1 = OperandResolver.coerceValueToString(veText);
        return OperandResolver.parseDouble(strText1);
    }
}
