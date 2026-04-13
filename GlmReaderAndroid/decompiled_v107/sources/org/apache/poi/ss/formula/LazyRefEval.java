package org.apache.poi.ss.formula;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.RefEvalBase;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.AreaI;
import org.apache.poi.ss.util.CellReference;

/* loaded from: classes10.dex */
public final class LazyRefEval extends RefEvalBase {
    private final SheetRangeEvaluator _evaluator;

    public LazyRefEval(int rowIndex, int columnIndex, SheetRangeEvaluator sre) {
        super(sre, rowIndex, columnIndex);
        this._evaluator = sre;
    }

    @Override // org.apache.poi.ss.formula.eval.RefEval
    public ValueEval getInnerValueEval(int sheetIndex) {
        return this._evaluator.getEvalForCell(sheetIndex, getRow(), getColumn());
    }

    public ValueEval getInnerValueEvalForFirstSheet() {
        return this._evaluator.getEvalForCell(this._evaluator.getFirstSheetIndex(), getRow(), getColumn());
    }

    @Override // org.apache.poi.ss.formula.eval.RefEval
    public AreaEval offset(int relFirstRowIx, int relLastRowIx, int relFirstColIx, int relLastColIx) {
        AreaI area = new AreaI.OffsetArea(getRow(), getColumn(), relFirstRowIx, relLastRowIx, relFirstColIx, relLastColIx);
        return new LazyAreaEval(area, this._evaluator);
    }

    public boolean isSubTotal() {
        SheetRefEvaluator sheetEvaluator = this._evaluator.getSheetEvaluator(getFirstSheetIndex());
        return sheetEvaluator.isSubTotal(getRow(), getColumn());
    }

    public boolean isRowHidden() {
        SheetRefEvaluator _sre = this._evaluator.getSheetEvaluator(this._evaluator.getFirstSheetIndex());
        return _sre.isRowHidden(getRow());
    }

    public String toString() {
        CellReference cr = new CellReference(getRow(), getColumn());
        return getClass().getName() + CollectionUtils.DEFAULT_TOSTRING_PREFIX + this._evaluator.getSheetNameRange() + '!' + cr.formatAsString() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
