package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBottomPageBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTopPageBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageBorderDisplay;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageBorderOffset;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageBorderZOrder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageBorderZOrder$Enum;

/* loaded from: classes12.dex */
public class CTPageBordersImpl extends XmlComplexContentImpl implements CTPageBorders {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "top"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "left"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bottom"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "right"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "zOrder"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "display"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "offsetFrom")};
    private static final long serialVersionUID = 1;

    public CTPageBordersImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public CTTopPageBorder getTop() {
        CTTopPageBorder cTTopPageBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTTopPageBorder target = (CTTopPageBorder) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTopPageBorder = target == null ? null : target;
        }
        return cTTopPageBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public boolean isSetTop() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void setTop(CTTopPageBorder top) {
        generatedSetterHelperImpl(top, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public CTTopPageBorder addNewTop() {
        CTTopPageBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTopPageBorder) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void unsetTop() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public CTPageBorder getLeft() {
        CTPageBorder cTPageBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTPageBorder target = (CTPageBorder) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTPageBorder = target == null ? null : target;
        }
        return cTPageBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void setLeft(CTPageBorder left) {
        generatedSetterHelperImpl(left, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public CTPageBorder addNewLeft() {
        CTPageBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageBorder) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void unsetLeft() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public CTBottomPageBorder getBottom() {
        CTBottomPageBorder cTBottomPageBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTBottomPageBorder target = (CTBottomPageBorder) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTBottomPageBorder = target == null ? null : target;
        }
        return cTBottomPageBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public boolean isSetBottom() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void setBottom(CTBottomPageBorder bottom) {
        generatedSetterHelperImpl(bottom, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public CTBottomPageBorder addNewBottom() {
        CTBottomPageBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBottomPageBorder) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void unsetBottom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public CTPageBorder getRight() {
        CTPageBorder cTPageBorder;
        synchronized (monitor()) {
            check_orphaned();
            CTPageBorder target = (CTPageBorder) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTPageBorder = target == null ? null : target;
        }
        return cTPageBorder;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public boolean isSetRight() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void setRight(CTPageBorder right) {
        generatedSetterHelperImpl(right, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public CTPageBorder addNewRight() {
        CTPageBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPageBorder) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void unsetRight() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public STPageBorderZOrder$Enum getZOrder() {
        STPageBorderZOrder$Enum sTPageBorderZOrder$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
            sTPageBorderZOrder$Enum = target == null ? null : (STPageBorderZOrder$Enum) target.getEnumValue();
        }
        return sTPageBorderZOrder$Enum;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public STPageBorderZOrder xgetZOrder() {
        STPageBorderZOrder target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STPageBorderZOrder) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public boolean isSetZOrder() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void setZOrder(STPageBorderZOrder$Enum zOrder) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setEnumValue(zOrder);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void xsetZOrder(STPageBorderZOrder zOrder) {
        synchronized (monitor()) {
            check_orphaned();
            STPageBorderZOrder target = get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STPageBorderZOrder) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(zOrder);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void unsetZOrder() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public STPageBorderDisplay.Enum getDisplay() {
        STPageBorderDisplay.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            r1 = target == null ? null : (STPageBorderDisplay.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public STPageBorderDisplay xgetDisplay() {
        STPageBorderDisplay target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPageBorderDisplay) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public boolean isSetDisplay() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void setDisplay(STPageBorderDisplay.Enum display) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setEnumValue(display);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void xsetDisplay(STPageBorderDisplay display) {
        synchronized (monitor()) {
            check_orphaned();
            STPageBorderDisplay target = (STPageBorderDisplay) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STPageBorderDisplay) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(display);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void unsetDisplay() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public STPageBorderOffset.Enum getOffsetFrom() {
        STPageBorderOffset.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
            r1 = target == null ? null : (STPageBorderOffset.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public STPageBorderOffset xgetOffsetFrom() {
        STPageBorderOffset target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPageBorderOffset) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STPageBorderOffset) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public boolean isSetOffsetFrom() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void setOffsetFrom(STPageBorderOffset.Enum offsetFrom) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setEnumValue(offsetFrom);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void xsetOffsetFrom(STPageBorderOffset offsetFrom) {
        synchronized (monitor()) {
            check_orphaned();
            STPageBorderOffset target = (STPageBorderOffset) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STPageBorderOffset) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(offsetFrom);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders
    public void unsetOffsetFrom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }
}
