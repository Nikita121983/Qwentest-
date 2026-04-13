package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLogBase;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLogBase;

/* loaded from: classes11.dex */
public class CTLogBaseImpl extends XmlComplexContentImpl implements CTLogBase {
    private static final QName[] PROPERTY_QNAME = {new QName("", "val")};
    private static final long serialVersionUID = 1;

    public CTLogBaseImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLogBase
    public double getVal() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLogBase
    public STLogBase xgetVal() {
        STLogBase target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLogBase) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLogBase
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLogBase
    public void xsetVal(STLogBase val) {
        synchronized (monitor()) {
            check_orphaned();
            STLogBase target = (STLogBase) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STLogBase) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }
}
