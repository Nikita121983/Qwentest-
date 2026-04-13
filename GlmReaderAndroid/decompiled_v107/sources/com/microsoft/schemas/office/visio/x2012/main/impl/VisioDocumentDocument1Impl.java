package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentDocument1;
import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class VisioDocumentDocument1Impl extends XmlComplexContentImpl implements VisioDocumentDocument1 {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "VisioDocument")};
    private static final long serialVersionUID = 1;

    public VisioDocumentDocument1Impl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentDocument1
    public VisioDocumentType getVisioDocument() {
        VisioDocumentType visioDocumentType;
        synchronized (monitor()) {
            check_orphaned();
            VisioDocumentType target = (VisioDocumentType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            visioDocumentType = target == null ? null : target;
        }
        return visioDocumentType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentDocument1
    public void setVisioDocument(VisioDocumentType visioDocument) {
        generatedSetterHelperImpl(visioDocument, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.VisioDocumentDocument1
    public VisioDocumentType addNewVisioDocument() {
        VisioDocumentType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (VisioDocumentType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
