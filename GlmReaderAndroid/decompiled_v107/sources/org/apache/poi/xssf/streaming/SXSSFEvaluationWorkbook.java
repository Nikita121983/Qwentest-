package org.apache.poi.xssf.streaming;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.BaseXSSFEvaluationWorkbook;

@Internal
/* loaded from: classes10.dex */
public final class SXSSFEvaluationWorkbook extends BaseXSSFEvaluationWorkbook {
    private final SXSSFWorkbook _sxssfBook;

    public static SXSSFEvaluationWorkbook create(SXSSFWorkbook book) {
        if (book == null) {
            return null;
        }
        return new SXSSFEvaluationWorkbook(book);
    }

    private SXSSFEvaluationWorkbook(SXSSFWorkbook book) {
        super(book.getXSSFWorkbook());
        this._sxssfBook = book;
    }

    @Override // org.apache.poi.ss.formula.EvaluationWorkbook
    public int getSheetIndex(EvaluationSheet evalSheet) {
        SXSSFSheet sheet = ((SXSSFEvaluationSheet) evalSheet).getSXSSFSheet();
        return this._sxssfBook.getSheetIndex(sheet);
    }

    @Override // org.apache.poi.ss.formula.EvaluationWorkbook
    public EvaluationSheet getSheet(int sheetIndex) {
        return new SXSSFEvaluationSheet(this._sxssfBook.getSheetAt(sheetIndex));
    }

    @Override // org.apache.poi.ss.formula.EvaluationWorkbook
    public Ptg[] getFormulaTokens(EvaluationCell evalCell) {
        SXSSFCell cell = ((SXSSFEvaluationCell) evalCell).getSXSSFCell();
        return FormulaParser.parse(cell.getCellFormula(), this, FormulaType.CELL, this._sxssfBook.getSheetIndex(cell.getSheet()));
    }
}
