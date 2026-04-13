package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBarType;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrBarType;

/* loaded from: classes11.dex */
public class CTErrBarTypeImpl extends XmlComplexContentImpl implements CTErrBarType {
    private static final QName[] PROPERTY_QNAME = {new QName("", "val")};
    private static final long serialVersionUID = 1;

    public CTErrBarTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBarType
    public STErrBarType.Enum getVal() {
        STErrBarType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
            r1 = target == null ? null : (STErrBarType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBarType
    public STErrBarType xgetVal() {
        STErrBarType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STErrBarType) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STErrBarType) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBarType
    public boolean isSetVal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBarType
    public void setVal(STErrBarType.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBarType
    public void xsetVal(STErrBarType val) {
        synchronized (monitor()) {
            check_orphaned();
            STErrBarType target = (STErrBarType) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STErrBarType) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBarType
    public void unsetVal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
