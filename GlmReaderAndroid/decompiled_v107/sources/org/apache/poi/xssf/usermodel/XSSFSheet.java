package org.apache.poi.xssf.usermodel;

import androidx.core.view.MotionEventCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Spliterator;
import java.util.TreeMap;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.PartAlreadyExistsException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyContext;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.IgnoredErrorType;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.PageMargin;
import org.apache.poi.ss.usermodel.PaneType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.PaneInformation;
import org.apache.poi.ss.util.SSCellRange;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.model.Comments;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.helpers.ColumnHelper;
import org.apache.poi.xssf.usermodel.helpers.XSSFColumnShifter;
import org.apache.poi.xssf.usermodel.helpers.XSSFIgnoredErrorHelper;
import org.apache.poi.xssf.usermodel.helpers.XSSFPasswordHelper;
import org.apache.poi.xssf.usermodel.helpers.XSSFRowShifter;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.NameUtil;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBreak;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCols;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidations;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredError;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredErrors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMergeCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMergeCells;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageBreak;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetUpPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetData;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetDimension;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTablePart;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableParts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheetSource;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCalcMode;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellFormulaType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPane;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPaneState;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.WorksheetDocument;

/* loaded from: classes10.dex */
public class XSSFSheet extends POIXMLDocumentPart implements Sheet, OoxmlSheetExtensions {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final double DEFAULT_MARGIN_BOTTOM = 0.75d;
    private static final double DEFAULT_MARGIN_FOOTER = 0.3d;
    private static final double DEFAULT_MARGIN_HEADER = 0.3d;
    private static final double DEFAULT_MARGIN_LEFT = 0.7d;
    private static final double DEFAULT_MARGIN_RIGHT = 0.7d;
    private static final double DEFAULT_MARGIN_TOP = 0.75d;
    private static final double DEFAULT_ROW_HEIGHT = 15.0d;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSSFSheet.class);
    private final SortedMap<Integer, XSSFRow> _rows;
    private double arbitraryExtraWidth;
    private List<CellRangeAddress> arrayFormulas;
    private ColumnHelper columnHelper;
    private final XSSFDataValidationHelper dataValidationHelper;
    private CellRangeAddress dimensionOverride;
    private List<XSSFHyperlink> hyperlinks;
    private Map<Integer, CTCellFormula> sharedFormulas;
    protected CTSheet sheet;
    private Comments sheetComments;
    private SortedMap<String, XSSFTable> tables;
    protected CTWorksheet worksheet;
    private XSSFVMLDrawing xssfvmlDrawing;

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFSheet() {
        this._rows = new TreeMap();
        this.arbitraryExtraWidth = 0.0d;
        this.dataValidationHelper = new XSSFDataValidationHelper(this);
        onDocumentCreate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFSheet(PackagePart part) {
        super(part);
        this._rows = new TreeMap();
        this.arbitraryExtraWidth = 0.0d;
        this.dataValidationHelper = new XSSFDataValidationHelper(this);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFWorkbook getWorkbook() {
        return (XSSFWorkbook) getParent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() {
        try {
            InputStream stream = getPackagePart().getInputStream();
            try {
                read(stream);
                if (stream != null) {
                    stream.close();
                }
            } finally {
            }
        } catch (IOException | ArithmeticException e) {
            throw new POIXMLException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void read(InputStream is) throws IOException {
        try {
            this.worksheet = WorksheetDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS).getWorksheet();
            this.columnHelper = new ColumnHelper(this.worksheet);
            initRows(this.worksheet);
            for (POIXMLDocumentPart.RelationPart rp : getRelationParts()) {
                Object documentPart = rp.getDocumentPart();
                if (documentPart instanceof Comments) {
                    this.sheetComments = (Comments) documentPart;
                    this.sheetComments.setSheet(this);
                }
                if (documentPart instanceof XSSFTable) {
                    this.tables.put(rp.getRelationship().getId(), (XSSFTable) documentPart);
                }
                if (documentPart instanceof XSSFPivotTable) {
                    getWorkbook().addPivotTable((XSSFPivotTable) documentPart);
                }
            }
            initHyperlinks();
        } catch (XmlException e) {
            throw new POIXMLException(e);
        }
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void onDocumentCreate() {
        this.worksheet = newSheet();
        initRows(this.worksheet);
        this.columnHelper = new ColumnHelper(this.worksheet);
        this.hyperlinks = new ArrayList();
    }

    private void initRows(CTWorksheet worksheetParam) {
        if (worksheetParam.getSheetData() == null || worksheetParam.getSheetData().getRowArray() == null) {
            throw new IllegalArgumentException("Had empty sheet data when initializing the sheet");
        }
        this._rows.clear();
        this.tables = new TreeMap();
        this.sharedFormulas = new HashMap();
        this.arrayFormulas = new ArrayList();
        for (CTRow row : worksheetParam.getSheetData().getRowArray()) {
            XSSFRow r = new XSSFRow(row, this);
            Integer rownumI = Integer.valueOf(r.getRowNum());
            this._rows.put(rownumI, r);
        }
    }

    private void initHyperlinks() {
        this.hyperlinks = new ArrayList();
        if (!this.worksheet.isSetHyperlinks()) {
            return;
        }
        try {
            PackageRelationshipCollection hyperRels = getPackagePart().getRelationshipsByType(XSSFRelation.SHEET_HYPERLINKS.getRelation());
            for (CTHyperlink hyperlink : this.worksheet.getHyperlinks().getHyperlinkArray()) {
                PackageRelationship hyperRel = null;
                if (hyperlink.getId() != null) {
                    hyperRel = hyperRels.getRelationshipByID(hyperlink.getId());
                }
                this.hyperlinks.add(new XSSFHyperlink(hyperlink, hyperRel));
            }
        } catch (InvalidFormatException e) {
            throw new POIXMLException(e);
        }
    }

    private static CTWorksheet newSheet() {
        CTWorksheet worksheet = CTWorksheet.Factory.newInstance();
        CTSheetFormatPr ctFormat = worksheet.addNewSheetFormatPr();
        ctFormat.setDefaultRowHeight(DEFAULT_ROW_HEIGHT);
        CTSheetView ctView = worksheet.addNewSheetViews().addNewSheetView();
        ctView.setWorkbookViewId(0L);
        worksheet.addNewDimension().setRef("A1");
        worksheet.addNewSheetData();
        CTPageMargins ctMargins = worksheet.addNewPageMargins();
        ctMargins.setBottom(0.75d);
        ctMargins.setFooter(0.3d);
        ctMargins.setHeader(0.3d);
        ctMargins.setLeft(0.7d);
        ctMargins.setRight(0.7d);
        ctMargins.setTop(0.75d);
        return worksheet;
    }

    @Internal
    public CTWorksheet getCTWorksheet() {
        return this.worksheet;
    }

    public ColumnHelper getColumnHelper() {
        return this.columnHelper;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public String getSheetName() {
        return this.sheet.getName();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int addMergedRegion(CellRangeAddress region) {
        return addMergedRegion(region, true);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int addMergedRegionUnsafe(CellRangeAddress region) {
        return addMergedRegion(region, false);
    }

    private int addMergedRegion(CellRangeAddress region, boolean validate) {
        long count;
        if (region.getNumberOfCells() < 2) {
            throw new IllegalArgumentException("Merged region " + region.formatAsString() + " must contain 2 or more cells");
        }
        region.validate(SpreadsheetVersion.EXCEL2007);
        if (validate) {
            validateArrayFormulas(region);
            validateMergedRegions(region);
        }
        CTMergeCells ctMergeCells = this.worksheet.isSetMergeCells() ? this.worksheet.getMergeCells() : this.worksheet.addNewMergeCells();
        CTMergeCell ctMergeCell = ctMergeCells.addNewMergeCell();
        ctMergeCell.setRef(region.formatAsString());
        long count2 = ctMergeCells.getCount();
        if (count2 == 0) {
            count = ctMergeCells.sizeOfMergeCellArray();
        } else {
            count = count2 + 1;
        }
        ctMergeCells.setCount(count);
        return Math.toIntExact(count - 1);
    }

    private void validateArrayFormulas(CellRangeAddress region) {
        int firstRow = region.getFirstRow();
        int firstColumn = region.getFirstColumn();
        int lastRow = region.getLastRow();
        int lastColumn = region.getLastColumn();
        for (int rowIn = firstRow; rowIn <= lastRow; rowIn++) {
            XSSFRow row = getRow(rowIn);
            if (row != null) {
                for (int colIn = firstColumn; colIn <= lastColumn; colIn++) {
                    XSSFCell cell = row.getCell(colIn);
                    if (cell != null && cell.isPartOfArrayFormulaGroup()) {
                        CellRangeAddress arrayRange = cell.getArrayFormulaRange();
                        if (arrayRange.getNumberOfCells() > 1 && region.intersects(arrayRange)) {
                            String msg = "The range " + region.formatAsString() + " intersects with a multi-cell array formula. You cannot merge cells of an array.";
                            throw new IllegalStateException(msg);
                        }
                    }
                }
            }
        }
    }

    private void checkForMergedRegionsIntersectingArrayFormulas() {
        for (CellRangeAddress region : getMergedRegions()) {
            validateArrayFormulas(region);
        }
    }

    private void validateMergedRegions(CellRangeAddress candidateRegion) {
        for (CellRangeAddress existingRegion : getMergedRegions()) {
            if (existingRegion.intersects(candidateRegion)) {
                throw new IllegalStateException("Cannot add merged region " + candidateRegion.formatAsString() + " to sheet because it overlaps with an existing merged region (" + existingRegion.formatAsString() + ").");
            }
        }
    }

    private void checkForIntersectingMergedRegions() {
        List<CellRangeAddress> regions = getMergedRegions();
        int size = regions.size();
        for (int i = 0; i < size; i++) {
            CellRangeAddress region = regions.get(i);
            for (CellRangeAddress other : regions.subList(i + 1, regions.size())) {
                if (region.intersects(other)) {
                    String msg = "The range " + region.formatAsString() + " intersects with another merged region " + other.formatAsString() + " in this sheet";
                    throw new IllegalStateException(msg);
                }
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void validateMergedRegions() {
        checkForMergedRegionsIntersectingArrayFormulas();
        checkForIntersectingMergedRegions();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void autoSizeColumn(int column) {
        autoSizeColumn(column, false);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void autoSizeColumn(int column, boolean useMergedCells) {
        double width = SheetUtil.getColumnWidth(this, column, useMergedCells);
        if (width != -1.0d) {
            double width2 = (width * 256.0d) + this.arbitraryExtraWidth;
            if (width2 > MotionEventCompat.ACTION_POINTER_INDEX_MASK) {
                width2 = MotionEventCompat.ACTION_POINTER_INDEX_MASK;
            }
            setColumnWidth(column, Math.toIntExact(Math.round(width2)));
            this.columnHelper.setColBestFit(column, true);
        }
    }

    public void setArbitraryExtraWidth(double arbitraryExtraWidth) {
        this.arbitraryExtraWidth = arbitraryExtraWidth;
    }

    public double getArbitraryExtraWidth() {
        return this.arbitraryExtraWidth;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFDrawing getDrawingPatriarch() {
        CTDrawing ctDrawing = getCTDrawing();
        if (ctDrawing != null) {
            for (POIXMLDocumentPart.RelationPart rp : getRelationParts()) {
                POIXMLDocumentPart p = rp.getDocumentPart();
                if (p instanceof XSSFDrawing) {
                    XSSFDrawing dr = (XSSFDrawing) p;
                    String drId = rp.getRelationship().getId();
                    if (drId.equals(ctDrawing.getId())) {
                        return dr;
                    }
                }
            }
            LOG.atError().log("Can't find drawing with id={} in the list of the sheet's relationships", ctDrawing.getId());
            return null;
        }
        return null;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFDrawing createDrawingPatriarch() {
        XSSFDrawing existingDrawing = getDrawingPatriarch();
        if (existingDrawing != null) {
            return existingDrawing;
        }
        int drawingNumber = getPackagePart().getPackage().getPartsByContentType(XSSFRelation.DRAWINGS.getContentType()).size() + 1;
        POIXMLDocumentPart.RelationPart rp = createRelationship(XSSFRelation.DRAWINGS, getWorkbook().getXssfFactory(), getNextPartNumber(XSSFRelation.DRAWINGS, drawingNumber), false);
        XSSFDrawing drawing = (XSSFDrawing) rp.getDocumentPart();
        String relId = rp.getRelationship().getId();
        CTDrawing ctDrawing = this.worksheet.addNewDrawing();
        ctDrawing.setId(relId);
        return drawing;
    }

    @Override // org.apache.poi.xssf.usermodel.OoxmlSheetExtensions
    public XSSFVMLDrawing getVMLDrawing(boolean autoCreate) {
        if (this.xssfvmlDrawing == null) {
            XSSFVMLDrawing drawing = null;
            CTLegacyDrawing ctDrawing = getCTLegacyDrawing();
            if (ctDrawing == null) {
                if (autoCreate) {
                    int drawingNumber = getNextPartNumber(XSSFRelation.VML_DRAWINGS, getPackagePart().getPackage().getPartsByContentType(XSSFRelation.VML_DRAWINGS.getContentType()).size());
                    POIXMLDocumentPart.RelationPart rp = createRelationship(XSSFRelation.VML_DRAWINGS, getWorkbook().getXssfFactory(), drawingNumber, false);
                    drawing = (XSSFVMLDrawing) rp.getDocumentPart();
                    String relId = rp.getRelationship().getId();
                    this.worksheet.addNewLegacyDrawing().setId(relId);
                }
            } else {
                String id = ctDrawing.getId();
                Iterator<POIXMLDocumentPart.RelationPart> it = getRelationParts().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    POIXMLDocumentPart.RelationPart rp2 = it.next();
                    POIXMLDocumentPart p = rp2.getDocumentPart();
                    if (p instanceof XSSFVMLDrawing) {
                        XSSFVMLDrawing dr = (XSSFVMLDrawing) p;
                        String drId = rp2.getRelationship().getId();
                        if (drId.equals(id)) {
                            drawing = dr;
                            break;
                        }
                    }
                }
                if (drawing == null) {
                    LOG.atError().log("Can't find VML drawing with id={} in the list of the sheet's relationships", id);
                }
            }
            this.xssfvmlDrawing = drawing;
        }
        return this.xssfvmlDrawing;
    }

    protected CTDrawing getCTDrawing() {
        return this.worksheet.getDrawing();
    }

    protected CTLegacyDrawing getCTLegacyDrawing() {
        return this.worksheet.getLegacyDrawing();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void createFreezePane(int colSplit, int rowSplit) {
        createFreezePane(colSplit, rowSplit, colSplit, rowSplit);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void createFreezePane(int colSplit, int rowSplit, int leftmostColumn, int topRow) {
        boolean removeSplit = colSplit == 0 && rowSplit == 0;
        CTSheetView ctView = getDefaultSheetView(removeSplit ? false : true);
        if (ctView != null) {
            ctView.setSelectionArray(null);
        }
        if (removeSplit) {
            if (ctView != null && ctView.isSetPane()) {
                ctView.unsetPane();
                return;
            }
            return;
        }
        if (ctView == null) {
            throw new AssertionError();
        }
        CTPane pane = ctView.isSetPane() ? ctView.getPane() : ctView.addNewPane();
        if (pane == null) {
            throw new AssertionError();
        }
        if (colSplit > 0) {
            pane.setXSplit(colSplit);
        } else if (pane.isSetXSplit()) {
            pane.unsetXSplit();
        }
        if (rowSplit > 0) {
            pane.setYSplit(rowSplit);
        } else if (pane.isSetYSplit()) {
            pane.unsetYSplit();
        }
        STPane.Enum activePane = STPane.BOTTOM_RIGHT;
        int pRow = topRow;
        int pCol = leftmostColumn;
        if (rowSplit == 0) {
            pRow = 0;
            activePane = STPane.TOP_RIGHT;
        } else if (colSplit == 0) {
            pCol = 0;
            activePane = STPane.BOTTOM_LEFT;
        }
        pane.setState(STPaneState.FROZEN);
        pane.setTopLeftCell(new CellReference(pRow, pCol).formatAsString());
        pane.setActivePane(activePane);
        ctView.addNewSelection().setPane(activePane);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFRow createRow(int rownum) {
        CTRow ctRow;
        Integer rownumI = Integer.valueOf(rownum);
        XSSFRow prev = this._rows.get(rownumI);
        if (prev != null) {
            while (prev.getFirstCellNum() != -1) {
                prev.removeCell(prev.getCell((int) prev.getFirstCellNum()));
            }
            ctRow = prev.getCTRow();
            ctRow.set(CTRow.Factory.newInstance());
        } else if (this._rows.isEmpty() || rownum > this._rows.lastKey().intValue()) {
            ctRow = this.worksheet.getSheetData().addNewRow();
        } else {
            int idx = this._rows.headMap(rownumI).size();
            ctRow = this.worksheet.getSheetData().insertNewRow(idx);
        }
        XSSFRow r = new XSSFRow(ctRow, this);
        r.setRowNum(rownum);
        this._rows.put(rownumI, r);
        return r;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    @Removal(version = "7.0.0")
    @Deprecated
    public void createSplitPane(int xSplitPos, int ySplitPos, int leftmostColumn, int topRow, int activePane) {
        createFreezePane(xSplitPos, ySplitPos, leftmostColumn, topRow);
        if (xSplitPos > 0 || ySplitPos > 0) {
            CTPane pane = getPane(true);
            pane.setState(STPaneState.SPLIT);
            pane.setActivePane(STPane.Enum.forInt(activePane));
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void createSplitPane(int xSplitPos, int ySplitPos, int leftmostColumn, int topRow, PaneType activePane) {
        STPane.Enum stPaneEnum;
        createFreezePane(xSplitPos, ySplitPos, leftmostColumn, topRow);
        if (xSplitPos > 0 || ySplitPos > 0) {
            CTPane pane = getPane(true);
            pane.setState(STPaneState.SPLIT);
            switch (activePane) {
                case LOWER_RIGHT:
                    stPaneEnum = STPane.BOTTOM_RIGHT;
                    break;
                case UPPER_RIGHT:
                    stPaneEnum = STPane.TOP_RIGHT;
                    break;
                case LOWER_LEFT:
                    stPaneEnum = STPane.BOTTOM_LEFT;
                    break;
                default:
                    stPaneEnum = STPane.TOP_LEFT;
                    break;
            }
            pane.setActivePane(stPaneEnum);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFComment getCellComment(CellAddress address) {
        if (this.sheetComments == null) {
            return null;
        }
        return this.sheetComments.findCellComment(address);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public Map<CellAddress, XSSFComment> getCellComments() {
        if (this.sheetComments == null) {
            return Collections.emptyMap();
        }
        Map<CellAddress, XSSFComment> map = new HashMap<>();
        Iterator<CellAddress> iter = this.sheetComments.getCellAddresses();
        while (iter.hasNext()) {
            CellAddress address = iter.next();
            map.put(address, getCellComment(address));
        }
        return map;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFHyperlink getHyperlink(int row, int column) {
        return getHyperlink(new CellAddress(row, column));
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFHyperlink getHyperlink(CellAddress addr) {
        for (XSSFHyperlink hyperlink : getHyperlinkList()) {
            if (addr.getRow() >= hyperlink.getFirstRow() && addr.getRow() <= hyperlink.getLastRow() && addr.getColumn() >= hyperlink.getFirstColumn() && addr.getColumn() <= hyperlink.getLastColumn()) {
                return hyperlink;
            }
        }
        return null;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public List<XSSFHyperlink> getHyperlinkList() {
        return Collections.unmodifiableList(this.hyperlinks);
    }

    private int[] getBreaks(CTPageBreak ctPageBreak) {
        CTBreak[] brkArray = ctPageBreak.getBrkArray();
        int[] breaks = new int[brkArray.length];
        for (int i = 0; i < brkArray.length; i++) {
            breaks[i] = Math.toIntExact(brkArray[i].getId() - 1);
        }
        return breaks;
    }

    private void removeBreak(int index, CTPageBreak ctPageBreak) {
        int index1 = index + 1;
        CTBreak[] brkArray = ctPageBreak.getBrkArray();
        for (int i = 0; i < brkArray.length; i++) {
            if (brkArray[i].getId() == index1) {
                ctPageBreak.removeBrk(i);
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int[] getColumnBreaks() {
        return this.worksheet.isSetColBreaks() ? getBreaks(this.worksheet.getColBreaks()) : new int[0];
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int getColumnWidth(int columnIndex) {
        CTCol col = this.columnHelper.getColumn(columnIndex, false);
        double width = (col == null || !col.isSetWidth()) ? getDefaultColumnWidth() : col.getWidth();
        return Math.toIntExact(Math.round(256.0d * width));
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public float getColumnWidthInPixels(int columnIndex) {
        float widthIn256 = getColumnWidth(columnIndex);
        return (float) ((widthIn256 / 256.0d) * 7.001699924468994d);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int getDefaultColumnWidth() {
        CTSheetFormatPr pr = this.worksheet.getSheetFormatPr();
        if (pr == null) {
            return 8;
        }
        return Math.toIntExact(pr.getBaseColWidth());
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public short getDefaultRowHeight() {
        return (short) (getDefaultRowHeightInPoints() * 20.0f);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public float getDefaultRowHeightInPoints() {
        CTSheetFormatPr pr = this.worksheet.getSheetFormatPr();
        return (float) (pr == null ? 0.0d : pr.getDefaultRowHeight());
    }

    private CTSheetFormatPr getSheetTypeSheetFormatPr(boolean createIfNotExists) {
        if (this.worksheet.isSetSheetFormatPr()) {
            return this.worksheet.getSheetFormatPr();
        }
        if (createIfNotExists) {
            return this.worksheet.addNewSheetFormatPr();
        }
        return null;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public CellStyle getColumnStyle(int column) {
        int idx = this.columnHelper.getColDefaultStyle(column);
        return getWorkbook().getCellStyleAt(idx == -1 ? 0 : idx);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setRightToLeft(boolean value) {
        CTSheetView dsv = getDefaultSheetView(true);
        if (dsv == null) {
            throw new AssertionError();
        }
        dsv.setRightToLeft(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isRightToLeft() {
        CTSheetView dsv = getDefaultSheetView(false);
        return dsv != null && dsv.getRightToLeft();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getDisplayGuts() {
        CTSheetPr sheetPr = getSheetTypeSheetPr();
        CTOutlinePr outlinePr = sheetPr.getOutlinePr() == null ? CTOutlinePr.Factory.newInstance() : sheetPr.getOutlinePr();
        return outlinePr.getShowOutlineSymbols();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDisplayGuts(boolean value) {
        CTSheetPr sheetPr = getSheetTypeSheetPr();
        CTOutlinePr outlinePr = sheetPr.getOutlinePr() == null ? sheetPr.addNewOutlinePr() : sheetPr.getOutlinePr();
        outlinePr.setShowOutlineSymbols(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isDisplayZeros() {
        CTSheetView dsv = getDefaultSheetView(false);
        return dsv == null || dsv.getShowZeros();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDisplayZeros(boolean value) {
        CTSheetView view = getDefaultSheetView(true);
        if (view == null) {
            throw new AssertionError();
        }
        view.setShowZeros(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int getFirstRowNum() {
        if (this._rows.isEmpty()) {
            return -1;
        }
        return this._rows.firstKey().intValue();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getFitToPage() {
        CTSheetPr sheetPr = getSheetTypeSheetPr();
        CTPageSetUpPr psSetup = (sheetPr == null || !sheetPr.isSetPageSetUpPr()) ? CTPageSetUpPr.Factory.newInstance() : sheetPr.getPageSetUpPr();
        return psSetup.getFitToPage();
    }

    private CTSheetPr getSheetTypeSheetPr() {
        if (this.worksheet.getSheetPr() == null) {
            this.worksheet.setSheetPr(CTSheetPr.Factory.newInstance());
        }
        return this.worksheet.getSheetPr();
    }

    private CTHeaderFooter getSheetTypeHeaderFooter() {
        if (this.worksheet.getHeaderFooter() == null) {
            this.worksheet.setHeaderFooter(CTHeaderFooter.Factory.newInstance());
        }
        return this.worksheet.getHeaderFooter();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public Footer getFooter() {
        return getOddFooter();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public Header getHeader() {
        return getOddHeader();
    }

    public Footer getOddFooter() {
        return new XSSFOddFooter(getSheetTypeHeaderFooter());
    }

    public Footer getEvenFooter() {
        return new XSSFEvenFooter(getSheetTypeHeaderFooter());
    }

    public Footer getFirstFooter() {
        return new XSSFFirstFooter(getSheetTypeHeaderFooter());
    }

    public Header getOddHeader() {
        return new XSSFOddHeader(getSheetTypeHeaderFooter());
    }

    public Header getEvenHeader() {
        return new XSSFEvenHeader(getSheetTypeHeaderFooter());
    }

    public Header getFirstHeader() {
        return new XSSFFirstHeader(getSheetTypeHeaderFooter());
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getHorizontallyCenter() {
        CTPrintOptions opts = this.worksheet.getPrintOptions();
        return opts != null && opts.getHorizontalCentered();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int getLastRowNum() {
        if (this._rows.isEmpty()) {
            return -1;
        }
        return this._rows.lastKey().intValue();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public short getLeftCol() {
        String cellRef = this.worksheet.getSheetViews().getSheetViewArray(0).getTopLeftCell();
        if (cellRef == null) {
            return (short) 0;
        }
        CellReference cellReference = new CellReference(cellRef);
        return cellReference.getCol();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    @Removal(version = "7.0.0")
    @Deprecated
    public double getMargin(short margin) {
        return getMargin(PageMargin.getByShortValue(margin));
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public double getMargin(PageMargin margin) {
        if (!this.worksheet.isSetPageMargins()) {
            return 0.0d;
        }
        CTPageMargins pageMargins = this.worksheet.getPageMargins();
        switch (margin) {
            case LEFT:
                return pageMargins.getLeft();
            case RIGHT:
                return pageMargins.getRight();
            case TOP:
                return pageMargins.getTop();
            case BOTTOM:
                return pageMargins.getBottom();
            case HEADER:
                return pageMargins.getHeader();
            case FOOTER:
                return pageMargins.getFooter();
            default:
                throw new IllegalArgumentException("Unknown margin constant:  " + margin);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    @Removal(version = "7.0.0")
    @Deprecated
    public void setMargin(short margin, double size) {
        PageMargin pageMargin = PageMargin.getByShortValue(margin);
        if (pageMargin == null) {
            throw new IllegalArgumentException("Unknown margin constant:  " + ((int) margin));
        }
        setMargin(pageMargin, size);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setMargin(PageMargin margin, double size) {
        CTPageMargins pageMargins = this.worksheet.isSetPageMargins() ? this.worksheet.getPageMargins() : this.worksheet.addNewPageMargins();
        switch (margin) {
            case LEFT:
                pageMargins.setLeft(size);
                return;
            case RIGHT:
                pageMargins.setRight(size);
                return;
            case TOP:
                pageMargins.setTop(size);
                return;
            case BOTTOM:
                pageMargins.setBottom(size);
                return;
            case HEADER:
                pageMargins.setHeader(size);
                return;
            case FOOTER:
                pageMargins.setFooter(size);
                return;
            default:
                throw new IllegalArgumentException("Unknown margin constant:  " + margin);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public CellRangeAddress getMergedRegion(int index) {
        CTMergeCells ctMergeCells = this.worksheet.getMergeCells();
        if (ctMergeCells == null) {
            throw new IllegalStateException("This worksheet does not contain merged regions");
        }
        CTMergeCell ctMergeCell = ctMergeCells.getMergeCellArray(index);
        String ref = ctMergeCell.getRef();
        return CellRangeAddress.valueOf(ref);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public List<CellRangeAddress> getMergedRegions() {
        List<CellRangeAddress> addresses = new ArrayList<>();
        CTMergeCells ctMergeCells = this.worksheet.getMergeCells();
        if (ctMergeCells == null) {
            return addresses;
        }
        for (CTMergeCell ctMergeCell : ctMergeCells.getMergeCellArray()) {
            String ref = ctMergeCell.getRef();
            addresses.add(CellRangeAddress.valueOf(ref));
        }
        return addresses;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int getNumMergedRegions() {
        CTMergeCells ctMergeCells = this.worksheet.getMergeCells();
        if (ctMergeCells == null) {
            return 0;
        }
        return ctMergeCells.sizeOfMergeCellArray();
    }

    public int getNumHyperlinks() {
        return this.hyperlinks.size();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public PaneInformation getPaneInformation() {
        short row;
        short col;
        CTPane pane = getPane(false);
        if (pane == null) {
            return null;
        }
        if (!pane.isSetTopLeftCell()) {
            row = 0;
            col = 0;
        } else {
            CellReference cellRef = new CellReference(pane.getTopLeftCell());
            short row2 = (short) cellRef.getRow();
            short col2 = cellRef.getCol();
            row = row2;
            col = col2;
        }
        short x = (short) pane.getXSplit();
        short y = (short) pane.getYSplit();
        byte active = (byte) (pane.getActivePane().intValue() - 1);
        boolean frozen = pane.getState() == STPaneState.FROZEN;
        return new PaneInformation(x, y, row, col, active, frozen);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int getPhysicalNumberOfRows() {
        return this._rows.size();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFPrintSetup getPrintSetup() {
        return new XSSFPrintSetup(this.worksheet);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getProtect() {
        return isSheetLocked();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void protectSheet(String password) {
        if (password != null) {
            CTSheetProtection sheetProtection = safeGetProtectionField();
            setSheetPassword(password, null);
            sheetProtection.setSheet(true);
            sheetProtection.setScenarios(true);
            sheetProtection.setObjects(true);
            return;
        }
        this.worksheet.unsetSheetProtection();
    }

    public void setSheetPassword(String password, HashAlgorithm hashAlgo) {
        if (password == null && !isSheetProtectionEnabled()) {
            return;
        }
        XSSFPasswordHelper.setPassword(safeGetProtectionField(), password, hashAlgo, null);
    }

    public boolean validateSheetPassword(String password) {
        if (isSheetProtectionEnabled()) {
            return XSSFPasswordHelper.validatePassword(safeGetProtectionField(), password, null);
        }
        return password == null;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFRow getRow(int rownum) {
        Integer rownumI = Integer.valueOf(rownum);
        return this._rows.get(rownumI);
    }

    private List<XSSFRow> getRows(int startRowNum, int endRowNum, boolean createRowIfMissing) {
        if (startRowNum > endRowNum) {
            throw new IllegalArgumentException("getRows: startRowNum must be less than or equal to endRowNum");
        }
        List<XSSFRow> rows = new ArrayList<>();
        if (createRowIfMissing) {
            for (int i = startRowNum; i <= endRowNum; i++) {
                XSSFRow row = getRow(i);
                if (row == null) {
                    row = createRow(i);
                }
                rows.add(row);
            }
        } else {
            Integer startI = Integer.valueOf(startRowNum);
            Integer endI = Integer.valueOf(endRowNum + 1);
            Collection<XSSFRow> inclusive = this._rows.subMap(startI, endI).values();
            rows.addAll(inclusive);
        }
        return rows;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int[] getRowBreaks() {
        return this.worksheet.isSetRowBreaks() ? getBreaks(this.worksheet.getRowBreaks()) : new int[0];
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getRowSumsBelow() {
        CTSheetPr sheetPr = this.worksheet.getSheetPr();
        CTOutlinePr outlinePr = (sheetPr == null || !sheetPr.isSetOutlinePr()) ? null : sheetPr.getOutlinePr();
        return outlinePr == null || outlinePr.getSummaryBelow();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setRowSumsBelow(boolean value) {
        ensureOutlinePr().setSummaryBelow(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getRowSumsRight() {
        CTSheetPr sheetPr = this.worksheet.getSheetPr();
        CTOutlinePr outlinePr = (sheetPr == null || !sheetPr.isSetOutlinePr()) ? CTOutlinePr.Factory.newInstance() : sheetPr.getOutlinePr();
        return outlinePr.getSummaryRight();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setRowSumsRight(boolean value) {
        ensureOutlinePr().setSummaryRight(value);
    }

    private CTOutlinePr ensureOutlinePr() {
        CTSheetPr sheetPr = this.worksheet.isSetSheetPr() ? this.worksheet.getSheetPr() : this.worksheet.addNewSheetPr();
        return sheetPr.isSetOutlinePr() ? sheetPr.getOutlinePr() : sheetPr.addNewOutlinePr();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getScenarioProtect() {
        return this.worksheet.isSetSheetProtection() && this.worksheet.getSheetProtection().getScenarios();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public short getTopRow() {
        CTSheetView dsv = getDefaultSheetView(false);
        String cellRef = dsv == null ? null : dsv.getTopLeftCell();
        if (cellRef == null) {
            return (short) 0;
        }
        return (short) new CellReference(cellRef).getRow();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getVerticallyCenter() {
        CTPrintOptions opts = this.worksheet.getPrintOptions();
        return opts != null && opts.getVerticalCentered();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void groupColumn(int fromColumn, int toColumn) {
        groupColumn1Based(fromColumn + 1, toColumn + 1);
    }

    private void groupColumn1Based(int fromColumn, int toColumn) {
        CTCols ctCols = this.worksheet.getColsArray(0);
        CTCol ctCol = CTCol.Factory.newInstance();
        CTCol fixCol_before = this.columnHelper.getColumn1Based(toColumn, false);
        if (fixCol_before != null) {
            fixCol_before = (CTCol) fixCol_before.copy();
        }
        ctCol.setMin(fromColumn);
        ctCol.setMax(toColumn);
        this.columnHelper.addCleanColIntoCols(ctCols, ctCol);
        CTCol fixCol_after = this.columnHelper.getColumn1Based(toColumn, false);
        if (fixCol_before != null && fixCol_after != null) {
            this.columnHelper.setColumnAttributes(fixCol_before, fixCol_after);
        }
        int maxLevelCol = -1;
        int index = fromColumn;
        while (index <= toColumn) {
            CTCol col = this.columnHelper.getColumn1Based(index, false);
            short outlineLevel = col.getOutlineLevel();
            int newOutlineLevel = outlineLevel + 1;
            col.setOutlineLevel((short) newOutlineLevel);
            maxLevelCol = Math.max(maxLevelCol, newOutlineLevel);
            int index2 = Math.toIntExact(col.getMax());
            index = index2 + 1;
        }
        this.worksheet.setColsArray(0, ctCols);
        increaseSheetFormatPrOutlineLevelColIfNecessary((short) Math.min(32767, maxLevelCol));
    }

    private void setColWidthAttribute(CTCols ctCols) {
        for (CTCol col : ctCols.getColArray()) {
            if (!col.isSetWidth()) {
                col.setWidth(getDefaultColumnWidth());
                col.setCustomWidth(false);
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void groupRow(int fromRow, int toRow) {
        int maxOutlineLevel = -1;
        for (int i = fromRow; i <= toRow; i++) {
            XSSFRow xrow = getRow(i);
            if (xrow == null) {
                xrow = createRow(i);
            }
            CTRow ctrow = xrow.getCTRow();
            short outlineLevel = ctrow.getOutlineLevel();
            int newOutlineLevel = outlineLevel + 1;
            maxOutlineLevel = Math.max(maxOutlineLevel, newOutlineLevel);
            ctrow.setOutlineLevel((short) newOutlineLevel);
        }
        increaseSheetFormatPrOutlineLevelRowIfNecessary((short) Math.min(32767, maxOutlineLevel));
    }

    private short getMaxOutlineLevelRows() {
        int outlineLevel = 0;
        for (XSSFRow xrow : this._rows.values()) {
            outlineLevel = Math.max(outlineLevel, (int) xrow.getCTRow().getOutlineLevel());
        }
        return (short) outlineLevel;
    }

    private short getMaxOutlineLevelCols() {
        CTCols ctCols = this.worksheet.getColsArray(0);
        int outlineLevel = 0;
        for (CTCol col : ctCols.getColArray()) {
            outlineLevel = Math.max(outlineLevel, (int) col.getOutlineLevel());
        }
        return (short) outlineLevel;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isColumnBroken(int column) {
        for (int colBreak : getColumnBreaks()) {
            if (colBreak == column) {
                return true;
            }
        }
        return false;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isColumnHidden(int columnIndex) {
        CTCol col = this.columnHelper.getColumn(columnIndex, false);
        return col != null && col.getHidden();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isDisplayFormulas() {
        CTSheetView dsv = getDefaultSheetView(false);
        return dsv != null && dsv.getShowFormulas();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isDisplayGridlines() {
        CTSheetView dsv = getDefaultSheetView(false);
        return dsv == null || dsv.getShowGridLines();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDisplayGridlines(boolean show) {
        CTSheetView dsv = getDefaultSheetView(true);
        if (dsv == null) {
            throw new AssertionError();
        }
        dsv.setShowGridLines(show);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isDisplayRowColHeadings() {
        CTSheetView dsv = getDefaultSheetView(false);
        return dsv == null || dsv.getShowRowColHeaders();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDisplayRowColHeadings(boolean show) {
        CTSheetView dsv = getDefaultSheetView(true);
        if (dsv == null) {
            throw new AssertionError();
        }
        dsv.setShowRowColHeaders(show);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isPrintGridlines() {
        CTPrintOptions opts = this.worksheet.getPrintOptions();
        return opts != null && opts.getGridLines();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setPrintGridlines(boolean value) {
        CTPrintOptions opts = this.worksheet.isSetPrintOptions() ? this.worksheet.getPrintOptions() : this.worksheet.addNewPrintOptions();
        opts.setGridLines(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isPrintRowAndColumnHeadings() {
        CTPrintOptions opts = this.worksheet.getPrintOptions();
        return opts != null && opts.getHeadings();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setPrintRowAndColumnHeadings(boolean value) {
        CTPrintOptions opts = this.worksheet.isSetPrintOptions() ? this.worksheet.getPrintOptions() : this.worksheet.addNewPrintOptions();
        opts.setHeadings(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isRowBroken(int row) {
        for (int rowBreak : getRowBreaks()) {
            if (rowBreak == row) {
                return true;
            }
        }
        return false;
    }

    private void setBreak(int id, CTPageBreak ctPgBreak, int lastIndex) {
        CTBreak brk = ctPgBreak.addNewBrk();
        brk.setId(id + 1);
        brk.setMan(true);
        brk.setMax(lastIndex);
        int nPageBreaks = ctPgBreak.sizeOfBrkArray();
        ctPgBreak.setCount(nPageBreaks);
        ctPgBreak.setManualBreakCount(nPageBreaks);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setRowBreak(int row) {
        if (!isRowBroken(row)) {
            CTPageBreak pgBreak = this.worksheet.isSetRowBreaks() ? this.worksheet.getRowBreaks() : this.worksheet.addNewRowBreaks();
            setBreak(row, pgBreak, SpreadsheetVersion.EXCEL2007.getLastColumnIndex());
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void removeColumnBreak(int column) {
        if (this.worksheet.isSetColBreaks()) {
            removeBreak(column, this.worksheet.getColBreaks());
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void removeMergedRegion(int index) {
        if (!this.worksheet.isSetMergeCells()) {
            return;
        }
        CTMergeCells ctMergeCells = this.worksheet.getMergeCells();
        int size = ctMergeCells.sizeOfMergeCellArray();
        if (index < 0 || index >= size) {
            throw new AssertionError();
        }
        if (size > 1) {
            ctMergeCells.removeMergeCell(index);
            ctMergeCells.setCount(ctMergeCells.getCount() - 1);
        } else {
            this.worksheet.unsetMergeCells();
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void removeMergedRegions(Collection<Integer> indices) {
        if (!this.worksheet.isSetMergeCells()) {
            return;
        }
        CTMergeCells ctMergeCells = this.worksheet.getMergeCells();
        List<CTMergeCell> newMergeCells = new ArrayList<>(ctMergeCells.sizeOfMergeCellArray());
        int idx = 0;
        CTMergeCell[] mergeCellArray = ctMergeCells.getMergeCellArray();
        int length = mergeCellArray.length;
        int i = 0;
        while (i < length) {
            CTMergeCell mc = mergeCellArray[i];
            int idx2 = idx + 1;
            if (!indices.contains(Integer.valueOf(idx))) {
                newMergeCells.add(mc);
            }
            i++;
            idx = idx2;
        }
        if (newMergeCells.isEmpty()) {
            this.worksheet.unsetMergeCells();
            return;
        }
        CTMergeCell[] newMergeCellsArray = new CTMergeCell[newMergeCells.size()];
        ctMergeCells.setMergeCellArray((CTMergeCell[]) newMergeCells.toArray(newMergeCellsArray));
        ctMergeCells.setCount(newMergeCells.size());
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void removeRow(Row row) {
        if (row.getSheet() != this) {
            throw new IllegalArgumentException("Specified row does not belong to this sheet");
        }
        ArrayList<XSSFCell> cellsToDelete = new ArrayList<>();
        for (Cell cell : row) {
            cellsToDelete.add((XSSFCell) cell);
        }
        Iterator<XSSFCell> it = cellsToDelete.iterator();
        while (it.hasNext()) {
            XSSFCell cell2 = it.next();
            row.removeCell(cell2);
        }
        int rowNum = row.getRowNum();
        Integer rowNumI = Integer.valueOf(rowNum);
        int idx = this._rows.headMap(rowNumI).size();
        this._rows.remove(rowNumI);
        this.worksheet.getSheetData().removeRow(idx);
        if (this.sheetComments != null) {
            for (CellAddress ref : getCellComments().keySet()) {
                if (ref.getRow() == rowNum) {
                    this.sheetComments.removeComment(ref);
                }
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void removeRowBreak(int row) {
        if (this.worksheet.isSetRowBreaks()) {
            removeBreak(row, this.worksheet.getRowBreaks());
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setForceFormulaRecalculation(boolean value) {
        CTCalcPr calcPr = getWorkbook().getCTWorkbook().getCalcPr();
        if (this.worksheet.isSetSheetCalcPr()) {
            CTSheetCalcPr calc = this.worksheet.getSheetCalcPr();
            calc.setFullCalcOnLoad(value);
        } else if (value) {
            CTSheetCalcPr calc2 = this.worksheet.addNewSheetCalcPr();
            calc2.setFullCalcOnLoad(value);
        }
        if (value && calcPr != null && calcPr.getCalcMode() == STCalcMode.MANUAL) {
            calcPr.setCalcMode(STCalcMode.AUTO);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getForceFormulaRecalculation() {
        if (this.worksheet.isSetSheetCalcPr()) {
            CTSheetCalcPr calc = this.worksheet.getSheetCalcPr();
            return calc.getFullCalcOnLoad();
        }
        return false;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public Iterator<Row> rowIterator() {
        return this._rows.values().iterator();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet, java.lang.Iterable
    public Spliterator<Row> spliterator() {
        return this._rows.values().spliterator();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean getAutobreaks() {
        CTSheetPr sheetPr = getSheetTypeSheetPr();
        CTPageSetUpPr psSetup = (sheetPr == null || !sheetPr.isSetPageSetUpPr()) ? CTPageSetUpPr.Factory.newInstance() : sheetPr.getPageSetUpPr();
        return psSetup.getAutoPageBreaks();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setAutobreaks(boolean value) {
        CTSheetPr sheetPr = getSheetTypeSheetPr();
        CTPageSetUpPr psSetup = sheetPr.isSetPageSetUpPr() ? sheetPr.getPageSetUpPr() : sheetPr.addNewPageSetUpPr();
        psSetup.setAutoPageBreaks(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setColumnBreak(int column) {
        if (!isColumnBroken(column)) {
            CTPageBreak pgBreak = this.worksheet.isSetColBreaks() ? this.worksheet.getColBreaks() : this.worksheet.addNewColBreaks();
            setBreak(column, pgBreak, SpreadsheetVersion.EXCEL2007.getLastRowIndex());
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setColumnGroupCollapsed(int columnNumber, boolean collapsed) {
        if (collapsed) {
            collapseColumn(columnNumber);
        } else {
            expandColumn(columnNumber);
        }
    }

    private void collapseColumn(int columnNumber) {
        CTCols cols = this.worksheet.getColsArray(0);
        CTCol col = this.columnHelper.getColumn(columnNumber, false);
        int colInfoIx = this.columnHelper.getIndexOfColumn(cols, col);
        if (colInfoIx == -1) {
            return;
        }
        int groupStartColInfoIx = findStartOfColumnOutlineGroup(colInfoIx);
        CTCol columnInfo = cols.getColArray(groupStartColInfoIx);
        int lastColMax = setGroupHidden(groupStartColInfoIx, columnInfo.getOutlineLevel(), true);
        setColumn(lastColMax + 1, 0, null, null, Boolean.TRUE);
    }

    private void setColumn(int targetColumnIx, Integer style, Integer level, Boolean hidden, Boolean collapsed) {
        CTCols cols = this.worksheet.getColsArray(0);
        CTCol ci = null;
        CTCol[] colArray = cols.getColArray();
        int length = colArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            CTCol tci = colArray[i];
            long tciMin = tci.getMin();
            long tciMax = tci.getMax();
            if (tciMin >= targetColumnIx && tciMax <= targetColumnIx) {
                ci = tci;
                break;
            } else if (tciMin > targetColumnIx) {
                break;
            } else {
                i++;
            }
        }
        if (ci == null) {
            CTCol nci = CTCol.Factory.newInstance();
            nci.setMin(targetColumnIx);
            nci.setMax(targetColumnIx);
            unsetCollapsed(collapsed, nci);
            this.columnHelper.addCleanColIntoCols(cols, nci);
            return;
        }
        boolean styleChanged = (style == null || ci.getStyle() == ((long) style.intValue())) ? false : true;
        boolean levelChanged = (level == null || ci.getOutlineLevel() == level.intValue()) ? false : true;
        boolean hiddenChanged = (hidden == null || ci.getHidden() == hidden.booleanValue()) ? false : true;
        boolean collapsedChanged = (collapsed == null || ci.getCollapsed() == collapsed.booleanValue()) ? false : true;
        boolean columnChanged = levelChanged || hiddenChanged || collapsedChanged || styleChanged;
        if (!columnChanged) {
            return;
        }
        long ciMin = ci.getMin();
        long ciMax = ci.getMax();
        if (ciMin == targetColumnIx && ciMax == targetColumnIx) {
            unsetCollapsed(collapsed, ci);
            return;
        }
        if (ciMin != targetColumnIx && ciMax != targetColumnIx) {
            CTCol ciMid = this.columnHelper.cloneCol(cols, ci);
            CTCol ciEnd = this.columnHelper.cloneCol(cols, ci);
            int lastcolumn = Math.toIntExact(ciMax);
            ci.setMax(targetColumnIx - 1);
            ciMid.setMin(targetColumnIx);
            ciMid.setMax(targetColumnIx);
            unsetCollapsed(collapsed, ciMid);
            this.columnHelper.addCleanColIntoCols(cols, ciMid);
            ciEnd.setMin(targetColumnIx + 1);
            ciEnd.setMax(lastcolumn);
            this.columnHelper.addCleanColIntoCols(cols, ciEnd);
            return;
        }
        if (ciMin == targetColumnIx) {
            ci.setMin(targetColumnIx + 1);
        } else {
            ci.setMax(targetColumnIx - 1);
        }
        CTCol nci2 = this.columnHelper.cloneCol(cols, ci);
        nci2.setMin(targetColumnIx);
        unsetCollapsed(collapsed, nci2);
        this.columnHelper.addCleanColIntoCols(cols, nci2);
    }

    private void unsetCollapsed(Boolean collapsed, CTCol ci) {
        if (collapsed != null && collapsed.booleanValue()) {
            ci.setCollapsed(true);
        } else {
            ci.unsetCollapsed();
        }
    }

    private int setGroupHidden(int pIdx, int level, boolean hidden) {
        CTCols cols = this.worksheet.getColsArray(0);
        int idx = pIdx;
        CTCol[] colArray = cols.getColArray();
        CTCol columnInfo = colArray[idx];
        while (idx < colArray.length) {
            columnInfo.setHidden(hidden);
            if (idx + 1 < colArray.length) {
                CTCol nextColumnInfo = colArray[idx + 1];
                if (!isAdjacentBefore(columnInfo, nextColumnInfo) || nextColumnInfo.getOutlineLevel() < level) {
                    break;
                }
                columnInfo = nextColumnInfo;
            }
            idx++;
        }
        return Math.toIntExact(columnInfo.getMax());
    }

    private boolean isAdjacentBefore(CTCol col, CTCol otherCol) {
        return col.getMax() == otherCol.getMin() - 1;
    }

    private int findStartOfColumnOutlineGroup(int pIdx) {
        CTCols cols = this.worksheet.getColsArray(0);
        CTCol[] colArray = cols.getColArray();
        CTCol columnInfo = colArray[pIdx];
        int level = columnInfo.getOutlineLevel();
        int idx = pIdx;
        while (idx != 0) {
            CTCol prevColumnInfo = colArray[idx - 1];
            if (!isAdjacentBefore(prevColumnInfo, columnInfo) || prevColumnInfo.getOutlineLevel() < level) {
                break;
            }
            idx--;
            columnInfo = prevColumnInfo;
        }
        return idx;
    }

    private int findEndOfColumnOutlineGroup(int colInfoIndex) {
        CTCols cols = this.worksheet.getColsArray(0);
        CTCol[] colArray = cols.getColArray();
        CTCol columnInfo = colArray[colInfoIndex];
        int level = columnInfo.getOutlineLevel();
        int idx = colInfoIndex;
        int lastIdx = colArray.length - 1;
        while (idx < lastIdx) {
            CTCol nextColumnInfo = colArray[idx + 1];
            if (!isAdjacentBefore(columnInfo, nextColumnInfo) || nextColumnInfo.getOutlineLevel() < level) {
                break;
            }
            idx++;
            columnInfo = nextColumnInfo;
        }
        return idx;
    }

    private void expandColumn(int columnIndex) {
        CTCols cols = this.worksheet.getColsArray(0);
        CTCol col = this.columnHelper.getColumn(columnIndex, false);
        int colInfoIx = this.columnHelper.getIndexOfColumn(cols, col);
        int idx = col == null ? -1 : findColInfoIdx(Math.toIntExact(col.getMax()), colInfoIx);
        if (idx == -1 || !isColumnGroupCollapsed(idx)) {
            return;
        }
        int startIdx = findStartOfColumnOutlineGroup(idx);
        int endIdx = findEndOfColumnOutlineGroup(idx);
        CTCol[] colArray = cols.getColArray();
        CTCol columnInfo = colArray[endIdx];
        if (!isColumnGroupHiddenByParent(idx)) {
            short outlineLevel = columnInfo.getOutlineLevel();
            boolean nestedGroup = false;
            for (int i = startIdx; i <= endIdx; i++) {
                CTCol ci = colArray[i];
                if (outlineLevel == ci.getOutlineLevel()) {
                    ci.unsetHidden();
                    if (nestedGroup) {
                        nestedGroup = false;
                        ci.setCollapsed(true);
                    }
                } else {
                    nestedGroup = true;
                }
            }
        }
        setColumn(Math.toIntExact(columnInfo.getMax() + 1), null, null, Boolean.FALSE, Boolean.FALSE);
    }

    private boolean isColumnGroupHiddenByParent(int idx) {
        CTCols cols = this.worksheet.getColsArray(0);
        int endLevel = 0;
        boolean endHidden = false;
        int endOfOutlineGroupIdx = findEndOfColumnOutlineGroup(idx);
        CTCol[] colArray = cols.getColArray();
        if (endOfOutlineGroupIdx < colArray.length - 1) {
            CTCol nextInfo = colArray[endOfOutlineGroupIdx + 1];
            if (isAdjacentBefore(colArray[endOfOutlineGroupIdx], nextInfo)) {
                endLevel = nextInfo.getOutlineLevel();
                endHidden = nextInfo.getHidden();
            }
        }
        int startLevel = 0;
        boolean startHidden = false;
        int startOfOutlineGroupIdx = findStartOfColumnOutlineGroup(idx);
        if (startOfOutlineGroupIdx > 0) {
            CTCol prevInfo = colArray[startOfOutlineGroupIdx - 1];
            if (isAdjacentBefore(prevInfo, colArray[startOfOutlineGroupIdx])) {
                startLevel = prevInfo.getOutlineLevel();
                startHidden = prevInfo.getHidden();
            }
        }
        if (endLevel > startLevel) {
            return endHidden;
        }
        return startHidden;
    }

    private int findColInfoIdx(int columnValue, int fromColInfoIdx) {
        CTCols cols = this.worksheet.getColsArray(0);
        if (columnValue < 0) {
            throw new IllegalArgumentException("column parameter out of range: " + columnValue);
        }
        if (fromColInfoIdx < 0) {
            throw new IllegalArgumentException("fromIdx parameter out of range: " + fromColInfoIdx);
        }
        CTCol[] colArray = cols.getColArray();
        for (int k = fromColInfoIdx; k < colArray.length; k++) {
            CTCol ci = colArray[k];
            if (containsColumn(ci, columnValue)) {
                return k;
            }
            if (ci.getMin() > fromColInfoIdx) {
                return -1;
            }
        }
        return -1;
    }

    private boolean containsColumn(CTCol col, int columnIndex) {
        return col.getMin() <= ((long) columnIndex) && ((long) columnIndex) <= col.getMax();
    }

    private boolean isColumnGroupCollapsed(int idx) {
        CTCols cols = this.worksheet.getColsArray(0);
        CTCol[] colArray = cols.getColArray();
        int endOfOutlineGroupIdx = findEndOfColumnOutlineGroup(idx);
        int nextColInfoIx = endOfOutlineGroupIdx + 1;
        if (nextColInfoIx >= colArray.length) {
            return false;
        }
        CTCol nextColInfo = colArray[nextColInfoIx];
        CTCol col = colArray[endOfOutlineGroupIdx];
        if (isAdjacentBefore(col, nextColInfo)) {
            return nextColInfo.getCollapsed();
        }
        return false;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setColumnHidden(int columnIndex, boolean hidden) {
        this.columnHelper.setColHidden(columnIndex, hidden);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setColumnWidth(int columnIndex, int width) {
        if (width > 65280) {
            throw new IllegalArgumentException("The maximum column width for an individual cell is 255 characters.");
        }
        this.columnHelper.setColWidth(columnIndex, width / 256.0d);
        this.columnHelper.setCustomWidth(columnIndex, true);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDefaultColumnStyle(int column, CellStyle style) {
        this.columnHelper.setColDefaultStyle(column, style);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDefaultColumnWidth(int width) {
        getSheetTypeSheetFormatPr(true).setBaseColWidth(width);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDefaultRowHeight(short height) {
        setDefaultRowHeightInPoints(height / 20.0f);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDefaultRowHeightInPoints(float height) {
        CTSheetFormatPr pr = getSheetTypeSheetFormatPr(true);
        pr.setDefaultRowHeight(height);
        pr.setCustomHeight(true);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setDisplayFormulas(boolean show) {
        CTSheetView dsv = getDefaultSheetView(true);
        if (dsv == null) {
            throw new AssertionError();
        }
        dsv.setShowFormulas(show);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setFitToPage(boolean b) {
        getSheetTypePageSetUpPr().setFitToPage(b);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setHorizontallyCenter(boolean value) {
        CTPrintOptions opts = this.worksheet.isSetPrintOptions() ? this.worksheet.getPrintOptions() : this.worksheet.addNewPrintOptions();
        opts.setHorizontalCentered(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setVerticallyCenter(boolean value) {
        CTPrintOptions opts = this.worksheet.isSetPrintOptions() ? this.worksheet.getPrintOptions() : this.worksheet.addNewPrintOptions();
        opts.setVerticalCentered(value);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setRowGroupCollapsed(int rowIndex, boolean collapse) {
        if (collapse) {
            collapseRow(rowIndex);
        } else {
            expandRow(rowIndex);
        }
    }

    private void collapseRow(int rowIndex) {
        XSSFRow row = getRow(rowIndex);
        if (row != null) {
            int startRow = findStartOfRowOutlineGroup(rowIndex);
            int lastRow = writeHidden(row, startRow, true);
            if (getRow(lastRow) != null) {
                getRow(lastRow).getCTRow().setCollapsed(true);
            } else {
                XSSFRow newRow = createRow(lastRow);
                newRow.getCTRow().setCollapsed(true);
            }
        }
    }

    private int findStartOfRowOutlineGroup(int rowIndex) {
        short level = getRow(rowIndex).getCTRow().getOutlineLevel();
        int currentRow = rowIndex;
        while (getRow(currentRow) != null) {
            if (getRow(currentRow).getCTRow().getOutlineLevel() < level) {
                return currentRow + 1;
            }
            currentRow--;
        }
        return currentRow;
    }

    private int writeHidden(XSSFRow xRow, int rowIndex, boolean hidden) {
        short level = xRow.getCTRow().getOutlineLevel();
        Iterator<Row> it = rowIterator();
        while (it.hasNext()) {
            XSSFRow xRow2 = (XSSFRow) it.next();
            if (xRow2.getRowNum() >= rowIndex && xRow2.getCTRow().getOutlineLevel() >= level) {
                xRow2.getCTRow().setHidden(hidden);
                rowIndex++;
            }
        }
        return rowIndex;
    }

    private void expandRow(int rowNumber) {
        if (rowNumber == -1) {
            return;
        }
        XSSFRow row = getRow(rowNumber);
        if (!row.getCTRow().isSetHidden()) {
            return;
        }
        int startIdx = findStartOfRowOutlineGroup(rowNumber);
        int endIdx = findEndOfRowOutlineGroup(rowNumber);
        short level = row.getCTRow().getOutlineLevel();
        if (!isRowGroupHiddenByParent(rowNumber)) {
            for (int i = startIdx; i < endIdx; i++) {
                if (level == getRow(i).getCTRow().getOutlineLevel()) {
                    getRow(i).getCTRow().unsetHidden();
                } else if (!isRowGroupCollapsed(i)) {
                    getRow(i).getCTRow().unsetHidden();
                }
            }
        }
        CTRow ctRow = getRow(endIdx).getCTRow();
        if (ctRow.getCollapsed()) {
            ctRow.unsetCollapsed();
        }
    }

    public int findEndOfRowOutlineGroup(int row) {
        short level = getRow(row).getCTRow().getOutlineLevel();
        int lastRowNum = getLastRowNum();
        int currentRow = row;
        while (currentRow < lastRowNum && getRow(currentRow) != null && getRow(currentRow).getCTRow().getOutlineLevel() >= level) {
            currentRow++;
        }
        return currentRow;
    }

    private boolean isRowGroupHiddenByParent(int row) {
        int endLevel;
        boolean endHidden;
        int startLevel;
        boolean startHidden;
        int endOfOutlineGroupIdx = findEndOfRowOutlineGroup(row);
        if (getRow(endOfOutlineGroupIdx) == null) {
            endLevel = 0;
            endHidden = false;
        } else {
            endLevel = getRow(endOfOutlineGroupIdx).getCTRow().getOutlineLevel();
            endHidden = getRow(endOfOutlineGroupIdx).getCTRow().getHidden();
        }
        int startOfOutlineGroupIdx = findStartOfRowOutlineGroup(row);
        if (startOfOutlineGroupIdx < 0 || getRow(startOfOutlineGroupIdx) == null) {
            startLevel = 0;
            startHidden = false;
        } else {
            startLevel = getRow(startOfOutlineGroupIdx).getCTRow().getOutlineLevel();
            startHidden = getRow(startOfOutlineGroupIdx).getCTRow().getHidden();
        }
        if (endLevel > startLevel) {
            return endHidden;
        }
        return startHidden;
    }

    private boolean isRowGroupCollapsed(int row) {
        int collapseRow = findEndOfRowOutlineGroup(row) + 1;
        if (getRow(collapseRow) == null) {
            return false;
        }
        return getRow(collapseRow).getCTRow().getCollapsed();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setZoom(int scale) {
        if (scale < 10 || scale > 400) {
            throw new IllegalArgumentException("Valid scale values range from 10 to 400");
        }
        CTSheetView dsv = getDefaultSheetView(true);
        if (dsv == null) {
            throw new AssertionError();
        }
        dsv.setZoomScale(scale);
    }

    public void copyRows(List<? extends Row> srcRows, int destStartRow, CellCopyPolicy policy) {
        copyRows(srcRows, destStartRow, policy, new CellCopyContext());
    }

    public void copyRows(List<? extends Row> srcRows, int destStartRow, CellCopyPolicy policy, CellCopyContext context) {
        int shift;
        if (srcRows == null || srcRows.isEmpty()) {
            throw new IllegalArgumentException("No rows to copy");
        }
        Row srcStartRow = srcRows.get(0);
        Row srcEndRow = srcRows.get(srcRows.size() - 1);
        if (srcStartRow == null) {
            throw new IllegalArgumentException("copyRows: First row cannot be null");
        }
        int srcStartRowNum = srcStartRow.getRowNum();
        int srcEndRowNum = srcEndRow.getRowNum();
        int size = srcRows.size();
        for (int index = 1; index < size; index++) {
            Row curRow = srcRows.get(index);
            if (curRow == null) {
                throw new IllegalArgumentException("srcRows may not contain null rows. Found null row at index " + index + ".");
            }
            if (srcStartRow.getSheet().getWorkbook() != curRow.getSheet().getWorkbook()) {
                throw new IllegalArgumentException("All rows in srcRows must belong to the same sheet in the same workbook. Expected all rows from same workbook (" + srcStartRow.getSheet().getWorkbook() + "). Got srcRows[" + index + "] from different workbook (" + curRow.getSheet().getWorkbook() + ").");
            }
            if (srcStartRow.getSheet() != curRow.getSheet()) {
                throw new IllegalArgumentException("All rows in srcRows must belong to the same sheet. Expected all rows from " + srcStartRow.getSheet().getSheetName() + ". Got srcRows[" + index + "] from " + curRow.getSheet().getSheetName());
            }
        }
        CellCopyPolicy options = new CellCopyPolicy(policy);
        options.setCopyMergedRegions(false);
        int destRowNum = destStartRow;
        for (Row srcRow : srcRows) {
            if (policy.isCondenseRows()) {
                shift = destRowNum + 1;
            } else {
                int r = srcRow.getRowNum();
                int i = destStartRow + (r - srcStartRowNum);
                shift = destRowNum;
                destRowNum = i;
            }
            XSSFRow destRow = createRow(destRowNum);
            destRow.copyRowFrom(srcRow, options, context);
            destRowNum = shift;
        }
        if (policy.isCopyMergedRegions()) {
            int shift2 = destStartRow - srcStartRowNum;
            for (CellRangeAddress srcRegion : srcStartRow.getSheet().getMergedRegions()) {
                if (srcStartRowNum <= srcRegion.getFirstRow() && srcRegion.getLastRow() <= srcEndRowNum) {
                    CellRangeAddress destRegion = srcRegion.copy();
                    destRegion.setFirstRow(destRegion.getFirstRow() + shift2);
                    destRegion.setLastRow(destRegion.getLastRow() + shift2);
                    addMergedRegion(destRegion);
                }
            }
        }
    }

    public void copyRows(int srcStartRow, int srcEndRow, int destStartRow, CellCopyPolicy cellCopyPolicy) {
        copyRows(srcStartRow, srcEndRow, destStartRow, cellCopyPolicy, new CellCopyContext());
    }

    public void copyRows(int srcStartRow, int srcEndRow, int destStartRow, CellCopyPolicy cellCopyPolicy, CellCopyContext context) {
        List<XSSFRow> srcRows = getRows(srcStartRow, srcEndRow, false);
        copyRows(srcRows, destStartRow, cellCopyPolicy, context);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void shiftRows(int startRow, int endRow, int n) {
        shiftRows(startRow, endRow, n, false, false);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void shiftRows(int startRow, int endRow, int n, boolean copyRowHeight, boolean resetOriginalRowHeight) {
        List<XSSFTable> overlappingTables = new ArrayList<>();
        for (XSSFTable table : getTables()) {
            if (table.getStartRowIndex() >= startRow || table.getEndRowIndex() >= startRow) {
                if (table.getStartRowIndex() <= endRow || table.getEndRowIndex() <= endRow) {
                    overlappingTables.add(table);
                }
            }
        }
        int sheetIndex = getWorkbook().getSheetIndex(this);
        String sheetName = getWorkbook().getSheetName(sheetIndex);
        FormulaShifter formulaShifter = FormulaShifter.createForRowShift(sheetIndex, sheetName, startRow, endRow, n, SpreadsheetVersion.EXCEL2007);
        removeOverwritten(startRow, endRow, n);
        shiftCommentsAndRows(startRow, endRow, n);
        XSSFRowShifter rowShifter = new XSSFRowShifter(this);
        rowShifter.shiftMergedRegions(startRow, endRow, n);
        rowShifter.updateNamedRanges(formulaShifter);
        rowShifter.updateFormulas(formulaShifter);
        rowShifter.updateConditionalFormatting(formulaShifter);
        rowShifter.updateHyperlinks(formulaShifter);
        rebuildRows();
        Iterator<XSSFTable> it = overlappingTables.iterator();
        while (it.hasNext()) {
            rebuildTableFormulas(it.next());
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void shiftColumns(int startColumn, int endColumn, int n) {
        List<XSSFTable> overlappingTables = new ArrayList<>();
        for (XSSFTable table : getTables()) {
            if (table.getStartColIndex() >= startColumn || table.getEndColIndex() >= startColumn) {
                if (table.getStartColIndex() <= endColumn || table.getEndColIndex() <= endColumn) {
                    overlappingTables.add(table);
                }
            }
        }
        XSSFVMLDrawing vml = getVMLDrawing(false);
        shiftCommentsForColumns(vml, startColumn, endColumn, n);
        FormulaShifter formulaShifter = FormulaShifter.createForColumnShift(getWorkbook().getSheetIndex(this), getSheetName(), startColumn, endColumn, n, SpreadsheetVersion.EXCEL2007);
        XSSFColumnShifter columnShifter = new XSSFColumnShifter(this);
        columnShifter.shiftColumns(startColumn, endColumn, n);
        columnShifter.shiftMergedRegions(startColumn, endColumn, n);
        columnShifter.updateFormulas(formulaShifter);
        columnShifter.updateConditionalFormatting(formulaShifter);
        columnShifter.updateHyperlinks(formulaShifter);
        columnShifter.updateNamedRanges(formulaShifter);
        rebuildRows();
        Iterator<XSSFTable> it = overlappingTables.iterator();
        while (it.hasNext()) {
            rebuildTableFormulas(it.next());
        }
    }

    private void rebuildTableFormulas(XSSFTable table) {
        for (CTTableColumn tableCol : table.getCTTable().getTableColumns().getTableColumnList()) {
            if (tableCol.getCalculatedColumnFormula() != null) {
                int id = Math.toIntExact(tableCol.getId());
                String formula = tableCol.getCalculatedColumnFormula().getStringValue();
                int rFirst = table.getStartCellReference().getRow() + table.getHeaderRowCount();
                int rLast = table.getEndCellReference().getRow() - table.getTotalsRowCount();
                int c = (table.getStartCellReference().getCol() + id) - 1;
                boolean cellFormulaValidationFlag = getWorkbook().getCellFormulaValidation();
                try {
                    getWorkbook().setCellFormulaValidation(false);
                    for (int r = rFirst; r <= rLast; r++) {
                        XSSFRow row = getRow(r);
                        if (row == null) {
                            row = createRow(r);
                        }
                        XSSFCell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        cell.setCellFormula(formula);
                    }
                } finally {
                    getWorkbook().setCellFormulaValidation(cellFormulaValidationFlag);
                }
            }
        }
    }

    private void rebuildRows() {
        SortedMap<Long, CTRow> ctRows = new TreeMap<>();
        CTSheetData sheetData = getCTWorksheet().getSheetData();
        for (CTRow ctRow : sheetData.getRowList()) {
            Long rownumL = Long.valueOf(ctRow.getR());
            ctRows.put(rownumL, ctRow);
        }
        List<CTRow> ctRowList = new ArrayList<>(ctRows.values());
        CTRow[] ctRowArray = new CTRow[ctRowList.size()];
        sheetData.setRowArray((CTRow[]) ctRowList.toArray(ctRowArray));
        this._rows.clear();
        Iterator<CTRow> it = sheetData.getRowList().iterator();
        while (it.hasNext()) {
            XSSFRow row = new XSSFRow(it.next(), this);
            Integer rownumI = Integer.valueOf(Math.toIntExact(row.getRowNum()));
            this._rows.put(rownumI, row);
        }
    }

    private void removeOverwritten(int startRow, int endRow, int n) {
        XSSFVMLDrawing vml = getVMLDrawing(false);
        HashSet<Integer> rowsToRemoveSet = new HashSet<>();
        Iterator<Row> it = rowIterator();
        while (it.hasNext()) {
            XSSFRow row = (XSSFRow) it.next();
            int rownum = row.getRowNum();
            if (shouldRemoveRow(startRow, endRow, n, rownum)) {
                rowsToRemoveSet.add(Integer.valueOf(rownum));
                Iterator<Cell> it2 = row.iterator();
                while (it2.hasNext()) {
                    Cell c = it2.next();
                    if (!c.isPartOfArrayFormulaGroup()) {
                        c.setBlank();
                    }
                }
                Integer rownumI = Integer.valueOf(row.getRowNum());
                int idx = this._rows.headMap(rownumI).size();
                this.worksheet.getSheetData().removeRow(idx);
                it.remove();
            }
        }
        if (this.sheetComments != null) {
            ArrayList<CellAddress> refsToRemove = new ArrayList<>();
            Iterator<CellAddress> commentAddressIterator = this.sheetComments.getCellAddresses();
            while (commentAddressIterator.hasNext()) {
                CellAddress ref = commentAddressIterator.next();
                if (rowsToRemoveSet.contains(Integer.valueOf(ref.getRow()))) {
                    refsToRemove.add(ref);
                }
            }
            Iterator<CellAddress> it3 = refsToRemove.iterator();
            while (it3.hasNext()) {
                CellAddress ref2 = it3.next();
                this.sheetComments.removeComment(ref2);
                if (vml != null) {
                    vml.removeCommentShape(ref2.getRow(), ref2.getColumn());
                }
            }
        }
        if (this.hyperlinks != null) {
            Iterator it4 = new ArrayList(this.hyperlinks).iterator();
            while (it4.hasNext()) {
                XSSFHyperlink link = (XSSFHyperlink) it4.next();
                CellRangeAddress range = CellRangeAddress.valueOf(link.getCellRef());
                if (range.getFirstRow() == range.getLastRow() && rowsToRemoveSet.contains(Integer.valueOf(range.getFirstRow()))) {
                    removeHyperlink(link);
                } else if (range.getFirstRow() != range.getLastRow()) {
                    boolean toRemove = true;
                    for (int i = range.getFirstRow(); i <= range.getLastRow() && toRemove; i++) {
                        toRemove = rowsToRemoveSet.contains(Integer.valueOf(i));
                    }
                    if (toRemove) {
                        removeHyperlink(link);
                    }
                }
            }
        }
    }

    private void shiftCommentsAndRows(int startRow, int endRow, final int n) {
        int newrownum;
        XSSFComment oldComment;
        SortedMap<XSSFComment, Integer> commentsToShift = new TreeMap<>((Comparator<? super XSSFComment>) new Comparator() { // from class: org.apache.poi.xssf.usermodel.XSSFSheet$$ExternalSyntheticLambda3
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return XSSFSheet.lambda$shiftCommentsAndRows$0(n, (XSSFComment) obj, (XSSFComment) obj2);
            }
        });
        Iterator<Row> it = rowIterator();
        while (it.hasNext()) {
            XSSFRow row = (XSSFRow) it.next();
            int rownum = row.getRowNum();
            if (this.sheetComments != null && (newrownum = shiftedRowNum(startRow, endRow, n, rownum)) != rownum) {
                Iterator<CellAddress> commentAddressIterator = this.sheetComments.getCellAddresses();
                while (commentAddressIterator.hasNext()) {
                    CellAddress cellAddress = commentAddressIterator.next();
                    if (cellAddress.getRow() == rownum && (oldComment = this.sheetComments.findCellComment(cellAddress)) != null) {
                        XSSFComment xssfComment = new XSSFComment(this.sheetComments, oldComment.getCTComment(), oldComment.getCTShape());
                        commentsToShift.put(xssfComment, Integer.valueOf(newrownum));
                    }
                }
            }
            if (rownum >= startRow && rownum <= endRow) {
                row.shift(n);
            }
        }
        for (Map.Entry<XSSFComment, Integer> entry : commentsToShift.entrySet()) {
            entry.getKey().setRow(entry.getValue().intValue());
        }
        rebuildRows();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$shiftCommentsAndRows$0(int n, XSSFComment o1, XSSFComment o2) {
        int row1 = o1.getRow();
        int row2 = o2.getRow();
        if (row1 == row2) {
            return o1.hashCode() - o2.hashCode();
        }
        return n > 0 ? row1 < row2 ? 1 : -1 : row1 > row2 ? 1 : -1;
    }

    private int shiftedRowNum(int startRow, int endRow, int n, int rownum) {
        if (rownum < startRow && (n > 0 || startRow - rownum > n)) {
            return rownum;
        }
        if (rownum > endRow && (n < 0 || rownum - endRow > n)) {
            return rownum;
        }
        if (rownum < startRow) {
            return (endRow - startRow) + rownum;
        }
        if (rownum > endRow) {
            return rownum - (endRow - startRow);
        }
        return rownum + n;
    }

    private void shiftCommentsForColumns(XSSFVMLDrawing vml, int startColumnIndex, int endColumnIndex, final int n) {
        XSSFComment oldComment;
        SortedMap<XSSFComment, Integer> commentsToShift = new TreeMap<>((Comparator<? super XSSFComment>) new Comparator() { // from class: org.apache.poi.xssf.usermodel.XSSFSheet$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return XSSFSheet.lambda$shiftCommentsForColumns$1(n, (XSSFComment) obj, (XSSFComment) obj2);
            }
        });
        if (this.sheetComments != null) {
            Iterator<CellAddress> commentAddressIterator = this.sheetComments.getCellAddresses();
            while (commentAddressIterator.hasNext()) {
                CellAddress oldCommentAddress = commentAddressIterator.next();
                int columnIndex = oldCommentAddress.getColumn();
                int newColumnIndex = shiftedRowNum(startColumnIndex, endColumnIndex, n, columnIndex);
                if (newColumnIndex != columnIndex && (oldComment = this.sheetComments.findCellComment(oldCommentAddress)) != null) {
                    XSSFComment xssfComment = new XSSFComment(this.sheetComments, oldComment.getCTComment(), oldComment.getCTShape());
                    commentsToShift.put(xssfComment, Integer.valueOf(newColumnIndex));
                }
            }
        }
        for (Map.Entry<XSSFComment, Integer> entry : commentsToShift.entrySet()) {
            entry.getKey().setColumn(entry.getValue().intValue());
        }
        rebuildRows();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$shiftCommentsForColumns$1(int n, XSSFComment o1, XSSFComment o2) {
        int column1 = o1.getColumn();
        int column2 = o2.getColumn();
        if (column1 == column2) {
            return o1.hashCode() - o2.hashCode();
        }
        return n > 0 ? column1 < column2 ? 1 : -1 : column1 > column2 ? 1 : -1;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void showInPane(int topRow, int leftCol) {
        CellReference cellReference = new CellReference(topRow, leftCol);
        String cellRef = cellReference.formatAsString();
        CTPane pane = getPane(true);
        if (pane == null) {
            throw new AssertionError();
        }
        pane.setTopLeftCell(cellRef);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void ungroupColumn(int fromColumn, int toColumn) {
        CTCols cols = this.worksheet.getColsArray(0);
        int maxLevelCol = -1;
        int index = fromColumn;
        while (index <= toColumn) {
            CTCol col = this.columnHelper.getColumn(index, false);
            if (col != null) {
                short outlineLevel = col.getOutlineLevel();
                maxLevelCol = Math.max(maxLevelCol, (int) outlineLevel);
                col.setOutlineLevel((short) (outlineLevel - 1));
                index = Math.toIntExact(col.getMax());
                if (col.getOutlineLevel() <= 0) {
                    int colIndex = this.columnHelper.getIndexOfColumn(cols, col);
                    this.worksheet.getColsArray(0).removeCol(colIndex);
                }
            }
            index++;
        }
        this.worksheet.setColsArray(0, cols);
        if (maxLevelCol >= getSheetFormatPrOutlineLevelCol()) {
            setSheetFormatPrOutlineLevelCol();
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void ungroupRow(int fromRow, int toRow) {
        int maxOutlineLevel = -1;
        for (int i = fromRow; i <= toRow; i++) {
            XSSFRow xrow = getRow(i);
            if (xrow != null) {
                CTRow ctRow = xrow.getCTRow();
                short outlineLevel = ctRow.getOutlineLevel();
                ctRow.setOutlineLevel((short) (outlineLevel - 1));
                maxOutlineLevel = Math.max(maxOutlineLevel, (int) outlineLevel);
                if (outlineLevel == 1 && xrow.getFirstCellNum() == -1) {
                    removeRow(xrow);
                }
            }
        }
        int i2 = getSheetFormatPrOutlineLevelRow();
        if (maxOutlineLevel >= i2) {
            setSheetFormatPrOutlineLevelRow();
        }
    }

    private void increaseSheetFormatPrOutlineLevelRowIfNecessary(short levelRow) {
        if (levelRow > getSheetFormatPrOutlineLevelRow()) {
            getSheetTypeSheetFormatPr(true).setOutlineLevelRow(levelRow);
        }
    }

    private void increaseSheetFormatPrOutlineLevelColIfNecessary(short levelCol) {
        if (levelCol > getSheetFormatPrOutlineLevelCol()) {
            getSheetTypeSheetFormatPr(true).setOutlineLevelCol(levelCol);
        }
    }

    private void setSheetFormatPrOutlineLevelRow() {
        short maxLevelRow = getMaxOutlineLevelRows();
        getSheetTypeSheetFormatPr(true).setOutlineLevelRow(maxLevelRow);
    }

    @Internal
    public short getSheetFormatPrOutlineLevelRow() {
        CTSheetFormatPr pr = getSheetTypeSheetFormatPr(false);
        if (pr == null) {
            return (short) 0;
        }
        return pr.getOutlineLevelRow();
    }

    @Internal
    public short getSheetFormatPrOutlineLevelCol() {
        CTSheetFormatPr pr = getSheetTypeSheetFormatPr(false);
        if (pr == null) {
            return (short) 0;
        }
        return pr.getOutlineLevelCol();
    }

    private void setSheetFormatPrOutlineLevelCol() {
        short maxLevelCol = getMaxOutlineLevelCols();
        getSheetTypeSheetFormatPr(true).setOutlineLevelCol(maxLevelCol);
    }

    protected CTSheetViews getSheetTypeSheetViews(boolean create) {
        CTSheetViews views = (this.worksheet.isSetSheetViews() || !create) ? this.worksheet.getSheetViews() : this.worksheet.addNewSheetViews();
        if (views == null && create) {
            throw new AssertionError();
        }
        if (views == null) {
            return null;
        }
        if (views.sizeOfSheetViewArray() == 0 && create) {
            views.addNewSheetView();
        }
        return views;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public boolean isSelected() {
        CTSheetView dsv = getDefaultSheetView(false);
        return dsv != null && dsv.getTabSelected();
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setSelected(boolean value) {
        CTSheetViews views = getSheetTypeSheetViews(true);
        if (views == null) {
            throw new AssertionError();
        }
        for (CTSheetView view : views.getSheetViewArray()) {
            view.setTabSelected(value);
        }
    }

    public void addHyperlink(XSSFHyperlink hyperlink) {
        this.hyperlinks.add(hyperlink);
    }

    public void removeHyperlink(XSSFHyperlink hyperlink) {
        this.hyperlinks.remove(hyperlink);
    }

    @Internal
    public void removeHyperlink(int row, int column) {
        XSSFHyperlink hyperlink = getHyperlink(row, column);
        if (hyperlink != null) {
            if (hyperlink.getFirstRow() == row && hyperlink.getLastRow() == row && hyperlink.getFirstColumn() == column && hyperlink.getLastColumn() == column) {
                removeHyperlink(hyperlink);
                return;
            }
            boolean leftCreated = false;
            boolean rightCreated = false;
            if (hyperlink.getFirstColumn() < column) {
                XSSFHyperlink newLink = new XSSFHyperlink(hyperlink);
                newLink.setFirstColumn(hyperlink.getFirstColumn());
                newLink.setLastColumn(column - 1);
                newLink.setFirstRow(hyperlink.getFirstRow());
                newLink.setLastRow(hyperlink.getLastRow());
                addHyperlink(newLink);
                leftCreated = true;
            }
            if (hyperlink.getLastColumn() > column) {
                XSSFHyperlink newLink2 = new XSSFHyperlink(hyperlink);
                newLink2.setFirstColumn(column + 1);
                newLink2.setLastColumn(hyperlink.getLastColumn());
                newLink2.setFirstRow(hyperlink.getFirstRow());
                newLink2.setLastRow(hyperlink.getLastRow());
                addHyperlink(newLink2);
                rightCreated = true;
            }
            if (hyperlink.getFirstRow() < row) {
                XSSFHyperlink newLink3 = new XSSFHyperlink(hyperlink);
                int firstColumn = leftCreated ? row : hyperlink.getFirstColumn();
                int lastColumn = rightCreated ? row : hyperlink.getLastColumn();
                newLink3.setFirstColumn(firstColumn);
                newLink3.setLastColumn(lastColumn);
                newLink3.setFirstRow(hyperlink.getFirstRow());
                newLink3.setLastRow(row - 1);
                addHyperlink(newLink3);
            }
            if (hyperlink.getLastRow() > row) {
                XSSFHyperlink newLink4 = new XSSFHyperlink(hyperlink);
                int firstColumn2 = leftCreated ? row : hyperlink.getFirstColumn();
                int lastColumn2 = rightCreated ? row : hyperlink.getLastColumn();
                newLink4.setFirstColumn(firstColumn2);
                newLink4.setLastColumn(lastColumn2);
                newLink4.setFirstRow(row + 1);
                newLink4.setLastRow(hyperlink.getLastRow());
                addHyperlink(newLink4);
            }
            removeHyperlink(hyperlink);
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public CellAddress getActiveCell() {
        CTSelection sts = getSheetTypeSelection(false);
        String address = sts != null ? sts.getActiveCell() : null;
        if (address != null) {
            return new CellAddress(address);
        }
        return null;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setActiveCell(CellAddress address) {
        CTSelection ctsel = getSheetTypeSelection(true);
        if (ctsel == null) {
            throw new AssertionError();
        }
        String ref = address.formatAsString();
        ctsel.setActiveCell(ref);
        ctsel.setSqref(Collections.singletonList(ref));
    }

    public boolean hasComments() {
        return this.sheetComments != null && this.sheetComments.getNumberOfComments() > 0;
    }

    protected int getNumberOfComments() {
        if (this.sheetComments == null) {
            return 0;
        }
        return this.sheetComments.getNumberOfComments();
    }

    private CTSelection getSheetTypeSelection(boolean create) {
        CTSheetView dsv = getDefaultSheetView(create);
        if (dsv == null && create) {
            throw new AssertionError();
        }
        if (dsv == null) {
            return null;
        }
        int sz = dsv.sizeOfSelectionArray();
        if (sz == 0) {
            if (create) {
                return dsv.addNewSelection();
            }
            return null;
        }
        return dsv.getSelectionArray(sz - 1);
    }

    private CTSheetView getDefaultSheetView(boolean create) {
        CTSheetViews views = getSheetTypeSheetViews(create);
        if (views == null && create) {
            throw new AssertionError();
        }
        if (views == null) {
            return null;
        }
        int sz = views.sizeOfSheetViewArray();
        if (sz <= 0 && create) {
            throw new AssertionError();
        }
        if (sz == 0) {
            return null;
        }
        return views.getSheetViewArray(sz - 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Comments getCommentsTable(boolean create) {
        if (this.sheetComments == null && create) {
            try {
                this.sheetComments = (Comments) createRelationship(XSSFRelation.SHEET_COMMENTS, getWorkbook().getXssfFactory(), Math.toIntExact(this.sheet.getSheetId()));
            } catch (PartAlreadyExistsException e) {
                this.sheetComments = (Comments) createRelationship(XSSFRelation.SHEET_COMMENTS, getWorkbook().getXssfFactory(), -1);
            }
            if (this.sheetComments != null) {
                this.sheetComments.setSheet(this);
            }
        }
        return this.sheetComments;
    }

    private CTPageSetUpPr getSheetTypePageSetUpPr() {
        CTSheetPr sheetPr = getSheetTypeSheetPr();
        return sheetPr.isSetPageSetUpPr() ? sheetPr.getPageSetUpPr() : sheetPr.addNewPageSetUpPr();
    }

    private static boolean shouldRemoveRow(int startRow, int endRow, int n, int rownum) {
        if (rownum < startRow + n || rownum > endRow + n) {
            return false;
        }
        if (n <= 0 || rownum <= endRow) {
            return n < 0 && rownum < startRow;
        }
        return true;
    }

    private CTPane getPane(boolean create) {
        CTSheetView dsv = getDefaultSheetView(create);
        if (dsv == null && create) {
            throw new AssertionError();
        }
        if (dsv == null) {
            return null;
        }
        return (dsv.isSetPane() || !create) ? dsv.getPane() : dsv.addNewPane();
    }

    @Internal
    public CTCellFormula getSharedFormula(int sid) {
        return this.sharedFormulas.get(Integer.valueOf(sid));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onReadCell(XSSFCell cell) {
        CTCell ct = cell.getCTCell();
        CTCellFormula f = ct.getF();
        if (f != null && f.getT() == STCellFormulaType.SHARED && f.isSetRef() && f.getStringValue() != null) {
            CTCellFormula sf = (CTCellFormula) f.copy();
            CellRangeAddress sfRef = CellRangeAddress.valueOf(sf.getRef());
            CellReference cellRef = new CellReference(cell);
            if (cellRef.getCol() > sfRef.getFirstColumn() || cellRef.getRow() > sfRef.getFirstRow()) {
                String effectiveRef = new CellRangeAddress(Math.max(cellRef.getRow(), sfRef.getFirstRow()), Math.max(cellRef.getRow(), sfRef.getLastRow()), Math.max((int) cellRef.getCol(), sfRef.getFirstColumn()), Math.max((int) cellRef.getCol(), sfRef.getLastColumn())).formatAsString();
                sf.setRef(effectiveRef);
            }
            this.sharedFormulas.put(Integer.valueOf(Math.toIntExact(f.getSi())), sf);
        }
        if (f != null && f.getT() == STCellFormulaType.ARRAY && f.getRef() != null) {
            this.arrayFormulas.add(CellRangeAddress.valueOf(f.getRef()));
        }
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            write(out);
            if (out != null) {
                out.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (out != null) {
                    try {
                        out.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void write(OutputStream out) throws IOException {
        if (this.worksheet == null) {
            throw new POIXMLException("Cannot write invalid sheet, internal data is missing");
        }
        boolean setToNull = false;
        if (this.worksheet.sizeOfColsArray() == 1) {
            CTCols col = this.worksheet.getColsArray(0);
            if (col.sizeOfColArray() == 0) {
                setToNull = true;
                this.worksheet.setColsArray(null);
            } else {
                setColWidthAttribute(col);
            }
        }
        if (!this.hyperlinks.isEmpty()) {
            if (this.worksheet.getHyperlinks() == null) {
                this.worksheet.addNewHyperlinks();
            }
            CTHyperlink[] ctHls = new CTHyperlink[this.hyperlinks.size()];
            for (int i = 0; i < ctHls.length; i++) {
                XSSFHyperlink hyperlink = this.hyperlinks.get(i);
                hyperlink.generateRelationIfNeeded(getPackagePart());
                ctHls[i] = hyperlink.getCTHyperlink();
            }
            this.worksheet.getHyperlinks().setHyperlinkArray(ctHls);
        } else if (this.worksheet.getHyperlinks() != null) {
            int count = this.worksheet.getHyperlinks().sizeOfHyperlinkArray();
            for (int i2 = count - 1; i2 >= 0; i2--) {
                this.worksheet.getHyperlinks().removeHyperlink(i2);
            }
            this.worksheet.unsetHyperlinks();
        }
        CellRangeAddress cellRangeAddress = this.dimensionOverride;
        if (cellRangeAddress == null) {
            int minCell = Integer.MAX_VALUE;
            int maxCell = Integer.MIN_VALUE;
            for (Map.Entry<Integer, XSSFRow> entry : this._rows.entrySet()) {
                XSSFRow row = entry.getValue();
                row.onDocumentWrite();
                if (row.getFirstCellNum() != -1) {
                    minCell = Math.min(minCell, (int) row.getFirstCellNum());
                }
                if (row.getLastCellNum() != -1) {
                    maxCell = Math.max(maxCell, row.getLastCellNum() - 1);
                }
            }
            if (minCell != Integer.MAX_VALUE) {
                cellRangeAddress = new CellRangeAddress(getFirstRowNum(), getLastRowNum(), minCell, maxCell);
            }
        } else {
            for (Map.Entry<Integer, XSSFRow> entry2 : this._rows.entrySet()) {
                entry2.getValue().onDocumentWrite();
            }
        }
        if (cellRangeAddress != null) {
            if (this.worksheet.isSetDimension()) {
                this.worksheet.getDimension().setRef(cellRangeAddress.formatAsString());
            } else {
                this.worksheet.addNewDimension().setRef(cellRangeAddress.formatAsString());
            }
        }
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTWorksheet.type.getName().getNamespaceURI(), "worksheet"));
        this.worksheet.save(out, xmlOptions);
        if (setToNull) {
            this.worksheet.addNewCols();
        }
    }

    public boolean isAutoFilterLocked() {
        return isSheetLocked() && safeGetProtectionField().getAutoFilter();
    }

    public boolean isDeleteColumnsLocked() {
        return isSheetLocked() && safeGetProtectionField().getDeleteColumns();
    }

    public boolean isDeleteRowsLocked() {
        return isSheetLocked() && safeGetProtectionField().getDeleteRows();
    }

    public boolean isFormatCellsLocked() {
        return isSheetLocked() && safeGetProtectionField().getFormatCells();
    }

    public boolean isFormatColumnsLocked() {
        return isSheetLocked() && safeGetProtectionField().getFormatColumns();
    }

    public boolean isFormatRowsLocked() {
        return isSheetLocked() && safeGetProtectionField().getFormatRows();
    }

    public boolean isInsertColumnsLocked() {
        return isSheetLocked() && safeGetProtectionField().getInsertColumns();
    }

    public boolean isInsertHyperlinksLocked() {
        return isSheetLocked() && safeGetProtectionField().getInsertHyperlinks();
    }

    public boolean isInsertRowsLocked() {
        return isSheetLocked() && safeGetProtectionField().getInsertRows();
    }

    public boolean isPivotTablesLocked() {
        return isSheetLocked() && safeGetProtectionField().getPivotTables();
    }

    public boolean isSortLocked() {
        return isSheetLocked() && safeGetProtectionField().getSort();
    }

    public boolean isObjectsLocked() {
        return isSheetLocked() && safeGetProtectionField().getObjects();
    }

    public boolean isScenariosLocked() {
        return isSheetLocked() && safeGetProtectionField().getScenarios();
    }

    public boolean isSelectLockedCellsLocked() {
        return isSheetLocked() && safeGetProtectionField().getSelectLockedCells();
    }

    public boolean isSelectUnlockedCellsLocked() {
        return isSheetLocked() && safeGetProtectionField().getSelectUnlockedCells();
    }

    public boolean isSheetLocked() {
        return this.worksheet.isSetSheetProtection() && safeGetProtectionField().getSheet();
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

    public CellRangeAddress getDimension() {
        if (this.dimensionOverride != null) {
            return this.dimensionOverride;
        }
        CTSheetDimension ctSheetDimension = this.worksheet.getDimension();
        String ref = ctSheetDimension == null ? null : ctSheetDimension.getRef();
        if (ref == null) {
            return null;
        }
        return CellRangeAddress.valueOf(ref);
    }

    private CTSheetProtection safeGetProtectionField() {
        if (!isSheetProtectionEnabled()) {
            return this.worksheet.addNewSheetProtection();
        }
        return this.worksheet.getSheetProtection();
    }

    boolean isSheetProtectionEnabled() {
        return this.worksheet.isSetSheetProtection();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isCellInArrayFormulaContext(XSSFCell cell) {
        int rowIndex = cell.getRowIndex();
        int columnIndex = cell.getColumnIndex();
        for (CellRangeAddress range : this.arrayFormulas) {
            if (range.isInRange(rowIndex, columnIndex)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSSFCell getFirstCellInArrayFormula(XSSFCell cell) {
        int rowIndex = cell.getRowIndex();
        int columnIndex = cell.getColumnIndex();
        for (CellRangeAddress range : this.arrayFormulas) {
            if (range.isInRange(rowIndex, columnIndex)) {
                return getRow(range.getFirstRow()).getCell(range.getFirstColumn());
            }
        }
        return null;
    }

    private CellRange<XSSFCell> getCellRange(CellRangeAddress range) {
        int firstRow = range.getFirstRow();
        int firstColumn = range.getFirstColumn();
        int lastRow = range.getLastRow();
        int lastColumn = range.getLastColumn();
        int height = (lastRow - firstRow) + 1;
        int width = (lastColumn - firstColumn) + 1;
        List<XSSFCell> temp = new ArrayList<>(height * width);
        for (int rowIn = firstRow; rowIn <= lastRow; rowIn++) {
            for (int colIn = firstColumn; colIn <= lastColumn; colIn++) {
                XSSFRow row = getRow(rowIn);
                if (row == null) {
                    row = createRow(rowIn);
                }
                XSSFCell cell = row.getCell(colIn);
                if (cell == null) {
                    cell = row.createCell(colIn);
                }
                temp.add(cell);
            }
        }
        return SSCellRange.create(firstRow, firstColumn, height, width, temp, XSSFCell.class);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public CellRange<XSSFCell> setArrayFormula(String formula, CellRangeAddress range) {
        CellRange<XSSFCell> cr = getCellRange(range);
        XSSFCell mainArrayFormulaCell = cr.getTopLeftCell();
        mainArrayFormulaCell.setCellArrayFormula(formula, range);
        this.arrayFormulas.add(range);
        return cr;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public CellRange<XSSFCell> removeArrayFormula(Cell cell) {
        if (cell.getSheet() != this) {
            throw new IllegalArgumentException("Specified cell does not belong to this sheet.");
        }
        for (CellRangeAddress range : this.arrayFormulas) {
            if (range.isInRange(cell)) {
                this.arrayFormulas.remove(range);
                CellRange<XSSFCell> cr = getCellRange(range);
                for (XSSFCell c : cr) {
                    c.setBlank();
                }
                return cr;
            }
        }
        String ref = new CellReference(cell).formatAsString();
        throw new IllegalArgumentException("Cell " + ref + " is not part of an array formula.");
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public DataValidationHelper getDataValidationHelper() {
        return this.dataValidationHelper;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public List<XSSFDataValidation> getDataValidations() {
        List<XSSFDataValidation> xssfValidations = new ArrayList<>();
        CTDataValidations dataValidations = this.worksheet.getDataValidations();
        if (dataValidations != null) {
            CTDataValidation[] dataValidationArray = dataValidations.getDataValidationArray();
            int length = dataValidationArray.length;
            for (int i = 0; i < length; i++) {
                CTDataValidation ctDataValidation = dataValidationArray[i];
                CellRangeAddressList addressList = new CellRangeAddressList();
                List<String> sqref = ctDataValidation.getSqref();
                for (String stRef : sqref) {
                    String[] regions = stRef.split(StringUtils.SPACE);
                    int length2 = regions.length;
                    int i2 = 0;
                    while (i2 < length2) {
                        String region = regions[i2];
                        String[] parts = region.split(":");
                        CTDataValidations dataValidations2 = dataValidations;
                        CellReference begin = new CellReference(parts[0]);
                        CellReference end = parts.length > 1 ? new CellReference(parts[1]) : begin;
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(begin.getRow(), end.getRow(), begin.getCol(), end.getCol());
                        addressList.addCellRangeAddress(cellRangeAddress);
                        i2++;
                        dataValidations = dataValidations2;
                        dataValidationArray = dataValidationArray;
                        length = length;
                    }
                }
                XSSFDataValidation xssfDataValidation = new XSSFDataValidation(addressList, ctDataValidation);
                xssfValidations.add(xssfDataValidation);
            }
        }
        return xssfValidations;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void addValidationData(DataValidation dataValidation) {
        XSSFDataValidation xssfDataValidation = (XSSFDataValidation) dataValidation;
        CTDataValidations dataValidations = this.worksheet.getDataValidations();
        if (dataValidations == null) {
            dataValidations = this.worksheet.addNewDataValidations();
        }
        int currentCount = dataValidations.sizeOfDataValidationArray();
        CTDataValidation newval = dataValidations.addNewDataValidation();
        newval.set(xssfDataValidation.getCtDataValidation());
        dataValidations.setCount(currentCount + 1);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFAutoFilter setAutoFilter(CellRangeAddress range) {
        CTAutoFilter af = this.worksheet.getAutoFilter();
        if (af == null) {
            af = this.worksheet.addNewAutoFilter();
        }
        CellRangeAddress norm = new CellRangeAddress(range.getFirstRow(), range.getLastRow(), range.getFirstColumn(), range.getLastColumn());
        String ref = norm.formatAsString();
        af.setRef(ref);
        XSSFWorkbook wb = getWorkbook();
        int sheetIndex = getWorkbook().getSheetIndex(this);
        XSSFName name = wb.getBuiltInName(XSSFName.BUILTIN_FILTER_DB, sheetIndex);
        if (name == null) {
            name = wb.createBuiltInName(XSSFName.BUILTIN_FILTER_DB, sheetIndex);
        }
        name.getCTName().setHidden(true);
        CellReference r1 = new CellReference(getSheetName(), range.getFirstRow(), range.getFirstColumn(), true, true);
        CellReference r2 = new CellReference(null, range.getLastRow(), range.getLastColumn(), true, true);
        String fmla = r1.formatAsString() + ":" + r2.formatAsString();
        name.setRefersToFormula(fmla);
        return new XSSFAutoFilter(this);
    }

    public XSSFTable createTable(AreaReference tableArea) {
        if (!this.worksheet.isSetTableParts()) {
            this.worksheet.addNewTableParts();
        }
        CTTableParts tblParts = this.worksheet.getTableParts();
        CTTablePart tbl = tblParts.addNewTablePart();
        int tableNumber = getPackagePart().getPackage().getPartsByContentType(XSSFRelation.TABLE.getContentType()).size() + 1;
        boolean loop = true;
        while (loop) {
            loop = false;
            Iterator<PackagePart> it = getPackagePart().getPackage().getPartsByContentType(XSSFRelation.TABLE.getContentType()).iterator();
            while (it.hasNext()) {
                PackagePart packagePart = it.next();
                String fileName = XSSFRelation.TABLE.getFileName(tableNumber);
                if (fileName.equals(packagePart.getPartName().getName())) {
                    tableNumber++;
                    loop = true;
                }
            }
        }
        POIXMLDocumentPart.RelationPart rp = createRelationship(XSSFRelation.TABLE, getWorkbook().getXssfFactory(), tableNumber, false);
        XSSFTable table = (XSSFTable) rp.getDocumentPart();
        tbl.setId(rp.getRelationship().getId());
        table.getCTTable().setId(tableNumber);
        this.tables.put(tbl.getId(), table);
        if (tableArea != null && table.supportsAreaReference(tableArea)) {
            table.setArea(tableArea);
        }
        while (true) {
            if (tableNumber >= Integer.MAX_VALUE) {
                break;
            }
            String displayName = "Table" + tableNumber;
            if (getWorkbook().getTable(displayName) == null && getWorkbook().getName(displayName) == null) {
                table.setDisplayName(displayName);
                table.setName(displayName);
                break;
            }
            tableNumber++;
        }
        return table;
    }

    public List<XSSFTable> getTables() {
        return new ArrayList(this.tables.values());
    }

    public void removeTable(XSSFTable t) {
        String rId = getRelationId(t);
        long id = t.getCTTable().getId();
        Map.Entry<String, XSSFTable> toDelete = null;
        for (Map.Entry<String, XSSFTable> entry : this.tables.entrySet()) {
            if (entry.getValue().getCTTable().getId() == id) {
                toDelete = entry;
            }
        }
        if (toDelete != null) {
            removeRelation(getRelationById(toDelete.getKey()), true);
            this.tables.remove(toDelete.getKey());
            toDelete.getValue().onTableDelete();
            CTTableParts tblParts = this.worksheet.getTableParts();
            int matchedPos = -1;
            if (rId != null) {
                int i = 0;
                while (true) {
                    if (i >= tblParts.sizeOfTablePartArray()) {
                        break;
                    }
                    if (!rId.equals(tblParts.getTablePartArray(i).getId())) {
                        i++;
                    } else {
                        matchedPos = i;
                        break;
                    }
                }
            }
            if (matchedPos != -1) {
                tblParts.removeTablePart(matchedPos);
            }
        }
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public XSSFSheetConditionalFormatting getSheetConditionalFormatting() {
        return new XSSFSheetConditionalFormatting(this);
    }

    public XSSFColor getTabColor() {
        CTSheetPr pr = this.worksheet.getSheetPr();
        if (pr == null) {
            pr = this.worksheet.addNewSheetPr();
        }
        if (!pr.isSetTabColor()) {
            return null;
        }
        return XSSFColor.from(pr.getTabColor(), getWorkbook().getStylesSource().getIndexedColors());
    }

    public void setTabColor(XSSFColor color) {
        CTSheetPr pr = this.worksheet.getSheetPr();
        if (pr == null) {
            pr = this.worksheet.addNewSheetPr();
        }
        pr.setTabColor(color.getCTColor());
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public CellRangeAddress getRepeatingRows() {
        return getRepeatingRowsOrColumns(true);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public CellRangeAddress getRepeatingColumns() {
        return getRepeatingRowsOrColumns(false);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setRepeatingRows(CellRangeAddress rowRangeRef) {
        CellRangeAddress columnRangeRef = getRepeatingColumns();
        setRepeatingRowsAndColumns(rowRangeRef, columnRangeRef);
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public void setRepeatingColumns(CellRangeAddress columnRangeRef) {
        CellRangeAddress rowRangeRef = getRepeatingRows();
        setRepeatingRowsAndColumns(rowRangeRef, columnRangeRef);
    }

    private void setRepeatingRowsAndColumns(CellRangeAddress rowDef, CellRangeAddress colDef) {
        int col1 = -1;
        int col2 = -1;
        int row1 = -1;
        int row2 = -1;
        if (rowDef != null) {
            row1 = rowDef.getFirstRow();
            row2 = rowDef.getLastRow();
            if ((row1 == -1 && row2 != -1) || row1 < -1 || row2 < -1 || row1 > row2) {
                throw new IllegalArgumentException("Invalid row range specification");
            }
        }
        if (colDef != null) {
            col1 = colDef.getFirstColumn();
            col2 = colDef.getLastColumn();
            if ((col1 == -1 && col2 != -1) || col1 < -1 || col2 < -1 || col1 > col2) {
                throw new IllegalArgumentException("Invalid column range specification");
            }
        }
        int sheetIndex = getWorkbook().getSheetIndex(this);
        boolean removeAll = rowDef == null && colDef == null;
        XSSFName name = getWorkbook().getBuiltInName(XSSFName.BUILTIN_PRINT_TITLE, sheetIndex);
        if (removeAll) {
            if (name != null) {
                getWorkbook().removeName(name);
                return;
            }
            return;
        }
        if (name == null) {
            name = getWorkbook().createBuiltInName(XSSFName.BUILTIN_PRINT_TITLE, sheetIndex);
        }
        String reference = getReferenceBuiltInRecord(name.getSheetName(), col1, col2, row1, row2);
        name.setRefersToFormula(reference);
        if (!this.worksheet.isSetPageSetup() || !this.worksheet.isSetPageMargins()) {
            getPrintSetup().setValidSettings(false);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x003c, code lost:
    
        if (r19 == (-1)) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0071, code lost:
    
        if (r21 == (-1)) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String getReferenceBuiltInRecord(java.lang.String r17, int r18, int r19, int r20, int r21) {
        /*
            org.apache.poi.ss.util.CellReference r0 = new org.apache.poi.ss.util.CellReference
            r4 = 1
            r5 = 1
            r2 = 0
            r1 = r17
            r3 = r18
            r0.<init>(r1, r2, r3, r4, r5)
            org.apache.poi.ss.util.CellReference r6 = new org.apache.poi.ss.util.CellReference
            r10 = 1
            r11 = 1
            r8 = 0
            r7 = r17
            r9 = r19
            r6.<init>(r7, r8, r9, r10, r11)
            r1 = r6
            org.apache.poi.ss.util.CellReference r6 = new org.apache.poi.ss.util.CellReference
            r9 = 0
            r8 = r20
            r6.<init>(r7, r8, r9, r10, r11)
            r2 = r6
            org.apache.poi.ss.util.CellReference r6 = new org.apache.poi.ss.util.CellReference
            r8 = r21
            r6.<init>(r7, r8, r9, r10, r11)
            java.lang.String r3 = org.apache.poi.ss.formula.SheetNameFormatter.format(r17)
            java.lang.String r4 = ""
            java.lang.String r5 = ""
            java.lang.String r7 = ":$"
            java.lang.String r8 = "!$"
            r9 = -1
            r10 = r18
            if (r10 != r9) goto L3f
            r11 = r19
            if (r11 == r9) goto L6b
            goto L41
        L3f:
            r11 = r19
        L41:
            java.lang.String[] r12 = r0.getCellRefParts()
            r13 = 2
            r12 = r12[r13]
            java.lang.String[] r14 = r1.getCellRefParts()
            r13 = r14[r13]
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.StringBuilder r14 = r14.append(r3)
            java.lang.StringBuilder r14 = r14.append(r8)
            java.lang.StringBuilder r14 = r14.append(r12)
            java.lang.StringBuilder r14 = r14.append(r7)
            java.lang.StringBuilder r14 = r14.append(r13)
            java.lang.String r4 = r14.toString()
        L6b:
            r12 = r20
            if (r12 != r9) goto L74
            r13 = r21
            if (r13 == r9) goto Lae
            goto L76
        L74:
            r13 = r21
        L76:
            java.lang.String[] r9 = r2.getCellRefParts()
            r14 = 1
            r9 = r9[r14]
            java.lang.String[] r15 = r6.getCellRefParts()
            r14 = r15[r14]
            java.lang.String r15 = "0"
            boolean r16 = r9.equals(r15)
            if (r16 != 0) goto Lae
            boolean r15 = r14.equals(r15)
            if (r15 != 0) goto Lae
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.StringBuilder r15 = r15.append(r3)
            java.lang.StringBuilder r8 = r15.append(r8)
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r7 = r8.append(r7)
            java.lang.StringBuilder r7 = r7.append(r14)
            java.lang.String r5 = r7.toString()
        Lae:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r4)
            int r8 = r7.length()
            if (r8 <= 0) goto Lc7
            boolean r8 = r5.isEmpty()
            if (r8 != 0) goto Lc7
            r8 = 44
            r7.append(r8)
        Lc7:
            r7.append(r5)
            java.lang.String r8 = r7.toString()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFSheet.getReferenceBuiltInRecord(java.lang.String, int, int, int, int):java.lang.String");
    }

    private CellRangeAddress getRepeatingRowsOrColumns(boolean rows) {
        String refStr;
        int sheetIndex = getWorkbook().getSheetIndex(this);
        XSSFName name = getWorkbook().getBuiltInName(XSSFName.BUILTIN_PRINT_TITLE, sheetIndex);
        if (name == null || (refStr = name.getRefersToFormula()) == null) {
            return null;
        }
        String[] parts = refStr.split(CollectionUtils.COMMA);
        int maxRowIndex = SpreadsheetVersion.EXCEL2007.getLastRowIndex();
        int maxColIndex = SpreadsheetVersion.EXCEL2007.getLastColumnIndex();
        for (String part : parts) {
            CellRangeAddress range = CellRangeAddress.valueOf(part);
            if ((range.getFirstColumn() == 0 && range.getLastColumn() == maxColIndex) || (range.getFirstColumn() == -1 && range.getLastColumn() == -1)) {
                if (rows) {
                    return range;
                }
            } else if (((range.getFirstRow() == 0 && range.getLastRow() == maxRowIndex) || (range.getFirstRow() == -1 && range.getLastRow() == -1)) && !rows) {
                return range;
            }
        }
        return null;
    }

    private XSSFPivotTable createPivotTable() {
        XSSFWorkbook wb = getWorkbook();
        int tableId = getWorkbook().getPivotTables().size() + 1;
        XSSFPivotTable pivotTable = (XSSFPivotTable) createRelationship(XSSFRelation.PIVOT_TABLE, getWorkbook().getXssfFactory(), tableId);
        pivotTable.setParentSheet(this);
        wb.addPivotTable(pivotTable);
        XSSFWorkbook workbook = getWorkbook();
        XSSFPivotCacheDefinition pivotCacheDefinition = (XSSFPivotCacheDefinition) workbook.createRelationship(XSSFRelation.PIVOT_CACHE_DEFINITION, getWorkbook().getXssfFactory(), tableId);
        String rId = workbook.getRelationId(pivotCacheDefinition);
        PackagePart pivotPackagePart = pivotTable.getPackagePart();
        pivotPackagePart.addRelationship(pivotCacheDefinition.getPackagePart().getPartName(), TargetMode.INTERNAL, XSSFRelation.PIVOT_CACHE_DEFINITION.getRelation());
        pivotTable.setPivotCacheDefinition(pivotCacheDefinition);
        pivotTable.setPivotCache(new XSSFPivotCache(workbook.addPivotCache(rId)));
        XSSFPivotCacheRecords pivotCacheRecords = (XSSFPivotCacheRecords) pivotCacheDefinition.createRelationship(XSSFRelation.PIVOT_CACHE_RECORDS, getWorkbook().getXssfFactory(), tableId);
        pivotTable.getPivotCacheDefinition().getCTPivotCacheDefinition().setId(pivotCacheDefinition.getRelationId(pivotCacheRecords));
        return pivotTable;
    }

    public XSSFPivotTable createPivotTable(final AreaReference source, CellReference position, Sheet sourceSheet) {
        String sourceSheetName = source.getFirstCell().getSheetName();
        if (sourceSheetName != null && !sourceSheetName.equalsIgnoreCase(sourceSheet.getSheetName())) {
            throw new IllegalArgumentException("The area is referenced in another sheet than the defined source sheet " + sourceSheet.getSheetName() + ".");
        }
        return createPivotTable(position, sourceSheet, new XSSFPivotTable.PivotTableReferenceConfigurator() { // from class: org.apache.poi.xssf.usermodel.XSSFSheet$$ExternalSyntheticLambda4
            @Override // org.apache.poi.xssf.usermodel.XSSFPivotTable.PivotTableReferenceConfigurator
            public final void configureReference(CTWorksheetSource cTWorksheetSource) {
                XSSFSheet.lambda$createPivotTable$2(AreaReference.this, cTWorksheetSource);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$createPivotTable$2(AreaReference source, CTWorksheetSource wsSource) {
        String[] firstCell = source.getFirstCell().getCellRefParts();
        String firstRow = firstCell[1];
        String firstCol = firstCell[2];
        String[] lastCell = source.getLastCell().getCellRefParts();
        String lastRow = lastCell[1];
        String lastCol = lastCell[2];
        String ref = firstCol + firstRow + NameUtil.COLON + lastCol + lastRow;
        wsSource.setRef(ref);
    }

    private XSSFPivotTable createPivotTable(CellReference position, Sheet sourceSheet, XSSFPivotTable.PivotTableReferenceConfigurator refConfig) {
        XSSFPivotTable pivotTable = createPivotTable();
        pivotTable.setDefaultPivotTableDefinition();
        pivotTable.createSourceReferences(position, sourceSheet, refConfig);
        pivotTable.getPivotCacheDefinition().createCacheFields(sourceSheet);
        pivotTable.createDefaultDataColumns();
        return pivotTable;
    }

    public XSSFPivotTable createPivotTable(AreaReference source, CellReference position) {
        String sourceSheetName = source.getFirstCell().getSheetName();
        if (sourceSheetName != null && !sourceSheetName.equalsIgnoreCase(getSheetName())) {
            XSSFSheet sourceSheet = getWorkbook().getSheet(sourceSheetName);
            return createPivotTable(source, position, sourceSheet);
        }
        return createPivotTable(source, position, this);
    }

    public XSSFPivotTable createPivotTable(final Name source, CellReference position, Sheet sourceSheet) {
        if (source.getSheetName() != null && !source.getSheetName().equals(sourceSheet.getSheetName())) {
            throw new IllegalArgumentException("The named range references another sheet than the defined source sheet " + sourceSheet.getSheetName() + ".");
        }
        return createPivotTable(position, sourceSheet, new XSSFPivotTable.PivotTableReferenceConfigurator() { // from class: org.apache.poi.xssf.usermodel.XSSFSheet$$ExternalSyntheticLambda0
            @Override // org.apache.poi.xssf.usermodel.XSSFPivotTable.PivotTableReferenceConfigurator
            public final void configureReference(CTWorksheetSource cTWorksheetSource) {
                cTWorksheetSource.setName(Name.this.getNameName());
            }
        });
    }

    public XSSFPivotTable createPivotTable(Name source, CellReference position) {
        return createPivotTable(source, position, getWorkbook().getSheet(source.getSheetName()));
    }

    public XSSFPivotTable createPivotTable(final Table source, CellReference position) {
        return createPivotTable(position, getWorkbook().getSheet(source.getSheetName()), new XSSFPivotTable.PivotTableReferenceConfigurator() { // from class: org.apache.poi.xssf.usermodel.XSSFSheet$$ExternalSyntheticLambda2
            @Override // org.apache.poi.xssf.usermodel.XSSFPivotTable.PivotTableReferenceConfigurator
            public final void configureReference(CTWorksheetSource cTWorksheetSource) {
                cTWorksheetSource.setName(Table.this.getName());
            }
        });
    }

    public List<XSSFPivotTable> getPivotTables() {
        List<XSSFPivotTable> tables = new ArrayList<>();
        for (XSSFPivotTable table : getWorkbook().getPivotTables()) {
            if (table.getParent() == this) {
                tables.add(table);
            }
        }
        return tables;
    }

    @Override // org.apache.poi.ss.usermodel.Sheet
    public int getColumnOutlineLevel(int columnIndex) {
        CTCol col = this.columnHelper.getColumn(columnIndex, false);
        if (col == null) {
            return 0;
        }
        return col.getOutlineLevel();
    }

    public void addIgnoredErrors(CellReference cell, IgnoredErrorType... ignoredErrorTypes) {
        addIgnoredErrors(cell.formatAsString(false), ignoredErrorTypes);
    }

    public void addIgnoredErrors(CellRangeAddress region, IgnoredErrorType... ignoredErrorTypes) {
        region.validate(SpreadsheetVersion.EXCEL2007);
        addIgnoredErrors(region.formatAsString(), ignoredErrorTypes);
    }

    public Map<IgnoredErrorType, Set<CellRangeAddress>> getIgnoredErrors() {
        Map<IgnoredErrorType, Set<CellRangeAddress>> result = new LinkedHashMap<>();
        if (this.worksheet.isSetIgnoredErrors()) {
            for (CTIgnoredError err : this.worksheet.getIgnoredErrors().getIgnoredErrorList()) {
                for (IgnoredErrorType errType : XSSFIgnoredErrorHelper.getErrorTypes(err)) {
                    if (!result.containsKey(errType)) {
                        result.put(errType, new LinkedHashSet<>());
                    }
                    for (Object ref : err.getSqref()) {
                        result.get(errType).add(CellRangeAddress.valueOf(ref.toString()));
                    }
                }
            }
        }
        return result;
    }

    private void addIgnoredErrors(String ref, IgnoredErrorType... ignoredErrorTypes) {
        CTIgnoredErrors ctIgnoredErrors = this.worksheet.isSetIgnoredErrors() ? this.worksheet.getIgnoredErrors() : this.worksheet.addNewIgnoredErrors();
        CTIgnoredError ctIgnoredError = ctIgnoredErrors.addNewIgnoredError();
        XSSFIgnoredErrorHelper.addIgnoredErrors(ctIgnoredError, ref, ignoredErrorTypes);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onSheetDelete() {
        for (POIXMLDocumentPart.RelationPart part : getRelationParts()) {
            if (part.getDocumentPart() instanceof XSSFTable) {
                removeTable((XSSFTable) part.getDocumentPart());
            } else {
                removeRelation(part.getDocumentPart(), true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDeleteFormula(XSSFCell cell, BaseXSSFEvaluationWorkbook evalWb) {
        int rowIndex = cell.getRowIndex();
        int columnIndex = cell.getColumnIndex();
        CTCellFormula f = cell.getCTCell().getF();
        if (f != null && f.getT() == STCellFormulaType.SHARED && f.isSetRef() && f.getStringValue() != null) {
            CellRangeAddress ref = CellRangeAddress.valueOf(f.getRef());
            if (ref.getNumberOfCells() > 1) {
                int i = rowIndex;
                while (i <= ref.getLastRow()) {
                    XSSFRow row = getRow(i);
                    if (row != null) {
                        int j = columnIndex;
                        while (j <= ref.getLastColumn()) {
                            XSSFCell nextCell = row.getCell(j);
                            if (nextCell != null && nextCell != cell && nextCell.getCellType() == CellType.FORMULA) {
                                CTCellFormula nextF = nextCell.getCTCell().getF();
                                if (nextF.getT() == STCellFormulaType.SHARED && nextF.getSi() == f.getSi()) {
                                    nextF.setStringValue(nextCell.getCellFormula(evalWb));
                                    CellRangeAddress nextRef = new CellRangeAddress(nextCell.getRowIndex(), ref.getLastRow(), nextCell.getColumnIndex(), ref.getLastColumn());
                                    nextF.setRef(nextRef.formatAsString());
                                    this.sharedFormulas.put(Integer.valueOf(Math.toIntExact(nextF.getSi())), nextF);
                                    return;
                                }
                            }
                            j++;
                            rowIndex = rowIndex;
                        }
                    }
                    i++;
                    rowIndex = rowIndex;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CTOleObject readOleObject(long shapeId) {
        CTOleObject coo;
        if (!getCTWorksheet().isSetOleObjects()) {
            return null;
        }
        XmlCursor cur = getCTWorksheet().getOleObjects().newCursor();
        try {
            cur.selectPath("declare namespace p='http://schemas.openxmlformats.org/spreadsheetml/2006/main' .//p:oleObject");
            coo = null;
        } finally {
        }
        while (cur.toNextSelection()) {
            String sId = cur.getAttributeText(new QName(null, "shapeId"));
            if (sId != null && Long.parseLong(sId) == shapeId) {
                XmlObject xObj = cur.getObject();
                if (xObj instanceof CTOleObject) {
                    coo = (CTOleObject) xObj;
                } else {
                    XMLStreamReader reader = cur.newXMLStreamReader();
                    try {
                        try {
                            CTOleObjects coos = CTOleObjects.Factory.parse(reader);
                            if (coos.sizeOfOleObjectArray() == 0) {
                                try {
                                    reader.close();
                                } catch (XMLStreamException e) {
                                    LOG.atInfo().withThrowable(e).log("can't close reader");
                                }
                            } else {
                                CTOleObject coo2 = coos.getOleObjectArray(0);
                                try {
                                    reader.close();
                                } catch (XMLStreamException e2) {
                                    LOG.atInfo().withThrowable(e2).log("can't close reader");
                                }
                                coo = coo2;
                            }
                        } catch (XmlException e3) {
                            LOG.atInfo().withThrowable(e3).log("can't parse CTOleObjects");
                            try {
                                reader.close();
                            } catch (XMLStreamException e4) {
                                LOG.atInfo().withThrowable(e4).log("can't close reader");
                            }
                        }
                    } catch (Throwable th) {
                        try {
                            reader.close();
                        } catch (XMLStreamException e5) {
                            LOG.atInfo().withThrowable(e5).log("can't close reader");
                        }
                        throw th;
                    }
                }
                if (cur.toChild(XSSFRelation.NS_SPREADSHEETML, "objectPr")) {
                    break;
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
        return coo;
    }

    public XSSFHeaderFooterProperties getHeaderFooterProperties() {
        return new XSSFHeaderFooterProperties(getSheetTypeHeaderFooter());
    }

    public void setDimensionOverride(CellRangeAddress dimension) {
        this.dimensionOverride = dimension;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void cloneTables(XSSFSheet sheet) {
        Iterator<XSSFTable> it;
        XSSFTable clonedTable;
        String subtotalFormulaStart;
        Iterator<XSSFTable> it2 = sheet.getTables().iterator();
        while (it2.hasNext()) {
            XSSFTable table = it2.next();
            XSSFTable clonedTable2 = null;
            if (table.supportsAreaReference(table.getArea())) {
                clonedTable2 = sheet.createTable(table.getArea());
            }
            if (clonedTable2 != null) {
                clonedTable2.updateHeaders();
                clonedTable2.setStyleName(table.getStyleName());
                XSSFTableStyleInfo style = (XSSFTableStyleInfo) table.getStyle();
                XSSFTableStyleInfo clonedStyle = (XSSFTableStyleInfo) clonedTable2.getStyle();
                if (style != null && clonedStyle != null) {
                    clonedStyle.setShowColumnStripes(style.isShowColumnStripes());
                    clonedStyle.setShowRowStripes(style.isShowRowStripes());
                    clonedStyle.setFirstColumn(style.isShowFirstColumn());
                    clonedStyle.setLastColumn(style.isShowLastColumn());
                }
                clonedTable2.getCTTable().setAutoFilter(table.getCTTable().getAutoFilter());
                int totalsRowCount = table.getTotalsRowCount();
                boolean z = false;
                int i = 1;
                if (totalsRowCount == 1) {
                    XSSFRow totalsRow = sheet.getRow(clonedTable2.getEndCellReference().getRow());
                    if (clonedTable2.getCTTable().getTableColumns() != null && !clonedTable2.getCTTable().getTableColumns().getTableColumnList().isEmpty()) {
                        clonedTable2.getCTTable().setTotalsRowCount(totalsRowCount);
                        int i2 = 0;
                        while (i2 < clonedTable2.getCTTable().getTableColumns().getTableColumnList().size()) {
                            CTTableColumn tableCol = table.getCTTable().getTableColumns().getTableColumnList().get(i2);
                            CTTableColumn clonedTableCol = clonedTable2.getCTTable().getTableColumns().getTableColumnList().get(i2);
                            clonedTableCol.setTotalsRowFunction(tableCol.getTotalsRowFunction());
                            int intTotalsRowFunction = clonedTableCol.getTotalsRowFunction().intValue();
                            sheet.getWorkbook().setCellFormulaValidation(z);
                            if (intTotalsRowFunction == 10) {
                                CTTableFormula totalsRowFormula = tableCol.getTotalsRowFormula();
                                clonedTableCol.setTotalsRowFormula(totalsRowFormula);
                                totalsRow.getCell(clonedTable2.getStartCellReference().getCol() + i2).setCellFormula(totalsRowFormula.getStringValue());
                            } else if (intTotalsRowFunction != i && (subtotalFormulaStart = getSubtotalFormulaStartFromTotalsRowFunction(intTotalsRowFunction)) != null) {
                                totalsRow.getCell(clonedTable2.getStartCellReference().getCol() + i2).setCellFormula(subtotalFormulaStart + CollectionUtils.COMMA + clonedTable2.getName() + CollectionUtils.DEFAULT_TOSTRING_PREFIX + clonedTableCol.getName() + "])");
                            }
                            i2++;
                            z = false;
                            i = 1;
                        }
                    }
                }
                if (clonedTable2.getCTTable().getTableColumns() != null && !clonedTable2.getCTTable().getTableColumns().getTableColumnList().isEmpty()) {
                    clonedTable2.getCTTable().setTotalsRowCount(totalsRowCount);
                    int i3 = 0;
                    while (i3 < clonedTable2.getCTTable().getTableColumns().getTableColumnList().size()) {
                        CTTableColumn tableCol2 = table.getCTTable().getTableColumns().getTableColumnList().get(i3);
                        CTTableColumn clonedTableCol2 = clonedTable2.getCTTable().getTableColumns().getTableColumnList().get(i3);
                        if (tableCol2.getCalculatedColumnFormula() == null) {
                            it = it2;
                            clonedTable = clonedTable2;
                        } else {
                            clonedTableCol2.setCalculatedColumnFormula(tableCol2.getCalculatedColumnFormula());
                            CTTableFormula calculatedColumnFormula = clonedTableCol2.getCalculatedColumnFormula();
                            String formula = tableCol2.getCalculatedColumnFormula().getStringValue();
                            String clonedFormula = formula.replace(table.getName(), clonedTable2.getName());
                            calculatedColumnFormula.setStringValue(clonedFormula);
                            int rFirst = clonedTable2.getStartCellReference().getRow() + clonedTable2.getHeaderRowCount();
                            int rLast = clonedTable2.getEndCellReference().getRow() - clonedTable2.getTotalsRowCount();
                            int c = clonedTable2.getStartCellReference().getCol() + i3;
                            it = it2;
                            clonedTable = clonedTable2;
                            sheet.getWorkbook().setCellFormulaValidation(false);
                            int r = rFirst;
                            while (r <= rLast) {
                                XSSFRow row = sheet.getRow(r);
                                if (row == null) {
                                    row = sheet.createRow(r);
                                }
                                XSSFRow row2 = row;
                                int r2 = r;
                                XSSFCell cell = row2.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cell.setCellFormula(clonedFormula);
                                r = r2 + 1;
                            }
                        }
                        i3++;
                        it2 = it;
                        clonedTable2 = clonedTable;
                    }
                }
            }
            sheet.removeTable(table);
            it2 = it2;
        }
    }

    private static String getSubtotalFormulaStartFromTotalsRowFunction(int intTotalsRowFunction) {
        switch (intTotalsRowFunction) {
            case 1:
                return null;
            case 2:
                return "SUBTOTAL(109";
            case 3:
                return "SUBTOTAL(105";
            case 4:
                return "SUBTOTAL(104";
            case 5:
                return "SUBTOTAL(101";
            case 6:
                return "SUBTOTAL(103";
            case 7:
                return "SUBTOTAL(102";
            case 8:
                return "SUBTOTAL(107";
            case 9:
                return "SUBTOTAL(110";
            case 10:
                return null;
            default:
                return null;
        }
    }
}
