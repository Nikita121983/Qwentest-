package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate;

/* loaded from: classes11.dex */
public class CTPositiveSize2DImpl extends XmlComplexContentImpl implements CTPositiveSize2D {
    private static final QName[] PROPERTY_QNAME = {new QName("", "cx"), new QName("", "cy")};
    private static final long serialVersionUID = 1;

    public CTPositiveSize2DImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D
    public long getCx() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D
    public STPositiveCoordinate xgetCx() {
        STPositiveCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D
    public void setCx(long cx) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setLongValue(cx);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D
    public void xsetCx(STPositiveCoordinate cx) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveCoordinate target = (STPositiveCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STPositiveCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(cx);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D
    public long getCy() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D
    public STPositiveCoordinate xgetCy() {
        STPositiveCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositiveCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D
    public void setCy(long cy) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setLongValue(cy);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D
    public void xsetCy(STPositiveCoordinate cy) {
        synchronized (monitor()) {
            check_orphaned();
            STPositiveCoordinate target = (STPositiveCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STPositiveCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(cy);
        }
    }
}
