package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList;
import org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument;

/* loaded from: classes11.dex */
public class CmLstDocumentImpl extends XmlComplexContentImpl implements CmLstDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "cmLst")};
    private static final long serialVersionUID = 1;

    public CmLstDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument
    public CTCommentList getCmLst() {
        CTCommentList cTCommentList;
        synchronized (monitor()) {
            check_orphaned();
            CTCommentList target = (CTCommentList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTCommentList = target == null ? null : target;
        }
        return cTCommentList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument
    public void setCmLst(CTCommentList cmLst) {
        generatedSetterHelperImpl(cmLst, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CmLstDocument
    public CTCommentList addNewCmLst() {
        CTCommentList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCommentList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
