package org.apache.poi.xssf.usermodel;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.TreeMap;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyContext;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.helpers.RowShifter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.helpers.XSSFRowShifter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow;

/* loaded from: classes10.dex */
public class XSSFRow implements Row, Comparable<XSSFRow> {
    private final TreeMap<Integer, XSSFCell> _cells = new TreeMap<>();
    private final CTRow _row;
    private final XSSFSheet _sheet;

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFRow(CTRow row, XSSFSheet sheet) {
        this._row = row;
        this._sheet = sheet;
        for (CTCell c : row.getCArray()) {
            XSSFCell cell = new XSSFCell(this, c);
            Integer colI = Integer.valueOf(cell.getColumnIndex());
            this._cells.put(colI, cell);
            sheet.onReadCell(cell);
        }
        if (!row.isSetR()) {
            int nextRowNum = sheet.getLastRowNum() + 2;
            if (nextRowNum == 2 && sheet.getPhysicalNumberOfRows() == 0) {
                nextRowNum = 1;
            }
            row.setR(nextRowNum);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public XSSFSheet getSheet() {
        return this._sheet;
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public Iterator<Cell> cellIterator() {
        return this._cells.values().iterator();
    }

    @Override // org.apache.poi.ss.usermodel.Row, java.lang.Iterable
    public Spliterator<Cell> spliterator() {
        return this._cells.values().spliterator();
    }

    @Override // java.lang.Comparable
    public int compareTo(XSSFRow other) {
        if (getSheet() != other.getSheet()) {
            throw new IllegalArgumentException("The compared rows must belong to the same sheet");
        }
        int thisRow = getRowNum();
        int otherRow = other.getRowNum();
        return Integer.compare(thisRow, otherRow);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof XSSFRow)) {
            return false;
        }
        XSSFRow other = (XSSFRow) obj;
        return getRowNum() == other.getRowNum() && getSheet() == other.getSheet();
    }

    public int hashCode() {
        return this._row.hashCode();
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public XSSFCell createCell(int columnIndex) {
        return createCell(columnIndex, CellType.BLANK);
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public XSSFCell createCell(int columnIndex, CellType type) {
        CTCell ctCell;
        Integer colI = Integer.valueOf(columnIndex);
        XSSFCell prev = this._cells.get(colI);
        if (prev != null) {
            ctCell = prev.getCTCell();
            ctCell.set(CTCell.Factory.newInstance());
        } else {
            ctCell = this._row.addNewC();
        }
        XSSFCell xcell = new XSSFCell(this, ctCell);
        try {
            xcell.setCellNum(columnIndex);
            if (type != CellType.BLANK && type != CellType.FORMULA) {
                setDefaultValue(xcell, type);
            }
            this._cells.put(colI, xcell);
            return xcell;
        } catch (IllegalArgumentException e) {
            this._row.removeC(this._row.getCList().size() - 1);
            throw e;
        }
    }

    private static void setDefaultValue(XSSFCell cell, CellType type) {
        switch (type) {
            case NUMERIC:
                cell.setCellValue(0.0d);
                return;
            case STRING:
                cell.setCellValue("");
                return;
            case BOOLEAN:
                cell.setCellValue(false);
                return;
            case ERROR:
                cell.setCellErrorValue(FormulaError._NO_ERROR);
                return;
            default:
                throw new AssertionError("Unknown cell-type specified: " + type);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public XSSFCell getCell(int cellnum) {
        return getCell(cellnum, this._sheet.getWorkbook().getMissingCellPolicy());
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public XSSFCell getCell(int cellnum, Row.MissingCellPolicy policy) {
        if (cellnum < 0) {
            throw new IllegalArgumentException("Cell index must be >= 0");
        }
        Integer colI = Integer.valueOf(cellnum);
        XSSFCell cell = this._cells.get(colI);
        switch (policy) {
            case RETURN_NULL_AND_BLANK:
                return cell;
            case RETURN_BLANK_AS_NULL:
                boolean isBlank = cell != null && cell.getCellType() == CellType.BLANK;
                if (isBlank) {
                    return null;
                }
                return cell;
            case CREATE_NULL_AS_BLANK:
                return cell == null ? createCell(cellnum, CellType.BLANK) : cell;
            default:
                throw new IllegalArgumentException("Illegal policy " + policy);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public short getFirstCellNum() {
        return (short) (this._cells.isEmpty() ? -1 : this._cells.firstKey().intValue());
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public short getLastCellNum() {
        return (short) (this._cells.isEmpty() ? -1 : this._cells.lastKey().intValue() + 1);
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public short getHeight() {
        return (short) (getHeightInPoints() * 20.0f);
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public float getHeightInPoints() {
        if (this._row.isSetHt()) {
            return (float) this._row.getHt();
        }
        return this._sheet.getDefaultRowHeightInPoints();
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void setHeight(short height) {
        if (height == -1) {
            if (this._row.isSetHt()) {
                this._row.unsetHt();
            }
            if (this._row.isSetCustomHeight()) {
                this._row.unsetCustomHeight();
                return;
            }
            return;
        }
        this._row.setHt(height / 20.0d);
        this._row.setCustomHeight(true);
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void setHeightInPoints(float height) {
        setHeight((short) (height != -1.0f ? 20.0f * height : -1.0f));
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public int getPhysicalNumberOfCells() {
        return this._cells.size();
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public int getRowNum() {
        return Math.toIntExact(this._row.getR() - 1);
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void setRowNum(int rowIndex) {
        int maxrow = SpreadsheetVersion.EXCEL2007.getLastRowIndex();
        if (rowIndex < 0 || rowIndex > maxrow) {
            throw new IllegalArgumentException("Invalid row number (" + rowIndex + ") outside allowable range (0.." + maxrow + ")");
        }
        this._row.setR(rowIndex + 1);
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public boolean getZeroHeight() {
        return this._row.getHidden();
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void setZeroHeight(boolean height) {
        this._row.setHidden(height);
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public boolean isFormatted() {
        return this._row.isSetS();
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public XSSFCellStyle getRowStyle() {
        if (!isFormatted()) {
            return null;
        }
        StylesTable stylesSource = getSheet().getWorkbook().getStylesSource();
        if (stylesSource.getNumCellStyles() > 0) {
            return stylesSource.getStyleAt(Math.toIntExact(this._row.getS()));
        }
        return null;
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void setRowStyle(CellStyle style) {
        if (style == null) {
            if (this._row.isSetS()) {
                this._row.unsetS();
                this._row.unsetCustomFormat();
                return;
            }
            return;
        }
        StylesTable styleSource = getSheet().getWorkbook().getStylesSource();
        XSSFCellStyle xStyle = (XSSFCellStyle) style;
        xStyle.verifyBelongsToStylesSource(styleSource);
        long idx = styleSource.putStyle(xStyle);
        this._row.setS(idx);
        this._row.setCustomFormat(true);
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void removeCell(Cell cell) {
        if (cell.getRow() != this) {
            throw new IllegalArgumentException("Specified cell does not belong to this row");
        }
        if (!this._cells.containsValue(cell)) {
            throw new IllegalArgumentException("the row does not contain this cell");
        }
        XSSFCell xcell = (XSSFCell) cell;
        if (xcell.isPartOfArrayFormulaGroup()) {
            xcell.setCellFormula(null);
        }
        if (cell.getCellType() == CellType.FORMULA) {
            this._sheet.getWorkbook().onDeleteFormula(xcell);
        }
        Integer colI = Integer.valueOf(cell.getColumnIndex());
        XSSFCell removed = this._cells.remove(colI);
        int i = 0;
        for (CTCell ctCell : this._row.getCArray()) {
            if (ctCell == removed.getCTCell()) {
                this._row.removeC(i);
            }
            i++;
        }
    }

    @Internal
    public CTRow getCTRow() {
        return this._row;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDocumentWrite() {
        CTCell[] cArrayOrig = this._row.getCArray();
        if (cArrayOrig.length == this._cells.size()) {
            boolean allEqual = true;
            Iterator<XSSFCell> it = this._cells.values().iterator();
            int length = cArrayOrig.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                CTCell ctCell = cArrayOrig[i];
                XSSFCell cell = it.next();
                cell.applyDefaultCellStyleIfNecessary();
                if (ctCell == cell.getCTCell()) {
                    i++;
                } else {
                    allEqual = false;
                    break;
                }
            }
            if (allEqual) {
                return;
            }
        }
        fixupCTCells(cArrayOrig);
    }

    private void fixupCTCells(CTCell[] cArrayOrig) {
        CTCell[] cArrayCopy = new CTCell[cArrayOrig.length];
        IdentityHashMap<CTCell, Integer> map = new IdentityHashMap<>(this._cells.size());
        int i = 0;
        for (CTCell ctCell : cArrayOrig) {
            cArrayCopy[i] = (CTCell) ctCell.copy();
            map.put(ctCell, Integer.valueOf(i));
            i++;
        }
        int i2 = 0;
        for (XSSFCell cell : this._cells.values()) {
            Integer correctPosition = map.get(cell.getCTCell());
            Objects.requireNonNull(correctPosition, "Should find CTCell in _row");
            if (correctPosition.intValue() != i2) {
                this._row.setCArray(i2, cArrayCopy[correctPosition.intValue()]);
                cell.setCTCell(this._row.getCArray(i2));
            }
            i2++;
        }
        while (this._row.sizeOfCArray() > this._cells.size()) {
            this._row.removeC(this._cells.size());
        }
    }

    public String toString() {
        return this._row.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void shift(int n) {
        int rownum = getRowNum();
        int newRownum = rownum + n;
        String msg = "Row[rownum=" + rownum + "] contains cell(s) included in a multi-cell array formula. You cannot change part of an array.";
        setRowNum(newRownum);
        Iterator<Cell> it = iterator();
        while (it.hasNext()) {
            Cell c = it.next();
            ((XSSFCell) c).updateCellReferencesForShifting(msg);
        }
    }

    public void copyRowFrom(Row srcRow, CellCopyPolicy policy) {
        copyRowFrom(srcRow, policy, null);
    }

    public void copyRowFrom(Row srcRow, CellCopyPolicy policy, CellCopyContext context) {
        if (srcRow == null) {
            Iterator<Cell> it = iterator();
            while (it.hasNext()) {
                Cell destCell = it.next();
                CellUtil.copyCell(null, destCell, policy, context);
            }
            if (policy.isCopyMergedRegions()) {
                int destRowNum = getRowNum();
                int index = 0;
                Set<Integer> indices = new HashSet<>();
                for (CellRangeAddress destRegion : getSheet().getMergedRegions()) {
                    if (destRowNum == destRegion.getFirstRow() && destRowNum == destRegion.getLastRow()) {
                        indices.add(Integer.valueOf(index));
                    }
                    index++;
                }
                getSheet().removeMergedRegions(indices);
            }
            if (policy.isCopyRowHeight()) {
                setHeight((short) -1);
                return;
            }
            return;
        }
        for (Cell c : srcRow) {
            XSSFCell destCell2 = createCell(c.getColumnIndex());
            CellUtil.copyCell(c, destCell2, policy, context);
        }
        int sheetIndex = this._sheet.getWorkbook().getSheetIndex(this._sheet);
        String sheetName = this._sheet.getWorkbook().getSheetName(sheetIndex);
        int srcRowNum = srcRow.getRowNum();
        int destRowNum2 = getRowNum();
        int rowDifference = destRowNum2 - srcRowNum;
        FormulaShifter formulaShifter = FormulaShifter.createForRowCopy(sheetIndex, sheetName, srcRowNum, srcRowNum, rowDifference, SpreadsheetVersion.EXCEL2007);
        XSSFRowShifter rowShifter = new XSSFRowShifter(this._sheet);
        rowShifter.updateRowFormulas(this, formulaShifter);
        if (policy.isCopyMergedRegions()) {
            for (CellRangeAddress srcRegion : srcRow.getSheet().getMergedRegions()) {
                if (srcRowNum == srcRegion.getFirstRow() && srcRowNum == srcRegion.getLastRow()) {
                    CellRangeAddress destRegion2 = srcRegion.copy();
                    destRegion2.setFirstRow(destRowNum2);
                    destRegion2.setLastRow(destRowNum2);
                    getSheet().addMergedRegion(destRegion2);
                }
            }
        }
        if (policy.isCopyRowHeight()) {
            setHeight(srcRow.getHeight());
        }
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public int getOutlineLevel() {
        return this._row.getOutlineLevel();
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void shiftCellsRight(int firstShiftColumnIndex, int lastShiftColumnIndex, int step) {
        RowShifter.validateShiftParameters(firstShiftColumnIndex, lastShiftColumnIndex, step);
        for (int columnIndex = lastShiftColumnIndex; columnIndex >= firstShiftColumnIndex; columnIndex--) {
            shiftCell(columnIndex, step);
        }
        for (int columnIndex2 = firstShiftColumnIndex; columnIndex2 <= (firstShiftColumnIndex + step) - 1; columnIndex2++) {
            this._cells.remove(Integer.valueOf(columnIndex2));
            XSSFCell targetCell = getCell(columnIndex2);
            if (targetCell != null) {
                targetCell.getCTCell().set(CTCell.Factory.newInstance());
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void shiftCellsLeft(int firstShiftColumnIndex, int lastShiftColumnIndex, int step) {
        RowShifter.validateShiftLeftParameters(firstShiftColumnIndex, lastShiftColumnIndex, step);
        for (int columnIndex = firstShiftColumnIndex; columnIndex <= lastShiftColumnIndex; columnIndex++) {
            shiftCell(columnIndex, -step);
        }
        int columnIndex2 = lastShiftColumnIndex - step;
        for (int columnIndex3 = columnIndex2 + 1; columnIndex3 <= lastShiftColumnIndex; columnIndex3++) {
            this._cells.remove(Integer.valueOf(columnIndex3));
            XSSFCell targetCell = getCell(columnIndex3);
            if (targetCell != null) {
                targetCell.getCTCell().set(CTCell.Factory.newInstance());
            }
        }
    }

    private void shiftCell(int columnIndex, int step) {
        if (columnIndex + step < 0) {
            throw new IllegalStateException("Column index less than zero : " + Integer.valueOf(columnIndex + step));
        }
        XSSFCell currentCell = getCell(columnIndex);
        if (currentCell != null) {
            currentCell.setCellNum(columnIndex + step);
            this._cells.put(Integer.valueOf(columnIndex + step), currentCell);
            return;
        }
        this._cells.remove(Integer.valueOf(columnIndex + step));
        XSSFCell targetCell = getCell(columnIndex + step);
        if (targetCell != null) {
            targetCell.getCTCell().set(CTCell.Factory.newInstance());
        }
    }
}
