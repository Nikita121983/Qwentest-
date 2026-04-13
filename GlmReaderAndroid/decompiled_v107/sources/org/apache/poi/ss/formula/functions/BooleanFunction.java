package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public abstract class BooleanFunction implements Function, ArrayFunction {
    public static final Function AND = new BooleanFunction() { // from class: org.apache.poi.ss.formula.functions.BooleanFunction.1
        @Override // org.apache.poi.ss.formula.functions.BooleanFunction
        protected boolean getInitialResultValue() {
            return true;
        }

        @Override // org.apache.poi.ss.formula.functions.BooleanFunction
        protected boolean partialEvaluate(boolean cumulativeResult, boolean currentValue) {
            return cumulativeResult && currentValue;
        }
    };
    public static final Function OR = new BooleanFunction() { // from class: org.apache.poi.ss.formula.functions.BooleanFunction.2
        @Override // org.apache.poi.ss.formula.functions.BooleanFunction
        protected boolean getInitialResultValue() {
            return false;
        }

        @Override // org.apache.poi.ss.formula.functions.BooleanFunction
        protected boolean partialEvaluate(boolean cumulativeResult, boolean currentValue) {
            return cumulativeResult || currentValue;
        }
    };
    public static final Function FALSE = new Function() { // from class: org.apache.poi.ss.formula.functions.BooleanFunction$$ExternalSyntheticLambda0
        @Override // org.apache.poi.ss.formula.functions.Function
        public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
            ValueEval evaluateFalse;
            evaluateFalse = BooleanFunction.evaluateFalse(valueEvalArr, i, i2);
            return evaluateFalse;
        }
    };
    public static final Function TRUE = new Function() { // from class: org.apache.poi.ss.formula.functions.BooleanFunction$$ExternalSyntheticLambda1
        @Override // org.apache.poi.ss.formula.functions.Function
        public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
            ValueEval evaluateTrue;
            evaluateTrue = BooleanFunction.evaluateTrue(valueEvalArr, i, i2);
            return evaluateTrue;
        }
    };
    public static final Function NOT = new Function() { // from class: org.apache.poi.ss.formula.functions.BooleanFunction$$ExternalSyntheticLambda2
        @Override // org.apache.poi.ss.formula.functions.Function
        public final ValueEval evaluate(ValueEval[] valueEvalArr, int i, int i2) {
            ValueEval evaluateNot;
            evaluateNot = BooleanFunction.evaluateNot(valueEvalArr, i, i2);
            return evaluateNot;
        }
    };

    protected abstract boolean getInitialResultValue();

    protected abstract boolean partialEvaluate(boolean z, boolean z2);

    @Override // org.apache.poi.ss.formula.functions.Function
    public final ValueEval evaluate(ValueEval[] args, int srcRow, int srcCol) {
        if (args.length < 1) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            boolean boolResult = calculate(args);
            return BoolEval.valueOf(boolResult);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean calculate(org.apache.poi.ss.formula.eval.ValueEval[] r17) throws org.apache.poi.ss.formula.eval.EvaluationException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            boolean r2 = r0.getInitialResultValue()
            r3 = 0
            int r4 = r1.length
            r6 = 0
        Lb:
            if (r6 >= r4) goto L8e
            r7 = r1[r6]
            boolean r8 = r7 instanceof org.apache.poi.ss.formula.TwoDEval
            r9 = 1
            if (r8 == 0) goto L40
            r8 = r7
            org.apache.poi.ss.formula.TwoDEval r8 = (org.apache.poi.ss.formula.TwoDEval) r8
            int r10 = r8.getHeight()
            int r11 = r8.getWidth()
            r12 = 0
        L20:
            if (r12 >= r10) goto L3e
            r13 = 0
        L23:
            if (r13 >= r11) goto L3b
            org.apache.poi.ss.formula.eval.ValueEval r14 = r8.getValue(r12, r13)
            java.lang.Boolean r15 = org.apache.poi.ss.formula.eval.OperandResolver.coerceValueToBoolean(r14, r9)
            if (r15 == 0) goto L38
            boolean r5 = r15.booleanValue()
            boolean r2 = r0.partialEvaluate(r2, r5)
            r3 = 1
        L38:
            int r13 = r13 + 1
            goto L23
        L3b:
            int r12 = r12 + 1
            goto L20
        L3e:
            r5 = 0
            goto L8a
        L40:
            boolean r5 = r7 instanceof org.apache.poi.ss.formula.eval.RefEval
            if (r5 == 0) goto L6a
            r5 = r7
            org.apache.poi.ss.formula.eval.RefEval r5 = (org.apache.poi.ss.formula.eval.RefEval) r5
            int r8 = r5.getFirstSheetIndex()
            int r10 = r5.getLastSheetIndex()
            r11 = r8
        L50:
            if (r11 > r10) goto L68
            org.apache.poi.ss.formula.eval.ValueEval r12 = r5.getInnerValueEval(r11)
            java.lang.Boolean r13 = org.apache.poi.ss.formula.eval.OperandResolver.coerceValueToBoolean(r12, r9)
            if (r13 == 0) goto L65
            boolean r14 = r13.booleanValue()
            boolean r2 = r0.partialEvaluate(r2, r14)
            r3 = 1
        L65:
            int r11 = r11 + 1
            goto L50
        L68:
            r5 = 0
            goto L8a
        L6a:
            org.apache.poi.ss.formula.eval.MissingArgEval r5 = org.apache.poi.ss.formula.eval.MissingArgEval.instance
            if (r7 == r5) goto L7a
            org.apache.poi.ss.formula.eval.BlankEval r5 = org.apache.poi.ss.formula.eval.BlankEval.instance
            if (r7 != r5) goto L74
            r5 = 0
            goto L7b
        L74:
            r5 = 0
            java.lang.Boolean r8 = org.apache.poi.ss.formula.eval.OperandResolver.coerceValueToBoolean(r7, r5)
            goto L7f
        L7a:
            r5 = 0
        L7b:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r5)
        L7f:
            if (r8 == 0) goto L8a
            boolean r9 = r8.booleanValue()
            boolean r2 = r0.partialEvaluate(r2, r9)
            r3 = 1
        L8a:
            int r6 = r6 + 1
            goto Lb
        L8e:
            if (r3 == 0) goto L91
            return r2
        L91:
            org.apache.poi.ss.formula.eval.EvaluationException r4 = new org.apache.poi.ss.formula.eval.EvaluationException
            org.apache.poi.ss.formula.eval.ErrorEval r5 = org.apache.poi.ss.formula.eval.ErrorEval.VALUE_INVALID
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.BooleanFunction.calculate(org.apache.poi.ss.formula.eval.ValueEval[]):boolean");
    }

    @Override // org.apache.poi.ss.formula.functions.ArrayFunction
    public ValueEval evaluateArray(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        return evaluate(args, srcRowIndex, srcColumnIndex);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ValueEval evaluateFalse(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        return args.length != 0 ? ErrorEval.VALUE_INVALID : BoolEval.FALSE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ValueEval evaluateTrue(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        return args.length != 0 ? ErrorEval.VALUE_INVALID : BoolEval.TRUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ValueEval evaluateNot(ValueEval[] args, final int srcRowIndex, final int srcColumnIndex) {
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        java.util.function.Function<ValueEval, ValueEval> notInner = new java.util.function.Function() { // from class: org.apache.poi.ss.formula.functions.BooleanFunction$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return BooleanFunction.lambda$evaluateNot$0(srcRowIndex, srcColumnIndex, (ValueEval) obj);
            }
        };
        return ArrayFunction._evaluateOneArrayArg(args[0], srcRowIndex, srcColumnIndex, notInner);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ValueEval lambda$evaluateNot$0(int srcRowIndex, int srcColumnIndex, ValueEval va) {
        boolean boolArgVal;
        try {
            ValueEval ve = OperandResolver.getSingleValue(va, srcRowIndex, srcColumnIndex);
            boolean z = false;
            Boolean b = OperandResolver.coerceValueToBoolean(ve, false);
            if (b == null || !b.booleanValue()) {
                boolArgVal = false;
            } else {
                boolArgVal = true;
            }
            if (!boolArgVal) {
                z = true;
            }
            return BoolEval.valueOf(z);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
