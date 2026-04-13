package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.PageContentsDocument;
import com.microsoft.schemas.office.visio.x2012.main.PageContentsType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class PageContentsDocumentImpl extends XmlComplexContentImpl implements PageContentsDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "PageContents")};
    private static final long serialVersionUID = 1;

    public PageContentsDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageContentsDocument
    public PageContentsType getPageContents() {
        PageContentsType pageContentsType;
        synchronized (monitor()) {
            check_orphaned();
            PageContentsType target = (PageContentsType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            pageContentsType = target == null ? null : target;
        }
        return pageContentsType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageContentsDocument
    public void setPageContents(PageContentsType pageContents) {
        generatedSetterHelperImpl(pageContents, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.PageContentsDocument
    public PageContentsType addNewPageContents() {
        PageContentsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (PageContentsType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
