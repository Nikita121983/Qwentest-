package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CertifiedRolesListType;
import org.etsi.uri.x01903.v13.ClaimedRolesListType;
import org.etsi.uri.x01903.v13.SignerRoleType;

/* loaded from: classes11.dex */
public class SignerRoleTypeImpl extends XmlComplexContentImpl implements SignerRoleType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "ClaimedRoles"), new QName(SignatureFacet.XADES_132_NS, "CertifiedRoles")};
    private static final long serialVersionUID = 1;

    public SignerRoleTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.SignerRoleType
    public ClaimedRolesListType getClaimedRoles() {
        ClaimedRolesListType claimedRolesListType;
        synchronized (monitor()) {
            check_orphaned();
            ClaimedRolesListType target = (ClaimedRolesListType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            claimedRolesListType = target == null ? null : target;
        }
        return claimedRolesListType;
    }

    @Override // org.etsi.uri.x01903.v13.SignerRoleType
    public boolean isSetClaimedRoles() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.SignerRoleType
    public void setClaimedRoles(ClaimedRolesListType claimedRoles) {
        generatedSetterHelperImpl(claimedRoles, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.SignerRoleType
    public ClaimedRolesListType addNewClaimedRoles() {
        ClaimedRolesListType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ClaimedRolesListType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignerRoleType
    public void unsetClaimedRoles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.SignerRoleType
    public CertifiedRolesListType getCertifiedRoles() {
        CertifiedRolesListType certifiedRolesListType;
        synchronized (monitor()) {
            check_orphaned();
            CertifiedRolesListType target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            certifiedRolesListType = target == null ? null : target;
        }
        return certifiedRolesListType;
    }

    @Override // org.etsi.uri.x01903.v13.SignerRoleType
    public boolean isSetCertifiedRoles() {
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

    @Override // org.etsi.uri.x01903.v13.SignerRoleType
    public void setCertifiedRoles(CertifiedRolesListType certifiedRoles) {
        generatedSetterHelperImpl(certifiedRoles, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.SignerRoleType
    public CertifiedRolesListType addNewCertifiedRoles() {
        CertifiedRolesListType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.SignerRoleType
    public void unsetCertifiedRoles() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
