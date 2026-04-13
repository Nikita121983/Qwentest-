package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList;
import org.openxmlformats.schemas.presentationml.x2006.main.CmAuthorLstDocument;

/* loaded from: classes11.dex */
public class CmAuthorLstDocumentImpl extends XmlComplexContentImpl implements CmAuthorLstDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "cmAuthorLst")};
    private static final long serialVersionUID = 1;

    public CmAuthorLstDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CmAuthorLstDocument
    public CTCommentAuthorList getCmAuthorLst() {
        CTCommentAuthorList cTCommentAuthorList;
        synchronized (monitor()) {
            check_orphaned();
            CTCommentAuthorList target = (CTCommentAuthorList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTCommentAuthorList = target == null ? null : target;
        }
        return cTCommentAuthorList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CmAuthorLstDocument
    public void setCmAuthorLst(CTCommentAuthorList cmAuthorLst) {
        generatedSetterHelperImpl(cmAuthorLst, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CmAuthorLstDocument
    public CTCommentAuthorList addNewCmAuthorLst() {
        CTCommentAuthorList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCommentAuthorList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
