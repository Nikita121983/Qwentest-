package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangeNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;

/* loaded from: classes12.dex */
public class CTFldCharImpl extends XmlComplexContentImpl implements CTFldChar {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "fldData"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "ffData"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "numberingChange"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "fldCharType"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "fldLock"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "dirty")};
    private static final long serialVersionUID = 1;

    public CTFldCharImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public CTText getFldData() {
        CTText cTText;
        synchronized (monitor()) {
            check_orphaned();
            CTText target = (CTText) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTText = target == null ? null : target;
        }
        return cTText;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public boolean isSetFldData() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public void setFldData(CTText fldData) {
        generatedSetterHelperImpl(fldData, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public CTText addNewFldData() {
        CTText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTText) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public void unsetFldData() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public CTFFData getFfData() {
        CTFFData cTFFData;
        synchronized (monitor()) {
            check_orphaned();
            CTFFData target = (CTFFData) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTFFData = target == null ? null : target;
        }
        return cTFFData;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public boolean isSetFfData() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public void setFfData(CTFFData ffData) {
        generatedSetterHelperImpl(ffData, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public CTFFData addNewFfData() {
        CTFFData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFFData) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public void unsetFfData() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public CTTrackChangeNumbering getNumberingChange() {
        CTTrackChangeNumbering cTTrackChangeNumbering;
        synchronized (monitor()) {
            check_orphaned();
            CTTrackChangeNumbering target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTTrackChangeNumbering = target == null ? null : target;
        }
        return cTTrackChangeNumbering;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public boolean isSetNumberingChange() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public void setNumberingChange(CTTrackChangeNumbering numberingChange) {
        generatedSetterHelperImpl(numberingChange, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public CTTrackChangeNumbering addNewNumberingChange() {
        CTTrackChangeNumbering target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public void unsetNumberingChange() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public STFldCharType.Enum getFldCharType() {
        STFldCharType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            r1 = target == null ? null : (STFldCharType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public STFldCharType xgetFldCharType() {
        STFldCharType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STFldCharType) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public void setFldCharType(STFldCharType.Enum fldCharType) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setEnumValue(fldCharType);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public void xsetFldCharType(STFldCharType fldCharType) {
        synchronized (monitor()) {
            check_orphaned();
            STFldCharType target = (STFldCharType) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STFldCharType) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(fldCharType);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public Object getFldLock() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public STOnOff xgetFldLock() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public boolean isSetFldLock() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public void setFldLock(Object fldLock) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setObjectValue(fldLock);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public void xsetFldLock(STOnOff fldLock) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(fldLock);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public void unsetFldLock() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public Object getDirty() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public STOnOff xgetDirty() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public boolean isSetDirty() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public void setDirty(Object dirty) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setObjectValue(dirty);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public void xsetDirty(STOnOff dirty) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(dirty);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
    public void unsetDirty() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }
}
