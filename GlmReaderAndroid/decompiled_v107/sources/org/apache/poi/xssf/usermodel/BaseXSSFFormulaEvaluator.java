package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.model.ExternalLinksTable;

/* loaded from: classes10.dex */
public abstract class BaseXSSFFormulaEvaluator extends BaseFormulaEvaluator {
    protected abstract EvaluationCell toEvaluationCell(Cell cell);

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseXSSFFormulaEvaluator(WorkbookEvaluator bookEvaluator) {
        super(bookEvaluator);
    }

    @Override // org.apache.poi.ss.formula.BaseFormulaEvaluator
    protected RichTextString createRichTextString(String str) {
        return new XSSFRichTextString(str);
    }

    @Override // org.apache.poi.ss.formula.BaseFormulaEvaluator
    protected CellValue evaluateFormulaCellValue(Cell cell) {
        try {
            EvaluationCell evalCell = toEvaluationCell(cell);
            ValueEval eval = this._bookEvaluator.evaluate(evalCell);
            if (evalCell instanceof XSSFEvaluationCell) {
                cacheExternalWorkbookCells((XSSFEvaluationCell) evalCell);
            }
            if (eval instanceof NumberEval) {
                NumberEval ne = (NumberEval) eval;
                return new CellValue(ne.getNumberValue());
            }
            if (eval instanceof BoolEval) {
                BoolEval be = (BoolEval) eval;
                return CellValue.valueOf(be.getBooleanValue());
            }
            if (eval instanceof StringEval) {
                StringEval ne2 = (StringEval) eval;
                return new CellValue(ne2.getStringValue());
            }
            if (eval instanceof ErrorEval) {
                return CellValue.getError(((ErrorEval) eval).getErrorCode());
            }
            throw new IllegalStateException("Unexpected eval class (" + eval.getClass().getName() + "): " + eval + ", cell: " + new CellReference(cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex(), false, false).formatAsString(true) + ", value: " + cell);
        } catch (IllegalStateException e) {
            if (e.getClass() == IllegalStateException.class) {
                throw new IllegalStateException("Failed to evaluate cell: " + new CellReference(cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex(), false, false).formatAsString(true) + ", value: " + cell, e);
            }
            throw e;
        }
    }

    private void cacheExternalWorkbookCells(XSSFEvaluationCell evalCell) {
        EvaluationWorkbook.ExternalSheet externalSheet;
        Ptg[] formulaTokens = getEvaluationWorkbook().getFormulaTokens(evalCell);
        for (Ptg ptg : formulaTokens) {
            if (ptg instanceof Area3DPxg) {
                Area3DPxg area3DPxg = (Area3DPxg) ptg;
                if (area3DPxg.getExternalWorkbookNumber() > 0 && (externalSheet = getEvaluationWorkbook().getExternalSheet(area3DPxg.getSheetName(), area3DPxg.getLastSheetName(), area3DPxg.getExternalWorkbookNumber())) != null) {
                    processEvalCell(evalCell, externalSheet, area3DPxg);
                }
            }
        }
    }

    private static void processEvalCell(XSSFEvaluationCell evalCell, EvaluationWorkbook.ExternalSheet externalSheet, Area3DPxg area3DPxg) {
        int lastSheet;
        XSSFCell xssfCell;
        XSSFWorkbook xssfWorkbook;
        XSSFCell xssfCell2;
        XSSFWorkbook xssfWorkbook2;
        XSSFRow row;
        int lastColumn;
        int cellIndex;
        XSSFCell xssfCell3 = evalCell.getXSSFCell();
        XSSFWorkbook xssfWorkbook3 = xssfCell3.getSheet().getWorkbook();
        XSSFWorkbook externalWorkbook = (XSSFWorkbook) xssfWorkbook3.getCreationHelper().getReferencedWorkbooks().get(externalSheet.getWorkbookName());
        ExternalLinksTable externalLinksTable = xssfWorkbook3.getExternalLinksTable(area3DPxg.getExternalWorkbookNumber() - 1);
        if (externalWorkbook != null && externalLinksTable != null) {
            int firstSheet = externalWorkbook.getSheetIndex(area3DPxg.getSheetName());
            if (area3DPxg.getLastSheetName() == null) {
                lastSheet = firstSheet;
            } else {
                int lastSheet2 = externalWorkbook.getSheetIndex(area3DPxg.getLastSheetName());
                lastSheet = lastSheet2;
            }
            for (int sheetIndex = firstSheet; sheetIndex <= lastSheet; sheetIndex++) {
                XSSFSheet sheet = externalWorkbook.getSheetAt(sheetIndex);
                int firstRow = area3DPxg.getFirstRow();
                int lastRow = area3DPxg.getLastRow();
                int rowIndex = firstRow;
                while (rowIndex <= lastRow) {
                    XSSFRow row2 = sheet.getRow(rowIndex);
                    if (row2 == null) {
                        xssfCell = xssfCell3;
                        xssfWorkbook = xssfWorkbook3;
                    } else {
                        int firstColumn = area3DPxg.getFirstColumn();
                        int lastColumn2 = area3DPxg.getLastColumn();
                        int cellIndex2 = firstColumn;
                        while (cellIndex2 <= lastColumn2) {
                            XSSFCell cell = row2.getCell(cellIndex2);
                            if (cell == null) {
                                xssfCell2 = xssfCell3;
                                xssfWorkbook2 = xssfWorkbook3;
                                row = row2;
                                lastColumn = lastColumn2;
                                cellIndex = cellIndex2;
                            } else {
                                String cellValue = cell.getRawValue();
                                xssfCell2 = xssfCell3;
                                xssfWorkbook2 = xssfWorkbook3;
                                String cellR = new CellReference(cell).formatAsString(false);
                                lastColumn = lastColumn2;
                                row = row2;
                                cellIndex = cellIndex2;
                                externalLinksTable.cacheData(sheet.getSheetName(), rowIndex + 1, cellR, cellValue);
                            }
                            cellIndex2 = cellIndex + 1;
                            lastColumn2 = lastColumn;
                            row2 = row;
                            xssfCell3 = xssfCell2;
                            xssfWorkbook3 = xssfWorkbook2;
                        }
                        xssfCell = xssfCell3;
                        xssfWorkbook = xssfWorkbook3;
                    }
                    rowIndex++;
                    xssfCell3 = xssfCell;
                    xssfWorkbook3 = xssfWorkbook;
                }
            }
        }
    }

    @Override // org.apache.poi.ss.formula.BaseFormulaEvaluator
    protected void setCellType(Cell cell, CellType cellType) {
        if (cell instanceof XSSFCell) {
            EvaluationWorkbook evaluationWorkbook = getEvaluationWorkbook();
            BaseXSSFEvaluationWorkbook xewb = BaseXSSFEvaluationWorkbook.class.isAssignableFrom(evaluationWorkbook.getClass()) ? (BaseXSSFEvaluationWorkbook) evaluationWorkbook : null;
            ((XSSFCell) cell).setCellType(cellType, xewb);
            return;
        }
        cell.setCellType(cellType);
    }
}
