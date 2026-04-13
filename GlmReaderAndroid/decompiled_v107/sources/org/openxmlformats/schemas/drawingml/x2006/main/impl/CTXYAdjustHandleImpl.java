package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName;

/* loaded from: classes11.dex */
public class CTXYAdjustHandleImpl extends XmlComplexContentImpl implements CTXYAdjustHandle {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "pos"), new QName("", "gdRefX"), new QName("", "minX"), new QName("", "maxX"), new QName("", "gdRefY"), new QName("", "minY"), new QName("", "maxY")};
    private static final long serialVersionUID = 1;

    public CTXYAdjustHandleImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public CTAdjPoint2D getPos() {
        CTAdjPoint2D cTAdjPoint2D;
        synchronized (monitor()) {
            check_orphaned();
            CTAdjPoint2D target = (CTAdjPoint2D) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTAdjPoint2D = target == null ? null : target;
        }
        return cTAdjPoint2D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void setPos(CTAdjPoint2D pos) {
        generatedSetterHelperImpl(pos, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public CTAdjPoint2D addNewPos() {
        CTAdjPoint2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAdjPoint2D) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public String getGdRefX() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public STGeomGuideName xgetGdRefX() {
        STGeomGuideName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGeomGuideName) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public boolean isSetGdRefX() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void setGdRefX(String gdRefX) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(gdRefX);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void xsetGdRefX(STGeomGuideName gdRefX) {
        synchronized (monitor()) {
            check_orphaned();
            STGeomGuideName target = (STGeomGuideName) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STGeomGuideName) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(gdRefX);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void unsetGdRefX() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public Object getMinX() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public STAdjCoordinate xgetMinX() {
        STAdjCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public boolean isSetMinX() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void setMinX(Object minX) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(minX);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void xsetMinX(STAdjCoordinate minX) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjCoordinate target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STAdjCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(minX);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void unsetMinX() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public Object getMaxX() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public STAdjCoordinate xgetMaxX() {
        STAdjCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public boolean isSetMaxX() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void setMaxX(Object maxX) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setObjectValue(maxX);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void xsetMaxX(STAdjCoordinate maxX) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjCoordinate target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STAdjCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(maxX);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void unsetMaxX() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public String getGdRefY() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public STGeomGuideName xgetGdRefY() {
        STGeomGuideName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGeomGuideName) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public boolean isSetGdRefY() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void setGdRefY(String gdRefY) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(gdRefY);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void xsetGdRefY(STGeomGuideName gdRefY) {
        synchronized (monitor()) {
            check_orphaned();
            STGeomGuideName target = (STGeomGuideName) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STGeomGuideName) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(gdRefY);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void unsetGdRefY() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public Object getMinY() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public STAdjCoordinate xgetMinY() {
        STAdjCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public boolean isSetMinY() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void setMinY(Object minY) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setObjectValue(minY);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void xsetMinY(STAdjCoordinate minY) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjCoordinate target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STAdjCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(minY);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void unsetMinY() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public Object getMaxY() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public STAdjCoordinate xgetMaxY() {
        STAdjCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public boolean isSetMaxY() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void setMaxY(Object maxY) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setObjectValue(maxY);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void xsetMaxY(STAdjCoordinate maxY) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjCoordinate target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STAdjCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(maxY);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
    public void unsetMaxY() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }
}
