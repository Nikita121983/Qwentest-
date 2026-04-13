package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorSchemeList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomColorList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTObjectStyleDefaults;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet;

/* loaded from: classes11.dex */
public class CTOfficeStyleSheetImpl extends XmlComplexContentImpl implements CTOfficeStyleSheet {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "themeElements"), new QName(XSSFRelation.NS_DRAWINGML, "objectDefaults"), new QName(XSSFRelation.NS_DRAWINGML, "extraClrSchemeLst"), new QName(XSSFRelation.NS_DRAWINGML, "custClrLst"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "name")};
    private static final long serialVersionUID = 1;

    public CTOfficeStyleSheetImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public CTBaseStyles getThemeElements() {
        CTBaseStyles cTBaseStyles;
        synchronized (monitor()) {
            check_orphaned();
            CTBaseStyles target = (CTBaseStyles) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTBaseStyles = target == null ? null : target;
        }
        return cTBaseStyles;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public void setThemeElements(CTBaseStyles themeElements) {
        generatedSetterHelperImpl(themeElements, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public CTBaseStyles addNewThemeElements() {
        CTBaseStyles target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBaseStyles) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public CTObjectStyleDefaults getObjectDefaults() {
        CTObjectStyleDefaults cTObjectStyleDefaults;
        synchronized (monitor()) {
            check_orphaned();
            CTObjectStyleDefaults target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTObjectStyleDefaults = target == null ? null : target;
        }
        return cTObjectStyleDefaults;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public boolean isSetObjectDefaults() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public void setObjectDefaults(CTObjectStyleDefaults objectDefaults) {
        generatedSetterHelperImpl(objectDefaults, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public CTObjectStyleDefaults addNewObjectDefaults() {
        CTObjectStyleDefaults target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public void unsetObjectDefaults() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public CTColorSchemeList getExtraClrSchemeLst() {
        CTColorSchemeList cTColorSchemeList;
        synchronized (monitor()) {
            check_orphaned();
            CTColorSchemeList target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTColorSchemeList = target == null ? null : target;
        }
        return cTColorSchemeList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public boolean isSetExtraClrSchemeLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public void setExtraClrSchemeLst(CTColorSchemeList extraClrSchemeLst) {
        generatedSetterHelperImpl(extraClrSchemeLst, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public CTColorSchemeList addNewExtraClrSchemeLst() {
        CTColorSchemeList target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public void unsetExtraClrSchemeLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public CTCustomColorList getCustClrLst() {
        CTCustomColorList cTCustomColorList;
        synchronized (monitor()) {
            check_orphaned();
            CTCustomColorList target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTCustomColorList = target == null ? null : target;
        }
        return cTCustomColorList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public boolean isSetCustClrLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public void setCustClrLst(CTCustomColorList custClrLst) {
        generatedSetterHelperImpl(custClrLst, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public CTCustomColorList addNewCustClrLst() {
        CTCustomColorList target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public void unsetCustClrLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public XmlString xgetName() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlString) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public void xsetName(XmlString name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(name);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }
}
