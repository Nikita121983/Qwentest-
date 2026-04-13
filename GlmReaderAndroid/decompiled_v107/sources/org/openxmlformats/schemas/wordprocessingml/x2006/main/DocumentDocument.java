package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface DocumentDocument extends XmlObject {
    public static final DocumentFactory<DocumentDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "document2bd9doctype");
    public static final SchemaType type = Factory.getType();

    CTDocument1 addNewDocument();

    CTDocument1 getDocument();

    void setDocument(CTDocument1 cTDocument1);
}
