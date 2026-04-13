package org.apache.poi.ss.formula.eval.forked;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Stream;
import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.IStabilityClassifier;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Workbook;

/* loaded from: classes10.dex */
public final class ForkedEvaluator {
    private final WorkbookEvaluator _evaluator;
    private final ForkedEvaluationWorkbook _sewb;

    private ForkedEvaluator(EvaluationWorkbook masterWorkbook, IStabilityClassifier stabilityClassifier, UDFFinder udfFinder) {
        this._sewb = new ForkedEvaluationWorkbook(masterWorkbook);
        this._evaluator = new WorkbookEvaluator(this._sewb, stabilityClassifier, udfFinder);
    }

    public static ForkedEvaluator create(Workbook wb, IStabilityClassifier stabilityClassifier, UDFFinder udfFinder) {
        return new ForkedEvaluator(wb.createEvaluationWorkbook(), stabilityClassifier, udfFinder);
    }

    public void updateCell(String sheetName, int rowIndex, int columnIndex, ValueEval value) {
        ForkedEvaluationCell cell = this._sewb.getOrCreateUpdatableCell(sheetName, rowIndex, columnIndex);
        cell.setValue(value);
        this._evaluator.notifyUpdateCell(cell);
    }

    public void copyUpdatedCells(Workbook workbook) {
        this._sewb.copyUpdatedCells(workbook);
    }

    public ValueEval evaluate(String sheetName, int rowIndex, int columnIndex) {
        EvaluationCell cell = this._sewb.getEvaluationCell(sheetName, rowIndex, columnIndex);
        switch (cell.getCellType()) {
            case BOOLEAN:
                return BoolEval.valueOf(cell.getBooleanCellValue());
            case ERROR:
                return ErrorEval.valueOf(cell.getErrorCellValue());
            case FORMULA:
                return this._evaluator.evaluate(cell);
            case NUMERIC:
                return new NumberEval(cell.getNumericCellValue());
            case STRING:
                return new StringEval(cell.getStringCellValue());
            case BLANK:
                return null;
            default:
                throw new IllegalStateException("Bad cell type (" + cell.getCellType() + ")");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ WorkbookEvaluator[] lambda$setupEnvironment$1(int x$0) {
        return new WorkbookEvaluator[x$0];
    }

    public static void setupEnvironment(String[] workbookNames, ForkedEvaluator[] evaluators) {
        WorkbookEvaluator[] wbEvals = (WorkbookEvaluator[]) Stream.of((Object[]) evaluators).map(new Function() { // from class: org.apache.poi.ss.formula.eval.forked.ForkedEvaluator$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                WorkbookEvaluator workbookEvaluator;
                workbookEvaluator = ((ForkedEvaluator) obj)._evaluator;
                return workbookEvaluator;
            }
        }).toArray(new IntFunction() { // from class: org.apache.poi.ss.formula.eval.forked.ForkedEvaluator$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return ForkedEvaluator.lambda$setupEnvironment$1(i);
            }
        });
        CollaboratingWorkbooksEnvironment.setup(workbookNames, wbEvals);
    }
}
