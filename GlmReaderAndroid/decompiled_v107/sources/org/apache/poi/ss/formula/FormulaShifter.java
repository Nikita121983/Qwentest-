package org.apache.poi.ss.formula;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.ptg.Area2DPtgBase;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.AreaErrPtg;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.AreaPtgBase;
import org.apache.poi.ss.formula.ptg.Deleted3DPxg;
import org.apache.poi.ss.formula.ptg.DeletedArea3DPtg;
import org.apache.poi.ss.formula.ptg.DeletedRef3DPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.ptg.Ref3DPxg;
import org.apache.poi.ss.formula.ptg.RefErrorPtg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.apache.poi.ss.formula.ptg.RefPtgBase;

/* loaded from: classes10.dex */
public final class FormulaShifter {
    private final int _amountToMove;
    private final int _dstSheetIndex;
    private final int _externSheetIndex;
    private final int _firstMovedIndex;
    private final int _lastMovedIndex;
    private final ShiftMode _mode;
    private final String _sheetName;
    private final int _srcSheetIndex;
    private final SpreadsheetVersion _version;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public enum ShiftMode {
        RowMove,
        RowCopy,
        ColumnMove,
        ColumnCopy,
        SheetMove
    }

    private FormulaShifter(int externSheetIndex, String sheetName, int firstMovedIndex, int lastMovedIndex, int amountToMove, ShiftMode mode, SpreadsheetVersion version) {
        if (firstMovedIndex > lastMovedIndex) {
            throw new IllegalArgumentException("firstMovedIndex, lastMovedIndex out of order");
        }
        this._externSheetIndex = externSheetIndex;
        this._sheetName = sheetName;
        this._firstMovedIndex = firstMovedIndex;
        this._lastMovedIndex = lastMovedIndex;
        this._amountToMove = amountToMove;
        this._mode = mode;
        this._version = version;
        this._dstSheetIndex = -1;
        this._srcSheetIndex = -1;
    }

    private FormulaShifter(int srcSheetIndex, int dstSheetIndex) {
        this._amountToMove = -1;
        this._lastMovedIndex = -1;
        this._firstMovedIndex = -1;
        this._externSheetIndex = -1;
        this._sheetName = null;
        this._version = null;
        this._srcSheetIndex = srcSheetIndex;
        this._dstSheetIndex = dstSheetIndex;
        this._mode = ShiftMode.SheetMove;
    }

    public static FormulaShifter createForRowShift(int externSheetIndex, String sheetName, int firstMovedRowIndex, int lastMovedRowIndex, int numberOfRowsToMove, SpreadsheetVersion version) {
        return new FormulaShifter(externSheetIndex, sheetName, firstMovedRowIndex, lastMovedRowIndex, numberOfRowsToMove, ShiftMode.RowMove, version);
    }

    public static FormulaShifter createForRowCopy(int externSheetIndex, String sheetName, int firstMovedRowIndex, int lastMovedRowIndex, int numberOfRowsToMove, SpreadsheetVersion version) {
        return new FormulaShifter(externSheetIndex, sheetName, firstMovedRowIndex, lastMovedRowIndex, numberOfRowsToMove, ShiftMode.RowCopy, version);
    }

    public static FormulaShifter createForColumnShift(int externSheetIndex, String sheetName, int firstMovedColumnIndex, int lastMovedColumnIndex, int numberOfColumnsToMove, SpreadsheetVersion version) {
        return new FormulaShifter(externSheetIndex, sheetName, firstMovedColumnIndex, lastMovedColumnIndex, numberOfColumnsToMove, ShiftMode.ColumnMove, version);
    }

    public static FormulaShifter createForColumnCopy(int externSheetIndex, String sheetName, int firstMovedColumnIndex, int lastMovedColumnIndex, int numberOfColumnsToMove, SpreadsheetVersion version) {
        return new FormulaShifter(externSheetIndex, sheetName, firstMovedColumnIndex, lastMovedColumnIndex, numberOfColumnsToMove, ShiftMode.ColumnCopy, version);
    }

    public static FormulaShifter createForSheetShift(int srcSheetIndex, int dstSheetIndex) {
        return new FormulaShifter(srcSheetIndex, dstSheetIndex);
    }

    public String toString() {
        return getClass().getName() + " [" + this._firstMovedIndex + this._lastMovedIndex + this._amountToMove + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }

    public boolean adjustFormula(Ptg[] ptgs, int currentExternSheetIx) {
        boolean refsWereChanged = false;
        for (int i = 0; i < ptgs.length; i++) {
            Ptg newPtg = adjustPtg(ptgs[i], currentExternSheetIx);
            if (newPtg != null) {
                refsWereChanged = true;
                ptgs[i] = newPtg;
            }
        }
        return refsWereChanged;
    }

    private Ptg adjustPtg(Ptg ptg, int currentExternSheetIx) {
        switch (this._mode) {
            case RowMove:
                return adjustPtgDueToRowMove(ptg, currentExternSheetIx);
            case RowCopy:
                return adjustPtgDueToRowCopy(ptg);
            case ColumnMove:
                return adjustPtgDueToColumnMove(ptg, currentExternSheetIx);
            case ColumnCopy:
                return adjustPtgDueToColumnCopy(ptg);
            case SheetMove:
                return adjustPtgDueToSheetMove(ptg);
            default:
                throw new IllegalStateException("Unsupported shift mode: " + this._mode);
        }
    }

    private Ptg adjustPtgDueToMove(Ptg ptg, int currentExternSheetIx, boolean isRowMove) {
        if (ptg instanceof RefPtg) {
            if (currentExternSheetIx != this._externSheetIndex) {
                return null;
            }
            RefPtgBase rptg = (RefPtg) ptg;
            return isRowMove ? rowMoveRefPtg(rptg) : columnMoveRefPtg(rptg);
        }
        if (ptg instanceof Ref3DPtg) {
            Ref3DPtg rptg2 = (Ref3DPtg) ptg;
            if (this._externSheetIndex != rptg2.getExternSheetIndex()) {
                return null;
            }
            return isRowMove ? rowMoveRefPtg(rptg2) : columnMoveRefPtg(rptg2);
        }
        if (ptg instanceof Ref3DPxg) {
            Ref3DPxg rpxg = (Ref3DPxg) ptg;
            if (rpxg.getExternalWorkbookNumber() > 0 || !this._sheetName.equalsIgnoreCase(rpxg.getSheetName())) {
                return null;
            }
            return isRowMove ? rowMoveRefPtg(rpxg) : columnMoveRefPtg(rpxg);
        }
        if (ptg instanceof Area2DPtgBase) {
            if (currentExternSheetIx != this._externSheetIndex) {
                return ptg;
            }
            AreaPtgBase aptg = (Area2DPtgBase) ptg;
            return isRowMove ? rowMoveAreaPtg(aptg) : columnMoveAreaPtg(aptg);
        }
        if (ptg instanceof Area3DPtg) {
            Area3DPtg aptg2 = (Area3DPtg) ptg;
            if (this._externSheetIndex != aptg2.getExternSheetIndex()) {
                return null;
            }
            return isRowMove ? rowMoveAreaPtg(aptg2) : columnMoveAreaPtg(aptg2);
        }
        if (!(ptg instanceof Area3DPxg)) {
            return null;
        }
        Area3DPxg apxg = (Area3DPxg) ptg;
        if (apxg.getExternalWorkbookNumber() > 0 || !this._sheetName.equalsIgnoreCase(apxg.getSheetName())) {
            return null;
        }
        return isRowMove ? rowMoveAreaPtg(apxg) : columnMoveAreaPtg(apxg);
    }

    private Ptg adjustPtgDueToRowMove(Ptg ptg, int currentExternSheetIx) {
        return adjustPtgDueToMove(ptg, currentExternSheetIx, true);
    }

    private Ptg adjustPtgDueToColumnMove(Ptg ptg, int currentExternSheetIx) {
        return adjustPtgDueToMove(ptg, currentExternSheetIx, false);
    }

    private Ptg adjustPtgDueToCopy(Ptg ptg, boolean isRowCopy) {
        if (ptg instanceof RefPtg) {
            RefPtg rptg = (RefPtg) ptg;
            return isRowCopy ? rowCopyRefPtg(rptg) : columnCopyRefPtg(rptg);
        }
        if (ptg instanceof Ref3DPtg) {
            Ref3DPtg rptg2 = (Ref3DPtg) ptg;
            return isRowCopy ? rowCopyRefPtg(rptg2) : columnCopyRefPtg(rptg2);
        }
        if (ptg instanceof Ref3DPxg) {
            Ref3DPxg rpxg = (Ref3DPxg) ptg;
            return isRowCopy ? rowCopyRefPtg(rpxg) : columnCopyRefPtg(rpxg);
        }
        if (ptg instanceof Area2DPtgBase) {
            Area2DPtgBase aptg = (Area2DPtgBase) ptg;
            return isRowCopy ? rowCopyAreaPtg(aptg) : columnCopyAreaPtg(aptg);
        }
        if (ptg instanceof Area3DPtg) {
            Area3DPtg aptg2 = (Area3DPtg) ptg;
            return isRowCopy ? rowCopyAreaPtg(aptg2) : columnCopyAreaPtg(aptg2);
        }
        if (ptg instanceof Area3DPxg) {
            Area3DPxg apxg = (Area3DPxg) ptg;
            return isRowCopy ? rowCopyAreaPtg(apxg) : columnCopyAreaPtg(apxg);
        }
        return null;
    }

    private Ptg adjustPtgDueToRowCopy(Ptg ptg) {
        return adjustPtgDueToCopy(ptg, true);
    }

    private Ptg adjustPtgDueToColumnCopy(Ptg ptg) {
        return adjustPtgDueToCopy(ptg, false);
    }

    private Ptg adjustPtgDueToSheetMove(Ptg ptg) {
        Ref3DPtg ref;
        int oldSheetIndex;
        if (!(ptg instanceof Ref3DPtg) || ((oldSheetIndex = (ref = (Ref3DPtg) ptg).getExternSheetIndex()) < this._srcSheetIndex && oldSheetIndex < this._dstSheetIndex)) {
            return null;
        }
        if (oldSheetIndex > this._srcSheetIndex && oldSheetIndex > this._dstSheetIndex) {
            return null;
        }
        if (oldSheetIndex == this._srcSheetIndex) {
            ref.setExternSheetIndex(this._dstSheetIndex);
            return ref;
        }
        if (this._dstSheetIndex < this._srcSheetIndex) {
            ref.setExternSheetIndex(oldSheetIndex + 1);
            return ref;
        }
        if (this._dstSheetIndex > this._srcSheetIndex) {
            ref.setExternSheetIndex(oldSheetIndex - 1);
            return ref;
        }
        return null;
    }

    private Ptg rowMoveRefPtg(RefPtgBase rptg) {
        int refRow = rptg.getRow();
        if (this._firstMovedIndex <= refRow && refRow <= this._lastMovedIndex) {
            rptg.setRow(this._amountToMove + refRow);
            return rptg;
        }
        int destFirstRowIndex = this._firstMovedIndex + this._amountToMove;
        int destLastRowIndex = this._lastMovedIndex + this._amountToMove;
        if (destLastRowIndex < refRow || refRow < destFirstRowIndex) {
            return null;
        }
        if (destFirstRowIndex <= refRow && refRow <= destLastRowIndex) {
            return createDeletedRef(rptg);
        }
        throw new IllegalStateException("Situation not covered: (" + this._firstMovedIndex + ", " + this._lastMovedIndex + ", " + this._amountToMove + ", " + refRow + ", " + refRow + ")");
    }

    private Ptg rowMoveAreaPtg(AreaPtgBase aptg) {
        int aFirstRow = aptg.getFirstRow();
        int aLastRow = aptg.getLastRow();
        if (this._firstMovedIndex <= aFirstRow && aLastRow <= this._lastMovedIndex) {
            aptg.setFirstRow(this._amountToMove + aFirstRow);
            aptg.setLastRow(this._amountToMove + aLastRow);
            return aptg;
        }
        int destFirstRowIndex = this._firstMovedIndex + this._amountToMove;
        int destLastRowIndex = this._lastMovedIndex + this._amountToMove;
        if (aFirstRow < this._firstMovedIndex && this._lastMovedIndex < aLastRow) {
            if (destFirstRowIndex < aFirstRow && aFirstRow <= destLastRowIndex) {
                aptg.setFirstRow(destLastRowIndex + 1);
                return aptg;
            }
            if (destFirstRowIndex > aLastRow || aLastRow >= destLastRowIndex) {
                return null;
            }
            aptg.setLastRow(destFirstRowIndex - 1);
            return aptg;
        }
        if (this._firstMovedIndex <= aFirstRow && aFirstRow <= this._lastMovedIndex) {
            if (this._amountToMove < 0) {
                aptg.setFirstRow(this._amountToMove + aFirstRow);
                return aptg;
            }
            if (destFirstRowIndex > aLastRow) {
                return null;
            }
            int newFirstRowIx = this._amountToMove + aFirstRow;
            if (destLastRowIndex < aLastRow) {
                aptg.setFirstRow(newFirstRowIx);
                return aptg;
            }
            int areaRemainingTopRowIx = this._lastMovedIndex + 1;
            if (destFirstRowIndex > areaRemainingTopRowIx) {
                newFirstRowIx = areaRemainingTopRowIx;
            }
            aptg.setFirstRow(newFirstRowIx);
            aptg.setLastRow(Math.max(aLastRow, destLastRowIndex));
            return aptg;
        }
        if (this._firstMovedIndex <= aLastRow && aLastRow <= this._lastMovedIndex) {
            if (this._amountToMove > 0) {
                aptg.setLastRow(this._amountToMove + aLastRow);
                return aptg;
            }
            if (destLastRowIndex < aFirstRow) {
                return null;
            }
            int newLastRowIx = this._amountToMove + aLastRow;
            if (destFirstRowIndex > aFirstRow) {
                aptg.setLastRow(newLastRowIx);
                return aptg;
            }
            int areaRemainingBottomRowIx = this._firstMovedIndex - 1;
            if (destLastRowIndex < areaRemainingBottomRowIx) {
                newLastRowIx = areaRemainingBottomRowIx;
            }
            aptg.setFirstRow(Math.min(aFirstRow, destFirstRowIndex));
            aptg.setLastRow(newLastRowIx);
            return aptg;
        }
        if (destLastRowIndex < aFirstRow || aLastRow < destFirstRowIndex) {
            return null;
        }
        if (destFirstRowIndex <= aFirstRow && aLastRow <= destLastRowIndex) {
            return createDeletedRef(aptg);
        }
        if (aFirstRow <= destFirstRowIndex && destLastRowIndex <= aLastRow) {
            return null;
        }
        if (destFirstRowIndex < aFirstRow && aFirstRow <= destLastRowIndex) {
            aptg.setFirstRow(destLastRowIndex + 1);
            return aptg;
        }
        if (destFirstRowIndex <= aLastRow && aLastRow < destLastRowIndex) {
            aptg.setLastRow(destFirstRowIndex - 1);
            return aptg;
        }
        throw new IllegalStateException("Situation not covered: (" + this._firstMovedIndex + ", " + this._lastMovedIndex + ", " + this._amountToMove + ", " + aFirstRow + ", " + aLastRow + ")");
    }

    private Ptg rowCopyRefPtg(RefPtgBase rptg) {
        int refRow = rptg.getRow();
        if (rptg.isRowRelative()) {
            int destRowIndex = this._firstMovedIndex + this._amountToMove;
            if (destRowIndex < 0 || this._version.getLastRowIndex() < destRowIndex) {
                return createDeletedRef(rptg);
            }
            int newRowIndex = this._amountToMove + refRow;
            if (newRowIndex < 0 || this._version.getLastRowIndex() < newRowIndex) {
                return createDeletedRef(rptg);
            }
            rptg.setRow(newRowIndex);
            return rptg;
        }
        return null;
    }

    private Ptg rowCopyAreaPtg(AreaPtgBase aptg) {
        boolean changed = false;
        int aFirstRow = aptg.getFirstRow();
        int aLastRow = aptg.getLastRow();
        if (aptg.isFirstRowRelative()) {
            int destFirstRowIndex = this._amountToMove + aFirstRow;
            if (destFirstRowIndex < 0 || this._version.getLastRowIndex() < destFirstRowIndex) {
                return createDeletedRef(aptg);
            }
            aptg.setFirstRow(destFirstRowIndex);
            changed = true;
        }
        if (aptg.isLastRowRelative()) {
            int destLastRowIndex = this._amountToMove + aLastRow;
            if (destLastRowIndex < 0 || this._version.getLastRowIndex() < destLastRowIndex) {
                return createDeletedRef(aptg);
            }
            aptg.setLastRow(destLastRowIndex);
            changed = true;
        }
        if (changed) {
            aptg.sortTopLeftToBottomRight();
        }
        if (changed) {
            return aptg;
        }
        return null;
    }

    private Ptg columnMoveRefPtg(RefPtgBase rptg) {
        int refColumn = rptg.getColumn();
        if (this._firstMovedIndex <= refColumn && refColumn <= this._lastMovedIndex) {
            rptg.setColumn(this._amountToMove + refColumn);
            return rptg;
        }
        int destFirstColumnIndex = this._firstMovedIndex + this._amountToMove;
        int destLastColumnIndex = this._lastMovedIndex + this._amountToMove;
        if (destLastColumnIndex < refColumn || refColumn < destFirstColumnIndex) {
            return null;
        }
        if (destFirstColumnIndex <= refColumn && refColumn <= destLastColumnIndex) {
            return createDeletedRef(rptg);
        }
        throw new IllegalStateException("Situation not covered: (" + this._firstMovedIndex + ", " + this._lastMovedIndex + ", " + this._amountToMove + ", " + refColumn + ", " + refColumn + ")");
    }

    private Ptg columnMoveAreaPtg(AreaPtgBase aptg) {
        int aFirstColumn = aptg.getFirstColumn();
        int aLastColumn = aptg.getLastColumn();
        if (this._firstMovedIndex <= aFirstColumn && aLastColumn <= this._lastMovedIndex) {
            aptg.setFirstColumn(this._amountToMove + aFirstColumn);
            aptg.setLastColumn(this._amountToMove + aLastColumn);
            return aptg;
        }
        int destFirstColumnIndex = this._firstMovedIndex + this._amountToMove;
        int destLastColumnIndex = this._lastMovedIndex + this._amountToMove;
        if (aFirstColumn < this._firstMovedIndex && this._lastMovedIndex < aLastColumn) {
            if (destFirstColumnIndex < aFirstColumn && aFirstColumn <= destLastColumnIndex) {
                aptg.setFirstColumn(destLastColumnIndex + 1);
                return aptg;
            }
            if (destFirstColumnIndex > aLastColumn || aLastColumn >= destLastColumnIndex) {
                return null;
            }
            aptg.setLastColumn(destFirstColumnIndex - 1);
            return aptg;
        }
        if (this._firstMovedIndex <= aFirstColumn && aFirstColumn <= this._lastMovedIndex) {
            if (this._amountToMove < 0) {
                aptg.setFirstColumn(this._amountToMove + aFirstColumn);
                return aptg;
            }
            if (destFirstColumnIndex > aLastColumn) {
                return null;
            }
            int newFirstColumnIx = this._amountToMove + aFirstColumn;
            if (destLastColumnIndex < aLastColumn) {
                aptg.setFirstColumn(newFirstColumnIx);
                return aptg;
            }
            int areaRemainingTopColumnIx = this._lastMovedIndex + 1;
            if (destFirstColumnIndex > areaRemainingTopColumnIx) {
                newFirstColumnIx = areaRemainingTopColumnIx;
            }
            aptg.setFirstColumn(newFirstColumnIx);
            aptg.setLastColumn(Math.max(aLastColumn, destLastColumnIndex));
            return aptg;
        }
        if (this._firstMovedIndex <= aLastColumn && aLastColumn <= this._lastMovedIndex) {
            if (this._amountToMove > 0) {
                aptg.setLastColumn(this._amountToMove + aLastColumn);
                return aptg;
            }
            if (destLastColumnIndex < aFirstColumn) {
                return null;
            }
            int newLastColumnIx = this._amountToMove + aLastColumn;
            if (destFirstColumnIndex > aFirstColumn) {
                aptg.setLastColumn(newLastColumnIx);
                return aptg;
            }
            int areaRemainingBottomColumnIx = this._firstMovedIndex - 1;
            if (destLastColumnIndex < areaRemainingBottomColumnIx) {
                newLastColumnIx = areaRemainingBottomColumnIx;
            }
            aptg.setFirstColumn(Math.min(aFirstColumn, destFirstColumnIndex));
            aptg.setLastColumn(newLastColumnIx);
            return aptg;
        }
        if (destLastColumnIndex < aFirstColumn || aLastColumn < destFirstColumnIndex) {
            return null;
        }
        if (destFirstColumnIndex <= aFirstColumn && aLastColumn <= destLastColumnIndex) {
            return createDeletedRef(aptg);
        }
        if (aFirstColumn <= destFirstColumnIndex && destLastColumnIndex <= aLastColumn) {
            return null;
        }
        if (destFirstColumnIndex < aFirstColumn && aFirstColumn <= destLastColumnIndex) {
            aptg.setFirstColumn(destLastColumnIndex + 1);
            return aptg;
        }
        if (destFirstColumnIndex <= aLastColumn && aLastColumn < destLastColumnIndex) {
            aptg.setLastColumn(destFirstColumnIndex - 1);
            return aptg;
        }
        throw new IllegalStateException("Situation not covered: (" + this._firstMovedIndex + ", " + this._lastMovedIndex + ", " + this._amountToMove + ", " + aFirstColumn + ", " + aLastColumn + ")");
    }

    private Ptg columnCopyRefPtg(RefPtgBase rptg) {
        int refColumn = rptg.getColumn();
        if (rptg.isColRelative()) {
            int destColumnIndex = this._firstMovedIndex + this._amountToMove;
            if (destColumnIndex < 0 || this._version.getLastColumnIndex() < destColumnIndex) {
                return createDeletedRef(rptg);
            }
            int newColumnIndex = this._amountToMove + refColumn;
            if (newColumnIndex < 0 || this._version.getLastColumnIndex() < newColumnIndex) {
                return createDeletedRef(rptg);
            }
            rptg.setColumn(newColumnIndex);
            return rptg;
        }
        return null;
    }

    private Ptg columnCopyAreaPtg(AreaPtgBase aptg) {
        boolean changed = false;
        int aFirstColumn = aptg.getFirstColumn();
        int aLastColumn = aptg.getLastColumn();
        if (aptg.isFirstColRelative()) {
            int destFirstColumnIndex = this._amountToMove + aFirstColumn;
            if (destFirstColumnIndex < 0 || this._version.getLastColumnIndex() < destFirstColumnIndex) {
                return createDeletedRef(aptg);
            }
            aptg.setFirstColumn(destFirstColumnIndex);
            changed = true;
        }
        if (aptg.isLastColRelative()) {
            int destLastColumnIndex = this._amountToMove + aLastColumn;
            if (destLastColumnIndex < 0 || this._version.getLastColumnIndex() < destLastColumnIndex) {
                return createDeletedRef(aptg);
            }
            aptg.setLastColumn(destLastColumnIndex);
            changed = true;
        }
        if (changed) {
            aptg.sortTopLeftToBottomRight();
        }
        if (changed) {
            return aptg;
        }
        return null;
    }

    private static Ptg createDeletedRef(Ptg ptg) {
        if (ptg instanceof RefPtg) {
            return new RefErrorPtg();
        }
        if (ptg instanceof Ref3DPtg) {
            Ref3DPtg rptg = (Ref3DPtg) ptg;
            return new DeletedRef3DPtg(rptg.getExternSheetIndex());
        }
        if (ptg instanceof AreaPtg) {
            return new AreaErrPtg();
        }
        if (ptg instanceof Area3DPtg) {
            Area3DPtg area3DPtg = (Area3DPtg) ptg;
            return new DeletedArea3DPtg(area3DPtg.getExternSheetIndex());
        }
        if (ptg instanceof Ref3DPxg) {
            Ref3DPxg pxg = (Ref3DPxg) ptg;
            return new Deleted3DPxg(pxg.getExternalWorkbookNumber(), pxg.getSheetName());
        }
        if (ptg instanceof Area3DPxg) {
            Area3DPxg pxg2 = (Area3DPxg) ptg;
            return new Deleted3DPxg(pxg2.getExternalWorkbookNumber(), pxg2.getSheetName());
        }
        throw new IllegalArgumentException("Unexpected ref ptg class (" + ptg.getClass().getName() + ")");
    }
}
