package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.RefListEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public abstract class LogicalFunction extends Fixed1ArgFunction implements ArrayFunction {
    public static final Function ISLOGICAL = new LogicalFunction() { // from class: org.apache.poi.ss.formula.functions.LogicalFunction.1
        @Override // org.apache.poi.ss.formula.functions.LogicalFunction
        protected boolean evaluate(ValueEval arg) {
            return arg instanceof BoolEval;
        }
    };
    public static final Function ISNONTEXT = new LogicalFunction() { // from class: org.apache.poi.ss.formula.functions.LogicalFunction.2
        @Override // org.apache.poi.ss.formula.functions.LogicalFunction
        protected boolean evaluate(ValueEval arg) {
            return !(arg instanceof StringEval);
        }
    };
    public static final Function ISNUMBER = new LogicalFunction() { // from class: org.apache.poi.ss.formula.functions.LogicalFunction.3
        @Override // org.apache.poi.ss.formula.functions.LogicalFunction
        protected boolean evaluate(ValueEval arg) {
            return arg instanceof NumberEval;
        }
    };
    public static final Function ISTEXT = new LogicalFunction() { // from class: org.apache.poi.ss.formula.functions.LogicalFunction.4
        @Override // org.apache.poi.ss.formula.functions.LogicalFunction
        protected boolean evaluate(ValueEval arg) {
            return arg instanceof StringEval;
        }
    };
    public static final Function ISBLANK = new LogicalFunction() { // from class: org.apache.poi.ss.formula.functions.LogicalFunction.5
        @Override // org.apache.poi.ss.formula.functions.LogicalFunction
        protected boolean evaluate(ValueEval arg) {
            return arg instanceof BlankEval;
        }
    };
    public static final Function ISERROR = new LogicalFunction() { // from class: org.apache.poi.ss.formula.functions.LogicalFunction.6
        @Override // org.apache.poi.ss.formula.functions.LogicalFunction
        protected boolean evaluate(ValueEval arg) {
            return arg instanceof ErrorEval;
        }
    };
    public static final Function ISERR = new LogicalFunction() { // from class: org.apache.poi.ss.formula.functions.LogicalFunction.7
        @Override // org.apache.poi.ss.formula.functions.LogicalFunction
        protected boolean evaluate(ValueEval arg) {
            return (arg instanceof ErrorEval) && arg != ErrorEval.NA;
        }
    };
    public static final Function ISNA = new LogicalFunction() { // from class: org.apache.poi.ss.formula.functions.LogicalFunction.8
        @Override // org.apache.poi.ss.formula.functions.LogicalFunction
        protected boolean evaluate(ValueEval arg) {
            return arg == ErrorEval.NA;
        }
    };
    public static final Function ISREF = new Fixed1ArgFunction() { // from class: org.apache.poi.ss.formula.functions.LogicalFunction.9
        @Override // org.apache.poi.ss.formula.functions.Function1Arg
        /* renamed from: evaluate */
        public ValueEval m2532lambda$evaluateArray$0$orgapachepoissformulafunctionsValue(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
            if ((arg0 instanceof RefEval) || (arg0 instanceof AreaEval) || (arg0 instanceof RefListEval)) {
                return BoolEval.TRUE;
            }
            return BoolEval.FALSE;
        }
    };

    protected abstract boolean evaluate(ValueEval valueEval);

    @Override // org.apache.poi.ss.formula.functions.Function1Arg
    /* renamed from: evaluate */
    public ValueEval m2532lambda$evaluateArray$0$orgapachepoissformulafunctionsValue(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        ValueEval ve;
        try {
            ve = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
        } catch (EvaluationException e) {
            ve = e.getErrorEval();
        }
        return BoolEval.valueOf(evaluate(ve));
    }

    @Override // org.apache.poi.ss.formula.functions.ArrayFunction
    public ValueEval evaluateArray(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        if (args.length != 1) {
            return ErrorEval.VALUE_INVALID;
        }
        return evaluateOneArrayArg(args[0], srcRowIndex, srcColumnIndex, new java.util.function.Function() { // from class: org.apache.poi.ss.formula.functions.LogicalFunction$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return LogicalFunction.this.m2526xe3aa8bff((ValueEval) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$evaluateArray$0$org-apache-poi-ss-formula-functions-LogicalFunction, reason: not valid java name */
    public /* synthetic */ ValueEval m2526xe3aa8bff(ValueEval valA) {
        return BoolEval.valueOf(evaluate(valA));
    }
}
