package org.apache.poi.ss.formula.atp;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.AggregateFunction;
import org.apache.poi.ss.formula.functions.FreeRefFunction;

/* loaded from: classes10.dex */
public class Stdevs implements FreeRefFunction {
    public static final Stdevs instance = new Stdevs();

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        return AggregateFunction.STDEV.evaluate(args, ec.getRowIndex(), ec.getColumnIndex());
    }
}
