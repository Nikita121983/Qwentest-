package org.apache.poi.ss.formula.functions;

import java.util.regex.Matcher;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public class ImReal extends Fixed1ArgFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new ImReal();

    @Override // org.apache.poi.ss.formula.functions.Function1Arg
    /* renamed from: evaluate */
    public ValueEval m2532lambda$evaluateArray$0$orgapachepoissformulafunctionsValue(int srcRowIndex, int srcColumnIndex, ValueEval inumberVE) {
        try {
            ValueEval veText1 = OperandResolver.getSingleValue(inumberVE, srcRowIndex, srcColumnIndex);
            String iNumber = OperandResolver.coerceValueToString(veText1);
            Matcher m = Imaginary.COMPLEX_NUMBER_PATTERN.matcher(iNumber);
            if (!m.matches()) {
                return ErrorEval.NUM_ERROR;
            }
            String realGroup = m.group(2);
            if (realGroup.isEmpty()) {
                return new StringEval("0");
            }
            String realSign = m.group(1);
            String groupRealNumber = m.group(2);
            String sign = "+".equals(realSign) ? "" : realSign;
            String real = groupRealNumber.isEmpty() ? "1" : groupRealNumber;
            return new StringEval(sign + real);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return m2532lambda$evaluateArray$0$orgapachepoissformulafunctionsValue(ec.getRowIndex(), ec.getColumnIndex(), args[0]);
    }
}
