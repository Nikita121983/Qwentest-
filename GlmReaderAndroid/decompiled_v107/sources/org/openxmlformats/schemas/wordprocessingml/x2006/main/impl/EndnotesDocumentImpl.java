package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument;

/* loaded from: classes12.dex */
public class EndnotesDocumentImpl extends XmlComplexContentImpl implements EndnotesDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "endnotes")};
    private static final long serialVersionUID = 1;

    public EndnotesDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument
    public CTEndnotes getEndnotes() {
        CTEndnotes cTEndnotes;
        synchronized (monitor()) {
            check_orphaned();
            CTEndnotes target = (CTEndnotes) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTEndnotes = target == null ? null : target;
        }
        return cTEndnotes;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument
    public void setEndnotes(CTEndnotes endnotes) {
        generatedSetterHelperImpl(endnotes, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.EndnotesDocument
    public CTEndnotes addNewEndnotes() {
        CTEndnotes target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEndnotes) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
