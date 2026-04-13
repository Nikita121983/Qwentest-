package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface MinLengthDocument extends XmlObject {
    public static final DocumentFactory<MinLengthDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "minlengthe7fddoctype");
    public static final SchemaType type = Factory.getType();

    NumFacet addNewMinLength();

    NumFacet getMinLength();

    void setMinLength(NumFacet numFacet);
}
