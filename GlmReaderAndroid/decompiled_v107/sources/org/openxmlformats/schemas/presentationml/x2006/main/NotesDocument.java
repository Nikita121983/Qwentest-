package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface NotesDocument extends XmlObject {
    public static final DocumentFactory<NotesDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "notes4a02doctype");
    public static final SchemaType type = Factory.getType();

    CTNotesSlide addNewNotes();

    CTNotesSlide getNotes();

    void setNotes(CTNotesSlide cTNotesSlide);
}
