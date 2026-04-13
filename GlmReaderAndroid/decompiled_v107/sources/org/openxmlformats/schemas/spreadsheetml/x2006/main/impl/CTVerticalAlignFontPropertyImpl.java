package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STVerticalAlignRun;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVerticalAlignFontProperty;

/* loaded from: classes12.dex */
public class CTVerticalAlignFontPropertyImpl extends XmlComplexContentImpl implements CTVerticalAlignFontProperty {
    private static final QName[] PROPERTY_QNAME = {new QName("", "val")};
    private static final long serialVersionUID = 1;

    public CTVerticalAlignFontPropertyImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVerticalAlignFontProperty
    public STVerticalAlignRun.Enum getVal() {
        STVerticalAlignRun.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STVerticalAlignRun.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVerticalAlignFontProperty
    public STVerticalAlignRun xgetVal() {
        STVerticalAlignRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STVerticalAlignRun) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVerticalAlignFontProperty
    public void setVal(STVerticalAlignRun.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVerticalAlignFontProperty
    public void xsetVal(STVerticalAlignRun val) {
        synchronized (monitor()) {
            check_orphaned();
            STVerticalAlignRun target = (STVerticalAlignRun) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STVerticalAlignRun) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }
}
