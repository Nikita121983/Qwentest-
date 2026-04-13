package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.etsi.uri.x01903.v13.ObjectIdentifierType;
import org.etsi.uri.x01903.v13.SigPolicyQualifiersListType;
import org.etsi.uri.x01903.v13.SignaturePolicyIdType;
import org.w3.x2000.x09.xmldsig.TransformsType;

/* loaded from: classes11.dex */
public class SignaturePolicyIdTypeImpl extends XmlComplexContentImpl implements SignaturePolicyIdType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "SigPolicyId"), new QName(SignatureFacet.XML_DIGSIG_NS, "Transforms"), new QName(SignatureFacet.XADES_132_NS, "SigPolicyHash"), new QName(SignatureFacet.XADES_132_NS, "SigPolicyQualifiers")};
    private static final long serialVersionUID = 1;

    public SignaturePolicyIdTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public ObjectIdentifierType getSigPolicyId() {
        ObjectIdentifierType objectIdentifierType;
        synchronized (monitor()) {
            check_orphaned();
            ObjectIdentifierType target = (ObjectIdentifierType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            objectIdentifierType = target == null ? null : target;
        }
        return objectIdentifierType;
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public void setSigPolicyId(ObjectIdentifierType sigPolicyId) {
        generatedSetterHelperImpl(sigPolicyId, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public ObjectIdentifierType addNewSigPolicyId() {
        ObjectIdentifierType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ObjectIdentifierType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public TransformsType getTransforms() {
        TransformsType transformsType;
        synchronized (monitor()) {
            check_orphaned();
            TransformsType target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            transformsType = target == null ? null : target;
        }
        return transformsType;
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public boolean isSetTransforms() {
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

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public void setTransforms(TransformsType transforms) {
        generatedSetterHelperImpl(transforms, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public TransformsType addNewTransforms() {
        TransformsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public void unsetTransforms() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public DigestAlgAndValueType getSigPolicyHash() {
        DigestAlgAndValueType digestAlgAndValueType;
        synchronized (monitor()) {
            check_orphaned();
            DigestAlgAndValueType target = (DigestAlgAndValueType) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            digestAlgAndValueType = target == null ? null : target;
        }
        return digestAlgAndValueType;
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public void setSigPolicyHash(DigestAlgAndValueType sigPolicyHash) {
        generatedSetterHelperImpl(sigPolicyHash, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public DigestAlgAndValueType addNewSigPolicyHash() {
        DigestAlgAndValueType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DigestAlgAndValueType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public SigPolicyQualifiersListType getSigPolicyQualifiers() {
        SigPolicyQualifiersListType sigPolicyQualifiersListType;
        synchronized (monitor()) {
            check_orphaned();
            SigPolicyQualifiersListType target = (SigPolicyQualifiersListType) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            sigPolicyQualifiersListType = target == null ? null : target;
        }
        return sigPolicyQualifiersListType;
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public boolean isSetSigPolicyQualifiers() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public void setSigPolicyQualifiers(SigPolicyQualifiersListType sigPolicyQualifiers) {
        generatedSetterHelperImpl(sigPolicyQualifiers, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public SigPolicyQualifiersListType addNewSigPolicyQualifiers() {
        SigPolicyQualifiersListType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SigPolicyQualifiersListType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignaturePolicyIdType
    public void unsetSigPolicyQualifiers() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
