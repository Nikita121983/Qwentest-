package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.ss.usermodel.TableStyleInfo;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.helpers.XSSFXmlColumnPr;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.TableDocument;

/* loaded from: classes10.dex */
public class XSSFTable extends POIXMLDocumentPart implements Table {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSSFTable.class);
    private transient ConcurrentSkipListMap<String, Integer> columnMap;
    private transient String commonXPath;
    private CTTable ctTable;
    private transient CellReference endCellReference;
    private transient String name;
    private transient CellReference startCellReference;
    private transient String styleName;
    private transient List<XSSFTableColumn> tableColumns;
    private transient List<XSSFXmlColumnPr> xmlColumnPrs;

    public XSSFTable() {
        this.ctTable = CTTable.Factory.newInstance();
    }

    public XSSFTable(PackagePart part) throws IOException {
        super(part);
        InputStream stream = part.getInputStream();
        try {
            readFrom(stream);
            if (stream != null) {
                stream.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public void readFrom(InputStream is) throws IOException {
        try {
            TableDocument doc = TableDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.ctTable = doc.getTable();
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public XSSFSheet getXSSFSheet() {
        return (XSSFSheet) getParent();
    }

    public void writeTo(OutputStream out) throws IOException {
        updateHeaders();
        TableDocument doc = TableDocument.Factory.newInstance();
        doc.setTable(this.ctTable);
        doc.save(out, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        PackagePart part = getPackagePart();
        OutputStream out = part.getOutputStream();
        try {
            writeTo(out);
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

    @Internal(since = "POI 3.15 beta 3")
    public CTTable getCTTable() {
        return this.ctTable;
    }

    public boolean mapsTo(long id) {
        List<XSSFXmlColumnPr> pointers = getXmlColumnPrs();
        for (XSSFXmlColumnPr pointer : pointers) {
            if (pointer.getMapId() == id) {
                return true;
            }
        }
        return false;
    }

    public String getCommonXpath() {
        if (this.commonXPath == null) {
            String[] commonTokens = new String[0];
            for (XSSFTableColumn column : getColumns()) {
                if (column.getXmlColumnPr() != null) {
                    String xpath = column.getXmlColumnPr().getXPath();
                    String[] tokens = xpath.split(PackagingURIHelper.FORWARD_SLASH_STRING);
                    if (commonTokens.length == 0) {
                        commonTokens = tokens;
                    } else {
                        int maxLength = Math.min(commonTokens.length, tokens.length);
                        int i = 0;
                        while (true) {
                            if (i >= maxLength) {
                                break;
                            }
                            if (!commonTokens[i].equals(tokens[i])) {
                                List<String> subCommonTokens = Arrays.asList(commonTokens).subList(0, i);
                                String[] container = new String[0];
                                commonTokens = (String[]) subCommonTokens.toArray(container);
                                break;
                            }
                            i++;
                        }
                    }
                }
            }
            commonTokens[0] = "";
            this.commonXPath = StringUtil.join(commonTokens, PackagingURIHelper.FORWARD_SLASH_STRING);
        }
        return this.commonXPath;
    }

    public List<XSSFTableColumn> getColumns() {
        if (this.tableColumns == null) {
            List<XSSFTableColumn> columns = new ArrayList<>();
            CTTableColumns ctTableColumns = this.ctTable.getTableColumns();
            if (ctTableColumns != null) {
                for (CTTableColumn column : ctTableColumns.getTableColumnList()) {
                    XSSFTableColumn tableColumn = new XSSFTableColumn(this, column);
                    columns.add(tableColumn);
                }
            }
            this.tableColumns = Collections.unmodifiableList(columns);
        }
        return this.tableColumns;
    }

    private List<XSSFXmlColumnPr> getXmlColumnPrs() {
        if (this.xmlColumnPrs == null) {
            this.xmlColumnPrs = new ArrayList();
            for (XSSFTableColumn column : getColumns()) {
                XSSFXmlColumnPr xmlColumnPr = column.getXmlColumnPr();
                if (xmlColumnPr != null) {
                    this.xmlColumnPrs.add(xmlColumnPr);
                }
            }
        }
        return this.xmlColumnPrs;
    }

    public XSSFTableColumn createColumn(String columnName) {
        return createColumn(columnName, getColumnCount());
    }

    public XSSFTableColumn createColumn(String columnName, int columnIndex) {
        int columnCount = getColumnCount();
        if (columnIndex < 0 || columnIndex > columnCount) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        CTTableColumns columns = this.ctTable.getTableColumns();
        if (columns == null) {
            columns = this.ctTable.addNewTableColumns();
        }
        long nextColumnId = 0;
        for (XSSFTableColumn tableColumn : getColumns()) {
            if (columnName != null && columnName.equalsIgnoreCase(tableColumn.getName())) {
                throw new IllegalArgumentException("Column '" + columnName + "' already exists. Column names must be unique per table.");
            }
            nextColumnId = Math.max(nextColumnId, tableColumn.getId());
        }
        long nextColumnId2 = nextColumnId + 1;
        CTTableColumn column = columns.insertNewTableColumn(columnIndex);
        columns.setCount(columns.sizeOfTableColumnArray());
        column.setId(nextColumnId2);
        if (columnName != null) {
            column.setName(columnName);
        } else {
            column.setName("Column " + nextColumnId2);
        }
        if (this.ctTable.getRef() != null) {
            int newColumnCount = columnCount + 1;
            CellReference tableStart = getStartCellReference();
            CellReference tableEnd = getEndCellReference();
            SpreadsheetVersion version = getXSSFSheet().getWorkbook().getSpreadsheetVersion();
            CellReference newTableEnd = new CellReference(tableEnd.getRow(), (tableStart.getCol() + newColumnCount) - 1);
            AreaReference newTableArea = new AreaReference(tableStart, newTableEnd, version);
            setCellRef(newTableArea);
        }
        updateHeaders();
        return getColumns().get(columnIndex);
    }

    public void removeColumn(XSSFTableColumn column) {
        int columnIndex = getColumns().indexOf(column);
        if (columnIndex >= 0) {
            this.ctTable.getTableColumns().removeTableColumn(columnIndex);
            updateReferences();
            updateHeaders();
        }
    }

    public void removeColumn(int columnIndex) {
        if (columnIndex < 0 || columnIndex > getColumnCount() - 1) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        if (getColumnCount() == 1) {
            throw new IllegalArgumentException("Table must have at least one column");
        }
        CTTableColumns tableColumns = this.ctTable.getTableColumns();
        tableColumns.removeTableColumn(columnIndex);
        tableColumns.setCount(tableColumns.getTableColumnList().size());
        updateReferences();
        updateHeaders();
    }

    @Override // org.apache.poi.ss.usermodel.Table
    public String getName() {
        if (this.name == null && this.ctTable.getName() != null) {
            setName(this.ctTable.getName());
        }
        return this.name;
    }

    public void setName(String newName) {
        if (newName == null) {
            this.ctTable.unsetName();
            this.name = null;
        } else {
            this.ctTable.setName(newName);
            this.name = newName;
        }
    }

    @Override // org.apache.poi.ss.usermodel.Table
    public String getStyleName() {
        if (this.styleName == null && this.ctTable.isSetTableStyleInfo()) {
            setStyleName(this.ctTable.getTableStyleInfo().getName());
        }
        return this.styleName;
    }

    public void setStyleName(String newStyleName) {
        if (newStyleName == null) {
            if (this.ctTable.isSetTableStyleInfo()) {
                try {
                    this.ctTable.getTableStyleInfo().unsetName();
                } catch (Exception e) {
                    LOG.atDebug().log("Failed to unset style name", e);
                }
            }
            this.styleName = null;
            return;
        }
        if (!this.ctTable.isSetTableStyleInfo()) {
            this.ctTable.addNewTableStyleInfo();
        }
        this.ctTable.getTableStyleInfo().setName(newStyleName);
        this.styleName = newStyleName;
    }

    public String getDisplayName() {
        return this.ctTable.getDisplayName();
    }

    public void setDisplayName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Display name must not be null or empty");
        }
        this.ctTable.setDisplayName(name);
    }

    public AreaReference getCellReferences() {
        return new AreaReference(getStartCellReference(), getEndCellReference(), SpreadsheetVersion.EXCEL2007);
    }

    public void setCellReferences(AreaReference refs) {
        setCellRef(refs);
    }

    @Internal
    protected void setCellRef(AreaReference refs) {
        String filterRef;
        String ref = refs.formatAsString();
        if (ref.indexOf(33) != -1) {
            ref = ref.substring(ref.indexOf(33) + 1);
        }
        this.ctTable.setRef(ref);
        if (this.ctTable.isSetAutoFilter()) {
            int totalsRowCount = getTotalsRowCount();
            if (totalsRowCount == 0) {
                filterRef = ref;
            } else {
                CellReference start = new CellReference(refs.getFirstCell().getRow(), refs.getFirstCell().getCol());
                CellReference end = new CellReference(refs.getLastCell().getRow() - totalsRowCount, refs.getLastCell().getCol());
                filterRef = new AreaReference(start, end, SpreadsheetVersion.EXCEL2007).formatAsString();
            }
            this.ctTable.getAutoFilter().setRef(filterRef);
        }
        updateReferences();
        updateHeaders();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean supportsAreaReference(AreaReference tableArea) {
        int rowCount = (tableArea.getLastCell().getRow() - tableArea.getFirstCell().getRow()) + 1;
        int headerRowCount = Math.max(1, getHeaderRowCount());
        int minimumRowCount = headerRowCount + 1 + getTotalsRowCount();
        return rowCount >= minimumRowCount;
    }

    public void setArea(AreaReference tableArea) {
        if (tableArea == null) {
            throw new IllegalArgumentException("AreaReference must not be null");
        }
        String areaSheetName = tableArea.getFirstCell().getSheetName();
        if (areaSheetName != null && !areaSheetName.equals(getXSSFSheet().getSheetName())) {
            throw new IllegalArgumentException("The AreaReference must not reference a different sheet");
        }
        if (!supportsAreaReference(tableArea)) {
            int minimumRowCount = getHeaderRowCount() + 1 + getTotalsRowCount();
            throw new IllegalArgumentException("AreaReference needs at least " + minimumRowCount + " rows, to cover at least one data row and all header rows and totals rows");
        }
        String ref = tableArea.formatAsString();
        if (ref.indexOf(33) != -1) {
            ref = ref.substring(ref.indexOf(33) + 1);
        }
        this.ctTable.setRef(ref);
        if (this.ctTable.isSetAutoFilter()) {
            this.ctTable.getAutoFilter().setRef(ref);
        }
        updateReferences();
        int columnCount = getColumnCount();
        int newColumnCount = (tableArea.getLastCell().getCol() - tableArea.getFirstCell().getCol()) + 1;
        if (newColumnCount > columnCount) {
            for (int i = columnCount; i < newColumnCount; i++) {
                createColumn(null, i);
            }
        } else if (newColumnCount < columnCount) {
            for (int i2 = columnCount; i2 > newColumnCount; i2--) {
                removeColumn(i2 - 1);
            }
        }
        updateHeaders();
    }

    public AreaReference getArea() {
        String ref = this.ctTable.getRef();
        if (ref != null) {
            SpreadsheetVersion version = getXSSFSheet().getWorkbook().getSpreadsheetVersion();
            return new AreaReference(this.ctTable.getRef(), version);
        }
        return null;
    }

    public CellReference getStartCellReference() {
        if (this.startCellReference == null) {
            setCellReferences();
        }
        return this.startCellReference;
    }

    public CellReference getEndCellReference() {
        if (this.endCellReference == null) {
            setCellReferences();
        }
        return this.endCellReference;
    }

    private void setCellReferences() {
        String ref = this.ctTable.getRef();
        if (ref != null) {
            String[] boundaries = ref.split(":", 2);
            String from = boundaries[0];
            String to = boundaries.length == 2 ? boundaries[1] : boundaries[0];
            this.startCellReference = new CellReference(from);
            this.endCellReference = new CellReference(to);
        }
    }

    public void updateReferences() {
        this.startCellReference = null;
        this.endCellReference = null;
    }

    public int getRowCount() {
        CellReference from = getStartCellReference();
        CellReference to = getEndCellReference();
        if (from == null || to == null) {
            return 0;
        }
        int rowCount = (to.getRow() - from.getRow()) + 1;
        return rowCount;
    }

    public int getDataRowCount() {
        CellReference from = getStartCellReference();
        CellReference to = getEndCellReference();
        if (from == null || to == null) {
            return 0;
        }
        int rowCount = (((to.getRow() - from.getRow()) + 1) - getHeaderRowCount()) - getTotalsRowCount();
        return rowCount;
    }

    public void setDataRowCount(int newDataRowCount) {
        CellReference clearAreaStart;
        CellReference clearAreaEnd;
        XSSFCell cell;
        if (newDataRowCount < 1) {
            throw new IllegalArgumentException("Table must have at least one data row");
        }
        updateReferences();
        int dataRowCount = getDataRowCount();
        if (dataRowCount == newDataRowCount) {
            return;
        }
        CellReference tableStart = getStartCellReference();
        CellReference tableEnd = getEndCellReference();
        SpreadsheetVersion version = getXSSFSheet().getWorkbook().getSpreadsheetVersion();
        int newTotalRowCount = getHeaderRowCount() + newDataRowCount + getTotalsRowCount();
        CellReference newTableEnd = new CellReference((tableStart.getRow() + newTotalRowCount) - 1, tableEnd.getCol());
        AreaReference newTableArea = new AreaReference(tableStart, newTableEnd, version);
        if (newDataRowCount < dataRowCount) {
            clearAreaStart = new CellReference(newTableArea.getLastCell().getRow() + 1, newTableArea.getFirstCell().getCol());
            clearAreaEnd = tableEnd;
        } else {
            clearAreaStart = new CellReference(tableEnd.getRow() + 1, newTableArea.getFirstCell().getCol());
            clearAreaEnd = newTableEnd;
        }
        AreaReference areaToClear = new AreaReference(clearAreaStart, clearAreaEnd, version);
        for (CellReference cellRef : areaToClear.getAllReferencedCells()) {
            XSSFRow row = getXSSFSheet().getRow(cellRef.getRow());
            if (row != null && (cell = row.getCell((int) cellRef.getCol())) != null) {
                cell.setBlank();
                cell.setCellStyle(null);
            }
        }
        setCellRef(newTableArea);
    }

    public int getColumnCount() {
        CTTableColumns tableColumns = this.ctTable.getTableColumns();
        if (tableColumns == null) {
            return 0;
        }
        return (int) tableColumns.getCount();
    }

    public void updateHeaders() {
        POIXMLDocumentPart parent = getParent();
        if (!(parent instanceof XSSFSheet)) {
            throw new IllegalArgumentException("Had unexpected type of parent: " + (parent == null ? "<null>" : parent.getClass()));
        }
        XSSFSheet sheet = (XSSFSheet) parent;
        CellReference ref = getStartCellReference();
        if (ref == null) {
            return;
        }
        int headerRow = ref.getRow();
        int firstHeaderColumn = ref.getCol();
        XSSFRow row = sheet.getRow(headerRow);
        DataFormatter formatter = new DataFormatter();
        if (row != null) {
            int cellnum = firstHeaderColumn;
            CTTableColumns ctTableColumns = getCTTable().getTableColumns();
            if (ctTableColumns != null) {
                for (CTTableColumn col : ctTableColumns.getTableColumnList()) {
                    XSSFCell cell = row.getCell(cellnum);
                    if (cell != null) {
                        String colName = formatter.formatCellValue(cell);
                        col.setName(colName.replace(StringUtils.LF, "_x000a_").replace(StringUtils.CR, "_x000d_"));
                    }
                    cellnum++;
                }
            }
        }
        this.tableColumns = null;
        this.columnMap = null;
        this.xmlColumnPrs = null;
        this.commonXPath = null;
    }

    @Override // org.apache.poi.ss.usermodel.Table
    public int findColumnIndex(String columnHeader) {
        if (columnHeader == null) {
            return -1;
        }
        if (this.columnMap == null) {
            this.columnMap = new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER);
            int i = 0;
            for (XSSFTableColumn column : getColumns()) {
                String columnName = column.getName();
                this.columnMap.put(columnName, Integer.valueOf(i));
                i++;
            }
        }
        String unescapedString = columnHeader.replace("''", "'").replace("'#", "#");
        Integer idx = this.columnMap.get(unescapedString);
        if (idx == null) {
            return -1;
        }
        return idx.intValue();
    }

    @Override // org.apache.poi.ss.usermodel.Table
    public String getSheetName() {
        return getXSSFSheet().getSheetName();
    }

    @Override // org.apache.poi.ss.usermodel.Table
    public boolean isHasTotalsRow() {
        return this.ctTable.getTotalsRowShown();
    }

    @Override // org.apache.poi.ss.usermodel.Table
    public int getTotalsRowCount() {
        return (int) this.ctTable.getTotalsRowCount();
    }

    @Override // org.apache.poi.ss.usermodel.Table
    public int getHeaderRowCount() {
        return (int) this.ctTable.getHeaderRowCount();
    }

    @Override // org.apache.poi.ss.usermodel.Table
    public int getStartColIndex() {
        return getStartCellReference().getCol();
    }

    @Override // org.apache.poi.ss.usermodel.Table
    public int getStartRowIndex() {
        return getStartCellReference().getRow();
    }

    @Override // org.apache.poi.ss.usermodel.Table
    public int getEndColIndex() {
        return getEndCellReference().getCol();
    }

    @Override // org.apache.poi.ss.usermodel.Table
    public int getEndRowIndex() {
        return getEndCellReference().getRow();
    }

    @Override // org.apache.poi.ss.usermodel.Table
    public TableStyleInfo getStyle() {
        if (this.ctTable.isSetTableStyleInfo()) {
            return new XSSFTableStyleInfo(((XSSFSheet) getParent()).getWorkbook().getStylesSource(), this.ctTable.getTableStyleInfo());
        }
        return null;
    }

    @Override // org.apache.poi.ss.usermodel.Table
    public boolean contains(CellReference cell) {
        return cell != null && getSheetName().equals(cell.getSheetName()) && cell.getRow() >= getStartRowIndex() && cell.getRow() <= getEndRowIndex() && cell.getCol() >= getStartColIndex() && cell.getCol() <= getEndColIndex();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onTableDelete() {
        for (POIXMLDocumentPart.RelationPart part : getRelationParts()) {
            removeRelation(part.getDocumentPart(), true);
        }
    }
}
