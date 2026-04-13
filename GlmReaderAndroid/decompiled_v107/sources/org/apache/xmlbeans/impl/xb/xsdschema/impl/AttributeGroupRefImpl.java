package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupRef;

/* loaded from: classes11.dex */
public class AttributeGroupRefImpl extends AttributeGroupImpl implements AttributeGroupRef {
    private static final QName[] PROPERTY_QNAME = {new QName("", "ref")};
    private static final long serialVersionUID = 1;

    public AttributeGroupRefImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.AttributeGroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup
    public QName getRef() {
        QName qNameValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            qNameValue = target == null ? null : target.getQNameValue();
        }
        return qNameValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.AttributeGroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup
    public XmlQName xgetRef() {
        XmlQName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.AttributeGroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup
    public boolean isSetRef() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.AttributeGroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup
    public void setRef(QName ref) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setQNameValue(ref);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.AttributeGroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup
    public void xsetRef(XmlQName ref) {
        synchronized (monitor()) {
            check_orphaned();
            XmlQName target = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlQName) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(ref);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.impl.AttributeGroupImpl, org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup
    public void unsetRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
