package org.apache.poi.ss.formula.atp;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

/* loaded from: classes10.dex */
public final class IfNa implements FreeRefFunction {
    public static final FreeRefFunction instance = new IfNa();

    private IfNa() {
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length != 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            return OperandResolver.getSingleValue(args[0], ec.getRowIndex(), ec.getColumnIndex());
        } catch (EvaluationException e) {
            ValueEval error = e.getErrorEval();
            if (error != ErrorEval.NA) {
                return error;
            }
            try {
                return OperandResolver.getSingleValue(args[1], ec.getRowIndex(), ec.getColumnIndex());
            } catch (EvaluationException e2) {
                return e2.getErrorEval();
            }
        }
    }
}
