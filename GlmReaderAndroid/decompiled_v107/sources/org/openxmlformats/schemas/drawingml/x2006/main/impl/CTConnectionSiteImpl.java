package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle;

/* loaded from: classes11.dex */
public class CTConnectionSiteImpl extends XmlComplexContentImpl implements CTConnectionSite {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "pos"), new QName("", "ang")};
    private static final long serialVersionUID = 1;

    public CTConnectionSiteImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite
    public CTAdjPoint2D getPos() {
        CTAdjPoint2D cTAdjPoint2D;
        synchronized (monitor()) {
            check_orphaned();
            CTAdjPoint2D target = (CTAdjPoint2D) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTAdjPoint2D = target == null ? null : target;
        }
        return cTAdjPoint2D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite
    public void setPos(CTAdjPoint2D pos) {
        generatedSetterHelperImpl(pos, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite
    public CTAdjPoint2D addNewPos() {
        CTAdjPoint2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAdjPoint2D) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite
    public Object getAng() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite
    public STAdjAngle xgetAng() {
        STAdjAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjAngle) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite
    public void setAng(Object ang) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setObjectValue(ang);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite
    public void xsetAng(STAdjAngle ang) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjAngle target = (STAdjAngle) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STAdjAngle) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(ang);
        }
    }
}
