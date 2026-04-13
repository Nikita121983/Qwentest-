package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorderPr;

/* loaded from: classes12.dex */
public class CTBorderImpl extends XmlComplexContentImpl implements CTBorder {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "start"), new QName(XSSFRelation.NS_SPREADSHEETML, "end"), new QName(XSSFRelation.NS_SPREADSHEETML, "left"), new QName(XSSFRelation.NS_SPREADSHEETML, "right"), new QName(XSSFRelation.NS_SPREADSHEETML, "top"), new QName(XSSFRelation.NS_SPREADSHEETML, "bottom"), new QName(XSSFRelation.NS_SPREADSHEETML, "diagonal"), new QName(XSSFRelation.NS_SPREADSHEETML, "vertical"), new QName(XSSFRelation.NS_SPREADSHEETML, "horizontal"), new QName("", "diagonalUp"), new QName("", "diagonalDown"), new QName("", "outline")};
    private static final long serialVersionUID = 1;

    public CTBorderImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr getStart() {
        CTBorderPr cTBorderPr;
        synchronized (monitor()) {
            check_orphaned();
            CTBorderPr target = (CTBorderPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTBorderPr = target == null ? null : target;
        }
        return cTBorderPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean isSetStart() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void setStart(CTBorderPr start) {
        generatedSetterHelperImpl(start, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr addNewStart() {
        CTBorderPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorderPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void unsetStart() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr getEnd() {
        CTBorderPr cTBorderPr;
        synchronized (monitor()) {
            check_orphaned();
            CTBorderPr target = (CTBorderPr) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTBorderPr = target == null ? null : target;
        }
        return cTBorderPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean isSetEnd() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void setEnd(CTBorderPr end) {
        generatedSetterHelperImpl(end, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr addNewEnd() {
        CTBorderPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorderPr) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void unsetEnd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr getLeft() {
        CTBorderPr cTBorderPr;
        synchronized (monitor()) {
            check_orphaned();
            CTBorderPr target = (CTBorderPr) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTBorderPr = target == null ? null : target;
        }
        return cTBorderPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean isSetLeft() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void setLeft(CTBorderPr left) {
        generatedSetterHelperImpl(left, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr addNewLeft() {
        CTBorderPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorderPr) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void unsetLeft() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr getRight() {
        CTBorderPr cTBorderPr;
        synchronized (monitor()) {
            check_orphaned();
            CTBorderPr target = (CTBorderPr) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTBorderPr = target == null ? null : target;
        }
        return cTBorderPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean isSetRight() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void setRight(CTBorderPr right) {
        generatedSetterHelperImpl(right, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr addNewRight() {
        CTBorderPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorderPr) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void unsetRight() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr getTop() {
        CTBorderPr cTBorderPr;
        synchronized (monitor()) {
            check_orphaned();
            CTBorderPr target = (CTBorderPr) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTBorderPr = target == null ? null : target;
        }
        return cTBorderPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean isSetTop() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void setTop(CTBorderPr top) {
        generatedSetterHelperImpl(top, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr addNewTop() {
        CTBorderPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorderPr) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void unsetTop() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr getBottom() {
        CTBorderPr cTBorderPr;
        synchronized (monitor()) {
            check_orphaned();
            CTBorderPr target = (CTBorderPr) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTBorderPr = target == null ? null : target;
        }
        return cTBorderPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean isSetBottom() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void setBottom(CTBorderPr bottom) {
        generatedSetterHelperImpl(bottom, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr addNewBottom() {
        CTBorderPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorderPr) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void unsetBottom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr getDiagonal() {
        CTBorderPr cTBorderPr;
        synchronized (monitor()) {
            check_orphaned();
            CTBorderPr target = (CTBorderPr) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTBorderPr = target == null ? null : target;
        }
        return cTBorderPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean isSetDiagonal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void setDiagonal(CTBorderPr diagonal) {
        generatedSetterHelperImpl(diagonal, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr addNewDiagonal() {
        CTBorderPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorderPr) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void unsetDiagonal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr getVertical() {
        CTBorderPr cTBorderPr;
        synchronized (monitor()) {
            check_orphaned();
            CTBorderPr target = (CTBorderPr) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTBorderPr = target == null ? null : target;
        }
        return cTBorderPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean isSetVertical() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void setVertical(CTBorderPr vertical) {
        generatedSetterHelperImpl(vertical, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr addNewVertical() {
        CTBorderPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorderPr) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void unsetVertical() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr getHorizontal() {
        CTBorderPr cTBorderPr;
        synchronized (monitor()) {
            check_orphaned();
            CTBorderPr target = (CTBorderPr) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTBorderPr = target == null ? null : target;
        }
        return cTBorderPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean isSetHorizontal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void setHorizontal(CTBorderPr horizontal) {
        generatedSetterHelperImpl(horizontal, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public CTBorderPr addNewHorizontal() {
        CTBorderPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBorderPr) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void unsetHorizontal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean getDiagonalUp() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public XmlBoolean xgetDiagonalUp() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean isSetDiagonalUp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void setDiagonalUp(boolean diagonalUp) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setBooleanValue(diagonalUp);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void xsetDiagonalUp(XmlBoolean diagonalUp) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(diagonalUp);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void unsetDiagonalUp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean getDiagonalDown() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public XmlBoolean xgetDiagonalDown() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean isSetDiagonalDown() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void setDiagonalDown(boolean diagonalDown) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setBooleanValue(diagonalDown);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void xsetDiagonalDown(XmlBoolean diagonalDown) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(diagonalDown);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void unsetDiagonalDown() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean getOutline() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[11]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public XmlBoolean xgetOutline() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[11]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public boolean isSetOutline() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void setOutline(boolean outline) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setBooleanValue(outline);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void xsetOutline(XmlBoolean outline) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(outline);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder
    public void unsetOutline() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }
}
