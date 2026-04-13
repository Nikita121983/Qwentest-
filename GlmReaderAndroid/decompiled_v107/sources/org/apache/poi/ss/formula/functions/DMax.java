package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class DMax implements IDStarAlgorithm {
    private ValueEval maximumValue;

    @Override // org.apache.poi.ss.formula.functions.IDStarAlgorithm
    public boolean processMatch(ValueEval eval) {
        if (eval instanceof NumericValueEval) {
            if (this.maximumValue == null) {
                this.maximumValue = eval;
                return true;
            }
            double currentValue = ((NumericValueEval) eval).getNumberValue();
            double oldValue = ((NumericValueEval) this.maximumValue).getNumberValue();
            if (currentValue > oldValue) {
                this.maximumValue = eval;
                return true;
            }
            return true;
        }
        return true;
    }

    @Override // org.apache.poi.ss.formula.functions.IDStarAlgorithm
    public ValueEval getResult() {
        if (this.maximumValue == null) {
            return NumberEval.ZERO;
        }
        return this.maximumValue;
    }
}
