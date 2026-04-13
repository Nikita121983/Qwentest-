package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.xmlbeans.impl.common.NameUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class SheetRangeEvaluator implements SheetRange {
    private final int _firstSheetIndex;
    private final int _lastSheetIndex;
    private final SheetRefEvaluator[] _sheetEvaluators;

    public SheetRangeEvaluator(int firstSheetIndex, int lastSheetIndex, SheetRefEvaluator[] sheetEvaluators) {
        if (firstSheetIndex < 0) {
            throw new IllegalArgumentException("Invalid firstSheetIndex: " + firstSheetIndex + ".");
        }
        if (lastSheetIndex < firstSheetIndex) {
            throw new IllegalArgumentException("Invalid lastSheetIndex: " + lastSheetIndex + " for firstSheetIndex: " + firstSheetIndex + ".");
        }
        this._firstSheetIndex = firstSheetIndex;
        this._lastSheetIndex = lastSheetIndex;
        this._sheetEvaluators = (SheetRefEvaluator[]) sheetEvaluators.clone();
    }

    public SheetRangeEvaluator(int onlySheetIndex, SheetRefEvaluator sheetEvaluator) {
        this(onlySheetIndex, onlySheetIndex, new SheetRefEvaluator[]{sheetEvaluator});
    }

    public SheetRefEvaluator getSheetEvaluator(int sheetIndex) {
        if (sheetIndex < this._firstSheetIndex || sheetIndex > this._lastSheetIndex) {
            throw new IllegalArgumentException("Invalid SheetIndex: " + sheetIndex + " - Outside range " + this._firstSheetIndex + " : " + this._lastSheetIndex);
        }
        return this._sheetEvaluators[sheetIndex - this._firstSheetIndex];
    }

    @Override // org.apache.poi.ss.formula.SheetRange
    public int getFirstSheetIndex() {
        return this._firstSheetIndex;
    }

    @Override // org.apache.poi.ss.formula.SheetRange
    public int getLastSheetIndex() {
        return this._lastSheetIndex;
    }

    public String getSheetName(int sheetIndex) {
        return getSheetEvaluator(sheetIndex).getSheetName();
    }

    public String getSheetNameRange() {
        StringBuilder sb = new StringBuilder();
        sb.append(getSheetName(this._firstSheetIndex));
        if (this._firstSheetIndex != this._lastSheetIndex) {
            sb.append(NameUtil.COLON);
            sb.append(getSheetName(this._lastSheetIndex));
        }
        return sb.toString();
    }

    public ValueEval getEvalForCell(int sheetIndex, int rowIndex, int columnIndex) {
        return getSheetEvaluator(sheetIndex).getEvalForCell(rowIndex, columnIndex);
    }

    public int adjustRowNumber(int rowIndex) {
        boolean found = false;
        for (int i = this._firstSheetIndex; i <= this._lastSheetIndex; i++) {
            if (rowIndex >= this._sheetEvaluators[i - this._firstSheetIndex].getMaxRowNum()) {
                found = true;
            }
        }
        if (!found) {
            return rowIndex;
        }
        int maxRowNum = 0;
        for (int i2 = this._firstSheetIndex; i2 <= this._lastSheetIndex; i2++) {
            maxRowNum = Math.max(maxRowNum, this._sheetEvaluators[i2 - this._firstSheetIndex].getLastRowNum());
        }
        int i3 = Math.min(rowIndex, maxRowNum);
        return i3;
    }
}
