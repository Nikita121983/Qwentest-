package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface EndnotesDocument extends XmlObject {
    public static final DocumentFactory<EndnotesDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "endnotes960edoctype");
    public static final SchemaType type = Factory.getType();

    CTEndnotes addNewEndnotes();

    CTEndnotes getEndnotes();

    void setEndnotes(CTEndnotes cTEndnotes);
}
