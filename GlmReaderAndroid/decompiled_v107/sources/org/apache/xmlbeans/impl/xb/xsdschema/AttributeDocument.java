package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface AttributeDocument extends XmlObject {
    public static final DocumentFactory<AttributeDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "attributeedb9doctype");
    public static final SchemaType type = Factory.getType();

    TopLevelAttribute addNewAttribute();

    TopLevelAttribute getAttribute();

    void setAttribute(TopLevelAttribute topLevelAttribute);
}
