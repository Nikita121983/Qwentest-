package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.MissingArgEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.CountUtils;

/* loaded from: classes10.dex */
public final class Count implements Function {
    private static final CountUtils.I_MatchPredicate defaultPredicate = new CountUtils.I_MatchPredicate() { // from class: org.apache.poi.ss.formula.functions.Count$$ExternalSyntheticLambda0
        @Override // org.apache.poi.ss.formula.functions.CountUtils.I_MatchPredicate
        public final boolean matches(ValueEval valueEval) {
            return Count.lambda$static$0(valueEval);
        }
    };
    private static final CountUtils.I_MatchPredicate subtotalPredicate = new CountUtils.I_MatchAreaPredicate() { // from class: org.apache.poi.ss.formula.functions.Count.1
        @Override // org.apache.poi.ss.formula.functions.CountUtils.I_MatchPredicate
        public boolean matches(ValueEval valueEval) {
            return Count.defaultPredicate.matches(valueEval);
        }

        @Override // org.apache.poi.ss.formula.functions.CountUtils.I_MatchAreaPredicate
        public boolean matches(TwoDEval areEval, int rowIndex, int columnIndex) {
            return !areEval.isSubTotal(rowIndex, columnIndex);
        }
    };
    private static final CountUtils.I_MatchPredicate subtotalVisibleOnlyPredicate = new CountUtils.I_MatchAreaPredicate() { // from class: org.apache.poi.ss.formula.functions.Count.2
        @Override // org.apache.poi.ss.formula.functions.CountUtils.I_MatchPredicate
        public boolean matches(ValueEval valueEval) {
            return Count.defaultPredicate.matches(valueEval);
        }

        @Override // org.apache.poi.ss.formula.functions.CountUtils.I_MatchAreaPredicate
        public boolean matches(TwoDEval areEval, int rowIndex, int columnIndex) {
            return (areEval.isSubTotal(rowIndex, columnIndex) || areEval.isRowHidden(rowIndex)) ? false : true;
        }
    };
    private final CountUtils.I_MatchPredicate _predicate;

    public Count() {
        this._predicate = defaultPredicate;
    }

    private Count(CountUtils.I_MatchPredicate criteriaPredicate) {
        this._predicate = criteriaPredicate;
    }

    @Override // org.apache.poi.ss.formula.functions.Function
    public ValueEval evaluate(ValueEval[] args, int srcCellRow, int srcCellCol) {
        int nArgs = args.length;
        if (nArgs < 1) {
            return ErrorEval.VALUE_INVALID;
        }
        if (nArgs > 30) {
            return ErrorEval.VALUE_INVALID;
        }
        int temp = 0;
        for (ValueEval arg : args) {
            temp += CountUtils.countArg(arg, this._predicate);
        }
        return new NumberEval(temp);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$0(ValueEval valueEval) {
        return (valueEval instanceof NumberEval) || valueEval == MissingArgEval.instance;
    }

    public static Count subtotalInstance(boolean includeHiddenRows) {
        return new Count(includeHiddenRows ? subtotalPredicate : subtotalVisibleOnlyPredicate);
    }
}
