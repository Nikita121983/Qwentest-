package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Baseifs;
import org.apache.poi.ss.formula.functions.CountUtils;
import org.apache.poi.ss.formula.functions.Countif;

/* loaded from: classes10.dex */
public class AverageIf extends Baseifs {
    public static final FreeRefFunction instance = new AverageIf();

    @Override // org.apache.poi.ss.formula.functions.Baseifs, org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        if (args.length < 2) {
            return ErrorEval.VALUE_INVALID;
        }
        try {
            AreaEval sumRange = convertRangeArg(args[0]);
            if (args.length == 3) {
                sumRange = convertRangeArg(args[2]);
            }
            AreaEval ae = convertRangeArg(args[0]);
            CountUtils.I_MatchPredicate mp = Countif.createCriteriaPredicate(args[1], ec.getRowIndex(), ec.getColumnIndex());
            if (mp instanceof Countif.ErrorMatcher) {
                throw new EvaluationException(ErrorEval.valueOf(((Countif.ErrorMatcher) mp).getValue()));
            }
            return aggregateMatchingCells(createAggregator(), sumRange, ae, mp);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }

    protected ValueEval aggregateMatchingCells(Baseifs.Aggregator aggregator, AreaEval sumRange, AreaEval testRange, CountUtils.I_MatchPredicate mp) throws EvaluationException {
        int height = testRange.getHeight();
        int width = testRange.getWidth();
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                ValueEval _testValue = testRange.getRelativeValue(r, c);
                ValueEval _sumValue = sumRange.getRelativeValue(r, c);
                if (mp != null && mp.matches(_testValue)) {
                    if (_testValue instanceof ErrorEval) {
                        throw new EvaluationException((ErrorEval) _testValue);
                    }
                    aggregator.addValue(_sumValue);
                }
            }
        }
        return aggregator.getResult();
    }

    @Override // org.apache.poi.ss.formula.functions.Baseifs
    protected boolean hasInitialRange() {
        return false;
    }

    @Override // org.apache.poi.ss.formula.functions.Baseifs
    protected Baseifs.Aggregator createAggregator() {
        return new Baseifs.Aggregator() { // from class: org.apache.poi.ss.formula.functions.AverageIf.1
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
