package org.apache.poi.xssf.usermodel.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.helpers.BaseRowColShifter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfRule;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormatting;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellFormulaType;

/* JADX INFO: Access modifiers changed from: package-private */
@Internal
/* loaded from: classes10.dex */
public final class XSSFRowColShifter {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSSFRowColShifter.class);

    private XSSFRowColShifter() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateNamedRanges(Sheet sheet, FormulaShifter formulaShifter) {
        Workbook wb = sheet.getWorkbook();
        XSSFEvaluationWorkbook fpb = XSSFEvaluationWorkbook.create((XSSFWorkbook) wb);
        for (Name name : wb.getAllNames()) {
            String formula = name.getRefersToFormula();
            int sheetIndex = name.getSheetIndex();
            Ptg[] ptgs = FormulaParser.parse(formula, fpb, FormulaType.NAMEDRANGE, sheetIndex, -1);
            if (formulaShifter.adjustFormula(ptgs, sheetIndex)) {
                String shiftedFmla = FormulaRenderer.toFormulaString(fpb, ptgs);
                name.setRefersToFormula(shiftedFmla);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateFormulas(Sheet sheet, FormulaShifter formulaShifter) {
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
            XSSFRow row = (XSSFRow) r;
            updateRowFormulas(row, formulashifter);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateRowFormulas(XSSFRow row, FormulaShifter formulaShifter) {
        String shiftedFormula;
        XSSFSheet sheet = row.getSheet();
        Iterator<Cell> it = row.iterator();
        while (it.hasNext()) {
            Cell c = it.next();
            XSSFCell cell = (XSSFCell) c;
            CTCell ctCell = cell.getCTCell();
            if (ctCell.isSetF()) {
                CTCellFormula f = ctCell.getF();
                String formula = f.getStringValue();
                if (formula.length() > 0 && (shiftedFormula = shiftFormula(row, formula, formulaShifter)) != null) {
                    f.setStringValue(shiftedFormula);
                    if (f.getT() == STCellFormulaType.SHARED) {
                        int si = Math.toIntExact(f.getSi());
                        CTCellFormula sf = sheet.getSharedFormula(si);
                        sf.setStringValue(shiftedFormula);
                        updateRefInCTCellFormula(row, formulaShifter, sf);
                    }
                }
                updateRefInCTCellFormula(row, formulaShifter, f);
            }
        }
    }

    static String shiftFormula(Row row, String formula, FormulaShifter formulaShifter) {
        Sheet sheet = row.getSheet();
        Workbook wb = sheet.getWorkbook();
        int sheetIndex = wb.getSheetIndex(sheet);
        int rowIndex = row.getRowNum();
        XSSFEvaluationWorkbook fpb = XSSFEvaluationWorkbook.create((XSSFWorkbook) wb);
        try {
            Ptg[] ptgs = FormulaParser.parse(formula, fpb, FormulaType.CELL, sheetIndex, rowIndex);
            if (!formulaShifter.adjustFormula(ptgs, sheetIndex)) {
                return null;
            }
            String shiftedFmla = FormulaRenderer.toFormulaString(fpb, ptgs);
            return shiftedFmla;
        } catch (FormulaParseException fpe) {
            LOG.atWarn().withThrowable(fpe).log("Error shifting formula on row {}", Unbox.box(row.getRowNum()));
            return formula;
        }
    }

    static void updateRefInCTCellFormula(Row row, FormulaShifter formulaShifter, CTCellFormula f) {
        if (f.isSetRef()) {
            String ref = f.getRef();
            String shiftedRef = shiftFormula(row, ref, formulaShifter);
            if (shiftedRef != null) {
                f.setRef(shiftedRef);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateConditionalFormatting(Sheet sheet, FormulaShifter formulaShifter) {
        XSSFWorkbook wb;
        XSSFSheet xsheet = (XSSFSheet) sheet;
        XSSFWorkbook wb2 = xsheet.getWorkbook();
        int sheetIndex = wb2.getSheetIndex(sheet);
        int rowIndex = -1;
        XSSFEvaluationWorkbook fpb = XSSFEvaluationWorkbook.create(wb2);
        CTWorksheet ctWorksheet = xsheet.getCTWorksheet();
        CTConditionalFormatting[] conditionalFormattingArray = ctWorksheet.getConditionalFormattingArray();
        int j = conditionalFormattingArray.length - 1;
        while (j >= 0) {
            CTConditionalFormatting cf = conditionalFormattingArray[j];
            ArrayList<CellRangeAddress> cellRanges = new ArrayList<>();
            for (Object stRef : cf.getSqref()) {
                String[] regions = stRef.toString().split(StringUtils.SPACE);
                for (String region : regions) {
                    cellRanges.add(CellRangeAddress.valueOf(region));
                }
            }
            boolean changed = false;
            List<CellRangeAddress> temp = new ArrayList<>();
            Iterator<CellRangeAddress> it = cellRanges.iterator();
            while (it.hasNext()) {
                CellRangeAddress craOld = it.next();
                CellRangeAddress craNew = BaseRowColShifter.shiftRange(formulaShifter, craOld, sheetIndex);
                if (craNew == null) {
                    changed = true;
                } else {
                    temp.add(craNew);
                    if (craNew != craOld) {
                        changed = true;
                    }
                }
            }
            if (changed) {
                int nRanges = temp.size();
                if (nRanges == 0) {
                    ctWorksheet.removeConditionalFormatting(j);
                    j--;
                    wb2 = wb2;
                    xsheet = xsheet;
                    rowIndex = rowIndex;
                } else {
                    List<String> refs = new ArrayList<>();
                    for (CellRangeAddress a : temp) {
                        refs.add(a.formatAsString());
                        changed = changed;
                    }
                    cf.setSqref(refs);
                }
            }
            CTCfRule[] cfRuleArray = cf.getCfRuleArray();
            int length = cfRuleArray.length;
            int i = 0;
            while (i < length) {
                CTCfRule cfRule = cfRuleArray[i];
                CTCfRule[] cTCfRuleArr = cfRuleArray;
                String[] formulaArray = cfRule.getFormulaArray();
                XSSFSheet xsheet2 = xsheet;
                int i2 = 0;
                while (true) {
                    wb = wb2;
                    if (i2 < formulaArray.length) {
                        String formula = formulaArray[i2];
                        String[] formulaArray2 = formulaArray;
                        int rowIndex2 = rowIndex;
                        Ptg[] ptgs = FormulaParser.parse(formula, fpb, FormulaType.CELL, sheetIndex, -1);
                        if (formulaShifter.adjustFormula(ptgs, sheetIndex)) {
                            String shiftedFmla = FormulaRenderer.toFormulaString(fpb, ptgs);
                            cfRule.setFormulaArray(i2, shiftedFmla);
                        }
                        i2++;
                        wb2 = wb;
                        formulaArray = formulaArray2;
                        rowIndex = rowIndex2;
                    }
                }
                i++;
                cfRuleArray = cTCfRuleArr;
                wb2 = wb;
                xsheet = xsheet2;
            }
            j--;
            wb2 = wb2;
            xsheet = xsheet;
            rowIndex = rowIndex;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateHyperlinks(Sheet sheet, FormulaShifter formulaShifter) {
        int sheetIndex = sheet.getWorkbook().getSheetIndex(sheet);
        for (Hyperlink hyperlink : sheet.getHyperlinkList()) {
            XSSFHyperlink xhyperlink = (XSSFHyperlink) hyperlink;
            String cellRef = xhyperlink.getCellRef();
            CellRangeAddress cra = CellRangeAddress.valueOf(cellRef);
            CellRangeAddress shiftedRange = BaseRowColShifter.shiftRange(formulaShifter, cra, sheetIndex);
            if (shiftedRange != null && shiftedRange != cra) {
                xhyperlink.setCellReference(shiftedRange.formatAsString());
            }
        }
    }
}
