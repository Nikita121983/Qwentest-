package org.apache.poi.xdgf.usermodel.section.geometry;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.Path2D;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

/* loaded from: classes10.dex */
public class SplineKnot implements GeometryRow {
    SplineKnot _master;
    Double a;
    Boolean deleted;
    Double x;
    Double y;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0026. Please report as an issue. */
    public SplineKnot(RowType row) {
        char c;
        if (row.isSetDel()) {
            this.deleted = Boolean.valueOf(row.getDel());
        }
        for (CellType cell : row.getCellArray()) {
            String cellName = cell.getN();
            switch (cellName.hashCode()) {
                case 65:
                    if (cellName.equals("A")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 88:
                    if (cellName.equals("X")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 89:
                    if (cellName.equals("Y")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    this.x = XDGFCell.parseDoubleValue(cell);
                    break;
                case 1:
                    this.y = XDGFCell.parseDoubleValue(cell);
                    break;
                case 2:
                    this.a = XDGFCell.parseDoubleValue(cell);
                    break;
                default:
                    throw new POIXMLException("Invalid cell '" + cellName + "' in SplineKnot row");
            }
        }
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

    public Double getA() {
        return this.a == null ? this._master.a : this.a;
    }

    @Override // org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow
    public void setupMaster(GeometryRow row) {
        this._master = (SplineKnot) row;
    }

    @Override // org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow
    public void addToPath(Path2D.Double path, XDGFShape parent) {
        throw new POIXMLException("Error: Use SplineRenderer!");
    }

    public String toString() {
        return "{SplineKnot x=" + getX() + " y=" + getY() + " a=" + getA() + VectorFormat.DEFAULT_SUFFIX;
    }
}
