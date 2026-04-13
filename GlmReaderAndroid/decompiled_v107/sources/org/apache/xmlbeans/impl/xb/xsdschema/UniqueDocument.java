package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface UniqueDocument extends XmlObject {
    public static final DocumentFactory<UniqueDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "unique3752doctype");
    public static final SchemaType type = Factory.getType();

    Keybase addNewUnique();

    Keybase getUnique();

    void setUnique(Keybase keybase);
}
