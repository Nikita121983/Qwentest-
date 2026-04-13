package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerSize;
import org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerSize;

/* loaded from: classes11.dex */
public class CTMarkerSizeImpl extends XmlComplexContentImpl implements CTMarkerSize {
    private static final QName[] PROPERTY_QNAME = {new QName("", "val")};
    private static final long serialVersionUID = 1;

    public CTMarkerSizeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerSize
    public short getVal() {
        short s;
        synchronized (monitor()) {
            check_orphaned();
            s = 0;
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
            if (target != null) {
                s = target.getShortValue();
            }
        }
        return s;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerSize
    public STMarkerSize xgetVal() {
        STMarkerSize target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STMarkerSize) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerSize
    public boolean isSetVal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerSize
    public void setVal(short val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setShortValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerSize
    public void xsetVal(STMarkerSize val) {
        synchronized (monitor()) {
            check_orphaned();
            STMarkerSize target = get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STMarkerSize) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerSize
    public void unsetVal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
