package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTXAlign;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXAlign;

/* loaded from: classes11.dex */
public class CTXAlignImpl extends XmlComplexContentImpl implements CTXAlign {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "val")};
    private static final long serialVersionUID = 1;

    public CTXAlignImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTXAlign
    public STXAlign.Enum getVal() {
        STXAlign.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STXAlign.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTXAlign
    public STXAlign xgetVal() {
        STXAlign target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXAlign) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTXAlign
    public void setVal(STXAlign.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTXAlign
    public void xsetVal(STXAlign val) {
        synchronized (monitor()) {
            check_orphaned();
            STXAlign target = (STXAlign) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STXAlign) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }
}
