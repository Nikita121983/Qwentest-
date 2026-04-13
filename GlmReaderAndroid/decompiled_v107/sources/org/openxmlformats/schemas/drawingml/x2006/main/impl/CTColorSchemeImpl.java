package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;

/* loaded from: classes11.dex */
public class CTColorSchemeImpl extends XmlComplexContentImpl implements CTColorScheme {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "dk1"), new QName(XSSFRelation.NS_DRAWINGML, "lt1"), new QName(XSSFRelation.NS_DRAWINGML, "dk2"), new QName(XSSFRelation.NS_DRAWINGML, "lt2"), new QName(XSSFRelation.NS_DRAWINGML, "accent1"), new QName(XSSFRelation.NS_DRAWINGML, "accent2"), new QName(XSSFRelation.NS_DRAWINGML, "accent3"), new QName(XSSFRelation.NS_DRAWINGML, "accent4"), new QName(XSSFRelation.NS_DRAWINGML, "accent5"), new QName(XSSFRelation.NS_DRAWINGML, "accent6"), new QName(XSSFRelation.NS_DRAWINGML, "hlink"), new QName(XSSFRelation.NS_DRAWINGML, "folHlink"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "name")};
    private static final long serialVersionUID = 1;

    public CTColorSchemeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor getDk1() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void setDk1(CTColor dk1) {
        generatedSetterHelperImpl(dk1, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor addNewDk1() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor getLt1() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void setLt1(CTColor lt1) {
        generatedSetterHelperImpl(lt1, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor addNewLt1() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor getDk2() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void setDk2(CTColor dk2) {
        generatedSetterHelperImpl(dk2, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor addNewDk2() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor getLt2() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void setLt2(CTColor lt2) {
        generatedSetterHelperImpl(lt2, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor addNewLt2() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor getAccent1() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void setAccent1(CTColor accent1) {
        generatedSetterHelperImpl(accent1, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor addNewAccent1() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor getAccent2() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void setAccent2(CTColor accent2) {
        generatedSetterHelperImpl(accent2, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor addNewAccent2() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor getAccent3() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void setAccent3(CTColor accent3) {
        generatedSetterHelperImpl(accent3, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor addNewAccent3() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor getAccent4() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void setAccent4(CTColor accent4) {
        generatedSetterHelperImpl(accent4, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor addNewAccent4() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor getAccent5() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void setAccent5(CTColor accent5) {
        generatedSetterHelperImpl(accent5, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor addNewAccent5() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor getAccent6() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void setAccent6(CTColor accent6) {
        generatedSetterHelperImpl(accent6, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor addNewAccent6() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor getHlink() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void setHlink(CTColor hlink) {
        generatedSetterHelperImpl(hlink, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor addNewHlink() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor getFolHlink() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void setFolHlink(CTColor folHlink) {
        generatedSetterHelperImpl(folHlink, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTColor addNewFolHlink() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public XmlString xgetName() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme
    public void xsetName(XmlString name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(name);
        }
    }
}
