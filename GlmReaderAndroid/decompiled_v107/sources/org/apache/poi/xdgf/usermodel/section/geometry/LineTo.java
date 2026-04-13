package org.apache.poi.xdgf.usermodel.section.geometry;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.Path2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

/* loaded from: classes10.dex */
public class LineTo implements GeometryRow {
    LineTo _master;
    Boolean deleted;
    Double x;
    Double y;

    public LineTo(RowType row) {
        if (row.isSetDel()) {
            this.deleted = Boolean.valueOf(row.getDel());
        }
        for (CellType cell : row.getCellArray()) {
            String cellName = cell.getN();
            if ("X".equals(cellName)) {
                this.x = XDGFCell.parseDoubleValue(cell);
            } else if ("Y".equals(cellName)) {
                this.y = XDGFCell.parseDoubleValue(cell);
            } else {
                throw new POIXMLException("Invalid cell '" + cellName + "' in LineTo row");
            }
        }
    }

    public String toString() {
        return "LineTo: x=" + getX() + "; y=" + getY();
    }

    public boolean getDel() {
        if (this.deleted != null) {
            return this.deleted.booleanValue();
        }
        return this._master != null && this._master.getDel();
    }

    public Double getX() {
        return this.x == null ? this._master.x : this.x;
    }

    public Double getY() {
        return this.y == null ? this._master.y : this.y;
    }

    @Override // org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow
    public void setupMaster(GeometryRow row) {
        this._master = (LineTo) row;
    }

    @Override // org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow
    public void addToPath(Path2D.Double path, XDGFShape parent) {
        if (getDel()) {
            return;
        }
        path.lineTo(getX().doubleValue(), getY().doubleValue());
    }
}
