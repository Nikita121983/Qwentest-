package org.apache.poi.ss.formula.functions;

import java.math.BigDecimal;
import java.math.MathContext;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;

/* loaded from: classes10.dex */
public final class DAverage implements IDStarAlgorithm {
    private long count;
    private double total;

    @Override // org.apache.poi.ss.formula.functions.IDStarAlgorithm
    public boolean processMatch(ValueEval eval) {
        if (eval instanceof NumericValueEval) {
            this.count++;
            this.total += ((NumericValueEval) eval).getNumberValue();
            return true;
        }
        return true;
    }

    @Override // org.apache.poi.ss.formula.functions.IDStarAlgorithm
    public ValueEval getResult() {
        return this.count == 0 ? NumberEval.ZERO : new NumberEval(getAverage());
    }

    private double getAverage() {
        return divide(this.total, this.count);
    }

    private static double divide(double total, long count) {
        return BigDecimal.valueOf(total).divide(BigDecimal.valueOf(count), MathContext.DECIMAL128).doubleValue();
    }
}
