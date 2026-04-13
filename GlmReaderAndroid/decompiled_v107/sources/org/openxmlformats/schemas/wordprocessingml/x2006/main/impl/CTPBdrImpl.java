package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr;

/* loaded from: classes12.dex */
public class CTPBdrImpl extends XmlComplexContentImpl implements CTPBdr {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "top"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "left"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bottom"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "right"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "between"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bar")};
    private static final long serialVersionUID = 1;

    public CTPBdrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public CTBorder getTop() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public boolean isSetTop() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public void setTop(CTBorder top) {
        generatedSetterHelperImpl(top, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public CTBorder addNewTop() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public void unsetTop() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public CTBorder getLeft() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public boolean isSetLeft() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public void setLeft(CTBorder left) {
        generatedSetterHelperImpl(left, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public CTBorder addNewLeft() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public void unsetLeft() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public CTBorder getBottom() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public boolean isSetBottom() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public void setBottom(CTBorder bottom) {
        generatedSetterHelperImpl(bottom, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public CTBorder addNewBottom() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public void unsetBottom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public CTBorder getRight() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public boolean isSetRight() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public void setRight(CTBorder right) {
        generatedSetterHelperImpl(right, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public CTBorder addNewRight() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public void unsetRight() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public CTBorder getBetween() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public boolean isSetBetween() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public void setBetween(CTBorder between) {
        generatedSetterHelperImpl(between, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public CTBorder addNewBetween() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public void unsetBetween() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public CTBorder getBar() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public boolean isSetBar() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public void setBar(CTBorder bar) {
        generatedSetterHelperImpl(bar, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public CTBorder addNewBar() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
    public void unsetBar() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }
}
