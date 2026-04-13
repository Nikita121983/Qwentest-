package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;

/* loaded from: classes12.dex */
public class CTUnderlineImpl extends XmlComplexContentImpl implements CTUnderline {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "val"), new QName(XSSFRelation.NS_WORDPROCESSINGML, TypedValues.Custom.S_COLOR), new QName(XSSFRelation.NS_WORDPROCESSINGML, "themeColor"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "themeTint"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "themeShade")};
    private static final long serialVersionUID = 1;

    public CTUnderlineImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public STUnderline.Enum getVal() {
        STUnderline.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STUnderline.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public STUnderline xgetVal() {
        STUnderline target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUnderline) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public boolean isSetVal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void setVal(STUnderline.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void xsetVal(STUnderline val) {
        synchronized (monitor()) {
            check_orphaned();
            STUnderline target = (STUnderline) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STUnderline) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void unsetVal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public Object getColor() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public STHexColor xgetColor() {
        STHexColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STHexColor) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STHexColor) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public boolean isSetColor() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void setColor(Object color) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setObjectValue(color);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void xsetColor(STHexColor color) {
        synchronized (monitor()) {
            check_orphaned();
            STHexColor target = (STHexColor) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STHexColor) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(color);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void unsetColor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public STThemeColor.Enum getThemeColor() {
        STThemeColor.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            r1 = target == null ? null : (STThemeColor.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public STThemeColor xgetThemeColor() {
        STThemeColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STThemeColor) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public boolean isSetThemeColor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void setThemeColor(STThemeColor.Enum themeColor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setEnumValue(themeColor);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void xsetThemeColor(STThemeColor themeColor) {
        synchronized (monitor()) {
            check_orphaned();
            STThemeColor target = (STThemeColor) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STThemeColor) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(themeColor);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void unsetThemeColor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public byte[] getThemeTint() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public STUcharHexNumber xgetThemeTint() {
        STUcharHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUcharHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public boolean isSetThemeTint() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void setThemeTint(byte[] themeTint) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setByteArrayValue(themeTint);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void xsetThemeTint(STUcharHexNumber themeTint) {
        synchronized (monitor()) {
            check_orphaned();
            STUcharHexNumber target = (STUcharHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STUcharHexNumber) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(themeTint);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void unsetThemeTint() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public byte[] getThemeShade() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public STUcharHexNumber xgetThemeShade() {
        STUcharHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUcharHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public boolean isSetThemeShade() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void setThemeShade(byte[] themeShade) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setByteArrayValue(themeShade);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void xsetThemeShade(STUcharHexNumber themeShade) {
        synchronized (monitor()) {
            check_orphaned();
            STUcharHexNumber target = (STUcharHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STUcharHexNumber) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(themeShade);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline
    public void unsetThemeShade() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
