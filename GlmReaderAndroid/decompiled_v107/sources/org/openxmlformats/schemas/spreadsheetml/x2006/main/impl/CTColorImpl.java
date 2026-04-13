package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import kotlinx.coroutines.DebugKt;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnsignedIntHex;

/* loaded from: classes12.dex */
public class CTColorImpl extends XmlComplexContentImpl implements CTColor {
    private static final QName[] PROPERTY_QNAME = {new QName("", DebugKt.DEBUG_PROPERTY_VALUE_AUTO), new QName("", "indexed"), new QName("", "rgb"), new QName("", "theme"), new QName("", "tint")};
    private static final long serialVersionUID = 1;

    public CTColorImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public boolean getAuto() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target != null) {
                z = target.getBooleanValue();
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public XmlBoolean xgetAuto() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public boolean isSetAuto() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void setAuto(boolean auto) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setBooleanValue(auto);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void xsetAuto(XmlBoolean auto) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(auto);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void unsetAuto() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public long getIndexed() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public XmlUnsignedInt xgetIndexed() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public boolean isSetIndexed() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void setIndexed(long indexed) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setLongValue(indexed);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void xsetIndexed(XmlUnsignedInt indexed) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(indexed);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void unsetIndexed() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public byte[] getRgb() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public STUnsignedIntHex xgetRgb() {
        STUnsignedIntHex target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUnsignedIntHex) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public boolean isSetRgb() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void setRgb(byte[] rgb) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setByteArrayValue(rgb);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void xsetRgb(STUnsignedIntHex rgb) {
        synchronized (monitor()) {
            check_orphaned();
            STUnsignedIntHex target = (STUnsignedIntHex) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STUnsignedIntHex) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(rgb);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void unsetRgb() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public long getTheme() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public XmlUnsignedInt xgetTheme() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public boolean isSetTheme() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void setTheme(long theme) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setLongValue(theme);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void xsetTheme(XmlUnsignedInt theme) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(theme);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void unsetTheme() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public double getTint() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public XmlDouble xgetTint() {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlDouble) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public boolean isSetTint() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void setTint(double tint) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setDoubleValue(tint);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void xsetTint(XmlDouble tint) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDouble target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlDouble) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(tint);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor
    public void unsetTint() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
