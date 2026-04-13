package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJcTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblCellMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLook;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblOverlap;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;

/* loaded from: classes12.dex */
public class CTTblPrBaseImpl extends XmlComplexContentImpl implements CTTblPrBase {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblStyle"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblpPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblOverlap"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bidiVisual"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblStyleRowBandSize"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblStyleColBandSize"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblW"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "jc"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblCellSpacing"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblInd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblBorders"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "shd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblLayout"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblCellMar"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblLook"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblCaption"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblDescription")};
    private static final long serialVersionUID = 1;

    public CTTblPrBaseImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTString getTblStyle() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetTblStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setTblStyle(CTString tblStyle) {
        generatedSetterHelperImpl(tblStyle, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTString addNewTblStyle() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetTblStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblPPr getTblpPr() {
        CTTblPPr cTTblPPr;
        synchronized (monitor()) {
            check_orphaned();
            CTTblPPr target = (CTTblPPr) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTblPPr = target == null ? null : target;
        }
        return cTTblPPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetTblpPr() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setTblpPr(CTTblPPr tblpPr) {
        generatedSetterHelperImpl(tblpPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblPPr addNewTblpPr() {
        CTTblPPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblPPr) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetTblpPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblOverlap getTblOverlap() {
        CTTblOverlap cTTblOverlap;
        synchronized (monitor()) {
            check_orphaned();
            CTTblOverlap target = (CTTblOverlap) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTblOverlap = target == null ? null : target;
        }
        return cTTblOverlap;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetTblOverlap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setTblOverlap(CTTblOverlap tblOverlap) {
        generatedSetterHelperImpl(tblOverlap, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblOverlap addNewTblOverlap() {
        CTTblOverlap target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblOverlap) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetTblOverlap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTOnOff getBidiVisual() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetBidiVisual() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setBidiVisual(CTOnOff bidiVisual) {
        generatedSetterHelperImpl(bidiVisual, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTOnOff addNewBidiVisual() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetBidiVisual() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTDecimalNumber getTblStyleRowBandSize() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetTblStyleRowBandSize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setTblStyleRowBandSize(CTDecimalNumber tblStyleRowBandSize) {
        generatedSetterHelperImpl(tblStyleRowBandSize, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTDecimalNumber addNewTblStyleRowBandSize() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetTblStyleRowBandSize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTDecimalNumber getTblStyleColBandSize() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetTblStyleColBandSize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setTblStyleColBandSize(CTDecimalNumber tblStyleColBandSize) {
        generatedSetterHelperImpl(tblStyleColBandSize, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTDecimalNumber addNewTblStyleColBandSize() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetTblStyleColBandSize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblWidth getTblW() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            CTTblWidth target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTTblWidth = target == null ? null : target;
        }
        return cTTblWidth;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetTblW() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setTblW(CTTblWidth tblW) {
        generatedSetterHelperImpl(tblW, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblWidth addNewTblW() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetTblW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTJcTable getJc() {
        CTJcTable cTJcTable;
        synchronized (monitor()) {
            check_orphaned();
            CTJcTable target = (CTJcTable) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTJcTable = target == null ? null : target;
        }
        return cTJcTable;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetJc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setJc(CTJcTable jc) {
        generatedSetterHelperImpl(jc, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTJcTable addNewJc() {
        CTJcTable target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTJcTable) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetJc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblWidth getTblCellSpacing() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            CTTblWidth target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTTblWidth = target == null ? null : target;
        }
        return cTTblWidth;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetTblCellSpacing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setTblCellSpacing(CTTblWidth tblCellSpacing) {
        generatedSetterHelperImpl(tblCellSpacing, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblWidth addNewTblCellSpacing() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetTblCellSpacing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblWidth getTblInd() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            CTTblWidth target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTTblWidth = target == null ? null : target;
        }
        return cTTblWidth;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetTblInd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setTblInd(CTTblWidth tblInd) {
        generatedSetterHelperImpl(tblInd, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblWidth addNewTblInd() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetTblInd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblBorders getTblBorders() {
        CTTblBorders cTTblBorders;
        synchronized (monitor()) {
            check_orphaned();
            CTTblBorders target = (CTTblBorders) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTTblBorders = target == null ? null : target;
        }
        return cTTblBorders;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetTblBorders() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setTblBorders(CTTblBorders tblBorders) {
        generatedSetterHelperImpl(tblBorders, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblBorders addNewTblBorders() {
        CTTblBorders target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblBorders) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetTblBorders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTShd getShd() {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            CTShd target = (CTShd) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTShd = target == null ? null : target;
        }
        return cTShd;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetShd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setShd(CTShd shd) {
        generatedSetterHelperImpl(shd, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTShd addNewShd() {
        CTShd target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShd) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetShd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblLayoutType getTblLayout() {
        CTTblLayoutType cTTblLayoutType;
        synchronized (monitor()) {
            check_orphaned();
            CTTblLayoutType target = (CTTblLayoutType) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTTblLayoutType = target == null ? null : target;
        }
        return cTTblLayoutType;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetTblLayout() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setTblLayout(CTTblLayoutType tblLayout) {
        generatedSetterHelperImpl(tblLayout, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblLayoutType addNewTblLayout() {
        CTTblLayoutType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblLayoutType) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetTblLayout() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblCellMar getTblCellMar() {
        CTTblCellMar cTTblCellMar;
        synchronized (monitor()) {
            check_orphaned();
            CTTblCellMar target = (CTTblCellMar) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTTblCellMar = target == null ? null : target;
        }
        return cTTblCellMar;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetTblCellMar() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setTblCellMar(CTTblCellMar tblCellMar) {
        generatedSetterHelperImpl(tblCellMar, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblCellMar addNewTblCellMar() {
        CTTblCellMar target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblCellMar) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetTblCellMar() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblLook getTblLook() {
        CTTblLook cTTblLook;
        synchronized (monitor()) {
            check_orphaned();
            CTTblLook target = (CTTblLook) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTTblLook = target == null ? null : target;
        }
        return cTTblLook;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetTblLook() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setTblLook(CTTblLook tblLook) {
        generatedSetterHelperImpl(tblLook, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTTblLook addNewTblLook() {
        CTTblLook target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblLook) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetTblLook() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTString getTblCaption() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetTblCaption() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setTblCaption(CTString tblCaption) {
        generatedSetterHelperImpl(tblCaption, PROPERTY_QNAME[15], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTString addNewTblCaption() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetTblCaption() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTString getTblDescription() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public boolean isSetTblDescription() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void setTblDescription(CTString tblDescription) {
        generatedSetterHelperImpl(tblDescription, PROPERTY_QNAME[16], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public CTString addNewTblDescription() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase
    public void unsetTblDescription() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }
}
