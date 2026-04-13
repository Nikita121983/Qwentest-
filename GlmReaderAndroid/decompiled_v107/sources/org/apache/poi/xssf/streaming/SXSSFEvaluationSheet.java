package org.apache.poi.xssf.streaming;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.streaming.SXSSFFormulaEvaluator;

/* JADX INFO: Access modifiers changed from: package-private */
@Internal
/* loaded from: classes10.dex */
public final class SXSSFEvaluationSheet implements EvaluationSheet {
    private final SXSSFSheet _xs;

    public SXSSFEvaluationSheet(SXSSFSheet sheet) {
        this._xs = sheet;
    }

    public SXSSFSheet getSXSSFSheet() {
        return this._xs;
    }

    @Override // org.apache.poi.ss.formula.EvaluationSheet
    public int getLastRowNum() {
        return this._xs.getLastRowNum();
    }

    @Override // org.apache.poi.ss.formula.EvaluationSheet
    public boolean isRowHidden(int rowIndex) {
        SXSSFRow row = this._xs.getRow(rowIndex);
        if (row == null) {
            return false;
        }
        return row.getZeroHeight();
    }

    @Override // org.apache.poi.ss.formula.EvaluationSheet
    public EvaluationCell getCell(int rowIndex, int columnIndex) {
        SXSSFRow row = this._xs.getRow(rowIndex);
        if (row == null) {
            if (rowIndex > this._xs.getLastFlushedRowNum()) {
                return null;
            }
            throw new SXSSFFormulaEvaluator.RowFlushedException(rowIndex, this._xs.getLastFlushedRowNum());
        }
        SXSSFCell cell = row.getCell(columnIndex);
        if (cell == null) {
            return null;
        }
        return new SXSSFEvaluationCell(cell, this);
    }

    @Override // org.apache.poi.ss.formula.EvaluationSheet
    public void clearAllCachedResultValues() {
    }
}
