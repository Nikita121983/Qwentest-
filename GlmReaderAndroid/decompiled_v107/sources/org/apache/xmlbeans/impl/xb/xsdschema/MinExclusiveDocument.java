package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface MinExclusiveDocument extends XmlObject {
    public static final DocumentFactory<MinExclusiveDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "minexclusive64d7doctype");
    public static final SchemaType type = Factory.getType();

    Facet addNewMinExclusive();

    Facet getMinExclusive();

    void setMinExclusive(Facet facet);
}
