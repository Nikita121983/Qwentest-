package org.apache.poi.ss.formula.functions;

import java.time.LocalDate;
import java.util.Calendar;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;

/* loaded from: classes10.dex */
public class Days360 extends Var2or3ArgFunction {
    @Override // org.apache.poi.ss.formula.functions.Function2Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        try {
            LocalDate d0 = Days.getDate(arg0, srcRowIndex, srcColumnIndex);
            LocalDate d1 = Days.getDate(arg1, srcRowIndex, srcColumnIndex);
            return new NumberEval(evaluate(DateUtil.getExcelDate(d0), DateUtil.getExcelDate(d1), false));
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override // org.apache.poi.ss.formula.functions.Function3Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        try {
            LocalDate d0 = Days.getDate(arg0, srcRowIndex, srcColumnIndex);
            try {
                LocalDate d1 = Days.getDate(arg1, srcRowIndex, srcColumnIndex);
                try {
                    ValueEval ve = OperandResolver.getSingleValue(arg2, srcRowIndex, srcColumnIndex);
                    boolean z = false;
                    Boolean method = OperandResolver.coerceValueToBoolean(ve, false);
                    double excelDate = DateUtil.getExcelDate(d0);
                    double excelDate2 = DateUtil.getExcelDate(d1);
                    if (method != null && method.booleanValue()) {
                        z = true;
                    }
                    return new NumberEval(evaluate(excelDate, excelDate2, z));
                } catch (EvaluationException e) {
                    e = e;
                    return e.getErrorEval();
                }
            } catch (EvaluationException e2) {
                e = e2;
                return e.getErrorEval();
            }
        } catch (EvaluationException e3) {
            e = e3;
        }
    }

    private static double evaluate(double d0, double d1, boolean method) {
        Calendar realStart = getDate(d0);
        Calendar realEnd = getDate(d1);
        int[] startingDate = getStartingDate(realStart, method);
        int[] endingDate = getEndingDate(realEnd, startingDate, method);
        return (((endingDate[0] * 360.0d) + (endingDate[1] * 30.0d)) + endingDate[2]) - (((startingDate[0] * 360.0d) + (startingDate[1] * 30.0d)) + startingDate[2]);
    }

    private static Calendar getDate(double date) {
        Calendar processedDate = LocaleUtil.getLocaleCalendar();
        processedDate.setTime(DateUtil.getJavaDate(date, false));
        return processedDate;
    }

    private static int[] getStartingDate(Calendar realStart, boolean method) {
        int yyyy = realStart.get(1);
        int mm = realStart.get(2);
        int dd = Math.min(30, realStart.get(5));
        if (!method && isLastDayOfMonth(realStart)) {
            dd = 30;
        }
        return new int[]{yyyy, mm, dd};
    }

    private static int[] getEndingDate(Calendar realEnd, int[] startingDate, boolean method) {
        int yyyy = realEnd.get(1);
        int mm = realEnd.get(2);
        int dd = Math.min(30, realEnd.get(5));
        if (!method && realEnd.get(5) == 31) {
            if (startingDate[2] < 30) {
                realEnd.set(5, 1);
                realEnd.add(2, 1);
                yyyy = realEnd.get(1);
                mm = realEnd.get(2);
                dd = 1;
            } else {
                dd = 30;
            }
        }
        return new int[]{yyyy, mm, dd};
    }

    private static boolean isLastDayOfMonth(Calendar date) {
        int dayOfMonth = date.get(5);
        int lastDayOfMonth = date.getActualMaximum(5);
        return dayOfMonth == lastDayOfMonth;
    }
}
