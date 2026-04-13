package org.etsi.uri.x01903.v13.impl;

import java.math.BigInteger;
import java.util.Calendar;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CRLIdentifierType;

/* loaded from: classes11.dex */
public class CRLIdentifierTypeImpl extends XmlComplexContentImpl implements CRLIdentifierType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "Issuer"), new QName(SignatureFacet.XADES_132_NS, "IssueTime"), new QName(SignatureFacet.XADES_132_NS, "Number"), new QName("", "URI")};
    private static final long serialVersionUID = 1;

    public CRLIdentifierTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public String getIssuer() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public XmlString xgetIssuer() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public void setIssuer(String issuer) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(issuer);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public void xsetIssuer(XmlString issuer) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(issuer);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public Calendar getIssueTime() {
        Calendar calendarValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            calendarValue = target == null ? null : target.getCalendarValue();
        }
        return calendarValue;
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public XmlDateTime xgetIssueTime() {
        XmlDateTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDateTime) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public void setIssueTime(Calendar issueTime) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setCalendarValue(issueTime);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public void xsetIssueTime(XmlDateTime issueTime) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDateTime target = (XmlDateTime) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (XmlDateTime) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(issueTime);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public BigInteger getNumber() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public XmlInteger xgetNumber() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[2], 0);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public boolean isSetNumber() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public void setNumber(BigInteger number) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.setBigIntegerValue(number);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public void xsetNumber(XmlInteger number) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.set(number);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public void unsetNumber() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public String getURI() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public XmlAnyURI xgetURI() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public boolean isSetURI() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
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

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
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

    @Override // org.etsi.uri.x01903.v13.CRLIdentifierType
    public void unsetURI() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }
}
