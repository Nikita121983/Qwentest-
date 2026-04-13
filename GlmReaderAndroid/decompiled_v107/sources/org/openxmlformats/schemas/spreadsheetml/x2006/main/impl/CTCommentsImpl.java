package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCommentList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;

/* loaded from: classes12.dex */
public class CTCommentsImpl extends XmlComplexContentImpl implements CTComments {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "authors"), new QName(XSSFRelation.NS_SPREADSHEETML, "commentList"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTCommentsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments
    public CTAuthors getAuthors() {
        CTAuthors cTAuthors;
        synchronized (monitor()) {
            check_orphaned();
            CTAuthors target = (CTAuthors) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTAuthors = target == null ? null : target;
        }
        return cTAuthors;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments
    public void setAuthors(CTAuthors authors) {
        generatedSetterHelperImpl(authors, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments
    public CTAuthors addNewAuthors() {
        CTAuthors target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAuthors) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments
    public CTCommentList getCommentList() {
        CTCommentList cTCommentList;
        synchronized (monitor()) {
            check_orphaned();
            CTCommentList target = (CTCommentList) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTCommentList = target == null ? null : target;
        }
        return cTCommentList;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments
    public void setCommentList(CTCommentList commentList) {
        generatedSetterHelperImpl(commentList, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments
    public CTCommentList addNewCommentList() {
        CTCommentList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCommentList) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }
}
