package org.apache.poi.ss.formula.atp;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.usermodel.DateUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class WorkdayIntlFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new WorkdayIntlFunction(ArgumentsEvaluator.instance);
    private ArgumentsEvaluator evaluator;

    private WorkdayIntlFunction(ArgumentsEvaluator anEvaluator) {
        this.evaluator = anEvaluator;
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        double start;
        int days;
        int weekendType;
        if (args.length < 2 || args.length > 4) {
            return ErrorEval.VALUE_INVALID;
        }
        int srcCellRow = ec.getRowIndex();
        int srcCellCol = ec.getColumnIndex();
        int weekendType2 = 1;
        try {
            start = this.evaluator.evaluateDateArg(args[0], srcCellRow, srcCellCol);
            days = (int) Math.floor(this.evaluator.evaluateNumberArg(args[1], srcCellRow, srcCellCol));
            if (args.length < 3) {
                weekendType = 1;
            } else {
                if (args[2] != BlankEval.instance) {
                    weekendType2 = (int) this.evaluator.evaluateNumberArg(args[2], srcCellRow, srcCellCol);
                }
                if (WorkdayCalculator.instance.getValidWeekendTypes().contains(Integer.valueOf(weekendType2))) {
                    weekendType = weekendType2;
                } else {
                    return ErrorEval.NUM_ERROR;
                }
            }
        } catch (EvaluationException e) {
        }
        try {
            ValueEval holidaysCell = args.length >= 4 ? args[3] : null;
            double[] holidays = this.evaluator.evaluateDatesArg(holidaysCell, srcCellRow, srcCellCol);
            return new NumberEval(DateUtil.getExcelDate(WorkdayCalculator.instance.calculateWorkdays(start, days, weekendType, holidays)));
        } catch (EvaluationException e2) {
            return ErrorEval.VALUE_INVALID;
        }
    }
}
