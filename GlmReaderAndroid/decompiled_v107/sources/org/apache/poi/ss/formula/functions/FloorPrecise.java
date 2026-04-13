package org.apache.poi.ss.formula.functions;

import java.math.RoundingMode;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class FloorPrecise implements FreeRefFunction {
    public static final FloorPrecise instance = new FloorPrecise();

    private FloorPrecise() {
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length == 0) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            Double xval = evaluateValue(args[0], ec.getRowIndex(), ec.getColumnIndex());
            if (xval == null) {
                return ErrorEval.VALUE_INVALID;
            }
            double multiplier = 1.0d;
            if (args.length > 1) {
                Double arg1Val = evaluateValue(args[1], ec.getRowIndex(), ec.getColumnIndex());
                multiplier = arg1Val != null ? Math.abs(arg1Val.doubleValue()) : 1.0d;
            }
            if (multiplier != 1.0d) {
                return new NumberEval(MathX.scaledRoundUsingBigDecimal(xval.doubleValue(), multiplier, RoundingMode.FLOOR));
            }
            return new NumberEval(Math.floor(xval.doubleValue()));
        } catch (EvaluationException evaluationException) {
            return evaluationException.getErrorEval();
        }
    }

    private static Double evaluateValue(ValueEval arg, int srcRowIndex, int srcColumnIndex) throws EvaluationException {
        ValueEval veText = OperandResolver.getSingleValue(arg, srcRowIndex, srcColumnIndex);
        String strText1 = OperandResolver.coerceValueToString(veText);
        return OperandResolver.parseDouble(strText1);
    }
}
