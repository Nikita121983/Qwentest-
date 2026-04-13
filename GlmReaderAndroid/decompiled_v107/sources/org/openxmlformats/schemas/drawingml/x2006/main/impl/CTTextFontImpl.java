package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.STPitchFamily;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextTypeface;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STPanose;

/* loaded from: classes11.dex */
public class CTTextFontImpl extends XmlComplexContentImpl implements CTTextFont {
    private static final QName[] PROPERTY_QNAME = {new QName("", "typeface"), new QName("", "panose"), new QName("", "pitchFamily"), new QName("", "charset")};
    private static final long serialVersionUID = 1;

    public CTTextFontImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public String getTypeface() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public STTextTypeface xgetTypeface() {
        STTextTypeface target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextTypeface) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public void setTypeface(String typeface) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(typeface);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public void xsetTypeface(STTextTypeface typeface) {
        synchronized (monitor()) {
            check_orphaned();
            STTextTypeface target = (STTextTypeface) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STTextTypeface) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(typeface);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public byte[] getPanose() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public STPanose xgetPanose() {
        STPanose target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPanose) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public boolean isSetPanose() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public void setPanose(byte[] panose) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setByteArrayValue(panose);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public void xsetPanose(STPanose panose) {
        synchronized (monitor()) {
            check_orphaned();
            STPanose target = (STPanose) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STPanose) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(panose);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public void unsetPanose() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public byte getPitchFamily() {
        byte byteValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
            byteValue = target == null ? (byte) 0 : target.getByteValue();
        }
        return byteValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public STPitchFamily xgetPitchFamily() {
        STPitchFamily target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPitchFamily) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STPitchFamily) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public boolean isSetPitchFamily() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public void setPitchFamily(byte pitchFamily) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setByteValue(pitchFamily);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public void xsetPitchFamily(STPitchFamily pitchFamily) {
        synchronized (monitor()) {
            check_orphaned();
            STPitchFamily target = (STPitchFamily) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STPitchFamily) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(pitchFamily);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public void unsetPitchFamily() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public byte getCharset() {
        byte byteValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
            byteValue = target == null ? (byte) 0 : target.getByteValue();
        }
        return byteValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public XmlByte xgetCharset() {
        XmlByte target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlByte) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlByte) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public boolean isSetCharset() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public void setCharset(byte charset) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setByteValue(charset);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public void xsetCharset(XmlByte charset) {
        synchronized (monitor()) {
            check_orphaned();
            XmlByte target = (XmlByte) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlByte) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(charset);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont
    public void unsetCharset() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }
}
