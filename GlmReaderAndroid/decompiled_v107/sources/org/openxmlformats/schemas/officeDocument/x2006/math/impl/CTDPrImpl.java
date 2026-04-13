package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTChar;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTShp;

/* loaded from: classes11.dex */
public class CTDPrImpl extends XmlComplexContentImpl implements CTDPr {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "begChr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "sepChr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "endChr"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "grow"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "shp"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "ctrlPr")};
    private static final long serialVersionUID = 1;

    public CTDPrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public CTChar getBegChr() {
        CTChar cTChar;
        synchronized (monitor()) {
            check_orphaned();
            CTChar target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTChar = target == null ? null : target;
        }
        return cTChar;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public boolean isSetBegChr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public void setBegChr(CTChar begChr) {
        generatedSetterHelperImpl(begChr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public CTChar addNewBegChr() {
        CTChar target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public void unsetBegChr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public CTChar getSepChr() {
        CTChar cTChar;
        synchronized (monitor()) {
            check_orphaned();
            CTChar target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTChar = target == null ? null : target;
        }
        return cTChar;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public boolean isSetSepChr() {
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

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public void setSepChr(CTChar sepChr) {
        generatedSetterHelperImpl(sepChr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public CTChar addNewSepChr() {
        CTChar target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public void unsetSepChr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public CTChar getEndChr() {
        CTChar cTChar;
        synchronized (monitor()) {
            check_orphaned();
            CTChar target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTChar = target == null ? null : target;
        }
        return cTChar;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public boolean isSetEndChr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public void setEndChr(CTChar endChr) {
        generatedSetterHelperImpl(endChr, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public CTChar addNewEndChr() {
        CTChar target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public void unsetEndChr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public CTOnOff getGrow() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public boolean isSetGrow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public void setGrow(CTOnOff grow) {
        generatedSetterHelperImpl(grow, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public CTOnOff addNewGrow() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public void unsetGrow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public CTShp getShp() {
        CTShp cTShp;
        synchronized (monitor()) {
            check_orphaned();
            CTShp target = get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTShp = target == null ? null : target;
        }
        return cTShp;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public boolean isSetShp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public void setShp(CTShp shp) {
        generatedSetterHelperImpl(shp, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public CTShp addNewShp() {
        CTShp target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public void unsetShp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public CTCtrlPr getCtrlPr() {
        CTCtrlPr cTCtrlPr;
        synchronized (monitor()) {
            check_orphaned();
            CTCtrlPr target = (CTCtrlPr) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTCtrlPr = target == null ? null : target;
        }
        return cTCtrlPr;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public boolean isSetCtrlPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public void setCtrlPr(CTCtrlPr ctrlPr) {
        generatedSetterHelperImpl(ctrlPr, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public CTCtrlPr addNewCtrlPr() {
        CTCtrlPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCtrlPr) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTDPr
    public void unsetCtrlPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }
}
