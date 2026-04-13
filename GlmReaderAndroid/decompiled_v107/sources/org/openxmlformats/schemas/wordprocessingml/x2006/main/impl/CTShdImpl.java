package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber;

/* loaded from: classes12.dex */
public class CTShdImpl extends XmlComplexContentImpl implements CTShd {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "val"), new QName(XSSFRelation.NS_WORDPROCESSINGML, TypedValues.Custom.S_COLOR), new QName(XSSFRelation.NS_WORDPROCESSINGML, "themeColor"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "themeTint"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "themeShade"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "fill"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "themeFill"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "themeFillTint"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "themeFillShade")};
    private static final long serialVersionUID = 1;

    public CTShdImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public STShd.Enum getVal() {
        STShd.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STShd.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public STShd xgetVal() {
        STShd target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STShd) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void setVal(STShd.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void xsetVal(STShd val) {
        synchronized (monitor()) {
            check_orphaned();
            STShd target = (STShd) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STShd) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public Object getColor() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public STHexColor xgetColor() {
        STHexColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STHexColor) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void unsetColor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public STThemeColor.Enum getThemeColor() {
        STThemeColor.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            r1 = target == null ? null : (STThemeColor.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public STThemeColor xgetThemeColor() {
        STThemeColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STThemeColor) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public boolean isSetThemeColor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void unsetThemeColor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public byte[] getThemeTint() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public STUcharHexNumber xgetThemeTint() {
        STUcharHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUcharHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public boolean isSetThemeTint() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void unsetThemeTint() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public byte[] getThemeShade() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public STUcharHexNumber xgetThemeShade() {
        STUcharHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUcharHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public boolean isSetThemeShade() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void unsetThemeShade() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public Object getFill() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public STHexColor xgetFill() {
        STHexColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STHexColor) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public boolean isSetFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void setFill(Object fill) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setObjectValue(fill);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void xsetFill(STHexColor fill) {
        synchronized (monitor()) {
            check_orphaned();
            STHexColor target = (STHexColor) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STHexColor) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(fill);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void unsetFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public STThemeColor.Enum getThemeFill() {
        STThemeColor.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            r1 = target == null ? null : (STThemeColor.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public STThemeColor xgetThemeFill() {
        STThemeColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STThemeColor) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public boolean isSetThemeFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void setThemeFill(STThemeColor.Enum themeFill) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setEnumValue(themeFill);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void xsetThemeFill(STThemeColor themeFill) {
        synchronized (monitor()) {
            check_orphaned();
            STThemeColor target = (STThemeColor) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STThemeColor) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(themeFill);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void unsetThemeFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public byte[] getThemeFillTint() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public STUcharHexNumber xgetThemeFillTint() {
        STUcharHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUcharHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public boolean isSetThemeFillTint() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void setThemeFillTint(byte[] themeFillTint) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setByteArrayValue(themeFillTint);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void xsetThemeFillTint(STUcharHexNumber themeFillTint) {
        synchronized (monitor()) {
            check_orphaned();
            STUcharHexNumber target = (STUcharHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STUcharHexNumber) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(themeFillTint);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void unsetThemeFillTint() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public byte[] getThemeFillShade() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public STUcharHexNumber xgetThemeFillShade() {
        STUcharHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUcharHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public boolean isSetThemeFillShade() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void setThemeFillShade(byte[] themeFillShade) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setByteArrayValue(themeFillShade);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void xsetThemeFillShade(STUcharHexNumber themeFillShade) {
        synchronized (monitor()) {
            check_orphaned();
            STUcharHexNumber target = (STUcharHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STUcharHexNumber) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(themeFillShade);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd
    public void unsetThemeFillShade() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }
}
