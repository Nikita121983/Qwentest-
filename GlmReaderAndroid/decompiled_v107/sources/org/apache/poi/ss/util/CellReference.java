package org.apache.poi.ss.util;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public class CellReference implements GenericRecord {
    private static final char ABSOLUTE_REFERENCE_MARKER = '$';
    private static final char SHEET_NAME_DELIMITER = '!';
    private static final char SPECIAL_NAME_DELIMITER = '\'';
    private final int _colIndex;
    private final boolean _isColAbs;
    private final boolean _isRowAbs;
    private final int _rowIndex;
    private final String _sheetName;
    private static final Pattern CELL_REF_PATTERN = Pattern.compile("(\\$?[A-Z]+)?(\\$?[0-9]+)?", 2);
    private static final Pattern STRICTLY_CELL_REF_PATTERN = Pattern.compile("\\$?([A-Z]+)\\$?([0-9]+)", 2);
    private static final Pattern COLUMN_REF_PATTERN = Pattern.compile("\\$?([A-Z]+)", 2);
    private static final Pattern ROW_REF_PATTERN = Pattern.compile("\\$?([0-9]+)");
    private static final Pattern NAMED_RANGE_NAME_PATTERN = Pattern.compile("[_A-Z][_.A-Z0-9]*", 2);

    /* loaded from: classes10.dex */
    public enum NameType {
        CELL,
        NAMED_RANGE,
        COLUMN,
        ROW,
        BAD_CELL_OR_NAMED_RANGE
    }

    public CellReference(String cellRef) {
        if (StringUtil.endsWithIgnoreCase(cellRef, "#REF!")) {
            throw new IllegalArgumentException("Cell reference invalid: " + cellRef);
        }
        CellRefParts parts = separateRefParts(cellRef);
        this._sheetName = parts.sheetName;
        String colRef = parts.colRef;
        boolean z = false;
        this._isColAbs = !colRef.isEmpty() && colRef.charAt(0) == '$';
        colRef = this._isColAbs ? colRef.substring(1) : colRef;
        if (colRef.isEmpty()) {
            this._colIndex = -1;
        } else {
            this._colIndex = convertColStringToIndex(colRef);
        }
        String rowRef = parts.rowRef;
        if (!rowRef.isEmpty() && rowRef.charAt(0) == '$') {
            z = true;
        }
        this._isRowAbs = z;
        rowRef = this._isRowAbs ? rowRef.substring(1) : rowRef;
        if (rowRef.isEmpty()) {
            this._rowIndex = -1;
        } else {
            this._rowIndex = Integer.parseInt(rowRef) - 1;
        }
    }

    public CellReference(int pRow, int pCol) {
        this(pRow, pCol, false, false);
    }

    public CellReference(int pRow, short pCol) {
        this(pRow, 65535 & pCol, false, false);
    }

    public CellReference(Cell cell) {
        this(cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex(), false, false);
    }

    public CellReference(int pRow, int pCol, boolean pAbsRow, boolean pAbsCol) {
        this(null, pRow, pCol, pAbsRow, pAbsCol);
    }

    public CellReference(String pSheetName, int pRow, int pCol, boolean pAbsRow, boolean pAbsCol) {
        if (pRow < -1) {
            throw new IllegalArgumentException("row index may not be negative, but had " + pRow);
        }
        if (pCol < -1) {
            throw new IllegalArgumentException("column index may not be negative, but had " + pCol);
        }
        this._sheetName = pSheetName;
        this._rowIndex = pRow;
        this._colIndex = pCol;
        this._isRowAbs = pAbsRow;
        this._isColAbs = pAbsCol;
    }

    public int getRow() {
        return this._rowIndex;
    }

    public short getCol() {
        return (short) this._colIndex;
    }

    public boolean isRowAbsolute() {
        return this._isRowAbs;
    }

    public boolean isColAbsolute() {
        return this._isColAbs;
    }

    public String getSheetName() {
        return this._sheetName;
    }

    public static boolean isPartAbsolute(String part) {
        return part.charAt(0) == '$';
    }

    public static int convertColStringToIndex(String ref) {
        int retval = 0;
        char[] refs = ref.toUpperCase(Locale.ROOT).toCharArray();
        for (int k = 0; k < refs.length; k++) {
            char thechar = refs[k];
            if (thechar == '$') {
                if (k != 0) {
                    throw new IllegalArgumentException("Bad col ref format '" + ref + "'");
                }
            } else {
                retval = (retval * 26) + (thechar - 'A') + 1;
            }
        }
        int k2 = retval - 1;
        return k2;
    }

    public static NameType classifyCellReference(String str, SpreadsheetVersion ssVersion) {
        int len = str.length();
        if (len < 1) {
            throw new IllegalArgumentException("Empty string not allowed");
        }
        char firstChar = str.charAt(0);
        switch (firstChar) {
            case '$':
            case '.':
            case '_':
                break;
            default:
                if (!Character.isLetter(firstChar) && !Character.isDigit(firstChar)) {
                    throw new IllegalArgumentException("Invalid first char (" + firstChar + ") of cell reference or named range.  Letter expected");
                }
                break;
        }
        if (!Character.isDigit(str.charAt(len - 1))) {
            return validateNamedRangeName(str, ssVersion);
        }
        Matcher cellRefPatternMatcher = STRICTLY_CELL_REF_PATTERN.matcher(str);
        if (!cellRefPatternMatcher.matches()) {
            return validateNamedRangeName(str, ssVersion);
        }
        String lettersGroup = cellRefPatternMatcher.group(1);
        String digitsGroup = cellRefPatternMatcher.group(2);
        if (cellReferenceIsWithinRange(lettersGroup, digitsGroup, ssVersion)) {
            return NameType.CELL;
        }
        if (str.indexOf(36) >= 0) {
            return NameType.BAD_CELL_OR_NAMED_RANGE;
        }
        return NameType.NAMED_RANGE;
    }

    private static NameType validateNamedRangeName(String str, SpreadsheetVersion ssVersion) {
        Matcher colMatcher = COLUMN_REF_PATTERN.matcher(str);
        if (colMatcher.matches()) {
            String colStr = colMatcher.group(1);
            if (isColumnWithinRange(colStr, ssVersion)) {
                return NameType.COLUMN;
            }
        }
        Matcher rowMatcher = ROW_REF_PATTERN.matcher(str);
        if (rowMatcher.matches()) {
            String rowStr = rowMatcher.group(1);
            if (isRowWithinRange(rowStr, ssVersion)) {
                return NameType.ROW;
            }
        }
        if (!NAMED_RANGE_NAME_PATTERN.matcher(str).matches()) {
            return NameType.BAD_CELL_OR_NAMED_RANGE;
        }
        return NameType.NAMED_RANGE;
    }

    public static boolean cellReferenceIsWithinRange(String colStr, String rowStr, SpreadsheetVersion ssVersion) {
        if (!isColumnWithinRange(colStr, ssVersion)) {
            return false;
        }
        return isRowWithinRange(rowStr, ssVersion);
    }

    public static boolean isColumnWithinRange(String colStr, SpreadsheetVersion ssVersion) {
        String lastCol = ssVersion.getLastColumnName();
        int lastColLength = lastCol.length();
        int numberOfLetters = colStr.length();
        if (numberOfLetters > lastColLength) {
            return false;
        }
        if (numberOfLetters == lastColLength && colStr.toUpperCase(Locale.ROOT).compareTo(lastCol) > 0) {
            return false;
        }
        return true;
    }

    public static boolean isRowWithinRange(String rowStr, SpreadsheetVersion ssVersion) {
        long rowNum = Long.parseLong(rowStr) - 1;
        if (rowNum > 2147483647L) {
            return false;
        }
        return isRowWithinRange(Math.toIntExact(rowNum), ssVersion);
    }

    public static boolean isRowWithinRange(int rowNum, SpreadsheetVersion ssVersion) {
        return rowNum >= 0 && rowNum <= ssVersion.getLastRowIndex();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class CellRefParts {
        final String colRef;
        final String rowRef;
        final String sheetName;

        private CellRefParts(String sheetName, String rowRef, String colRef) {
            this.sheetName = sheetName;
            this.rowRef = rowRef != null ? rowRef : "";
            this.colRef = colRef != null ? colRef : "";
        }
    }

    private static CellRefParts separateRefParts(String reference) {
        int plingPos = reference.lastIndexOf(33);
        String sheetName = parseSheetName(reference, plingPos);
        String cell = reference.substring(plingPos + 1).toUpperCase(Locale.ROOT);
        Matcher matcher = CELL_REF_PATTERN.matcher(cell);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid CellReference: " + reference);
        }
        String col = matcher.group(1);
        String row = matcher.group(2);
        return new CellRefParts(sheetName, row, col);
    }

    private static String parseSheetName(String reference, int indexOfSheetNameDelimiter) {
        if (indexOfSheetNameDelimiter < 0) {
            return null;
        }
        boolean isQuoted = reference.charAt(0) == '\'';
        if (!isQuoted) {
            if (!reference.contains(StringUtils.SPACE)) {
                return reference.substring(0, indexOfSheetNameDelimiter);
            }
            throw new IllegalArgumentException("Sheet names containing spaces must be quoted: (" + reference + ")");
        }
        int lastQuotePos = indexOfSheetNameDelimiter - 1;
        if (reference.charAt(lastQuotePos) != '\'') {
            throw new IllegalArgumentException("Mismatched quotes: (" + reference + ")");
        }
        StringBuilder sb = new StringBuilder(indexOfSheetNameDelimiter);
        int i = 1;
        while (i < lastQuotePos) {
            char ch = reference.charAt(i);
            if (ch != '\'') {
                sb.append(ch);
            } else if (i + 1 < lastQuotePos && reference.charAt(i + 1) == '\'') {
                i++;
                sb.append(ch);
            } else {
                throw new IllegalArgumentException("Bad sheet name quote escaping: (" + reference + ")");
            }
            i++;
        }
        return sb.toString();
    }

    public static String convertNumToColString(int col) {
        int excelColNum = col + 1;
        StringBuilder colRef = new StringBuilder(2);
        int colRemain = excelColNum;
        while (colRemain > 0) {
            int thisPart = colRemain % 26;
            if (thisPart == 0) {
                thisPart = 26;
            }
            colRemain = (colRemain - thisPart) / 26;
            char colChar = (char) (thisPart + 64);
            colRef.insert(0, colChar);
        }
        return colRef.toString();
    }

    public String formatAsString() {
        return formatAsString(true);
    }

    public String formatAsR1C1String() {
        return formatAsR1C1String(true);
    }

    public String formatAsString(boolean includeSheetName) {
        StringBuilder sb = new StringBuilder(32);
        if (includeSheetName && this._sheetName != null) {
            SheetNameFormatter.appendFormat(sb, this._sheetName);
            sb.append(SHEET_NAME_DELIMITER);
        }
        appendCellReference(sb);
        return sb.toString();
    }

    public String formatAsR1C1String(boolean includeSheetName) {
        StringBuilder sb = new StringBuilder(32);
        if (includeSheetName && this._sheetName != null) {
            SheetNameFormatter.appendFormat(sb, this._sheetName);
            sb.append(SHEET_NAME_DELIMITER);
        }
        appendR1C1CellReference(sb);
        return sb.toString();
    }

    public String toString() {
        return getClass().getName() + " [" + formatAsString() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }

    public String[] getCellRefParts() {
        return new String[]{this._sheetName, Integer.toString(this._rowIndex + 1), convertNumToColString(this._colIndex)};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void appendCellReference(StringBuilder sb) {
        if (this._colIndex != -1) {
            if (this._isColAbs) {
                sb.append('$');
            }
            sb.append(convertNumToColString(this._colIndex));
        }
        if (this._rowIndex != -1) {
            if (this._isRowAbs) {
                sb.append('$');
            }
            sb.append(this._rowIndex + 1);
        }
    }

    void appendR1C1CellReference(StringBuilder sb) {
        if (this._rowIndex != -1) {
            sb.append('R').append(this._rowIndex + 1);
        }
        if (this._colIndex != -1) {
            sb.append('C').append(this._colIndex + 1);
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CellReference)) {
            return false;
        }
        CellReference cr = (CellReference) o;
        return this._rowIndex == cr._rowIndex && this._colIndex == cr._colIndex && this._isRowAbs == cr._isRowAbs && this._isColAbs == cr._isColAbs && Objects.equals(this._sheetName, cr._sheetName);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this._rowIndex), Integer.valueOf(this._colIndex), Boolean.valueOf(this._isRowAbs), Boolean.valueOf(this._isColAbs), this._sheetName);
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("sheetName", new Supplier() { // from class: org.apache.poi.ss.util.CellReference$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return CellReference.this.getSheetName();
            }
        }, "rowIndex", new Supplier() { // from class: org.apache.poi.ss.util.CellReference$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Integer.valueOf(CellReference.this.getRow());
            }
        }, "colIndex", new Supplier() { // from class: org.apache.poi.ss.util.CellReference$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return Short.valueOf(CellReference.this.getCol());
            }
        }, "rowAbs", new Supplier() { // from class: org.apache.poi.ss.util.CellReference$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CellReference.this.isRowAbsolute());
            }
        }, "colAbs", new Supplier() { // from class: org.apache.poi.ss.util.CellReference$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(CellReference.this.isColAbsolute());
            }
        }, "formatAsString", new Supplier() { // from class: org.apache.poi.ss.util.CellReference$$ExternalSyntheticLambda5
            @Override // java.util.function.Supplier
            public final Object get() {
                return CellReference.this.formatAsString();
            }
        });
    }
}
