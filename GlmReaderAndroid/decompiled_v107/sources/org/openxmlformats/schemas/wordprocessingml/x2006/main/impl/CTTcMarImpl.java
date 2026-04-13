package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar;

/* loaded from: classes12.dex */
public class CTTcMarImpl extends XmlComplexContentImpl implements CTTcMar {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "top"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "start"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "left"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bottom"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "end"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "right")};
    private static final long serialVersionUID = 1;

    public CTTcMarImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public CTTblWidth getTop() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            CTTblWidth target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTblWidth = target == null ? null : target;
        }
        return cTTblWidth;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public boolean isSetTop() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public void setTop(CTTblWidth top) {
        generatedSetterHelperImpl(top, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public CTTblWidth addNewTop() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public void unsetTop() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public CTTblWidth getStart() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            CTTblWidth target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTblWidth = target == null ? null : target;
        }
        return cTTblWidth;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public boolean isSetStart() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public void setStart(CTTblWidth start) {
        generatedSetterHelperImpl(start, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public CTTblWidth addNewStart() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public void unsetStart() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public CTTblWidth getLeft() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            CTTblWidth target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTblWidth = target == null ? null : target;
        }
        return cTTblWidth;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public boolean isSetLeft() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public void setLeft(CTTblWidth left) {
        generatedSetterHelperImpl(left, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public CTTblWidth addNewLeft() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public void unsetLeft() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public CTTblWidth getBottom() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            CTTblWidth target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTTblWidth = target == null ? null : target;
        }
        return cTTblWidth;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public boolean isSetBottom() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public void setBottom(CTTblWidth bottom) {
        generatedSetterHelperImpl(bottom, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public CTTblWidth addNewBottom() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public void unsetBottom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public CTTblWidth getEnd() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            CTTblWidth target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTTblWidth = target == null ? null : target;
        }
        return cTTblWidth;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public boolean isSetEnd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public void setEnd(CTTblWidth end) {
        generatedSetterHelperImpl(end, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public CTTblWidth addNewEnd() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public void unsetEnd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public CTTblWidth getRight() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            CTTblWidth target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTTblWidth = target == null ? null : target;
        }
        return cTTblWidth;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public boolean isSetRight() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public void setRight(CTTblWidth right) {
        generatedSetterHelperImpl(right, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public CTTblWidth addNewRight() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar
    public void unsetRight() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }
}
