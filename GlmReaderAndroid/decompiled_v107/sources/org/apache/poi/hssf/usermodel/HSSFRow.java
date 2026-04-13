package org.apache.poi.hssf.usermodel;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.usermodel.helpers.HSSFRowShifter;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyContext;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.helpers.RowShifter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.Configurator;

/* loaded from: classes10.dex */
public final class HSSFRow implements Row, Comparable<HSSFRow> {
    public static final int INITIAL_CAPACITY = Configurator.getIntValue("HSSFRow.ColInitialCapacity", 5);
    private final HSSFWorkbook book;
    private HSSFCell[] cells;
    private final RowRecord row;
    private int rowNum;
    private final HSSFSheet sheet;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HSSFRow(HSSFWorkbook book, HSSFSheet sheet, int rowNum) {
        this(book, sheet, new RowRecord(rowNum));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HSSFRow(HSSFWorkbook book, HSSFSheet sheet, RowRecord record) {
        this.book = book;
        this.sheet = sheet;
        this.row = record;
        setRowNum(record.getRowNumber());
        if (record.getLastCol() < 0 || INITIAL_CAPACITY < 0) {
            throw new IllegalArgumentException("Had invalid column counts: " + record.getLastCol() + " and " + INITIAL_CAPACITY);
        }
        this.cells = new HSSFCell[record.getLastCol() + INITIAL_CAPACITY];
        record.setEmpty();
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public HSSFCell createCell(int column) {
        return createCell(column, CellType.BLANK);
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public HSSFCell createCell(int columnIndex, CellType type) {
        short shortCellNum;
        short shortCellNum2 = (short) columnIndex;
        if (columnIndex <= 32767) {
            shortCellNum = shortCellNum2;
        } else {
            short shortCellNum3 = (short) (65535 - columnIndex);
            shortCellNum = shortCellNum3;
        }
        HSSFCell cell = new HSSFCell(this.book, this.sheet, getRowNum(), shortCellNum, type);
        addCell(cell);
        this.sheet.getSheet().addValueRecord(getRowNum(), cell.getCellValueRecord());
        return cell;
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void removeCell(Cell cell) {
        if (cell == null) {
            throw new IllegalArgumentException("cell must not be null");
        }
        removeCell((HSSFCell) cell, true);
    }

    private void removeCell(HSSFCell cell, boolean alsoRemoveRecords) {
        int column = cell.getColumnIndex();
        if (column < 0) {
            throw new IllegalStateException("Negative cell indexes not allowed");
        }
        if (column >= this.cells.length || cell != this.cells[column]) {
            throw new IllegalStateException("Specified cell is not from this row");
        }
        if (cell.isPartOfArrayFormulaGroup()) {
            cell.tryToDeleteArrayFormula(null);
        }
        this.cells[column] = null;
        if (alsoRemoveRecords) {
            CellValueRecordInterface cval = cell.getCellValueRecord();
            this.sheet.getSheet().removeValueRecord(getRowNum(), cval);
        }
        if (cell.getColumnIndex() + 1 == this.row.getLastCol()) {
            this.row.setLastCol(calculateNewLastCellPlusOne(this.row.getLastCol()));
        }
        if (cell.getColumnIndex() == this.row.getFirstCol()) {
            this.row.setFirstCol(calculateNewFirstCell(this.row.getFirstCol()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeAllCells() {
        for (HSSFCell cell : this.cells) {
            if (cell != null) {
                removeCell(cell, true);
            }
        }
        this.cells = new HSSFCell[INITIAL_CAPACITY];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HSSFCell createCellFromRecord(CellValueRecordInterface cell) {
        HSSFCell hcell = new HSSFCell(this.book, this.sheet, cell);
        addCell(hcell);
        int colIx = cell.getColumn();
        if (this.row.isEmpty()) {
            this.row.setFirstCol(colIx);
            this.row.setLastCol(colIx + 1);
        } else if (colIx < this.row.getFirstCol()) {
            this.row.setFirstCol(colIx);
        } else if (colIx > this.row.getLastCol()) {
            this.row.setLastCol(colIx + 1);
        }
        return hcell;
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void setRowNum(int rowIndex) {
        int maxrow = SpreadsheetVersion.EXCEL97.getLastRowIndex();
        if (rowIndex < 0 || rowIndex > maxrow) {
            throw new IllegalArgumentException("Invalid row number (" + rowIndex + ") outside allowable range (0.." + maxrow + ")");
        }
        this.rowNum = rowIndex;
        if (this.row != null) {
            this.row.setRowNumber(rowIndex);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public int getRowNum() {
        return this.rowNum;
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public HSSFSheet getSheet() {
        return this.sheet;
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public int getOutlineLevel() {
        return this.row.getOutlineLevel();
    }

    public void moveCell(HSSFCell cell, short newColumn) {
        if (this.cells.length > newColumn && this.cells[newColumn] != null) {
            throw new IllegalArgumentException("Asked to move cell to column " + ((int) newColumn) + " but there's already a cell there");
        }
        if (!this.cells[cell.getColumnIndex()].equals(cell)) {
            throw new IllegalArgumentException("Asked to move a cell, but it didn't belong to our row");
        }
        removeCell(cell, false);
        cell.updateCellNum(newColumn);
        addCell(cell);
    }

    private void addCell(HSSFCell cell) {
        int column = cell.getColumnIndex();
        if (column >= this.cells.length) {
            HSSFCell[] oldCells = this.cells;
            int newSize = ((oldCells.length * 3) / 2) + 1;
            if (newSize < column + 1) {
                newSize = column + INITIAL_CAPACITY;
            }
            this.cells = new HSSFCell[newSize];
            System.arraycopy(oldCells, 0, this.cells, 0, oldCells.length);
        }
        this.cells[column] = cell;
        if (this.row.isEmpty() || column < this.row.getFirstCol()) {
            this.row.setFirstCol((short) column);
        }
        if (this.row.isEmpty() || column >= this.row.getLastCol()) {
            this.row.setLastCol((short) (column + 1));
        }
    }

    private HSSFCell retrieveCell(int cellIndex) {
        if (cellIndex < 0 || cellIndex >= this.cells.length) {
            return null;
        }
        return this.cells[cellIndex];
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public HSSFCell getCell(int cellnum) {
        return getCell(cellnum, this.book.getMissingCellPolicy());
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public HSSFCell getCell(int cellnum, Row.MissingCellPolicy policy) {
        HSSFCell cell = retrieveCell(cellnum);
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
        if (this.row.isEmpty()) {
            return (short) -1;
        }
        return (short) this.row.getFirstCol();
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public short getLastCellNum() {
        if (this.row.isEmpty()) {
            return (short) -1;
        }
        return (short) this.row.getLastCol();
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public int getPhysicalNumberOfCells() {
        int count = 0;
        for (HSSFCell cell : this.cells) {
            if (cell != null) {
                count++;
            }
        }
        return count;
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void setHeight(short height) {
        if (height == -1) {
            this.row.setHeight((short) -32513);
            this.row.setBadFontHeight(false);
        } else {
            this.row.setBadFontHeight(true);
            this.row.setHeight(height);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void setZeroHeight(boolean zHeight) {
        this.row.setZeroHeight(zHeight);
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public boolean getZeroHeight() {
        return this.row.getZeroHeight();
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void setHeightInPoints(float height) {
        if (height == -1.0f) {
            this.row.setHeight((short) -32513);
            this.row.setBadFontHeight(false);
        } else {
            this.row.setBadFontHeight(true);
            this.row.setHeight((short) (20.0f * height));
        }
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public short getHeight() {
        short height = this.row.getHeight();
        if ((32768 & height) != 0) {
            return this.sheet.getSheet().getDefaultRowHeight();
        }
        return (short) (height & Short.MAX_VALUE);
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public float getHeightInPoints() {
        return getHeight() / 20.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public RowRecord getRowRecord() {
        return this.row;
    }

    private int calculateNewLastCellPlusOne(int lastcell) {
        int cellIx = lastcell - 1;
        HSSFCell r = retrieveCell(cellIx);
        while (r == null) {
            if (cellIx < 0) {
                return 0;
            }
            cellIx--;
            r = retrieveCell(cellIx);
        }
        return cellIx + 1;
    }

    private int calculateNewFirstCell(int firstcell) {
        int cellIx = firstcell + 1;
        HSSFCell r = retrieveCell(cellIx);
        while (r == null) {
            if (cellIx <= this.cells.length) {
                return 0;
            }
            cellIx++;
            r = retrieveCell(cellIx);
        }
        return cellIx;
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public boolean isFormatted() {
        return this.row.getFormatted();
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public HSSFCellStyle getRowStyle() {
        if (!isFormatted()) {
            return null;
        }
        short styleIndex = this.row.getXFIndex();
        ExtendedFormatRecord xf = this.book.getWorkbook().getExFormatAt(styleIndex);
        return new HSSFCellStyle(styleIndex, xf, this.book);
    }

    public void setRowStyle(HSSFCellStyle style) {
        this.row.setFormatted(true);
        this.row.setXFIndex(style.getIndex());
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void setRowStyle(CellStyle style) {
        setRowStyle((HSSFCellStyle) style);
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public Iterator<Cell> cellIterator() {
        return new CellIterator();
    }

    /* loaded from: classes10.dex */
    private class CellIterator implements Iterator<Cell> {
        int thisId = -1;
        int nextId = -1;

        public CellIterator() {
            findNext();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextId < HSSFRow.this.cells.length;
        }

        @Override // java.util.Iterator
        public Cell next() {
            if (hasNext()) {
                HSSFCell cell = HSSFRow.this.cells[this.nextId];
                this.thisId = this.nextId;
                findNext();
                return cell;
            }
            throw new NoSuchElementException("At last element");
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.thisId != -1) {
                HSSFRow.this.cells[this.thisId] = null;
                return;
            }
            throw new IllegalStateException("remove() called before next()");
        }

        private void findNext() {
            int i = this.nextId;
            do {
                i++;
                if (i >= HSSFRow.this.cells.length) {
                    break;
                }
            } while (HSSFRow.this.cells[i] == null);
            this.nextId = i;
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(HSSFRow other) {
        if (getSheet() != other.getSheet()) {
            throw new IllegalArgumentException("The compared rows must belong to the same sheet");
        }
        int thisRow = getRowNum();
        int otherRow = other.getRowNum();
        return Integer.compare(thisRow, otherRow);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HSSFRow)) {
            return false;
        }
        HSSFRow other = (HSSFRow) obj;
        return getRowNum() == other.getRowNum() && getSheet() == other.getSheet();
    }

    public int hashCode() {
        return this.row.hashCode();
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void shiftCellsRight(int firstShiftColumnIndex, int lastShiftColumnIndex, int step) {
        RowShifter.validateShiftParameters(firstShiftColumnIndex, lastShiftColumnIndex, step);
        if (lastShiftColumnIndex + step + 1 > this.cells.length) {
            extend(lastShiftColumnIndex + step + 1);
        }
        for (int columnIndex = lastShiftColumnIndex; columnIndex >= firstShiftColumnIndex; columnIndex--) {
            HSSFCell cell = getCell(columnIndex);
            this.cells[columnIndex + step] = null;
            if (cell != null) {
                moveCell(cell, (short) (columnIndex + step));
            }
        }
        for (int columnIndex2 = firstShiftColumnIndex; columnIndex2 <= (firstShiftColumnIndex + step) - 1; columnIndex2++) {
            this.cells[columnIndex2] = null;
        }
    }

    private void extend(int newLength) {
        HSSFCell[] temp = (HSSFCell[]) this.cells.clone();
        this.cells = new HSSFCell[newLength];
        System.arraycopy(temp, 0, this.cells, 0, temp.length);
    }

    @Override // org.apache.poi.ss.usermodel.Row
    public void shiftCellsLeft(int firstShiftColumnIndex, int lastShiftColumnIndex, int step) {
        RowShifter.validateShiftLeftParameters(firstShiftColumnIndex, lastShiftColumnIndex, step);
        for (int columnIndex = firstShiftColumnIndex; columnIndex <= lastShiftColumnIndex; columnIndex++) {
            HSSFCell cell = getCell(columnIndex);
            if (cell != null) {
                this.cells[columnIndex - step] = null;
                moveCell(cell, (short) (columnIndex - step));
            } else {
                this.cells[columnIndex - step] = null;
            }
        }
        int columnIndex2 = lastShiftColumnIndex - step;
        for (int columnIndex3 = columnIndex2 + 1; columnIndex3 <= lastShiftColumnIndex; columnIndex3++) {
            this.cells[columnIndex3] = null;
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
            HSSFCell destCell2 = createCell(c.getColumnIndex());
            CellUtil.copyCell(c, destCell2, policy, context);
        }
        int sheetIndex = this.sheet.getWorkbook().getSheetIndex(this.sheet);
        String sheetName = this.sheet.getWorkbook().getSheetName(sheetIndex);
        int srcRowNum = srcRow.getRowNum();
        int destRowNum2 = getRowNum();
        int rowDifference = destRowNum2 - srcRowNum;
        FormulaShifter formulaShifter = FormulaShifter.createForRowCopy(sheetIndex, sheetName, srcRowNum, srcRowNum, rowDifference, SpreadsheetVersion.EXCEL2007);
        HSSFRowShifter rowShifter = new HSSFRowShifter(this.sheet);
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
}
