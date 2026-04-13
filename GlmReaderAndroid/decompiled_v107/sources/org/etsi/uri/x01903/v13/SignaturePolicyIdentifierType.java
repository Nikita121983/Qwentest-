package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface SignaturePolicyIdentifierType extends XmlObject {
    public static final DocumentFactory<SignaturePolicyIdentifierType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "signaturepolicyidentifiertype80aftype");
    public static final SchemaType type = Factory.getType();

    SignaturePolicyIdType addNewSignaturePolicyId();

    XmlObject addNewSignaturePolicyImplied();

    SignaturePolicyIdType getSignaturePolicyId();

    XmlObject getSignaturePolicyImplied();

    boolean isSetSignaturePolicyId();

    boolean isSetSignaturePolicyImplied();

    void setSignaturePolicyId(SignaturePolicyIdType signaturePolicyIdType);

    void setSignaturePolicyImplied(XmlObject xmlObject);

    void unsetSignaturePolicyId();

    void unsetSignaturePolicyImplied();
}
