package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCommentPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef;

/* loaded from: classes12.dex */
public class CTCommentImpl extends XmlComplexContentImpl implements CTComment {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "text"), new QName(XSSFRelation.NS_SPREADSHEETML, "commentPr"), new QName("", "ref"), new QName("", "authorId"), new QName("", "guid"), new QName("", "shapeId")};
    private static final long serialVersionUID = 1;

    public CTCommentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public CTRst getText() {
        CTRst cTRst;
        synchronized (monitor()) {
            check_orphaned();
            CTRst target = (CTRst) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTRst = target == null ? null : target;
        }
        return cTRst;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public void setText(CTRst text) {
        generatedSetterHelperImpl(text, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public CTRst addNewText() {
        CTRst target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRst) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public CTCommentPr getCommentPr() {
        CTCommentPr cTCommentPr;
        synchronized (monitor()) {
            check_orphaned();
            CTCommentPr target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTCommentPr = target == null ? null : target;
        }
        return cTCommentPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public boolean isSetCommentPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public void setCommentPr(CTCommentPr commentPr) {
        generatedSetterHelperImpl(commentPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public CTCommentPr addNewCommentPr() {
        CTCommentPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public void unsetCommentPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public String getRef() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public STRef xgetRef() {
        STRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRef) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public void setRef(String ref) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(ref);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public void xsetRef(STRef ref) {
        synchronized (monitor()) {
            check_orphaned();
            STRef target = (STRef) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STRef) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(ref);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public long getAuthorId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public XmlUnsignedInt xgetAuthorId() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public void setAuthorId(long authorId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setLongValue(authorId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public void xsetAuthorId(XmlUnsignedInt authorId) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(authorId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public String getGuid() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public STGuid xgetGuid() {
        STGuid target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public boolean isSetGuid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public void setGuid(String guid) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(guid);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public void xsetGuid(STGuid guid) {
        synchronized (monitor()) {
            check_orphaned();
            STGuid target = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STGuid) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(guid);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public void unsetGuid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public long getShapeId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public XmlUnsignedInt xgetShapeId() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public boolean isSetShapeId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public void setShapeId(long shapeId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setLongValue(shapeId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public void xsetShapeId(XmlUnsignedInt shapeId) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(shapeId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment
    public void unsetShapeId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }
}
