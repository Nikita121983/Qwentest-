package org.apache.poi.hssf.usermodel;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.util.Internal;

/* JADX INFO: Access modifiers changed from: package-private */
@Internal
/* loaded from: classes10.dex */
public final class HSSFEvaluationSheet implements EvaluationSheet {
    private final HSSFSheet _hs;

    public HSSFEvaluationSheet(HSSFSheet hs) {
        this._hs = hs;
    }

    public HSSFSheet getHSSFSheet() {
        return this._hs;
    }

    @Override // org.apache.poi.ss.formula.EvaluationSheet
    public int getLastRowNum() {
        return this._hs.getLastRowNum();
    }

    @Override // org.apache.poi.ss.formula.EvaluationSheet
    public boolean isRowHidden(int rowIndex) {
        HSSFRow row = this._hs.getRow(rowIndex);
        if (row == null) {
            return false;
        }
        return row.getZeroHeight();
    }

    @Override // org.apache.poi.ss.formula.EvaluationSheet
    public EvaluationCell getCell(int rowIndex, int columnIndex) {
        HSSFCell cell;
        HSSFRow row = this._hs.getRow(rowIndex);
        if (row == null || (cell = row.getCell(columnIndex)) == null) {
            return null;
        }
        return new HSSFEvaluationCell(cell, this);
    }

    @Override // org.apache.poi.ss.formula.EvaluationSheet
    public void clearAllCachedResultValues() {
    }
}
