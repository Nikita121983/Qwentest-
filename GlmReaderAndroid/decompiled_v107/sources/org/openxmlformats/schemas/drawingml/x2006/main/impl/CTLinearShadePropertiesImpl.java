package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveFixedAngle;

/* loaded from: classes11.dex */
public class CTLinearShadePropertiesImpl extends XmlComplexContentImpl implements CTLinearShadeProperties {
    private static final QName[] PROPERTY_QNAME = {new QName("", "ang"), new QName("", "scaled")};
    private static final long serialVersionUID = 1;

    public CTLinearShadePropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties
    public int getAng() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties
    public STPositiveFixedAngle xgetAng() {
        STPositiveFixedAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveFixedAngle) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties
    public boolean isSetAng() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties
    public void setAng(int ang) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setIntValue(ang);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties
    public void xsetAng(STPositiveFixedAngle ang) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveFixedAngle target = (STPositiveFixedAngle) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STPositiveFixedAngle) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(ang);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties
    public void unsetAng() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties
    public boolean getScaled() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties
    public XmlBoolean xgetScaled() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties
    public boolean isSetScaled() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties
    public void setScaled(boolean scaled) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setBooleanValue(scaled);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties
    public void xsetScaled(XmlBoolean scaled) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(scaled);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties
    public void unsetScaled() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
