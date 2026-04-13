package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.ArcToCommandIf;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo;

/* loaded from: classes10.dex */
public class XSLFArcTo implements ArcToCommandIf {
    private final CTPath2DArcTo arc;

    public XSLFArcTo(CTPath2DArcTo arc) {
        this.arc = arc;
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public String getHR() {
        return this.arc.xgetHR().getStringValue();
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public void setHR(String hr) {
        this.arc.setHR(hr);
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public String getWR() {
        return this.arc.xgetHR().getStringValue();
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public void setWR(String wr) {
        this.arc.setWR(wr);
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public String getStAng() {
        return this.arc.xgetStAng().getStringValue();
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public void setStAng(String stAng) {
        this.arc.setStAng(stAng);
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public String getSwAng() {
        return this.arc.xgetSwAng().getStringValue();
    }

    @Override // org.apache.poi.sl.draw.geom.ArcToCommandIf
    public void setSwAng(String swAng) {
        this.arc.setSwAng(swAng);
    }
}
