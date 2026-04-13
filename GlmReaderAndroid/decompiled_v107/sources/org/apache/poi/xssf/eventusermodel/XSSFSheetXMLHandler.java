package org.apache.poi.xssf.eventusermodel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.model.Comments;
import org.apache.poi.xssf.model.SharedStrings;
import org.apache.poi.xssf.model.Styles;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* loaded from: classes10.dex */
public class XSSFSheetXMLHandler extends DefaultHandler {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSSFSheetXMLHandler.class);
    private String cellRef;
    private Queue<CellAddress> commentCellRefs;
    private final Comments comments;
    private boolean fIsOpen;
    private short formatIndex;
    private String formatString;
    private final DataFormatter formatter;
    private final StringBuilder formula;
    private final boolean formulasNotResults;
    private final StringBuilder headerFooter;
    private boolean hfIsOpen;
    private boolean isIsOpen;
    private xssfDataType nextDataType;
    private int nextRowNum;
    private final SheetContentsHandler output;
    private int rowNum;
    private final SharedStrings sharedStringsTable;
    private final Styles stylesTable;
    private boolean vIsOpen;
    private final StringBuilder value;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public enum EmptyCellCommentsCheckType {
        CELL,
        END_OF_ROW,
        END_OF_SHEET_DATA
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public enum xssfDataType {
        BOOLEAN,
        ERROR,
        FORMULA,
        INLINE_STRING,
        SST_STRING,
        NUMBER
    }

    public XSSFSheetXMLHandler(Styles styles, Comments comments, SharedStrings strings, SheetContentsHandler sheetContentsHandler, DataFormatter dataFormatter, boolean formulasNotResults) {
        this.value = new StringBuilder(64);
        this.formula = new StringBuilder(64);
        this.headerFooter = new StringBuilder(64);
        this.stylesTable = styles;
        this.comments = comments;
        this.sharedStringsTable = strings;
        this.output = sheetContentsHandler;
        this.formulasNotResults = formulasNotResults;
        this.nextDataType = xssfDataType.NUMBER;
        this.formatter = dataFormatter;
        init(comments);
    }

    public XSSFSheetXMLHandler(Styles styles, SharedStrings strings, SheetContentsHandler sheetContentsHandler, DataFormatter dataFormatter, boolean formulasNotResults) {
        this(styles, null, strings, sheetContentsHandler, dataFormatter, formulasNotResults);
    }

    public XSSFSheetXMLHandler(Styles styles, SharedStrings strings, SheetContentsHandler sheetContentsHandler, boolean formulasNotResults) {
        this(styles, strings, sheetContentsHandler, new DataFormatter(), formulasNotResults);
    }

    private void init(Comments commentsTable) {
        if (commentsTable != null) {
            this.commentCellRefs = new LinkedList();
            Iterator<CellAddress> iter = commentsTable.getCellAddresses();
            while (iter.hasNext()) {
                this.commentCellRefs.add(iter.next());
            }
        }
    }

    private boolean isTextTag(String name) {
        if ("v".equals(name) || "inlineStr".equals(name)) {
            return true;
        }
        return "t".equals(name) && this.isIsOpen;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (uri != null && !uri.equals(XSSFRelation.NS_SPREADSHEETML)) {
            return;
        }
        if (isTextTag(localName)) {
            this.vIsOpen = true;
            if (!this.isIsOpen) {
                this.value.setLength(0);
                return;
            }
            return;
        }
        if ("is".equals(localName)) {
            this.isIsOpen = true;
            return;
        }
        if ("f".equals(localName)) {
            this.formula.setLength(0);
            if (this.nextDataType == xssfDataType.NUMBER) {
                this.nextDataType = xssfDataType.FORMULA;
            }
            String type = attributes.getValue("t");
            if (type != null && type.equals("shared")) {
                String ref = attributes.getValue("ref");
                attributes.getValue("si");
                if (ref != null) {
                    this.fIsOpen = true;
                    return;
                } else {
                    if (this.formulasNotResults) {
                        LOG.atWarn().log("shared formulas not yet supported!");
                        return;
                    }
                    return;
                }
            }
            this.fIsOpen = true;
            return;
        }
        if ("oddHeader".equals(localName) || "evenHeader".equals(localName) || "firstHeader".equals(localName) || "firstFooter".equals(localName) || "oddFooter".equals(localName) || "evenFooter".equals(localName)) {
            this.hfIsOpen = true;
            this.headerFooter.setLength(0);
            return;
        }
        if ("row".equals(localName)) {
            String rowNumStr = attributes.getValue("r");
            if (rowNumStr != null) {
                this.rowNum = parseInt(rowNumStr) - 1;
            } else {
                this.rowNum = this.nextRowNum;
            }
            this.output.startRow(this.rowNum);
            return;
        }
        if ("c".equals(localName)) {
            this.formula.setLength(0);
            this.nextDataType = xssfDataType.NUMBER;
            this.formatIndex = (short) -1;
            this.formatString = null;
            this.cellRef = attributes.getValue("r");
            String cellType = attributes.getValue("t");
            String cellStyleStr = attributes.getValue("s");
            if ("b".equals(cellType)) {
                this.nextDataType = xssfDataType.BOOLEAN;
                return;
            }
            if ("e".equals(cellType)) {
                this.nextDataType = xssfDataType.ERROR;
                return;
            }
            if ("inlineStr".equals(cellType)) {
                this.nextDataType = xssfDataType.INLINE_STRING;
                return;
            }
            if ("s".equals(cellType)) {
                this.nextDataType = xssfDataType.SST_STRING;
                return;
            }
            if ("str".equals(cellType)) {
                this.nextDataType = xssfDataType.FORMULA;
                return;
            }
            XSSFCellStyle style = null;
            if (this.stylesTable != null) {
                if (cellStyleStr != null) {
                    int styleIndex = parseInt(cellStyleStr);
                    style = this.stylesTable.getStyleAt(styleIndex);
                } else if (this.stylesTable.getNumCellStyles() > 0) {
                    style = this.stylesTable.getStyleAt(0);
                }
            }
            if (style != null) {
                this.formatIndex = style.getDataFormat();
                this.formatString = style.getDataFormatString();
                if (this.formatString == null) {
                    this.formatString = BuiltinFormats.getBuiltinFormat(this.formatIndex);
                }
            }
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (uri != null && !uri.equals(XSSFRelation.NS_SPREADSHEETML)) {
            return;
        }
        if (isTextTag(localName)) {
            this.vIsOpen = false;
            if (!this.isIsOpen) {
                outputCell();
                this.value.setLength(0);
                return;
            }
            return;
        }
        if ("f".equals(localName)) {
            this.fIsOpen = false;
            return;
        }
        if ("is".equals(localName)) {
            this.isIsOpen = false;
            outputCell();
            this.value.setLength(0);
            return;
        }
        if ("row".equals(localName)) {
            checkForEmptyCellComments(EmptyCellCommentsCheckType.END_OF_ROW);
            this.output.endRow(this.rowNum);
            this.nextRowNum = this.rowNum + 1;
            return;
        }
        if ("sheetData".equals(localName)) {
            checkForEmptyCellComments(EmptyCellCommentsCheckType.END_OF_SHEET_DATA);
            this.output.endSheet();
            return;
        }
        if ("oddHeader".equals(localName) || "evenHeader".equals(localName) || "firstHeader".equals(localName)) {
            this.hfIsOpen = false;
            this.output.headerFooter(this.headerFooter.toString(), true, localName);
        } else if ("oddFooter".equals(localName) || "evenFooter".equals(localName) || "firstFooter".equals(localName)) {
            this.hfIsOpen = false;
            this.output.headerFooter(this.headerFooter.toString(), false, localName);
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.vIsOpen) {
            this.value.append(ch, start, length);
        }
        if (this.fIsOpen) {
            this.formula.append(ch, start, length);
        }
        if (this.hfIsOpen) {
            this.headerFooter.append(ch, start, length);
        }
    }

    private void outputCell() {
        String thisStr = null;
        if (this.formulasNotResults && this.formula.length() > 0) {
            thisStr = this.formula.toString();
        } else {
            switch (this.nextDataType) {
                case BOOLEAN:
                    char first = this.value.charAt(0);
                    thisStr = first == '0' ? "FALSE" : "TRUE";
                    break;
                case ERROR:
                    thisStr = "ERROR:" + ((Object) this.value);
                    break;
                case FORMULA:
                    if (this.formulasNotResults) {
                        thisStr = this.formula.toString();
                        break;
                    } else {
                        String fv = this.value.toString();
                        if (this.formatString != null) {
                            try {
                                double d = parseDouble(fv);
                                thisStr = this.formatter.formatRawCellContents(d, this.formatIndex, this.formatString);
                                break;
                            } catch (Exception e) {
                                LOG.atInfo().log("Error formatting cell '{}' - will use its raw value instead (format '{}')", this.cellRef, this.formatString);
                                thisStr = fv;
                                break;
                            }
                        } else {
                            thisStr = fv;
                            break;
                        }
                    }
                case INLINE_STRING:
                    XSSFRichTextString rtsi = new XSSFRichTextString(this.value.toString());
                    thisStr = rtsi.toString();
                    break;
                case SST_STRING:
                    String sstIndex = this.value.toString().trim();
                    if (!sstIndex.isEmpty()) {
                        try {
                            int idx = parseInt(sstIndex);
                            RichTextString rtss = this.sharedStringsTable.getItemAt(idx);
                            thisStr = rtss.toString();
                            break;
                        } catch (NumberFormatException ex) {
                            LOG.atError().withThrowable(ex).log("Failed to parse SST index '{}'", sstIndex);
                            break;
                        }
                    }
                    break;
                case NUMBER:
                    String n = this.value.toString();
                    if (this.formatString != null && !n.isEmpty()) {
                        try {
                            thisStr = this.formatter.formatRawCellContents(parseDouble(n), this.formatIndex, this.formatString);
                            break;
                        } catch (Exception e2) {
                            LOG.atInfo().log("Error formatting cell '{}' - will use its raw value instead (format '{}')", this.cellRef, this.formatString);
                            thisStr = n;
                            break;
                        }
                    } else {
                        thisStr = n;
                        break;
                    }
                    break;
                default:
                    thisStr = "(TODO: Unexpected type: " + this.nextDataType + ")";
                    break;
            }
        }
        checkForEmptyCellComments(EmptyCellCommentsCheckType.CELL);
        XSSFComment comment = this.comments != null ? this.comments.findCellComment(new CellAddress(this.cellRef)) : null;
        this.output.cell(this.cellRef, thisStr, comment);
    }

    private void checkForEmptyCellComments(EmptyCellCommentsCheckType type) {
        CellAddress nextCommentCellRef;
        if (this.commentCellRefs != null && !this.commentCellRefs.isEmpty()) {
            if (type == EmptyCellCommentsCheckType.END_OF_SHEET_DATA) {
                while (!this.commentCellRefs.isEmpty()) {
                    outputEmptyCellComment(this.commentCellRefs.remove());
                }
                return;
            }
            if (this.cellRef == null) {
                if (type == EmptyCellCommentsCheckType.END_OF_ROW) {
                    while (!this.commentCellRefs.isEmpty() && this.commentCellRefs.peek().getRow() == this.rowNum) {
                        outputEmptyCellComment(this.commentCellRefs.remove());
                    }
                    return;
                }
                throw new IllegalStateException("Cell ref should be null only if there are only empty cells in the row; rowNum: " + this.rowNum);
            }
            do {
                CellAddress cellAddress = new CellAddress(this.cellRef);
                CellAddress peek = this.commentCellRefs.peek();
                if (type == EmptyCellCommentsCheckType.CELL && cellAddress.equals(peek)) {
                    this.commentCellRefs.remove();
                    return;
                }
                int comparison = peek.compareTo(cellAddress);
                if (comparison > 0 && type == EmptyCellCommentsCheckType.END_OF_ROW && peek.getRow() <= this.rowNum) {
                    nextCommentCellRef = this.commentCellRefs.remove();
                    outputEmptyCellComment(nextCommentCellRef);
                } else if (comparison < 0 && type == EmptyCellCommentsCheckType.CELL && peek.getRow() <= this.rowNum) {
                    nextCommentCellRef = this.commentCellRefs.remove();
                    outputEmptyCellComment(nextCommentCellRef);
                } else {
                    nextCommentCellRef = null;
                }
                if (nextCommentCellRef == null) {
                    return;
                }
            } while (!this.commentCellRefs.isEmpty());
        }
    }

    private void outputEmptyCellComment(CellAddress cellRef) {
        XSSFComment comment = this.comments.findCellComment(cellRef);
        this.output.cell(cellRef.formatAsString(), null, comment);
    }

    private static int parseInt(String value) throws NumberFormatException {
        return Integer.parseInt(value.trim());
    }

    private static double parseDouble(String value) throws NumberFormatException {
        return Double.parseDouble(value.trim());
    }

    /* loaded from: classes10.dex */
    public interface SheetContentsHandler {
        void cell(String str, String str2, XSSFComment xSSFComment);

        void endRow(int i);

        void startRow(int i);

        default void headerFooter(String text, boolean isHeader, String tagName) {
        }

        default void endSheet() {
        }
    }
}
