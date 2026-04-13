package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMCS;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTSpacingRule;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTUnSignedInteger;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTYAlign;

/* loaded from: classes11.dex */
public class CTMPrImpl extends XmlComplexContentImpl implements CTMPr {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "baseJc"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "plcHide"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "rSpRule"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "cGpRule"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "rSp"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "cSp"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "cGp"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "mcs"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "ctrlPr")};
    private static final long serialVersionUID = 1;

    public CTMPrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTYAlign getBaseJc() {
        CTYAlign cTYAlign;
        synchronized (monitor()) {
            check_orphaned();
            CTYAlign target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTYAlign = target == null ? null : target;
        }
        return cTYAlign;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public boolean isSetBaseJc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void setBaseJc(CTYAlign baseJc) {
        generatedSetterHelperImpl(baseJc, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTYAlign addNewBaseJc() {
        CTYAlign target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void unsetBaseJc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTOnOff getPlcHide() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public boolean isSetPlcHide() {
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

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void setPlcHide(CTOnOff plcHide) {
        generatedSetterHelperImpl(plcHide, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTOnOff addNewPlcHide() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void unsetPlcHide() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTSpacingRule getRSpRule() {
        CTSpacingRule cTSpacingRule;
        synchronized (monitor()) {
            check_orphaned();
            CTSpacingRule target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTSpacingRule = target == null ? null : target;
        }
        return cTSpacingRule;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public boolean isSetRSpRule() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void setRSpRule(CTSpacingRule rSpRule) {
        generatedSetterHelperImpl(rSpRule, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTSpacingRule addNewRSpRule() {
        CTSpacingRule target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void unsetRSpRule() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTSpacingRule getCGpRule() {
        CTSpacingRule cTSpacingRule;
        synchronized (monitor()) {
            check_orphaned();
            CTSpacingRule target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTSpacingRule = target == null ? null : target;
        }
        return cTSpacingRule;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public boolean isSetCGpRule() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void setCGpRule(CTSpacingRule cGpRule) {
        generatedSetterHelperImpl(cGpRule, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTSpacingRule addNewCGpRule() {
        CTSpacingRule target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void unsetCGpRule() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTUnSignedInteger getRSp() {
        CTUnSignedInteger cTUnSignedInteger;
        synchronized (monitor()) {
            check_orphaned();
            CTUnSignedInteger target = get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTUnSignedInteger = target == null ? null : target;
        }
        return cTUnSignedInteger;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public boolean isSetRSp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void setRSp(CTUnSignedInteger rSp) {
        generatedSetterHelperImpl(rSp, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTUnSignedInteger addNewRSp() {
        CTUnSignedInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void unsetRSp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTUnSignedInteger getCSp() {
        CTUnSignedInteger cTUnSignedInteger;
        synchronized (monitor()) {
            check_orphaned();
            CTUnSignedInteger target = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTUnSignedInteger = target == null ? null : target;
        }
        return cTUnSignedInteger;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public boolean isSetCSp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void setCSp(CTUnSignedInteger cSp) {
        generatedSetterHelperImpl(cSp, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTUnSignedInteger addNewCSp() {
        CTUnSignedInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void unsetCSp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTUnSignedInteger getCGp() {
        CTUnSignedInteger cTUnSignedInteger;
        synchronized (monitor()) {
            check_orphaned();
            CTUnSignedInteger target = get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTUnSignedInteger = target == null ? null : target;
        }
        return cTUnSignedInteger;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public boolean isSetCGp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void setCGp(CTUnSignedInteger cGp) {
        generatedSetterHelperImpl(cGp, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTUnSignedInteger addNewCGp() {
        CTUnSignedInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void unsetCGp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTMCS getMcs() {
        CTMCS ctmcs;
        synchronized (monitor()) {
            check_orphaned();
            CTMCS target = (CTMCS) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            ctmcs = target == null ? null : target;
        }
        return ctmcs;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public boolean isSetMcs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void setMcs(CTMCS mcs) {
        generatedSetterHelperImpl(mcs, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTMCS addNewMcs() {
        CTMCS target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMCS) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void unsetMcs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTCtrlPr getCtrlPr() {
        CTCtrlPr cTCtrlPr;
        synchronized (monitor()) {
            check_orphaned();
            CTCtrlPr target = (CTCtrlPr) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTCtrlPr = target == null ? null : target;
        }
        return cTCtrlPr;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public boolean isSetCtrlPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void setCtrlPr(CTCtrlPr ctrlPr) {
        generatedSetterHelperImpl(ctrlPr, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public CTCtrlPr addNewCtrlPr() {
        CTCtrlPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCtrlPr) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTMPr
    public void unsetCtrlPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }
}
