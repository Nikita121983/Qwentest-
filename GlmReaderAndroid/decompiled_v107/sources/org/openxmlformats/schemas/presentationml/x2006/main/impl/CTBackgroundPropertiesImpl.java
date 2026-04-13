package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;

/* loaded from: classes11.dex */
public class CTBackgroundPropertiesImpl extends XmlComplexContentImpl implements CTBackgroundProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "noFill"), new QName(XSSFRelation.NS_DRAWINGML, "solidFill"), new QName(XSSFRelation.NS_DRAWINGML, "gradFill"), new QName(XSSFRelation.NS_DRAWINGML, "blipFill"), new QName(XSSFRelation.NS_DRAWINGML, "pattFill"), new QName(XSSFRelation.NS_DRAWINGML, "grpFill"), new QName(XSSFRelation.NS_DRAWINGML, "effectLst"), new QName(XSSFRelation.NS_DRAWINGML, "effectDag"), new QName(XSSFRelation.NS_PRESENTATIONML, "extLst"), new QName("", "shadeToTitle")};
    private static final long serialVersionUID = 1;

    public CTBackgroundPropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTNoFillProperties getNoFill() {
        CTNoFillProperties cTNoFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTNoFillProperties target = (CTNoFillProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNoFillProperties = target == null ? null : target;
        }
        return cTNoFillProperties;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public boolean isSetNoFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void setNoFill(CTNoFillProperties noFill) {
        generatedSetterHelperImpl(noFill, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTNoFillProperties addNewNoFill() {
        CTNoFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNoFillProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void unsetNoFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTSolidColorFillProperties getSolidFill() {
        CTSolidColorFillProperties cTSolidColorFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTSolidColorFillProperties target = (CTSolidColorFillProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTSolidColorFillProperties = target == null ? null : target;
        }
        return cTSolidColorFillProperties;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public boolean isSetSolidFill() {
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

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void setSolidFill(CTSolidColorFillProperties solidFill) {
        generatedSetterHelperImpl(solidFill, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTSolidColorFillProperties addNewSolidFill() {
        CTSolidColorFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSolidColorFillProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void unsetSolidFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTGradientFillProperties getGradFill() {
        CTGradientFillProperties cTGradientFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTGradientFillProperties target = (CTGradientFillProperties) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTGradientFillProperties = target == null ? null : target;
        }
        return cTGradientFillProperties;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public boolean isSetGradFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void setGradFill(CTGradientFillProperties gradFill) {
        generatedSetterHelperImpl(gradFill, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTGradientFillProperties addNewGradFill() {
        CTGradientFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGradientFillProperties) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void unsetGradFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTBlipFillProperties getBlipFill() {
        CTBlipFillProperties cTBlipFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTBlipFillProperties target = (CTBlipFillProperties) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTBlipFillProperties = target == null ? null : target;
        }
        return cTBlipFillProperties;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public boolean isSetBlipFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void setBlipFill(CTBlipFillProperties blipFill) {
        generatedSetterHelperImpl(blipFill, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTBlipFillProperties addNewBlipFill() {
        CTBlipFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBlipFillProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void unsetBlipFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTPatternFillProperties getPattFill() {
        CTPatternFillProperties cTPatternFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTPatternFillProperties target = (CTPatternFillProperties) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTPatternFillProperties = target == null ? null : target;
        }
        return cTPatternFillProperties;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public boolean isSetPattFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void setPattFill(CTPatternFillProperties pattFill) {
        generatedSetterHelperImpl(pattFill, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTPatternFillProperties addNewPattFill() {
        CTPatternFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPatternFillProperties) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void unsetPattFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTGroupFillProperties getGrpFill() {
        CTGroupFillProperties cTGroupFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTGroupFillProperties target = (CTGroupFillProperties) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTGroupFillProperties = target == null ? null : target;
        }
        return cTGroupFillProperties;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public boolean isSetGrpFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void setGrpFill(CTGroupFillProperties grpFill) {
        generatedSetterHelperImpl(grpFill, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTGroupFillProperties addNewGrpFill() {
        CTGroupFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupFillProperties) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void unsetGrpFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTEffectList getEffectLst() {
        CTEffectList cTEffectList;
        synchronized (monitor()) {
            check_orphaned();
            CTEffectList target = (CTEffectList) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTEffectList = target == null ? null : target;
        }
        return cTEffectList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public boolean isSetEffectLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void setEffectLst(CTEffectList effectLst) {
        generatedSetterHelperImpl(effectLst, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTEffectList addNewEffectLst() {
        CTEffectList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectList) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void unsetEffectLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTEffectContainer getEffectDag() {
        CTEffectContainer cTEffectContainer;
        synchronized (monitor()) {
            check_orphaned();
            CTEffectContainer target = (CTEffectContainer) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTEffectContainer = target == null ? null : target;
        }
        return cTEffectContainer;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public boolean isSetEffectDag() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void setEffectDag(CTEffectContainer effectDag) {
        generatedSetterHelperImpl(effectDag, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTEffectContainer addNewEffectDag() {
        CTEffectContainer target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectContainer) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void unsetEffectDag() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public boolean getShadeToTitle() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public XmlBoolean xgetShadeToTitle() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public boolean isSetShadeToTitle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void setShadeToTitle(boolean shadeToTitle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setBooleanValue(shadeToTitle);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void xsetShadeToTitle(XmlBoolean shadeToTitle) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(shadeToTitle);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties
    public void unsetShadeToTitle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }
}
