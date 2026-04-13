package org.apache.poi.xssf.binary;

import java.io.InputStream;
import java.util.Queue;
import kotlin.UByte;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.usermodel.XSSFComment;

@Internal
/* loaded from: classes10.dex */
public class XSSFBSheetHandler extends XSSFBParser {
    private static final int CHECK_ALL_ROWS = -1;
    private final XSSFBCellHeader cellBuffer;
    private final XSSFBCommentsTable comments;
    private int currentRow;
    private final boolean formulasNotResults;
    private final XSSFBSheetContentsHandler handler;
    private XSSFBCellRange hyperlinkCellRange;
    private int lastEndedRow;
    private int lastStartedRow;
    private byte[] rkBuffer;
    private final SharedStrings stringsTable;
    private final XSSFBStylesTable styles;
    private StringBuilder xlWideStringBuffer;

    /* loaded from: classes10.dex */
    public interface SheetContentsHandler extends XSSFSheetXMLHandler.SheetContentsHandler {
        void hyperlinkCell(String str, String str2, String str3, String str4, XSSFComment xSSFComment);
    }

    /* loaded from: classes10.dex */
    public interface XSSFBSheetContentsHandler {
        void booleanCell(String str, boolean z, XSSFComment xSSFComment);

        void doubleCell(String str, double d, XSSFComment xSSFComment, ExcelNumberFormat excelNumberFormat);

        void endRow(int i);

        void endSheet();

        void errorCell(String str, FormulaError formulaError, XSSFComment xSSFComment);

        void headerFooter(String str, boolean z, String str2);

        void startRow(int i);

        void stringCell(String str, String str2, XSSFComment xSSFComment);
    }

    public XSSFBSheetHandler(InputStream is, XSSFBStylesTable styles, XSSFBCommentsTable comments, SharedStrings strings, XSSFBSheetContentsHandler sheetContentsHandler, boolean formulasNotResults) {
        super(is);
        this.lastEndedRow = -1;
        this.lastStartedRow = -1;
        this.rkBuffer = new byte[8];
        this.xlWideStringBuffer = new StringBuilder();
        this.cellBuffer = new XSSFBCellHeader();
        this.styles = styles;
        this.comments = comments;
        this.stringsTable = strings;
        this.handler = sheetContentsHandler;
        this.formulasNotResults = formulasNotResults;
    }

    public XSSFBSheetHandler(InputStream is, XSSFBStylesTable styles, XSSFBCommentsTable comments, SharedStrings strings, XSSFSheetXMLHandler.SheetContentsHandler sheetContentsHandler, DataFormatter dataFormatter, boolean formulasNotResults) {
        super(is);
        this.lastEndedRow = -1;
        this.lastStartedRow = -1;
        this.rkBuffer = new byte[8];
        this.xlWideStringBuffer = new StringBuilder();
        this.cellBuffer = new XSSFBCellHeader();
        this.styles = styles;
        this.comments = comments;
        this.stringsTable = strings;
        this.handler = new XSSFBSheetContentsHandlerWrapper(sheetContentsHandler, dataFormatter);
        this.formulasNotResults = formulasNotResults;
    }

    @Override // org.apache.poi.xssf.binary.XSSFBParser
    public void handleRecord(int id, byte[] data) throws XSSFBParseException {
        XSSFBRecordType type = XSSFBRecordType.lookup(id);
        switch (type) {
            case BrtRowHdr:
                int rw = XSSFBUtils.castToInt(LittleEndian.getUInt(data, 0));
                if (rw > 1048576) {
                    throw new XSSFBParseException("Row number beyond allowable range: " + rw);
                }
                this.currentRow = rw;
                checkMissedComments(this.currentRow);
                startRow(this.currentRow);
                return;
            case BrtCellIsst:
                handleBrtCellIsst(data);
                return;
            case BrtCellSt:
                handleCellSt(data);
                return;
            case BrtCellRk:
                handleCellRk(data);
                return;
            case BrtCellReal:
                handleCellReal(data);
                return;
            case BrtCellBool:
            case BrtFmlaBool:
                handleBoolean(data);
                return;
            case BrtCellError:
                handleCellError(data);
                return;
            case BrtCellBlank:
                beforeCellValue(data);
                return;
            case BrtFmlaString:
                handleFmlaString(data);
                return;
            case BrtFmlaNum:
                handleFmlaNum(data);
                return;
            case BrtFmlaError:
                handleFmlaError(data);
                return;
            case BrtEndSheetData:
                checkMissedComments(-1);
                endRow(this.lastStartedRow);
                return;
            case BrtBeginHeaderFooter:
                handleHeaderFooter(data);
                return;
            default:
                return;
        }
    }

    private void beforeCellValue(byte[] data) {
        XSSFBCellHeader.parse(data, 0, this.currentRow, this.cellBuffer);
        checkMissedComments(this.currentRow, this.cellBuffer.getColNum());
    }

    private void handleStringCellValue(String val) {
        CellAddress cellAddress = getCellAddress();
        XSSFBComment comment = getCellComment(cellAddress);
        this.handler.stringCell(cellAddress.formatAsString(), val, comment);
    }

    private void handleDoubleCellValue(double val) {
        CellAddress cellAddress = getCellAddress();
        XSSFBComment comment = getCellComment(cellAddress);
        ExcelNumberFormat nf = getExcelNumberFormat();
        this.handler.doubleCell(cellAddress.formatAsString(), val, comment, nf);
    }

    private void handleErrorCellValue(int val) {
        FormulaError fe;
        try {
            fe = FormulaError.forInt(val);
        } catch (IllegalArgumentException e) {
            fe = null;
        }
        CellAddress cellAddress = getCellAddress();
        XSSFBComment comment = getCellComment(cellAddress);
        this.handler.errorCell(cellAddress.formatAsString(), fe, comment);
    }

    private CellAddress getCellAddress() {
        return new CellAddress(this.currentRow, this.cellBuffer.getColNum());
    }

    private XSSFBComment getCellComment(CellAddress cellAddress) {
        if (this.comments == null) {
            return null;
        }
        XSSFBComment comment = this.comments.get(cellAddress);
        return comment;
    }

    private ExcelNumberFormat getExcelNumberFormat() {
        int styleIdx = this.cellBuffer.getStyleIdx();
        String formatString = this.styles.getNumberFormatString(styleIdx);
        short styleIndex = this.styles.getNumberFormatIndex(styleIdx);
        if (formatString == null) {
            formatString = BuiltinFormats.getBuiltinFormat(0);
            styleIndex = 0;
        }
        return new ExcelNumberFormat(styleIndex, formatString);
    }

    private void handleFmlaNum(byte[] data) {
        beforeCellValue(data);
        double val = LittleEndian.getDouble(data, 8);
        handleDoubleCellValue(val);
    }

    private void handleCellSt(byte[] data) {
        beforeCellValue(data);
        this.xlWideStringBuffer.setLength(0);
        XSSFBUtils.readXLWideString(data, 8, this.xlWideStringBuffer);
        handleStringCellValue(this.xlWideStringBuffer.toString());
    }

    private void handleFmlaString(byte[] data) {
        beforeCellValue(data);
        this.xlWideStringBuffer.setLength(0);
        XSSFBUtils.readXLWideString(data, 8, this.xlWideStringBuffer);
        handleStringCellValue(this.xlWideStringBuffer.toString());
    }

    private void handleCellError(byte[] data) {
        beforeCellValue(data);
        int val = data[8] & UByte.MAX_VALUE;
        handleErrorCellValue(val);
    }

    private void handleFmlaError(byte[] data) {
        beforeCellValue(data);
        int val = data[8] & UByte.MAX_VALUE;
        handleErrorCellValue(val);
    }

    private void handleBoolean(byte[] data) {
        beforeCellValue(data);
        boolean val = data[8] == 1;
        CellAddress cellAddress = getCellAddress();
        XSSFBComment comment = getCellComment(cellAddress);
        this.handler.booleanCell(cellAddress.formatAsString(), val, comment);
    }

    private void handleCellReal(byte[] data) {
        beforeCellValue(data);
        double val = LittleEndian.getDouble(data, 8);
        handleDoubleCellValue(val);
    }

    private void handleCellRk(byte[] data) {
        beforeCellValue(data);
        double val = rkNumber(data, 8);
        handleDoubleCellValue(val);
    }

    private void handleBrtCellIsst(byte[] data) {
        beforeCellValue(data);
        int idx = XSSFBUtils.castToInt(LittleEndian.getUInt(data, 8));
        RichTextString rtss = this.stringsTable.getItemAt(idx);
        handleStringCellValue(rtss.getString());
    }

    private void handleHeaderFooter(byte[] data) {
        XSSFBHeaderFooters headerFooter = XSSFBHeaderFooters.parse(data);
        outputHeaderFooter(headerFooter.getHeader());
        outputHeaderFooter(headerFooter.getFooter());
        outputHeaderFooter(headerFooter.getHeaderEven());
        outputHeaderFooter(headerFooter.getFooterEven());
        outputHeaderFooter(headerFooter.getHeaderFirst());
        outputHeaderFooter(headerFooter.getFooterFirst());
    }

    private void outputHeaderFooter(XSSFBHeaderFooter headerFooter) {
        String text = headerFooter.getString();
        if (StringUtil.isNotBlank(text)) {
            this.handler.headerFooter(text, headerFooter.isHeader(), headerFooter.getHeaderFooterTypeLabel());
        }
    }

    private void checkMissedComments(int currentRow, int colNum) {
        if (this.comments == null) {
            return;
        }
        Queue<CellAddress> queue = this.comments.getAddresses();
        while (!queue.isEmpty()) {
            CellAddress cellAddress = queue.peek();
            if (cellAddress.getRow() == currentRow && cellAddress.getColumn() < colNum) {
                CellAddress cellAddress2 = queue.remove();
                dumpEmptyCellComment(cellAddress2, this.comments.get(cellAddress2));
            } else if (cellAddress.getRow() == currentRow && cellAddress.getColumn() == colNum) {
                queue.remove();
                return;
            } else if ((cellAddress.getRow() == currentRow && cellAddress.getColumn() > colNum) || cellAddress.getRow() > currentRow) {
                return;
            }
        }
    }

    private void checkMissedComments(int currentRow) {
        if (this.comments == null) {
            return;
        }
        Queue<CellAddress> queue = this.comments.getAddresses();
        int lastInterpolatedRow = -1;
        while (!queue.isEmpty()) {
            CellAddress cellAddress = queue.peek();
            if (currentRow == -1 || cellAddress.getRow() < currentRow) {
                CellAddress cellAddress2 = queue.remove();
                CellAddress cellAddress3 = cellAddress2;
                if (cellAddress3.getRow() != lastInterpolatedRow) {
                    startRow(cellAddress3.getRow());
                }
                dumpEmptyCellComment(cellAddress3, this.comments.get(cellAddress3));
                lastInterpolatedRow = cellAddress3.getRow();
            } else {
                return;
            }
        }
    }

    private void startRow(int row) {
        if (row == this.lastStartedRow) {
            return;
        }
        if (this.lastStartedRow != this.lastEndedRow) {
            endRow(this.lastStartedRow);
        }
        this.handler.startRow(row);
        this.lastStartedRow = row;
    }

    private void endRow(int row) {
        if (this.lastEndedRow == row) {
            return;
        }
        this.handler.endRow(row);
        this.lastEndedRow = row;
    }

    private void dumpEmptyCellComment(CellAddress cellAddress, XSSFBComment comment) {
        this.handler.stringCell(cellAddress.formatAsString(), null, comment);
    }

    private double rkNumber(byte[] data, int offset) {
        double d;
        byte b0 = data[offset];
        boolean numDivBy100 = (b0 & 1) == 1;
        boolean floatingPoint = ((b0 >> 1) & 1) == 0;
        this.rkBuffer[4] = (byte) (((byte) (b0 & (-2))) & (-3));
        System.arraycopy(data, offset + 1, this.rkBuffer, 5, 3);
        if (floatingPoint) {
            d = LittleEndian.getDouble(this.rkBuffer);
        } else {
            int rawInt = LittleEndian.getInt(this.rkBuffer, 4);
            d = rawInt >> 2;
        }
        return numDivBy100 ? d / 100.0d : d;
    }

    /* loaded from: classes10.dex */
    private final class XSSFBSheetContentsHandlerWrapper implements XSSFBSheetContentsHandler {
        private final DataFormatter dataFormatter;
        private final XSSFSheetXMLHandler.SheetContentsHandler delegate;

        XSSFBSheetContentsHandlerWrapper(XSSFSheetXMLHandler.SheetContentsHandler delegate, DataFormatter dataFormatter) {
            this.delegate = delegate;
            this.dataFormatter = dataFormatter;
        }

        @Override // org.apache.poi.xssf.binary.XSSFBSheetHandler.XSSFBSheetContentsHandler
        public void startRow(int rowNum) {
            this.delegate.startRow(rowNum);
        }

        @Override // org.apache.poi.xssf.binary.XSSFBSheetHandler.XSSFBSheetContentsHandler
        public void endRow(int rowNum) {
            this.delegate.endRow(rowNum);
        }

        @Override // org.apache.poi.xssf.binary.XSSFBSheetHandler.XSSFBSheetContentsHandler
        public void stringCell(String cellReference, String value, XSSFComment comment) {
            this.delegate.cell(cellReference, value, comment);
        }

        @Override // org.apache.poi.xssf.binary.XSSFBSheetHandler.XSSFBSheetContentsHandler
        public void doubleCell(String cellReference, double value, XSSFComment comment, ExcelNumberFormat nf) {
            String formattedValue = this.dataFormatter.formatRawCellContents(value, nf.getIdx(), nf.getFormat());
            this.delegate.cell(cellReference, formattedValue, comment);
        }

        @Override // org.apache.poi.xssf.binary.XSSFBSheetHandler.XSSFBSheetContentsHandler
        public void booleanCell(String cellReference, boolean value, XSSFComment comment) {
            this.delegate.cell(cellReference, Boolean.toString(value), comment);
        }

        @Override // org.apache.poi.xssf.binary.XSSFBSheetHandler.XSSFBSheetContentsHandler
        public void errorCell(String cellReference, FormulaError fe, XSSFComment comment) {
            this.delegate.cell(cellReference, "ERROR", comment);
        }

        @Override // org.apache.poi.xssf.binary.XSSFBSheetHandler.XSSFBSheetContentsHandler
        public void headerFooter(String text, boolean isHeader, String tagName) {
            this.delegate.headerFooter(text, isHeader, tagName);
        }

        @Override // org.apache.poi.xssf.binary.XSSFBSheetHandler.XSSFBSheetContentsHandler
        public void endSheet() {
            this.delegate.endSheet();
        }
    }
}
