package org.apache.poi.ss.usermodel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.util.CellRangeAddress;

/* loaded from: classes10.dex */
public abstract class RangeCopier {
    private Sheet destSheet;
    private FormulaShifter horizontalFormulaShifter;
    private Sheet sourceSheet;
    private FormulaShifter verticalFormulaShifter;

    protected abstract void adjustCellReferencesInsideFormula(Cell cell, Sheet sheet, int i, int i2);

    public RangeCopier(Sheet sourceSheet, Sheet destSheet) {
        this.sourceSheet = sourceSheet;
        this.destSheet = destSheet;
    }

    public RangeCopier(Sheet sheet) {
        this(sheet, sheet);
    }

    public void copyRange(CellRangeAddress tilePatternRange, CellRangeAddress tileDestRange) {
        copyRange(tilePatternRange, tileDestRange, false, false);
    }

    public void copyRange(CellRangeAddress tilePatternRange, CellRangeAddress tileDestRange, boolean copyStyles, boolean copyMergedRanges) {
        Sheet sourceCopy = this.sourceSheet.getWorkbook().cloneSheet(this.sourceSheet.getWorkbook().getSheetIndex(this.sourceSheet));
        Map<Integer, CellStyle> styleMap = copyStyles ? new HashMap<Integer, CellStyle>() { // from class: org.apache.poi.ss.usermodel.RangeCopier.1
        } : null;
        int sourceWidthMinus1 = tilePatternRange.getLastColumn() - tilePatternRange.getFirstColumn();
        int sourceHeightMinus1 = tilePatternRange.getLastRow() - tilePatternRange.getFirstRow();
        int nextRowIndexToCopy = tileDestRange.getFirstRow();
        do {
            int nextCellIndexInRowToCopy = tileDestRange.getFirstColumn();
            int heightToCopyMinus1 = Math.min(sourceHeightMinus1, tileDestRange.getLastRow() - nextRowIndexToCopy);
            int bottomLimitToCopy = tilePatternRange.getFirstRow() + heightToCopyMinus1;
            int nextCellIndexInRowToCopy2 = nextCellIndexInRowToCopy;
            do {
                int nextCellIndexInRowToCopy3 = tileDestRange.getLastColumn();
                int widthToCopyMinus1 = Math.min(sourceWidthMinus1, nextCellIndexInRowToCopy3 - nextCellIndexInRowToCopy2);
                int rightLimitToCopy = tilePatternRange.getFirstColumn() + widthToCopyMinus1;
                CellRangeAddress rangeToCopy = new CellRangeAddress(tilePatternRange.getFirstRow(), bottomLimitToCopy, tilePatternRange.getFirstColumn(), rightLimitToCopy);
                copyRange(rangeToCopy, nextCellIndexInRowToCopy2 - rangeToCopy.getFirstColumn(), nextRowIndexToCopy - rangeToCopy.getFirstRow(), sourceCopy, styleMap);
                nextCellIndexInRowToCopy2 += widthToCopyMinus1 + 1;
            } while (nextCellIndexInRowToCopy2 <= tileDestRange.getLastColumn());
            nextRowIndexToCopy += heightToCopyMinus1 + 1;
        } while (nextRowIndexToCopy <= tileDestRange.getLastRow());
        if (copyMergedRanges) {
            this.sourceSheet.getMergedRegions().forEach(new Consumer() { // from class: org.apache.poi.ss.usermodel.RangeCopier$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RangeCopier.this.m2556lambda$copyRange$0$orgapachepoissusermodelRangeCopier((CellRangeAddress) obj);
                }
            });
        }
        int tempCopyIndex = this.sourceSheet.getWorkbook().getSheetIndex(sourceCopy);
        this.sourceSheet.getWorkbook().removeSheetAt(tempCopyIndex);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$copyRange$0$org-apache-poi-ss-usermodel-RangeCopier, reason: not valid java name */
    public /* synthetic */ void m2556lambda$copyRange$0$orgapachepoissusermodelRangeCopier(CellRangeAddress mergedRangeAddress) {
        this.destSheet.addMergedRegion(mergedRangeAddress);
    }

    private void copyRange(CellRangeAddress sourceRange, int deltaX, int deltaY, Sheet sourceClone, Map<Integer, CellStyle> styleMap) {
        int i;
        if (deltaX != 0) {
            this.horizontalFormulaShifter = FormulaShifter.createForColumnCopy(this.sourceSheet.getWorkbook().getSheetIndex(this.sourceSheet), this.sourceSheet.getSheetName(), sourceRange.getFirstColumn(), sourceRange.getLastColumn(), deltaX, this.sourceSheet.getWorkbook().getSpreadsheetVersion());
        }
        if (deltaY == 0) {
            i = deltaY;
        } else {
            i = deltaY;
            this.verticalFormulaShifter = FormulaShifter.createForRowCopy(this.sourceSheet.getWorkbook().getSheetIndex(this.sourceSheet), this.sourceSheet.getSheetName(), sourceRange.getFirstRow(), sourceRange.getLastRow(), i, this.sourceSheet.getWorkbook().getSpreadsheetVersion());
        }
        for (int rowNo = sourceRange.getFirstRow(); rowNo <= sourceRange.getLastRow(); rowNo++) {
            Row sourceRow = sourceClone.getRow(rowNo);
            if (sourceRow != null) {
                for (int columnIndex = sourceRange.getFirstColumn(); columnIndex <= sourceRange.getLastColumn(); columnIndex++) {
                    Cell sourceCell = sourceRow.getCell(columnIndex);
                    if (sourceCell != null) {
                        Row destRow = this.destSheet.getRow(rowNo + i);
                        if (destRow == null) {
                            destRow = this.destSheet.createRow(rowNo + i);
                        }
                        Cell newCell = destRow.getCell(columnIndex + deltaX);
                        if (newCell == null) {
                            newCell = destRow.createCell(columnIndex + deltaX);
                        }
                        cloneCellContent(sourceCell, newCell, styleMap);
                        if (newCell.getCellType() == CellType.FORMULA) {
                            adjustCellReferencesInsideFormula(newCell, this.destSheet, deltaX, deltaY);
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean adjustInBothDirections(Ptg[] ptgs, int sheetIndex, int deltaX, int deltaY) {
        boolean adjustSucceeded = true;
        if (deltaY != 0) {
            adjustSucceeded = this.verticalFormulaShifter.adjustFormula(ptgs, sheetIndex);
        }
        if (deltaX != 0) {
            boolean adjustSucceeded2 = adjustSucceeded && this.horizontalFormulaShifter.adjustFormula(ptgs, sheetIndex);
            return adjustSucceeded2;
        }
        return adjustSucceeded;
    }

    public static void cloneCellContent(Cell srcCell, Cell destCell, Map<Integer, CellStyle> styleMap) {
        if (styleMap != null) {
            if (srcCell.getSheet().getWorkbook() == destCell.getSheet().getWorkbook()) {
                destCell.setCellStyle(srcCell.getCellStyle());
            } else {
                int stHashCode = srcCell.getCellStyle().hashCode();
                CellStyle newCellStyle = styleMap.get(Integer.valueOf(stHashCode));
                if (newCellStyle == null) {
                    newCellStyle = destCell.getSheet().getWorkbook().createCellStyle();
                    newCellStyle.cloneStyleFrom(srcCell.getCellStyle());
                    styleMap.put(Integer.valueOf(stHashCode), newCellStyle);
                }
                destCell.setCellStyle(newCellStyle);
            }
        }
        switch (srcCell.getCellType()) {
            case STRING:
                destCell.setCellValue(srcCell.getStringCellValue());
                return;
            case NUMERIC:
                destCell.setCellValue(srcCell.getNumericCellValue());
                return;
            case BLANK:
                destCell.setBlank();
                return;
            case BOOLEAN:
                destCell.setCellValue(srcCell.getBooleanCellValue());
                return;
            case ERROR:
                destCell.setCellErrorValue(srcCell.getErrorCellValue());
                return;
            case FORMULA:
                String oldFormula = srcCell.getCellFormula();
                destCell.setCellFormula(oldFormula);
                return;
            default:
                return;
        }
    }
}
