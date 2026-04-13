package org.etsi.uri.x01903.v13.impl;

import java.util.Calendar;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CertIDListType;
import org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType;
import org.etsi.uri.x01903.v13.SignatureProductionPlaceType;
import org.etsi.uri.x01903.v13.SignedSignaturePropertiesType;
import org.etsi.uri.x01903.v13.SignerRoleType;

/* loaded from: classes11.dex */
public class SignedSignaturePropertiesTypeImpl extends XmlComplexContentImpl implements SignedSignaturePropertiesType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "SigningTime"), new QName(SignatureFacet.XADES_132_NS, "SigningCertificate"), new QName(SignatureFacet.XADES_132_NS, "SignaturePolicyIdentifier"), new QName(SignatureFacet.XADES_132_NS, "SignatureProductionPlace"), new QName(SignatureFacet.XADES_132_NS, "SignerRole"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public SignedSignaturePropertiesTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public Calendar getSigningTime() {
        Calendar calendarValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            calendarValue = target == null ? null : target.getCalendarValue();
        }
        return calendarValue;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public XmlDateTime xgetSigningTime() {
        XmlDateTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDateTime) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public boolean isSetSigningTime() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public void setSigningTime(Calendar signingTime) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setCalendarValue(signingTime);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public void xsetSigningTime(XmlDateTime signingTime) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDateTime target = (XmlDateTime) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (XmlDateTime) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(signingTime);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public void unsetSigningTime() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public CertIDListType getSigningCertificate() {
        CertIDListType certIDListType;
        synchronized (monitor()) {
            check_orphaned();
            CertIDListType target = (CertIDListType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            certIDListType = target == null ? null : target;
        }
        return certIDListType;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public boolean isSetSigningCertificate() {
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

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public void setSigningCertificate(CertIDListType signingCertificate) {
        generatedSetterHelperImpl(signingCertificate, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public CertIDListType addNewSigningCertificate() {
        CertIDListType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CertIDListType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public void unsetSigningCertificate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public SignaturePolicyIdentifierType getSignaturePolicyIdentifier() {
        SignaturePolicyIdentifierType signaturePolicyIdentifierType;
        synchronized (monitor()) {
            check_orphaned();
            SignaturePolicyIdentifierType target = (SignaturePolicyIdentifierType) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            signaturePolicyIdentifierType = target == null ? null : target;
        }
        return signaturePolicyIdentifierType;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public boolean isSetSignaturePolicyIdentifier() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public void setSignaturePolicyIdentifier(SignaturePolicyIdentifierType signaturePolicyIdentifier) {
        generatedSetterHelperImpl(signaturePolicyIdentifier, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public SignaturePolicyIdentifierType addNewSignaturePolicyIdentifier() {
        SignaturePolicyIdentifierType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SignaturePolicyIdentifierType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public void unsetSignaturePolicyIdentifier() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public SignatureProductionPlaceType getSignatureProductionPlace() {
        SignatureProductionPlaceType signatureProductionPlaceType;
        synchronized (monitor()) {
            check_orphaned();
            SignatureProductionPlaceType target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            signatureProductionPlaceType = target == null ? null : target;
        }
        return signatureProductionPlaceType;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public boolean isSetSignatureProductionPlace() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public void setSignatureProductionPlace(SignatureProductionPlaceType signatureProductionPlace) {
        generatedSetterHelperImpl(signatureProductionPlace, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public SignatureProductionPlaceType addNewSignatureProductionPlace() {
        SignatureProductionPlaceType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public void unsetSignatureProductionPlace() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public SignerRoleType getSignerRole() {
        SignerRoleType signerRoleType;
        synchronized (monitor()) {
            check_orphaned();
            SignerRoleType target = (SignerRoleType) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            signerRoleType = target == null ? null : target;
        }
        return signerRoleType;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public boolean isSetSignerRole() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public void setSignerRole(SignerRoleType signerRole) {
        generatedSetterHelperImpl(signerRole, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public SignerRoleType addNewSignerRole() {
        SignerRoleType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SignerRoleType) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public void unsetSignerRole() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public void xsetId(XmlID id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignedSignaturePropertiesType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }
}
