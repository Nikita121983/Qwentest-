package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CertIDType;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.w3.x2000.x09.xmldsig.X509IssuerSerialType;

/* loaded from: classes11.dex */
public class CertIDTypeImpl extends XmlComplexContentImpl implements CertIDType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "CertDigest"), new QName(SignatureFacet.XADES_132_NS, "IssuerSerial"), new QName("", "URI")};
    private static final long serialVersionUID = 1;

    public CertIDTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.CertIDType
    public DigestAlgAndValueType getCertDigest() {
        DigestAlgAndValueType digestAlgAndValueType;
        synchronized (monitor()) {
            check_orphaned();
            DigestAlgAndValueType target = (DigestAlgAndValueType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            digestAlgAndValueType = target == null ? null : target;
        }
        return digestAlgAndValueType;
    }

    @Override // org.etsi.uri.x01903.v13.CertIDType
    public void setCertDigest(DigestAlgAndValueType certDigest) {
        generatedSetterHelperImpl(certDigest, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.CertIDType
    public DigestAlgAndValueType addNewCertDigest() {
        DigestAlgAndValueType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DigestAlgAndValueType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CertIDType
    public X509IssuerSerialType getIssuerSerial() {
        X509IssuerSerialType x509IssuerSerialType;
        synchronized (monitor()) {
            check_orphaned();
            X509IssuerSerialType target = (X509IssuerSerialType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            x509IssuerSerialType = target == null ? null : target;
        }
        return x509IssuerSerialType;
    }

    @Override // org.etsi.uri.x01903.v13.CertIDType
    public void setIssuerSerial(X509IssuerSerialType issuerSerial) {
        generatedSetterHelperImpl(issuerSerial, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.CertIDType
    public X509IssuerSerialType addNewIssuerSerial() {
        X509IssuerSerialType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (X509IssuerSerialType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CertIDType
    public String getURI() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.CertIDType
    public XmlAnyURI xgetURI() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CertIDType
    public boolean isSetURI() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.CertIDType
    public void setURI(String uri) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(uri);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CertIDType
    public void xsetURI(XmlAnyURI uri) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(uri);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CertIDType
    public void unsetURI() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }
}
