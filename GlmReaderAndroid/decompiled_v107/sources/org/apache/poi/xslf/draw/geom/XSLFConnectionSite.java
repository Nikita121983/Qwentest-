package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.AdjustPointIf;
import org.apache.poi.sl.draw.geom.ConnectionSiteIf;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite;

/* loaded from: classes10.dex */
public class XSLFConnectionSite implements ConnectionSiteIf {
    final CTConnectionSite cxn;

    public XSLFConnectionSite(CTConnectionSite cxn) {
        this.cxn = cxn;
    }

    @Override // org.apache.poi.sl.draw.geom.ConnectionSiteIf
    public AdjustPointIf getPos() {
        return new XSLFAdjustPoint(this.cxn.getPos());
    }

    @Override // org.apache.poi.sl.draw.geom.ConnectionSiteIf
    public void setPos(AdjustPointIf pos) {
        CTAdjPoint2D p = this.cxn.getPos();
        if (p == null) {
            p = this.cxn.addNewPos();
        }
        p.setX(pos.getX());
        p.setY(pos.getY());
    }

    @Override // org.apache.poi.sl.draw.geom.ConnectionSiteIf
    public String getAng() {
        return this.cxn.xgetAng().getStringValue();
    }

    @Override // org.apache.poi.sl.draw.geom.ConnectionSiteIf
    public void setAng(String value) {
        this.cxn.setAng(value);
    }

    @Override // org.apache.poi.sl.draw.geom.ConnectionSiteIf
    public boolean isSetAng() {
        return this.cxn.xgetAng() == null;
    }
}
