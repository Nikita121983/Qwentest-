package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrDefault;

/* loaded from: classes12.dex */
public class CTDocDefaultsImpl extends XmlComplexContentImpl implements CTDocDefaults {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "rPrDefault"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "pPrDefault")};
    private static final long serialVersionUID = 1;

    public CTDocDefaultsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults
    public CTRPrDefault getRPrDefault() {
        CTRPrDefault cTRPrDefault;
        synchronized (monitor()) {
            check_orphaned();
            CTRPrDefault target = (CTRPrDefault) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTRPrDefault = target == null ? null : target;
        }
        return cTRPrDefault;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults
    public boolean isSetRPrDefault() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults
    public void setRPrDefault(CTRPrDefault rPrDefault) {
        generatedSetterHelperImpl(rPrDefault, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults
    public CTRPrDefault addNewRPrDefault() {
        CTRPrDefault target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRPrDefault) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults
    public void unsetRPrDefault() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults
    public CTPPrDefault getPPrDefault() {
        CTPPrDefault cTPPrDefault;
        synchronized (monitor()) {
            check_orphaned();
            CTPPrDefault target = (CTPPrDefault) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTPPrDefault = target == null ? null : target;
        }
        return cTPPrDefault;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults
    public boolean isSetPPrDefault() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults
    public void setPPrDefault(CTPPrDefault pPrDefault) {
        generatedSetterHelperImpl(pPrDefault, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults
    public CTPPrDefault addNewPPrDefault() {
        CTPPrDefault target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPPrDefault) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults
    public void unsetPPrDefault() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
