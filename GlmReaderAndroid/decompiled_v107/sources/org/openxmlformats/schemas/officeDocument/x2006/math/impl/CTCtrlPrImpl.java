package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMathCtrlDel;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMathCtrlIns;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;

/* loaded from: classes11.dex */
public class CTCtrlPrImpl extends XmlComplexContentImpl implements CTCtrlPr {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "rPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "ins"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "del")};
    private static final long serialVersionUID = 1;

    public CTCtrlPrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public CTRPr getRPr() {
        CTRPr cTRPr;
        synchronized (monitor()) {
            check_orphaned();
            CTRPr target = (CTRPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTRPr = target == null ? null : target;
        }
        return cTRPr;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public boolean isSetRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public void setRPr(CTRPr rPr) {
        generatedSetterHelperImpl(rPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public CTRPr addNewRPr() {
        CTRPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public void unsetRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public CTMathCtrlIns getIns() {
        CTMathCtrlIns cTMathCtrlIns;
        synchronized (monitor()) {
            check_orphaned();
            CTMathCtrlIns target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTMathCtrlIns = target == null ? null : target;
        }
        return cTMathCtrlIns;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public boolean isSetIns() {
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

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public void setIns(CTMathCtrlIns ins) {
        generatedSetterHelperImpl(ins, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public CTMathCtrlIns addNewIns() {
        CTMathCtrlIns target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public void unsetIns() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public CTMathCtrlDel getDel() {
        CTMathCtrlDel cTMathCtrlDel;
        synchronized (monitor()) {
            check_orphaned();
            CTMathCtrlDel target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTMathCtrlDel = target == null ? null : target;
        }
        return cTMathCtrlDel;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public boolean isSetDel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public void setDel(CTMathCtrlDel del) {
        generatedSetterHelperImpl(del, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public CTMathCtrlDel addNewDel() {
        CTMathCtrlDel target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr
    public void unsetDel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }
}
