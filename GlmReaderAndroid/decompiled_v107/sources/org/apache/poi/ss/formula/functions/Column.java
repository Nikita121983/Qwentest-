package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class Column {
    public static ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        int rnum;
        if (args.length > 1) {
            return ErrorEval.VALUE_INVALID;
        }
        if (args.length == 0) {
            rnum = srcColumnIndex;
        } else if (args[0] instanceof AreaEval) {
            rnum = ((AreaEval) args[0]).getFirstColumn();
        } else if (args[0] instanceof RefEval) {
            rnum = ((RefEval) args[0]).getColumn();
        } else {
            return ErrorEval.VALUE_INVALID;
        }
        return new NumberEval(rnum + 1.0d);
    }
}
