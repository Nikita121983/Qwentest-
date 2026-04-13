package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize;
import org.openxmlformats.schemas.presentationml.x2006.main.STSlideSizeCoordinate;
import org.openxmlformats.schemas.presentationml.x2006.main.STSlideSizeType;

/* loaded from: classes11.dex */
public class CTSlideSizeImpl extends XmlComplexContentImpl implements CTSlideSize {
    private static final QName[] PROPERTY_QNAME = {new QName("", "cx"), new QName("", "cy"), new QName("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY)};
    private static final long serialVersionUID = 1;

    public CTSlideSizeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize
    public int getCx() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize
    public STSlideSizeCoordinate xgetCx() {
        STSlideSizeCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSlideSizeCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize
    public void setCx(int cx) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setIntValue(cx);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize
    public void xsetCx(STSlideSizeCoordinate cx) {
        synchronized (monitor()) {
            check_orphaned();
            STSlideSizeCoordinate target = (STSlideSizeCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STSlideSizeCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(cx);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize
    public int getCy() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize
    public STSlideSizeCoordinate xgetCy() {
        STSlideSizeCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSlideSizeCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize
    public void setCy(int cy) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setIntValue(cy);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize
    public void xsetCy(STSlideSizeCoordinate cy) {
        synchronized (monitor()) {
            check_orphaned();
            STSlideSizeCoordinate target = (STSlideSizeCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STSlideSizeCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(cy);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize
    public STSlideSizeType.Enum getType() {
        STSlideSizeType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
            r1 = target == null ? null : (STSlideSizeType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize
    public STSlideSizeType xgetType() {
        STSlideSizeType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSlideSizeType) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STSlideSizeType) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize
    public void setType(STSlideSizeType.Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize
    public void xsetType(STSlideSizeType type) {
        synchronized (monitor()) {
            check_orphaned();
            STSlideSizeType target = (STSlideSizeType) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STSlideSizeType) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(type);
        }
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }
}
