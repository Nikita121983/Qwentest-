package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.AdjustPointIf;
import org.apache.poi.sl.draw.geom.CurveToCommandIf;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo;

/* loaded from: classes10.dex */
public class XSLFCurveTo implements CurveToCommandIf {
    private final CTPath2DCubicBezierTo bezier;

    public XSLFCurveTo(CTPath2DCubicBezierTo bezier) {
        this.bezier = bezier;
    }

    @Override // org.apache.poi.sl.draw.geom.CurveToCommandIf
    public XSLFAdjustPoint getPt1() {
        return new XSLFAdjustPoint(this.bezier.getPtArray(0));
    }

    @Override // org.apache.poi.sl.draw.geom.CurveToCommandIf
    public void setPt1(AdjustPointIf pt1) {
        CTAdjPoint2D xpt = getOrCreate(0);
        xpt.setX(pt1.getX());
        xpt.setY(pt1.getY());
    }

    @Override // org.apache.poi.sl.draw.geom.CurveToCommandIf
    public XSLFAdjustPoint getPt2() {
        return new XSLFAdjustPoint(this.bezier.getPtArray(1));
    }

    @Override // org.apache.poi.sl.draw.geom.CurveToCommandIf
    public void setPt2(AdjustPointIf pt2) {
        CTAdjPoint2D xpt = getOrCreate(1);
        xpt.setX(pt2.getX());
        xpt.setY(pt2.getY());
    }

    @Override // org.apache.poi.sl.draw.geom.CurveToCommandIf
    public XSLFAdjustPoint getPt3() {
        return new XSLFAdjustPoint(this.bezier.getPtArray(2));
    }

    @Override // org.apache.poi.sl.draw.geom.CurveToCommandIf
    public void setPt3(AdjustPointIf pt3) {
        CTAdjPoint2D xpt = getOrCreate(2);
        xpt.setX(pt3.getX());
        xpt.setY(pt3.getY());
    }

    private CTAdjPoint2D getOrCreate(int idx) {
        for (int i = (idx + 1) - this.bezier.sizeOfPtArray(); i > 0; i--) {
            this.bezier.addNewPt();
        }
        return this.bezier.getPtArray(idx);
    }
}
