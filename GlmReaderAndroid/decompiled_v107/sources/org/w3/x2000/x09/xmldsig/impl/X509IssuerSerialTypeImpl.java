package org.w3.x2000.x09.xmldsig.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.w3.x2000.x09.xmldsig.X509IssuerSerialType;

/* loaded from: classes12.dex */
public class X509IssuerSerialTypeImpl extends XmlComplexContentImpl implements X509IssuerSerialType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XML_DIGSIG_NS, "X509IssuerName"), new QName(SignatureFacet.XML_DIGSIG_NS, "X509SerialNumber")};
    private static final long serialVersionUID = 1;

    public X509IssuerSerialTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.w3.x2000.x09.xmldsig.X509IssuerSerialType
    public String getX509IssuerName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.w3.x2000.x09.xmldsig.X509IssuerSerialType
    public XmlString xgetX509IssuerName() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.X509IssuerSerialType
    public void setX509IssuerName(String x509IssuerName) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(x509IssuerName);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.X509IssuerSerialType
    public void xsetX509IssuerName(XmlString x509IssuerName) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(x509IssuerName);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.X509IssuerSerialType
    public BigInteger getX509SerialNumber() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.w3.x2000.x09.xmldsig.X509IssuerSerialType
    public XmlInteger xgetX509SerialNumber() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.X509IssuerSerialType
    public void setX509SerialNumber(BigInteger x509SerialNumber) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setBigIntegerValue(x509SerialNumber);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.X509IssuerSerialType
    public void xsetX509SerialNumber(XmlInteger x509SerialNumber) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(x509SerialNumber);
        }
    }
}
