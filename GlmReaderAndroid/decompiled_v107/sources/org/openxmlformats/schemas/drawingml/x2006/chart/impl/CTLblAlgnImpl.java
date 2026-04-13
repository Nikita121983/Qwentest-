package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLblAlgn;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLblAlgn;

/* loaded from: classes11.dex */
public class CTLblAlgnImpl extends XmlComplexContentImpl implements CTLblAlgn {
    private static final QName[] PROPERTY_QNAME = {new QName("", "val")};
    private static final long serialVersionUID = 1;

    public CTLblAlgnImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLblAlgn
    public STLblAlgn.Enum getVal() {
        STLblAlgn.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STLblAlgn.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLblAlgn
    public STLblAlgn xgetVal() {
        STLblAlgn target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLblAlgn) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLblAlgn
    public void setVal(STLblAlgn.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLblAlgn
    public void xsetVal(STLblAlgn val) {
        synchronized (monitor()) {
            check_orphaned();
            STLblAlgn target = (STLblAlgn) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STLblAlgn) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }
}
