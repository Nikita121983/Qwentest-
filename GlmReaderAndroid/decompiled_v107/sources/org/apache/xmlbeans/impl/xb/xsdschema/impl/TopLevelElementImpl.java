package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement;

/* loaded from: classes11.dex */
public class TopLevelElementImpl extends ElementImpl implements TopLevelElement {
    private static final QName[] PROPERTY_QNAME = {new QName("", "name")};
    private static final long serialVersionUID = 1;

    public TopLevelElementImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl, org.apache.xmlbeans.impl.xb.xsdschema.Element
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl, org.apache.xmlbeans.impl.xb.xsdschema.Element
    public XmlNCName xgetName() {
        XmlNCName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl, org.apache.xmlbeans.impl.xb.xsdschema.Element
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl, org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl, org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void xsetName(XmlNCName name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlNCName target = (XmlNCName) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlNCName) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(name);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.ElementImpl, org.apache.xmlbeans.impl.xb.xsdschema.Element
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
