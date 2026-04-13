package org.apache.poi.ss.formula.functions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.DateUtil;

/* loaded from: classes10.dex */
public class WeekNum extends Fixed2ArgFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new WeekNum();
    private static final NumberEval DEFAULT_RETURN_TYPE = new NumberEval(1.0d);
    private static final HashSet<Integer> VALID_RETURN_TYPES = new HashSet<>(Arrays.asList(1, 2, 11, 12, 13, 14, 15, 16, 17, 21));
    private WeekFields SUNDAY_START = WeekFields.of(DayOfWeek.SUNDAY, 1);
    private WeekFields MONDAY_START = WeekFields.of(DayOfWeek.MONDAY, 1);
    private WeekFields TUESDAY_START = WeekFields.of(DayOfWeek.TUESDAY, 1);
    private WeekFields WEDNESDAY_START = WeekFields.of(DayOfWeek.WEDNESDAY, 1);
    private WeekFields THURSDAY_START = WeekFields.of(DayOfWeek.THURSDAY, 1);
    private WeekFields FRIDAY_START = WeekFields.of(DayOfWeek.FRIDAY, 1);
    private WeekFields SATURDAY_START = WeekFields.of(DayOfWeek.SATURDAY, 1);

    @Override // org.apache.poi.ss.formula.functions.Function2Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval serialNumVE, ValueEval returnTypeVE) {
        int returnType;
        try {
            double serialNum = NumericFunction.singleOperandEvaluate(serialNumVE, srcRowIndex, srcColumnIndex);
            try {
                Date dateToConvert = DateUtil.getJavaDate(serialNum, false);
                LocalDate localDate = dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                try {
                    ValueEval ve = OperandResolver.getSingleValue(returnTypeVE, srcRowIndex, srcColumnIndex);
                    if (ve instanceof MissingArgEval) {
                        returnType = (int) DEFAULT_RETURN_TYPE.getNumberValue();
                    } else {
                        returnType = OperandResolver.coerceValueToInt(ve);
                    }
                    if (!VALID_RETURN_TYPES.contains(Integer.valueOf(returnType))) {
                        return ErrorEval.NUM_ERROR;
                    }
                    return new NumberEval(getWeekNo(localDate, returnType));
                } catch (EvaluationException e) {
                    return ErrorEval.NUM_ERROR;
                }
            } catch (Exception e2) {
                return ErrorEval.NUM_ERROR;
            }
        } catch (EvaluationException e3) {
            return ErrorEval.VALUE_INVALID;
        }
    }

    public int getWeekNo(LocalDate date, int weekStartOn) {
        if (weekStartOn == 1 || weekStartOn == 17) {
            return date.get(this.SUNDAY_START.weekOfYear());
        }
        if (weekStartOn == 2 || weekStartOn == 11) {
            return date.get(this.MONDAY_START.weekOfYear());
        }
        if (weekStartOn == 12) {
            return date.get(this.TUESDAY_START.weekOfYear());
        }
        if (weekStartOn == 13) {
            return date.get(this.WEDNESDAY_START.weekOfYear());
        }
        if (weekStartOn == 14) {
            return date.get(this.THURSDAY_START.weekOfYear());
        }
        if (weekStartOn == 15) {
            return date.get(this.FRIDAY_START.weekOfYear());
        }
        if (weekStartOn == 16) {
            return date.get(this.SATURDAY_START.weekOfYear());
        }
        return date.get(WeekFields.ISO.weekOfYear());
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length == 1) {
            return evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0], DEFAULT_RETURN_TYPE);
        }
        if (args.length == 2) {
            return evaluate(ec.getRowIndex(), ec.getColumnIndex(), args[0], args[1]);
        }
        return ErrorEval.VALUE_INVALID;
    }
}
