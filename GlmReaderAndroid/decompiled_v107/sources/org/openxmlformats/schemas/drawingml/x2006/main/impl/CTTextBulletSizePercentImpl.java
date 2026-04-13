package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePercent;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextBulletSizePercent;

/* loaded from: classes11.dex */
public class CTTextBulletSizePercentImpl extends XmlComplexContentImpl implements CTTextBulletSizePercent {
    private static final QName[] PROPERTY_QNAME = {new QName("", "val")};
    private static final long serialVersionUID = 1;

    public CTTextBulletSizePercentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePercent
    public String getVal() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePercent
    public STTextBulletSizePercent xgetVal() {
        STTextBulletSizePercent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextBulletSizePercent) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePercent
    public void setVal(String val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePercent
    public void xsetVal(STTextBulletSizePercent val) {
        synchronized (monitor()) {
            check_orphaned();
            STTextBulletSizePercent target = (STTextBulletSizePercent) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STTextBulletSizePercent) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }
}
