package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.NotesMasterDocument;

/* loaded from: classes11.dex */
public class NotesMasterDocumentImpl extends XmlComplexContentImpl implements NotesMasterDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "notesMaster")};
    private static final long serialVersionUID = 1;

    public NotesMasterDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.NotesMasterDocument
    public CTNotesMaster getNotesMaster() {
        CTNotesMaster cTNotesMaster;
        synchronized (monitor()) {
            check_orphaned();
            CTNotesMaster target = (CTNotesMaster) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNotesMaster = target == null ? null : target;
        }
        return cTNotesMaster;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.NotesMasterDocument
    public void setNotesMaster(CTNotesMaster notesMaster) {
        generatedSetterHelperImpl(notesMaster, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.NotesMasterDocument
    public CTNotesMaster addNewNotesMaster() {
        CTNotesMaster target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNotesMaster) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
