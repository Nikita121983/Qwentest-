package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class DCountA implements IDStarAlgorithm {
    private long count;

    @Override // org.apache.poi.ss.formula.functions.IDStarAlgorithm
    public boolean processMatch(ValueEval eval) {
        if (!(eval instanceof BlankEval)) {
            this.count++;
            return true;
        }
        return true;
    }

    @Override // org.apache.poi.ss.formula.functions.IDStarAlgorithm
    public ValueEval getResult() {
        return new NumberEval(this.count);
    }

    @Override // org.apache.poi.ss.formula.functions.IDStarAlgorithm
    public boolean allowEmptyMatchField() {
        return true;
    }
}
