package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTDocumentBase extends XmlObject {
    public static final DocumentFactory<CTDocumentBase> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdocumentbasedf5ctype");
    public static final SchemaType type = Factory.getType();

    CTBackground addNewBackground();

    CTBackground getBackground();

    boolean isSetBackground();

    void setBackground(CTBackground cTBackground);

    void unsetBackground();
}
