package org.apache.poi.ss.formula.eval;

import java.util.function.BiFunction;
import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.Fixed2ArgFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.util.NumberComparer;

/* loaded from: classes10.dex */
public abstract class RelationalOperationEval extends Fixed2ArgFunction implements ArrayFunction {
    public static final Function EqualEval = new RelationalOperationEval() { // from class: org.apache.poi.ss.formula.eval.RelationalOperationEval.1
        @Override // org.apache.poi.ss.formula.eval.RelationalOperationEval
        protected boolean convertComparisonResult(int cmpResult) {
            return cmpResult == 0;
        }
    };
    public static final Function GreaterEqualEval = new RelationalOperationEval() { // from class: org.apache.poi.ss.formula.eval.RelationalOperationEval.2
        @Override // org.apache.poi.ss.formula.eval.RelationalOperationEval
        protected boolean convertComparisonResult(int cmpResult) {
            return cmpResult >= 0;
        }
    };
    public static final Function GreaterThanEval = new RelationalOperationEval() { // from class: org.apache.poi.ss.formula.eval.RelationalOperationEval.3
        @Override // org.apache.poi.ss.formula.eval.RelationalOperationEval
        protected boolean convertComparisonResult(int cmpResult) {
            return cmpResult > 0;
        }
    };
    public static final Function LessEqualEval = new RelationalOperationEval() { // from class: org.apache.poi.ss.formula.eval.RelationalOperationEval.4
        @Override // org.apache.poi.ss.formula.eval.RelationalOperationEval
        protected boolean convertComparisonResult(int cmpResult) {
            return cmpResult <= 0;
        }
    };
    public static final Function LessThanEval = new RelationalOperationEval() { // from class: org.apache.poi.ss.formula.eval.RelationalOperationEval.5
        @Override // org.apache.poi.ss.formula.eval.RelationalOperationEval
        protected boolean convertComparisonResult(int cmpResult) {
            return cmpResult < 0;
        }
    };
    public static final Function NotEqualEval = new RelationalOperationEval() { // from class: org.apache.poi.ss.formula.eval.RelationalOperationEval.6
        @Override // org.apache.poi.ss.formula.eval.RelationalOperationEval
        protected boolean convertComparisonResult(int cmpResult) {
            return cmpResult != 0;
        }
    };

    protected abstract boolean convertComparisonResult(int i);

    @Override // org.apache.poi.ss.formula.functions.Function2Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1) {
        try {
            ValueEval vA = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
            ValueEval vB = OperandResolver.getSingleValue(arg1, srcRowIndex, srcColumnIndex);
            int cmpResult = doCompare(vA, vB);
            boolean result = convertComparisonResult(cmpResult);
            return BoolEval.valueOf(result);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    @Override // org.apache.poi.ss.formula.functions.ArrayFunction
    public ValueEval evaluateArray(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        ValueEval arg0 = args[0];
        ValueEval arg1 = args[1];
        return evaluateTwoArrayArgs(arg0, arg1, srcRowIndex, srcColumnIndex, new BiFunction() { // from class: org.apache.poi.ss.formula.eval.RelationalOperationEval$$ExternalSyntheticLambda0
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return RelationalOperationEval.this.m2522xaa02471b((ValueEval) obj, (ValueEval) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$evaluateArray$0$org-apache-poi-ss-formula-eval-RelationalOperationEval, reason: not valid java name */
    public /* synthetic */ ValueEval m2522xaa02471b(ValueEval vA, ValueEval vB) {
        int cmpResult = doCompare(vA, vB);
        boolean result = convertComparisonResult(cmpResult);
        return BoolEval.valueOf(result);
    }

    private static int doCompare(ValueEval va, ValueEval vb) {
        if (va == BlankEval.instance || (va instanceof MissingArgEval)) {
            return compareBlank(vb);
        }
        if (vb == BlankEval.instance || (vb instanceof MissingArgEval)) {
            return -compareBlank(va);
        }
        if (va instanceof BoolEval) {
            if (!(vb instanceof BoolEval)) {
                return 1;
            }
            BoolEval bA = (BoolEval) va;
            BoolEval bB = (BoolEval) vb;
            if (bA.getBooleanValue() == bB.getBooleanValue()) {
                return 0;
            }
            return bA.getBooleanValue() ? 1 : -1;
        }
        if (vb instanceof BoolEval) {
            return -1;
        }
        if (va instanceof StringEval) {
            if (!(vb instanceof StringEval)) {
                return 1;
            }
            StringEval sA = (StringEval) va;
            StringEval sB = (StringEval) vb;
            return sA.getStringValue().compareToIgnoreCase(sB.getStringValue());
        }
        if (vb instanceof StringEval) {
            return -1;
        }
        if ((va instanceof NumberEval) && (vb instanceof NumberEval)) {
            NumberEval nA = (NumberEval) va;
            NumberEval nB = (NumberEval) vb;
            return NumberComparer.compare(nA.getNumberValue(), nB.getNumberValue());
        }
        throw new IllegalArgumentException("Bad operand types (" + va.getClass().getName() + "), (" + vb.getClass().getName() + ")");
    }

    private static int compareBlank(ValueEval v) {
        if (v == BlankEval.instance || (v instanceof MissingArgEval)) {
            return 0;
        }
        if (v instanceof BoolEval) {
            BoolEval boolEval = (BoolEval) v;
            return boolEval.getBooleanValue() ? -1 : 0;
        }
        if (v instanceof NumberEval) {
            NumberEval ne = (NumberEval) v;
            return NumberComparer.compare(0.0d, ne.getNumberValue());
        }
        if (v instanceof StringEval) {
            StringEval se = (StringEval) v;
            return se.getStringValue().length() < 1 ? 0 : -1;
        }
        throw new IllegalArgumentException("bad value class (" + v.getClass().getName() + ")");
    }
}
