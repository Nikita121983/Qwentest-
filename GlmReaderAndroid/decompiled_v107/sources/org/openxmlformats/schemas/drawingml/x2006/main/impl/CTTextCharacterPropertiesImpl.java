package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import androidx.core.app.NotificationCompat;
import javax.xml.namespace.QName;
import org.apache.poi.ss.formula.functions.Complex;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextUnderlineFillFollowText;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextUnderlineFillGroupWrapper;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextUnderlineLineFollowText;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextCapsType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontSize;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextNonNegativePoint;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextPoint;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STLang;

/* loaded from: classes11.dex */
public class CTTextCharacterPropertiesImpl extends XmlComplexContentImpl implements CTTextCharacterProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "ln"), new QName(XSSFRelation.NS_DRAWINGML, "noFill"), new QName(XSSFRelation.NS_DRAWINGML, "solidFill"), new QName(XSSFRelation.NS_DRAWINGML, "gradFill"), new QName(XSSFRelation.NS_DRAWINGML, "blipFill"), new QName(XSSFRelation.NS_DRAWINGML, "pattFill"), new QName(XSSFRelation.NS_DRAWINGML, "grpFill"), new QName(XSSFRelation.NS_DRAWINGML, "effectLst"), new QName(XSSFRelation.NS_DRAWINGML, "effectDag"), new QName(XSSFRelation.NS_DRAWINGML, "highlight"), new QName(XSSFRelation.NS_DRAWINGML, "uLnTx"), new QName(XSSFRelation.NS_DRAWINGML, "uLn"), new QName(XSSFRelation.NS_DRAWINGML, "uFillTx"), new QName(XSSFRelation.NS_DRAWINGML, "uFill"), new QName(XSSFRelation.NS_DRAWINGML, "latin"), new QName(XSSFRelation.NS_DRAWINGML, "ea"), new QName(XSSFRelation.NS_DRAWINGML, "cs"), new QName(XSSFRelation.NS_DRAWINGML, "sym"), new QName(XSSFRelation.NS_DRAWINGML, "hlinkClick"), new QName(XSSFRelation.NS_DRAWINGML, "hlinkMouseOver"), new QName(XSSFRelation.NS_DRAWINGML, "rtl"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "kumimoji"), new QName("", "lang"), new QName("", "altLang"), new QName("", "sz"), new QName("", "b"), new QName("", Complex.DEFAULT_SUFFIX), new QName("", "u"), new QName("", "strike"), new QName("", "kern"), new QName("", "cap"), new QName("", "spc"), new QName("", "normalizeH"), new QName("", "baseline"), new QName("", "noProof"), new QName("", "dirty"), new QName("", NotificationCompat.CATEGORY_ERROR), new QName("", "smtClean"), new QName("", "smtId"), new QName("", "bmk")};
    private static final long serialVersionUID = 1;

    public CTTextCharacterPropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTLineProperties getLn() {
        CTLineProperties cTLineProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTLineProperties target = (CTLineProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTLineProperties = target == null ? null : target;
        }
        return cTLineProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetLn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setLn(CTLineProperties ln) {
        generatedSetterHelperImpl(ln, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTLineProperties addNewLn() {
        CTLineProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetLn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTNoFillProperties getNoFill() {
        CTNoFillProperties cTNoFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTNoFillProperties target = (CTNoFillProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTNoFillProperties = target == null ? null : target;
        }
        return cTNoFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetNoFill() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setNoFill(CTNoFillProperties noFill) {
        generatedSetterHelperImpl(noFill, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTNoFillProperties addNewNoFill() {
        CTNoFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNoFillProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetNoFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTSolidColorFillProperties getSolidFill() {
        CTSolidColorFillProperties cTSolidColorFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTSolidColorFillProperties target = (CTSolidColorFillProperties) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTSolidColorFillProperties = target == null ? null : target;
        }
        return cTSolidColorFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetSolidFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setSolidFill(CTSolidColorFillProperties solidFill) {
        generatedSetterHelperImpl(solidFill, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTSolidColorFillProperties addNewSolidFill() {
        CTSolidColorFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSolidColorFillProperties) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetSolidFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTGradientFillProperties getGradFill() {
        CTGradientFillProperties cTGradientFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTGradientFillProperties target = (CTGradientFillProperties) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTGradientFillProperties = target == null ? null : target;
        }
        return cTGradientFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetGradFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setGradFill(CTGradientFillProperties gradFill) {
        generatedSetterHelperImpl(gradFill, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTGradientFillProperties addNewGradFill() {
        CTGradientFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGradientFillProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetGradFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTBlipFillProperties getBlipFill() {
        CTBlipFillProperties cTBlipFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTBlipFillProperties target = (CTBlipFillProperties) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTBlipFillProperties = target == null ? null : target;
        }
        return cTBlipFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetBlipFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setBlipFill(CTBlipFillProperties blipFill) {
        generatedSetterHelperImpl(blipFill, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTBlipFillProperties addNewBlipFill() {
        CTBlipFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBlipFillProperties) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetBlipFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTPatternFillProperties getPattFill() {
        CTPatternFillProperties cTPatternFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTPatternFillProperties target = (CTPatternFillProperties) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTPatternFillProperties = target == null ? null : target;
        }
        return cTPatternFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetPattFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setPattFill(CTPatternFillProperties pattFill) {
        generatedSetterHelperImpl(pattFill, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTPatternFillProperties addNewPattFill() {
        CTPatternFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPatternFillProperties) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetPattFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTGroupFillProperties getGrpFill() {
        CTGroupFillProperties cTGroupFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTGroupFillProperties target = (CTGroupFillProperties) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTGroupFillProperties = target == null ? null : target;
        }
        return cTGroupFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetGrpFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setGrpFill(CTGroupFillProperties grpFill) {
        generatedSetterHelperImpl(grpFill, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTGroupFillProperties addNewGrpFill() {
        CTGroupFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupFillProperties) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetGrpFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTEffectList getEffectLst() {
        CTEffectList cTEffectList;
        synchronized (monitor()) {
            check_orphaned();
            CTEffectList target = (CTEffectList) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTEffectList = target == null ? null : target;
        }
        return cTEffectList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetEffectLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setEffectLst(CTEffectList effectLst) {
        generatedSetterHelperImpl(effectLst, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTEffectList addNewEffectLst() {
        CTEffectList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectList) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetEffectLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTEffectContainer getEffectDag() {
        CTEffectContainer cTEffectContainer;
        synchronized (monitor()) {
            check_orphaned();
            CTEffectContainer target = (CTEffectContainer) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTEffectContainer = target == null ? null : target;
        }
        return cTEffectContainer;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetEffectDag() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setEffectDag(CTEffectContainer effectDag) {
        generatedSetterHelperImpl(effectDag, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTEffectContainer addNewEffectDag() {
        CTEffectContainer target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectContainer) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetEffectDag() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTColor getHighlight() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetHighlight() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setHighlight(CTColor highlight) {
        generatedSetterHelperImpl(highlight, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTColor addNewHighlight() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetHighlight() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTTextUnderlineLineFollowText getULnTx() {
        CTTextUnderlineLineFollowText cTTextUnderlineLineFollowText;
        synchronized (monitor()) {
            check_orphaned();
            CTTextUnderlineLineFollowText target = get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTTextUnderlineLineFollowText = target == null ? null : target;
        }
        return cTTextUnderlineLineFollowText;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetULnTx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setULnTx(CTTextUnderlineLineFollowText uLnTx) {
        generatedSetterHelperImpl(uLnTx, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTTextUnderlineLineFollowText addNewULnTx() {
        CTTextUnderlineLineFollowText target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetULnTx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTLineProperties getULn() {
        CTLineProperties cTLineProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTLineProperties target = (CTLineProperties) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTLineProperties = target == null ? null : target;
        }
        return cTLineProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetULn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setULn(CTLineProperties uLn) {
        generatedSetterHelperImpl(uLn, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTLineProperties addNewULn() {
        CTLineProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineProperties) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetULn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTTextUnderlineFillFollowText getUFillTx() {
        CTTextUnderlineFillFollowText cTTextUnderlineFillFollowText;
        synchronized (monitor()) {
            check_orphaned();
            CTTextUnderlineFillFollowText target = (CTTextUnderlineFillFollowText) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTTextUnderlineFillFollowText = target == null ? null : target;
        }
        return cTTextUnderlineFillFollowText;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetUFillTx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setUFillTx(CTTextUnderlineFillFollowText uFillTx) {
        generatedSetterHelperImpl(uFillTx, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTTextUnderlineFillFollowText addNewUFillTx() {
        CTTextUnderlineFillFollowText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextUnderlineFillFollowText) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetUFillTx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTTextUnderlineFillGroupWrapper getUFill() {
        CTTextUnderlineFillGroupWrapper cTTextUnderlineFillGroupWrapper;
        synchronized (monitor()) {
            check_orphaned();
            CTTextUnderlineFillGroupWrapper target = get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTTextUnderlineFillGroupWrapper = target == null ? null : target;
        }
        return cTTextUnderlineFillGroupWrapper;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetUFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setUFill(CTTextUnderlineFillGroupWrapper uFill) {
        generatedSetterHelperImpl(uFill, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTTextUnderlineFillGroupWrapper addNewUFill() {
        CTTextUnderlineFillGroupWrapper target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetUFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTTextFont getLatin() {
        CTTextFont cTTextFont;
        synchronized (monitor()) {
            check_orphaned();
            CTTextFont target = (CTTextFont) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTTextFont = target == null ? null : target;
        }
        return cTTextFont;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetLatin() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setLatin(CTTextFont latin) {
        generatedSetterHelperImpl(latin, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTTextFont addNewLatin() {
        CTTextFont target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextFont) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetLatin() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTTextFont getEa() {
        CTTextFont cTTextFont;
        synchronized (monitor()) {
            check_orphaned();
            CTTextFont target = (CTTextFont) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            cTTextFont = target == null ? null : target;
        }
        return cTTextFont;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetEa() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setEa(CTTextFont ea) {
        generatedSetterHelperImpl(ea, PROPERTY_QNAME[15], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTTextFont addNewEa() {
        CTTextFont target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextFont) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetEa() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTTextFont getCs() {
        CTTextFont cTTextFont;
        synchronized (monitor()) {
            check_orphaned();
            CTTextFont target = (CTTextFont) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            cTTextFont = target == null ? null : target;
        }
        return cTTextFont;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetCs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setCs(CTTextFont cs) {
        generatedSetterHelperImpl(cs, PROPERTY_QNAME[16], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTTextFont addNewCs() {
        CTTextFont target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextFont) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetCs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTTextFont getSym() {
        CTTextFont cTTextFont;
        synchronized (monitor()) {
            check_orphaned();
            CTTextFont target = (CTTextFont) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            cTTextFont = target == null ? null : target;
        }
        return cTTextFont;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetSym() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setSym(CTTextFont sym) {
        generatedSetterHelperImpl(sym, PROPERTY_QNAME[17], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTTextFont addNewSym() {
        CTTextFont target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextFont) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetSym() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTHyperlink getHlinkClick() {
        CTHyperlink cTHyperlink;
        synchronized (monitor()) {
            check_orphaned();
            CTHyperlink target = (CTHyperlink) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            cTHyperlink = target == null ? null : target;
        }
        return cTHyperlink;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetHlinkClick() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setHlinkClick(CTHyperlink hlinkClick) {
        generatedSetterHelperImpl(hlinkClick, PROPERTY_QNAME[18], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTHyperlink addNewHlinkClick() {
        CTHyperlink target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHyperlink) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetHlinkClick() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTHyperlink getHlinkMouseOver() {
        CTHyperlink cTHyperlink;
        synchronized (monitor()) {
            check_orphaned();
            CTHyperlink target = (CTHyperlink) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            cTHyperlink = target == null ? null : target;
        }
        return cTHyperlink;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetHlinkMouseOver() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setHlinkMouseOver(CTHyperlink hlinkMouseOver) {
        generatedSetterHelperImpl(hlinkMouseOver, PROPERTY_QNAME[19], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTHyperlink addNewHlinkMouseOver() {
        CTHyperlink target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHyperlink) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetHlinkMouseOver() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTBoolean getRtl() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            CTBoolean target = get_store().find_element_user(PROPERTY_QNAME[20], 0);
            cTBoolean = target == null ? null : target;
        }
        return cTBoolean;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetRtl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setRtl(CTBoolean rtl) {
        generatedSetterHelperImpl(rtl, PROPERTY_QNAME[20], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTBoolean addNewRtl() {
        CTBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetRtl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[21], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[21], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean getKumimoji() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public XmlBoolean xgetKumimoji() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetKumimoji() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[22]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setKumimoji(boolean kumimoji) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.setBooleanValue(kumimoji);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetKumimoji(XmlBoolean kumimoji) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.set(kumimoji);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetKumimoji() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[22]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public String getLang() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public STLang xgetLang() {
        STLang target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLang) get_store().find_attribute_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetLang() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[23]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setLang(String lang) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.setStringValue(lang);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetLang(STLang lang) {
        synchronized (monitor()) {
            check_orphaned();
            STLang target = (STLang) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (STLang) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.set(lang);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetLang() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[23]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public String getAltLang() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public STLang xgetAltLang() {
        STLang target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLang) get_store().find_attribute_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetAltLang() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[24]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setAltLang(String altLang) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.setStringValue(altLang);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetAltLang(STLang altLang) {
        synchronized (monitor()) {
            check_orphaned();
            STLang target = (STLang) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (STLang) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.set(altLang);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetAltLang() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[24]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public int getSz() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public STTextFontSize xgetSz() {
        STTextFontSize target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextFontSize) get_store().find_attribute_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetSz() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[25]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setSz(int sz) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.setIntValue(sz);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetSz(STTextFontSize sz) {
        synchronized (monitor()) {
            check_orphaned();
            STTextFontSize target = (STTextFontSize) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (STTextFontSize) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.set(sz);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetSz() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[25]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean getB() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public XmlBoolean xgetB() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetB() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[26]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setB(boolean b) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.setBooleanValue(b);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetB(XmlBoolean b) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.set(b);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetB() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[26]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean getI() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public XmlBoolean xgetI() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetI() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[27]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setI(boolean iValue) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.setBooleanValue(iValue);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetI(XmlBoolean iValue) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.set(iValue);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetI() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[27]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public STTextUnderlineType.Enum getU() {
        STTextUnderlineType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            r1 = target == null ? null : (STTextUnderlineType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public STTextUnderlineType xgetU() {
        STTextUnderlineType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextUnderlineType) get_store().find_attribute_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetU() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[28]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setU(STTextUnderlineType.Enum u) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.setEnumValue(u);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetU(STTextUnderlineType u) {
        synchronized (monitor()) {
            check_orphaned();
            STTextUnderlineType target = (STTextUnderlineType) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (target == null) {
                target = (STTextUnderlineType) get_store().add_attribute_user(PROPERTY_QNAME[28]);
            }
            target.set(u);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetU() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[28]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public STTextStrikeType.Enum getStrike() {
        STTextStrikeType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            r1 = target == null ? null : (STTextStrikeType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public STTextStrikeType xgetStrike() {
        STTextStrikeType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextStrikeType) get_store().find_attribute_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetStrike() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[29]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setStrike(STTextStrikeType.Enum strike) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[29]);
            }
            target.setEnumValue(strike);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetStrike(STTextStrikeType strike) {
        synchronized (monitor()) {
            check_orphaned();
            STTextStrikeType target = (STTextStrikeType) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (target == null) {
                target = (STTextStrikeType) get_store().add_attribute_user(PROPERTY_QNAME[29]);
            }
            target.set(strike);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetStrike() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[29]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public int getKern() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public STTextNonNegativePoint xgetKern() {
        STTextNonNegativePoint target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextNonNegativePoint) get_store().find_attribute_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetKern() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[30]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setKern(int kern) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[30]);
            }
            target.setIntValue(kern);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetKern(STTextNonNegativePoint kern) {
        synchronized (monitor()) {
            check_orphaned();
            STTextNonNegativePoint target = (STTextNonNegativePoint) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (target == null) {
                target = (STTextNonNegativePoint) get_store().add_attribute_user(PROPERTY_QNAME[30]);
            }
            target.set(kern);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetKern() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[30]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public STTextCapsType.Enum getCap() {
        STTextCapsType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[31]);
            }
            r1 = target == null ? null : (STTextCapsType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public STTextCapsType xgetCap() {
        STTextCapsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextCapsType) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (STTextCapsType) get_default_attribute_value(PROPERTY_QNAME[31]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetCap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[31]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setCap(STTextCapsType.Enum cap) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[31]);
            }
            target.setEnumValue(cap);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetCap(STTextCapsType cap) {
        synchronized (monitor()) {
            check_orphaned();
            STTextCapsType target = (STTextCapsType) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (target == null) {
                target = (STTextCapsType) get_store().add_attribute_user(PROPERTY_QNAME[31]);
            }
            target.set(cap);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetCap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[31]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public Object getSpc() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public STTextPoint xgetSpc() {
        STTextPoint target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextPoint) get_store().find_attribute_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetSpc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[32]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setSpc(Object spc) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[32]);
            }
            target.setObjectValue(spc);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetSpc(STTextPoint spc) {
        synchronized (monitor()) {
            check_orphaned();
            STTextPoint target = (STTextPoint) get_store().find_attribute_user(PROPERTY_QNAME[32]);
            if (target == null) {
                target = (STTextPoint) get_store().add_attribute_user(PROPERTY_QNAME[32]);
            }
            target.set(spc);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetSpc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[32]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean getNormalizeH() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public XmlBoolean xgetNormalizeH() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[33]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetNormalizeH() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[33]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setNormalizeH(boolean normalizeH) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[33]);
            }
            target.setBooleanValue(normalizeH);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetNormalizeH(XmlBoolean normalizeH) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[33]);
            }
            target.set(normalizeH);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetNormalizeH() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[33]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public Object getBaseline() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public STPercentage xgetBaseline() {
        STPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[34]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetBaseline() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[34]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setBaseline(Object baseline) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[34]);
            }
            target.setObjectValue(baseline);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetBaseline(STPercentage baseline) {
        synchronized (monitor()) {
            check_orphaned();
            STPercentage target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            if (target == null) {
                target = (STPercentage) get_store().add_attribute_user(PROPERTY_QNAME[34]);
            }
            target.set(baseline);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetBaseline() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[34]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean getNoProof() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public XmlBoolean xgetNoProof() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[35]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetNoProof() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[35]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setNoProof(boolean noProof) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[35]);
            }
            target.setBooleanValue(noProof);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetNoProof(XmlBoolean noProof) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[35]);
            }
            target.set(noProof);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetNoProof() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[35]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean getDirty() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[36]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public XmlBoolean xgetDirty() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[36]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetDirty() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[36]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setDirty(boolean dirty) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[36]);
            }
            target.setBooleanValue(dirty);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetDirty(XmlBoolean dirty) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[36]);
            }
            target.set(dirty);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetDirty() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[36]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean getErr() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[37]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public XmlBoolean xgetErr() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[37]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetErr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[37]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setErr(boolean err) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[37]);
            }
            target.setBooleanValue(err);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetErr(XmlBoolean err) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[37]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[37]);
            }
            target.set(err);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetErr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[37]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean getSmtClean() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[38]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public XmlBoolean xgetSmtClean() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[38]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetSmtClean() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[38]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setSmtClean(boolean smtClean) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[38]);
            }
            target.setBooleanValue(smtClean);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetSmtClean(XmlBoolean smtClean) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[38]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[38]);
            }
            target.set(smtClean);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetSmtClean() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[38]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public long getSmtId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[39]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public XmlUnsignedInt xgetSmtId() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            if (target == null) {
                target = (XmlUnsignedInt) get_default_attribute_value(PROPERTY_QNAME[39]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetSmtId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[39]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setSmtId(long smtId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[39]);
            }
            target.setLongValue(smtId);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetSmtId(XmlUnsignedInt smtId) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[39]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[39]);
            }
            target.set(smtId);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetSmtId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[39]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public String getBmk() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public XmlString xgetBmk() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[40]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public boolean isSetBmk() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[40]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void setBmk(String bmk) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[40]);
            }
            target.setStringValue(bmk);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void xsetBmk(XmlString bmk) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[40]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[40]);
            }
            target.set(bmk);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties
    public void unsetBmk() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[40]);
        }
    }
}
