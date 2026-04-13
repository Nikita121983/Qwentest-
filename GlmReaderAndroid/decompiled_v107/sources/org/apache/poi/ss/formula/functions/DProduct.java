package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class DProduct implements IDStarAlgorithm {
    private boolean initDone = false;
    private double product;

    @Override // org.apache.poi.ss.formula.functions.IDStarAlgorithm
    public boolean processMatch(ValueEval eval) {
        if (eval instanceof NumericValueEval) {
            if (this.initDone) {
                this.product *= ((NumericValueEval) eval).getNumberValue();
            } else {
                this.product = ((NumericValueEval) eval).getNumberValue();
                this.initDone = true;
            }
        }
        return true;
    }

    @Override // org.apache.poi.ss.formula.functions.IDStarAlgorithm
    public ValueEval getResult() {
        return new NumberEval(this.product);
    }
}
