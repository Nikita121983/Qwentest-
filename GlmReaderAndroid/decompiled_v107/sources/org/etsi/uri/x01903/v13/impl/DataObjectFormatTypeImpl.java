package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.DataObjectFormatType;
import org.etsi.uri.x01903.v13.ObjectIdentifierType;

/* loaded from: classes11.dex */
public class DataObjectFormatTypeImpl extends XmlComplexContentImpl implements DataObjectFormatType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "Description"), new QName(SignatureFacet.XADES_132_NS, "ObjectIdentifier"), new QName(SignatureFacet.XADES_132_NS, "MimeType"), new QName(SignatureFacet.XADES_132_NS, "Encoding"), new QName("", "ObjectReference")};
    private static final long serialVersionUID = 1;

    public DataObjectFormatTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public String getDescription() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public XmlString xgetDescription() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public boolean isSetDescription() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public void setDescription(String description) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(description);
        }
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public void xsetDescription(XmlString description) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(description);
        }
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public void unsetDescription() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public ObjectIdentifierType getObjectIdentifier() {
        ObjectIdentifierType objectIdentifierType;
        synchronized (monitor()) {
            check_orphaned();
            ObjectIdentifierType target = (ObjectIdentifierType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            objectIdentifierType = target == null ? null : target;
        }
        return objectIdentifierType;
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public boolean isSetObjectIdentifier() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public void setObjectIdentifier(ObjectIdentifierType objectIdentifier) {
        generatedSetterHelperImpl(objectIdentifier, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public ObjectIdentifierType addNewObjectIdentifier() {
        ObjectIdentifierType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ObjectIdentifierType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public void unsetObjectIdentifier() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public String getMimeType() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public XmlString xgetMimeType() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[2], 0);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public boolean isSetMimeType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public void setMimeType(String mimeType) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(mimeType);
        }
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public void xsetMimeType(XmlString mimeType) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.set(mimeType);
        }
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public void unsetMimeType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public String getEncoding() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public XmlAnyURI xgetEncoding() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_element_user(PROPERTY_QNAME[3], 0);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public boolean isSetEncoding() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public void setEncoding(String encoding) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(encoding);
        }
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public void xsetEncoding(XmlAnyURI encoding) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI target = (XmlAnyURI) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (target == null) {
                target = (XmlAnyURI) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            target.set(encoding);
        }
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public void unsetEncoding() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public String getObjectReference() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public XmlAnyURI xgetObjectReference() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public void setObjectReference(String objectReference) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(objectReference);
        }
    }

    @Override // org.etsi.uri.x01903.v13.DataObjectFormatType
    public void xsetObjectReference(XmlAnyURI objectReference) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(objectReference);
        }
    }
}
