package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Baseifs;

/* loaded from: classes10.dex */
public final class Averageifs extends Baseifs {
    public static final FreeRefFunction instance = new Averageifs();

    @Override // org.apache.poi.ss.formula.functions.Baseifs, org.apache.poi.ss.formula.functions.FreeRefFunction
    public /* bridge */ /* synthetic */ ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        return super.evaluate(valueEvalArr, operationEvaluationContext);
    }

    @Override // org.apache.poi.ss.formula.functions.Baseifs
    protected boolean hasInitialRange() {
        return true;
    }

    @Override // org.apache.poi.ss.formula.functions.Baseifs
    protected Baseifs.Aggregator createAggregator() {
        return new Baseifs.Aggregator() { // from class: org.apache.poi.ss.formula.functions.Averageifs.1
            Double sum = Double.valueOf(0.0d);
            Integer count = 0;

            @Override // org.apache.poi.ss.formula.functions.Baseifs.Aggregator
            public void addValue(ValueEval value) {
                if (value instanceof NumberEval) {
                    double d = ((NumberEval) value).getNumberValue();
                    this.sum = Double.valueOf(this.sum.doubleValue() + d);
                    this.count = Integer.valueOf(this.count.intValue() + 1);
                }
            }

            @Override // org.apache.poi.ss.formula.functions.Baseifs.Aggregator
            public ValueEval getResult() {
                return this.count.intValue() == 0 ? ErrorEval.DIV_ZERO : new NumberEval(this.sum.doubleValue() / this.count.intValue());
            }
        };
    }
}
