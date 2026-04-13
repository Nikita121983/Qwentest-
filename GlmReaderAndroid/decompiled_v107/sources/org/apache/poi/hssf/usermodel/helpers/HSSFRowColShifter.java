package org.apache.poi.hssf.usermodel.helpers;

import java.util.Iterator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Internal;

/* JADX INFO: Access modifiers changed from: package-private */
@Internal
/* loaded from: classes10.dex */
public final class HSSFRowColShifter {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) HSSFRowColShifter.class);

    private HSSFRowColShifter() {
    }

    static void updateFormulas(Sheet sheet, FormulaShifter formulaShifter) {
        updateSheetFormulas(sheet, formulaShifter);
        Workbook wb = sheet.getWorkbook();
        for (Sheet sh : wb) {
            if (sheet != sh) {
                updateSheetFormulas(sh, formulaShifter);
            }
        }
    }

    static void updateSheetFormulas(Sheet sh, FormulaShifter formulashifter) {
        for (Row r : sh) {
            HSSFRow row = (HSSFRow) r;
            updateRowFormulas(row, formulashifter);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateRowFormulas(HSSFRow row, FormulaShifter formulaShifter) {
        String formula;
        Iterator<Cell> it = row.iterator();
        while (it.hasNext()) {
            Cell cell = it.next();
            if (cell.getCellType() == CellType.FORMULA && (formula = cell.getCellFormula()) != null && !formula.isEmpty()) {
                String shiftedFormula = shiftFormula(row, formula, formulaShifter);
                cell.setCellFormula(shiftedFormula);
            }
        }
    }

    static String shiftFormula(Row row, String formula, FormulaShifter formulaShifter) {
        Sheet sheet = row.getSheet();
        Workbook wb = sheet.getWorkbook();
        int sheetIndex = wb.getSheetIndex(sheet);
        int rowIndex = row.getRowNum();
        HSSFEvaluationWorkbook fpb = HSSFEvaluationWorkbook.create((HSSFWorkbook) wb);
        try {
            Ptg[] ptgs = FormulaParser.parse(formula, fpb, FormulaType.CELL, sheetIndex, rowIndex);
            if (formulaShifter.adjustFormula(ptgs, sheetIndex)) {
                String shiftedFmla = FormulaRenderer.toFormulaString(fpb, ptgs);
                return shiftedFmla;
            }
            return formula;
        } catch (FormulaParseException fpe) {
            LOG.atWarn().withThrowable(fpe).log("Error shifting formula on row {}", Unbox.box(row.getRowNum()));
            return formula;
        }
    }
}
