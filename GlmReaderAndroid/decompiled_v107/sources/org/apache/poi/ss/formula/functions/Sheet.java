package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public class Sheet implements FreeRefFunction {
    public static final Sheet instance = new Sheet();

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        try {
            if (args.length == 0) {
                return new NumberEval(ec.getSheetIndex() + 1.0d);
            }
            ValueEval arg = args[0];
            if (arg instanceof RefEval) {
                RefEval ref = (RefEval) arg;
                return new NumberEval(ref.getFirstSheetIndex() + 1.0d);
            }
            if (arg instanceof AreaEval) {
                AreaEval area = (AreaEval) arg;
                return new NumberEval(area.getFirstSheetIndex() + 1.0d);
            }
            if (arg instanceof StringEval) {
                String sheetName = ((StringEval) arg).getStringValue();
                EvaluationWorkbook wb = ec.getWorkbook();
                int sheetIndex = wb.getSheetIndex(sheetName);
                if (sheetIndex >= 0) {
                    return new NumberEval(sheetIndex + 1.0d);
                }
                return ErrorEval.NA;
            }
            return ErrorEval.NA;
        } catch (Exception e) {
            return ErrorEval.VALUE_INVALID;
        }
    }
}
