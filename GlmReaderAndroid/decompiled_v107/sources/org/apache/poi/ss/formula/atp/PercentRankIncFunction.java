package org.apache.poi.ss.formula.atp;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.PercentRank;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class PercentRankIncFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new PercentRankIncFunction(ArgumentsEvaluator.instance);
    private ArgumentsEvaluator evaluator;

    private PercentRankIncFunction(ArgumentsEvaluator anEvaluator) {
        this.evaluator = anEvaluator;
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        return PercentRank.instance.evaluate(args, ec.getRowIndex(), ec.getColumnIndex());
    }
}
