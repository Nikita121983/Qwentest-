package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxisUnit;
import org.openxmlformats.schemas.drawingml.x2006.chart.STAxisUnit;

/* loaded from: classes11.dex */
public class CTAxisUnitImpl extends XmlComplexContentImpl implements CTAxisUnit {
    private static final QName[] PROPERTY_QNAME = {new QName("", "val")};
    private static final long serialVersionUID = 1;

    public CTAxisUnitImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxisUnit
    public double getVal() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxisUnit
    public STAxisUnit xgetVal() {
        STAxisUnit target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAxisUnit) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxisUnit
    public void setVal(double val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setDoubleValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTAxisUnit
    public void xsetVal(STAxisUnit val) {
        synchronized (monitor()) {
            check_orphaned();
            STAxisUnit target = (STAxisUnit) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STAxisUnit) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }
}
