package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument;

/* loaded from: classes11.dex */
public class NotesDocumentImpl extends XmlComplexContentImpl implements NotesDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "notes")};
    private static final long serialVersionUID = 1;

    public NotesDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument
    public CTNotesSlide getNotes() {
        CTNotesSlide cTNotesSlide;
        synchronized (monitor()) {
            check_orphaned();
            CTNotesSlide target = (CTNotesSlide) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNotesSlide = target == null ? null : target;
        }
        return cTNotesSlide;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument
    public void setNotes(CTNotesSlide notes) {
        generatedSetterHelperImpl(notes, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument
    public CTNotesSlide addNewNotes() {
        CTNotesSlide target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNotesSlide) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
