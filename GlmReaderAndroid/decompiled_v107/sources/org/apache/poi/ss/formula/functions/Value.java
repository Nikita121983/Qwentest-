package org.apache.poi.ss.formula.functions;

import java.time.DateTimeException;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public final class Value extends Fixed1ArgFunction implements ArrayFunction {
    private static final int MIN_DISTANCE_BETWEEN_THOUSANDS_SEPARATOR = 4;
    private static final Double ZERO = Double.valueOf(0.0d);

    @Override // org.apache.poi.ss.formula.functions.Function1Arg
    /* renamed from: evaluate, reason: merged with bridge method [inline-methods] */
    public ValueEval m2532lambda$evaluateArray$0$orgapachepoissformulafunctionsValue(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        try {
            ValueEval veText = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
            String strText = OperandResolver.coerceValueToString(veText);
            if (veText == BlankEval.instance) {
                return new NumberEval(0.0d);
            }
            if (StringUtil.isBlank(strText)) {
                return ErrorEval.VALUE_INVALID;
            }
            Double result = convertTextToNumber(strText);
            if (result == null) {
                result = parseDateTime(strText);
            }
            if (result == null) {
                return ErrorEval.VALUE_INVALID;
            }
            return new NumberEval(result.doubleValue());
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override // org.apache.poi.ss.formula.functions.ArrayFunction
    public ValueEval evaluateArray(ValueEval[] args, final int srcRowIndex, final int srcColumnIndex) {
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluateOneArrayArg(args[0], srcRowIndex, srcColumnIndex, new java.util.function.Function() { // from class: org.apache.poi.ss.formula.functions.Value$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Value.this.m2532lambda$evaluateArray$0$orgapachepoissformulafunctionsValue(srcRowIndex, srcColumnIndex, (ValueEval) obj);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Double convertTextToNumber(java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.Value.convertTextToNumber(java.lang.String):java.lang.Double");
    }

    public static Double parseDateTime(String pText) {
        try {
            return DateUtil.parseDateTime(pText);
        } catch (DateTimeException e) {
            return null;
        }
    }
}
