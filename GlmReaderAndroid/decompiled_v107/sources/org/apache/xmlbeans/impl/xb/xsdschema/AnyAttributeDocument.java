package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface AnyAttributeDocument extends XmlObject {
    public static final DocumentFactory<AnyAttributeDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "anyattribute23b3doctype");
    public static final SchemaType type = Factory.getType();

    Wildcard addNewAnyAttribute();

    Wildcard getAnyAttribute();

    void setAnyAttribute(Wildcard wildcard);
}
