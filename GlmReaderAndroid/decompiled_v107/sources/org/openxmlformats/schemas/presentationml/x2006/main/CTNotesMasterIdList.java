package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTNotesMasterIdList extends XmlObject {
    public static final DocumentFactory<CTNotesMasterIdList> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnotesmasteridlist2853type");
    public static final SchemaType type = Factory.getType();

    CTNotesMasterIdListEntry addNewNotesMasterId();

    CTNotesMasterIdListEntry getNotesMasterId();

    boolean isSetNotesMasterId();

    void setNotesMasterId(CTNotesMasterIdListEntry cTNotesMasterIdListEntry);

    void unsetNotesMasterId();
}
