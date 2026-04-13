package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Baseifs;

/* loaded from: classes10.dex */
public class Countifs extends Baseifs {
    public static final FreeRefFunction instance = new Countifs();

    @Override // org.apache.poi.ss.formula.functions.Baseifs, org.apache.poi.ss.formula.functions.FreeRefFunction
    public /* bridge */ /* synthetic */ ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        return super.evaluate(valueEvalArr, operationEvaluationContext);
    }

    @Override // org.apache.poi.ss.formula.functions.Baseifs
    protected boolean hasInitialRange() {
        return false;
    }

    @Override // org.apache.poi.ss.formula.functions.Baseifs
    protected Baseifs.Aggregator createAggregator() {
        return new Baseifs.Aggregator() { // from class: org.apache.poi.ss.formula.functions.Countifs.1
            double accumulator = 0.0d;

            @Override // org.apache.poi.ss.formula.functions.Baseifs.Aggregator
            public void addValue(ValueEval value) {
                this.accumulator += 1.0d;
            }

            @Override // org.apache.poi.ss.formula.functions.Baseifs.Aggregator
            public ValueEval getResult() {
                return new NumberEval(this.accumulator);
            }
        };
    }
}
