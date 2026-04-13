package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface EnumerationDocument extends XmlObject {
    public static final DocumentFactory<EnumerationDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "enumeration052edoctype");
    public static final SchemaType type = Factory.getType();

    NoFixedFacet addNewEnumeration();

    NoFixedFacet getEnumeration();

    void setEnumeration(NoFixedFacet noFixedFacet);
}
