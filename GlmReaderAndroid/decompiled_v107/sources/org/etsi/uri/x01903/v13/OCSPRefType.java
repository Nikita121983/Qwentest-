package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface OCSPRefType extends XmlObject {
    public static final DocumentFactory<OCSPRefType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ocspreftype089etype");
    public static final SchemaType type = Factory.getType();

    DigestAlgAndValueType addNewDigestAlgAndValue();

    OCSPIdentifierType addNewOCSPIdentifier();

    DigestAlgAndValueType getDigestAlgAndValue();

    OCSPIdentifierType getOCSPIdentifier();

    boolean isSetDigestAlgAndValue();

    void setDigestAlgAndValue(DigestAlgAndValueType digestAlgAndValueType);

    void setOCSPIdentifier(OCSPIdentifierType oCSPIdentifierType);

    void unsetDigestAlgAndValue();
}
