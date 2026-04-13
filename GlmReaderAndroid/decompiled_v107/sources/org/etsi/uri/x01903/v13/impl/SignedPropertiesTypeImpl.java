package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType;
import org.etsi.uri.x01903.v13.SignedPropertiesType;
import org.etsi.uri.x01903.v13.SignedSignaturePropertiesType;

/* loaded from: classes11.dex */
public class SignedPropertiesTypeImpl extends XmlComplexContentImpl implements SignedPropertiesType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "SignedSignatureProperties"), new QName(SignatureFacet.XADES_132_NS, "SignedDataObjectProperties"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public SignedPropertiesTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public SignedSignaturePropertiesType getSignedSignatureProperties() {
        SignedSignaturePropertiesType signedSignaturePropertiesType;
        synchronized (monitor()) {
            check_orphaned();
            SignedSignaturePropertiesType target = (SignedSignaturePropertiesType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            signedSignaturePropertiesType = target == null ? null : target;
        }
        return signedSignaturePropertiesType;
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public boolean isSetSignedSignatureProperties() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public void setSignedSignatureProperties(SignedSignaturePropertiesType signedSignatureProperties) {
        generatedSetterHelperImpl(signedSignatureProperties, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public SignedSignaturePropertiesType addNewSignedSignatureProperties() {
        SignedSignaturePropertiesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SignedSignaturePropertiesType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public void unsetSignedSignatureProperties() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public SignedDataObjectPropertiesType getSignedDataObjectProperties() {
        SignedDataObjectPropertiesType signedDataObjectPropertiesType;
        synchronized (monitor()) {
            check_orphaned();
            SignedDataObjectPropertiesType target = (SignedDataObjectPropertiesType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            signedDataObjectPropertiesType = target == null ? null : target;
        }
        return signedDataObjectPropertiesType;
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public boolean isSetSignedDataObjectProperties() {
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

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public void setSignedDataObjectProperties(SignedDataObjectPropertiesType signedDataObjectProperties) {
        generatedSetterHelperImpl(signedDataObjectProperties, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public SignedDataObjectPropertiesType addNewSignedDataObjectProperties() {
        SignedDataObjectPropertiesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SignedDataObjectPropertiesType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public void unsetSignedDataObjectProperties() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public void xsetId(XmlID id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedPropertiesType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }
}
