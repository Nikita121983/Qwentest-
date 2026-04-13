package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import javax.xml.namespace.QName;
import org.apache.poi.ss.util.CellUtil;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFontId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPhoneticAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPhoneticAlignment$Enum;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPhoneticType;

/* loaded from: classes12.dex */
public class CTPhoneticPrImpl extends XmlComplexContentImpl implements CTPhoneticPr {
    private static final QName[] PROPERTY_QNAME = {new QName("", "fontId"), new QName("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY), new QName("", CellUtil.ALIGNMENT)};
    private static final long serialVersionUID = 1;

    public CTPhoneticPrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public long getFontId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public STFontId xgetFontId() {
        STFontId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STFontId) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public void setFontId(long fontId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setLongValue(fontId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public void xsetFontId(STFontId fontId) {
        synchronized (monitor()) {
            check_orphaned();
            STFontId target = (STFontId) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STFontId) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(fontId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public STPhoneticType.Enum getType() {
        STPhoneticType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
            r1 = target == null ? null : (STPhoneticType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public STPhoneticType xgetType() {
        STPhoneticType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPhoneticType) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STPhoneticType) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public boolean isSetType() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public void setType(STPhoneticType.Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public void xsetType(STPhoneticType type) {
        synchronized (monitor()) {
            check_orphaned();
            STPhoneticType target = (STPhoneticType) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STPhoneticType) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(type);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public STPhoneticAlignment$Enum getAlignment() {
        STPhoneticAlignment$Enum sTPhoneticAlignment$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
            sTPhoneticAlignment$Enum = target == null ? null : (STPhoneticAlignment$Enum) target.getEnumValue();
        }
        return sTPhoneticAlignment$Enum;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public STPhoneticAlignment xgetAlignment() {
        STPhoneticAlignment target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STPhoneticAlignment) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public boolean isSetAlignment() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public void setAlignment(STPhoneticAlignment$Enum alignment) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setEnumValue(alignment);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public void xsetAlignment(STPhoneticAlignment alignment) {
        synchronized (monitor()) {
            check_orphaned();
            STPhoneticAlignment target = get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STPhoneticAlignment) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(alignment);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr
    public void unsetAlignment() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }
}
