package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;

/* loaded from: classes11.dex */
public class CTLineEndPropertiesImpl extends XmlComplexContentImpl implements CTLineEndProperties {
    private static final QName[] PROPERTY_QNAME = {new QName("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY), new QName("", "w"), new QName("", "len")};
    private static final long serialVersionUID = 1;

    public CTLineEndPropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public STLineEndType.Enum getType() {
        STLineEndType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
            r1 = target == null ? null : (STLineEndType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public STLineEndType xgetType() {
        STLineEndType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLineEndType) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STLineEndType) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public void setType(STLineEndType.Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public void xsetType(STLineEndType type) {
        synchronized (monitor()) {
            check_orphaned();
            STLineEndType target = (STLineEndType) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STLineEndType) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(type);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public STLineEndWidth.Enum getW() {
        STLineEndWidth.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            r1 = target == null ? null : (STLineEndWidth.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public STLineEndWidth xgetW() {
        STLineEndWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLineEndWidth) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public boolean isSetW() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public void setW(STLineEndWidth.Enum w) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(w);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public void xsetW(STLineEndWidth w) {
        synchronized (monitor()) {
            check_orphaned();
            STLineEndWidth target = (STLineEndWidth) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STLineEndWidth) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(w);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public void unsetW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public STLineEndLength.Enum getLen() {
        STLineEndLength.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            r1 = target == null ? null : (STLineEndLength.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public STLineEndLength xgetLen() {
        STLineEndLength target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLineEndLength) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public boolean isSetLen() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public void setLen(STLineEndLength.Enum len) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setEnumValue(len);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public void xsetLen(STLineEndLength len) {
        synchronized (monitor()) {
            check_orphaned();
            STLineEndLength target = (STLineEndLength) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STLineEndLength) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(len);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties
    public void unsetLen() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }
}
