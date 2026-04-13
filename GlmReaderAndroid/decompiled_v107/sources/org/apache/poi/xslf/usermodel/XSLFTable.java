package org.apache.poi.xslf.usermodel;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawTextShape;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TableShape;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTable;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCol;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableRow;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGraphicalObjectFrameNonVisual;

/* loaded from: classes10.dex */
public class XSLFTable extends XSLFGraphicFrame implements Iterable<XSLFTableRow>, TableShape<XSLFShape, XSLFTextParagraph> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XSLFTable.class);
    static final String TABLE_URI = "http://schemas.openxmlformats.org/drawingml/2006/table";
    private final List<XSLFTableRow> _rows;
    private final CTTable _table;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFTable(CTGraphicalObjectFrame shape, XSLFSheet sheet) {
        super(shape, sheet);
        CTGraphicalObjectData god = shape.getGraphic().getGraphicData();
        XmlCursor xc = god.newCursor();
        try {
            if (!xc.toChild(XSSFRelation.NS_DRAWINGML, "tbl")) {
                throw new IllegalStateException("a:tbl element was not found in\n " + god);
            }
            XmlObject xo = xc.getObject();
            if (xo instanceof XmlAnyTypeImpl) {
                throw new IllegalStateException("Schemas (*.xsb) for CTTable can't be loaded - usually this happens when OSGI loading is used and the thread context classloader has no reference to the xmlbeans classes");
            }
            this._table = (CTTable) xo;
            if (xc != null) {
                xc.close();
            }
            this._rows = new ArrayList(this._table.sizeOfTrArray());
            for (CTTableRow row : this._table.getTrList()) {
                this._rows.add(new XSLFTableRow(row, this));
            }
            updateRowColIndexes();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (xc != null) {
                    try {
                        xc.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.sl.usermodel.TableShape
    public TableCell<XSLFShape, XSLFTextParagraph> getCell(int row, int col) {
        XSLFTableRow r;
        if (row < 0 || this._rows.size() <= row || (r = this._rows.get(row)) == null) {
            return null;
        }
        List<XSLFTableCell> cells = r.getCells();
        if (col < 0 || cells.size() <= col) {
            return null;
        }
        return cells.get(col);
    }

    @Internal
    public CTTable getCTTable() {
        return this._table;
    }

    @Override // org.apache.poi.sl.usermodel.TableShape
    public int getNumberOfColumns() {
        if (this._table.getTblGrid() == null) {
            return 0;
        }
        return this._table.getTblGrid().sizeOfGridColArray();
    }

    @Override // org.apache.poi.sl.usermodel.TableShape
    public int getNumberOfRows() {
        return this._table.sizeOfTrArray();
    }

    @Override // org.apache.poi.sl.usermodel.TableShape
    public double getColumnWidth(int idx) {
        return Units.toPoints(POIXMLUnits.parseLength(this._table.getTblGrid().getGridColArray(idx).xgetW()));
    }

    @Override // org.apache.poi.sl.usermodel.TableShape
    public void setColumnWidth(int idx, double width) {
        this._table.getTblGrid().getGridColArray(idx).setW(Integer.valueOf(Units.toEMU(width)));
    }

    @Override // org.apache.poi.sl.usermodel.TableShape
    public double getRowHeight(int row) {
        return Units.toPoints(POIXMLUnits.parseLength(this._table.getTrArray(row).xgetH()));
    }

    @Override // org.apache.poi.sl.usermodel.TableShape
    public void setRowHeight(int row, double height) {
        this._table.getTrArray(row).setH(Integer.valueOf(Units.toEMU(height)));
    }

    @Override // java.lang.Iterable
    public Iterator<XSLFTableRow> iterator() {
        return this._rows.iterator();
    }

    public List<XSLFTableRow> getRows() {
        return Collections.unmodifiableList(this._rows);
    }

    public XSLFTableRow addRow() {
        CTTableRow tr = this._table.addNewTr();
        XSLFTableRow row = initializeRow(tr);
        this._rows.add(row);
        updateRowColIndexes();
        return row;
    }

    private XSLFTableRow initializeRow(CTTableRow tr) {
        XSLFTableRow row = new XSLFTableRow(tr, this);
        row.setHeight(20.0d);
        return row;
    }

    public XSLFTableRow insertRow(int rowIdx) {
        if (getNumberOfRows() < rowIdx) {
            throw new IndexOutOfBoundsException("Cannot insert row at " + rowIdx + "; table has only " + getNumberOfRows() + "rows.");
        }
        CTTableRow tr = this._table.insertNewTr(rowIdx);
        XSLFTableRow row = initializeRow(tr);
        for (int i = 0; i < getNumberOfColumns(); i++) {
            row.addCell();
        }
        this._rows.add(rowIdx, row);
        return row;
    }

    public void removeRow(int rowIdx) {
        if (getNumberOfRows() < rowIdx) {
            throw new IndexOutOfBoundsException("Cannot remove row at " + rowIdx + "; table has only " + getNumberOfRows() + "rows.");
        }
        this._table.removeTr(rowIdx);
        this._rows.remove(rowIdx);
        updateRowColIndexes();
    }

    public void addColumn() {
        long width = POIXMLUnits.parseLength(this._table.getTblGrid().getGridColArray(getNumberOfColumns() - 1).xgetW());
        CTTableCol col = this._table.getTblGrid().addNewGridCol();
        col.setW(Long.valueOf(width));
        for (XSLFTableRow row : this._rows) {
            XSLFTableCell cell = row.addCell();
            new XDDFTextBody(cell, cell.getTextBody(true)).initialize();
        }
    }

    public void insertColumn(int colIdx) {
        if (getNumberOfColumns() < colIdx) {
            throw new IndexOutOfBoundsException("Cannot insert column at " + colIdx + "; table has only " + getNumberOfColumns() + "columns.");
        }
        long width = POIXMLUnits.parseLength(this._table.getTblGrid().getGridColArray(colIdx).xgetW());
        CTTableCol col = this._table.getTblGrid().insertNewGridCol(colIdx);
        col.setW(Long.valueOf(width));
        for (XSLFTableRow row : this._rows) {
            XSLFTableCell cell = row.insertCell(colIdx);
            new XDDFTextBody(cell, cell.getTextBody(true)).initialize();
        }
    }

    public void removeColumn(int colIdx) {
        if (getNumberOfColumns() < colIdx) {
            throw new IndexOutOfBoundsException("Cannot remove column at " + colIdx + "; table has only " + getNumberOfColumns() + "columns.");
        }
        this._table.getTblGrid().removeGridCol(colIdx);
        for (XSLFTableRow row : this._rows) {
            row.removeCell(colIdx);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CTGraphicalObjectFrame prototype(int shapeId) {
        CTGraphicalObjectFrame frame = CTGraphicalObjectFrame.Factory.newInstance();
        CTGraphicalObjectFrameNonVisual nvGr = frame.addNewNvGraphicFramePr();
        CTNonVisualDrawingProps cnv = nvGr.addNewCNvPr();
        cnv.setName("Table " + shapeId);
        cnv.setId(shapeId);
        nvGr.addNewCNvGraphicFramePr().addNewGraphicFrameLocks().setNoGrp(true);
        nvGr.addNewNvPr();
        frame.addNewXfrm();
        CTGraphicalObjectData gr = frame.addNewGraphic().addNewGraphicData();
        XmlCursor grCur = gr.newCursor();
        try {
            grCur.toNextToken();
            grCur.beginElement(new QName(XSSFRelation.NS_DRAWINGML, "tbl"));
            CTTable tbl = CTTable.Factory.newInstance();
            tbl.addNewTblPr();
            tbl.addNewTblGrid();
            XmlCursor tblCur = tbl.newCursor();
            try {
                tblCur.moveXmlContents(grCur);
                if (tblCur != null) {
                    tblCur.close();
                }
                if (grCur != null) {
                    grCur.close();
                }
                gr.setUri(TABLE_URI);
                return frame;
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (grCur != null) {
                    try {
                        grCur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public void mergeCells(int firstRow, int lastRow, int firstCol, int lastCol) {
        if (firstRow > lastRow) {
            throw new IllegalArgumentException("Cannot merge, first row > last row : " + firstRow + " > " + lastRow);
        }
        if (firstCol > lastCol) {
            throw new IllegalArgumentException("Cannot merge, first column > last column : " + firstCol + " > " + lastCol);
        }
        int rowSpan = (lastRow - firstRow) + 1;
        boolean mergeRowRequired = rowSpan > 1;
        int colSpan = (lastCol - firstCol) + 1;
        boolean mergeColumnRequired = colSpan > 1;
        for (int i = firstRow; i <= lastRow; i++) {
            XSLFTableRow row = this._rows.get(i);
            for (int colPos = firstCol; colPos <= lastCol; colPos++) {
                XSLFTableCell cell = row.getCells().get(colPos);
                if (mergeRowRequired) {
                    if (i == firstRow) {
                        cell.setRowSpan(rowSpan);
                    } else {
                        cell.setVMerge();
                    }
                }
                if (mergeColumnRequired) {
                    if (colPos == firstCol) {
                        cell.setGridSpan(colSpan);
                    } else {
                        cell.setHMerge();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference failed for: r3v1, types: [org.apache.poi.xslf.usermodel.XMLSlideShow] */
    public XSLFTableStyle getTableStyle() {
        CTTable tab = getCTTable();
        if (!tab.isSetTblPr() || !tab.getTblPr().isSetTableStyleId()) {
            return null;
        }
        String styleId = tab.getTblPr().getTableStyleId();
        XSLFTableStyles styles = getSheet().getSlideShow().getTableStyles();
        for (XSLFTableStyle style : styles.getStyles()) {
            if (style.getStyleId().equals(styleId)) {
                return style;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateRowColIndexes() {
        int rowIdx = 0;
        Iterator<XSLFTableRow> it = iterator();
        while (it.hasNext()) {
            XSLFTableRow xr = it.next();
            int colIdx = 0;
            Iterator<XSLFTableCell> it2 = xr.iterator();
            while (it2.hasNext()) {
                XSLFTableCell tc = it2.next();
                tc.setRowColIndex(rowIdx, colIdx);
                colIdx++;
            }
            rowIdx++;
        }
    }

    /* JADX WARN: Type inference failed for: r14v5, types: [org.apache.poi.xslf.usermodel.XSLFTableCell] */
    /* JADX WARN: Type inference failed for: r2v10, types: [org.apache.poi.xslf.usermodel.XSLFTableCell] */
    /* JADX WARN: Type inference failed for: r9v1, types: [org.apache.poi.xslf.usermodel.XSLFTableCell] */
    public void updateCellAnchor() {
        int cols;
        int row;
        double[] colWidths;
        double[] rowHeights;
        int rows = getNumberOfRows();
        int cols2 = getNumberOfColumns();
        double[] colWidths2 = new double[cols2];
        double[] rowHeights2 = new double[rows];
        for (int row2 = 0; row2 < rows; row2++) {
            rowHeights2[row2] = getRowHeight(row2);
        }
        for (int col = 0; col < cols2; col++) {
            colWidths2[col] = getColumnWidth(col);
        }
        Rectangle2D tblAnc = getAnchor();
        DrawFactory df = DrawFactory.getInstance(null);
        double nextY = tblAnc.getY();
        double nextX = tblAnc.getX();
        int row3 = 0;
        while (row3 < rows) {
            double maxHeight = 0.0d;
            int col2 = 0;
            while (col2 < cols2) {
                TableCell<XSLFShape, XSLFTextParagraph> cell = getCell(row3, col2);
                if (cell != null) {
                    colWidths = colWidths2;
                    rowHeights = rowHeights2;
                    if (cell.getGridSpan() == 1 && cell.getRowSpan() == 1) {
                        cell.setAnchor(new Rectangle2D.Double(0.0d, 0.0d, colWidths[col2], 0.0d));
                        DrawTextShape dts = df.getDrawable((TextShape<?, ?>) cell);
                        maxHeight = Math.max(maxHeight, dts.getTextHeight());
                    }
                } else {
                    colWidths = colWidths2;
                    rowHeights = rowHeights2;
                }
                col2++;
                colWidths2 = colWidths;
                rowHeights2 = rowHeights;
            }
            double[] rowHeights3 = rowHeights2;
            rowHeights3[row3] = Math.max(rowHeights3[row3], maxHeight);
            row3++;
            colWidths2 = colWidths2;
            rowHeights2 = rowHeights3;
        }
        double[] colWidths3 = colWidths2;
        double[] rowHeights4 = rowHeights2;
        int row4 = 0;
        double nextY2 = nextY;
        double nextY3 = nextX;
        while (row4 < rows) {
            double nextX2 = tblAnc.getX();
            double nextX3 = nextX2;
            for (int col3 = 0; col3 < cols2; col3++) {
                Rectangle2D.Double r7 = new Rectangle2D.Double(nextX3, nextY2, colWidths3[col3], rowHeights4[row4]);
                TableCell<XSLFShape, XSLFTextParagraph> cell2 = getCell(row4, col3);
                if (cell2 != null) {
                    cell2.setAnchor(r7);
                    nextX3 += colWidths3[col3] + 2.0d;
                }
            }
            nextY2 += rowHeights4[row4] + 2.0d;
            row4++;
            nextY3 = nextX3;
        }
        int row5 = 0;
        while (row5 < rows) {
            int col4 = 0;
            while (col4 < cols2) {
                ?? cell3 = getCell(row5, col4);
                if (cell3 == 0) {
                    cols = cols2;
                    row = row5;
                } else {
                    Rectangle2D mergedBounds = cell3.getAnchor();
                    int col22 = col4 + 1;
                    while (col22 < cell3.getGridSpan() + col4) {
                        if (col22 >= cols2) {
                            throw new AssertionError();
                        }
                        ?? cell4 = getCell(row5, col22);
                        int cols3 = cols2;
                        int row6 = row5;
                        if (cell4.getGridSpan() != 1 || cell4.getRowSpan() != 1) {
                            LOG.warn("invalid table span - rendering result is probably wrong");
                        }
                        mergedBounds.add(cell4.getAnchor());
                        col22++;
                        cols2 = cols3;
                        row5 = row6;
                    }
                    cols = cols2;
                    row = row5;
                    for (int row22 = row + 1; row22 < row + cell3.getRowSpan(); row22++) {
                        if (row22 >= rows) {
                            throw new AssertionError();
                        }
                        ?? cell5 = getCell(row22, col4);
                        if (cell5.getGridSpan() != 1 || cell5.getRowSpan() != 1) {
                            LOG.warn("invalid table span - rendering result is probably wrong");
                        }
                        mergedBounds.add(cell5.getAnchor());
                    }
                    cell3.setAnchor(mergedBounds);
                }
                col4++;
                cols2 = cols;
                row5 = row;
            }
            row5++;
        }
        setAnchor(new Rectangle2D.Double(tblAnc.getX(), tblAnc.getY(), nextY3 - tblAnc.getX(), nextY2 - tblAnc.getY()));
    }
}
