package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.Fixed1ArgFunction;
import org.apache.poi.ss.formula.functions.Function;

/* loaded from: classes10.dex */
public final class UnaryMinusEval extends Fixed1ArgFunction implements ArrayFunction {
    public static final Function instance = new UnaryMinusEval();

    private UnaryMinusEval() {
    }

    @Override // org.apache.poi.ss.formula.functions.Function1Arg
    /* renamed from: evaluate, reason: merged with bridge method [inline-methods] */
    public ValueEval m2524x1f30617e(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        try {
            ValueEval ve = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
            double d = OperandResolver.coerceValueToDouble(ve);
            if (d == 0.0d) {
                return NumberEval.ZERO;
            }
            return new NumberEval(-d);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override // org.apache.poi.ss.formula.functions.ArrayFunction
    public ValueEval evaluateArray(ValueEval[] args, final int srcRowIndex, final int srcColumnIndex) {
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluateOneArrayArg(args[0], srcRowIndex, srcColumnIndex, new java.util.function.Function() { // from class: org.apache.poi.ss.formula.eval.UnaryMinusEval$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return UnaryMinusEval.this.m2524x1f30617e(srcRowIndex, srcColumnIndex, (ValueEval) obj);
            }
        });
    }
}
