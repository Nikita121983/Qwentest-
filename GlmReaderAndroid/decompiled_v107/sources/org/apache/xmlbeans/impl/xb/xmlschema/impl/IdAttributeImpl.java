package org.apache.xmlbeans.impl.xb.xmlschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlschema.IdAttribute;

/* loaded from: classes11.dex */
public class IdAttributeImpl extends XmlComplexContentImpl implements IdAttribute {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/XML/1998/namespace", "id")};
    private static final long serialVersionUID = 1;

    public IdAttributeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.IdAttribute
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.IdAttribute
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.IdAttribute
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.IdAttribute
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.IdAttribute
    public void xsetId(XmlID id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(id);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.IdAttribute
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
