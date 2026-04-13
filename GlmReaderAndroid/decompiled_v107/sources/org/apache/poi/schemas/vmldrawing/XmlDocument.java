package org.apache.poi.schemas.vmldrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes10.dex */
public interface XmlDocument extends XmlObject {
    public static final DocumentFactory<XmlDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "xml2eb5doctype");
    public static final SchemaType type = Factory.getType();

    CTXML addNewXml();

    CTXML getXml();

    void setXml(CTXML ctxml);
}
