package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRPrElt;

/* loaded from: classes12.dex */
public class CTREltImpl extends XmlComplexContentImpl implements CTRElt {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "rPr"), new QName(XSSFRelation.NS_SPREADSHEETML, "t")};
    private static final long serialVersionUID = 1;

    public CTREltImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt
    public CTRPrElt getRPr() {
        CTRPrElt cTRPrElt;
        synchronized (monitor()) {
            check_orphaned();
            CTRPrElt target = (CTRPrElt) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTRPrElt = target == null ? null : target;
        }
        return cTRPrElt;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt
    public boolean isSetRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt
    public void setRPr(CTRPrElt rPr) {
        generatedSetterHelperImpl(rPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt
    public CTRPrElt addNewRPr() {
        CTRPrElt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRPrElt) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt
    public void unsetRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt
    public String getT() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt
    public STXstring xgetT() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt
    public void setT(String t) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(t);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt
    public void xsetT(STXstring t) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (STXstring) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(t);
        }
    }
}
