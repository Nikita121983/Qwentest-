package org.apache.poi.ss.formula.functions;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.DateParser;
import org.apache.poi.util.LocaleUtil;

/* loaded from: classes10.dex */
public class Days implements FreeRefFunction {
    public static final Days instance = new Days();

    private Days() {
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0], args[1]);
    }

    private ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        try {
            LocalDate d0 = getDate(arg0, srcRowIndex, srcColumnIndex);
            LocalDate d1 = getDate(arg1, srcRowIndex, srcColumnIndex);
            double result = evaluate(d0, d1);
            return new NumberEval(result);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    private static double evaluate(LocalDate endDate, LocalDate startDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LocalDate getDate(ValueEval eval, int srcRowIndex, int srcColumnIndex) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(eval, srcRowIndex, srcColumnIndex);
        try {
            double d0 = NumericFunction.singleOperandEvaluate(ve, srcRowIndex, srcColumnIndex);
            return getDate(d0);
        } catch (Exception e) {
            String strText1 = OperandResolver.coerceValueToString(ve);
            return DateParser.parseLocalDate(strText1);
        }
    }

    private static LocalDate getDate(double date) {
        Date d = DateUtil.getJavaDate(date, false);
        return d.toInstant().atZone(LocaleUtil.getUserTimeZone().toZoneId()).toLocalDate();
    }
}
