package org.apache.poi.xdgf.usermodel.section.geometry;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

/* loaded from: classes10.dex */
public class Ellipse implements GeometryRow {
    Ellipse _master;
    Double a;
    Double b;
    Double c;
    Double d;
    Boolean deleted;
    Double x;
    Double y;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x002c. Please report as an issue. */
    public Ellipse(RowType row) {
        char c;
        if (row.isSetDel()) {
            this.deleted = Boolean.valueOf(row.getDel());
        }
        for (CellType cell : row.getCellArray()) {
            String cellName = cell.getN();
            if (cellName == null) {
                throw new POIXMLException("Invalid cell '" + cellName + "' in Ellipse row");
            }
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
                case 67:
                    if (cellName.equals("C")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 68:
                    if (cellName.equals("D")) {
                        c = 5;
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
                case 4:
                    this.c = XDGFCell.parseDoubleValue(cell);
                    break;
                case 5:
                    this.d = XDGFCell.parseDoubleValue(cell);
                    break;
                default:
                    throw new POIXMLException("Invalid cell '" + cellName + "' in Ellipse row");
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

    public Double getC() {
        return this.c == null ? this._master.c : this.c;
    }

    public Double getD() {
        return this.d == null ? this._master.d : this.d;
    }

    @Override // org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow
    public void setupMaster(GeometryRow row) {
        this._master = (Ellipse) row;
    }

    public Path2D.Double getPath() {
        if (getDel()) {
            return null;
        }
        double cx = getX().doubleValue();
        double cy = getY().doubleValue();
        double a = getA().doubleValue();
        double b = getB().doubleValue();
        double c = getC().doubleValue();
        double d = getD().doubleValue();
        double rx = Math.hypot(a - cx, b - cy);
        double ry = Math.hypot(c - cx, d - cy);
        double angle = (((cy > b ? 1.0d : -1.0d) * Math.acos((cx - a) / rx)) + 6.283185307179586d) % 6.283185307179586d;
        Ellipse2D.Double ellipse = new Ellipse2D.Double(cx - rx, cy - ry, rx * 2.0d, ry * 2.0d);
        Path2D.Double path = new Path2D.Double(ellipse);
        AffineTransform tr = new AffineTransform();
        tr.rotate(angle, cx, cy);
        path.transform(tr);
        return path;
    }

    @Override // org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow
    public void addToPath(Path2D.Double path, XDGFShape parent) {
        throw new POIXMLException("Ellipse elements cannot be part of a path");
    }
}
