package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.Function;

/* loaded from: classes10.dex */
public final class UnaryPlusEval extends Fixed1ArgFunction implements ArrayFunction {
    public static final Function instance = new UnaryPlusEval();

    private UnaryPlusEval() {
    }

    @Override // org.apache.poi.ss.formula.functions.Function1Arg
    /* renamed from: evaluate, reason: merged with bridge method [inline-methods] */
    public ValueEval m2525x35adf6c4(int srcCellRow, int srcCellCol, ValueEval arg0) {
        try {
            ValueEval ve = OperandResolver.getSingleValue(arg0, srcCellRow, srcCellCol);
            if (ve instanceof StringEval) {
                return ve;
            }
            double d = OperandResolver.coerceValueToDouble(ve);
            return new NumberEval(d);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override // org.apache.poi.ss.formula.functions.ArrayFunction
    public ValueEval evaluateArray(ValueEval[] args, final int srcRowIndex, final int srcColumnIndex) {
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluateOneArrayArg(args[0], srcRowIndex, srcColumnIndex, new java.util.function.Function() { // from class: org.apache.poi.ss.formula.eval.UnaryPlusEval$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return UnaryPlusEval.this.m2525x35adf6c4(srcRowIndex, srcColumnIndex, (ValueEval) obj);
            }
        });
    }
}
