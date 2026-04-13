package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DList;

/* loaded from: classes11.dex */
public class CTCustomGeometry2DImpl extends XmlComplexContentImpl implements CTCustomGeometry2D {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "avLst"), new QName(XSSFRelation.NS_DRAWINGML, "gdLst"), new QName(XSSFRelation.NS_DRAWINGML, "ahLst"), new QName(XSSFRelation.NS_DRAWINGML, "cxnLst"), new QName(XSSFRelation.NS_DRAWINGML, "rect"), new QName(XSSFRelation.NS_DRAWINGML, "pathLst")};
    private static final long serialVersionUID = 1;

    public CTCustomGeometry2DImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public CTGeomGuideList getAvLst() {
        CTGeomGuideList cTGeomGuideList;
        synchronized (monitor()) {
            check_orphaned();
            CTGeomGuideList target = (CTGeomGuideList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTGeomGuideList = target == null ? null : target;
        }
        return cTGeomGuideList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public boolean isSetAvLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public void setAvLst(CTGeomGuideList avLst) {
        generatedSetterHelperImpl(avLst, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public CTGeomGuideList addNewAvLst() {
        CTGeomGuideList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGeomGuideList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public void unsetAvLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public CTGeomGuideList getGdLst() {
        CTGeomGuideList cTGeomGuideList;
        synchronized (monitor()) {
            check_orphaned();
            CTGeomGuideList target = (CTGeomGuideList) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTGeomGuideList = target == null ? null : target;
        }
        return cTGeomGuideList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public boolean isSetGdLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public void setGdLst(CTGeomGuideList gdLst) {
        generatedSetterHelperImpl(gdLst, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public CTGeomGuideList addNewGdLst() {
        CTGeomGuideList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGeomGuideList) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public void unsetGdLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public CTAdjustHandleList getAhLst() {
        CTAdjustHandleList cTAdjustHandleList;
        synchronized (monitor()) {
            check_orphaned();
            CTAdjustHandleList target = (CTAdjustHandleList) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTAdjustHandleList = target == null ? null : target;
        }
        return cTAdjustHandleList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public boolean isSetAhLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public void setAhLst(CTAdjustHandleList ahLst) {
        generatedSetterHelperImpl(ahLst, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public CTAdjustHandleList addNewAhLst() {
        CTAdjustHandleList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAdjustHandleList) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public void unsetAhLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public CTConnectionSiteList getCxnLst() {
        CTConnectionSiteList cTConnectionSiteList;
        synchronized (monitor()) {
            check_orphaned();
            CTConnectionSiteList target = (CTConnectionSiteList) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTConnectionSiteList = target == null ? null : target;
        }
        return cTConnectionSiteList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public boolean isSetCxnLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public void setCxnLst(CTConnectionSiteList cxnLst) {
        generatedSetterHelperImpl(cxnLst, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public CTConnectionSiteList addNewCxnLst() {
        CTConnectionSiteList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTConnectionSiteList) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public void unsetCxnLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public CTGeomRect getRect() {
        CTGeomRect cTGeomRect;
        synchronized (monitor()) {
            check_orphaned();
            CTGeomRect target = (CTGeomRect) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTGeomRect = target == null ? null : target;
        }
        return cTGeomRect;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public boolean isSetRect() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public void setRect(CTGeomRect rect) {
        generatedSetterHelperImpl(rect, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public CTGeomRect addNewRect() {
        CTGeomRect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGeomRect) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public void unsetRect() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public CTPath2DList getPathLst() {
        CTPath2DList cTPath2DList;
        synchronized (monitor()) {
            check_orphaned();
            CTPath2DList target = (CTPath2DList) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTPath2DList = target == null ? null : target;
        }
        return cTPath2DList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public void setPathLst(CTPath2DList pathLst) {
        generatedSetterHelperImpl(pathLst, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D
    public CTPath2DList addNewPathLst() {
        CTPath2DList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPath2DList) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }
}
