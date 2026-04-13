package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.AdjustPointIf;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;

/* loaded from: classes10.dex */
public class XSLFAdjustPoint implements AdjustPointIf {
    private final CTAdjPoint2D pnt;

    public XSLFAdjustPoint(CTAdjPoint2D pnt) {
        this.pnt = pnt;
    }

    @Override // org.apache.poi.sl.draw.geom.AdjustPointIf
    public String getX() {
        return this.pnt.xgetX().getStringValue();
    }

    @Override // org.apache.poi.sl.draw.geom.AdjustPointIf
    public void setX(String value) {
        this.pnt.setX(value);
    }

    @Override // org.apache.poi.sl.draw.geom.AdjustPointIf
    public boolean isSetX() {
        return this.pnt.xgetX() != null;
    }

    @Override // org.apache.poi.sl.draw.geom.AdjustPointIf
    public String getY() {
        return this.pnt.xgetY().getStringValue();
    }

    @Override // org.apache.poi.sl.draw.geom.AdjustPointIf
    public void setY(String value) {
        this.pnt.setY(value);
    }

    @Override // org.apache.poi.sl.draw.geom.AdjustPointIf
    public boolean isSetY() {
        return this.pnt.xgetY() != null;
    }
}
