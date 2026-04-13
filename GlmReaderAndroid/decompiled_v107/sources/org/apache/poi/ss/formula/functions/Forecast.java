package org.apache.poi.ss.formula.functions;

import java.util.List;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public class Forecast extends Fixed3ArgFunction implements FreeRefFunction {
    public static final Forecast instance = new Forecast();

    private Forecast() {
    }

    @Override // org.apache.poi.ss.formula.functions.Function3Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        try {
            Double x = evaluateValue(arg0, srcRowIndex, srcColumnIndex);
            if (x != null && !x.isNaN()) {
                if (!x.isInfinite()) {
                    List<DoubleList> arrays = ArrayFunctionUtils.getNumberArrays(arg1, arg2);
                    double[] arrY = arrays.get(0).toArray();
                    double[] arrX = arrays.get(1).toArray();
                    double averageY = MathX.average(arrY);
                    double averageX = MathX.average(arrX);
                    double bnum = 0.0d;
                    double bdem = 0.0d;
                    int len = arrY.length;
                    int i = 0;
                    while (i < len) {
                        int i2 = i;
                        double diff0 = arrX[i] - averageX;
                        bnum += (arrY[i2] - averageY) * diff0;
                        bdem += Math.pow(diff0, 2.0d);
                        i = i2 + 1;
                    }
                    if (bdem == 0.0d) {
                        return ErrorEval.DIV_ZERO;
                    }
                    double b = bnum / bdem;
                    double a = averageY - (b * averageX);
                    return new NumberEval(a + (x.doubleValue() * b));
                }
            }
            return ErrorEval.VALUE_INVALID;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        } catch (Exception e2) {
            return ErrorEval.NA;
        }
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length != 3) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0], args[1], args[2]);
    }

    private static Double evaluateValue(ValueEval arg, int srcRowIndex, int srcColumnIndex) throws EvaluationException {
        ValueEval veText = OperandResolver.getSingleValue(arg, srcRowIndex, srcColumnIndex);
        String strText1 = OperandResolver.coerceValueToString(veText);
        return OperandResolver.parseDouble(strText1);
    }
}
