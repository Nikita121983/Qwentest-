package org.apache.poi.ss.formula.functions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.util.NumberToTextConverter;

/* loaded from: classes10.dex */
public final class DVarp implements IDStarAlgorithm {
    private final ArrayList<NumericValueEval> values = new ArrayList<>();

    @Override // org.apache.poi.ss.formula.functions.IDStarAlgorithm
    public boolean processMatch(ValueEval eval) {
        if (eval instanceof NumericValueEval) {
            this.values.add((NumericValueEval) eval);
            return true;
        }
        return true;
    }

    @Override // org.apache.poi.ss.formula.functions.IDStarAlgorithm
    public ValueEval getResult() {
        double[] array = new double[this.values.size()];
        int pos = 0;
        Iterator<NumericValueEval> it = this.values.iterator();
        while (it.hasNext()) {
            NumericValueEval d = it.next();
            array[pos] = d.getNumberValue();
            pos++;
        }
        double var = StatsLib.varp(array);
        return new NumberEval(new BigDecimal(NumberToTextConverter.toText(var)).doubleValue());
    }
}
