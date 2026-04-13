package org.apache.poi.xssf.streaming;

import java.util.Iterator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.IStabilityClassifier;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.BaseXSSFFormulaEvaluator;

/* loaded from: classes10.dex */
public final class SXSSFFormulaEvaluator extends BaseXSSFFormulaEvaluator {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) SXSSFFormulaEvaluator.class);
    private final SXSSFWorkbook wb;

    public SXSSFFormulaEvaluator(SXSSFWorkbook workbook) {
        this(workbook, null, null);
    }

    private SXSSFFormulaEvaluator(SXSSFWorkbook workbook, IStabilityClassifier stabilityClassifier, UDFFinder udfFinder) {
        this(workbook, new WorkbookEvaluator(SXSSFEvaluationWorkbook.create(workbook), stabilityClassifier, udfFinder));
    }

    private SXSSFFormulaEvaluator(SXSSFWorkbook workbook, WorkbookEvaluator bookEvaluator) {
        super(bookEvaluator);
        this.wb = workbook;
    }

    public static SXSSFFormulaEvaluator create(SXSSFWorkbook workbook, IStabilityClassifier stabilityClassifier, UDFFinder udfFinder) {
        return new SXSSFFormulaEvaluator(workbook, stabilityClassifier, udfFinder);
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public void notifySetFormula(Cell cell) {
        this._bookEvaluator.notifyUpdateCell(new SXSSFEvaluationCell((SXSSFCell) cell));
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public void notifyDeleteCell(Cell cell) {
        this._bookEvaluator.notifyDeleteCell(new SXSSFEvaluationCell((SXSSFCell) cell));
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public void notifyUpdateCell(Cell cell) {
        this._bookEvaluator.notifyUpdateCell(new SXSSFEvaluationCell((SXSSFCell) cell));
    }

    @Override // org.apache.poi.xssf.usermodel.BaseXSSFFormulaEvaluator
    protected EvaluationCell toEvaluationCell(Cell cell) {
        if (!(cell instanceof SXSSFCell)) {
            throw new IllegalArgumentException("Unexpected type of cell: " + cell.getClass() + ". Only SXSSFCells can be evaluated.");
        }
        return new SXSSFEvaluationCell((SXSSFCell) cell);
    }

    @Override // org.apache.poi.ss.formula.BaseFormulaEvaluator, org.apache.poi.ss.usermodel.FormulaEvaluator
    public SXSSFCell evaluateInCell(Cell cell) {
        return (SXSSFCell) super.evaluateInCell(cell);
    }

    public static void evaluateAllFormulaCells(SXSSFWorkbook wb, boolean skipOutOfWindow) {
        int lastFlushedRowNum;
        SXSSFFormulaEvaluator eval = new SXSSFFormulaEvaluator(wb);
        Iterator<Sheet> it = wb.iterator();
        while (it.hasNext()) {
            if (((SXSSFSheet) it.next()).areAllRowsFlushed()) {
                throw new SheetsFlushedException();
            }
        }
        Iterator<Sheet> it2 = wb.iterator();
        while (it2.hasNext()) {
            Sheet sheet = it2.next();
            if ((sheet instanceof SXSSFSheet) && (lastFlushedRowNum = ((SXSSFSheet) sheet).getLastFlushedRowNum()) > -1) {
                if (!skipOutOfWindow) {
                    throw new RowFlushedException(0, lastFlushedRowNum);
                }
                LOG.atInfo().log("Rows up to {} have already been flushed, skipping", Unbox.box(lastFlushedRowNum));
            }
            for (Row r : sheet) {
                for (Cell c : r) {
                    if (c.getCellType() == CellType.FORMULA) {
                        eval.evaluateFormulaCell(c);
                    }
                }
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.FormulaEvaluator
    public void evaluateAll() {
        evaluateAllFormulaCells(this.wb, false);
    }

    /* loaded from: classes10.dex */
    public static class SheetsFlushedException extends IllegalStateException {
        protected SheetsFlushedException() {
            super("One or more sheets have been flushed, cannot evaluate all cells");
        }
    }

    /* loaded from: classes10.dex */
    public static class RowFlushedException extends IllegalStateException {
        /* JADX INFO: Access modifiers changed from: protected */
        public RowFlushedException(int rowNum, int lastFlushedRowNum) {
            super("Row " + rowNum + " has been flushed (rows up to " + lastFlushedRowNum + " have been flushed), cannot evaluate all cells");
        }
    }
}
