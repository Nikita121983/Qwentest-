package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;

/* loaded from: classes11.dex */
public class CTPath2DArcToImpl extends XmlComplexContentImpl implements CTPath2DArcTo {
    private static final QName[] PROPERTY_QNAME = {new QName("", "wR"), new QName("", "hR"), new QName("", "stAng"), new QName("", "swAng")};
    private static final long serialVersionUID = 1;

    public CTPath2DArcToImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public Object getWR() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public STAdjCoordinate xgetWR() {
        STAdjCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public void setWR(Object wr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(wr);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public void xsetWR(STAdjCoordinate wr) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjCoordinate target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STAdjCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(wr);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public Object getHR() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public STAdjCoordinate xgetHR() {
        STAdjCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public void setHR(Object hr) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setObjectValue(hr);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public void xsetHR(STAdjCoordinate hr) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjCoordinate target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STAdjCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(hr);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public Object getStAng() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public STAdjAngle xgetStAng() {
        STAdjAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjAngle) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public void setStAng(Object stAng) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(stAng);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public void xsetStAng(STAdjAngle stAng) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjAngle target = (STAdjAngle) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STAdjAngle) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(stAng);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public Object getSwAng() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public STAdjAngle xgetSwAng() {
        STAdjAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjAngle) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public void setSwAng(Object swAng) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setObjectValue(swAng);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
    public void xsetSwAng(STAdjAngle swAng) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjAngle target = (STAdjAngle) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STAdjAngle) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(swAng);
        }
    }
}
