package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.Calendar;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCalendarType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLang;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDateMappingType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime;

/* loaded from: classes12.dex */
public class CTSdtDateImpl extends XmlComplexContentImpl implements CTSdtDate {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "dateFormat"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lid"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "storeMappedDataAs"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "calendar"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "fullDate")};
    private static final long serialVersionUID = 1;

    public CTSdtDateImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public CTString getDateFormat() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public boolean isSetDateFormat() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public void setDateFormat(CTString dateFormat) {
        generatedSetterHelperImpl(dateFormat, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public CTString addNewDateFormat() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public void unsetDateFormat() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public CTLang getLid() {
        CTLang cTLang;
        synchronized (monitor()) {
            check_orphaned();
            CTLang target = (CTLang) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTLang = target == null ? null : target;
        }
        return cTLang;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public boolean isSetLid() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public void setLid(CTLang lid) {
        generatedSetterHelperImpl(lid, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public CTLang addNewLid() {
        CTLang target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLang) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public void unsetLid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public CTSdtDateMappingType getStoreMappedDataAs() {
        CTSdtDateMappingType cTSdtDateMappingType;
        synchronized (monitor()) {
            check_orphaned();
            CTSdtDateMappingType target = (CTSdtDateMappingType) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTSdtDateMappingType = target == null ? null : target;
        }
        return cTSdtDateMappingType;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public boolean isSetStoreMappedDataAs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public void setStoreMappedDataAs(CTSdtDateMappingType storeMappedDataAs) {
        generatedSetterHelperImpl(storeMappedDataAs, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public CTSdtDateMappingType addNewStoreMappedDataAs() {
        CTSdtDateMappingType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtDateMappingType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public void unsetStoreMappedDataAs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public CTCalendarType getCalendar() {
        CTCalendarType cTCalendarType;
        synchronized (monitor()) {
            check_orphaned();
            CTCalendarType target = (CTCalendarType) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTCalendarType = target == null ? null : target;
        }
        return cTCalendarType;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public boolean isSetCalendar() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public void setCalendar(CTCalendarType calendar) {
        generatedSetterHelperImpl(calendar, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public CTCalendarType addNewCalendar() {
        CTCalendarType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCalendarType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public void unsetCalendar() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public Calendar getFullDate() {
        Calendar calendarValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            calendarValue = target == null ? null : target.getCalendarValue();
        }
        return calendarValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public STDateTime xgetFullDate() {
        STDateTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDateTime) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public boolean isSetFullDate() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public void setFullDate(Calendar fullDate) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setCalendarValue(fullDate);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public void xsetFullDate(STDateTime fullDate) {
        synchronized (monitor()) {
            check_orphaned();
            STDateTime target = (STDateTime) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STDateTime) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(fullDate);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
    public void unsetFullDate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
