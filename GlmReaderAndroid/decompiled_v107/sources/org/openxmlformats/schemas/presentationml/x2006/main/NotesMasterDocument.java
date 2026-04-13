package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface NotesMasterDocument extends XmlObject {
    public static final DocumentFactory<NotesMasterDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "notesmaster8840doctype");
    public static final SchemaType type = Factory.getType();

    CTNotesMaster addNewNotesMaster();

    CTNotesMaster getNotesMaster();

    void setNotesMaster(CTNotesMaster cTNotesMaster);
}
