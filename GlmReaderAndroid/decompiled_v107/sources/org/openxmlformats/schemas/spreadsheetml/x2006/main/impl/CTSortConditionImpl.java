package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDxfId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSortBy;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSortBy$Enum;

/* loaded from: classes12.dex */
public class CTSortConditionImpl extends XmlComplexContentImpl implements CTSortCondition {
    private static final QName[] PROPERTY_QNAME = {new QName("", "descending"), new QName("", "sortBy"), new QName("", "ref"), new QName("", "customList"), new QName("", "dxfId"), new QName("", "iconSet"), new QName("", "iconId")};
    private static final long serialVersionUID = 1;

    public CTSortConditionImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public boolean getDescending() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
            if (target != null) {
                z = target.getBooleanValue();
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public XmlBoolean xgetDescending() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public boolean isSetDescending() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void setDescending(boolean descending) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setBooleanValue(descending);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void xsetDescending(XmlBoolean descending) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(descending);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void unsetDescending() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public STSortBy$Enum getSortBy() {
        STSortBy$Enum sTSortBy$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
            sTSortBy$Enum = target == null ? null : (STSortBy$Enum) target.getEnumValue();
        }
        return sTSortBy$Enum;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public STSortBy xgetSortBy() {
        STSortBy target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STSortBy) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public boolean isSetSortBy() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void setSortBy(STSortBy$Enum sortBy) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(sortBy);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void xsetSortBy(STSortBy sortBy) {
        synchronized (monitor()) {
            check_orphaned();
            STSortBy target = get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STSortBy) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(sortBy);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void unsetSortBy() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public String getRef() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public STRef xgetRef() {
        STRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRef) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void setRef(String ref) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(ref);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void xsetRef(STRef ref) {
        synchronized (monitor()) {
            check_orphaned();
            STRef target = (STRef) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STRef) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(ref);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public String getCustomList() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public STXstring xgetCustomList() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public boolean isSetCustomList() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void setCustomList(String customList) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(customList);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void xsetCustomList(STXstring customList) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(customList);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void unsetCustomList() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public long getDxfId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public STDxfId xgetDxfId() {
        STDxfId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDxfId) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public boolean isSetDxfId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void setDxfId(long dxfId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setLongValue(dxfId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void xsetDxfId(STDxfId dxfId) {
        synchronized (monitor()) {
            check_orphaned();
            STDxfId target = (STDxfId) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STDxfId) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(dxfId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void unsetDxfId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public STIconSetType.Enum getIconSet() {
        STIconSetType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
            r1 = target == null ? null : (STIconSetType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public STIconSetType xgetIconSet() {
        STIconSetType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STIconSetType) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STIconSetType) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public boolean isSetIconSet() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void setIconSet(STIconSetType.Enum iconSet) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setEnumValue(iconSet);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void xsetIconSet(STIconSetType iconSet) {
        synchronized (monitor()) {
            check_orphaned();
            STIconSetType target = (STIconSetType) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STIconSetType) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(iconSet);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void unsetIconSet() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public long getIconId() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public XmlUnsignedInt xgetIconId() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public boolean isSetIconId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void setIconId(long iconId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setLongValue(iconId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void xsetIconId(XmlUnsignedInt iconId) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(iconId);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortCondition
    public void unsetIconId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }
}
