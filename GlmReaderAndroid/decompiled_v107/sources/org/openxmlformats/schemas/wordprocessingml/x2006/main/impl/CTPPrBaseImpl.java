package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramePr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabs;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextAlignment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextboxTightWrap;

/* loaded from: classes12.dex */
public class CTPPrBaseImpl extends XmlComplexContentImpl implements CTPPrBase {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "pStyle"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "keepNext"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "keepLines"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "pageBreakBefore"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "framePr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "widowControl"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "numPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "suppressLineNumbers"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "pBdr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "shd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tabs"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "suppressAutoHyphens"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "kinsoku"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "wordWrap"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "overflowPunct"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "topLinePunct"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "autoSpaceDE"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "autoSpaceDN"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bidi"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "adjustRightInd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "snapToGrid"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "spacing"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "ind"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "contextualSpacing"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "mirrorIndents"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "suppressOverlap"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "jc"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "textDirection"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "textAlignment"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "textboxTightWrap"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "outlineLvl"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "divId"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cnfStyle")};
    private static final long serialVersionUID = 1;

    public CTPPrBaseImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTString getPStyle() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            CTString target = (CTString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTString = target == null ? null : target;
        }
        return cTString;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetPStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setPStyle(CTString pStyle) {
        generatedSetterHelperImpl(pStyle, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTString addNewPStyle() {
        CTString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTString) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetPStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getKeepNext() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetKeepNext() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setKeepNext(CTOnOff keepNext) {
        generatedSetterHelperImpl(keepNext, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewKeepNext() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetKeepNext() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getKeepLines() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetKeepLines() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setKeepLines(CTOnOff keepLines) {
        generatedSetterHelperImpl(keepLines, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewKeepLines() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetKeepLines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getPageBreakBefore() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetPageBreakBefore() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setPageBreakBefore(CTOnOff pageBreakBefore) {
        generatedSetterHelperImpl(pageBreakBefore, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewPageBreakBefore() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetPageBreakBefore() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTFramePr getFramePr() {
        CTFramePr cTFramePr;
        synchronized (monitor()) {
            check_orphaned();
            CTFramePr target = (CTFramePr) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTFramePr = target == null ? null : target;
        }
        return cTFramePr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetFramePr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setFramePr(CTFramePr framePr) {
        generatedSetterHelperImpl(framePr, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTFramePr addNewFramePr() {
        CTFramePr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFramePr) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetFramePr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getWidowControl() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetWidowControl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setWidowControl(CTOnOff widowControl) {
        generatedSetterHelperImpl(widowControl, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewWidowControl() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetWidowControl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTNumPr getNumPr() {
        CTNumPr cTNumPr;
        synchronized (monitor()) {
            check_orphaned();
            CTNumPr target = (CTNumPr) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTNumPr = target == null ? null : target;
        }
        return cTNumPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetNumPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setNumPr(CTNumPr numPr) {
        generatedSetterHelperImpl(numPr, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTNumPr addNewNumPr() {
        CTNumPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumPr) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetNumPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getSuppressLineNumbers() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetSuppressLineNumbers() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setSuppressLineNumbers(CTOnOff suppressLineNumbers) {
        generatedSetterHelperImpl(suppressLineNumbers, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewSuppressLineNumbers() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetSuppressLineNumbers() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTPBdr getPBdr() {
        CTPBdr cTPBdr;
        synchronized (monitor()) {
            check_orphaned();
            CTPBdr target = (CTPBdr) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTPBdr = target == null ? null : target;
        }
        return cTPBdr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetPBdr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setPBdr(CTPBdr pBdr) {
        generatedSetterHelperImpl(pBdr, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTPBdr addNewPBdr() {
        CTPBdr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPBdr) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetPBdr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTShd getShd() {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            CTShd target = (CTShd) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTShd = target == null ? null : target;
        }
        return cTShd;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetShd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setShd(CTShd shd) {
        generatedSetterHelperImpl(shd, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTShd addNewShd() {
        CTShd target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShd) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetShd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTTabs getTabs() {
        CTTabs cTTabs;
        synchronized (monitor()) {
            check_orphaned();
            CTTabs target = (CTTabs) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTTabs = target == null ? null : target;
        }
        return cTTabs;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetTabs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setTabs(CTTabs tabs) {
        generatedSetterHelperImpl(tabs, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTTabs addNewTabs() {
        CTTabs target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTabs) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetTabs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getSuppressAutoHyphens() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetSuppressAutoHyphens() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setSuppressAutoHyphens(CTOnOff suppressAutoHyphens) {
        generatedSetterHelperImpl(suppressAutoHyphens, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewSuppressAutoHyphens() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetSuppressAutoHyphens() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getKinsoku() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetKinsoku() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setKinsoku(CTOnOff kinsoku) {
        generatedSetterHelperImpl(kinsoku, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewKinsoku() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetKinsoku() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getWordWrap() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetWordWrap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setWordWrap(CTOnOff wordWrap) {
        generatedSetterHelperImpl(wordWrap, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewWordWrap() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetWordWrap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getOverflowPunct() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetOverflowPunct() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setOverflowPunct(CTOnOff overflowPunct) {
        generatedSetterHelperImpl(overflowPunct, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewOverflowPunct() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetOverflowPunct() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getTopLinePunct() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetTopLinePunct() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setTopLinePunct(CTOnOff topLinePunct) {
        generatedSetterHelperImpl(topLinePunct, PROPERTY_QNAME[15], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewTopLinePunct() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetTopLinePunct() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getAutoSpaceDE() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetAutoSpaceDE() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setAutoSpaceDE(CTOnOff autoSpaceDE) {
        generatedSetterHelperImpl(autoSpaceDE, PROPERTY_QNAME[16], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewAutoSpaceDE() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetAutoSpaceDE() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getAutoSpaceDN() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetAutoSpaceDN() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setAutoSpaceDN(CTOnOff autoSpaceDN) {
        generatedSetterHelperImpl(autoSpaceDN, PROPERTY_QNAME[17], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewAutoSpaceDN() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetAutoSpaceDN() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getBidi() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetBidi() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setBidi(CTOnOff bidi) {
        generatedSetterHelperImpl(bidi, PROPERTY_QNAME[18], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewBidi() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetBidi() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getAdjustRightInd() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetAdjustRightInd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setAdjustRightInd(CTOnOff adjustRightInd) {
        generatedSetterHelperImpl(adjustRightInd, PROPERTY_QNAME[19], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewAdjustRightInd() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetAdjustRightInd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getSnapToGrid() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[20], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetSnapToGrid() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setSnapToGrid(CTOnOff snapToGrid) {
        generatedSetterHelperImpl(snapToGrid, PROPERTY_QNAME[20], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewSnapToGrid() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetSnapToGrid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTSpacing getSpacing() {
        CTSpacing cTSpacing;
        synchronized (monitor()) {
            check_orphaned();
            CTSpacing target = (CTSpacing) get_store().find_element_user(PROPERTY_QNAME[21], 0);
            cTSpacing = target == null ? null : target;
        }
        return cTSpacing;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetSpacing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setSpacing(CTSpacing spacing) {
        generatedSetterHelperImpl(spacing, PROPERTY_QNAME[21], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTSpacing addNewSpacing() {
        CTSpacing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSpacing) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetSpacing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTInd getInd() {
        CTInd cTInd;
        synchronized (monitor()) {
            check_orphaned();
            CTInd target = (CTInd) get_store().find_element_user(PROPERTY_QNAME[22], 0);
            cTInd = target == null ? null : target;
        }
        return cTInd;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetInd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[22]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setInd(CTInd ind) {
        generatedSetterHelperImpl(ind, PROPERTY_QNAME[22], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTInd addNewInd() {
        CTInd target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTInd) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetInd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getContextualSpacing() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[23], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetContextualSpacing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[23]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setContextualSpacing(CTOnOff contextualSpacing) {
        generatedSetterHelperImpl(contextualSpacing, PROPERTY_QNAME[23], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewContextualSpacing() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetContextualSpacing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getMirrorIndents() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[24], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetMirrorIndents() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[24]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setMirrorIndents(CTOnOff mirrorIndents) {
        generatedSetterHelperImpl(mirrorIndents, PROPERTY_QNAME[24], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewMirrorIndents() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetMirrorIndents() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff getSuppressOverlap() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[25], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetSuppressOverlap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[25]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setSuppressOverlap(CTOnOff suppressOverlap) {
        generatedSetterHelperImpl(suppressOverlap, PROPERTY_QNAME[25], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTOnOff addNewSuppressOverlap() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetSuppressOverlap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTJc getJc() {
        CTJc cTJc;
        synchronized (monitor()) {
            check_orphaned();
            CTJc target = (CTJc) get_store().find_element_user(PROPERTY_QNAME[26], 0);
            cTJc = target == null ? null : target;
        }
        return cTJc;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetJc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[26]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setJc(CTJc jc) {
        generatedSetterHelperImpl(jc, PROPERTY_QNAME[26], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTJc addNewJc() {
        CTJc target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTJc) get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetJc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTTextDirection getTextDirection() {
        CTTextDirection cTTextDirection;
        synchronized (monitor()) {
            check_orphaned();
            CTTextDirection target = (CTTextDirection) get_store().find_element_user(PROPERTY_QNAME[27], 0);
            cTTextDirection = target == null ? null : target;
        }
        return cTTextDirection;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetTextDirection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[27]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setTextDirection(CTTextDirection textDirection) {
        generatedSetterHelperImpl(textDirection, PROPERTY_QNAME[27], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTTextDirection addNewTextDirection() {
        CTTextDirection target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextDirection) get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetTextDirection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTTextAlignment getTextAlignment() {
        CTTextAlignment cTTextAlignment;
        synchronized (monitor()) {
            check_orphaned();
            CTTextAlignment target = (CTTextAlignment) get_store().find_element_user(PROPERTY_QNAME[28], 0);
            cTTextAlignment = target == null ? null : target;
        }
        return cTTextAlignment;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetTextAlignment() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[28]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setTextAlignment(CTTextAlignment textAlignment) {
        generatedSetterHelperImpl(textAlignment, PROPERTY_QNAME[28], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTTextAlignment addNewTextAlignment() {
        CTTextAlignment target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextAlignment) get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetTextAlignment() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTTextboxTightWrap getTextboxTightWrap() {
        CTTextboxTightWrap cTTextboxTightWrap;
        synchronized (monitor()) {
            check_orphaned();
            CTTextboxTightWrap target = get_store().find_element_user(PROPERTY_QNAME[29], 0);
            cTTextboxTightWrap = target == null ? null : target;
        }
        return cTTextboxTightWrap;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetTextboxTightWrap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[29]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setTextboxTightWrap(CTTextboxTightWrap textboxTightWrap) {
        generatedSetterHelperImpl(textboxTightWrap, PROPERTY_QNAME[29], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTTextboxTightWrap addNewTextboxTightWrap() {
        CTTextboxTightWrap target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetTextboxTightWrap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTDecimalNumber getOutlineLvl() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[30], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetOutlineLvl() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[30]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setOutlineLvl(CTDecimalNumber outlineLvl) {
        generatedSetterHelperImpl(outlineLvl, PROPERTY_QNAME[30], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTDecimalNumber addNewOutlineLvl() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetOutlineLvl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTDecimalNumber getDivId() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[31], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetDivId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[31]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setDivId(CTDecimalNumber divId) {
        generatedSetterHelperImpl(divId, PROPERTY_QNAME[31], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTDecimalNumber addNewDivId() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetDivId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTCnf getCnfStyle() {
        CTCnf cTCnf;
        synchronized (monitor()) {
            check_orphaned();
            CTCnf target = (CTCnf) get_store().find_element_user(PROPERTY_QNAME[32], 0);
            cTCnf = target == null ? null : target;
        }
        return cTCnf;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public boolean isSetCnfStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[32]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void setCnfStyle(CTCnf cnfStyle) {
        generatedSetterHelperImpl(cnfStyle, PROPERTY_QNAME[32], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public CTCnf addNewCnfStyle() {
        CTCnf target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCnf) get_store().add_element_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrBase
    public void unsetCnfStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], 0);
        }
    }
}
