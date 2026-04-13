package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;

/* loaded from: classes11.dex */
public class FacetImpl extends AnnotatedImpl implements Facet {
    private static final QName[] PROPERTY_QNAME = {new QName("", "value"), new QName("", "fixed")};
    private static final long serialVersionUID = 1;

    public FacetImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Facet
    public XmlAnySimpleType getValue() {
        XmlAnySimpleType xmlAnySimpleType;
        synchronized (monitor()) {
            check_orphaned();
            XmlAnySimpleType target = (XmlAnySimpleType) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            xmlAnySimpleType = target == null ? null : target;
        }
        return xmlAnySimpleType;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Facet
    public void setValue(XmlAnySimpleType value) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnySimpleType target = (XmlAnySimpleType) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlAnySimpleType) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(value);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Facet
    public XmlAnySimpleType addNewValue() {
        XmlAnySimpleType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnySimpleType) get_store().add_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Facet
    public boolean getFixed() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Facet
    public XmlBoolean xgetFixed() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Facet
    public boolean isSetFixed() {
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

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Facet
    public void setFixed(boolean fixed) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setBooleanValue(fixed);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Facet
    public void xsetFixed(XmlBoolean fixed) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(fixed);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.Facet
    public void unsetFixed() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
