package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.util.NumberToTextConverter;

/* loaded from: classes10.dex */
public class Sqrtpi implements FreeRefFunction {
    public static final Sqrtpi instance = new Sqrtpi();

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0]);
    }

    private ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        try {
            ValueEval v1 = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
            double d = OperandResolver.coerceValueToDouble(v1);
            if (isInvalidInput(d)) {
                return ErrorEval.NUM_ERROR;
            }
            double result = Math.sqrt(3.141592653589793d * d);
            return new NumberEval(Double.parseDouble(NumberToTextConverter.toText(result)));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private boolean isInvalidInput(double d) {
        return d < 0.0d;
    }
}
