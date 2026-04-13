package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType;

/* loaded from: classes12.dex */
public class CTPatternFillImpl extends XmlComplexContentImpl implements CTPatternFill {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "fgColor"), new QName(XSSFRelation.NS_SPREADSHEETML, "bgColor"), new QName("", "patternType")};
    private static final long serialVersionUID = 1;

    public CTPatternFillImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public CTColor getFgColor() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public boolean isSetFgColor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public void setFgColor(CTColor fgColor) {
        generatedSetterHelperImpl(fgColor, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public CTColor addNewFgColor() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public void unsetFgColor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public CTColor getBgColor() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public boolean isSetBgColor() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public void setBgColor(CTColor bgColor) {
        generatedSetterHelperImpl(bgColor, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public CTColor addNewBgColor() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public void unsetBgColor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public STPatternType.Enum getPatternType() {
        STPatternType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            r1 = target == null ? null : (STPatternType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public STPatternType xgetPatternType() {
        STPatternType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPatternType) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public boolean isSetPatternType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public void setPatternType(STPatternType.Enum patternType) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setEnumValue(patternType);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public void xsetPatternType(STPatternType patternType) {
        synchronized (monitor()) {
            check_orphaned();
            STPatternType target = (STPatternType) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STPatternType) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(patternType);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill
    public void unsetPatternType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }
}
