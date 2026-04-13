package org.apache.poi.xssf.streaming;

import androidx.core.view.MotionEventCompat;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.TreeMap;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.AutoFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.PageMargin;
import org.apache.poi.ss.usermodel.PaneType;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PaneInformation;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.usermodel.OoxmlSheetExtensions;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFVMLDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

/* loaded from: classes10.dex */
public class SXSSFSheet implements Sheet, OoxmlSheetExtensions {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) SXSSFSheet.class);
    protected AutoSizeColumnTracker _autoSizeColumnTracker;
    final XSSFSheet _sh;
    protected final SXSSFWorkbook _workbook;
    protected SheetDataWriter _writer;
    private boolean allFlushed;
    private int rightMostColumn;
    private final TreeMap<Integer, SXSSFRow> _rows = new TreeMap<>();
    private int _randomAccessWindowSize = 100;
    private int lastFlushedRowNumber = -1;
    private int leftMostColumn = SpreadsheetVersion.EXCEL2007.getLastColumnIndex();

    /* JADX INFO: Access modifiers changed from: protected */
    public SXSSFSheet(SXSSFWorkbook workbook, XSSFSheet xSheet, int randomAccessWindowSize) {
        this._workbook = workbook;
        this._sh = xSheet;
        calculateLeftAndRightMostColumns(xSheet);
        setRandomAccessWindowSize(randomAccessWindowSize);
        try {
            this._autoSizeColumnTracker = new AutoSizeColumnTracker(this);
        } catch (IndexOutOfBoundsException | InternalError | NoClassDefFoundError | UnsatisfiedLinkError e) {
            if (!e.getMessage().contains("X11FontManager")) {
                throw e;
            }
            LOG.atWarn().withThrowable(e).log("Failed to create AutoSizeColumnTracker, possibly due to fonts not being installed in your OS");
        }
    }

    private void calculateLeftAndRightMostColumns(XSSFSheet xssfSheet) {
        if (this._workbook.shouldCalculateSheetDimensions()) {
            int rowCount = 0;
            int leftMostColumn = Integer.MAX_VALUE;
            int rightMostColumn = 0;
            Iterator<Row> it = xssfSheet.iterator();
            while (it.hasNext()) {
                Row row = it.next();
                rowCount++;
                if (row.getFirstCellNum() < leftMostColumn) {
                    int first = row.getFirstCellNum();
                    int last = row.getLastCellNum() - 1;
                    leftMostColumn = Math.min(first, leftMostColumn);
                    rightMostColumn = Math.max(last, rightMostColumn);
                }
            }
            if (rowCount > 0) {
                this.leftMostColumn = leftMostColumn;
                this.rightMostColumn = rightMostColumn;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SXSSFSheet(org.apache.poi.xssf.streaming.SXSSFWorkbook r4, org.apache.poi.xssf.usermodel.XSSFSheet r5) throws java.io.IOException {
        /*
            r3 = this;
            r3.<init>()
            java.util.TreeMap r0 = new java.util.TreeMap
            r0.<init>()
            r3._rows = r0
            r0 = 100
            r3._randomAccessWindowSize = r0
            r0 = -1
            r3.lastFlushedRowNumber = r0
            org.apache.poi.ss.SpreadsheetVersion r0 = org.apache.poi.ss.SpreadsheetVersion.EXCEL2007
            int r0 = r0.getLastColumnIndex()
            r3.leftMostColumn = r0
            r3._workbook = r4
            r3._sh = r5
            org.apache.poi.xssf.streaming.SheetDataWriter r0 = r4.createSheetDataWriter()
            r3._writer = r0
            org.apache.poi.xssf.streaming.SXSSFWorkbook r0 = r3._workbook
            int r0 = r0.getRandomAccessWindowSize()
            r3.setRandomAccessWindowSize(r0)
            org.apache.poi.xssf.streaming.AutoSizeColumnTracker r0 = new org.apache.poi.xssf.streaming.AutoSizeColumnTracker     // Catch: java.lang.Throwable -> L34 java.lang.IndexOutOfBoundsException -> L3b java.lang.InternalError -> L3d java.lang.NoClassDefFoundError -> L3f java.lang.UnsatisfiedLinkError -> L41
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L34 java.lang.IndexOutOfBoundsException -> L3b java.lang.InternalError -> L3d java.lang.NoClassDefFoundError -> L3f java.lang.UnsatisfiedLinkError -> L41
            r3._autoSizeColumnTracker = r0     // Catch: java.lang.Throwable -> L34 java.lang.IndexOutOfBoundsException -> L3b java.lang.InternalError -> L3d java.lang.NoClassDefFoundError -> L3f java.lang.UnsatisfiedLinkError -> L41
            goto L5d
        L34:
            r0 = move-exception
            org.apache.poi.xssf.streaming.SheetDataWriter r1 = r3._writer
            r1.close()
            throw r0
        L3b:
            r0 = move-exception
            goto L42
        L3d:
            r0 = move-exception
            goto L42
        L3f:
            r0 = move-exception
            goto L42
        L41:
            r0 = move-exception
        L42:
            java.lang.String r1 = r0.getMessage()
            java.lang.String r2 = "X11FontManager"
            boolean r1 = r1.contains(r2)
            if (r1 == 0) goto L5f
            org.apache.logging.log4j.Logger r1 = org.apache.poi.xssf.streaming.SXSSFSheet.LOG
            org.apache.logging.log4j.LogBuilder r1 = r1.atWarn()
            org.apache.logging.log4j.LogBuilder r1 = r1.withThrowable(r0)
            java.lang.String r2 = "Failed to create AutoSizeColumnTracker, possibly due to fonts not being installed in your OS"
            r1.log(r2)
        L5d:
            return
        L5f:
            org.apache.poi.xssf.streaming.SheetDataWriter r1 = r3._writer
            r1.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.streaming.SXSSFSheet.<init>(org.apache.poi.xssf.streaming.SXSSFWorkbook, org.apache.poi.xssf.usermodel.XSSFSheet):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Internal
    public SheetDataWriter getSheetDataWriter() {
        return this._writer;
    }

    public InputStream getWorksheetXMLInputStream() throws IOException {
        flushRows(0);
        this._writer.close();
        return this._writer.getWorksheetXMLInputStream();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public SXSSFRow createRow(int rownum) {
        int maxrow = SpreadsheetVersion.EXCEL2007.getLastRowIndex();
        if (rownum < 0 || rownum > maxrow) {
            throw new IllegalArgumentException("Invalid row number (" + rownum + ") outside allowable range (0.." + maxrow + ")");
        }
        if (this._writer != null && rownum <= this._writer.getLastFlushedRow()) {
            throw new IllegalArgumentException("Attempting to write a row[" + rownum + "] in the range [0," + this._writer.getLastFlushedRow() + "] that is already written to disk.");
        }
        if (this._sh.getPhysicalNumberOfRows() > 0 && rownum <= this._sh.getLastRowNum()) {
            throw new IllegalArgumentException("Attempting to write a row[" + rownum + "] in the range [0," + this._sh.getLastRowNum() + "] that is already written to disk.");
        }
        SXSSFRow newRow = new SXSSFRow(this);
        newRow.setRowNumWithoutUpdatingSheet(rownum);
        this._rows.put(Integer.valueOf(rownum), newRow);
        this.allFlushed = false;
        if (this._randomAccessWindowSize >= 0 && this._rows.size() > this._randomAccessWindowSize) {
            try {
                flushRows(this._randomAccessWindowSize);
            } catch (IOException ioe) {
                throw new IllegalStateException(ioe);
            }
        }
        return newRow;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void removeRow(Row row) {
        if (row.getSheet() != this) {
            throw new IllegalArgumentException("Specified row does not belong to this sheet");
        }
        Iterator<Map.Entry<Integer, SXSSFRow>> iter = this._rows.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, SXSSFRow> entry = iter.next();
            if (entry.getValue() == row) {
                iter.remove();
                return;
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public SXSSFRow getRow(int rownum) {
        return this._rows.get(Integer.valueOf(rownum));
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int getPhysicalNumberOfRows() {
        return this._rows.size() + this._writer.getNumberOfFlushedRows();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int getFirstRowNum() {
        if (this._writer.getNumberOfFlushedRows() > 0) {
            return this._writer.getLowestIndexOfFlushedRows();
        }
        if (this._rows.isEmpty()) {
            return -1;
        }
        return this._rows.firstKey().intValue();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int getLastRowNum() {
        if (this._rows.isEmpty()) {
            return -1;
        }
        return this._rows.lastKey().intValue();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setColumnHidden(int columnIndex, boolean hidden) {
        this._sh.setColumnHidden(columnIndex, hidden);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isColumnHidden(int columnIndex) {
        return this._sh.isColumnHidden(columnIndex);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setColumnWidth(int columnIndex, int width) {
        this._sh.setColumnWidth(columnIndex, width);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int getColumnWidth(int columnIndex) {
        return this._sh.getColumnWidth(columnIndex);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public float getColumnWidthInPixels(int columnIndex) {
        return this._sh.getColumnWidthInPixels(columnIndex);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDefaultColumnWidth(int width) {
        this._sh.setDefaultColumnWidth(width);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int getDefaultColumnWidth() {
        return this._sh.getDefaultColumnWidth();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public short getDefaultRowHeight() {
        return this._sh.getDefaultRowHeight();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public float getDefaultRowHeightInPoints() {
        return this._sh.getDefaultRowHeightInPoints();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDefaultRowHeight(short height) {
        this._sh.setDefaultRowHeight(height);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDefaultRowHeightInPoints(float height) {
        this._sh.setDefaultRowHeightInPoints(height);
    }

    @Override // org.apache.poi.xssf.usermodel.OoxmlSheetExtensions
    public XSSFVMLDrawing getVMLDrawing(boolean autoCreate) {
        XSSFSheet xssfSheet = getWorkbook().getXSSFSheet(this);
        if (xssfSheet == null) {
            return null;
        }
        return xssfSheet.getVMLDrawing(autoCreate);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public CellStyle getColumnStyle(int column) {
        return this._sh.getColumnStyle(column);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int addMergedRegion(CellRangeAddress region) {
        return this._sh.addMergedRegion(region);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int addMergedRegionUnsafe(CellRangeAddress region) {
        return this._sh.addMergedRegionUnsafe(region);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void validateMergedRegions() {
        this._sh.validateMergedRegions();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setVerticallyCenter(boolean value) {
        this._sh.setVerticallyCenter(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setHorizontallyCenter(boolean value) {
        this._sh.setHorizontallyCenter(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getHorizontallyCenter() {
        return this._sh.getHorizontallyCenter();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getVerticallyCenter() {
        return this._sh.getVerticallyCenter();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void removeMergedRegion(int index) {
        this._sh.removeMergedRegion(index);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void removeMergedRegions(Collection<Integer> indices) {
        this._sh.removeMergedRegions(indices);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int getNumMergedRegions() {
        return this._sh.getNumMergedRegions();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public CellRangeAddress getMergedRegion(int index) {
        return this._sh.getMergedRegion(index);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public List<CellRangeAddress> getMergedRegions() {
        return this._sh.getMergedRegions();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public Iterator<Row> rowIterator() {
        Iterator<Row> result = this._rows.values().iterator();
        return result;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet, java.lang.Iterable
    public Spliterator<Row> spliterator() {
        return this._rows.values().spliterator();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setAutobreaks(boolean value) {
        this._sh.setAutobreaks(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDisplayGuts(boolean value) {
        this._sh.setDisplayGuts(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDisplayZeros(boolean value) {
        this._sh.setDisplayZeros(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isDisplayZeros() {
        return this._sh.isDisplayZeros();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setRightToLeft(boolean value) {
        this._sh.setRightToLeft(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isRightToLeft() {
        return this._sh.isRightToLeft();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setFitToPage(boolean value) {
        this._sh.setFitToPage(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setRowSumsBelow(boolean value) {
        this._sh.setRowSumsBelow(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setRowSumsRight(boolean value) {
        this._sh.setRowSumsRight(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getAutobreaks() {
        return this._sh.getAutobreaks();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getDisplayGuts() {
        return this._sh.getDisplayGuts();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getFitToPage() {
        return this._sh.getFitToPage();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getRowSumsBelow() {
        return this._sh.getRowSumsBelow();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getRowSumsRight() {
        return this._sh.getRowSumsRight();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isPrintGridlines() {
        return this._sh.isPrintGridlines();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setPrintGridlines(boolean show) {
        this._sh.setPrintGridlines(show);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isPrintRowAndColumnHeadings() {
        return this._sh.isPrintRowAndColumnHeadings();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setPrintRowAndColumnHeadings(boolean show) {
        this._sh.setPrintRowAndColumnHeadings(show);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public PrintSetup getPrintSetup() {
        return this._sh.getPrintSetup();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public Header getHeader() {
        return this._sh.getHeader();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public Footer getFooter() {
        return this._sh.getFooter();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setSelected(boolean value) {
        this._sh.setSelected(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    @Removal(version = "7.0.0")
    @Deprecated
    public double getMargin(short margin) {
        return this._sh.getMargin(margin);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public double getMargin(PageMargin margin) {
        return this._sh.getMargin(margin);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    @Removal(version = "7.0.0")
    @Deprecated
    public void setMargin(short margin, double size) {
        this._sh.setMargin(margin, size);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setMargin(PageMargin margin, double size) {
        this._sh.setMargin(margin, size);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getProtect() {
        return this._sh.getProtect();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void protectSheet(String password) {
        this._sh.protectSheet(password);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getScenarioProtect() {
        return this._sh.getScenarioProtect();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setZoom(int scale) {
        this._sh.setZoom(scale);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public short getTopRow() {
        return this._sh.getTopRow();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public short getLeftCol() {
        return this._sh.getLeftCol();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void showInPane(int topRow, int leftCol) {
        this._sh.showInPane(topRow, leftCol);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setForceFormulaRecalculation(boolean value) {
        this._sh.setForceFormulaRecalculation(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getForceFormulaRecalculation() {
        return this._sh.getForceFormulaRecalculation();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    @NotImplemented
    public void shiftRows(int startRow, int endRow, int n) {
        throw new IllegalStateException("Not Implemented");
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    @NotImplemented
    public void shiftRows(int startRow, int endRow, int n, boolean copyRowHeight, boolean resetOriginalRowHeight) {
        throw new IllegalStateException("Not Implemented");
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void createFreezePane(int colSplit, int rowSplit, int leftmostColumn, int topRow) {
        this._sh.createFreezePane(colSplit, rowSplit, leftmostColumn, topRow);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void createFreezePane(int colSplit, int rowSplit) {
        this._sh.createFreezePane(colSplit, rowSplit);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    @Removal(version = "7.0.0")
    @Deprecated
    public void createSplitPane(int xSplitPos, int ySplitPos, int leftmostColumn, int topRow, int activePane) {
        this._sh.createSplitPane(xSplitPos, ySplitPos, leftmostColumn, topRow, activePane);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void createSplitPane(int xSplitPos, int ySplitPos, int leftmostColumn, int topRow, PaneType activePane) {
        this._sh.createSplitPane(xSplitPos, ySplitPos, leftmostColumn, topRow, activePane);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public PaneInformation getPaneInformation() {
        return this._sh.getPaneInformation();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDisplayGridlines(boolean show) {
        this._sh.setDisplayGridlines(show);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isDisplayGridlines() {
        return this._sh.isDisplayGridlines();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDisplayFormulas(boolean show) {
        this._sh.setDisplayFormulas(show);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isDisplayFormulas() {
        return this._sh.isDisplayFormulas();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDisplayRowColHeadings(boolean show) {
        this._sh.setDisplayRowColHeadings(show);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isDisplayRowColHeadings() {
        return this._sh.isDisplayRowColHeadings();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setRowBreak(int row) {
        this._sh.setRowBreak(row);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isRowBroken(int row) {
        return this._sh.isRowBroken(row);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void removeRowBreak(int row) {
        this._sh.removeRowBreak(row);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int[] getRowBreaks() {
        return this._sh.getRowBreaks();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int[] getColumnBreaks() {
        return this._sh.getColumnBreaks();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setColumnBreak(int column) {
        this._sh.setColumnBreak(column);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isColumnBroken(int column) {
        return this._sh.isColumnBroken(column);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void removeColumnBreak(int column) {
        this._sh.removeColumnBreak(column);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setColumnGroupCollapsed(int columnNumber, boolean collapsed) {
        this._sh.setColumnGroupCollapsed(columnNumber, collapsed);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void groupColumn(int fromColumn, int toColumn) {
        this._sh.groupColumn(fromColumn, toColumn);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void ungroupColumn(int fromColumn, int toColumn) {
        this._sh.ungroupColumn(fromColumn, toColumn);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void groupRow(int fromRow, int toRow) {
        int maxLevelRow = -1;
        for (SXSSFRow row : this._rows.subMap(Integer.valueOf(fromRow), Integer.valueOf(toRow + 1)).values()) {
            int level = row.getOutlineLevel() + 1;
            row.setOutlineLevel(level);
            maxLevelRow = Math.max(maxLevelRow, level);
        }
        setWorksheetOutlineLevelRowIfNecessary((short) Math.min(32767, maxLevelRow));
    }

    public void setRowOutlineLevel(int rownum, int level) {
        SXSSFRow row = this._rows.get(Integer.valueOf(rownum));
        row.setOutlineLevel(level);
        setWorksheetOutlineLevelRowIfNecessary((short) Math.min(32767, level));
    }

    private void setWorksheetOutlineLevelRowIfNecessary(short levelRow) {
        CTSheetFormatPr pr;
        CTWorksheet ct = this._sh.getCTWorksheet();
        if (ct.isSetSheetFormatPr()) {
            pr = ct.getSheetFormatPr();
        } else {
            pr = ct.addNewSheetFormatPr();
        }
        if (levelRow > this._sh.getSheetFormatPrOutlineLevelRow()) {
            pr.setOutlineLevelRow(levelRow);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void ungroupRow(int fromRow, int toRow) {
        this._sh.ungroupRow(fromRow, toRow);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setRowGroupCollapsed(int row, boolean collapse) {
        if (collapse) {
            collapseRow(row);
            return;
        }
        throw new IllegalStateException("Unable to expand row: Not Implemented");
    }

    private void collapseRow(int rowIndex) {
        SXSSFRow row = getRow(rowIndex);
        if (row == null) {
            throw new IllegalArgumentException("Invalid row number(" + rowIndex + "). Row does not exist.");
        }
        int startRow = findStartOfRowOutlineGroup(rowIndex);
        int lastRow = writeHidden(row, startRow);
        SXSSFRow lastRowObj = getRow(lastRow);
        if (lastRowObj != null) {
            lastRowObj.setCollapsed(true);
        } else {
            SXSSFRow newRow = createRow(lastRow);
            newRow.setCollapsed(true);
        }
    }

    private int findStartOfRowOutlineGroup(int rowIndex) {
        Row row = getRow(rowIndex);
        int level = row.getOutlineLevel();
        if (level == 0) {
            throw new IllegalArgumentException("Outline level is zero for the row (" + rowIndex + ").");
        }
        int currentRow = rowIndex;
        while (getRow(currentRow) != null) {
            if (getRow(currentRow).getOutlineLevel() < level) {
                return currentRow + 1;
            }
            currentRow--;
        }
        return currentRow + 1;
    }

    private int writeHidden(SXSSFRow xRow, int rowIndex) {
        int level = xRow.getOutlineLevel();
        SXSSFRow currRow = getRow(rowIndex);
        while (currRow != null && currRow.getOutlineLevel() >= level) {
            currRow.setHidden(true);
            rowIndex++;
            currRow = getRow(rowIndex);
        }
        return rowIndex;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDefaultColumnStyle(int column, CellStyle style) {
        this._sh.setDefaultColumnStyle(column, style);
    }

    public void setArbitraryExtraWidth(double arbitraryExtraWidth) {
        if (this._autoSizeColumnTracker == null) {
            throw new IllegalStateException("Cannot trackColumnForAutoSizing because autoSizeColumnTracker failed to initialize (possibly due to fonts not being installed in your OS)");
        }
        this._autoSizeColumnTracker.setArbitraryExtraWidth(arbitraryExtraWidth);
    }

    public double getArbitraryExtraWidth() {
        if (this._autoSizeColumnTracker == null) {
            throw new IllegalStateException("Cannot trackColumnForAutoSizing because autoSizeColumnTracker failed to initialize (possibly due to fonts not being installed in your OS)");
        }
        return this._autoSizeColumnTracker.getArbitraryExtraWidth();
    }

    public void trackColumnForAutoSizing(int column) {
        if (this._autoSizeColumnTracker == null) {
            throw new IllegalStateException("Cannot trackColumnForAutoSizing because autoSizeColumnTracker failed to initialize (possibly due to fonts not being installed in your OS)");
        }
        this._autoSizeColumnTracker.trackColumn(column);
    }

    public void trackColumnsForAutoSizing(Collection<Integer> columns) {
        if (this._autoSizeColumnTracker == null) {
            throw new IllegalStateException("Cannot trackColumnForAutoSizing because autoSizeColumnTracker failed to initialize (possibly due to fonts not being installed in your OS)");
        }
        this._autoSizeColumnTracker.trackColumns(columns);
    }

    public void trackAllColumnsForAutoSizing() {
        if (this._autoSizeColumnTracker == null) {
            throw new IllegalStateException("Cannot trackColumnForAutoSizing because autoSizeColumnTracker failed to initialize (possibly due to fonts not being installed in your OS)");
        }
        this._autoSizeColumnTracker.trackAllColumns();
    }

    public boolean untrackColumnForAutoSizing(int column) {
        return this._autoSizeColumnTracker != null && this._autoSizeColumnTracker.untrackColumn(column);
    }

    public boolean untrackColumnsForAutoSizing(Collection<Integer> columns) {
        return this._autoSizeColumnTracker != null && this._autoSizeColumnTracker.untrackColumns(columns);
    }

    public void untrackAllColumnsForAutoSizing() {
        if (this._autoSizeColumnTracker != null) {
            this._autoSizeColumnTracker.untrackAllColumns();
        }
    }

    public boolean isColumnTrackedForAutoSizing(int column) {
        return this._autoSizeColumnTracker != null && this._autoSizeColumnTracker.isColumnTracked(column);
    }

    public Set<Integer> getTrackedColumnsForAutoSizing() {
        return this._autoSizeColumnTracker == null ? Collections.emptySet() : this._autoSizeColumnTracker.getTrackedColumns();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void autoSizeColumn(int column) {
        autoSizeColumn(column, false);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void autoSizeColumn(int column, boolean useMergedCells) {
        if (this._autoSizeColumnTracker == null) {
            throw new IllegalStateException("Cannot trackColumnForAutoSizing because autoSizeColumnTracker failed to initialize (possibly due to fonts not being installed in your OS)");
        }
        try {
            int flushedWidth = this._autoSizeColumnTracker.getBestFitColumnWidth(column, useMergedCells);
            double w1 = SheetUtil.getColumnWidth(this, column, useMergedCells);
            int activeWidth = (int) ((256.0d * w1) + getArbitraryExtraWidth());
            int bestFitWidth = Math.max(flushedWidth, activeWidth);
            if (bestFitWidth > 0) {
                int width = Math.min(bestFitWidth, MotionEventCompat.ACTION_POINTER_INDEX_MASK);
                setColumnWidth(column, width);
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Could not auto-size column. Make sure the column was tracked prior to auto-sizing the column.", e);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFComment getCellComment(CellAddress ref) {
        return this._sh.getCellComment(ref);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public Map<CellAddress, XSSFComment> getCellComments() {
        return this._sh.getCellComments();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFHyperlink getHyperlink(int row, int column) {
        return this._sh.getHyperlink(row, column);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFHyperlink getHyperlink(CellAddress addr) {
        return this._sh.getHyperlink(addr);
    }

    public void addHyperlink(XSSFHyperlink hyperlink) {
        this._sh.addHyperlink(hyperlink);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public List<XSSFHyperlink> getHyperlinkList() {
        return this._sh.getHyperlinkList();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFDrawing getDrawingPatriarch() {
        return this._sh.getDrawingPatriarch();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public SXSSFDrawing createDrawingPatriarch() {
        return new SXSSFDrawing(getWorkbook(), this._sh.createDrawingPatriarch());
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public SXSSFWorkbook getWorkbook() {
        return this._workbook;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public String getSheetName() {
        return this._sh.getSheetName();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isSelected() {
        return this._sh.isSelected();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public CellRange<? extends Cell> setArrayFormula(String formula, CellRangeAddress range) {
        throw new IllegalStateException("Not Implemented");
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public CellRange<? extends Cell> removeArrayFormula(Cell cell) {
        throw new IllegalStateException("Not Implemented");
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public DataValidationHelper getDataValidationHelper() {
        return this._sh.getDataValidationHelper();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public List<XSSFDataValidation> getDataValidations() {
        return this._sh.getDataValidations();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void addValidationData(DataValidation dataValidation) {
        this._sh.addValidationData(dataValidation);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public AutoFilter setAutoFilter(CellRangeAddress range) {
        return this._sh.setAutoFilter(range);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public SheetConditionalFormatting getSheetConditionalFormatting() {
        return this._sh.getSheetConditionalFormatting();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public CellRangeAddress getRepeatingRows() {
        return this._sh.getRepeatingRows();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public CellRangeAddress getRepeatingColumns() {
        return this._sh.getRepeatingColumns();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setRepeatingRows(CellRangeAddress rowRangeRef) {
        this._sh.setRepeatingRows(rowRangeRef);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setRepeatingColumns(CellRangeAddress columnRangeRef) {
        this._sh.setRepeatingColumns(columnRangeRef);
    }

    public void setRandomAccessWindowSize(int value) {
        if (value == 0 || value < -1) {
            throw new IllegalArgumentException("RandomAccessWindowSize must be either -1 or a positive integer");
        }
        this._randomAccessWindowSize = value;
    }

    public boolean areAllRowsFlushed() {
        return this.allFlushed;
    }

    public int getLastFlushedRowNum() {
        return this.lastFlushedRowNumber;
    }

    public void flushRows(int remaining) throws IOException {
        while (this._rows.size() > remaining) {
            flushOneRow();
        }
        if (remaining == 0) {
            this.allFlushed = true;
        }
    }

    public void flushRows() throws IOException {
        flushRows(0);
    }

    public void flushBufferedData() throws IOException {
        this._writer.flush();
    }

    private void flushOneRow() throws IOException {
        Integer firstRowNum = this._rows.firstKey();
        if (firstRowNum != null) {
            int rowIndex = firstRowNum.intValue();
            SXSSFRow row = this._rows.get(firstRowNum);
            if (this._autoSizeColumnTracker != null) {
                this._autoSizeColumnTracker.updateColumnWidths(row);
            }
            if (this._writer != null) {
                this._writer.writeRow(rowIndex, row);
            }
            this._rows.remove(firstRowNum);
            this.lastFlushedRowNumber = rowIndex;
        }
    }

    public void changeRowNum(SXSSFRow row, int newRowNum) {
        removeRow(row);
        row.setRowNumWithoutUpdatingSheet(newRowNum);
        this._rows.put(Integer.valueOf(newRowNum), row);
    }

    public int getRowNum(SXSSFRow row) {
        return row.getRowNum();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean dispose() throws IOException {
        SheetDataWriter sheetDataWriter;
        boolean dispose;
        try {
            if (!this.allFlushed) {
                flushRows();
            }
            if (sheetDataWriter != null) {
                if (!dispose) {
                    return false;
                }
            }
            return true;
        } finally {
            if (this._writer == null || this._writer.dispose()) {
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int getColumnOutlineLevel(int columnIndex) {
        return this._sh.getColumnOutlineLevel(columnIndex);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public CellAddress getActiveCell() {
        return this._sh.getActiveCell();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setActiveCell(CellAddress address) {
        this._sh.setActiveCell(address);
    }

    public XSSFColor getTabColor() {
        return this._sh.getTabColor();
    }

    public void setTabColor(XSSFColor color) {
        this._sh.setTabColor(color);
    }

    public void enableLocking() {
        safeGetProtectionField().setSheet(true);
    }

    public void disableLocking() {
        safeGetProtectionField().setSheet(false);
    }

    public void lockAutoFilter(boolean enabled) {
        safeGetProtectionField().setAutoFilter(enabled);
    }

    public void lockDeleteColumns(boolean enabled) {
        safeGetProtectionField().setDeleteColumns(enabled);
    }

    public void lockDeleteRows(boolean enabled) {
        safeGetProtectionField().setDeleteRows(enabled);
    }

    public void lockFormatCells(boolean enabled) {
        safeGetProtectionField().setFormatCells(enabled);
    }

    public void lockFormatColumns(boolean enabled) {
        safeGetProtectionField().setFormatColumns(enabled);
    }

    public void lockFormatRows(boolean enabled) {
        safeGetProtectionField().setFormatRows(enabled);
    }

    public void lockInsertColumns(boolean enabled) {
        safeGetProtectionField().setInsertColumns(enabled);
    }

    public void lockInsertHyperlinks(boolean enabled) {
        safeGetProtectionField().setInsertHyperlinks(enabled);
    }

    public void lockInsertRows(boolean enabled) {
        safeGetProtectionField().setInsertRows(enabled);
    }

    public void lockPivotTables(boolean enabled) {
        safeGetProtectionField().setPivotTables(enabled);
    }

    public void lockSort(boolean enabled) {
        safeGetProtectionField().setSort(enabled);
    }

    public void lockObjects(boolean enabled) {
        safeGetProtectionField().setObjects(enabled);
    }

    public void lockScenarios(boolean enabled) {
        safeGetProtectionField().setScenarios(enabled);
    }

    public void lockSelectLockedCells(boolean enabled) {
        safeGetProtectionField().setSelectLockedCells(enabled);
    }

    public void lockSelectUnlockedCells(boolean enabled) {
        safeGetProtectionField().setSelectUnlockedCells(enabled);
    }

    private CTSheetProtection safeGetProtectionField() {
        CTWorksheet ct = this._sh.getCTWorksheet();
        if (!isSheetProtectionEnabled()) {
            return ct.addNewSheetProtection();
        }
        return ct.getSheetProtection();
    }

    boolean isSheetProtectionEnabled() {
        CTWorksheet ct = this._sh.getCTWorksheet();
        return ct.isSetSheetProtection();
    }

    public void setTabColor(int colorIndex) {
        CTWorksheet ct = this._sh.getCTWorksheet();
        CTSheetPr pr = ct.getSheetPr();
        if (pr == null) {
            pr = ct.addNewSheetPr();
        }
        CTColor color = CTColor.Factory.newInstance();
        color.setIndexed(colorIndex);
        pr.setTabColor(color);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    @NotImplemented
    public void shiftColumns(int startColumn, int endColumn, int n) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void trackNewCell(SXSSFCell cell) {
        this.leftMostColumn = Math.min(cell.getColumnIndex(), this.leftMostColumn);
        this.rightMostColumn = Math.max(cell.getColumnIndex(), this.rightMostColumn);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deriveDimension() {
        if (this._workbook.shouldCalculateSheetDimensions()) {
            try {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(getFirstRowNum(), getLastRowNum(), this.leftMostColumn, this.rightMostColumn);
                this._sh.setDimensionOverride(cellRangeAddress);
            } catch (Exception e) {
                LOG.atDebug().log("Failed to set dimension details on sheet", e);
            }
        }
    }
}
