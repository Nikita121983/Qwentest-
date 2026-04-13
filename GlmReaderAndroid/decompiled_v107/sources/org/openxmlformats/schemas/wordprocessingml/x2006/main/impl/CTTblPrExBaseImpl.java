package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJcTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblCellMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLook;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;

/* loaded from: classes12.dex */
public class CTTblPrExBaseImpl extends XmlComplexContentImpl implements CTTblPrExBase {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblW"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "jc"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblCellSpacing"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblInd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblBorders"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "shd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblLayout"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblCellMar"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblLook")};
    private static final long serialVersionUID = 1;

    public CTTblPrExBaseImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTTblWidth getTblW() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            CTTblWidth target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTblWidth = target == null ? null : target;
        }
        return cTTblWidth;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public boolean isSetTblW() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void setTblW(CTTblWidth tblW) {
        generatedSetterHelperImpl(tblW, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTTblWidth addNewTblW() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void unsetTblW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTJcTable getJc() {
        CTJcTable cTJcTable;
        synchronized (monitor()) {
            check_orphaned();
            CTJcTable target = (CTJcTable) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTJcTable = target == null ? null : target;
        }
        return cTJcTable;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public boolean isSetJc() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void setJc(CTJcTable jc) {
        generatedSetterHelperImpl(jc, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTJcTable addNewJc() {
        CTJcTable target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTJcTable) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void unsetJc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTTblWidth getTblCellSpacing() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            CTTblWidth target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTblWidth = target == null ? null : target;
        }
        return cTTblWidth;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public boolean isSetTblCellSpacing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void setTblCellSpacing(CTTblWidth tblCellSpacing) {
        generatedSetterHelperImpl(tblCellSpacing, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTTblWidth addNewTblCellSpacing() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void unsetTblCellSpacing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTTblWidth getTblInd() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            CTTblWidth target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTTblWidth = target == null ? null : target;
        }
        return cTTblWidth;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public boolean isSetTblInd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void setTblInd(CTTblWidth tblInd) {
        generatedSetterHelperImpl(tblInd, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTTblWidth addNewTblInd() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void unsetTblInd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTTblBorders getTblBorders() {
        CTTblBorders cTTblBorders;
        synchronized (monitor()) {
            check_orphaned();
            CTTblBorders target = (CTTblBorders) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTTblBorders = target == null ? null : target;
        }
        return cTTblBorders;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public boolean isSetTblBorders() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void setTblBorders(CTTblBorders tblBorders) {
        generatedSetterHelperImpl(tblBorders, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTTblBorders addNewTblBorders() {
        CTTblBorders target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblBorders) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void unsetTblBorders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTShd getShd() {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            CTShd target = (CTShd) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTShd = target == null ? null : target;
        }
        return cTShd;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public boolean isSetShd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void setShd(CTShd shd) {
        generatedSetterHelperImpl(shd, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTShd addNewShd() {
        CTShd target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShd) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void unsetShd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTTblLayoutType getTblLayout() {
        CTTblLayoutType cTTblLayoutType;
        synchronized (monitor()) {
            check_orphaned();
            CTTblLayoutType target = (CTTblLayoutType) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTTblLayoutType = target == null ? null : target;
        }
        return cTTblLayoutType;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public boolean isSetTblLayout() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void setTblLayout(CTTblLayoutType tblLayout) {
        generatedSetterHelperImpl(tblLayout, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTTblLayoutType addNewTblLayout() {
        CTTblLayoutType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblLayoutType) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void unsetTblLayout() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTTblCellMar getTblCellMar() {
        CTTblCellMar cTTblCellMar;
        synchronized (monitor()) {
            check_orphaned();
            CTTblCellMar target = (CTTblCellMar) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTTblCellMar = target == null ? null : target;
        }
        return cTTblCellMar;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public boolean isSetTblCellMar() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void setTblCellMar(CTTblCellMar tblCellMar) {
        generatedSetterHelperImpl(tblCellMar, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTTblCellMar addNewTblCellMar() {
        CTTblCellMar target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblCellMar) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void unsetTblCellMar() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTTblLook getTblLook() {
        CTTblLook cTTblLook;
        synchronized (monitor()) {
            check_orphaned();
            CTTblLook target = (CTTblLook) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTTblLook = target == null ? null : target;
        }
        return cTTblLook;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public boolean isSetTblLook() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void setTblLook(CTTblLook tblLook) {
        generatedSetterHelperImpl(tblLook, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public CTTblLook addNewTblLook() {
        CTTblLook target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblLook) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase
    public void unsetTblLook() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }
}
