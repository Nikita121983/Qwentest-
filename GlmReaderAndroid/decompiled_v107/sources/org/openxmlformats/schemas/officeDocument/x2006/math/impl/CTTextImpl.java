package org.openxmlformats.schemas.officeDocument.x2006.math.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTText;

/* loaded from: classes11.dex */
public class CTTextImpl extends JavaStringHolderEx implements CTText {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/XML/1998/namespace", "space")};
    private static final long serialVersionUID = 1;

    public CTTextImpl(SchemaType sType) {
        super(sType, true);
    }

    protected CTTextImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTText
    public SpaceAttribute.Space.Enum getSpace() {
        SpaceAttribute.Space.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (SpaceAttribute.Space.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTText
    public SpaceAttribute.Space xgetSpace() {
        SpaceAttribute.Space target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SpaceAttribute.Space) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTText
    public boolean isSetSpace() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTText
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

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTText
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

    @Override // org.openxmlformats.schemas.officeDocument.x2006.math.CTText
    public void unsetSpace() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
