package org.apache.poi.xssf.usermodel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public final class XSSFEvaluationWorkbook extends BaseXSSFEvaluationWorkbook {
    private final Map<XSSFSheet, XSSFEvaluationSheet> _sheetCache;

    public static XSSFEvaluationWorkbook create(XSSFWorkbook book) {
        if (book == null) {
            return null;
        }
        return new XSSFEvaluationWorkbook(book);
    }

    private XSSFEvaluationWorkbook(XSSFWorkbook book) {
        super(book);
        this._sheetCache = new HashMap();
    }

    @Override // org.apache.poi.xssf.usermodel.BaseXSSFEvaluationWorkbook, org.apache.poi.ss.formula.EvaluationWorkbook
    public void clearAllCachedResultValues() {
        super.clearAllCachedResultValues();
        this._sheetCache.clear();
    }

    @Override // org.apache.poi.ss.formula.EvaluationWorkbook
    public int getSheetIndex(EvaluationSheet evalSheet) {
        XSSFSheet sheet = ((XSSFEvaluationSheet) evalSheet).getXSSFSheet();
        return this._uBook.getSheetIndex(sheet);
    }

    @Override // org.apache.poi.ss.formula.EvaluationWorkbook
    public EvaluationSheet getSheet(int sheetIndex) {
        if (sheetIndex < 0 || sheetIndex >= this._uBook.getNumberOfSheets()) {
            this._uBook.getSheetAt(sheetIndex);
        }
        final XSSFSheet sheet = this._uBook.getSheetAt(sheetIndex);
        return this._sheetCache.computeIfAbsent(sheet, new Function() { // from class: org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XSSFEvaluationWorkbook.lambda$getSheet$0(XSSFSheet.this, (XSSFSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XSSFEvaluationSheet lambda$getSheet$0(XSSFSheet sheet, XSSFSheet rows) {
        return new XSSFEvaluationSheet(sheet);
    }

    @Override // org.apache.poi.ss.formula.EvaluationWorkbook
    public Ptg[] getFormulaTokens(EvaluationCell evalCell) {
        XSSFCell cell = ((XSSFEvaluationCell) evalCell).getXSSFCell();
        int sheetIndex = this._uBook.getSheetIndex(cell.getSheet());
        return FormulaParser.parse(cell.getCellFormula(this), this, FormulaType.CELL, sheetIndex, cell.getRowIndex());
    }
}
