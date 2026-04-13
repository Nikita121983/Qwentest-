package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.SignaturePolicyIdType;
import org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType;

/* loaded from: classes11.dex */
public class SignaturePolicyIdentifierTypeImpl extends XmlComplexContentImpl implements SignaturePolicyIdentifierType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "SignaturePolicyId"), new QName(SignatureFacet.XADES_132_NS, "SignaturePolicyImplied")};
    private static final long serialVersionUID = 1;

    public SignaturePolicyIdentifierTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType
    public SignaturePolicyIdType getSignaturePolicyId() {
        SignaturePolicyIdType signaturePolicyIdType;
        synchronized (monitor()) {
            check_orphaned();
            SignaturePolicyIdType target = (SignaturePolicyIdType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            signaturePolicyIdType = target == null ? null : target;
        }
        return signaturePolicyIdType;
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType
    public boolean isSetSignaturePolicyId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType
    public void setSignaturePolicyId(SignaturePolicyIdType signaturePolicyId) {
        generatedSetterHelperImpl(signaturePolicyId, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType
    public SignaturePolicyIdType addNewSignaturePolicyId() {
        SignaturePolicyIdType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SignaturePolicyIdType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType
    public void unsetSignaturePolicyId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType
    public XmlObject getSignaturePolicyImplied() {
        XmlObject xmlObject;
        synchronized (monitor()) {
            check_orphaned();
            XmlObject target = (XmlObject) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            xmlObject = target == null ? null : target;
        }
        return xmlObject;
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType
    public boolean isSetSignaturePolicyImplied() {
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

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType
    public void setSignaturePolicyImplied(XmlObject signaturePolicyImplied) {
        generatedSetterHelperImpl(signaturePolicyImplied, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType
    public XmlObject addNewSignaturePolicyImplied() {
        XmlObject target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlObject) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType
    public void unsetSignaturePolicyImplied() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
