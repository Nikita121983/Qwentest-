package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.DocumentDocument;

/* loaded from: classes12.dex */
public class DocumentDocumentImpl extends XmlComplexContentImpl implements DocumentDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "document")};
    private static final long serialVersionUID = 1;

    public DocumentDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.DocumentDocument
    public CTDocument1 getDocument() {
        CTDocument1 cTDocument1;
        synchronized (monitor()) {
            check_orphaned();
            CTDocument1 target = (CTDocument1) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTDocument1 = target == null ? null : target;
        }
        return cTDocument1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.DocumentDocument
    public void setDocument(CTDocument1 document) {
        generatedSetterHelperImpl(document, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.DocumentDocument
    public CTDocument1 addNewDocument() {
        CTDocument1 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDocument1) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
