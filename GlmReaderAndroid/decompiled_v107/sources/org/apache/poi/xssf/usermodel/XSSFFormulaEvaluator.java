package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.IStabilityClassifier;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Cell;

/* loaded from: classes10.dex */
public final class XSSFFormulaEvaluator extends BaseXSSFFormulaEvaluator {
    private final XSSFWorkbook _book;

    public XSSFFormulaEvaluator(XSSFWorkbook workbook) {
        this(workbook, null, null);
    }

    private XSSFFormulaEvaluator(XSSFWorkbook workbook, IStabilityClassifier stabilityClassifier, UDFFinder udfFinder) {
        this(workbook, new WorkbookEvaluator(XSSFEvaluationWorkbook.create(workbook), stabilityClassifier, udfFinder));
    }

    protected XSSFFormulaEvaluator(XSSFWorkbook workbook, WorkbookEvaluator bookEvaluator) {
        super(bookEvaluator);
        this._book = workbook;
    }

    public static XSSFFormulaEvaluator create(XSSFWorkbook workbook, IStabilityClassifier stabilityClassifier, UDFFinder udfFinder) {
        return new XSSFFormulaEvaluator(workbook, stabilityClassifier, udfFinder);
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public void notifySetFormula(Cell cell) {
        this._bookEvaluator.notifyUpdateCell(new XSSFEvaluationCell((XSSFCell) cell));
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public void notifyDeleteCell(Cell cell) {
        this._bookEvaluator.notifyDeleteCell(new XSSFEvaluationCell((XSSFCell) cell));
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public void notifyUpdateCell(Cell cell) {
        this._bookEvaluator.notifyUpdateCell(new XSSFEvaluationCell((XSSFCell) cell));
    }

    public static void evaluateAllFormulaCells(XSSFWorkbook wb) {
        BaseFormulaEvaluator.evaluateAllFormulaCells(wb);
    }

    @Override // org.apache.poi.ss.formula.BaseFormulaEvaluator, org.apache.poi.ss.usermodel.FormulaEvaluator
    public XSSFCell evaluateInCell(Cell cell) {
        return (XSSFCell) super.evaluateInCell(cell);
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public void evaluateAll() {
        evaluateAllFormulaCells(this._book, this);
    }

    @Override // org.apache.poi.xssf.usermodel.BaseXSSFFormulaEvaluator
    protected EvaluationCell toEvaluationCell(Cell cell) {
        if (!(cell instanceof XSSFCell)) {
            throw new IllegalArgumentException("Unexpected type of cell: " + cell.getClass() + ". Only XSSFCells can be evaluated.");
        }
        return new XSSFEvaluationCell((XSSFCell) cell);
    }
}
