package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface ExternalLinkDocument extends XmlObject {
    public static final DocumentFactory<ExternalLinkDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "externallinkb4c2doctype");
    public static final SchemaType type = Factory.getType();

    CTExternalLink addNewExternalLink();

    CTExternalLink getExternalLink();

    void setExternalLink(CTExternalLink cTExternalLink);
}
