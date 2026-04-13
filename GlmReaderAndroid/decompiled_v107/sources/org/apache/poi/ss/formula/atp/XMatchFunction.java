package org.apache.poi.ss.formula.atp;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.LookupUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class XMatchFunction implements FreeRefFunction {
    public static final FreeRefFunction instance = new XMatchFunction(ArgumentsEvaluator.instance);
    private final ArgumentsEvaluator evaluator;

    private XMatchFunction(ArgumentsEvaluator anEvaluator) {
        this.evaluator = anEvaluator;
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        int srcRowIndex = ec.getRowIndex();
        int srcColumnIndex = ec.getColumnIndex();
        return _evaluate(args, srcRowIndex, srcColumnIndex);
    }

    private ValueEval _evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        LookupUtils.MatchMode matchMode;
        LookupUtils.SearchMode searchMode;
        if (args.length < 2) {
            return ErrorEval.VALUE_INVALID;
        }
        LookupUtils.MatchMode matchMode2 = LookupUtils.MatchMode.ExactMatch;
        if (args.length <= 2) {
            matchMode = matchMode2;
        } else {
            try {
                ValueEval matchModeValue = OperandResolver.getSingleValue(args[2], srcRowIndex, srcColumnIndex);
                int matchInt = OperandResolver.coerceValueToInt(matchModeValue);
                LookupUtils.MatchMode matchMode3 = LookupUtils.matchMode(matchInt);
                matchMode = matchMode3;
            } catch (EvaluationException e) {
                return e.getErrorEval();
            } catch (Exception e2) {
                return ErrorEval.VALUE_INVALID;
            }
        }
        LookupUtils.SearchMode searchMode2 = LookupUtils.SearchMode.IterateForward;
        if (args.length <= 3) {
            searchMode = searchMode2;
        } else {
            try {
                ValueEval searchModeValue = OperandResolver.getSingleValue(args[3], srcRowIndex, srcColumnIndex);
                int searchInt = OperandResolver.coerceValueToInt(searchModeValue);
                LookupUtils.SearchMode searchMode3 = LookupUtils.searchMode(searchInt);
                searchMode = searchMode3;
            } catch (EvaluationException e3) {
                return e3.getErrorEval();
            } catch (Exception e4) {
                return ErrorEval.VALUE_INVALID;
            }
        }
        return evaluate(srcRowIndex, srcColumnIndex, args[0], args[1], matchMode, searchMode);
    }

    private ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval lookupEval, ValueEval indexEval, LookupUtils.MatchMode matchMode, LookupUtils.SearchMode searchMode) {
        LookupUtils.ValueVector vector;
        try {
            ValueEval lookupValue = OperandResolver.getSingleValue(lookupEval, srcRowIndex, srcColumnIndex);
            TwoDEval tableArray = LookupUtils.resolveTableArrayArg(indexEval);
            if (tableArray.isColumn()) {
                vector = LookupUtils.createColumnVector(tableArray, 0);
            } else {
                vector = LookupUtils.createRowVector(tableArray, 0);
            }
            int matchedIdx = LookupUtils.xlookupIndexOfValue(lookupValue, vector, matchMode, searchMode);
            return new NumberEval(matchedIdx + 1.0d);
        } catch (EvaluationException e) {
            return e.getErrorEval();
        }
    }
}
