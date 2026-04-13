package org.w3.x2000.x09.xmldsig;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CanonicalizationMethodType extends XmlObject {
    public static final DocumentFactory<CanonicalizationMethodType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "canonicalizationmethodtypeec74type");
    public static final SchemaType type = Factory.getType();

    String getAlgorithm();

    void setAlgorithm(String str);

    XmlAnyURI xgetAlgorithm();

    void xsetAlgorithm(XmlAnyURI xmlAnyURI);
}
