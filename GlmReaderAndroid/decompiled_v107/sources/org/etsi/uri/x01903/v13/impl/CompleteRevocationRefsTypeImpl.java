package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CRLRefsType;
import org.etsi.uri.x01903.v13.CompleteRevocationRefsType;
import org.etsi.uri.x01903.v13.OCSPRefsType;
import org.etsi.uri.x01903.v13.OtherCertStatusRefsType;

/* loaded from: classes11.dex */
public class CompleteRevocationRefsTypeImpl extends XmlComplexContentImpl implements CompleteRevocationRefsType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "CRLRefs"), new QName(SignatureFacet.XADES_132_NS, "OCSPRefs"), new QName(SignatureFacet.XADES_132_NS, "OtherRefs"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public CompleteRevocationRefsTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public CRLRefsType getCRLRefs() {
        CRLRefsType cRLRefsType;
        synchronized (monitor()) {
            check_orphaned();
            CRLRefsType target = (CRLRefsType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cRLRefsType = target == null ? null : target;
        }
        return cRLRefsType;
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public boolean isSetCRLRefs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public void setCRLRefs(CRLRefsType crlRefs) {
        generatedSetterHelperImpl(crlRefs, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public CRLRefsType addNewCRLRefs() {
        CRLRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CRLRefsType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public void unsetCRLRefs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public OCSPRefsType getOCSPRefs() {
        OCSPRefsType oCSPRefsType;
        synchronized (monitor()) {
            check_orphaned();
            OCSPRefsType target = (OCSPRefsType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            oCSPRefsType = target == null ? null : target;
        }
        return oCSPRefsType;
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public boolean isSetOCSPRefs() {
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

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public void setOCSPRefs(OCSPRefsType ocspRefs) {
        generatedSetterHelperImpl(ocspRefs, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public OCSPRefsType addNewOCSPRefs() {
        OCSPRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (OCSPRefsType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public void unsetOCSPRefs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public OtherCertStatusRefsType getOtherRefs() {
        OtherCertStatusRefsType otherCertStatusRefsType;
        synchronized (monitor()) {
            check_orphaned();
            OtherCertStatusRefsType target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            otherCertStatusRefsType = target == null ? null : target;
        }
        return otherCertStatusRefsType;
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public boolean isSetOtherRefs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public void setOtherRefs(OtherCertStatusRefsType otherRefs) {
        generatedSetterHelperImpl(otherRefs, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public OtherCertStatusRefsType addNewOtherRefs() {
        OtherCertStatusRefsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public void unsetOtherRefs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
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

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
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

    @Override // org.etsi.uri.x01903.v13.CompleteRevocationRefsType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }
}
