package org.apache.poi.ss.formula.atp;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RelationalOperationEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

/* loaded from: classes10.dex */
public final class Switch implements FreeRefFunction {
    public static final FreeRefFunction instance = new Switch();

    private Switch() {
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length < 3) {
            return ErrorEval.NA;
        }
        try {
            ValueEval expression = OperandResolver.getSingleValue(args[0], ec.getRowIndex(), ec.getColumnIndex());
            for (int i = 1; i < args.length - 1; i += 2) {
                try {
                    ValueEval value = OperandResolver.getSingleValue(args[i], ec.getRowIndex(), ec.getColumnIndex());
                    ValueEval result = args[i + 1];
                    ValueEval evaluate = RelationalOperationEval.EqualEval.evaluate(new ValueEval[]{expression, value}, ec.getRowIndex(), ec.getColumnIndex());
                    if (evaluate instanceof BoolEval) {
                        BoolEval boolEval = (BoolEval) evaluate;
                        boolean booleanValue = boolEval.getBooleanValue();
                        if (booleanValue) {
                            return result;
                        }
                    }
                    if (i + 2 == args.length - 1) {
                        return args[args.length - 1];
                    }
                } catch (EvaluationException e) {
                    return ErrorEval.NA;
                }
            }
            return ErrorEval.NA;
        } catch (Exception e2) {
            return ErrorEval.NA;
        }
    }
}
