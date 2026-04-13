package org.apache.poi.ss.formula.functions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class Fixed implements Function1Arg, Function2Arg, Function3Arg {
    @Override // org.apache.poi.ss.formula.functions.Function3Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        return fixed(arg0, arg1, arg2, srcRowIndex, srcColumnIndex);
    }

    @Override // org.apache.poi.ss.formula.functions.Function2Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        return fixed(arg0, arg1, BoolEval.FALSE, srcRowIndex, srcColumnIndex);
    }

    @Override // org.apache.poi.ss.formula.functions.Function1Arg
    /* renamed from: evaluate */
    public ValueEval m2524x1f30617e(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        return fixed(arg0, new NumberEval(2.0d), BoolEval.FALSE, srcRowIndex, srcColumnIndex);
    }

    @Override // org.apache.poi.ss.formula.functions.Function
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        switch (args.length) {
            case 1:
                return fixed(args[0], new NumberEval(2.0d), BoolEval.FALSE, srcRowIndex, srcColumnIndex);
            case 2:
                return fixed(args[0], args[1], BoolEval.FALSE, srcRowIndex, srcColumnIndex);
            case 3:
                return fixed(args[0], args[1], args[2], srcRowIndex, srcColumnIndex);
            default:
                return ErrorEval.VALUE_INVALID;
        }
    }

    private ValueEval fixed(ValueEval numberParam, ValueEval placesParam, ValueEval skipThousandsSeparatorParam, int srcRowIndex, int srcColumnIndex) {
        boolean z;
        try {
            ValueEval numberValueEval = OperandResolver.getSingleValue(numberParam, srcRowIndex, srcColumnIndex);
            BigDecimal number = BigDecimal.valueOf(OperandResolver.coerceValueToDouble(numberValueEval));
            try {
                ValueEval placesValueEval = OperandResolver.getSingleValue(placesParam, srcRowIndex, srcColumnIndex);
                int places = OperandResolver.coerceValueToInt(placesValueEval);
                ValueEval skipThousandsSeparatorValueEval = OperandResolver.getSingleValue(skipThousandsSeparatorParam, srcRowIndex, srcColumnIndex);
                Boolean skipThousandsSeparator = OperandResolver.coerceValueToBoolean(skipThousandsSeparatorValueEval, false);
                BigDecimal number2 = number.setScale(places, RoundingMode.HALF_UP);
                NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
                DecimalFormat formatter = (DecimalFormat) nf;
                if (skipThousandsSeparator != null && skipThousandsSeparator.booleanValue()) {
                    z = false;
                    formatter.setGroupingUsed(z);
                    formatter.setMinimumFractionDigits(Math.max(places, 0));
                    formatter.setMaximumFractionDigits(Math.max(places, 0));
                    String numberString = formatter.format(number2.doubleValue());
                    return new StringEval(numberString);
                }
                z = true;
                formatter.setGroupingUsed(z);
                formatter.setMinimumFractionDigits(Math.max(places, 0));
                formatter.setMaximumFractionDigits(Math.max(places, 0));
                String numberString2 = formatter.format(number2.doubleValue());
                return new StringEval(numberString2);
            } catch (EvaluationException e) {
                e = e;
                return e.getErrorEval();
            }
        } catch (EvaluationException e2) {
            e = e2;
        }
    }
}
