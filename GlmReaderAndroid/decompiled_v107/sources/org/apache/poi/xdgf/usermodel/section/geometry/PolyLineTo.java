package org.apache.poi.xdgf.usermodel.section.geometry;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.Path2D;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;

/* loaded from: classes10.dex */
public class PolyLineTo implements GeometryRow {
    private static final String POLYLINE_FORMULA_PREFIX = "POLYLINE(";
    private static final String POLYLINE_FORMULA_SUFFIX = ")";
    PolyLineTo _master;
    String a;
    Boolean deleted;
    Double x;
    Double y;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0026. Please report as an issue. */
    public PolyLineTo(RowType row) {
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
                    this.a = cell.getV();
                    break;
                default:
                    throw new POIXMLException("Invalid cell '" + cellName + "' in ArcTo row");
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

    public String getA() {
        return this.a == null ? this._master.a : this.a;
    }

    @Override // org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow
    public void setupMaster(GeometryRow row) {
        this._master = (PolyLineTo) row;
    }

    @Override // org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow
    public void addToPath(Path2D.Double path, XDGFShape parent) {
        if (getDel()) {
            return;
        }
        String formula = getA().trim();
        if (!formula.startsWith(POLYLINE_FORMULA_PREFIX) || !formula.endsWith(POLYLINE_FORMULA_SUFFIX)) {
            throw new POIXMLException("Invalid POLYLINE formula: " + formula);
        }
        String[] components = formula.substring(POLYLINE_FORMULA_PREFIX.length(), formula.length() - POLYLINE_FORMULA_SUFFIX.length()).split(CollectionUtils.COMMA);
        if (components.length < 2) {
            throw new POIXMLException("Invalid POLYLINE formula (not enough arguments): " + formula);
        }
        if (components.length % 2 != 0) {
            throw new POIXMLException("Invalid POLYLINE formula -- need 2 + n*2 arguments, got " + components.length);
        }
        if (components.length > 2) {
            double xScale = Integer.parseInt(components[0].trim()) == 0 ? parent.getWidth().doubleValue() : 1.0d;
            double yScale = Integer.parseInt(components[1].trim()) == 0 ? parent.getHeight().doubleValue() : 1.0d;
            int i = 2;
            for (int i2 = 1; i < components.length - i2; i2 = 1) {
                double x = Double.parseDouble(components[i].trim());
                double y = Double.parseDouble(components[i + 1].trim());
                double yScale2 = yScale;
                path.lineTo(x * xScale, y * yScale2);
                i += 2;
                yScale = yScale2;
            }
        }
        path.lineTo(getX().doubleValue(), getY().doubleValue());
    }
}
