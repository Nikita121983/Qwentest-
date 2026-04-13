package org.apache.poi.xdgf.usermodel.section.geometry;

import com.graphbuilder.curve.ControlPath;
import com.graphbuilder.curve.ShapeMultiPath;
import com.graphbuilder.curve.ValueVector;
import com.graphbuilder.geom.PointFactory;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.geom.SplineRenderer;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

/* loaded from: classes10.dex */
public class NURBSTo implements GeometryRow {
    NURBSTo _master;
    Double a;
    Double b;
    Double c;
    Double d;
    Boolean deleted;
    String e;
    Double x;
    Double y;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0028. Please report as an issue. */
    public NURBSTo(RowType row) {
        char c;
        if (row.isSetDel()) {
            this.deleted = Boolean.valueOf(row.getDel());
        }
        for (CellType cell : row.getCellArray()) {
            String cellName = cell.getN();
            if (cellName == null) {
                throw new POIXMLException("Invalid null-cell in NURBS row");
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
                case 69:
                    if (cellName.equals("E")) {
                        c = 6;
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
                case 6:
                    this.e = cell.getV();
                    break;
                default:
                    throw new POIXMLException("Invalid cell '" + cellName + "' in NURBS row");
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

    public String getE() {
        return this.e == null ? this._master.e : this.e;
    }

    @Override // org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow
    public void setupMaster(GeometryRow row) {
        this._master = (NURBSTo) row;
    }

    @Override // org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow
    public void addToPath(Path2D.Double path, XDGFShape parent) {
        if (getDel()) {
            return;
        }
        Point2D last = path.getCurrentPoint();
        String formula = getE().trim();
        if (!formula.startsWith("NURBS(") || !formula.endsWith(")")) {
            throw new POIXMLException("Invalid NURBS formula: " + formula);
        }
        String[] components = formula.substring(6, formula.length() - 1).split(CollectionUtils.COMMA);
        if (components.length < 8) {
            throw new POIXMLException("Invalid NURBS formula (not enough arguments)");
        }
        if ((components.length - 4) % 4 != 0) {
            throw new POIXMLException("Invalid NURBS formula -- need 4 + n*4 arguments, got " + components.length);
        }
        double lastControlX = getX().doubleValue();
        double lastControlY = getY().doubleValue();
        double secondToLastKnot = getA().doubleValue();
        double lastWeight = getB().doubleValue();
        double firstKnot = getC().doubleValue();
        double firstWeight = getD().doubleValue();
        double lastKnot = Double.parseDouble(components[0].trim());
        int degree = Integer.parseInt(components[1].trim());
        int xType = Integer.parseInt(components[2].trim());
        int yType = Integer.parseInt(components[3].trim());
        double xScale = 1.0d;
        double yScale = 1.0d;
        if (xType == 0) {
            xScale = parent.getWidth().doubleValue();
        }
        if (yType == 0) {
            yScale = parent.getHeight().doubleValue();
        }
        ControlPath controlPath = new ControlPath();
        ValueVector knots = new ValueVector();
        ValueVector weights = new ValueVector();
        knots.add(firstKnot);
        weights.add(firstWeight);
        controlPath.addPoint(PointFactory.create(last.getX(), last.getY()));
        int sets = (components.length - 4) / 4;
        int i = 0;
        while (i < sets) {
            double x1 = Double.parseDouble(components[(i * 4) + 4 + 0].trim());
            double y1 = Double.parseDouble(components[(i * 4) + 4 + 1].trim());
            int i2 = i;
            double k = Double.parseDouble(components[(i * 4) + 4 + 2].trim());
            String formula2 = formula;
            double w = Double.parseDouble(components[(i2 * 4) + 4 + 3].trim());
            double lastWeight2 = lastWeight;
            double lastWeight3 = x1 * xScale;
            double lastKnot2 = lastKnot;
            double lastKnot3 = y1 * yScale;
            controlPath.addPoint(PointFactory.create(lastWeight3, lastKnot3));
            knots.add(k);
            weights.add(w);
            i = i2 + 1;
            formula = formula2;
            components = components;
            lastWeight = lastWeight2;
            lastKnot = lastKnot2;
        }
        knots.add(secondToLastKnot);
        knots.add(lastKnot);
        weights.add(lastWeight);
        controlPath.addPoint(PointFactory.create(lastControlX, lastControlY));
        ShapeMultiPath shape = SplineRenderer.createNurbsSpline(controlPath, knots, weights, degree);
        path.append(shape, true);
    }
}
