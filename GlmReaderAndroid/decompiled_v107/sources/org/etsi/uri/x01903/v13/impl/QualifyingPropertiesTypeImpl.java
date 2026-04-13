package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;
import org.etsi.uri.x01903.v13.SignedPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedPropertiesType;

/* loaded from: classes11.dex */
public class QualifyingPropertiesTypeImpl extends XmlComplexContentImpl implements QualifyingPropertiesType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "SignedProperties"), new QName(SignatureFacet.XADES_132_NS, "UnsignedProperties"), new QName("", PackageRelationship.TARGET_ATTRIBUTE_NAME), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public QualifyingPropertiesTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public SignedPropertiesType getSignedProperties() {
        SignedPropertiesType signedPropertiesType;
        synchronized (monitor()) {
            check_orphaned();
            SignedPropertiesType target = (SignedPropertiesType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            signedPropertiesType = target == null ? null : target;
        }
        return signedPropertiesType;
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public boolean isSetSignedProperties() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public void setSignedProperties(SignedPropertiesType signedProperties) {
        generatedSetterHelperImpl(signedProperties, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public SignedPropertiesType addNewSignedProperties() {
        SignedPropertiesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SignedPropertiesType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public void unsetSignedProperties() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public UnsignedPropertiesType getUnsignedProperties() {
        UnsignedPropertiesType unsignedPropertiesType;
        synchronized (monitor()) {
            check_orphaned();
            UnsignedPropertiesType target = (UnsignedPropertiesType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            unsignedPropertiesType = target == null ? null : target;
        }
        return unsignedPropertiesType;
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public boolean isSetUnsignedProperties() {
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

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public void setUnsignedProperties(UnsignedPropertiesType unsignedProperties) {
        generatedSetterHelperImpl(unsignedProperties, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public UnsignedPropertiesType addNewUnsignedProperties() {
        UnsignedPropertiesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (UnsignedPropertiesType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public void unsetUnsignedProperties() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public String getTarget() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public XmlAnyURI xgetTarget() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public void setTarget(String targetValue) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(targetValue);
        }
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public void xsetTarget(XmlAnyURI targetValue) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(targetValue);
        }
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public void xsetId(XmlID id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.QualifyingPropertiesType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }
}
