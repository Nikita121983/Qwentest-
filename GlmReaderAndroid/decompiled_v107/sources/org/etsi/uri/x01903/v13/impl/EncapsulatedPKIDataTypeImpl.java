package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.JavaBase64HolderEx;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;

/* loaded from: classes11.dex */
public class EncapsulatedPKIDataTypeImpl extends JavaBase64HolderEx implements EncapsulatedPKIDataType {
    private static final QName[] PROPERTY_QNAME = {new QName("", PackageRelationship.ID_ATTRIBUTE_NAME), new QName("", "Encoding")};
    private static final long serialVersionUID = 1;

    public EncapsulatedPKIDataTypeImpl(SchemaType sType) {
        super(sType, true);
    }

    protected EncapsulatedPKIDataTypeImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }

    @Override // org.etsi.uri.x01903.v13.EncapsulatedPKIDataType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.EncapsulatedPKIDataType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.EncapsulatedPKIDataType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.EncapsulatedPKIDataType
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

    @Override // org.etsi.uri.x01903.v13.EncapsulatedPKIDataType
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

    @Override // org.etsi.uri.x01903.v13.EncapsulatedPKIDataType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.etsi.uri.x01903.v13.EncapsulatedPKIDataType
    public String getEncoding() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.EncapsulatedPKIDataType
    public XmlAnyURI xgetEncoding() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.EncapsulatedPKIDataType
    public boolean isSetEncoding() {
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

    @Override // org.etsi.uri.x01903.v13.EncapsulatedPKIDataType
    public void setEncoding(String encoding) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(encoding);
        }
    }

    @Override // org.etsi.uri.x01903.v13.EncapsulatedPKIDataType
    public void xsetEncoding(XmlAnyURI encoding) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(encoding);
        }
    }

    @Override // org.etsi.uri.x01903.v13.EncapsulatedPKIDataType
    public void unsetEncoding() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
