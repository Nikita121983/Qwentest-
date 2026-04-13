package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.AdjustPointIf;
import org.apache.poi.sl.draw.geom.LineToCommandIf;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo;

/* loaded from: classes10.dex */
public class XSLFLineTo implements LineToCommandIf {
    private final CTPath2DLineTo lineTo;

    public XSLFLineTo(CTPath2DLineTo lineTo) {
        this.lineTo = lineTo;
    }

    @Override // org.apache.poi.sl.draw.geom.LineToCommandIf
    public AdjustPointIf getPt() {
        return new XSLFAdjustPoint(this.lineTo.getPt());
    }

    @Override // org.apache.poi.sl.draw.geom.LineToCommandIf
    public void setPt(AdjustPointIf pt) {
        CTAdjPoint2D xpt = this.lineTo.getPt();
        if (xpt == null) {
            xpt = this.lineTo.addNewPt();
        }
        xpt.setX(pt.getX());
        xpt.setY(pt.getY());
    }
}
