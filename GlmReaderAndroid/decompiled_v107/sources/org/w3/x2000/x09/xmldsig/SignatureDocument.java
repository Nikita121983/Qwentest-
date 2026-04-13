package org.w3.x2000.x09.xmldsig;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface SignatureDocument extends XmlObject {
    public static final DocumentFactory<SignatureDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "signature5269doctype");
    public static final SchemaType type = Factory.getType();

    SignatureType addNewSignature();

    SignatureType getSignature();

    void setSignature(SignatureType signatureType);
}
