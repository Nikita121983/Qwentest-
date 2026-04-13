package org.apache.poi.xslf.usermodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCell;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableRow;

/* loaded from: classes10.dex */
public class XSLFTableRow implements Iterable<XSLFTableCell> {
    private final List<XSLFTableCell> _cells;
    private final CTTableRow _row;
    private final XSLFTable _table;

    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFTableRow(CTTableRow row, XSLFTable table) {
        this._row = row;
        this._table = table;
        CTTableCell[] tcArray = this._row.getTcArray();
        this._cells = new ArrayList(tcArray.length);
        for (CTTableCell cell : tcArray) {
            this._cells.add(new XSLFTableCell(cell, table));
        }
    }

    public CTTableRow getXmlObject() {
        return this._row;
    }

    @Override // java.lang.Iterable
    public Iterator<XSLFTableCell> iterator() {
        return this._cells.iterator();
    }

    public List<XSLFTableCell> getCells() {
        return Collections.unmodifiableList(this._cells);
    }

    public double getHeight() {
        return Units.toPoints(POIXMLUnits.parseLength(this._row.xgetH()));
    }

    public void setHeight(double height) {
        this._row.setH(Integer.valueOf(Units.toEMU(height)));
    }

    public XSLFTableCell addCell() {
        CTTableCell c = this._row.addNewTc();
        c.set(XSLFTableCell.prototype());
        XSLFTableCell cell = new XSLFTableCell(c, this._table);
        this._cells.add(cell);
        if (this._table.getNumberOfColumns() < this._row.sizeOfTcArray()) {
            this._table.getCTTable().getTblGrid().addNewGridCol().setW(Integer.valueOf(Units.toEMU(100.0d)));
        }
        this._table.updateRowColIndexes();
        return cell;
    }

    public XSLFTableCell insertCell(int colIdx) {
        CTTableCell c = this._row.insertNewTc(colIdx);
        c.set(XSLFTableCell.prototype());
        XSLFTableCell cell = new XSLFTableCell(c, this._table);
        this._cells.add(colIdx, cell);
        if (this._table.getNumberOfColumns() < this._row.sizeOfTcArray()) {
            this._table.getCTTable().getTblGrid().insertNewGridCol(colIdx).setW(Integer.valueOf(Units.toEMU(100.0d)));
        }
        this._table.updateRowColIndexes();
        return cell;
    }

    public void removeCell(int colIdx) {
        if (this._row.sizeOfTcArray() < colIdx) {
            throw new IndexOutOfBoundsException("Cannot remove cell at " + colIdx + "; row has only " + this._row.sizeOfTcArray() + "columns.");
        }
        this._row.removeTc(colIdx);
        this._cells.remove(colIdx);
        this._table.updateRowColIndexes();
    }

    public void mergeCells(int firstCol, int lastCol) {
        if (firstCol >= lastCol) {
            throw new IllegalArgumentException("Cannot merge, first column >= last column : " + firstCol + " >= " + lastCol);
        }
        int colSpan = (lastCol - firstCol) + 1;
        this._cells.get(firstCol).setGridSpan(colSpan);
        for (XSLFTableCell cell : this._cells.subList(firstCol + 1, lastCol + 1)) {
            cell.setHMerge();
        }
    }
}
