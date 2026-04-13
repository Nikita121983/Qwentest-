package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTInteger255;
import org.openxmlformats.schemas.officeDocument.x2006.math.STInteger255;

/* loaded from: classes11.dex */
public class CTInteger255Impl extends XmlComplexContentImpl implements CTInteger255 {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "val")};
    private static final long serialVersionUID = 1;

    public CTInteger255Impl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTInteger255
    public int getVal() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTInteger255
    public STInteger255 xgetVal() {
        STInteger255 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STInteger255) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTInteger255
    public void setVal(int val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setIntValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTInteger255
    public void xsetVal(STInteger255 val) {
        synchronized (monitor()) {
            check_orphaned();
            STInteger255 target = (STInteger255) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STInteger255) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }
}
