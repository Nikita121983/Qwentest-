package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeaders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;

/* loaded from: classes12.dex */
public class CTTcPrBaseImpl extends XmlComplexContentImpl implements CTTcPrBase {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "cnfStyle"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tcW"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "gridSpan"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hMerge"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "vMerge"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tcBorders"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "shd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "noWrap"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tcMar"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "textDirection"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tcFitText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "vAlign"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hideMark"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "headers")};
    private static final long serialVersionUID = 1;

    public CTTcPrBaseImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTCnf getCnfStyle() {
        CTCnf cTCnf;
        synchronized (monitor()) {
            check_orphaned();
            CTCnf target = (CTCnf) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTCnf = target == null ? null : target;
        }
        return cTCnf;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public boolean isSetCnfStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void setCnfStyle(CTCnf cnfStyle) {
        generatedSetterHelperImpl(cnfStyle, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTCnf addNewCnfStyle() {
        CTCnf target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCnf) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void unsetCnfStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTTblWidth getTcW() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            CTTblWidth target = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTblWidth = target == null ? null : target;
        }
        return cTTblWidth;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public boolean isSetTcW() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void setTcW(CTTblWidth tcW) {
        generatedSetterHelperImpl(tcW, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTTblWidth addNewTcW() {
        CTTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void unsetTcW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTDecimalNumber getGridSpan() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public boolean isSetGridSpan() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void setGridSpan(CTDecimalNumber gridSpan) {
        generatedSetterHelperImpl(gridSpan, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTDecimalNumber addNewGridSpan() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void unsetGridSpan() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTHMerge getHMerge() {
        CTHMerge cTHMerge;
        synchronized (monitor()) {
            check_orphaned();
            CTHMerge target = (CTHMerge) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTHMerge = target == null ? null : target;
        }
        return cTHMerge;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public boolean isSetHMerge() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void setHMerge(CTHMerge hMerge) {
        generatedSetterHelperImpl(hMerge, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTHMerge addNewHMerge() {
        CTHMerge target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHMerge) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void unsetHMerge() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTVMerge getVMerge() {
        CTVMerge cTVMerge;
        synchronized (monitor()) {
            check_orphaned();
            CTVMerge target = (CTVMerge) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTVMerge = target == null ? null : target;
        }
        return cTVMerge;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public boolean isSetVMerge() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void setVMerge(CTVMerge vMerge) {
        generatedSetterHelperImpl(vMerge, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTVMerge addNewVMerge() {
        CTVMerge target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVMerge) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void unsetVMerge() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTTcBorders getTcBorders() {
        CTTcBorders cTTcBorders;
        synchronized (monitor()) {
            check_orphaned();
            CTTcBorders target = (CTTcBorders) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTTcBorders = target == null ? null : target;
        }
        return cTTcBorders;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public boolean isSetTcBorders() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void setTcBorders(CTTcBorders tcBorders) {
        generatedSetterHelperImpl(tcBorders, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTTcBorders addNewTcBorders() {
        CTTcBorders target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTcBorders) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void unsetTcBorders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTShd getShd() {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            CTShd target = (CTShd) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTShd = target == null ? null : target;
        }
        return cTShd;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public boolean isSetShd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void setShd(CTShd shd) {
        generatedSetterHelperImpl(shd, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTShd addNewShd() {
        CTShd target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShd) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void unsetShd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTOnOff getNoWrap() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public boolean isSetNoWrap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void setNoWrap(CTOnOff noWrap) {
        generatedSetterHelperImpl(noWrap, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTOnOff addNewNoWrap() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void unsetNoWrap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTTcMar getTcMar() {
        CTTcMar cTTcMar;
        synchronized (monitor()) {
            check_orphaned();
            CTTcMar target = (CTTcMar) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTTcMar = target == null ? null : target;
        }
        return cTTcMar;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public boolean isSetTcMar() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void setTcMar(CTTcMar tcMar) {
        generatedSetterHelperImpl(tcMar, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTTcMar addNewTcMar() {
        CTTcMar target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTcMar) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void unsetTcMar() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTTextDirection getTextDirection() {
        CTTextDirection cTTextDirection;
        synchronized (monitor()) {
            check_orphaned();
            CTTextDirection target = (CTTextDirection) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTTextDirection = target == null ? null : target;
        }
        return cTTextDirection;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public boolean isSetTextDirection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void setTextDirection(CTTextDirection textDirection) {
        generatedSetterHelperImpl(textDirection, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTTextDirection addNewTextDirection() {
        CTTextDirection target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextDirection) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void unsetTextDirection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTOnOff getTcFitText() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public boolean isSetTcFitText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void setTcFitText(CTOnOff tcFitText) {
        generatedSetterHelperImpl(tcFitText, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTOnOff addNewTcFitText() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void unsetTcFitText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTVerticalJc getVAlign() {
        CTVerticalJc cTVerticalJc;
        synchronized (monitor()) {
            check_orphaned();
            CTVerticalJc target = (CTVerticalJc) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTVerticalJc = target == null ? null : target;
        }
        return cTVerticalJc;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public boolean isSetVAlign() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void setVAlign(CTVerticalJc vAlign) {
        generatedSetterHelperImpl(vAlign, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTVerticalJc addNewVAlign() {
        CTVerticalJc target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTVerticalJc) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void unsetVAlign() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTOnOff getHideMark() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public boolean isSetHideMark() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void setHideMark(CTOnOff hideMark) {
        generatedSetterHelperImpl(hideMark, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTOnOff addNewHideMark() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void unsetHideMark() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTHeaders getHeaders() {
        CTHeaders cTHeaders;
        synchronized (monitor()) {
            check_orphaned();
            CTHeaders target = get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTHeaders = target == null ? null : target;
        }
        return cTHeaders;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public boolean isSetHeaders() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void setHeaders(CTHeaders headers) {
        generatedSetterHelperImpl(headers, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public CTHeaders addNewHeaders() {
        CTHeaders target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase
    public void unsetHeaders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }
}
