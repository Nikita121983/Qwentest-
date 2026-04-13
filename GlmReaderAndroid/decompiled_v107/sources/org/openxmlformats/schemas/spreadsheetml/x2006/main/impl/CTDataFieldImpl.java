package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataConsolidateFunction;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STNumFmtId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STShowDataAs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STShowDataAs$Enum;

/* loaded from: classes12.dex */
public class CTDataFieldImpl extends XmlComplexContentImpl implements CTDataField {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "extLst"), new QName("", "name"), new QName("", "fld"), new QName("", "subtotal"), new QName("", "showDataAs"), new QName("", "baseField"), new QName("", "baseItem"), new QName("", "numFmtId")};
    private static final long serialVersionUID = 1;

    public CTDataFieldImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public STXstring xgetName() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void xsetName(STXstring name) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(name);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public long getFld() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public XmlUnsignedInt xgetFld() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void setFld(long fld) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setLongValue(fld);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void xsetFld(XmlUnsignedInt fld) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(fld);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public STDataConsolidateFunction.Enum getSubtotal() {
        STDataConsolidateFunction.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
            r1 = target == null ? null : (STDataConsolidateFunction.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public STDataConsolidateFunction xgetSubtotal() {
        STDataConsolidateFunction target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDataConsolidateFunction) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STDataConsolidateFunction) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public boolean isSetSubtotal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void setSubtotal(STDataConsolidateFunction.Enum subtotal) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setEnumValue(subtotal);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void xsetSubtotal(STDataConsolidateFunction subtotal) {
        synchronized (monitor()) {
            check_orphaned();
            STDataConsolidateFunction target = (STDataConsolidateFunction) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STDataConsolidateFunction) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(subtotal);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void unsetSubtotal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public STShowDataAs$Enum getShowDataAs() {
        STShowDataAs$Enum sTShowDataAs$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
            sTShowDataAs$Enum = target == null ? null : (STShowDataAs$Enum) target.getEnumValue();
        }
        return sTShowDataAs$Enum;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public STShowDataAs xgetShowDataAs() {
        STShowDataAs target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STShowDataAs) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public boolean isSetShowDataAs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void setShowDataAs(STShowDataAs$Enum showDataAs) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setEnumValue(showDataAs);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void xsetShowDataAs(STShowDataAs showDataAs) {
        synchronized (monitor()) {
            check_orphaned();
            STShowDataAs target = get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STShowDataAs) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(showDataAs);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void unsetShowDataAs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public int getBaseField() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public XmlInt xgetBaseField() {
        XmlInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlInt) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public boolean isSetBaseField() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void setBaseField(int baseField) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setIntValue(baseField);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void xsetBaseField(XmlInt baseField) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInt target = (XmlInt) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlInt) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(baseField);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void unsetBaseField() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public long getBaseItem() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public XmlUnsignedInt xgetBaseItem() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlUnsignedInt) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public boolean isSetBaseItem() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void setBaseItem(long baseItem) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setLongValue(baseItem);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void xsetBaseItem(XmlUnsignedInt baseItem) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(baseItem);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void unsetBaseItem() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public long getNumFmtId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public STNumFmtId xgetNumFmtId() {
        STNumFmtId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STNumFmtId) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public boolean isSetNumFmtId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void setNumFmtId(long numFmtId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setLongValue(numFmtId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void xsetNumFmtId(STNumFmtId numFmtId) {
        synchronized (monitor()) {
            check_orphaned();
            STNumFmtId target = (STNumFmtId) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STNumFmtId) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(numFmtId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField
    public void unsetNumFmtId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }
}
