package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Baseifs;

/* loaded from: classes10.dex */
public final class Minifs extends Baseifs {
    public static final FreeRefFunction instance = new Minifs();

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
        return new Baseifs.Aggregator() { // from class: org.apache.poi.ss.formula.functions.Minifs.1
            Double accumulator = null;

            @Override // org.apache.poi.ss.formula.functions.Baseifs.Aggregator
            public void addValue(ValueEval value) {
                double d = value instanceof NumberEval ? ((NumberEval) value).getNumberValue() : 0.0d;
                if (this.accumulator == null || this.accumulator.doubleValue() > d) {
                    this.accumulator = Double.valueOf(d);
                }
            }

            @Override // org.apache.poi.ss.formula.functions.Baseifs.Aggregator
            public ValueEval getResult() {
                return new NumberEval(this.accumulator == null ? 0.0d : this.accumulator.doubleValue());
            }
        };
    }
}
