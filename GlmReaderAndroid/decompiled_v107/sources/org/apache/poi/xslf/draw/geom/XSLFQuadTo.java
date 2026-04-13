package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.AdjustPointIf;
import org.apache.poi.sl.draw.geom.QuadToCommandIf;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DQuadBezierTo;

/* loaded from: classes10.dex */
public class XSLFQuadTo implements QuadToCommandIf {
    private final CTPath2DQuadBezierTo bezier;

    public XSLFQuadTo(CTPath2DQuadBezierTo bezier) {
        this.bezier = bezier;
    }

    @Override // org.apache.poi.sl.draw.geom.QuadToCommandIf
    public AdjustPointIf getPt1() {
        return new XSLFAdjustPoint(this.bezier.getPtArray(0));
    }

    @Override // org.apache.poi.sl.draw.geom.QuadToCommandIf
    public void setPt1(AdjustPointIf pt1) {
    }

    @Override // org.apache.poi.sl.draw.geom.QuadToCommandIf
    public AdjustPointIf getPt2() {
        return new XSLFAdjustPoint(this.bezier.getPtArray(1));
    }

    @Override // org.apache.poi.sl.draw.geom.QuadToCommandIf
    public void setPt2(AdjustPointIf pt2) {
    }
}
