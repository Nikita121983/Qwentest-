package org.apache.poi.xdgf.usermodel.section.geometry;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

/* loaded from: classes10.dex */
public class EllipticalArcTo implements GeometryRow {
    private static final double EPS = 1.0E-10d;
    EllipticalArcTo _master;
    Double a;
    Double b;
    Double c;
    Double d;
    Boolean deleted;
    Double x;
    Double y;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0028. Please report as an issue. */
    public EllipticalArcTo(RowType row) {
        char c;
        if (row.isSetDel()) {
            this.deleted = Boolean.valueOf(row.getDel());
        }
        for (CellType cell : row.getCellArray()) {
            String cellName = cell.getN();
            if (cellName == null) {
                throw new POIXMLException("Missing cellName in EllipticalArcTo row");
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
                    throw new POIXMLException("Invalid cell '" + cellName + "' in EllipticalArcTo row");
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
        this._master = (EllipticalArcTo) row;
    }

    @Override // org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow
    public void addToPath(Path2D.Double path, XDGFShape parent) {
        if (getDel()) {
            return;
        }
        double x = getX().doubleValue();
        double y = getY().doubleValue();
        double a = getA().doubleValue();
        double b = getB().doubleValue();
        double c = getC().doubleValue();
        double d = getD().doubleValue();
        createEllipticalArc(x, y, a, b, c, d, path);
    }

    public static void createEllipticalArc(double x, double y, double a, double b, double c, double d, Path2D.Double path) {
        Point2D last = path.getCurrentPoint();
        double x0 = last.getX();
        double y0 = last.getY();
        if (isColinear(x0, y0, x, y, a, b)) {
            path.lineTo(x, y);
            return;
        }
        AffineTransform at = AffineTransform.getRotateInstance(-c);
        double[] pts = {x0, y0, x, y, a, b};
        at.transform(pts, 0, pts, 0, 3);
        double x02 = pts[0];
        double y02 = pts[1];
        double x2 = pts[2];
        double y2 = pts[3];
        double a2 = pts[4];
        double b2 = pts[5];
        double d2 = d * d;
        double cx = (((((x02 - x2) * (x02 + x2)) * (y2 - b2)) - (((x2 - a2) * (x2 + a2)) * (y02 - y2))) + ((((y02 - y2) * d2) * (y2 - b2)) * (y02 - b2))) / ((((x02 - x2) * (y2 - b2)) - ((x2 - a2) * (y02 - y2))) * 2.0d);
        double cy = ((((((x02 - x2) * (x2 - a2)) * (x02 - a2)) / d2) + (((x2 - a2) * (y02 - y2)) * (y02 + y2))) - (((x02 - x2) * (y2 - b2)) * (y2 + b2))) / ((((x2 - a2) * (y02 - y2)) - ((x02 - x2) * (y2 - b2))) * 2.0d);
        double rx = Math.sqrt(Math.pow(x02 - cx, 2.0d) + (Math.pow(y02 - cy, 2.0d) * d2));
        double ry = rx / d;
        double ctrlAngle = Math.toDegrees(Math.atan2((b2 - cy) / ry, (a2 - cx) / rx));
        double startAngle = Math.toDegrees(Math.atan2((y02 - cy) / ry, (x02 - cx) / rx));
        double endAngle = Math.toDegrees(Math.atan2((y2 - cy) / ry, (x2 - cx) / rx));
        double sweep = computeSweep(startAngle, endAngle, ctrlAngle);
        Arc2D.Double r36 = new Arc2D.Double(cx - rx, cy - ry, rx * 2.0d, ry * 2.0d, -startAngle, sweep, 0);
        at.setToRotation(c);
        path.append(at.createTransformedShape(r36), false);
    }

    private static boolean isColinear(double x1, double y1, double x2, double y2, double x3, double y3) {
        return Math.abs(((y1 - y2) * (x1 - x3)) - ((y1 - y3) * (x1 - x2))) < 1.0E-10d;
    }

    protected static double computeSweep(double startAngle, double endAngle, double ctrlAngle) {
        double startAngle2 = (startAngle + 360.0d) % 360.0d;
        double startAngle3 = endAngle + 360.0d;
        double endAngle2 = startAngle3 % 360.0d;
        double endAngle3 = ctrlAngle + 360.0d;
        double ctrlAngle2 = endAngle3 % 360.0d;
        if (startAngle2 < endAngle2) {
            if (startAngle2 < ctrlAngle2 && ctrlAngle2 < endAngle2) {
                double sweep = startAngle2 - endAngle2;
                return sweep;
            }
            double sweep2 = startAngle2 - endAngle2;
            return sweep2 + 360.0d;
        }
        if (endAngle2 < ctrlAngle2 && ctrlAngle2 < startAngle2) {
            double sweep3 = startAngle2 - endAngle2;
            return sweep3;
        }
        double sweep4 = startAngle2 - endAngle2;
        return -(360.0d - sweep4);
    }
}
