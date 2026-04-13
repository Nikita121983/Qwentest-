package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface AllDocument extends XmlObject {
    public static final DocumentFactory<AllDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "all7214doctype");
    public static final SchemaType type = Factory.getType();

    All addNewAll();

    All getAll();

    void setAll(All all);
}
