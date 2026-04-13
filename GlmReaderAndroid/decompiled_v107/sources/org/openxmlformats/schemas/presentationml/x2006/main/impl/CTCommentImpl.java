package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.Calendar;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTComment;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.STIndex;

/* loaded from: classes11.dex */
public class CTCommentImpl extends XmlComplexContentImpl implements CTComment {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "pos"), new QName(XSSFRelation.NS_PRESENTATIONML, "text"), new QName(XSSFRelation.NS_PRESENTATIONML, "extLst"), new QName("", "authorId"), new QName("", "dt"), new QName("", "idx")};
    private static final long serialVersionUID = 1;

    public CTCommentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public CTPoint2D getPos() {
        CTPoint2D cTPoint2D;
        synchronized (monitor()) {
            check_orphaned();
            CTPoint2D target = (CTPoint2D) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTPoint2D = target == null ? null : target;
        }
        return cTPoint2D;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public void setPos(CTPoint2D pos) {
        generatedSetterHelperImpl(pos, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public CTPoint2D addNewPos() {
        CTPoint2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPoint2D) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public String getText() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public XmlString xgetText() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public void setText(String text) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(text);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public void xsetText(XmlString text) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(text);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public CTExtensionListModify getExtLst() {
        CTExtensionListModify cTExtensionListModify;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionListModify target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTExtensionListModify = target == null ? null : target;
        }
        return cTExtensionListModify;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public void setExtLst(CTExtensionListModify extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public CTExtensionListModify addNewExtLst() {
        CTExtensionListModify target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public long getAuthorId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public XmlUnsignedInt xgetAuthorId() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public Calendar getDt() {
        Calendar calendarValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            calendarValue = target == null ? null : target.getCalendarValue();
        }
        return calendarValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public XmlDateTime xgetDt() {
        XmlDateTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDateTime) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public boolean isSetDt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public void setDt(Calendar dt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setCalendarValue(dt);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public void xsetDt(XmlDateTime dt) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDateTime target = (XmlDateTime) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlDateTime) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(dt);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public void unsetDt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public long getIdx() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public STIndex xgetIdx() {
        STIndex target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public void setIdx(long idx) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setLongValue(idx);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTComment
    public void xsetIdx(STIndex idx) {
        synchronized (monitor()) {
            check_orphaned();
            STIndex target = get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STIndex) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(idx);
        }
    }
}
