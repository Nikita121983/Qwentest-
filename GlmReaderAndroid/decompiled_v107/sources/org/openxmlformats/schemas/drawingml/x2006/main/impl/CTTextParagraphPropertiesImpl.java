package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBlipBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletColorFollowText;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizeFollowText;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePercent;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePoint;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletTypefaceFollowText;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNoBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAlignType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontAlignType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextIndent;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextIndentLevelType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextMargin;

/* loaded from: classes11.dex */
public class CTTextParagraphPropertiesImpl extends XmlComplexContentImpl implements CTTextParagraphProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "lnSpc"), new QName(XSSFRelation.NS_DRAWINGML, "spcBef"), new QName(XSSFRelation.NS_DRAWINGML, "spcAft"), new QName(XSSFRelation.NS_DRAWINGML, "buClrTx"), new QName(XSSFRelation.NS_DRAWINGML, "buClr"), new QName(XSSFRelation.NS_DRAWINGML, "buSzTx"), new QName(XSSFRelation.NS_DRAWINGML, "buSzPct"), new QName(XSSFRelation.NS_DRAWINGML, "buSzPts"), new QName(XSSFRelation.NS_DRAWINGML, "buFontTx"), new QName(XSSFRelation.NS_DRAWINGML, "buFont"), new QName(XSSFRelation.NS_DRAWINGML, "buNone"), new QName(XSSFRelation.NS_DRAWINGML, "buAutoNum"), new QName(XSSFRelation.NS_DRAWINGML, "buChar"), new QName(XSSFRelation.NS_DRAWINGML, "buBlip"), new QName(XSSFRelation.NS_DRAWINGML, "tabLst"), new QName(XSSFRelation.NS_DRAWINGML, "defRPr"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "marL"), new QName("", "marR"), new QName("", "lvl"), new QName("", "indent"), new QName("", "algn"), new QName("", "defTabSz"), new QName("", "rtl"), new QName("", "eaLnBrk"), new QName("", "fontAlgn"), new QName("", "latinLnBrk"), new QName("", "hangingPunct")};
    private static final long serialVersionUID = 1;

    public CTTextParagraphPropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextSpacing getLnSpc() {
        CTTextSpacing cTTextSpacing;
        synchronized (monitor()) {
            check_orphaned();
            CTTextSpacing target = (CTTextSpacing) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTextSpacing = target == null ? null : target;
        }
        return cTTextSpacing;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetLnSpc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setLnSpc(CTTextSpacing lnSpc) {
        generatedSetterHelperImpl(lnSpc, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextSpacing addNewLnSpc() {
        CTTextSpacing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextSpacing) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetLnSpc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextSpacing getSpcBef() {
        CTTextSpacing cTTextSpacing;
        synchronized (monitor()) {
            check_orphaned();
            CTTextSpacing target = (CTTextSpacing) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTextSpacing = target == null ? null : target;
        }
        return cTTextSpacing;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetSpcBef() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setSpcBef(CTTextSpacing spcBef) {
        generatedSetterHelperImpl(spcBef, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextSpacing addNewSpcBef() {
        CTTextSpacing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextSpacing) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetSpcBef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextSpacing getSpcAft() {
        CTTextSpacing cTTextSpacing;
        synchronized (monitor()) {
            check_orphaned();
            CTTextSpacing target = (CTTextSpacing) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTextSpacing = target == null ? null : target;
        }
        return cTTextSpacing;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetSpcAft() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setSpcAft(CTTextSpacing spcAft) {
        generatedSetterHelperImpl(spcAft, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextSpacing addNewSpcAft() {
        CTTextSpacing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextSpacing) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetSpcAft() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextBulletColorFollowText getBuClrTx() {
        CTTextBulletColorFollowText cTTextBulletColorFollowText;
        synchronized (monitor()) {
            check_orphaned();
            CTTextBulletColorFollowText target = (CTTextBulletColorFollowText) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTTextBulletColorFollowText = target == null ? null : target;
        }
        return cTTextBulletColorFollowText;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetBuClrTx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setBuClrTx(CTTextBulletColorFollowText buClrTx) {
        generatedSetterHelperImpl(buClrTx, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextBulletColorFollowText addNewBuClrTx() {
        CTTextBulletColorFollowText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextBulletColorFollowText) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetBuClrTx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTColor getBuClr() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            CTColor target = (CTColor) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTColor = target == null ? null : target;
        }
        return cTColor;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetBuClr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setBuClr(CTColor buClr) {
        generatedSetterHelperImpl(buClr, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTColor addNewBuClr() {
        CTColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColor) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetBuClr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextBulletSizeFollowText getBuSzTx() {
        CTTextBulletSizeFollowText cTTextBulletSizeFollowText;
        synchronized (monitor()) {
            check_orphaned();
            CTTextBulletSizeFollowText target = (CTTextBulletSizeFollowText) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTTextBulletSizeFollowText = target == null ? null : target;
        }
        return cTTextBulletSizeFollowText;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetBuSzTx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setBuSzTx(CTTextBulletSizeFollowText buSzTx) {
        generatedSetterHelperImpl(buSzTx, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextBulletSizeFollowText addNewBuSzTx() {
        CTTextBulletSizeFollowText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextBulletSizeFollowText) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetBuSzTx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextBulletSizePercent getBuSzPct() {
        CTTextBulletSizePercent cTTextBulletSizePercent;
        synchronized (monitor()) {
            check_orphaned();
            CTTextBulletSizePercent target = (CTTextBulletSizePercent) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTTextBulletSizePercent = target == null ? null : target;
        }
        return cTTextBulletSizePercent;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetBuSzPct() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setBuSzPct(CTTextBulletSizePercent buSzPct) {
        generatedSetterHelperImpl(buSzPct, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextBulletSizePercent addNewBuSzPct() {
        CTTextBulletSizePercent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextBulletSizePercent) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetBuSzPct() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextBulletSizePoint getBuSzPts() {
        CTTextBulletSizePoint cTTextBulletSizePoint;
        synchronized (monitor()) {
            check_orphaned();
            CTTextBulletSizePoint target = (CTTextBulletSizePoint) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTTextBulletSizePoint = target == null ? null : target;
        }
        return cTTextBulletSizePoint;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetBuSzPts() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setBuSzPts(CTTextBulletSizePoint buSzPts) {
        generatedSetterHelperImpl(buSzPts, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextBulletSizePoint addNewBuSzPts() {
        CTTextBulletSizePoint target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextBulletSizePoint) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetBuSzPts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextBulletTypefaceFollowText getBuFontTx() {
        CTTextBulletTypefaceFollowText cTTextBulletTypefaceFollowText;
        synchronized (monitor()) {
            check_orphaned();
            CTTextBulletTypefaceFollowText target = (CTTextBulletTypefaceFollowText) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTTextBulletTypefaceFollowText = target == null ? null : target;
        }
        return cTTextBulletTypefaceFollowText;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetBuFontTx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setBuFontTx(CTTextBulletTypefaceFollowText buFontTx) {
        generatedSetterHelperImpl(buFontTx, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextBulletTypefaceFollowText addNewBuFontTx() {
        CTTextBulletTypefaceFollowText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextBulletTypefaceFollowText) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetBuFontTx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextFont getBuFont() {
        CTTextFont cTTextFont;
        synchronized (monitor()) {
            check_orphaned();
            CTTextFont target = (CTTextFont) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTTextFont = target == null ? null : target;
        }
        return cTTextFont;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetBuFont() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setBuFont(CTTextFont buFont) {
        generatedSetterHelperImpl(buFont, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextFont addNewBuFont() {
        CTTextFont target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextFont) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetBuFont() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextNoBullet getBuNone() {
        CTTextNoBullet cTTextNoBullet;
        synchronized (monitor()) {
            check_orphaned();
            CTTextNoBullet target = (CTTextNoBullet) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTTextNoBullet = target == null ? null : target;
        }
        return cTTextNoBullet;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetBuNone() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setBuNone(CTTextNoBullet buNone) {
        generatedSetterHelperImpl(buNone, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextNoBullet addNewBuNone() {
        CTTextNoBullet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextNoBullet) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetBuNone() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextAutonumberBullet getBuAutoNum() {
        CTTextAutonumberBullet cTTextAutonumberBullet;
        synchronized (monitor()) {
            check_orphaned();
            CTTextAutonumberBullet target = (CTTextAutonumberBullet) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTTextAutonumberBullet = target == null ? null : target;
        }
        return cTTextAutonumberBullet;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetBuAutoNum() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setBuAutoNum(CTTextAutonumberBullet buAutoNum) {
        generatedSetterHelperImpl(buAutoNum, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextAutonumberBullet addNewBuAutoNum() {
        CTTextAutonumberBullet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextAutonumberBullet) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetBuAutoNum() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextCharBullet getBuChar() {
        CTTextCharBullet cTTextCharBullet;
        synchronized (monitor()) {
            check_orphaned();
            CTTextCharBullet target = (CTTextCharBullet) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTTextCharBullet = target == null ? null : target;
        }
        return cTTextCharBullet;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetBuChar() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setBuChar(CTTextCharBullet buChar) {
        generatedSetterHelperImpl(buChar, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextCharBullet addNewBuChar() {
        CTTextCharBullet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextCharBullet) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetBuChar() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextBlipBullet getBuBlip() {
        CTTextBlipBullet cTTextBlipBullet;
        synchronized (monitor()) {
            check_orphaned();
            CTTextBlipBullet target = (CTTextBlipBullet) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTTextBlipBullet = target == null ? null : target;
        }
        return cTTextBlipBullet;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetBuBlip() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setBuBlip(CTTextBlipBullet buBlip) {
        generatedSetterHelperImpl(buBlip, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextBlipBullet addNewBuBlip() {
        CTTextBlipBullet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextBlipBullet) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetBuBlip() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextTabStopList getTabLst() {
        CTTextTabStopList cTTextTabStopList;
        synchronized (monitor()) {
            check_orphaned();
            CTTextTabStopList target = (CTTextTabStopList) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTTextTabStopList = target == null ? null : target;
        }
        return cTTextTabStopList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetTabLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setTabLst(CTTextTabStopList tabLst) {
        generatedSetterHelperImpl(tabLst, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextTabStopList addNewTabLst() {
        CTTextTabStopList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextTabStopList) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetTabLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextCharacterProperties getDefRPr() {
        CTTextCharacterProperties cTTextCharacterProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextCharacterProperties target = (CTTextCharacterProperties) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            cTTextCharacterProperties = target == null ? null : target;
        }
        return cTTextCharacterProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetDefRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setDefRPr(CTTextCharacterProperties defRPr) {
        generatedSetterHelperImpl(defRPr, PROPERTY_QNAME[15], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTTextCharacterProperties addNewDefRPr() {
        CTTextCharacterProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextCharacterProperties) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetDefRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTOfficeArtExtensionList target = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            cTOfficeArtExtensionList = target == null ? null : target;
        }
        return cTOfficeArtExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setExtLst(CTOfficeArtExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[16], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public int getMarL() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public STTextMargin xgetMarL() {
        STTextMargin target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextMargin) get_store().find_attribute_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetMarL() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[17]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setMarL(int marL) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.setIntValue(marL);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void xsetMarL(STTextMargin marL) {
        synchronized (monitor()) {
            check_orphaned();
            STTextMargin target = (STTextMargin) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            if (target == null) {
                target = (STTextMargin) get_store().add_attribute_user(PROPERTY_QNAME[17]);
            }
            target.set(marL);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetMarL() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[17]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public int getMarR() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public STTextMargin xgetMarR() {
        STTextMargin target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextMargin) get_store().find_attribute_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetMarR() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[18]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setMarR(int marR) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.setIntValue(marR);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void xsetMarR(STTextMargin marR) {
        synchronized (monitor()) {
            check_orphaned();
            STTextMargin target = (STTextMargin) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            if (target == null) {
                target = (STTextMargin) get_store().add_attribute_user(PROPERTY_QNAME[18]);
            }
            target.set(marR);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetMarR() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[18]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public int getLvl() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public STTextIndentLevelType xgetLvl() {
        STTextIndentLevelType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextIndentLevelType) get_store().find_attribute_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetLvl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[19]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setLvl(int lvl) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.setIntValue(lvl);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void xsetLvl(STTextIndentLevelType lvl) {
        synchronized (monitor()) {
            check_orphaned();
            STTextIndentLevelType target = (STTextIndentLevelType) get_store().find_attribute_user(PROPERTY_QNAME[19]);
            if (target == null) {
                target = (STTextIndentLevelType) get_store().add_attribute_user(PROPERTY_QNAME[19]);
            }
            target.set(lvl);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetLvl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[19]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public int getIndent() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public STTextIndent xgetIndent() {
        STTextIndent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextIndent) get_store().find_attribute_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetIndent() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[20]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setIndent(int indent) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.setIntValue(indent);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void xsetIndent(STTextIndent indent) {
        synchronized (monitor()) {
            check_orphaned();
            STTextIndent target = (STTextIndent) get_store().find_attribute_user(PROPERTY_QNAME[20]);
            if (target == null) {
                target = (STTextIndent) get_store().add_attribute_user(PROPERTY_QNAME[20]);
            }
            target.set(indent);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetIndent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[20]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public STTextAlignType.Enum getAlgn() {
        STTextAlignType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            r1 = target == null ? null : (STTextAlignType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public STTextAlignType xgetAlgn() {
        STTextAlignType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextAlignType) get_store().find_attribute_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetAlgn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[21]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setAlgn(STTextAlignType.Enum algn) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.setEnumValue(algn);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void xsetAlgn(STTextAlignType algn) {
        synchronized (monitor()) {
            check_orphaned();
            STTextAlignType target = (STTextAlignType) get_store().find_attribute_user(PROPERTY_QNAME[21]);
            if (target == null) {
                target = (STTextAlignType) get_store().add_attribute_user(PROPERTY_QNAME[21]);
            }
            target.set(algn);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetAlgn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[21]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public Object getDefTabSz() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public STCoordinate32 xgetDefTabSz() {
        STCoordinate32 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetDefTabSz() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[22]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setDefTabSz(Object defTabSz) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.setObjectValue(defTabSz);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void xsetDefTabSz(STCoordinate32 defTabSz) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate32 target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[22]);
            if (target == null) {
                target = (STCoordinate32) get_store().add_attribute_user(PROPERTY_QNAME[22]);
            }
            target.set(defTabSz);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetDefTabSz() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[22]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean getRtl() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public XmlBoolean xgetRtl() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetRtl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[23]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setRtl(boolean rtl) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.setBooleanValue(rtl);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void xsetRtl(XmlBoolean rtl) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[23]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[23]);
            }
            target.set(rtl);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetRtl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[23]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean getEaLnBrk() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public XmlBoolean xgetEaLnBrk() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetEaLnBrk() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[24]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setEaLnBrk(boolean eaLnBrk) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.setBooleanValue(eaLnBrk);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void xsetEaLnBrk(XmlBoolean eaLnBrk) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[24]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[24]);
            }
            target.set(eaLnBrk);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetEaLnBrk() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[24]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public STTextFontAlignType.Enum getFontAlgn() {
        STTextFontAlignType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            r1 = target == null ? null : (STTextFontAlignType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public STTextFontAlignType xgetFontAlgn() {
        STTextFontAlignType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextFontAlignType) get_store().find_attribute_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetFontAlgn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[25]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setFontAlgn(STTextFontAlignType.Enum fontAlgn) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.setEnumValue(fontAlgn);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void xsetFontAlgn(STTextFontAlignType fontAlgn) {
        synchronized (monitor()) {
            check_orphaned();
            STTextFontAlignType target = (STTextFontAlignType) get_store().find_attribute_user(PROPERTY_QNAME[25]);
            if (target == null) {
                target = (STTextFontAlignType) get_store().add_attribute_user(PROPERTY_QNAME[25]);
            }
            target.set(fontAlgn);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetFontAlgn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[25]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean getLatinLnBrk() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public XmlBoolean xgetLatinLnBrk() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetLatinLnBrk() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[26]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setLatinLnBrk(boolean latinLnBrk) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.setBooleanValue(latinLnBrk);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void xsetLatinLnBrk(XmlBoolean latinLnBrk) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[26]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[26]);
            }
            target.set(latinLnBrk);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetLatinLnBrk() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[26]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean getHangingPunct() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public XmlBoolean xgetHangingPunct() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public boolean isSetHangingPunct() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[27]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void setHangingPunct(boolean hangingPunct) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.setBooleanValue(hangingPunct);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void xsetHangingPunct(XmlBoolean hangingPunct) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[27]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[27]);
            }
            target.set(hangingPunct);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties
    public void unsetHangingPunct() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[27]);
        }
    }
}
