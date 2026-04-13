package org.apache.poi.xssf.streaming;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.PrimitiveIterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.util.CodepointsUtil;
import org.apache.poi.util.Removal;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType;

/* loaded from: classes10.dex */
public class SheetDataWriter implements Closeable {
    private final File _fd;
    private int _lowestIndexOfFlushedRows;
    private int _numberLastFlushedRow;
    private int _numberOfCellsOfLastFlushedRow;
    private int _numberOfFlushedRows;
    protected final Writer _out;
    private int _rownum;
    private SharedStringsTable _sharedStringSource;

    public SheetDataWriter() throws IOException {
        this._numberLastFlushedRow = -1;
        this._fd = createTempFile();
        this._out = createWriter(this._fd);
    }

    public SheetDataWriter(Writer writer) throws IOException {
        this._numberLastFlushedRow = -1;
        this._fd = null;
        this._out = writer;
    }

    public SheetDataWriter(SharedStringsTable sharedStringsTable) throws IOException {
        this();
        this._sharedStringSource = sharedStringsTable;
    }

    @Removal(version = "6.0.0")
    public File createTempFile() throws IOException {
        return TempFile.createTempFile("poi-sxssf-sheet", ".xml");
    }

    @Removal(version = "6.0.0")
    public Writer createWriter(File fd) throws IOException {
        FileOutputStream fos = new FileOutputStream(fd);
        try {
            OutputStream decorated = decorateOutputStream(fos);
            return new BufferedWriter(new OutputStreamWriter(decorated, StandardCharsets.UTF_8));
        } catch (IOException e) {
            fos.close();
            throw e;
        }
    }

    protected OutputStream decorateOutputStream(FileOutputStream fos) throws IOException {
        return fos;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this._out.close();
    }

    protected File getTempFile() {
        return this._fd;
    }

    public InputStream getWorksheetXMLInputStream() throws IOException {
        File fd = getTempFile();
        if (fd == null) {
            throw new IOException("getWorksheetXMLInputStream only works when a temp file is used");
        }
        FileInputStream fis = new FileInputStream(fd);
        try {
            return decorateInputStream(fis);
        } catch (IOException e) {
            fis.close();
            throw e;
        }
    }

    protected InputStream decorateInputStream(FileInputStream fis) throws IOException {
        return fis;
    }

    public int getNumberOfFlushedRows() {
        return this._numberOfFlushedRows;
    }

    public int getNumberOfCellsOfLastFlushedRow() {
        return this._numberOfCellsOfLastFlushedRow;
    }

    public int getLowestIndexOfFlushedRows() {
        return this._lowestIndexOfFlushedRows;
    }

    public int getLastFlushedRow() {
        return this._numberLastFlushedRow;
    }

    public void writeRow(int rownum, SXSSFRow row) throws IOException {
        if (this._numberOfFlushedRows == 0) {
            this._lowestIndexOfFlushedRows = rownum;
        }
        this._numberLastFlushedRow = Math.max(rownum, this._numberLastFlushedRow);
        this._numberOfCellsOfLastFlushedRow = row.getLastCellNum();
        this._numberOfFlushedRows++;
        beginRow(rownum, row);
        Iterator<Cell> cells = row.allCellsIterator();
        int columnIndex = 0;
        while (cells.hasNext()) {
            writeCell(columnIndex, cells.next());
            columnIndex++;
        }
        endRow();
    }

    void beginRow(int rownum, SXSSFRow row) throws IOException {
        this._out.write("<row");
        writeAttribute("r", Integer.toString(rownum + 1));
        if (row.hasCustomHeight()) {
            writeAttribute("customHeight", "1");
            writeAttribute("ht", Float.toString(row.getHeightInPoints()));
        }
        if (row.getZeroHeight()) {
            writeAttribute(CellUtil.HIDDEN, "1");
        }
        if (row.isFormatted()) {
            writeAttribute("s", Integer.toString(row.getRowStyleIndex()));
            writeAttribute("customFormat", "1");
        }
        if (row.getOutlineLevel() != 0) {
            writeAttribute("outlineLevel", Integer.toString(row.getOutlineLevel()));
        }
        if (row.getHidden() != null) {
            writeAttribute(CellUtil.HIDDEN, row.getHidden().booleanValue() ? "1" : "0");
        }
        if (row.getCollapsed() != null) {
            writeAttribute("collapsed", row.getCollapsed().booleanValue() ? "1" : "0");
        }
        this._out.write(">\n");
        this._rownum = rownum;
    }

    void endRow() throws IOException {
        this._out.write("</row>\n");
    }

    public void writeCell(int columnIndex, Cell cell) throws IOException {
        if (cell == null) {
            return;
        }
        String ref = new CellReference(this._rownum, columnIndex).formatAsString();
        this._out.write("<c");
        writeAttribute("r", ref);
        CellStyle cellStyle = cell.getCellStyle();
        if (cellStyle.getIndex() != 0) {
            writeAttribute("s", Integer.toString(cellStyle.getIndex() & 65535));
        }
        CellType cellType = cell.getCellType();
        switch (cellType) {
            case NUMERIC:
                writeAttribute("t", "n");
                this._out.write("><v>");
                this._out.write(Double.toString(cell.getNumericCellValue()));
                this._out.write("</v>");
                break;
            case STRING:
                if (this._sharedStringSource != null) {
                    RichTextString rt = cell.getRichStringCellValue();
                    int sRef = this._sharedStringSource.addSharedStringItem(rt);
                    writeAttribute("t", STCellType.S.toString());
                    this._out.write("><v>");
                    this._out.write(String.valueOf(sRef));
                    this._out.write("</v>");
                    break;
                } else {
                    writeAttribute("t", "inlineStr");
                    this._out.write("><is><t");
                    if (hasLeadingTrailingSpaces(cell.getStringCellValue())) {
                        writeAttribute("xml:space", "preserve");
                    }
                    this._out.write(">");
                    outputEscapedString(cell.getStringCellValue());
                    this._out.write("</t></is>");
                    break;
                }
            case BOOLEAN:
                writeAttribute("t", "b");
                this._out.write("><v>");
                this._out.write(cell.getBooleanCellValue() ? "1" : "0");
                this._out.write("</v>");
                break;
            case ERROR:
                FormulaError error = FormulaError.forInt(cell.getErrorCellValue());
                writeAttribute("t", "e");
                this._out.write("><v>");
                outputEscapedString(error.getString());
                this._out.write("</v>");
                break;
            case BLANK:
                this._out.write(62);
                break;
            case FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case NUMERIC:
                        writeAttribute("t", "n");
                        break;
                    case STRING:
                        writeAttribute("t", STCellType.STR.toString());
                        break;
                    case BOOLEAN:
                        writeAttribute("t", "b");
                        break;
                    case ERROR:
                        writeAttribute("t", "e");
                        break;
                }
                this._out.write("><f>");
                outputEscapedString(cell.getCellFormula());
                this._out.write("</f>");
                switch (cell.getCachedFormulaResultType()) {
                    case NUMERIC:
                        double nval = cell.getNumericCellValue();
                        if (!Double.isNaN(nval)) {
                            this._out.write("<v>");
                            this._out.write(Double.toString(nval));
                            this._out.write("</v>");
                            break;
                        }
                        break;
                    case STRING:
                        String value = cell.getStringCellValue();
                        if (value != null && !value.isEmpty()) {
                            this._out.write("<v>");
                            outputEscapedString(value);
                            this._out.write("</v>");
                            break;
                        }
                        break;
                    case BOOLEAN:
                        this._out.write("><v>");
                        this._out.write(cell.getBooleanCellValue() ? "1" : "0");
                        this._out.write("</v>");
                        break;
                    case ERROR:
                        FormulaError error2 = FormulaError.forInt(cell.getErrorCellValue());
                        this._out.write("><v>");
                        outputEscapedString(error2.getString());
                        this._out.write("</v>");
                        break;
                }
            default:
                throw new IllegalStateException("Invalid cell type: " + cellType);
        }
        this._out.write("</c>");
    }

    private void writeAttribute(String name, String value) throws IOException {
        this._out.write(32);
        this._out.write(name);
        this._out.write("=\"");
        this._out.write(value);
        this._out.write(34);
    }

    boolean hasLeadingTrailingSpaces(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        char firstChar = str.charAt(0);
        char lastChar = str.charAt(str.length() - 1);
        return Character.isWhitespace(firstChar) || Character.isWhitespace(lastChar);
    }

    protected void outputEscapedString(String s) throws IOException {
        if (s == null || s.isEmpty()) {
            return;
        }
        PrimitiveIterator.OfInt iter = CodepointsUtil.primitiveIterator(s);
        while (iter.hasNext()) {
            int codepoint = iter.nextInt();
            switch (codepoint) {
                case 9:
                    this._out.write("&#x9;");
                    break;
                case 10:
                    this._out.write("&#xa;");
                    break;
                case 13:
                    this._out.write("&#xd;");
                    break;
                case 34:
                    this._out.write("&quot;");
                    break;
                case 38:
                    this._out.write("&amp;");
                    break;
                case 60:
                    this._out.write("&lt;");
                    break;
                case 62:
                    this._out.write("&gt;");
                    break;
                case 160:
                    this._out.write("&#xa0;");
                    break;
                default:
                    char[] chars = Character.toChars(codepoint);
                    if (chars.length == 1) {
                        char c = chars[0];
                        if (replaceWithQuestionMark(c)) {
                            this._out.write(63);
                            break;
                        } else {
                            this._out.write(c);
                            break;
                        }
                    } else {
                        this._out.write(chars);
                        break;
                    }
            }
        }
    }

    static boolean replaceWithQuestionMark(char c) {
        return c < ' ' || (65534 <= c && c <= 65535);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void flush() throws IOException {
        this._out.flush();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean dispose() throws IOException {
        File file;
        boolean delete;
        try {
            this._out.close();
            if (file != null) {
                if (!delete) {
                    return false;
                }
            }
            return true;
        } finally {
            if (this._fd == null || this._fd.delete()) {
            }
        }
    }
}
