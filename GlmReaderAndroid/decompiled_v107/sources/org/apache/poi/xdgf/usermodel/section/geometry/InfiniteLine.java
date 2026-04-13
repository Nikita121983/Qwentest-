package org.apache.poi.xdgf.usermodel.section.geometry;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.Path2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

/* loaded from: classes10.dex */
public class InfiniteLine implements GeometryRow {
    InfiniteLine _master;
    Double a;
    Double b;
    Boolean deleted;
    Double x;
    Double y;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0026. Please report as an issue. */
    public InfiniteLine(RowType row) {
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
                case 66:
                    if (cellName.equals("B")) {
                        c = 3;
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
                case 3:
                    this.b = XDGFCell.parseDoubleValue(cell);
                    break;
                default:
                    throw new POIXMLException("Invalid cell '" + cellName + "' in InfiniteLine row");
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

    public Double getB() {
        return this.b == null ? this._master.b : this.b;
    }

    @Override // org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow
    public void setupMaster(GeometryRow row) {
        this._master = (InfiniteLine) row;
    }

    @Override // org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow
    public void addToPath(Path2D.Double path, XDGFShape parent) {
        if (getDel()) {
        } else {
            throw new POIXMLException("InfiniteLine elements cannot be part of a path");
        }
    }

    public Path2D.Double getPath() {
        Path2D.Double path = new Path2D.Double();
        double x0 = getX().doubleValue();
        double y0 = getY().doubleValue();
        double x1 = getA().doubleValue();
        double y1 = getB().doubleValue();
        if (x0 == x1) {
            path.moveTo(x0, -100000.0d);
            path.lineTo(x0, 100000.0d);
        } else if (y0 == y1) {
            path.moveTo(-100000.0d, y0);
            path.lineTo(100000.0d, y0);
        } else {
            double m = (y1 - y0) / (x1 - x0);
            double c = y0 - (m * x0);
            path.moveTo(100000.0d, (m * 100000.0d) + c);
            path.lineTo(100000.0d, (100000.0d - c) / m);
        }
        return path;
    }
}
