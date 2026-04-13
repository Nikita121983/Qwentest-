package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIntProperty;

/* loaded from: classes12.dex */
public class CTIntPropertyImpl extends XmlComplexContentImpl implements CTIntProperty {
    private static final QName[] PROPERTY_QNAME = {new QName("", "val")};
    private static final long serialVersionUID = 1;

    public CTIntPropertyImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIntProperty
    public int getVal() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIntProperty
    public XmlInt xgetVal() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIntProperty
    public void setVal(int val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setIntValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIntProperty
    public void xsetVal(XmlInt val) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlInt) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }
}
