package org.apache.poi.ss.util.cellwalk;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/* loaded from: classes10.dex */
public class CellWalk {
    private final CellRangeAddress range;
    private final Sheet sheet;
    private boolean traverseEmptyCells = false;

    public CellWalk(Sheet sheet, CellRangeAddress range) {
        this.sheet = sheet;
        this.range = range;
    }

    public boolean isTraverseEmptyCells() {
        return this.traverseEmptyCells;
    }

    public void setTraverseEmptyCells(boolean traverseEmptyCells) {
        this.traverseEmptyCells = traverseEmptyCells;
    }

    public void traverse(CellHandler handler) {
        int firstRow = this.range.getFirstRow();
        int lastRow = this.range.getLastRow();
        int firstColumn = this.range.getFirstColumn();
        int lastColumn = this.range.getLastColumn();
        int width = (lastColumn - firstColumn) + 1;
        SimpleCellWalkContext ctx = new SimpleCellWalkContext();
        ctx.rowNumber = firstRow;
        while (ctx.rowNumber <= lastRow) {
            Row currentRow = this.sheet.getRow(ctx.rowNumber);
            if (currentRow != null) {
                ctx.colNumber = firstColumn;
                while (ctx.colNumber <= lastColumn) {
                    Cell currentCell = currentRow.getCell(ctx.colNumber);
                    if (currentCell != null && (!isEmpty(currentCell) || this.traverseEmptyCells)) {
                        long rowSize = Math.multiplyExact(Math.subtractExact(ctx.rowNumber, firstRow), width);
                        ctx.ordinalNumber = Math.addExact(rowSize, (ctx.colNumber - firstColumn) + 1);
                        handler.onCell(currentCell, ctx);
                    }
                    SimpleCellWalkContext.access$204(ctx);
                }
            }
            SimpleCellWalkContext.access$104(ctx);
        }
    }

    private boolean isEmpty(Cell cell) {
        return cell.getCellType() == CellType.BLANK;
    }

    /* loaded from: classes10.dex */
    private static class SimpleCellWalkContext implements CellWalkContext {
        private int colNumber;
        private long ordinalNumber;
        private int rowNumber;

        private SimpleCellWalkContext() {
        }

        static /* synthetic */ int access$104(SimpleCellWalkContext x0) {
            int i = x0.rowNumber + 1;
            x0.rowNumber = i;
            return i;
        }

        static /* synthetic */ int access$204(SimpleCellWalkContext x0) {
            int i = x0.colNumber + 1;
            x0.colNumber = i;
            return i;
        }

        @Override // org.apache.poi.ss.util.cellwalk.CellWalkContext
        public long getOrdinalNumber() {
            return this.ordinalNumber;
        }

        @Override // org.apache.poi.ss.util.cellwalk.CellWalkContext
        public int getRowNumber() {
            return this.rowNumber;
        }

        @Override // org.apache.poi.ss.util.cellwalk.CellWalkContext
        public int getColumnNumber() {
            return this.colNumber;
        }
    }
}
