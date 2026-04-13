package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.Calendar;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlErrorCodes;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime;

/* loaded from: classes12.dex */
public class CTMoveBookmarkImpl extends CTBookmarkImpl implements CTMoveBookmark {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "author"), new QName(XSSFRelation.NS_WORDPROCESSINGML, XmlErrorCodes.DATE)};
    private static final long serialVersionUID = 1;

    public CTMoveBookmarkImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark
    public String getAuthor() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark
    public STString xgetAuthor() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark
    public void setAuthor(String author) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(author);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark
    public void xsetAuthor(STString author) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(author);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark
    public Calendar getDate() {
        Calendar calendarValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            calendarValue = target == null ? null : target.getCalendarValue();
        }
        return calendarValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark
    public STDateTime xgetDate() {
        STDateTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDateTime) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark
    public void setDate(Calendar date) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setCalendarValue(date);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark
    public void xsetDate(STDateTime date) {
        synchronized (monitor()) {
            check_orphaned();
            STDateTime target = (STDateTime) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STDateTime) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(date);
        }
    }
}
