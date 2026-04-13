package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class IfFunc extends Var2or3ArgFunction implements ArrayFunction {
    @Override // org.apache.poi.ss.formula.functions.Function2Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        try {
            boolean b = evaluateFirstArg(arg0, srcRowIndex, srcColumnIndex);
            if (b) {
                if (arg1 == MissingArgEval.instance) {
                    return BlankEval.instance;
                }
                return arg1;
            }
            return BoolEval.FALSE;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override // org.apache.poi.ss.formula.functions.Function3Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        try {
            boolean b = evaluateFirstArg(arg0, srcRowIndex, srcColumnIndex);
            if (b) {
                if (arg1 == MissingArgEval.instance) {
                    return BlankEval.instance;
                }
                return arg1;
            }
            if (arg2 == MissingArgEval.instance) {
                return BlankEval.instance;
            }
            return arg2;
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    public static boolean evaluateFirstArg(ValueEval arg, int srcCellRow, int srcCellCol) throws EvaluationException {
        ValueEval ve = OperandResolver.getSingleValue(arg, srcCellRow, srcCellCol);
        Boolean b = OperandResolver.coerceValueToBoolean(ve, false);
        if (b == null) {
            return false;
        }
        return b.booleanValue();
    }

    @Override // org.apache.poi.ss.formula.functions.ArrayFunction
    public ValueEval evaluateArray(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length >= 2 && args.length <= 3) {
            ValueEval arg0 = args[0];
            ValueEval arg1 = args[1];
            ValueEval arg2 = args.length == 2 ? BoolEval.FALSE : args[2];
            return evaluateArrayArgs(arg0, arg1, arg2, srcRowIndex, srcColumnIndex);
        }
        return ErrorEval.VALUE_INVALID;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x013d A[EDGE_INSN: B:70:0x013d->B:71:0x013d BREAK  A[LOOP:0: B:12:0x00a9->B:69:0x0131], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    org.apache.poi.ss.formula.eval.ValueEval evaluateArrayArgs(org.apache.poi.ss.formula.eval.ValueEval r26, org.apache.poi.ss.formula.eval.ValueEval r27, org.apache.poi.ss.formula.eval.ValueEval r28, int r29, int r30) {
        /*
            Method dump skipped, instructions count: 348
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.IfFunc.evaluateArrayArgs(org.apache.poi.ss.formula.eval.ValueEval, org.apache.poi.ss.formula.eval.ValueEval, org.apache.poi.ss.formula.eval.ValueEval, int, int):org.apache.poi.ss.formula.eval.ValueEval");
    }
}
