package org.apache.poi.ss.usermodel.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/* loaded from: classes10.dex */
public abstract class ColumnShifter extends BaseRowColShifter {
    protected final Sheet sheet;

    public ColumnShifter(Sheet sh) {
        this.sheet = sh;
    }

    @Override // org.apache.poi.ss.usermodel.helpers.BaseRowColShifter
    public List<CellRangeAddress> shiftMergedRegions(int startColumn, int endColumn, int n) {
        List<CellRangeAddress> shiftedRegions = new ArrayList<>();
        Set<Integer> removedIndices = new HashSet<>();
        int size = this.sheet.getNumMergedRegions();
        for (int i = 0; i < size; i++) {
            CellRangeAddress merged = this.sheet.getMergedRegion(i);
            if (removalNeeded(merged, startColumn, endColumn, n)) {
                removedIndices.add(Integer.valueOf(i));
            } else {
                boolean inStart = merged.getFirstColumn() >= startColumn || merged.getLastColumn() >= startColumn;
                boolean inEnd = merged.getFirstColumn() <= endColumn || merged.getLastColumn() <= endColumn;
                if (inStart && inEnd && !merged.containsColumn(startColumn - 1) && !merged.containsColumn(endColumn + 1)) {
                    merged.setFirstColumn(merged.getFirstColumn() + n);
                    merged.setLastColumn(merged.getLastColumn() + n);
                    shiftedRegions.add(merged);
                    removedIndices.add(Integer.valueOf(i));
                }
            }
        }
        if (!removedIndices.isEmpty()) {
            this.sheet.removeMergedRegions(removedIndices);
        }
        for (CellRangeAddress region : shiftedRegions) {
            this.sheet.addMergedRegion(region);
        }
        return shiftedRegions;
    }

    private boolean removalNeeded(CellRangeAddress merged, int startColumn, int endColumn, int n) {
        CellRangeAddress overwrite;
        int movedColumns = (endColumn - startColumn) + 1;
        if (n > 0) {
            int firstCol = Math.max(endColumn + 1, (endColumn + n) - movedColumns);
            int lastCol = endColumn + n;
            overwrite = new CellRangeAddress(merged.getFirstRow(), merged.getLastRow(), firstCol, lastCol);
        } else {
            int firstCol2 = startColumn + n;
            int lastCol2 = Math.min(startColumn - 1, startColumn + n + movedColumns);
            overwrite = new CellRangeAddress(merged.getFirstRow(), merged.getLastRow(), firstCol2, lastCol2);
        }
        return merged.intersects(overwrite);
    }

    public void shiftColumns(int firstShiftColumnIndex, int lastShiftColumnIndex, int step) {
        if (step > 0) {
            for (Row row : this.sheet) {
                if (row != null) {
                    row.shiftCellsRight(firstShiftColumnIndex, lastShiftColumnIndex, step);
                }
            }
            return;
        }
        if (step < 0) {
            for (Row row2 : this.sheet) {
                if (row2 != null) {
                    row2.shiftCellsLeft(firstShiftColumnIndex, lastShiftColumnIndex, -step);
                }
            }
        }
    }
}
