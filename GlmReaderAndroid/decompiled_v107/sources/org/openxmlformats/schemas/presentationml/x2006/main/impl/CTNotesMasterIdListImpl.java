package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdListEntry;

/* loaded from: classes11.dex */
public class CTNotesMasterIdListImpl extends XmlComplexContentImpl implements CTNotesMasterIdList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "notesMasterId")};
    private static final long serialVersionUID = 1;

    public CTNotesMasterIdListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdList
    public CTNotesMasterIdListEntry getNotesMasterId() {
        CTNotesMasterIdListEntry cTNotesMasterIdListEntry;
        synchronized (monitor()) {
            check_orphaned();
            CTNotesMasterIdListEntry target = (CTNotesMasterIdListEntry) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNotesMasterIdListEntry = target == null ? null : target;
        }
        return cTNotesMasterIdListEntry;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdList
    public boolean isSetNotesMasterId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdList
    public void setNotesMasterId(CTNotesMasterIdListEntry notesMasterId) {
        generatedSetterHelperImpl(notesMasterId, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdList
    public CTNotesMasterIdListEntry addNewNotesMasterId() {
        CTNotesMasterIdListEntry target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNotesMasterIdListEntry) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdList
    public void unsetNotesMasterId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }
}
