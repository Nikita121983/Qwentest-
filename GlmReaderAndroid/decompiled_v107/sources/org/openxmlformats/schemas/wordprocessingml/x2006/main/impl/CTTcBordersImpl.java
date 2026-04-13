package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;

/* loaded from: classes12.dex */
public class CTTcBordersImpl extends XmlComplexContentImpl implements CTTcBorders {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "top"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "start"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "left"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bottom"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "end"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "right"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "insideH"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "insideV"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tl2br"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tr2bl")};
    private static final long serialVersionUID = 1;

    public CTTcBordersImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder getTop() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public boolean isSetTop() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void setTop(CTBorder top) {
        generatedSetterHelperImpl(top, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder addNewTop() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void unsetTop() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder getStart() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void setStart(CTBorder start) {
        generatedSetterHelperImpl(start, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder addNewStart() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void unsetStart() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder getLeft() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public boolean isSetLeft() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void setLeft(CTBorder left) {
        generatedSetterHelperImpl(left, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder addNewLeft() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void unsetLeft() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder getBottom() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public boolean isSetBottom() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void setBottom(CTBorder bottom) {
        generatedSetterHelperImpl(bottom, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder addNewBottom() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void unsetBottom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder getEnd() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public boolean isSetEnd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void setEnd(CTBorder end) {
        generatedSetterHelperImpl(end, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder addNewEnd() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void unsetEnd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder getRight() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public boolean isSetRight() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void setRight(CTBorder right) {
        generatedSetterHelperImpl(right, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder addNewRight() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void unsetRight() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder getInsideH() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public boolean isSetInsideH() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void setInsideH(CTBorder insideH) {
        generatedSetterHelperImpl(insideH, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder addNewInsideH() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void unsetInsideH() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder getInsideV() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public boolean isSetInsideV() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void setInsideV(CTBorder insideV) {
        generatedSetterHelperImpl(insideV, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder addNewInsideV() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void unsetInsideV() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder getTl2Br() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public boolean isSetTl2Br() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void setTl2Br(CTBorder tl2Br) {
        generatedSetterHelperImpl(tl2Br, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder addNewTl2Br() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void unsetTl2Br() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder getTr2Bl() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBorder target = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTBorder = target == null ? null : target;
        }
        return cTBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public boolean isSetTr2Bl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void setTr2Bl(CTBorder tr2Bl) {
        generatedSetterHelperImpl(tr2Bl, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public CTBorder addNewTr2Bl() {
        CTBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders
    public void unsetTr2Bl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }
}
