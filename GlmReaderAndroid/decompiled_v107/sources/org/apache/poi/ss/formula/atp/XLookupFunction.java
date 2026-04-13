package org.apache.poi.ss.formula.atp;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.ArrayFunction;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.LookupUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class XLookupFunction implements FreeRefFunction, ArrayFunction {
    public static final FreeRefFunction instance = new XLookupFunction(ArgumentsEvaluator.instance);
    private final ArgumentsEvaluator evaluator;

    private XLookupFunction(ArgumentsEvaluator anEvaluator) {
        this.evaluator = anEvaluator;
    }

    @Override // org.apache.poi.ss.formula.functions.FreeRefFunction
    public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
        int srcRowIndex = ec.getRowIndex();
        int srcColumnIndex = ec.getColumnIndex();
        return _evaluate(args, srcRowIndex, srcColumnIndex);
    }

    @Override // org.apache.poi.ss.formula.functions.ArrayFunction
    public ValueEval evaluateArray(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        return _evaluate(args, srcRowIndex, srcColumnIndex);
    }

    private ValueEval _evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        ValueEval notFound;
        LookupUtils.MatchMode matchMode;
        LookupUtils.SearchMode searchMode;
        if (args.length < 3) {
            return ErrorEval.VALUE_INVALID;
        }
        ValueEval notFound2 = BlankEval.instance;
        if (args.length <= 3) {
            notFound = notFound2;
        } else {
            try {
                ValueEval notFoundValue = OperandResolver.getSingleValue(args[3], srcRowIndex, srcColumnIndex);
                if (notFoundValue != null) {
                    notFound2 = notFoundValue;
                }
                notFound = notFound2;
            } catch (EvaluationException e) {
                return e.getErrorEval();
            }
        }
        LookupUtils.MatchMode matchMode2 = LookupUtils.MatchMode.ExactMatch;
        if (args.length <= 4) {
            matchMode = matchMode2;
        } else {
            try {
                ValueEval matchModeValue = OperandResolver.getSingleValue(args[4], srcRowIndex, srcColumnIndex);
                int matchInt = OperandResolver.coerceValueToInt(matchModeValue);
                LookupUtils.MatchMode matchMode3 = LookupUtils.matchMode(matchInt);
                matchMode = matchMode3;
            } catch (EvaluationException e2) {
                return e2.getErrorEval();
            } catch (Exception e3) {
                return ErrorEval.VALUE_INVALID;
            }
        }
        LookupUtils.SearchMode searchMode2 = LookupUtils.SearchMode.IterateForward;
        if (args.length <= 5) {
            searchMode = searchMode2;
        } else {
            try {
                ValueEval searchModeValue = OperandResolver.getSingleValue(args[5], srcRowIndex, srcColumnIndex);
                int searchInt = OperandResolver.coerceValueToInt(searchModeValue);
                LookupUtils.SearchMode searchMode3 = LookupUtils.searchMode(searchInt);
                searchMode = searchMode3;
            } catch (EvaluationException e4) {
                return e4.getErrorEval();
            } catch (Exception e5) {
                return ErrorEval.VALUE_INVALID;
            }
        }
        return evaluate(srcRowIndex, srcColumnIndex, args[0], args[1], args[2], notFound, matchMode, searchMode);
    }

    private ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval lookupEval, ValueEval indexEval, ValueEval returnEval, ValueEval notFound, LookupUtils.MatchMode matchMode, LookupUtils.SearchMode searchMode) {
        LookupUtils.ValueVector vector;
        int width;
        try {
            ValueEval lookupValue = OperandResolver.getSingleValue(lookupEval, srcRowIndex, srcColumnIndex);
            TwoDEval tableArray = LookupUtils.resolveTableArrayArg(indexEval);
            if (tableArray.isColumn()) {
                vector = LookupUtils.createColumnVector(tableArray, 0);
            } else {
                LookupUtils.ValueVector vector2 = LookupUtils.createRowVector(tableArray, 0);
                vector = vector2;
            }
            try {
                try {
                    int matchedIdx = LookupUtils.xlookupIndexOfValue(lookupValue, vector, matchMode, searchMode);
                    if (!(returnEval instanceof AreaEval)) {
                        return returnEval;
                    }
                    AreaEval area = (AreaEval) returnEval;
                    return tableArray.isColumn() ? area.offset(matchedIdx, matchedIdx, 0, area.getWidth() - 1) : area.offset(0, area.getHeight() - 1, matchedIdx, matchedIdx);
                } catch (EvaluationException e) {
                    e = e;
                    return e.getErrorEval();
                }
            } catch (EvaluationException e2) {
                if (ErrorEval.NA.equals(e2.getErrorEval())) {
                    if (notFound != BlankEval.instance) {
                        return (!(returnEval instanceof AreaEval) || (width = ((AreaEval) returnEval).getWidth()) <= 1) ? notFound : notFoundAreaEval(notFound, width);
                    }
                    return ErrorEval.NA;
                }
                return e2.getErrorEval();
            }
        } catch (EvaluationException e3) {
            e = e3;
        }
    }

    private AreaEval notFoundAreaEval(final ValueEval notFound, final int width) {
        return new AreaEval() { // from class: org.apache.poi.ss.formula.atp.XLookupFunction.1
            @Override // org.apache.poi.ss.formula.eval.AreaEval
            public int getFirstRow() {
                return 0;
            }

            @Override // org.apache.poi.ss.formula.eval.AreaEval
            public int getLastRow() {
                return 0;
            }

            @Override // org.apache.poi.ss.formula.eval.AreaEval
            public int getFirstColumn() {
                return 0;
            }

            @Override // org.apache.poi.ss.formula.eval.AreaEval
            public int getLastColumn() {
                return width - 1;
            }

            @Override // org.apache.poi.ss.formula.eval.AreaEval
            public ValueEval getAbsoluteValue(int row, int col) {
                if (col == 0) {
                    return notFound;
                }
                return new StringEval("");
            }

            @Override // org.apache.poi.ss.formula.eval.AreaEval
            public boolean contains(int row, int col) {
                return containsRow(row) && containsColumn(col);
            }

            @Override // org.apache.poi.ss.formula.eval.AreaEval
            public boolean containsColumn(int col) {
                return col < width;
            }

            @Override // org.apache.poi.ss.formula.eval.AreaEval
            public boolean containsRow(int row) {
                return row == 0;
            }

            @Override // org.apache.poi.ss.formula.eval.AreaEval, org.apache.poi.ss.formula.TwoDEval
            public int getWidth() {
                return width;
            }

            @Override // org.apache.poi.ss.formula.eval.AreaEval, org.apache.poi.ss.formula.TwoDEval
            public int getHeight() {
                return 1;
            }

            @Override // org.apache.poi.ss.formula.eval.AreaEval
            public ValueEval getRelativeValue(int relativeRowIndex, int relativeColumnIndex) {
                return getAbsoluteValue(relativeRowIndex, relativeColumnIndex);
            }

            @Override // org.apache.poi.ss.formula.eval.AreaEval
            public AreaEval offset(int relFirstRowIx, int relLastRowIx, int relFirstColIx, int relLastColIx) {
                return null;
            }

            @Override // org.apache.poi.ss.formula.ThreeDEval
            public ValueEval getValue(int sheetIndex, int rowIndex, int columnIndex) {
                return getAbsoluteValue(rowIndex, columnIndex);
            }

            @Override // org.apache.poi.ss.formula.SheetRange
            public int getFirstSheetIndex() {
                return 0;
            }

            @Override // org.apache.poi.ss.formula.SheetRange
            public int getLastSheetIndex() {
                return 0;
            }

            @Override // org.apache.poi.ss.formula.TwoDEval
            public ValueEval getValue(int rowIndex, int columnIndex) {
                return getAbsoluteValue(rowIndex, columnIndex);
            }

            @Override // org.apache.poi.ss.formula.TwoDEval
            public boolean isColumn() {
                return false;
            }

            @Override // org.apache.poi.ss.formula.TwoDEval
            public TwoDEval getRow(int rowIndex) {
                return null;
            }

            @Override // org.apache.poi.ss.formula.TwoDEval
            public TwoDEval getColumn(int columnIndex) {
                return null;
            }

            @Override // org.apache.poi.ss.formula.TwoDEval
            public boolean isSubTotal(int rowIndex, int columnIndex) {
                return false;
            }

            @Override // org.apache.poi.ss.formula.TwoDEval
            public boolean isRowHidden(int rowIndex) {
                return false;
            }
        };
    }
}
