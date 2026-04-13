package org.apache.poi.ss.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public class AreaReference {
    private static final char CELL_DELIMITER = ':';
    private static final SpreadsheetVersion DEFAULT_SPREADSHEET_VERSION = SpreadsheetVersion.EXCEL97;
    private static final char SHEET_NAME_DELIMITER = '!';
    private static final char SPECIAL_NAME_DELIMITER = '\'';
    private final CellReference _firstCell;
    private final boolean _isSingleCell;
    private final CellReference _lastCell;
    private final SpreadsheetVersion _version;

    public AreaReference(String reference, SpreadsheetVersion version) {
        this._version = version != null ? version : DEFAULT_SPREADSHEET_VERSION;
        if (!isContiguous(reference)) {
            throw new IllegalArgumentException("References passed to the AreaReference must be contiguous, use generateContiguous(ref) if you have non-contiguous references");
        }
        String[] parts = separateAreaRefs(reference);
        String part0 = parts[0];
        if (parts.length == 1) {
            this._firstCell = new CellReference(part0);
            this._lastCell = this._firstCell;
            this._isSingleCell = true;
            return;
        }
        if (parts.length != 2) {
            throw new IllegalArgumentException("Bad area ref '" + reference + "'");
        }
        String part1 = parts[1];
        if (isPlainColumn(part0)) {
            if (!isPlainColumn(part1)) {
                throw new IllegalStateException("Bad area ref '" + reference + "'");
            }
            boolean firstIsAbs = CellReference.isPartAbsolute(part0);
            boolean lastIsAbs = CellReference.isPartAbsolute(part1);
            int col0 = CellReference.convertColStringToIndex(part0);
            int col1 = CellReference.convertColStringToIndex(part1);
            this._firstCell = new CellReference(0, col0, true, firstIsAbs);
            this._lastCell = new CellReference(65535, col1, true, lastIsAbs);
            this._isSingleCell = false;
            return;
        }
        this._firstCell = new CellReference(part0);
        this._lastCell = new CellReference(part1);
        this._isSingleCell = part0.equals(part1);
    }

    public AreaReference(CellReference topLeft, CellReference botRight, SpreadsheetVersion version) {
        CellReference cellReference;
        CellReference cellReference2;
        int lastRow;
        boolean lastRowAbs;
        int firstRow;
        boolean firstRowAbs;
        boolean lastRowAbs2;
        int lastColumn;
        int lastColumn2;
        String lastSheet;
        String lastSheet2;
        boolean lastColAbs;
        boolean firstColAbs;
        this._version = version != null ? version : DEFAULT_SPREADSHEET_VERSION;
        boolean swapRows = topLeft.getRow() > botRight.getRow();
        boolean swapCols = topLeft.getCol() > botRight.getCol();
        if (swapRows) {
            cellReference = topLeft;
            cellReference2 = botRight;
        } else {
            if (!swapCols) {
                this._firstCell = topLeft;
                this._lastCell = botRight;
                this._isSingleCell = false;
            }
            cellReference = topLeft;
            cellReference2 = botRight;
        }
        if (swapRows) {
            int firstRow2 = cellReference2.getRow();
            boolean firstRowAbs2 = cellReference2.isRowAbsolute();
            lastRow = cellReference.getRow();
            lastRowAbs = cellReference.isRowAbsolute();
            firstRow = firstRow2;
            firstRowAbs = firstRowAbs2;
        } else {
            int firstRow3 = cellReference.getRow();
            boolean firstRowAbs3 = cellReference.isRowAbsolute();
            lastRow = cellReference2.getRow();
            lastRowAbs = cellReference2.isRowAbsolute();
            firstRow = firstRow3;
            firstRowAbs = firstRowAbs3;
        }
        if (swapCols) {
            String firstSheet = cellReference2.getSheetName();
            int firstColumn = cellReference2.getCol();
            boolean firstColAbs2 = cellReference2.isColAbsolute();
            String lastSheet3 = cellReference.getSheetName();
            int lastColumn3 = cellReference.getCol();
            lastRowAbs2 = lastRowAbs;
            lastColumn = lastColumn3;
            lastColumn2 = firstColumn;
            lastSheet = lastSheet3;
            lastSheet2 = firstSheet;
            lastColAbs = cellReference.isColAbsolute();
            firstColAbs = firstColAbs2;
        } else {
            String firstSheet2 = cellReference.getSheetName();
            int firstColumn2 = cellReference.getCol();
            boolean firstColAbs3 = cellReference.isColAbsolute();
            String lastSheet4 = cellReference2.getSheetName();
            int lastColumn4 = cellReference2.getCol();
            lastRowAbs2 = lastRowAbs;
            lastColumn = lastColumn4;
            lastColumn2 = firstColumn2;
            lastSheet = lastSheet4;
            lastSheet2 = firstSheet2;
            lastColAbs = cellReference2.isColAbsolute();
            firstColAbs = firstColAbs3;
        }
        this._firstCell = new CellReference(lastSheet2, firstRow, lastColumn2, firstRowAbs, firstColAbs);
        this._lastCell = new CellReference(lastSheet, lastRow, lastColumn, lastRowAbs2, lastColAbs);
        this._isSingleCell = false;
    }

    private static boolean isPlainColumn(String refPart) {
        for (int i = refPart.length() - 1; i >= 0; i--) {
            int ch = refPart.charAt(i);
            if ((ch != 36 || i != 0) && (ch < 65 || ch > 90)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isContiguous(String reference) {
        return splitAreaReferences(reference).length == 1;
    }

    public static AreaReference getWholeRow(SpreadsheetVersion version, String start, String end) {
        if (version == null) {
            version = DEFAULT_SPREADSHEET_VERSION;
        }
        return new AreaReference("$A" + start + ":$" + version.getLastColumnName() + end, version);
    }

    public static AreaReference getWholeColumn(SpreadsheetVersion version, String start, String end) {
        if (version == null) {
            version = DEFAULT_SPREADSHEET_VERSION;
        }
        return new AreaReference(start + "$1:" + end + "$" + version.getMaxRows(), version);
    }

    public static boolean isWholeColumnReference(SpreadsheetVersion version, CellReference topLeft, CellReference botRight) {
        if (version == null) {
            version = DEFAULT_SPREADSHEET_VERSION;
        }
        return topLeft.getRow() == 0 && topLeft.isRowAbsolute() && botRight.getRow() == version.getLastRowIndex() && botRight.isRowAbsolute();
    }

    public static AreaReference[] generateContiguous(SpreadsheetVersion version, String reference) {
        if (version == null) {
            version = DEFAULT_SPREADSHEET_VERSION;
        }
        List<AreaReference> refs = new ArrayList<>();
        String[] splitReferences = splitAreaReferences(reference);
        for (String ref : splitReferences) {
            refs.add(new AreaReference(ref, version));
        }
        return (AreaReference[]) refs.toArray(new AreaReference[0]);
    }

    private static String[] separateAreaRefs(String reference) {
        int len = reference.length();
        int delimiterPos = -1;
        boolean insideDelimitedName = false;
        int i = 0;
        while (i < len) {
            switch (reference.charAt(i)) {
                case '\'':
                    if (!insideDelimitedName) {
                        insideDelimitedName = true;
                        break;
                    } else {
                        if (i >= len - 1) {
                            throw new IllegalArgumentException("Area reference '" + reference + "' ends with special name delimiter '''");
                        }
                        if (reference.charAt(i + 1) == '\'') {
                            i++;
                            break;
                        } else {
                            insideDelimitedName = false;
                            break;
                        }
                    }
                case ':':
                    if (insideDelimitedName) {
                        continue;
                    } else {
                        if (delimiterPos >= 0) {
                            throw new IllegalArgumentException("More than one cell delimiter ':' appears in area reference '" + reference + "'");
                        }
                        delimiterPos = i;
                        break;
                    }
            }
            i++;
        }
        if (delimiterPos < 0) {
            return new String[]{reference};
        }
        String partA = reference.substring(0, delimiterPos);
        String partB = reference.substring(delimiterPos + 1);
        if (partB.indexOf(33) >= 0) {
            throw new IllegalStateException("Unexpected ! in second cell reference of '" + reference + "'");
        }
        int plingPos = partA.lastIndexOf(33);
        if (plingPos >= 0) {
            String sheetName = partA.substring(0, plingPos + 1);
            return new String[]{partA, sheetName + partB};
        }
        return new String[]{partA, partB};
    }

    private static String[] splitAreaReferences(String reference) {
        List<String> results = new ArrayList<>();
        String currentSegment = "";
        StringTokenizer st = new StringTokenizer(reference, CollectionUtils.COMMA);
        while (st.hasMoreTokens()) {
            if (!currentSegment.isEmpty()) {
                currentSegment = currentSegment + CollectionUtils.COMMA;
            }
            currentSegment = currentSegment + st.nextToken();
            int numSingleQuotes = StringUtil.countMatches(currentSegment, '\'');
            if (numSingleQuotes == 0 || numSingleQuotes == 2) {
                results.add(currentSegment);
                currentSegment = "";
            }
        }
        if (!currentSegment.isEmpty()) {
            results.add(currentSegment);
        }
        return (String[]) results.toArray(new String[0]);
    }

    public boolean isWholeColumnReference() {
        return isWholeColumnReference(this._version, this._firstCell, this._lastCell);
    }

    public boolean isSingleCell() {
        return this._isSingleCell;
    }

    public CellReference getFirstCell() {
        return this._firstCell;
    }

    public CellReference getLastCell() {
        return this._lastCell;
    }

    public CellReference[] getAllReferencedCells() {
        if (this._isSingleCell) {
            return new CellReference[]{this._firstCell};
        }
        int minRow = Math.min(this._firstCell.getRow(), this._lastCell.getRow());
        int maxRow = Math.max(this._firstCell.getRow(), this._lastCell.getRow());
        int minCol = Math.min((int) this._firstCell.getCol(), (int) this._lastCell.getCol());
        int maxCol = Math.max((int) this._firstCell.getCol(), (int) this._lastCell.getCol());
        String sheetName = this._firstCell.getSheetName();
        List<CellReference> refs = new ArrayList<>();
        for (int row = minRow; row <= maxRow; row++) {
            for (int col = minCol; col <= maxCol; col++) {
                CellReference ref = new CellReference(sheetName, row, col, this._firstCell.isRowAbsolute(), this._firstCell.isColAbsolute());
                refs.add(ref);
            }
        }
        return (CellReference[]) refs.toArray(new CellReference[0]);
    }

    public String formatAsString() {
        if (isWholeColumnReference()) {
            return CellReference.convertNumToColString(this._firstCell.getCol()) + ":" + CellReference.convertNumToColString(this._lastCell.getCol());
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append(this._firstCell.formatAsString());
        if (!this._isSingleCell) {
            sb.append(':');
            if (this._lastCell.getSheetName() == null) {
                sb.append(this._lastCell.formatAsString());
            } else {
                this._lastCell.appendCellReference(sb);
            }
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(getClass().getName()).append(" [");
        try {
            sb.append(formatAsString());
        } catch (Exception e) {
            sb.append(e);
        }
        sb.append(']');
        return sb.toString();
    }
}
