package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public abstract class FinanceFunction implements Function3Arg, Function4Arg {
    private static final ValueEval DEFAULT_ARG3 = NumberEval.ZERO;
    private static final ValueEval DEFAULT_ARG4 = BoolEval.FALSE;
    public static final Function FV = new FinanceFunction() { // from class: org.apache.poi.ss.formula.functions.FinanceFunction.1
        @Override // org.apache.poi.ss.formula.functions.FinanceFunction
        protected double evaluate(double rate, double arg1, double arg2, double arg3, boolean type) {
            return FinanceLib.fv(rate, arg1, arg2, arg3, type);
        }
    };
    public static final Function NPER = new FinanceFunction() { // from class: org.apache.poi.ss.formula.functions.FinanceFunction.2
        @Override // org.apache.poi.ss.formula.functions.FinanceFunction
        protected double evaluate(double rate, double arg1, double arg2, double arg3, boolean type) {
            return FinanceLib.nper(rate, arg1, arg2, arg3, type);
        }
    };
    public static final Function PMT = new FinanceFunction() { // from class: org.apache.poi.ss.formula.functions.FinanceFunction.3
        @Override // org.apache.poi.ss.formula.functions.FinanceFunction
        protected double evaluate(double rate, double arg1, double arg2, double arg3, boolean type) {
            return FinanceLib.pmt(rate, arg1, arg2, arg3, type);
        }
    };
    public static final Function PV = new FinanceFunction() { // from class: org.apache.poi.ss.formula.functions.FinanceFunction.4
        @Override // org.apache.poi.ss.formula.functions.FinanceFunction
        protected double evaluate(double rate, double arg1, double arg2, double arg3, boolean type) {
            return FinanceLib.pv(rate, arg1, arg2, arg3, type);
        }
    };

    protected abstract double evaluate(double d, double d2, double d3, double d4, boolean z) throws EvaluationException;

    protected FinanceFunction() {
    }

    @Override // org.apache.poi.ss.formula.functions.Function3Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2) {
        return evaluate(srcRowIndex, srcColumnIndex, arg0, arg1, arg2, DEFAULT_ARG3);
    }

    @Override // org.apache.poi.ss.formula.functions.Function4Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2, ValueEval arg3) {
        return evaluate(srcRowIndex, srcColumnIndex, arg0, arg1, arg2, arg3, DEFAULT_ARG4);
    }

    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1, ValueEval arg2, ValueEval arg3, ValueEval arg4) {
        double d0;
        double d1;
        try {
            d0 = NumericFunction.singleOperandEvaluate(arg0, srcRowIndex, srcColumnIndex);
            try {
                d1 = NumericFunction.singleOperandEvaluate(arg1, srcRowIndex, srcColumnIndex);
            } catch (EvaluationException e) {
                e = e;
                return e.getErrorEval();
            }
        } catch (EvaluationException e2) {
            e = e2;
        }
        try {
            double d2 = NumericFunction.singleOperandEvaluate(arg2, srcRowIndex, srcColumnIndex);
            double d3 = NumericFunction.singleOperandEvaluate(arg3, srcRowIndex, srcColumnIndex);
            double d4 = NumericFunction.singleOperandEvaluate(arg4, srcRowIndex, srcColumnIndex);
            double result = evaluate(d0, d1, d2, d3, d4 != 0.0d);
            NumericFunction.checkValue(result);
            return new NumberEval(result);
        } catch (EvaluationException e3) {
            e = e3;
            return e.getErrorEval();
        }
    }

    @Override // org.apache.poi.ss.formula.functions.Function
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        ValueEval arg3;
        ValueEval arg32;
        ValueEval arg4;
        switch (args.length) {
            case 3:
                return evaluate(srcRowIndex, srcColumnIndex, args[0], args[1], args[2], DEFAULT_ARG3, DEFAULT_ARG4);
            case 4:
                ValueEval arg33 = args[3];
                if (arg33 != MissingArgEval.instance) {
                    arg3 = arg33;
                } else {
                    arg3 = DEFAULT_ARG3;
                }
                return evaluate(srcRowIndex, srcColumnIndex, args[0], args[1], args[2], arg3, DEFAULT_ARG4);
            case 5:
                ValueEval arg34 = args[3];
                if (arg34 != MissingArgEval.instance) {
                    arg32 = arg34;
                } else {
                    arg32 = DEFAULT_ARG3;
                }
                ValueEval arg42 = args[4];
                if (arg42 != MissingArgEval.instance) {
                    arg4 = arg42;
                } else {
                    arg4 = DEFAULT_ARG4;
                }
                return evaluate(srcRowIndex, srcColumnIndex, args[0], args[1], args[2], arg32, arg4);
            default:
                return ErrorEval.VALUE_INVALID;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0007. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected double evaluate(double[] r17) throws org.apache.poi.ss.formula.eval.EvaluationException {
        /*
            r16 = this;
            r0 = r17
            r1 = 0
            r3 = 0
            int r5 = r0.length
            switch(r5) {
                case 3: goto L1a;
                case 4: goto L15;
                case 5: goto L12;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "Wrong number of arguments"
            r5.<init>(r6)
            throw r5
        L12:
            r5 = 4
            r3 = r0[r5]
        L15:
            r5 = 3
            r1 = r0[r5]
            r12 = r1
            goto L1b
        L1a:
            r12 = r1
        L1b:
            r1 = 0
            r6 = r0[r1]
            r2 = 1
            r8 = r0[r2]
            r5 = 2
            r10 = r0[r5]
            r14 = 0
            int r5 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1))
            if (r5 == 0) goto L2d
            r14 = r2
            goto L2e
        L2d:
            r14 = r1
        L2e:
            r5 = r16
            double r1 = r5.evaluate(r6, r8, r10, r12, r14)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.functions.FinanceFunction.evaluate(double[]):double");
    }
}
