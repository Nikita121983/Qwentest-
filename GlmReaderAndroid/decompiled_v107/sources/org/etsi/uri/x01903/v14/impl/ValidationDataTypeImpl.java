package org.etsi.uri.x01903.v14.impl;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CertificateValuesType;
import org.etsi.uri.x01903.v13.RevocationValuesType;
import org.etsi.uri.x01903.v14.ValidationDataType;

/* loaded from: classes11.dex */
public class ValidationDataTypeImpl extends XmlComplexContentImpl implements ValidationDataType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "CertificateValues"), new QName(SignatureFacet.XADES_132_NS, "RevocationValues"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME), new QName("", "URI")};
    private static final long serialVersionUID = 1;

    public ValidationDataTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public CertificateValuesType getCertificateValues() {
        CertificateValuesType certificateValuesType;
        synchronized (monitor()) {
            check_orphaned();
            CertificateValuesType target = (CertificateValuesType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            certificateValuesType = target == null ? null : target;
        }
        return certificateValuesType;
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public boolean isSetCertificateValues() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public void setCertificateValues(CertificateValuesType certificateValues) {
        generatedSetterHelperImpl(certificateValues, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public CertificateValuesType addNewCertificateValues() {
        CertificateValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CertificateValuesType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public void unsetCertificateValues() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public RevocationValuesType getRevocationValues() {
        RevocationValuesType revocationValuesType;
        synchronized (monitor()) {
            check_orphaned();
            RevocationValuesType target = (RevocationValuesType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            revocationValuesType = target == null ? null : target;
        }
        return revocationValuesType;
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public boolean isSetRevocationValues() {
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

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public void setRevocationValues(RevocationValuesType revocationValues) {
        generatedSetterHelperImpl(revocationValues, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public RevocationValuesType addNewRevocationValues() {
        RevocationValuesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (RevocationValuesType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public void unsetRevocationValues() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
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

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
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

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public String getURI() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public XmlAnyURI xgetURI() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public boolean isSetURI() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public void setURI(String uri) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(uri);
        }
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public void xsetURI(XmlAnyURI uri) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(uri);
        }
    }

    @Override // org.etsi.uri.x01903.v14.ValidationDataType
    public void unsetURI() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }
}
