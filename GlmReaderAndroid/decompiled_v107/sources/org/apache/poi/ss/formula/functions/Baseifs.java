package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.CountUtils;
import org.apache.poi.ss.formula.functions.Countif;

/* loaded from: classes10.dex */
abstract class Baseifs implements FreeRefFunction {

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes10.dex */
    public interface Aggregator {
        void addValue(ValueEval valueEval);

        ValueEval getResult();
    }

    protected abstract Aggregator createAggregator();

    protected abstract boolean hasInitialRange();

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] valueEvalArr, OperationEvaluationContext operationEvaluationContext) {
        boolean hasInitialRange = hasInitialRange();
        if (valueEvalArr.length < (hasInitialRange ? 1 : 0) + 2 || valueEvalArr.length % 2 != hasInitialRange) {
            return ErrorEval.VALUE_INVALID;
        }
        AreaEval areaEval = null;
        if (hasInitialRange) {
            try {
                areaEval = convertRangeArg(valueEvalArr[0]);
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
        AreaEval[] areaEvalArr = new AreaEval[(valueEvalArr.length - (hasInitialRange ? 1 : 0)) / 2];
        CountUtils.I_MatchPredicate[] i_MatchPredicateArr = new CountUtils.I_MatchPredicate[areaEvalArr.length];
        int i = hasInitialRange ? 1 : 0;
        int i2 = 0;
        while (i < valueEvalArr.length - 1) {
            areaEvalArr[i2] = convertRangeArg(valueEvalArr[i]);
            i_MatchPredicateArr[i2] = Countif.createCriteriaPredicate(valueEvalArr[i + 1], operationEvaluationContext.getRowIndex(), operationEvaluationContext.getColumnIndex());
            i += 2;
            i2++;
        }
        validateCriteriaRanges(areaEval, areaEvalArr);
        validateCriteria(i_MatchPredicateArr);
        return aggregateMatchingCells(createAggregator(), areaEval, areaEvalArr, i_MatchPredicateArr);
    }

    private static void validateCriteriaRanges(AreaEval sumRange, AreaEval[] criteriaRanges) throws EvaluationException {
        int h = criteriaRanges[0].getHeight();
        int w = criteriaRanges[0].getWidth();
        if (sumRange != null && (sumRange.getHeight() != h || sumRange.getWidth() != w)) {
            throw EvaluationException.invalidValue();
        }
        for (AreaEval r : criteriaRanges) {
            if (r.getHeight() != h || r.getWidth() != w) {
                throw EvaluationException.invalidValue();
            }
        }
    }

    private static void validateCriteria(CountUtils.I_MatchPredicate[] criteria) throws EvaluationException {
        for (CountUtils.I_MatchPredicate predicate : criteria) {
            if (predicate instanceof Countif.ErrorMatcher) {
                throw new EvaluationException(ErrorEval.valueOf(((Countif.ErrorMatcher) predicate).getValue()));
            }
        }
    }

    private static ValueEval aggregateMatchingCells(Aggregator aggregator, AreaEval sumRange, AreaEval[] ranges, CountUtils.I_MatchPredicate[] predicates) throws EvaluationException {
        int height = ranges[0].getHeight();
        int width = ranges[0].getWidth();
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                boolean matches = true;
                for (int i = 0; i < ranges.length; i++) {
                    AreaEval aeRange = ranges[i];
                    CountUtils.I_MatchPredicate mp = predicates[i];
                    if (mp == null || !mp.matches(aeRange.getRelativeValue(r, c))) {
                        matches = false;
                        break;
                    }
                }
                if (matches) {
                    if (sumRange != null) {
                        ValueEval value = sumRange.getRelativeValue(r, c);
                        if (value instanceof ErrorEval) {
                            throw new EvaluationException((ErrorEval) value);
                        }
                        aggregator.addValue(value);
                    } else {
                        aggregator.addValue(null);
                    }
                }
            }
        }
        return aggregator.getResult();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static AreaEval convertRangeArg(ValueEval eval) throws EvaluationException {
        if (eval instanceof AreaEval) {
            return (AreaEval) eval;
        }
        if (eval instanceof RefEval) {
            return ((RefEval) eval).offset(0, 0, 0, 0);
        }
        throw new EvaluationException(ErrorEval.VALUE_INVALID);
    }
}
