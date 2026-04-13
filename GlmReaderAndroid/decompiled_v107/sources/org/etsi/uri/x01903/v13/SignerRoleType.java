package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface SignerRoleType extends XmlObject {
    public static final DocumentFactory<SignerRoleType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "signerroletypef58etype");
    public static final SchemaType type = Factory.getType();

    CertifiedRolesListType addNewCertifiedRoles();

    ClaimedRolesListType addNewClaimedRoles();

    CertifiedRolesListType getCertifiedRoles();

    ClaimedRolesListType getClaimedRoles();

    boolean isSetCertifiedRoles();

    boolean isSetClaimedRoles();

    void setCertifiedRoles(CertifiedRolesListType certifiedRolesListType);

    void setClaimedRoles(ClaimedRolesListType claimedRolesListType);

    void unsetCertifiedRoles();

    void unsetClaimedRoles();
}
