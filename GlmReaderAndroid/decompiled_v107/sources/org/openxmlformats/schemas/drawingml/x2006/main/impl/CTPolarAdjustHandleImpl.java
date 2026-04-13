package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName;

/* loaded from: classes11.dex */
public class CTPolarAdjustHandleImpl extends XmlComplexContentImpl implements CTPolarAdjustHandle {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "pos"), new QName("", "gdRefR"), new QName("", "minR"), new QName("", "maxR"), new QName("", "gdRefAng"), new QName("", "minAng"), new QName("", "maxAng")};
    private static final long serialVersionUID = 1;

    public CTPolarAdjustHandleImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public CTAdjPoint2D getPos() {
        CTAdjPoint2D cTAdjPoint2D;
        synchronized (monitor()) {
            check_orphaned();
            CTAdjPoint2D target = (CTAdjPoint2D) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTAdjPoint2D = target == null ? null : target;
        }
        return cTAdjPoint2D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void setPos(CTAdjPoint2D pos) {
        generatedSetterHelperImpl(pos, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public CTAdjPoint2D addNewPos() {
        CTAdjPoint2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAdjPoint2D) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public String getGdRefR() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public STGeomGuideName xgetGdRefR() {
        STGeomGuideName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGeomGuideName) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public boolean isSetGdRefR() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void setGdRefR(String gdRefR) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(gdRefR);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void xsetGdRefR(STGeomGuideName gdRefR) {
        synchronized (monitor()) {
            check_orphaned();
            STGeomGuideName target = (STGeomGuideName) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STGeomGuideName) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(gdRefR);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void unsetGdRefR() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public Object getMinR() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public STAdjCoordinate xgetMinR() {
        STAdjCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public boolean isSetMinR() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void setMinR(Object minR) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(minR);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void xsetMinR(STAdjCoordinate minR) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjCoordinate target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STAdjCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(minR);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void unsetMinR() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public Object getMaxR() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public STAdjCoordinate xgetMaxR() {
        STAdjCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public boolean isSetMaxR() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void setMaxR(Object maxR) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setObjectValue(maxR);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void xsetMaxR(STAdjCoordinate maxR) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjCoordinate target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STAdjCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(maxR);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void unsetMaxR() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public String getGdRefAng() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public STGeomGuideName xgetGdRefAng() {
        STGeomGuideName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGeomGuideName) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public boolean isSetGdRefAng() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void setGdRefAng(String gdRefAng) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(gdRefAng);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void xsetGdRefAng(STGeomGuideName gdRefAng) {
        synchronized (monitor()) {
            check_orphaned();
            STGeomGuideName target = (STGeomGuideName) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STGeomGuideName) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(gdRefAng);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void unsetGdRefAng() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public Object getMinAng() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public STAdjAngle xgetMinAng() {
        STAdjAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjAngle) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public boolean isSetMinAng() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void setMinAng(Object minAng) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setObjectValue(minAng);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void xsetMinAng(STAdjAngle minAng) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjAngle target = (STAdjAngle) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STAdjAngle) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(minAng);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void unsetMinAng() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public Object getMaxAng() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public STAdjAngle xgetMaxAng() {
        STAdjAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjAngle) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public boolean isSetMaxAng() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void setMaxAng(Object maxAng) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setObjectValue(maxAng);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void xsetMaxAng(STAdjAngle maxAng) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjAngle target = (STAdjAngle) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STAdjAngle) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(maxAng);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
    public void unsetMaxAng() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }
}
