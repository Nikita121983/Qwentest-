package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLang;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyAlign;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr;

/* loaded from: classes12.dex */
public class CTRubyPrImpl extends XmlComplexContentImpl implements CTRubyPr {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "rubyAlign"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hps"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hpsRaise"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hpsBaseText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lid"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "dirty")};
    private static final long serialVersionUID = 1;

    public CTRubyPrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public CTRubyAlign getRubyAlign() {
        CTRubyAlign cTRubyAlign;
        synchronized (monitor()) {
            check_orphaned();
            CTRubyAlign target = (CTRubyAlign) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTRubyAlign = target == null ? null : target;
        }
        return cTRubyAlign;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public void setRubyAlign(CTRubyAlign rubyAlign) {
        generatedSetterHelperImpl(rubyAlign, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public CTRubyAlign addNewRubyAlign() {
        CTRubyAlign target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRubyAlign) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public CTHpsMeasure getHps() {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            CTHpsMeasure target = (CTHpsMeasure) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTHpsMeasure = target == null ? null : target;
        }
        return cTHpsMeasure;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public void setHps(CTHpsMeasure hps) {
        generatedSetterHelperImpl(hps, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public CTHpsMeasure addNewHps() {
        CTHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHpsMeasure) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public CTHpsMeasure getHpsRaise() {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            CTHpsMeasure target = (CTHpsMeasure) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTHpsMeasure = target == null ? null : target;
        }
        return cTHpsMeasure;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public void setHpsRaise(CTHpsMeasure hpsRaise) {
        generatedSetterHelperImpl(hpsRaise, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public CTHpsMeasure addNewHpsRaise() {
        CTHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHpsMeasure) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public CTHpsMeasure getHpsBaseText() {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            CTHpsMeasure target = (CTHpsMeasure) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTHpsMeasure = target == null ? null : target;
        }
        return cTHpsMeasure;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public void setHpsBaseText(CTHpsMeasure hpsBaseText) {
        generatedSetterHelperImpl(hpsBaseText, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public CTHpsMeasure addNewHpsBaseText() {
        CTHpsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHpsMeasure) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public CTLang getLid() {
        CTLang cTLang;
        synchronized (monitor()) {
            check_orphaned();
            CTLang target = (CTLang) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTLang = target == null ? null : target;
        }
        return cTLang;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public void setLid(CTLang lid) {
        generatedSetterHelperImpl(lid, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public CTLang addNewLid() {
        CTLang target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLang) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public CTOnOff getDirty() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            CTOnOff target = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTOnOff = target == null ? null : target;
        }
        return cTOnOff;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public boolean isSetDirty() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public void setDirty(CTOnOff dirty) {
        generatedSetterHelperImpl(dirty, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public CTOnOff addNewDirty() {
        CTOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyPr
    public void unsetDirty() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }
}
