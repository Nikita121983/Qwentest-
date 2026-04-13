package org.apache.poi.xslf.draw.geom;

import java.awt.geom.Path2D;
import org.apache.poi.sl.draw.geom.ClosePathCommand;
import org.apache.poi.sl.draw.geom.Context;
import org.apache.poi.sl.draw.geom.PathCommand;
import org.apache.poi.sl.draw.geom.PathIf;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DClose;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DMoveTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DQuadBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode;

/* loaded from: classes10.dex */
public class XSLFPath implements PathIf {
    private final CTPath2D pathXml;

    public XSLFPath(CTPath2D pathXml) {
        this.pathXml = pathXml;
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public void addCommand(PathCommand cmd) {
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public Path2D.Double getPath(Context ctx) {
        PathCommand pc;
        Path2D.Double path2D = new Path2D.Double();
        XmlCursor cur = this.pathXml.newCursor();
        try {
            for (boolean hasNext = cur.toFirstChild(); hasNext; hasNext = cur.toNextSibling()) {
                XmlObject xo = cur.getObject();
                if (xo instanceof CTPath2DArcTo) {
                    pc = new XSLFArcTo((CTPath2DArcTo) xo);
                } else if (xo instanceof CTPath2DCubicBezierTo) {
                    pc = new XSLFCurveTo((CTPath2DCubicBezierTo) xo);
                } else if (xo instanceof CTPath2DMoveTo) {
                    pc = new XSLFMoveTo((CTPath2DMoveTo) xo);
                } else if (xo instanceof CTPath2DLineTo) {
                    pc = new XSLFLineTo((CTPath2DLineTo) xo);
                } else if (xo instanceof CTPath2DQuadBezierTo) {
                    pc = new XSLFQuadTo((CTPath2DQuadBezierTo) xo);
                } else {
                    if (xo instanceof CTPath2DClose) {
                        pc = new ClosePathCommand();
                    }
                }
                pc.execute(path2D, ctx);
            }
            if (cur != null) {
                cur.close();
            }
            return path2D;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public boolean isStroked() {
        return this.pathXml.getStroke();
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public void setStroke(boolean stroke) {
        this.pathXml.setStroke(stroke);
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public boolean isFilled() {
        return this.pathXml.getFill() != STPathFillMode.NONE;
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public PaintStyle.PaintModifier getFill() {
        switch (this.pathXml.getFill().intValue()) {
            case 2:
                return PaintStyle.PaintModifier.NORM;
            case 3:
                return PaintStyle.PaintModifier.LIGHTEN;
            case 4:
                return PaintStyle.PaintModifier.LIGHTEN_LESS;
            case 5:
                return PaintStyle.PaintModifier.DARKEN;
            case 6:
                return PaintStyle.PaintModifier.DARKEN_LESS;
            default:
                return PaintStyle.PaintModifier.NONE;
        }
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public void setFill(PaintStyle.PaintModifier fill) {
        STPathFillMode.Enum f;
        switch (fill) {
            case NORM:
                f = STPathFillMode.NORM;
                break;
            case LIGHTEN:
                f = STPathFillMode.LIGHTEN;
                break;
            case LIGHTEN_LESS:
                f = STPathFillMode.LIGHTEN_LESS;
                break;
            case DARKEN:
                f = STPathFillMode.DARKEN;
                break;
            case DARKEN_LESS:
                f = STPathFillMode.DARKEN_LESS;
                break;
            default:
                f = STPathFillMode.NONE;
                break;
        }
        this.pathXml.setFill(f);
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public long getW() {
        return this.pathXml.getW();
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public void setW(long w) {
        this.pathXml.setW(w);
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public long getH() {
        return this.pathXml.getH();
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public void setH(long h) {
        this.pathXml.setH(h);
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public boolean isExtrusionOk() {
        return this.pathXml.getExtrusionOk();
    }

    @Override // org.apache.poi.sl.draw.geom.PathIf
    public void setExtrusionOk(boolean extrusionOk) {
        this.pathXml.setExtrusionOk(extrusionOk);
    }
}
