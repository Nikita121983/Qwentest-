package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRgbColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnsignedIntHex;

/* loaded from: classes12.dex */
public class CTRgbColorImpl extends XmlComplexContentImpl implements CTRgbColor {
    private static final QName[] PROPERTY_QNAME = {new QName("", "rgb")};
    private static final long serialVersionUID = 1;

    public CTRgbColorImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRgbColor
    public byte[] getRgb() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRgbColor
    public STUnsignedIntHex xgetRgb() {
        STUnsignedIntHex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUnsignedIntHex) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRgbColor
    public boolean isSetRgb() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRgbColor
    public void setRgb(byte[] rgb) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setByteArrayValue(rgb);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRgbColor
    public void xsetRgb(STUnsignedIntHex rgb) {
        synchronized (monitor()) {
            check_orphaned();
            STUnsignedIntHex target = (STUnsignedIntHex) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STUnsignedIntHex) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(rgb);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRgbColor
    public void unsetRgb() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
