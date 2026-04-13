package org.apache.poi.ss.formula;

import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/* loaded from: classes10.dex */
public abstract class BaseFormulaEvaluator implements FormulaEvaluator, WorkbookEvaluatorProvider {
    protected final WorkbookEvaluator _bookEvaluator;

    protected abstract RichTextString createRichTextString(String str);

    protected abstract CellValue evaluateFormulaCellValue(Cell cell);

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseFormulaEvaluator(WorkbookEvaluator bookEvaluator) {
        this._bookEvaluator = bookEvaluator;
    }

    public static void setupEnvironment(String[] workbookNames, BaseFormulaEvaluator[] evaluators) {
        WorkbookEvaluator[] wbEvals = new WorkbookEvaluator[evaluators.length];
        for (int i = 0; i < wbEvals.length; i++) {
            wbEvals[i] = evaluators[i]._bookEvaluator;
        }
        CollaboratingWorkbooksEnvironment.setup(workbookNames, wbEvals);
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public void setupReferencedWorkbooks(Map<String, FormulaEvaluator> evaluators) {
        CollaboratingWorkbooksEnvironment.setupFormulaEvaluator(evaluators);
    }

    @Override // org.apache.poi.ss.formula.WorkbookEvaluatorProvider
    public WorkbookEvaluator _getWorkbookEvaluator() {
        return this._bookEvaluator;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EvaluationWorkbook getEvaluationWorkbook() {
        return this._bookEvaluator.getWorkbook();
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public void clearAllCachedResultValues() {
        this._bookEvaluator.clearAllCachedResultValues();
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public CellValue evaluate(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case BOOLEAN:
                return CellValue.valueOf(cell.getBooleanCellValue());
            case ERROR:
                return CellValue.getError(cell.getErrorCellValue());
            case FORMULA:
                return evaluateFormulaCellValue(cell);
            case NUMERIC:
                return new CellValue(cell.getNumericCellValue());
            case STRING:
                return new CellValue(cell.getRichStringCellValue().getString());
            case BLANK:
                return null;
            default:
                throw new IllegalStateException("Bad cell type (" + cell.getCellType() + ")");
        }
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public Cell evaluateInCell(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == CellType.FORMULA) {
            CellValue cv = evaluateFormulaCellValue(cell);
            setCellValue(cell, cv);
            setCellType(cell, cv);
            setCellValue(cell, cv);
        }
        return cell;
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public CellType evaluateFormulaCell(Cell cell) {
        if (cell == null || cell.getCellType() != CellType.FORMULA) {
            return CellType._NONE;
        }
        CellValue cv = evaluateFormulaCellValue(cell);
        setCellValue(cell, cv);
        return cv.getCellType();
    }

    protected void setCellType(Cell cell, CellValue cv) {
        CellType cellType = cv.getCellType();
        switch (cellType) {
            case BOOLEAN:
            case ERROR:
            case NUMERIC:
            case STRING:
                setCellType(cell, cellType);
                return;
            case FORMULA:
                throw new IllegalArgumentException("This should never happen. Formulas should have already been evaluated.");
            case BLANK:
                throw new IllegalArgumentException("This should never happen. Blanks eventually get translated to zero.");
            default:
                throw new IllegalStateException("Unexpected cell value type (" + cellType + ")");
        }
    }

    protected void setCellType(Cell cell, CellType cellType) {
        cell.setCellType(cellType);
    }

    protected void setCellValue(Cell cell, CellValue cv) {
        CellType cellType = cv.getCellType();
        switch (cellType) {
            case BOOLEAN:
                cell.setCellValue(cv.getBooleanValue());
                return;
            case ERROR:
                cell.setCellErrorValue(cv.getErrorValue());
                return;
            case FORMULA:
            default:
                throw new IllegalStateException("Unexpected cell value type (" + cellType + ")");
            case NUMERIC:
                cell.setCellValue(cv.getNumberValue());
                return;
            case STRING:
                cell.setCellValue(createRichTextString(cv.getStringValue()));
                return;
        }
    }

    public static void evaluateAllFormulaCells(Workbook wb) {
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        evaluateAllFormulaCells(wb, evaluator);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void evaluateAllFormulaCells(Workbook wb, FormulaEvaluator evaluator) {
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            for (Row r : sheet) {
                for (Cell c : r) {
                    if (c.getCellType() == CellType.FORMULA) {
                        evaluator.evaluateFormulaCell(c);
                    }
                }
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public void setIgnoreMissingWorkbooks(boolean ignore) {
        this._bookEvaluator.setIgnoreMissingWorkbooks(ignore);
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public void setDebugEvaluationOutputForNextEval(boolean value) {
        this._bookEvaluator.setDebugEvaluationOutputForNextEval(value);
    }
}
