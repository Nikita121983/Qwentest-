package org.apache.poi.ss.formula.functions;

import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class DollarDe extends Fixed2ArgFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new DollarDe();

    @Override // org.apache.poi.ss.formula.functions.Function2Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg1, ValueEval arg2) {
        try {
            Double number1 = evaluateValue(arg1, srcRowIndex, srcColumnIndex);
            if (number1 != null) {
                try {
                    Double number2 = evaluateValue(arg2, srcRowIndex, srcColumnIndex);
                    if (number2 == null) {
                        return ErrorEval.VALUE_INVALID;
                    }
                    int fraction = number2.intValue();
                    if (fraction < 0) {
                        return ErrorEval.NUM_ERROR;
                    }
                    if (fraction == 0) {
                        return ErrorEval.DIV_ZERO;
                    }
                    int fractionLength = String.valueOf(fraction).length();
                    boolean negative = false;
                    long valueLong = number1.longValue();
                    if (valueLong < 0) {
                        negative = true;
                        valueLong = -valueLong;
                        number1 = Double.valueOf(-number1.doubleValue());
                    }
                    double valueFractional = number1.doubleValue() - valueLong;
                    if (valueFractional == 0.0d) {
                        return new NumberEval(valueLong);
                    }
                    BigDecimal inflated = BigDecimal.valueOf(valueFractional).multiply(BigDecimal.valueOf(Math.pow(10.0d, fractionLength)));
                    BigDecimal calc = inflated.divide(BigDecimal.valueOf(fraction), MathContext.DECIMAL128);
                    BigDecimal result = calc.add(BigDecimal.valueOf(valueLong));
                    if (negative) {
                        result = result.multiply(BigDecimal.valueOf(-1L));
                    }
                    return new NumberEval(result.doubleValue());
                } catch (EvaluationException e) {
                    e = e;
                    return e.getErrorEval();
                }
            }
            return ErrorEval.VALUE_INVALID;
        } catch (EvaluationException e2) {
            e = e2;
        }
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length == 2) {
            return evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0], args[1]);
        }
        return ErrorEval.VALUE_INVALID;
    }

    private static Double evaluateValue(ValueEval arg, int srcRowIndex, int srcColumnIndex) throws EvaluationException {
        ValueEval veText = OperandResolver.getSingleValue(arg, srcRowIndex, srcColumnIndex);
        String strText1 = OperandResolver.coerceValueToString(veText);
        return OperandResolver.parseDouble(strText1);
    }
}
