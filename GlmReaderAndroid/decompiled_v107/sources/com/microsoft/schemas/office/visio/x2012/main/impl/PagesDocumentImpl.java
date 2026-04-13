package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.PagesDocument;
import com.microsoft.schemas.office.visio.x2012.main.PagesType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class PagesDocumentImpl extends XmlComplexContentImpl implements PagesDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "Pages")};
    private static final long serialVersionUID = 1;

    public PagesDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PagesDocument
    public PagesType getPages() {
        PagesType pagesType;
        synchronized (monitor()) {
            check_orphaned();
            PagesType target = (PagesType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            pagesType = target == null ? null : target;
        }
        return pagesType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PagesDocument
    public void setPages(PagesType pages) {
        generatedSetterHelperImpl(pages, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PagesDocument
    public PagesType addNewPages() {
        PagesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (PagesType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
