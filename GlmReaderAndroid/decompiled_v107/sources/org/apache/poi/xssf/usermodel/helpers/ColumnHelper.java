package org.apache.poi.xssf.usermodel.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.util.CTColComparator;
import org.apache.poi.xssf.util.NumericRanges;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCols;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

/* loaded from: classes10.dex */
public class ColumnHelper {
    private CTWorksheet worksheet;

    public ColumnHelper(CTWorksheet worksheet) {
        this.worksheet = worksheet;
        cleanColumns();
    }

    public void cleanColumns() {
        TreeSet<CTCol> trackedCols = new TreeSet<>(CTColComparator.BY_MIN_MAX);
        CTCols newCols = CTCols.Factory.newInstance();
        CTCols[] colsArray = this.worksheet.getColsArray();
        int i = 0;
        while (i < colsArray.length) {
            CTCols cols = colsArray[i];
            for (CTCol col : cols.getColList()) {
                addCleanColIntoCols(newCols, col, trackedCols);
            }
            i++;
        }
        for (int y = i - 1; y >= 0; y--) {
            this.worksheet.removeCols(y);
        }
        newCols.setColArray((CTCol[]) trackedCols.toArray(new CTCol[0]));
        this.worksheet.addNewCols();
        this.worksheet.setColsArray(0, newCols);
    }

    public CTCols addCleanColIntoCols(CTCols cols, CTCol newCol) {
        TreeSet<CTCol> trackedCols = new TreeSet<>(CTColComparator.BY_MIN_MAX);
        trackedCols.addAll(cols.getColList());
        addCleanColIntoCols(cols, newCol, trackedCols);
        cols.setColArray((CTCol[]) trackedCols.toArray(new CTCol[0]));
        return cols;
    }

    private void addCleanColIntoCols(CTCols cols, CTCol newCol, TreeSet<CTCol> trackedCols) {
        List<CTCol> overlapping = getOverlappingCols(newCol, trackedCols);
        if (overlapping.isEmpty()) {
            trackedCols.add(cloneCol(cols, newCol));
            return;
        }
        trackedCols.removeAll(overlapping);
        Iterator<CTCol> it = overlapping.iterator();
        while (it.hasNext()) {
            CTCol existing = it.next();
            long[] overlap = getOverlap(newCol, existing);
            CTCol overlapCol = cloneCol(cols, existing, overlap);
            setColumnAttributes(newCol, overlapCol);
            trackedCols.add(overlapCol);
            CTCol beforeCol = existing.getMin() < newCol.getMin() ? existing : newCol;
            long[] before = {Math.min(existing.getMin(), newCol.getMin()), overlap[0] - 1};
            if (before[0] <= before[1]) {
                trackedCols.add(cloneCol(cols, beforeCol, before));
            }
            CTCol afterCol = existing.getMax() > newCol.getMax() ? existing : newCol;
            CTCol afterCol2 = afterCol;
            List<CTCol> overlapping2 = overlapping;
            Iterator<CTCol> it2 = it;
            long[] after = {overlap[1] + 1, Math.max(existing.getMax(), newCol.getMax())};
            if (after[0] <= after[1]) {
                trackedCols.add(cloneCol(cols, afterCol2, after));
            }
            overlapping = overlapping2;
            it = it2;
        }
    }

    private CTCol cloneCol(CTCols cols, CTCol col, long[] newRange) {
        CTCol cloneCol = cloneCol(cols, col);
        cloneCol.setMin(newRange[0]);
        cloneCol.setMax(newRange[1]);
        return cloneCol;
    }

    private long[] getOverlap(CTCol col1, CTCol col2) {
        return getOverlappingRange(col1, col2);
    }

    private List<CTCol> getOverlappingCols(CTCol newCol, TreeSet<CTCol> trackedCols) {
        CTCol lower = trackedCols.lower(newCol);
        NavigableSet<CTCol> potentiallyOverlapping = lower == null ? trackedCols : trackedCols.tailSet(lower, overlaps(lower, newCol));
        List<CTCol> overlapping = new ArrayList<>();
        for (CTCol existing : potentiallyOverlapping) {
            if (!overlaps(newCol, existing)) {
                break;
            }
            overlapping.add(existing);
        }
        return overlapping;
    }

    private boolean overlaps(CTCol col1, CTCol col2) {
        return NumericRanges.getOverlappingType(toRange(col1), toRange(col2)) != -1;
    }

    private long[] getOverlappingRange(CTCol col1, CTCol col2) {
        return NumericRanges.getOverlappingRange(toRange(col1), toRange(col2));
    }

    private long[] toRange(CTCol col) {
        return new long[]{col.getMin(), col.getMax()};
    }

    public static void sortColumns(CTCols newCols) {
        CTCol[] colArray = newCols.getColArray();
        Arrays.sort(colArray, CTColComparator.BY_MIN_MAX);
        newCols.setColArray(colArray);
    }

    public CTCol cloneCol(CTCols cols, CTCol col) {
        CTCol newCol = cols.addNewCol();
        newCol.setMin(col.getMin());
        newCol.setMax(col.getMax());
        setColumnAttributes(col, newCol);
        return newCol;
    }

    public CTCol getColumn(long index, boolean splitColumns) {
        return getColumn1Based(1 + index, splitColumns);
    }

    public CTCol getColumn1Based(long index1, boolean splitColumns) {
        char c;
        int i;
        ColumnHelper columnHelper = this;
        CTCol cTCol = null;
        if (columnHelper.worksheet.sizeOfColsArray() == 0) {
            return null;
        }
        char c2 = 0;
        CTCols cols = columnHelper.worksheet.getColsArray(0);
        CTCol[] colArray = cols.getColArray();
        int length = colArray.length;
        int i2 = 0;
        while (i2 < length) {
            CTCol col = colArray[i2];
            CTCol cTCol2 = cTCol;
            int i3 = length;
            long colMin = col.getMin();
            long colMax = col.getMax();
            if (colMin > index1 || colMax < index1) {
                i2++;
                columnHelper = this;
                length = i3;
                cTCol = cTCol2;
                c2 = c2;
            } else {
                if (splitColumns) {
                    if (colMin < index1) {
                        CTCol[] cTColArr = new CTCol[1];
                        cTColArr[c2] = col;
                        c = c2;
                        i = 1;
                        columnHelper.insertCol(cols, colMin, index1 - 1, cTColArr);
                    } else {
                        c = c2;
                        i = 1;
                    }
                    if (colMax > index1) {
                        CTCol[] cTColArr2 = new CTCol[i];
                        cTColArr2[c] = col;
                        insertCol(cols, index1 + 1, colMax, cTColArr2);
                    }
                    col.setMin(index1);
                    col.setMax(index1);
                }
                return col;
            }
        }
        return cTCol;
    }

    private CTCol insertCol(CTCols cols, long min, long max, CTCol[] colsWithAttributes) {
        return insertCol(cols, min, max, colsWithAttributes, false, null);
    }

    private CTCol insertCol(CTCols cols, long min, long max, CTCol[] colsWithAttributes, boolean ignoreExistsCheck, CTCol overrideColumn) {
        long max2;
        long min2;
        CTCols cols2;
        if (ignoreExistsCheck) {
            max2 = max;
            min2 = min;
            cols2 = cols;
        } else {
            boolean columnExists = columnExists(cols, min, max);
            max2 = max;
            min2 = min;
            cols2 = cols;
            if (columnExists) {
                return null;
            }
        }
        CTCol newCol = cols2.insertNewCol(0);
        newCol.setMin(min2);
        newCol.setMax(max2);
        for (CTCol col : colsWithAttributes) {
            setColumnAttributes(col, newCol);
        }
        if (overrideColumn != null) {
            setColumnAttributes(overrideColumn, newCol);
        }
        return newCol;
    }

    public boolean columnExists(CTCols cols, long index) {
        return columnExists1Based(cols, 1 + index);
    }

    private boolean columnExists1Based(CTCols cols, long index1) {
        for (CTCol col : cols.getColArray()) {
            if (col.getMin() == index1) {
                return true;
            }
        }
        return false;
    }

    public void setColumnAttributes(CTCol fromCol, CTCol toCol) {
        if (fromCol.isSetBestFit()) {
            toCol.setBestFit(fromCol.getBestFit());
        }
        if (fromCol.isSetCustomWidth()) {
            toCol.setCustomWidth(fromCol.getCustomWidth());
        }
        if (fromCol.isSetHidden()) {
            toCol.setHidden(fromCol.getHidden());
        }
        if (fromCol.isSetStyle()) {
            toCol.setStyle(fromCol.getStyle());
        }
        if (fromCol.isSetWidth()) {
            toCol.setWidth(fromCol.getWidth());
        }
        if (fromCol.isSetCollapsed()) {
            toCol.setCollapsed(fromCol.getCollapsed());
        }
        if (fromCol.isSetPhonetic()) {
            toCol.setPhonetic(fromCol.getPhonetic());
        }
        if (fromCol.isSetOutlineLevel()) {
            toCol.setOutlineLevel(fromCol.getOutlineLevel());
        }
    }

    public void setColBestFit(long index, boolean bestFit) {
        CTCol col = getOrCreateColumn1Based(1 + index, false);
        col.setBestFit(bestFit);
    }

    public void setCustomWidth(long index, boolean bestFit) {
        CTCol col = getOrCreateColumn1Based(1 + index, true);
        col.setCustomWidth(bestFit);
    }

    public void setColWidth(long index, double width) {
        CTCol col = getOrCreateColumn1Based(1 + index, true);
        col.setWidth(width);
    }

    public void setColHidden(long index, boolean hidden) {
        CTCol col = getOrCreateColumn1Based(1 + index, true);
        col.setHidden(hidden);
    }

    protected CTCol getOrCreateColumn1Based(long index1, boolean splitColumns) {
        CTCol col = getColumn1Based(index1, splitColumns);
        if (col == null) {
            CTCol col2 = this.worksheet.getColsArray(0).addNewCol();
            col2.setMin(index1);
            col2.setMax(index1);
            return col2;
        }
        return col;
    }

    public void setColDefaultStyle(long index, CellStyle style) {
        setColDefaultStyle(index, style.getIndex());
    }

    public void setColDefaultStyle(long index, int styleId) {
        CTCol col = getOrCreateColumn1Based(1 + index, true);
        col.setStyle(styleId);
    }

    public int getColDefaultStyle(long index) {
        if (getColumn(index, false) != null) {
            return (int) getColumn(index, false).getStyle();
        }
        return -1;
    }

    private boolean columnExists(CTCols cols, long min, long max) {
        for (CTCol col : cols.getColList()) {
            if (col.getMin() == min && col.getMax() == max) {
                return true;
            }
        }
        return false;
    }

    public int getIndexOfColumn(CTCols cols, CTCol searchCol) {
        if (cols == null || searchCol == null) {
            return -1;
        }
        int i = 0;
        for (CTCol col : cols.getColList()) {
            if (col.getMin() == searchCol.getMin() && col.getMax() == searchCol.getMax()) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
