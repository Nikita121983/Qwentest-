package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

/* loaded from: classes11.dex */
public class CTTextListStyleImpl extends XmlComplexContentImpl implements CTTextListStyle {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "defPPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl1pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl2pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl3pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl4pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl5pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl6pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl7pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl8pPr"), new QName(XSSFRelation.NS_DRAWINGML, "lvl9pPr"), new QName(XSSFRelation.NS_DRAWINGML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTTextListStyleImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties getDefPPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextParagraphProperties target = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTextParagraphProperties = target == null ? null : target;
        }
        return cTTextParagraphProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public boolean isSetDefPPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void setDefPPr(CTTextParagraphProperties defPPr) {
        generatedSetterHelperImpl(defPPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties addNewDefPPr() {
        CTTextParagraphProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void unsetDefPPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties getLvl1PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextParagraphProperties target = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTextParagraphProperties = target == null ? null : target;
        }
        return cTTextParagraphProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public boolean isSetLvl1PPr() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void setLvl1PPr(CTTextParagraphProperties lvl1PPr) {
        generatedSetterHelperImpl(lvl1PPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties addNewLvl1PPr() {
        CTTextParagraphProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void unsetLvl1PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties getLvl2PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextParagraphProperties target = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTextParagraphProperties = target == null ? null : target;
        }
        return cTTextParagraphProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public boolean isSetLvl2PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void setLvl2PPr(CTTextParagraphProperties lvl2PPr) {
        generatedSetterHelperImpl(lvl2PPr, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties addNewLvl2PPr() {
        CTTextParagraphProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void unsetLvl2PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties getLvl3PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextParagraphProperties target = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTTextParagraphProperties = target == null ? null : target;
        }
        return cTTextParagraphProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public boolean isSetLvl3PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void setLvl3PPr(CTTextParagraphProperties lvl3PPr) {
        generatedSetterHelperImpl(lvl3PPr, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties addNewLvl3PPr() {
        CTTextParagraphProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void unsetLvl3PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties getLvl4PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextParagraphProperties target = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTTextParagraphProperties = target == null ? null : target;
        }
        return cTTextParagraphProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public boolean isSetLvl4PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void setLvl4PPr(CTTextParagraphProperties lvl4PPr) {
        generatedSetterHelperImpl(lvl4PPr, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties addNewLvl4PPr() {
        CTTextParagraphProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void unsetLvl4PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties getLvl5PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextParagraphProperties target = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTTextParagraphProperties = target == null ? null : target;
        }
        return cTTextParagraphProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public boolean isSetLvl5PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void setLvl5PPr(CTTextParagraphProperties lvl5PPr) {
        generatedSetterHelperImpl(lvl5PPr, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties addNewLvl5PPr() {
        CTTextParagraphProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void unsetLvl5PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties getLvl6PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextParagraphProperties target = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTTextParagraphProperties = target == null ? null : target;
        }
        return cTTextParagraphProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public boolean isSetLvl6PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void setLvl6PPr(CTTextParagraphProperties lvl6PPr) {
        generatedSetterHelperImpl(lvl6PPr, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties addNewLvl6PPr() {
        CTTextParagraphProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void unsetLvl6PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties getLvl7PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextParagraphProperties target = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTTextParagraphProperties = target == null ? null : target;
        }
        return cTTextParagraphProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public boolean isSetLvl7PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void setLvl7PPr(CTTextParagraphProperties lvl7PPr) {
        generatedSetterHelperImpl(lvl7PPr, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties addNewLvl7PPr() {
        CTTextParagraphProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void unsetLvl7PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties getLvl8PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextParagraphProperties target = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTTextParagraphProperties = target == null ? null : target;
        }
        return cTTextParagraphProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public boolean isSetLvl8PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void setLvl8PPr(CTTextParagraphProperties lvl8PPr) {
        generatedSetterHelperImpl(lvl8PPr, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties addNewLvl8PPr() {
        CTTextParagraphProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void unsetLvl8PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties getLvl9PPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextParagraphProperties target = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTTextParagraphProperties = target == null ? null : target;
        }
        return cTTextParagraphProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public boolean isSetLvl9PPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void setLvl9PPr(CTTextParagraphProperties lvl9PPr) {
        generatedSetterHelperImpl(lvl9PPr, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTTextParagraphProperties addNewLvl9PPr() {
        CTTextParagraphProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void unsetLvl9PPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }
}
