package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositivePercentage;

/* loaded from: classes11.dex */
public class CTLineJoinMiterPropertiesImpl extends XmlComplexContentImpl implements CTLineJoinMiterProperties {
    private static final QName[] PROPERTY_QNAME = {new QName("", "lim")};
    private static final long serialVersionUID = 1;

    public CTLineJoinMiterPropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties
    public Object getLim() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties
    public STPositivePercentage xgetLim() {
        STPositivePercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositivePercentage) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties
    public boolean isSetLim() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties
    public void setLim(Object lim) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(lim);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties
    public void xsetLim(STPositivePercentage lim) {
        synchronized (monitor()) {
            check_orphaned();
            STPositivePercentage target = (STPositivePercentage) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STPositivePercentage) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(lim);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties
    public void unsetLim() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
