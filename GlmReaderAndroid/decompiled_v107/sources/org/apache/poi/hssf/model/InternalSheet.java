package org.apache.poi.hssf.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.CalcCountRecord;
import org.apache.poi.hssf.record.CalcModeRecord;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.ColumnInfoRecord;
import org.apache.poi.hssf.record.DefaultColWidthRecord;
import org.apache.poi.hssf.record.DefaultRowHeightRecord;
import org.apache.poi.hssf.record.DeltaRecord;
import org.apache.poi.hssf.record.DimensionsRecord;
import org.apache.poi.hssf.record.DrawingRecord;
import org.apache.poi.hssf.record.EOFRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.GridsetRecord;
import org.apache.poi.hssf.record.GutsRecord;
import org.apache.poi.hssf.record.IterationRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.PaneRecord;
import org.apache.poi.hssf.record.PrintGridlinesRecord;
import org.apache.poi.hssf.record.PrintHeadersRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordBase;
import org.apache.poi.hssf.record.RefModeRecord;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.record.SCLRecord;
import org.apache.poi.hssf.record.SaveRecalcRecord;
import org.apache.poi.hssf.record.SelectionRecord;
import org.apache.poi.hssf.record.UncalcedRecord;
import org.apache.poi.hssf.record.WSBoolRecord;
import org.apache.poi.hssf.record.WindowTwoRecord;
import org.apache.poi.hssf.record.aggregates.ColumnInfoRecordsAggregate;
import org.apache.poi.hssf.record.aggregates.ConditionalFormattingTable;
import org.apache.poi.hssf.record.aggregates.DataValidityTable;
import org.apache.poi.hssf.record.aggregates.MergedCellsTable;
import org.apache.poi.hssf.record.aggregates.PageSettingsBlock;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.hssf.record.aggregates.RowRecordsAggregate;
import org.apache.poi.hssf.record.aggregates.WorksheetProtectionBlock;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PaneInformation;
import org.apache.poi.util.Internal;
import org.apache.poi.util.RecordFormatException;

@Internal
/* loaded from: classes10.dex */
public final class InternalSheet {
    public static final short BottomMargin = 3;
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) InternalSheet.class);
    public static final short LeftMargin = 0;
    public static final byte PANE_LOWER_LEFT = 2;
    public static final byte PANE_LOWER_RIGHT = 0;
    public static final byte PANE_UPPER_LEFT = 3;
    public static final byte PANE_UPPER_RIGHT = 1;
    public static final short RightMargin = 1;
    public static final short TopMargin = 2;
    ColumnInfoRecordsAggregate _columnInfos;
    private DataValidityTable _dataValidityTable;
    private DimensionsRecord _dimensions;
    private GutsRecord _gutsRecord;
    protected boolean _isUncalced;
    private PageSettingsBlock _psBlock;
    private final List<RecordBase> _records;
    protected final RowRecordsAggregate _rowsAggregate;
    protected SelectionRecord _selection;
    private ConditionalFormattingTable condFormatting;
    protected DefaultColWidthRecord defaultcolwidth;
    protected DefaultRowHeightRecord defaultrowheight;
    protected GridsetRecord gridset;
    protected PrintGridlinesRecord printGridlines;
    protected PrintHeadersRecord printHeaders;
    private Iterator<RowRecord> rowRecIterator;
    protected WindowTwoRecord windowTwo;
    private final WorksheetProtectionBlock _protectionBlock = new WorksheetProtectionBlock();
    private final MergedCellsTable _mergedCellsTable = new MergedCellsTable();

    public static InternalSheet createSheet(RecordStream rs) {
        return new InternalSheet(rs);
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x01d6, code lost:
    
        if (r11.windowTwo == null) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01da, code lost:
    
        if (r11._dimensions != null) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01dc, code lost:
    
        if (r0 != null) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x01de, code lost:
    
        r0 = new org.apache.poi.hssf.record.aggregates.RowRecordsAggregate();
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x01f0, code lost:
    
        r3 = findFirstRecordLocBySid(org.apache.poi.hssf.record.WindowTwoRecord.sid);
        r11._dimensions = r0.createDimensions();
        r1.add(r3, r11._dimensions);
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x01e5, code lost:
    
        org.apache.poi.hssf.model.InternalSheet.LOGGER.atWarn().log("DIMENSION record not found even though row/cells present");
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x01ff, code lost:
    
        if (r0 != null) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0201, code lost:
    
        r0 = new org.apache.poi.hssf.record.aggregates.RowRecordsAggregate();
        r1.add(r3 + 1, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x020c, code lost:
    
        r11._rowsAggregate = r0;
        org.apache.poi.hssf.model.RecordOrderer.addNewSheetRecord(r1, r11._mergedCellsTable);
        org.apache.poi.hssf.model.RecordOrderer.addNewSheetRecord(r1, r11._protectionBlock);
        org.apache.poi.hssf.model.InternalSheet.LOGGER.atDebug().log("sheet createSheet (existing file) exited");
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0223, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x022b, code lost:
    
        throw new org.apache.poi.util.RecordFormatException("WINDOW2 was not found");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private InternalSheet(org.apache.poi.hssf.model.RecordStream r12) {
        /*
            Method dump skipped, instructions count: 564
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.model.InternalSheet.<init>(org.apache.poi.hssf.model.RecordStream):void");
    }

    private static void spillAggregate(RecordAggregate ra, final List<RecordBase> recs) {
        recs.getClass();
        ra.visitContainedRecords(new RecordAggregate.RecordVisitor() { // from class: org.apache.poi.hssf.model.InternalSheet$$ExternalSyntheticLambda0
            @Override // org.apache.poi.hssf.record.aggregates.RecordAggregate.RecordVisitor
            public final void visitRecord(Record record) {
                recs.add(record);
            }
        });
    }

    /* loaded from: classes10.dex */
    public static class UnsupportedBOFType extends RecordFormatException {
        private final int type;

        protected UnsupportedBOFType(int type) {
            super("BOF not of a supported type, found " + type);
            this.type = type;
        }

        public int getType() {
            return this.type;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class RecordCloner implements RecordAggregate.RecordVisitor {
        private final List<Record> _destList;

        public RecordCloner(List<Record> destList) {
            this._destList = destList;
        }

        @Override // org.apache.poi.hssf.record.aggregates.RecordAggregate.RecordVisitor
        public void visitRecord(Record r) {
            this._destList.add(r.copy());
        }
    }

    public InternalSheet cloneSheet() {
        List<Record> clonedRecords = new ArrayList<>(this._records.size());
        for (RecordBase rb : this._records) {
            if (rb instanceof RecordAggregate) {
                ((RecordAggregate) rb).visitContainedRecords(new RecordCloner(clonedRecords));
            } else {
                if (rb instanceof EscherAggregate) {
                    rb = new DrawingRecord();
                }
                Record rec = ((Record) rb).copy();
                clonedRecords.add(rec);
            }
        }
        return createSheet(new RecordStream(clonedRecords, 0));
    }

    public static InternalSheet createSheet() {
        return new InternalSheet();
    }

    private InternalSheet() {
        this.defaultcolwidth = new DefaultColWidthRecord();
        this.defaultrowheight = new DefaultRowHeightRecord();
        List<RecordBase> records = new ArrayList<>(32);
        LOGGER.atDebug().log("Sheet createsheet from scratch called");
        records.add(createBOF());
        records.add(createCalcMode());
        records.add(createCalcCount());
        records.add(createRefMode());
        records.add(createIteration());
        records.add(createDelta());
        records.add(createSaveRecalc());
        this.printHeaders = createPrintHeaders();
        records.add(this.printHeaders);
        this.printGridlines = createPrintGridlines();
        records.add(this.printGridlines);
        this.gridset = createGridset();
        records.add(this.gridset);
        this._gutsRecord = createGuts();
        records.add(this._gutsRecord);
        this.defaultrowheight = createDefaultRowHeight();
        records.add(this.defaultrowheight);
        records.add(createWSBool());
        this._psBlock = new PageSettingsBlock();
        records.add(this._psBlock);
        records.add(this._protectionBlock);
        this.defaultcolwidth = createDefaultColWidth();
        records.add(this.defaultcolwidth);
        ColumnInfoRecordsAggregate columns = new ColumnInfoRecordsAggregate();
        records.add(columns);
        this._columnInfos = columns;
        this._dimensions = createDimensions();
        records.add(this._dimensions);
        this._rowsAggregate = new RowRecordsAggregate();
        records.add(this._rowsAggregate);
        WindowTwoRecord createWindowTwo = createWindowTwo();
        this.windowTwo = createWindowTwo;
        records.add(createWindowTwo);
        this._selection = createSelection();
        records.add(this._selection);
        records.add(this._mergedCellsTable);
        records.add(EOFRecord.instance);
        this._records = records;
        LOGGER.atDebug().log("Sheet createsheet from scratch exit");
    }

    public RowRecordsAggregate getRowsAggregate() {
        return this._rowsAggregate;
    }

    private MergedCellsTable getMergedRecords() {
        return this._mergedCellsTable;
    }

    public void updateFormulasAfterCellShift(FormulaShifter shifter, int externSheetIndex) {
        getRowsAggregate().updateFormulasAfterRowShift(shifter, externSheetIndex);
        if (this.condFormatting != null) {
            getConditionalFormattingTable().updateFormulasAfterCellShift(shifter, externSheetIndex);
        }
    }

    public int addMergedRegion(int rowFrom, int colFrom, int rowTo, int colTo) {
        if (rowTo < rowFrom) {
            throw new IllegalArgumentException("The 'to' row (" + rowTo + ") must not be less than the 'from' row (" + rowFrom + ")");
        }
        if (colTo < colFrom) {
            throw new IllegalArgumentException("The 'to' col (" + colTo + ") must not be less than the 'from' col (" + colFrom + ")");
        }
        MergedCellsTable mrt = getMergedRecords();
        mrt.addArea(rowFrom, colFrom, rowTo, colTo);
        return mrt.getNumberOfMergedRegions() - 1;
    }

    public void removeMergedRegion(int index) {
        MergedCellsTable mrt = getMergedRecords();
        if (index >= mrt.getNumberOfMergedRegions()) {
            return;
        }
        mrt.remove(index);
    }

    public CellRangeAddress getMergedRegionAt(int index) {
        MergedCellsTable mrt = getMergedRecords();
        if (index >= mrt.getNumberOfMergedRegions()) {
            return null;
        }
        return mrt.get(index);
    }

    public int getNumMergedRegions() {
        return getMergedRecords().getNumberOfMergedRegions();
    }

    public ConditionalFormattingTable getConditionalFormattingTable() {
        if (this.condFormatting == null) {
            this.condFormatting = new ConditionalFormattingTable();
            RecordOrderer.addNewSheetRecord(this._records, this.condFormatting);
        }
        return this.condFormatting;
    }

    public void setDimensions(int firstrow, short firstcol, int lastrow, short lastcol) {
        LOGGER.atDebug().log("Sheet.setDimensions");
        LOGGER.atDebug().log("firstrow: {} firstcol: {} lastrow: {} lastcol: {}", Integer.valueOf(firstrow), Short.valueOf(firstcol), Integer.valueOf(lastrow), Short.valueOf(lastcol));
        this._dimensions.setFirstCol(firstcol);
        this._dimensions.setFirstRow(firstrow);
        this._dimensions.setLastCol(lastcol);
        this._dimensions.setLastRow(lastrow);
        LOGGER.atDebug().log("Sheet.setDimensions exiting");
    }

    public void visitContainedRecords(RecordAggregate.RecordVisitor rv, int offset) {
        RecordAggregate.PositionTrackingVisitor ptv = new RecordAggregate.PositionTrackingVisitor(rv, offset);
        boolean haveSerializedIndex = false;
        for (int k = 0; k < this._records.size(); k++) {
            RecordBase recordBase = this._records.get(k);
            if (recordBase instanceof RecordAggregate) {
                RecordAggregate agg = (RecordAggregate) recordBase;
                agg.visitContainedRecords(ptv);
            } else if (recordBase instanceof Record) {
                ptv.visitRecord((Record) recordBase);
            }
            if ((recordBase instanceof BOFRecord) && !haveSerializedIndex) {
                haveSerializedIndex = true;
                if (this._isUncalced) {
                    ptv.visitRecord(new UncalcedRecord());
                }
                if (this._rowsAggregate != null) {
                    int initRecsSize = getSizeOfInitialSheetRecords(k);
                    int currentPos = ptv.getPosition();
                    ptv.visitRecord(this._rowsAggregate.createIndexRecord(currentPos, initRecsSize));
                }
            }
        }
    }

    private int getSizeOfInitialSheetRecords(int bofRecordIndex) {
        int result = 0;
        for (int j = bofRecordIndex + 1; j < this._records.size(); j++) {
            RecordBase tmpRec = this._records.get(j);
            if (tmpRec instanceof RowRecordsAggregate) {
                break;
            }
            result += tmpRec.getRecordSize();
        }
        if (this._isUncalced) {
            return result + UncalcedRecord.getStaticRecordSize();
        }
        return result;
    }

    public void addValueRecord(int row, CellValueRecordInterface col) {
        LOGGER.atDebug().log("add value record row{}", Unbox.box(row));
        DimensionsRecord d = this._dimensions;
        if (col.getColumn() >= d.getLastCol()) {
            d.setLastCol((short) (col.getColumn() + 1));
        }
        if (col.getColumn() < d.getFirstCol()) {
            d.setFirstCol(col.getColumn());
        }
        this._rowsAggregate.insertCell(col);
    }

    public void removeValueRecord(int row, CellValueRecordInterface col) {
        LOGGER.atDebug().log("remove value record row {}", Unbox.box(row));
        this._rowsAggregate.removeCell(col);
    }

    public void replaceValueRecord(CellValueRecordInterface newval) {
        LOGGER.atDebug().log("replaceValueRecord ");
        this._rowsAggregate.removeCell(newval);
        this._rowsAggregate.insertCell(newval);
    }

    public void addRow(RowRecord row) {
        LOGGER.atDebug().log("addRow ");
        DimensionsRecord d = this._dimensions;
        if (row.getRowNumber() >= d.getLastRow()) {
            d.setLastRow(row.getRowNumber() + 1);
        }
        if (row.getRowNumber() < d.getFirstRow()) {
            d.setFirstRow(row.getRowNumber());
        }
        RowRecord existingRow = this._rowsAggregate.getRow(row.getRowNumber());
        if (existingRow != null) {
            this._rowsAggregate.removeRow(existingRow);
        }
        this._rowsAggregate.insertRow(row);
        LOGGER.atDebug().log("exit addRow");
    }

    public void removeRow(RowRecord row) {
        this._rowsAggregate.removeRow(row);
    }

    public Iterator<CellValueRecordInterface> getCellValueIterator() {
        return this._rowsAggregate.getCellValueIterator();
    }

    public RowRecord getNextRow() {
        if (this.rowRecIterator == null) {
            this.rowRecIterator = this._rowsAggregate.getIterator();
        }
        if (!this.rowRecIterator.hasNext()) {
            return null;
        }
        return this.rowRecIterator.next();
    }

    public RowRecord getRow(int rownum) {
        return this._rowsAggregate.getRow(rownum);
    }

    static BOFRecord createBOF() {
        BOFRecord retval = new BOFRecord();
        retval.setVersion(BOFRecord.VERSION);
        retval.setType(16);
        retval.setBuild(3515);
        retval.setBuildYear(BOFRecord.BUILD_YEAR);
        retval.setHistoryBitMask(193);
        retval.setRequiredVersion(6);
        return retval;
    }

    private static CalcModeRecord createCalcMode() {
        CalcModeRecord retval = new CalcModeRecord();
        retval.setCalcMode((short) 1);
        return retval;
    }

    private static CalcCountRecord createCalcCount() {
        CalcCountRecord retval = new CalcCountRecord();
        retval.setIterations((short) 100);
        return retval;
    }

    private static RefModeRecord createRefMode() {
        RefModeRecord retval = new RefModeRecord();
        retval.setMode((short) 1);
        return retval;
    }

    private static IterationRecord createIteration() {
        return new IterationRecord(false);
    }

    private static DeltaRecord createDelta() {
        return new DeltaRecord(0.001d);
    }

    private static SaveRecalcRecord createSaveRecalc() {
        SaveRecalcRecord retval = new SaveRecalcRecord();
        retval.setRecalc(true);
        return retval;
    }

    private static PrintHeadersRecord createPrintHeaders() {
        PrintHeadersRecord retval = new PrintHeadersRecord();
        retval.setPrintHeaders(false);
        return retval;
    }

    private static PrintGridlinesRecord createPrintGridlines() {
        PrintGridlinesRecord retval = new PrintGridlinesRecord();
        retval.setPrintGridlines(false);
        return retval;
    }

    private static GridsetRecord createGridset() {
        GridsetRecord retval = new GridsetRecord();
        retval.setGridset(true);
        return retval;
    }

    private static GutsRecord createGuts() {
        GutsRecord retval = new GutsRecord();
        retval.setLeftRowGutter((short) 0);
        retval.setTopColGutter((short) 0);
        retval.setRowLevelMax((short) 0);
        retval.setColLevelMax((short) 0);
        return retval;
    }

    private GutsRecord getGutsRecord() {
        if (this._gutsRecord == null) {
            GutsRecord result = createGuts();
            RecordOrderer.addNewSheetRecord(this._records, result);
            this._gutsRecord = result;
        }
        return this._gutsRecord;
    }

    private static DefaultRowHeightRecord createDefaultRowHeight() {
        DefaultRowHeightRecord retval = new DefaultRowHeightRecord();
        retval.setOptionFlags((short) 0);
        retval.setRowHeight((short) 255);
        return retval;
    }

    private static WSBoolRecord createWSBool() {
        WSBoolRecord retval = new WSBoolRecord();
        retval.setWSBool1((byte) 4);
        retval.setWSBool2((byte) -63);
        return retval;
    }

    private static DefaultColWidthRecord createDefaultColWidth() {
        DefaultColWidthRecord retval = new DefaultColWidthRecord();
        retval.setColWidth(8);
        return retval;
    }

    public int getDefaultColumnWidth() {
        return this.defaultcolwidth.getColWidth();
    }

    public boolean isGridsPrinted() {
        if (this.gridset == null) {
            this.gridset = createGridset();
            int loc = findFirstRecordLocBySid((short) 10);
            this._records.add(loc, this.gridset);
        }
        return !this.gridset.getGridset();
    }

    public void setGridsPrinted(boolean value) {
        this.gridset.setGridset(!value);
    }

    public void setDefaultColumnWidth(int dcw) {
        this.defaultcolwidth.setColWidth(dcw);
    }

    public void setDefaultRowHeight(short dch) {
        this.defaultrowheight.setRowHeight(dch);
        this.defaultrowheight.setOptionFlags((short) 1);
    }

    public short getDefaultRowHeight() {
        return this.defaultrowheight.getRowHeight();
    }

    public int getColumnWidth(int columnIndex) {
        ColumnInfoRecord ci = this._columnInfos.findColumnInfo(columnIndex);
        if (ci != null) {
            return ci.getColumnWidth();
        }
        return this.defaultcolwidth.getColWidth() * 256;
    }

    public short getXFIndexForColAt(short columnIndex) {
        ColumnInfoRecord ci = this._columnInfos.findColumnInfo(columnIndex);
        if (ci != null) {
            return (short) ci.getXFIndex();
        }
        return (short) 15;
    }

    public void setColumnWidth(int column, int width) {
        if (width > 65280) {
            throw new IllegalArgumentException("The maximum column width for an individual cell is 255 characters.");
        }
        setColumn(column, null, Integer.valueOf(width), null, null, null);
    }

    public boolean isColumnHidden(int columnIndex) {
        ColumnInfoRecord cir = this._columnInfos.findColumnInfo(columnIndex);
        if (cir == null) {
            return false;
        }
        return cir.getHidden();
    }

    public void setColumnHidden(int column, boolean hidden) {
        setColumn(column, null, null, null, Boolean.valueOf(hidden), null);
    }

    public void setDefaultColumnStyle(int column, int styleIndex) {
        setColumn(column, Short.valueOf((short) styleIndex), null, null, null, null);
    }

    private void setColumn(int column, Short xfStyle, Integer width, Integer level, Boolean hidden, Boolean collapsed) {
        this._columnInfos.setColumn(column, xfStyle, width, level, hidden, collapsed);
    }

    public void groupColumnRange(int fromColumn, int toColumn, boolean indent) {
        this._columnInfos.groupColumnRange(fromColumn, toColumn, indent);
        int maxLevel = this._columnInfos.getMaxOutlineLevel();
        GutsRecord guts = getGutsRecord();
        guts.setColLevelMax((short) (maxLevel + 1));
        if (maxLevel == 0) {
            guts.setTopColGutter((short) 0);
        } else {
            guts.setTopColGutter((short) (((maxLevel - 1) * 12) + 29));
        }
    }

    private static DimensionsRecord createDimensions() {
        DimensionsRecord retval = new DimensionsRecord();
        retval.setFirstCol((short) 0);
        retval.setLastRow(1);
        retval.setFirstRow(0);
        retval.setLastCol((short) 1);
        return retval;
    }

    private static WindowTwoRecord createWindowTwo() {
        WindowTwoRecord retval = new WindowTwoRecord();
        retval.setOptions((short) 1718);
        retval.setTopRow((short) 0);
        retval.setLeftCol((short) 0);
        retval.setHeaderColor(64);
        retval.setPageBreakZoom((short) 0);
        retval.setNormalZoom((short) 0);
        return retval;
    }

    private static SelectionRecord createSelection() {
        return new SelectionRecord(0, 0);
    }

    public short getTopRow() {
        if (this.windowTwo == null) {
            return (short) 0;
        }
        return this.windowTwo.getTopRow();
    }

    public void setTopRow(short topRow) {
        if (this.windowTwo != null) {
            this.windowTwo.setTopRow(topRow);
        }
    }

    public void setLeftCol(short leftCol) {
        if (this.windowTwo != null) {
            this.windowTwo.setLeftCol(leftCol);
        }
    }

    public short getLeftCol() {
        if (this.windowTwo == null) {
            return (short) 0;
        }
        return this.windowTwo.getLeftCol();
    }

    public int getActiveCellRow() {
        if (this._selection == null) {
            return 0;
        }
        return this._selection.getActiveCellRow();
    }

    public void setActiveCellRow(int row) {
        if (this._selection != null) {
            this._selection.setActiveCellRow(row);
        }
    }

    public short getActiveCellCol() {
        if (this._selection == null) {
            return (short) 0;
        }
        return (short) this._selection.getActiveCellCol();
    }

    public void setActiveCellCol(short col) {
        if (this._selection != null) {
            this._selection.setActiveCellCol(col);
        }
    }

    public List<RecordBase> getRecords() {
        return this._records;
    }

    public GridsetRecord getGridsetRecord() {
        return this.gridset;
    }

    public Record findFirstRecordBySid(short sid) {
        int ix = findFirstRecordLocBySid(sid);
        if (ix < 0) {
            return null;
        }
        return (Record) this._records.get(ix);
    }

    public void setSCLRecord(SCLRecord sclRecord) {
        int oldRecordLoc = findFirstRecordLocBySid((short) 160);
        if (oldRecordLoc == -1) {
            int windowRecordLoc = findFirstRecordLocBySid(WindowTwoRecord.sid);
            this._records.add(windowRecordLoc + 1, sclRecord);
        } else {
            this._records.set(oldRecordLoc, sclRecord);
        }
    }

    public int findFirstRecordLocBySid(short sid) {
        int max = this._records.size();
        for (int i = 0; i < max; i++) {
            Object rb = this._records.get(i);
            if (rb instanceof Record) {
                Record record = (Record) rb;
                if (record.getSid() == sid) {
                    return i;
                }
            }
        }
        return -1;
    }

    public WindowTwoRecord getWindowTwo() {
        return this.windowTwo;
    }

    public PrintGridlinesRecord getPrintGridlines() {
        return this.printGridlines;
    }

    public void setPrintGridlines(PrintGridlinesRecord newPrintGridlines) {
        this.printGridlines = newPrintGridlines;
    }

    public PrintHeadersRecord getPrintHeaders() {
        return this.printHeaders;
    }

    public void setPrintHeaders(PrintHeadersRecord newPrintHeaders) {
        this.printHeaders = newPrintHeaders;
    }

    public void setSelected(boolean sel) {
        this.windowTwo.setSelected(sel);
    }

    public void createFreezePane(int colSplit, int rowSplit, int topRow, int leftmostColumn) {
        int paneLoc = findFirstRecordLocBySid((short) 65);
        if (paneLoc != -1) {
            this._records.remove(paneLoc);
        }
        if (colSplit == 0 && rowSplit == 0) {
            this.windowTwo.setFreezePanes(false);
            this.windowTwo.setFreezePanesNoSplit(false);
            SelectionRecord sel = (SelectionRecord) findFirstRecordBySid((short) 29);
            if (sel != null) {
                sel.setPane((byte) 3);
                return;
            }
            return;
        }
        int loc = findFirstRecordLocBySid(WindowTwoRecord.sid);
        PaneRecord pane = new PaneRecord();
        pane.setX((short) colSplit);
        pane.setY((short) rowSplit);
        pane.setTopRow((short) topRow);
        pane.setLeftColumn((short) leftmostColumn);
        if (rowSplit == 0) {
            pane.setTopRow((short) 0);
            pane.setActivePane((short) 1);
        } else if (colSplit == 0) {
            pane.setLeftColumn((short) 0);
            pane.setActivePane((short) 2);
        } else {
            pane.setActivePane((short) 0);
        }
        this._records.add(loc + 1, pane);
        this.windowTwo.setFreezePanes(true);
        this.windowTwo.setFreezePanesNoSplit(true);
        SelectionRecord sel2 = (SelectionRecord) findFirstRecordBySid((short) 29);
        if (sel2 != null) {
            sel2.setPane((byte) pane.getActivePane());
        }
    }

    public void createSplitPane(int xSplitPos, int ySplitPos, int topRow, int leftmostColumn, int activePane) {
        int paneLoc = findFirstRecordLocBySid((short) 65);
        if (paneLoc != -1) {
            this._records.remove(paneLoc);
        }
        int loc = findFirstRecordLocBySid(WindowTwoRecord.sid);
        PaneRecord r = new PaneRecord();
        r.setX((short) xSplitPos);
        r.setY((short) ySplitPos);
        r.setTopRow((short) topRow);
        r.setLeftColumn((short) leftmostColumn);
        r.setActivePane((short) activePane);
        this._records.add(loc + 1, r);
        this.windowTwo.setFreezePanes(false);
        this.windowTwo.setFreezePanesNoSplit(false);
        SelectionRecord sel = (SelectionRecord) findFirstRecordBySid((short) 29);
        if (sel != null) {
            sel.setPane((byte) 0);
        }
    }

    public PaneInformation getPaneInformation() {
        PaneRecord rec = (PaneRecord) findFirstRecordBySid((short) 65);
        if (rec == null) {
            return null;
        }
        return new PaneInformation(rec.getX(), rec.getY(), rec.getTopRow(), rec.getLeftColumn(), (byte) rec.getActivePane(), this.windowTwo.getFreezePanes());
    }

    public SelectionRecord getSelection() {
        return this._selection;
    }

    public void setSelection(SelectionRecord selection) {
        this._selection = selection;
    }

    public WorksheetProtectionBlock getProtectionBlock() {
        return this._protectionBlock;
    }

    public void setDisplayGridlines(boolean show) {
        this.windowTwo.setDisplayGridlines(show);
    }

    public boolean isDisplayGridlines() {
        return this.windowTwo.getDisplayGridlines();
    }

    public void setDisplayFormulas(boolean show) {
        this.windowTwo.setDisplayFormulas(show);
    }

    public boolean isDisplayFormulas() {
        return this.windowTwo.getDisplayFormulas();
    }

    public void setDisplayRowColHeadings(boolean show) {
        this.windowTwo.setDisplayRowColHeadings(show);
    }

    public boolean isDisplayRowColHeadings() {
        return this.windowTwo.getDisplayRowColHeadings();
    }

    public void setPrintRowColHeadings(boolean show) {
        this.windowTwo.setDisplayRowColHeadings(show);
    }

    public boolean isPrintRowColHeadings() {
        return this.windowTwo.getDisplayRowColHeadings();
    }

    public boolean getUncalced() {
        return this._isUncalced;
    }

    public void setUncalced(boolean uncalced) {
        this._isUncalced = uncalced;
    }

    public int aggregateDrawingRecords(DrawingManager2 drawingManager, boolean createIfMissing) {
        int loc = findFirstRecordLocBySid((short) 236);
        boolean noDrawingRecordsFound = loc == -1;
        if (noDrawingRecordsFound) {
            if (!createIfMissing) {
                return -1;
            }
            EscherAggregate aggregate = new EscherAggregate(true);
            int loc2 = findFirstRecordLocBySid(EscherAggregate.sid);
            if (loc2 == -1) {
                loc2 = findFirstRecordLocBySid(WindowTwoRecord.sid);
            } else {
                getRecords().remove(loc2);
            }
            getRecords().add(loc2, aggregate);
            return loc2;
        }
        List<RecordBase> records = getRecords();
        EscherAggregate.createAggregate(records, loc);
        return loc;
    }

    public void preSerialize() {
        for (RecordBase r : getRecords()) {
            if (r instanceof EscherAggregate) {
                r.getRecordSize();
            }
        }
    }

    public PageSettingsBlock getPageSettings() {
        if (this._psBlock == null) {
            this._psBlock = new PageSettingsBlock();
            RecordOrderer.addNewSheetRecord(this._records, this._psBlock);
        }
        return this._psBlock;
    }

    public void setColumnGroupCollapsed(int columnNumber, boolean collapsed) {
        if (collapsed) {
            this._columnInfos.collapseColumn(columnNumber);
        } else {
            this._columnInfos.expandColumn(columnNumber);
        }
    }

    public void groupRowRange(int fromRow, int toRow, boolean indent) {
        for (int rowNum = fromRow; rowNum <= toRow; rowNum++) {
            RowRecord row = getRow(rowNum);
            if (row == null) {
                row = RowRecordsAggregate.createRow(rowNum);
                addRow(row);
            }
            int level = row.getOutlineLevel();
            row.setOutlineLevel((short) Math.min(7, Math.max(0, indent ? level + 1 : level - 1)));
        }
        recalcRowGutter();
    }

    private void recalcRowGutter() {
        int maxLevel = 0;
        Iterator<RowRecord> iterator = this._rowsAggregate.getIterator();
        while (iterator.hasNext()) {
            RowRecord rowRecord = iterator.next();
            maxLevel = Math.max((int) rowRecord.getOutlineLevel(), maxLevel);
        }
        GutsRecord guts = getGutsRecord();
        guts.setRowLevelMax((short) (maxLevel + 1));
        guts.setLeftRowGutter((short) ((maxLevel * 12) + 29));
    }

    public DataValidityTable getOrCreateDataValidityTable() {
        if (this._dataValidityTable == null) {
            DataValidityTable result = new DataValidityTable();
            RecordOrderer.addNewSheetRecord(this._records, result);
            this._dataValidityTable = result;
        }
        return this._dataValidityTable;
    }

    public NoteRecord[] getNoteRecords() {
        List<NoteRecord> temp = new ArrayList<>();
        for (int i = this._records.size() - 1; i >= 0; i--) {
            RecordBase rec = this._records.get(i);
            if (rec instanceof NoteRecord) {
                temp.add((NoteRecord) rec);
            }
        }
        int i2 = temp.size();
        if (i2 < 1) {
            return NoteRecord.EMPTY_ARRAY;
        }
        NoteRecord[] result = new NoteRecord[temp.size()];
        temp.toArray(result);
        return result;
    }

    public int getColumnOutlineLevel(int columnIndex) {
        return this._columnInfos.getOutlineLevel(columnIndex);
    }

    public int getMinColumnIndex() {
        return this._columnInfos.getMinColumnIndex();
    }

    public int getMaxColumnIndex() {
        return this._columnInfos.getMaxColumnIndex();
    }
}
