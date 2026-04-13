package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface PresentationDocument extends XmlObject {
    public static final DocumentFactory<PresentationDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "presentation02f7doctype");
    public static final SchemaType type = Factory.getType();

    CTPresentation addNewPresentation();

    CTPresentation getPresentation();

    void setPresentation(CTPresentation cTPresentation);
}
