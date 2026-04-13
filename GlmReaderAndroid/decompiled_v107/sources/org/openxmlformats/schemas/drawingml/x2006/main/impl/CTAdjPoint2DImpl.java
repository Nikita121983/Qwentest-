package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;

/* loaded from: classes11.dex */
public class CTAdjPoint2DImpl extends XmlComplexContentImpl implements CTAdjPoint2D {
    private static final QName[] PROPERTY_QNAME = {new QName("", "x"), new QName("", "y")};
    private static final long serialVersionUID = 1;

    public CTAdjPoint2DImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D
    public Object getX() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D
    public STAdjCoordinate xgetX() {
        STAdjCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D
    public void setX(Object x) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(x);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D
    public void xsetX(STAdjCoordinate x) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjCoordinate target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STAdjCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(x);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D
    public Object getY() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D
    public STAdjCoordinate xgetY() {
        STAdjCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D
    public void setY(Object y) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setObjectValue(y);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D
    public void xsetY(STAdjCoordinate y) {
        synchronized (monitor()) {
            check_orphaned();
            STAdjCoordinate target = (STAdjCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STAdjCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(y);
        }
    }
}
