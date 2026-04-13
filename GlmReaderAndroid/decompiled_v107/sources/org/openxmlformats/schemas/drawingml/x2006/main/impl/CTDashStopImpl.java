package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositivePercentage;

/* loaded from: classes11.dex */
public class CTDashStopImpl extends XmlComplexContentImpl implements CTDashStop {
    private static final QName[] PROPERTY_QNAME = {new QName("", "d"), new QName("", "sp")};
    private static final long serialVersionUID = 1;

    public CTDashStopImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop
    public Object getD() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop
    public STPositivePercentage xgetD() {
        STPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositivePercentage) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop
    public void setD(Object d) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(d);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop
    public void xsetD(STPositivePercentage d) {
        synchronized (monitor()) {
            check_orphaned();
            STPositivePercentage target = (STPositivePercentage) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STPositivePercentage) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(d);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop
    public Object getSp() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop
    public STPositivePercentage xgetSp() {
        STPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositivePercentage) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop
    public void setSp(Object sp) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setObjectValue(sp);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop
    public void xsetSp(STPositivePercentage sp) {
        synchronized (monitor()) {
            check_orphaned();
            STPositivePercentage target = (STPositivePercentage) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STPositivePercentage) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(sp);
        }
    }
}
