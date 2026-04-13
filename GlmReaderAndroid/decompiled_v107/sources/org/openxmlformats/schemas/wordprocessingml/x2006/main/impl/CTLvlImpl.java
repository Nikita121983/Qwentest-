package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLevelSuffix;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLevelText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvlLegacy;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumFmt;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrGeneral;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;

/* loaded from: classes12.dex */
public class CTLvlImpl extends XmlComplexContentImpl implements CTLvl {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "start"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "numFmt"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lvlRestart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "pStyle"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "isLgl"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "suff"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lvlText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lvlPicBulletId"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "legacy"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lvlJc"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "pPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "rPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "ilvl"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tplc"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tentative")};
    private static final long serialVersionUID = 1;

    public CTLvlImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTDecimalNumber getStart() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public boolean isSetStart() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setStart(CTDecimalNumber start) {
        generatedSetterHelperImpl(start, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTDecimalNumber addNewStart() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void unsetStart() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTNumFmt getNumFmt() {
        CTNumFmt cTNumFmt;
        synchronized (monitor()) {
            check_orphaned();
            CTNumFmt target = (CTNumFmt) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTNumFmt = target == null ? null : target;
        }
        return cTNumFmt;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public boolean isSetNumFmt() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setNumFmt(CTNumFmt numFmt) {
        generatedSetterHelperImpl(numFmt, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTNumFmt addNewNumFmt() {
        CTNumFmt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumFmt) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void unsetNumFmt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTDecimalNumber getLvlRestart() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public boolean isSetLvlRestart() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setLvlRestart(CTDecimalNumber lvlRestart) {
        generatedSetterHelperImpl(lvlRestart, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTDecimalNumber addNewLvlRestart() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void unsetLvlRestart() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTString getPStyle() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public boolean isSetPStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setPStyle(CTString pStyle) {
        generatedSetterHelperImpl(pStyle, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTString addNewPStyle() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void unsetPStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTOnOff getIsLgl() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public boolean isSetIsLgl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setIsLgl(CTOnOff isLgl) {
        generatedSetterHelperImpl(isLgl, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTOnOff addNewIsLgl() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void unsetIsLgl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTLevelSuffix getSuff() {
        CTLevelSuffix cTLevelSuffix;
        synchronized (monitor()) {
            check_orphaned();
            CTLevelSuffix target = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTLevelSuffix = target == null ? null : target;
        }
        return cTLevelSuffix;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public boolean isSetSuff() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setSuff(CTLevelSuffix suff) {
        generatedSetterHelperImpl(suff, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTLevelSuffix addNewSuff() {
        CTLevelSuffix target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void unsetSuff() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTLevelText getLvlText() {
        CTLevelText cTLevelText;
        synchronized (monitor()) {
            check_orphaned();
            CTLevelText target = (CTLevelText) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTLevelText = target == null ? null : target;
        }
        return cTLevelText;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public boolean isSetLvlText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setLvlText(CTLevelText lvlText) {
        generatedSetterHelperImpl(lvlText, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTLevelText addNewLvlText() {
        CTLevelText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLevelText) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void unsetLvlText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTDecimalNumber getLvlPicBulletId() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public boolean isSetLvlPicBulletId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setLvlPicBulletId(CTDecimalNumber lvlPicBulletId) {
        generatedSetterHelperImpl(lvlPicBulletId, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTDecimalNumber addNewLvlPicBulletId() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void unsetLvlPicBulletId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTLvlLegacy getLegacy() {
        CTLvlLegacy cTLvlLegacy;
        synchronized (monitor()) {
            check_orphaned();
            CTLvlLegacy target = get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTLvlLegacy = target == null ? null : target;
        }
        return cTLvlLegacy;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public boolean isSetLegacy() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setLegacy(CTLvlLegacy legacy) {
        generatedSetterHelperImpl(legacy, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTLvlLegacy addNewLegacy() {
        CTLvlLegacy target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void unsetLegacy() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTJc getLvlJc() {
        CTJc cTJc;
        synchronized (monitor()) {
            check_orphaned();
            CTJc target = (CTJc) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTJc = target == null ? null : target;
        }
        return cTJc;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public boolean isSetLvlJc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setLvlJc(CTJc lvlJc) {
        generatedSetterHelperImpl(lvlJc, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTJc addNewLvlJc() {
        CTJc target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTJc) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void unsetLvlJc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTPPrGeneral getPPr() {
        CTPPrGeneral cTPPrGeneral;
        synchronized (monitor()) {
            check_orphaned();
            CTPPrGeneral target = (CTPPrGeneral) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTPPrGeneral = target == null ? null : target;
        }
        return cTPPrGeneral;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public boolean isSetPPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setPPr(CTPPrGeneral pPr) {
        generatedSetterHelperImpl(pPr, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTPPrGeneral addNewPPr() {
        CTPPrGeneral target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPPrGeneral) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void unsetPPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTRPr getRPr() {
        CTRPr cTRPr;
        synchronized (monitor()) {
            check_orphaned();
            CTRPr target = (CTRPr) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTRPr = target == null ? null : target;
        }
        return cTRPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public boolean isSetRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setRPr(CTRPr rPr) {
        generatedSetterHelperImpl(rPr, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public CTRPr addNewRPr() {
        CTRPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRPr) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void unsetRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public BigInteger getIlvl() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public STDecimalNumber xgetIlvl() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setIlvl(BigInteger ilvl) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setBigIntegerValue(ilvl);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void xsetIlvl(STDecimalNumber ilvl) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(ilvl);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public byte[] getTplc() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public STLongHexNumber xgetTplc() {
        STLongHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public boolean isSetTplc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setTplc(byte[] tplc) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setByteArrayValue(tplc);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void xsetTplc(STLongHexNumber tplc) {
        synchronized (monitor()) {
            check_orphaned();
            STLongHexNumber target = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (STLongHexNumber) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(tplc);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void unsetTplc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public Object getTentative() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public STOnOff xgetTentative() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public boolean isSetTentative() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void setTentative(Object tentative) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setObjectValue(tentative);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void xsetTentative(STOnOff tentative) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(tentative);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl
    public void unsetTentative() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }
}
