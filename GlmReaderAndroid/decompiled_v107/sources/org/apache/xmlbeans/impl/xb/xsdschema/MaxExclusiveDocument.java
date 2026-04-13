package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface MaxExclusiveDocument extends XmlObject {
    public static final DocumentFactory<MaxExclusiveDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "maxexclusive6d69doctype");
    public static final SchemaType type = Factory.getType();

    Facet addNewMaxExclusive();

    Facet getMaxExclusive();

    void setMaxExclusive(Facet facet);
}
