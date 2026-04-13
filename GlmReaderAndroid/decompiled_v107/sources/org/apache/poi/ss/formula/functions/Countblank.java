package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.ThreeDEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.CountUtils;

/* loaded from: classes10.dex */
public final class Countblank extends Fixed1ArgFunction {
    private static final CountUtils.I_MatchPredicate predicate = new CountUtils.I_MatchPredicate() { // from class: org.apache.poi.ss.formula.functions.Countblank$$ExternalSyntheticLambda0
        @Override // org.apache.poi.ss.formula.functions.CountUtils.I_MatchPredicate
        public final boolean matches(ValueEval valueEval) {
            return Countblank.lambda$static$0(valueEval);
        }
    };

    @Override // org.apache.poi.ss.formula.functions.Function1Arg
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        double result;
        if (arg0 instanceof RefEval) {
            result = CountUtils.countMatchingCellsInRef((RefEval) arg0, predicate);
        } else if (arg0 instanceof ThreeDEval) {
            result = CountUtils.countMatchingCellsInArea((ThreeDEval) arg0, predicate);
        } else {
            throw new IllegalArgumentException("Bad range arg type (" + arg0.getClass().getName() + ")");
        }
        return new NumberEval(result);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$0(ValueEval valueEval) {
        return valueEval == BlankEval.instance || ((valueEval instanceof StringEval) && ((StringEval) valueEval).getStringValue().isEmpty());
    }
}
