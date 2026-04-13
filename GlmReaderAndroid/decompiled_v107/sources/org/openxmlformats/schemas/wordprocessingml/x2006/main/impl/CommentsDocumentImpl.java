package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComments;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument;

/* loaded from: classes12.dex */
public class CommentsDocumentImpl extends XmlComplexContentImpl implements CommentsDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "comments")};
    private static final long serialVersionUID = 1;

    public CommentsDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument
    public CTComments getComments() {
        CTComments cTComments;
        synchronized (monitor()) {
            check_orphaned();
            CTComments target = (CTComments) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTComments = target == null ? null : target;
        }
        return cTComments;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument
    public void setComments(CTComments comments) {
        generatedSetterHelperImpl(comments, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CommentsDocument
    public CTComments addNewComments() {
        CTComments target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTComments) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
