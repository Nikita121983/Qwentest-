package org.apache.poi.ss.formula.functions;

import java.math.RoundingMode;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class CeilingMath implements FreeRefFunction {
    public static final CeilingMath instance = new CeilingMath();

    private CeilingMath() {
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length == 0) {
            return ErrorEval.VALUE_INVALID;
        }
        Double d = null;
        try {
            Double xval = evaluateValue(args[0], ec.getRowIndex(), ec.getColumnIndex());
            if (xval == null) {
                return ErrorEval.VALUE_INVALID;
            }
            double multiplier = 1.0d;
            if (args.length > 1) {
                Double arg1Val = evaluateValue(args[1], ec.getRowIndex(), ec.getColumnIndex());
                multiplier = arg1Val != null ? arg1Val.doubleValue() : 1.0d;
            }
            Double arg1Val2 = null;
            if (args.length > 2) {
                Double arg2Val = evaluateValue(args[2], ec.getRowIndex(), ec.getColumnIndex());
                if (arg2Val != null && arg2Val.doubleValue() < 0.0d) {
                    d = 1;
                }
                arg1Val2 = d;
            }
            if (arg1Val2 == null || xval.doubleValue() >= 0.0d) {
                if (multiplier != 1.0d) {
                    RoundingMode mode = multiplier < 0.0d ? RoundingMode.FLOOR : RoundingMode.CEILING;
                    return new NumberEval(MathX.scaledRoundUsingBigDecimal(xval.doubleValue(), multiplier, mode));
                }
                return new NumberEval(Math.ceil(xval.doubleValue()));
            }
            if (multiplier != 1.0d) {
                RoundingMode mode2 = multiplier < 0.0d ? RoundingMode.CEILING : RoundingMode.FLOOR;
                return new NumberEval(MathX.scaledRoundUsingBigDecimal(xval.doubleValue(), multiplier, mode2));
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
