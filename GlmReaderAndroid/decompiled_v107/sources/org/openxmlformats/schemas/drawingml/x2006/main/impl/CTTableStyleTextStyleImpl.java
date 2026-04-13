package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.ss.formula.functions.Complex;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontCollection;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.STOnOffStyleType;

/* loaded from: classes11.dex */
public class CTTableStyleTextStyleImpl extends XmlComplexContentImpl implements CTTableStyleTextStyle {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, CellUtil.FONT), new QName(XSSFRelation.NS_DRAWINGML, "fontRef"), new QName(XSSFRelation.NS_DRAWINGML, "scrgbClr"), new QName(XSSFRelation.NS_DRAWINGML, "srgbClr"), new QName(XSSFRelation.NS_DRAWINGML, "hslClr"), new QName(XSSFRelation.NS_DRAWINGML, "sysClr"), new QName(XSSFRelation.NS_DRAWINGML, "schemeClr"), new QName(XSSFRelation.NS_DRAWINGML, "prstClr"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "b"), new QName("", Complex.DEFAULT_SUFFIX)};
    private static final long serialVersionUID = 1;

    public CTTableStyleTextStyleImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTFontCollection getFont() {
        CTFontCollection cTFontCollection;
        synchronized (monitor()) {
            check_orphaned();
            CTFontCollection target = (CTFontCollection) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTFontCollection = target == null ? null : target;
        }
        return cTFontCollection;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public boolean isSetFont() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void setFont(CTFontCollection font) {
        generatedSetterHelperImpl(font, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTFontCollection addNewFont() {
        CTFontCollection target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontCollection) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void unsetFont() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTFontReference getFontRef() {
        CTFontReference cTFontReference;
        synchronized (monitor()) {
            check_orphaned();
            CTFontReference target = (CTFontReference) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTFontReference = target == null ? null : target;
        }
        return cTFontReference;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public boolean isSetFontRef() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void setFontRef(CTFontReference fontRef) {
        generatedSetterHelperImpl(fontRef, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTFontReference addNewFontRef() {
        CTFontReference target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFontReference) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void unsetFontRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTScRgbColor getScrgbClr() {
        CTScRgbColor cTScRgbColor;
        synchronized (monitor()) {
            check_orphaned();
            CTScRgbColor target = (CTScRgbColor) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTScRgbColor = target == null ? null : target;
        }
        return cTScRgbColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public boolean isSetScrgbClr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void setScrgbClr(CTScRgbColor scrgbClr) {
        generatedSetterHelperImpl(scrgbClr, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTScRgbColor addNewScrgbClr() {
        CTScRgbColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTScRgbColor) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void unsetScrgbClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTSRgbColor getSrgbClr() {
        CTSRgbColor cTSRgbColor;
        synchronized (monitor()) {
            check_orphaned();
            CTSRgbColor target = (CTSRgbColor) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTSRgbColor = target == null ? null : target;
        }
        return cTSRgbColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public boolean isSetSrgbClr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void setSrgbClr(CTSRgbColor srgbClr) {
        generatedSetterHelperImpl(srgbClr, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTSRgbColor addNewSrgbClr() {
        CTSRgbColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSRgbColor) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void unsetSrgbClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTHslColor getHslClr() {
        CTHslColor cTHslColor;
        synchronized (monitor()) {
            check_orphaned();
            CTHslColor target = (CTHslColor) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTHslColor = target == null ? null : target;
        }
        return cTHslColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public boolean isSetHslClr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void setHslClr(CTHslColor hslClr) {
        generatedSetterHelperImpl(hslClr, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTHslColor addNewHslClr() {
        CTHslColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHslColor) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void unsetHslClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTSystemColor getSysClr() {
        CTSystemColor cTSystemColor;
        synchronized (monitor()) {
            check_orphaned();
            CTSystemColor target = (CTSystemColor) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTSystemColor = target == null ? null : target;
        }
        return cTSystemColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public boolean isSetSysClr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void setSysClr(CTSystemColor sysClr) {
        generatedSetterHelperImpl(sysClr, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTSystemColor addNewSysClr() {
        CTSystemColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSystemColor) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void unsetSysClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTSchemeColor getSchemeClr() {
        CTSchemeColor cTSchemeColor;
        synchronized (monitor()) {
            check_orphaned();
            CTSchemeColor target = (CTSchemeColor) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTSchemeColor = target == null ? null : target;
        }
        return cTSchemeColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public boolean isSetSchemeClr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void setSchemeClr(CTSchemeColor schemeClr) {
        generatedSetterHelperImpl(schemeClr, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTSchemeColor addNewSchemeClr() {
        CTSchemeColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSchemeColor) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void unsetSchemeClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTPresetColor getPrstClr() {
        CTPresetColor cTPresetColor;
        synchronized (monitor()) {
            check_orphaned();
            CTPresetColor target = (CTPresetColor) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTPresetColor = target == null ? null : target;
        }
        return cTPresetColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public boolean isSetPrstClr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void setPrstClr(CTPresetColor prstClr) {
        generatedSetterHelperImpl(prstClr, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTPresetColor addNewPrstClr() {
        CTPresetColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPresetColor) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void unsetPrstClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public STOnOffStyleType.Enum getB() {
        STOnOffStyleType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
            r1 = target == null ? null : (STOnOffStyleType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public STOnOffStyleType xgetB() {
        STOnOffStyleType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOffStyleType) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (STOnOffStyleType) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public boolean isSetB() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void setB(STOnOffStyleType.Enum b) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setEnumValue(b);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void xsetB(STOnOffStyleType b) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOffStyleType target = (STOnOffStyleType) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (STOnOffStyleType) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(b);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void unsetB() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public STOnOffStyleType.Enum getI() {
        STOnOffStyleType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[10]);
            }
            r1 = target == null ? null : (STOnOffStyleType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public STOnOffStyleType xgetI() {
        STOnOffStyleType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOffStyleType) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (STOnOffStyleType) get_default_attribute_value(PROPERTY_QNAME[10]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public boolean isSetI() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void setI(STOnOffStyleType.Enum iValue) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setEnumValue(iValue);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void xsetI(STOnOffStyleType iValue) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOffStyleType target = (STOnOffStyleType) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (STOnOffStyleType) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(iValue);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle
    public void unsetI() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }
}
