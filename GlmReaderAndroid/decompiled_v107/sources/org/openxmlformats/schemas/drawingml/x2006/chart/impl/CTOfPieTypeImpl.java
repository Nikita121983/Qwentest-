package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTOfPieType;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOfPieType;

/* loaded from: classes11.dex */
public class CTOfPieTypeImpl extends XmlComplexContentImpl implements CTOfPieType {
    private static final QName[] PROPERTY_QNAME = {new QName("", "val")};
    private static final long serialVersionUID = 1;

    public CTOfPieTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTOfPieType
    public STOfPieType.Enum getVal() {
        STOfPieType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
            r1 = target == null ? null : (STOfPieType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTOfPieType
    public STOfPieType xgetVal() {
        STOfPieType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOfPieType) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STOfPieType) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTOfPieType
    public boolean isSetVal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTOfPieType
    public void setVal(STOfPieType.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTOfPieType
    public void xsetVal(STOfPieType val) {
        synchronized (monitor()) {
            check_orphaned();
            STOfPieType target = (STOfPieType) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STOfPieType) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTOfPieType
    public void unsetVal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
