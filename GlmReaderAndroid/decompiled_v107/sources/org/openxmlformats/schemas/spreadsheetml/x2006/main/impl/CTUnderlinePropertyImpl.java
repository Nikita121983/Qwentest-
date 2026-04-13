package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTUnderlineProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnderlineValues;

/* loaded from: classes12.dex */
public class CTUnderlinePropertyImpl extends XmlComplexContentImpl implements CTUnderlineProperty {
    private static final QName[] PROPERTY_QNAME = {new QName("", "val")};
    private static final long serialVersionUID = 1;

    public CTUnderlinePropertyImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTUnderlineProperty
    public STUnderlineValues.Enum getVal() {
        STUnderlineValues.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
            r1 = target == null ? null : (STUnderlineValues.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTUnderlineProperty
    public STUnderlineValues xgetVal() {
        STUnderlineValues target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUnderlineValues) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STUnderlineValues) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTUnderlineProperty
    public boolean isSetVal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTUnderlineProperty
    public void setVal(STUnderlineValues.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTUnderlineProperty
    public void xsetVal(STUnderlineValues val) {
        synchronized (monitor()) {
            check_orphaned();
            STUnderlineValues target = (STUnderlineValues) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STUnderlineValues) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTUnderlineProperty
    public void unsetVal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
