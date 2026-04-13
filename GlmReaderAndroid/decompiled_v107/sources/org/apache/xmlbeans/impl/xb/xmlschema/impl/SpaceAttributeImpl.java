package org.apache.xmlbeans.impl.xb.xmlschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;

/* loaded from: classes11.dex */
public class SpaceAttributeImpl extends XmlComplexContentImpl implements SpaceAttribute {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/XML/1998/namespace", "space")};
    private static final long serialVersionUID = 1;

    public SpaceAttributeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute
    public SpaceAttribute.Space.Enum getSpace() {
        SpaceAttribute.Space.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (SpaceAttribute.Space.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute
    public SpaceAttribute.Space xgetSpace() {
        SpaceAttribute.Space target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SpaceAttribute.Space) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute
    public boolean isSetSpace() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute
    public void setSpace(SpaceAttribute.Space.Enum space) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(space);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute
    public void xsetSpace(SpaceAttribute.Space space) {
        synchronized (monitor()) {
            check_orphaned();
            SpaceAttribute.Space target = (SpaceAttribute.Space) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SpaceAttribute.Space) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(space);
        }
    }

    @Override // org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute
    public void unsetSpace() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    /* loaded from: classes11.dex */
    public static class SpaceImpl extends JavaStringEnumerationHolderEx implements SpaceAttribute.Space {
        private static final long serialVersionUID = 1;

        public SpaceImpl(SchemaType sType) {
            super(sType, false);
        }

        protected SpaceImpl(SchemaType sType, boolean b) {
            super(sType, b);
        }
    }
}
