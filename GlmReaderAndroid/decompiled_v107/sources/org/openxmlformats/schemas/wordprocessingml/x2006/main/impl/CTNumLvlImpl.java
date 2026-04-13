package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;

/* loaded from: classes12.dex */
public class CTNumLvlImpl extends XmlComplexContentImpl implements CTNumLvl {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "startOverride"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "lvl"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "ilvl")};
    private static final long serialVersionUID = 1;

    public CTNumLvlImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl
    public CTDecimalNumber getStartOverride() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            CTDecimalNumber target = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTDecimalNumber = target == null ? null : target;
        }
        return cTDecimalNumber;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl
    public boolean isSetStartOverride() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl
    public void setStartOverride(CTDecimalNumber startOverride) {
        generatedSetterHelperImpl(startOverride, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl
    public CTDecimalNumber addNewStartOverride() {
        CTDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl
    public void unsetStartOverride() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl
    public CTLvl getLvl() {
        CTLvl cTLvl;
        synchronized (monitor()) {
            check_orphaned();
            CTLvl target = (CTLvl) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTLvl = target == null ? null : target;
        }
        return cTLvl;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl
    public boolean isSetLvl() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl
    public void setLvl(CTLvl lvl) {
        generatedSetterHelperImpl(lvl, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl
    public CTLvl addNewLvl() {
        CTLvl target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLvl) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl
    public void unsetLvl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl
    public BigInteger getIlvl() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl
    public STDecimalNumber xgetIlvl() {
        STDecimalNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl
    public void setIlvl(BigInteger ilvl) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setBigIntegerValue(ilvl);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl
    public void xsetIlvl(STDecimalNumber ilvl) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumber target = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STDecimalNumber) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(ilvl);
        }
    }
}
