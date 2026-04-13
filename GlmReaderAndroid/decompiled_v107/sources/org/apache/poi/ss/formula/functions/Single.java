package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.AreaEvalBase;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class Single implements FreeRefFunction {
    public static final FreeRefFunction instance = new Single();

    private Single() {
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        ValueEval intersect = null;
        int col = ec.getColumnIndex();
        int row = ec.getRowIndex();
        for (ValueEval val : args) {
            if (val instanceof AreaEvalBase) {
                AreaEvalBase area = (AreaEvalBase) val;
                if (area.contains(row, col)) {
                    if (intersect != null) {
                        return ErrorEval.VALUE_INVALID;
                    }
                    intersect = area.getAbsoluteValue(row, col);
                } else if (area.containsRow(row)) {
                    if (intersect != null) {
                        return ErrorEval.VALUE_INVALID;
                    }
                    intersect = area.getAbsoluteValue(row, area.getFirstColumn());
                } else if (!area.containsColumn(col)) {
                    continue;
                } else {
                    if (intersect != null) {
                        return ErrorEval.VALUE_INVALID;
                    }
                    intersect = area.getAbsoluteValue(area.getFirstRow(), col);
                }
            }
        }
        if (intersect != null) {
            return intersect;
        }
        return ErrorEval.VALUE_INVALID;
    }
}
