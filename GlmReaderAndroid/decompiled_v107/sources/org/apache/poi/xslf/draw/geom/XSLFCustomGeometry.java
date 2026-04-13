package org.apache.poi.xslf.draw.geom;

import org.apache.poi.sl.draw.geom.CustomGeometry;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle;

/* loaded from: classes10.dex */
public class XSLFCustomGeometry {
    public static CustomGeometry convertCustomGeometry(CTCustomGeometry2D custGeom) {
        CustomGeometry cg = new CustomGeometry();
        if (custGeom.isSetAhLst()) {
            CTAdjustHandleList ahLst = custGeom.getAhLst();
            for (CTXYAdjustHandle xy : ahLst.getAhXYArray()) {
                cg.addAdjustHandle(new XSLFXYAdjustHandle(xy));
            }
            for (CTPolarAdjustHandle pol : ahLst.getAhPolarArray()) {
                cg.addAdjustHandle(new XSLFPolarAdjustHandle(pol));
            }
        }
        if (custGeom.isSetAvLst()) {
            CTGeomGuideList avLst = custGeom.getAvLst();
            for (CTGeomGuide gg : avLst.getGdArray()) {
                cg.addAdjustGuide(new XSLFAdjustValue(gg));
            }
        }
        if (custGeom.isSetGdLst()) {
            CTGeomGuideList gdLst = custGeom.getGdLst();
            for (CTGeomGuide gg2 : gdLst.getGdArray()) {
                cg.addGeomGuide(new XSLFGuide(gg2));
            }
        }
        if (custGeom.isSetRect()) {
            CTGeomRect r = custGeom.getRect();
            cg.setTextBounds(r.xgetL().getStringValue(), r.xgetT().getStringValue(), r.xgetR().getStringValue(), r.xgetB().getStringValue());
        }
        if (custGeom.isSetCxnLst()) {
            for (CTConnectionSite cxn : custGeom.getCxnLst().getCxnArray()) {
                cg.addConnectionSite(new XSLFConnectionSite(cxn));
            }
        }
        CTPath2DList pl = custGeom.getPathLst();
        if (pl != null) {
            for (CTPath2D p : pl.getPathArray()) {
                cg.addPath(new XSLFPath(p));
            }
        }
        return cg;
    }
}
