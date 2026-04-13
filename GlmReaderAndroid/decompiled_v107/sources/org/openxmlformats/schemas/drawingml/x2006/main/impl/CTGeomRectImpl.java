package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;

/* loaded from: classes11.dex */
public class CTGeomRectImpl extends XmlComplexContentImpl implements CTGeomRect {
    private static final QName[] PROPERTY_QNAME = {new QName("", "l"), new QName("", "t"), new QName("", "r"), new QName("", "b")};
    private static final long serialVersionUID = 1;

    public CTGeomRectImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public Object getL() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public STAdjCoordinate xgetL() {
        STAdjCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public void setL(Object l) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(l);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public void xsetL(STAdjCoordinate l) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjCoordinate target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STAdjCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(l);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public Object getT() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public STAdjCoordinate xgetT() {
        STAdjCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public void setT(Object t) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setObjectValue(t);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public void xsetT(STAdjCoordinate t) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjCoordinate target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STAdjCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(t);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public Object getR() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public STAdjCoordinate xgetR() {
        STAdjCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public void setR(Object r) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(r);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public void xsetR(STAdjCoordinate r) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjCoordinate target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STAdjCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(r);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public Object getB() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public STAdjCoordinate xgetB() {
        STAdjCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public void setB(Object b) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setObjectValue(b);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect
    public void xsetB(STAdjCoordinate b) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjCoordinate target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STAdjCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(b);
        }
    }
}
