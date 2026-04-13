package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAutonumberScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextBulletStartAtNum;

/* loaded from: classes11.dex */
public class CTTextAutonumberBulletImpl extends XmlComplexContentImpl implements CTTextAutonumberBullet {
    private static final QName[] PROPERTY_QNAME = {new QName("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY), new QName("", "startAt")};
    private static final long serialVersionUID = 1;

    public CTTextAutonumberBulletImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet
    public STTextAutonumberScheme.Enum getType() {
        STTextAutonumberScheme.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STTextAutonumberScheme.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet
    public STTextAutonumberScheme xgetType() {
        STTextAutonumberScheme target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextAutonumberScheme) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet
    public void setType(STTextAutonumberScheme.Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet
    public void xsetType(STTextAutonumberScheme type) {
        synchronized (monitor()) {
            check_orphaned();
            STTextAutonumberScheme target = (STTextAutonumberScheme) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STTextAutonumberScheme) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(type);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet
    public int getStartAt() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet
    public STTextBulletStartAtNum xgetStartAt() {
        STTextBulletStartAtNum target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextBulletStartAtNum) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STTextBulletStartAtNum) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet
    public boolean isSetStartAt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet
    public void setStartAt(int startAt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setIntValue(startAt);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet
    public void xsetStartAt(STTextBulletStartAtNum startAt) {
        synchronized (monitor()) {
            check_orphaned();
            STTextBulletStartAtNum target = (STTextBulletStartAtNum) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STTextBulletStartAtNum) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(startAt);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet
    public void unsetStartAt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
