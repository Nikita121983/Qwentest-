package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStopList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinBevel;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinRound;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetLineDashProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineCap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineWidth;
import org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment;

/* loaded from: classes11.dex */
public class CTLinePropertiesImpl extends XmlComplexContentImpl implements CTLineProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "noFill"), new QName(XSSFRelation.NS_DRAWINGML, "solidFill"), new QName(XSSFRelation.NS_DRAWINGML, "gradFill"), new QName(XSSFRelation.NS_DRAWINGML, "pattFill"), new QName(XSSFRelation.NS_DRAWINGML, "prstDash"), new QName(XSSFRelation.NS_DRAWINGML, "custDash"), new QName(XSSFRelation.NS_DRAWINGML, "round"), new QName(XSSFRelation.NS_DRAWINGML, "bevel"), new QName(XSSFRelation.NS_DRAWINGML, "miter"), new QName(XSSFRelation.NS_DRAWINGML, "headEnd"), new QName(XSSFRelation.NS_DRAWINGML, "tailEnd"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "w"), new QName("", "cap"), new QName("", "cmpd"), new QName("", "algn")};
    private static final long serialVersionUID = 1;

    public CTLinePropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTNoFillProperties getNoFill() {
        CTNoFillProperties cTNoFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTNoFillProperties target = (CTNoFillProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNoFillProperties = target == null ? null : target;
        }
        return cTNoFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetNoFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setNoFill(CTNoFillProperties noFill) {
        generatedSetterHelperImpl(noFill, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTNoFillProperties addNewNoFill() {
        CTNoFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNoFillProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetNoFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTSolidColorFillProperties getSolidFill() {
        CTSolidColorFillProperties cTSolidColorFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTSolidColorFillProperties target = (CTSolidColorFillProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTSolidColorFillProperties = target == null ? null : target;
        }
        return cTSolidColorFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setSolidFill(CTSolidColorFillProperties solidFill) {
        generatedSetterHelperImpl(solidFill, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTSolidColorFillProperties addNewSolidFill() {
        CTSolidColorFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSolidColorFillProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetSolidFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTGradientFillProperties getGradFill() {
        CTGradientFillProperties cTGradientFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTGradientFillProperties target = (CTGradientFillProperties) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTGradientFillProperties = target == null ? null : target;
        }
        return cTGradientFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetGradFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setGradFill(CTGradientFillProperties gradFill) {
        generatedSetterHelperImpl(gradFill, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTGradientFillProperties addNewGradFill() {
        CTGradientFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGradientFillProperties) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetGradFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTPatternFillProperties getPattFill() {
        CTPatternFillProperties cTPatternFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTPatternFillProperties target = (CTPatternFillProperties) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTPatternFillProperties = target == null ? null : target;
        }
        return cTPatternFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetPattFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setPattFill(CTPatternFillProperties pattFill) {
        generatedSetterHelperImpl(pattFill, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTPatternFillProperties addNewPattFill() {
        CTPatternFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPatternFillProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetPattFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTPresetLineDashProperties getPrstDash() {
        CTPresetLineDashProperties cTPresetLineDashProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTPresetLineDashProperties target = (CTPresetLineDashProperties) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTPresetLineDashProperties = target == null ? null : target;
        }
        return cTPresetLineDashProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetPrstDash() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setPrstDash(CTPresetLineDashProperties prstDash) {
        generatedSetterHelperImpl(prstDash, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTPresetLineDashProperties addNewPrstDash() {
        CTPresetLineDashProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPresetLineDashProperties) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetPrstDash() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTDashStopList getCustDash() {
        CTDashStopList cTDashStopList;
        synchronized (monitor()) {
            check_orphaned();
            CTDashStopList target = (CTDashStopList) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTDashStopList = target == null ? null : target;
        }
        return cTDashStopList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetCustDash() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setCustDash(CTDashStopList custDash) {
        generatedSetterHelperImpl(custDash, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTDashStopList addNewCustDash() {
        CTDashStopList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDashStopList) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetCustDash() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTLineJoinRound getRound() {
        CTLineJoinRound cTLineJoinRound;
        synchronized (monitor()) {
            check_orphaned();
            CTLineJoinRound target = (CTLineJoinRound) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTLineJoinRound = target == null ? null : target;
        }
        return cTLineJoinRound;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetRound() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setRound(CTLineJoinRound round) {
        generatedSetterHelperImpl(round, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTLineJoinRound addNewRound() {
        CTLineJoinRound target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineJoinRound) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetRound() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTLineJoinBevel getBevel() {
        CTLineJoinBevel cTLineJoinBevel;
        synchronized (monitor()) {
            check_orphaned();
            CTLineJoinBevel target = (CTLineJoinBevel) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTLineJoinBevel = target == null ? null : target;
        }
        return cTLineJoinBevel;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetBevel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setBevel(CTLineJoinBevel bevel) {
        generatedSetterHelperImpl(bevel, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTLineJoinBevel addNewBevel() {
        CTLineJoinBevel target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineJoinBevel) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetBevel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTLineJoinMiterProperties getMiter() {
        CTLineJoinMiterProperties cTLineJoinMiterProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTLineJoinMiterProperties target = (CTLineJoinMiterProperties) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTLineJoinMiterProperties = target == null ? null : target;
        }
        return cTLineJoinMiterProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetMiter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setMiter(CTLineJoinMiterProperties miter) {
        generatedSetterHelperImpl(miter, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTLineJoinMiterProperties addNewMiter() {
        CTLineJoinMiterProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineJoinMiterProperties) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetMiter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTLineEndProperties getHeadEnd() {
        CTLineEndProperties cTLineEndProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTLineEndProperties target = (CTLineEndProperties) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTLineEndProperties = target == null ? null : target;
        }
        return cTLineEndProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetHeadEnd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setHeadEnd(CTLineEndProperties headEnd) {
        generatedSetterHelperImpl(headEnd, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTLineEndProperties addNewHeadEnd() {
        CTLineEndProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineEndProperties) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetHeadEnd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTLineEndProperties getTailEnd() {
        CTLineEndProperties cTLineEndProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTLineEndProperties target = (CTLineEndProperties) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTLineEndProperties = target == null ? null : target;
        }
        return cTLineEndProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetTailEnd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setTailEnd(CTLineEndProperties tailEnd) {
        generatedSetterHelperImpl(tailEnd, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTLineEndProperties addNewTailEnd() {
        CTLineEndProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineEndProperties) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetTailEnd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public int getW() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public STLineWidth xgetW() {
        STLineWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLineWidth) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetW() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setW(int w) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setIntValue(w);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void xsetW(STLineWidth w) {
        synchronized (monitor()) {
            check_orphaned();
            STLineWidth target = (STLineWidth) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (STLineWidth) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(w);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public STLineCap.Enum getCap() {
        STLineCap.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            r1 = target == null ? null : (STLineCap.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public STLineCap xgetCap() {
        STLineCap target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLineCap) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetCap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setCap(STLineCap.Enum cap) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setEnumValue(cap);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void xsetCap(STLineCap cap) {
        synchronized (monitor()) {
            check_orphaned();
            STLineCap target = (STLineCap) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (STLineCap) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(cap);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetCap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public STCompoundLine.Enum getCmpd() {
        STCompoundLine.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            r1 = target == null ? null : (STCompoundLine.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public STCompoundLine xgetCmpd() {
        STCompoundLine target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCompoundLine) get_store().find_attribute_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetCmpd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setCmpd(STCompoundLine.Enum cmpd) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setEnumValue(cmpd);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void xsetCmpd(STCompoundLine cmpd) {
        synchronized (monitor()) {
            check_orphaned();
            STCompoundLine target = (STCompoundLine) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (STCompoundLine) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(cmpd);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetCmpd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public STPenAlignment.Enum getAlgn() {
        STPenAlignment.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            r1 = target == null ? null : (STPenAlignment.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public STPenAlignment xgetAlgn() {
        STPenAlignment target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPenAlignment) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public boolean isSetAlgn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void setAlgn(STPenAlignment.Enum algn) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setEnumValue(algn);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void xsetAlgn(STPenAlignment algn) {
        synchronized (monitor()) {
            check_orphaned();
            STPenAlignment target = (STPenAlignment) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (STPenAlignment) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(algn);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties
    public void unsetAlgn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }
}
