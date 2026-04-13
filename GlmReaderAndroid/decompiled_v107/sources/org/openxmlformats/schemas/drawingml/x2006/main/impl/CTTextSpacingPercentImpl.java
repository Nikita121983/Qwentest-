package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextSpacingPercentOrPercentString;

/* loaded from: classes11.dex */
public class CTTextSpacingPercentImpl extends XmlComplexContentImpl implements CTTextSpacingPercent {
    private static final QName[] PROPERTY_QNAME = {new QName("", "val")};
    private static final long serialVersionUID = 1;

    public CTTextSpacingPercentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent
    public Object getVal() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent
    public STTextSpacingPercentOrPercentString xgetVal() {
        STTextSpacingPercentOrPercentString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextSpacingPercentOrPercentString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent
    public void setVal(Object val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent
    public void xsetVal(STTextSpacingPercentOrPercentString val) {
        synchronized (monitor()) {
            check_orphaned();
            STTextSpacingPercentOrPercentString target = (STTextSpacingPercentOrPercentString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STTextSpacingPercentOrPercentString) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }
}
