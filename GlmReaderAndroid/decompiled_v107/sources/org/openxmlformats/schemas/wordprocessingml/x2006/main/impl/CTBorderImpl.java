package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.math.BigInteger;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STEighthPointMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPointMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber;

/* loaded from: classes12.dex */
public class CTBorderImpl extends XmlComplexContentImpl implements CTBorder {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "val"), new QName(XSSFRelation.NS_WORDPROCESSINGML, TypedValues.Custom.S_COLOR), new QName(XSSFRelation.NS_WORDPROCESSINGML, "themeColor"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "themeTint"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "themeShade"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "sz"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "space"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "shadow"), new QName(XSSFRelation.NS_WORDPROCESSINGML, TypedValues.AttributesType.S_FRAME)};
    private static final long serialVersionUID = 1;

    public CTBorderImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public STBorder.Enum getVal() {
        STBorder.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STBorder.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public STBorder xgetVal() {
        STBorder target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBorder) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void setVal(STBorder.Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void xsetVal(STBorder val) {
        synchronized (monitor()) {
            check_orphaned();
            STBorder target = (STBorder) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STBorder) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void unsetColor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public STThemeColor.Enum getThemeColor() {
        STThemeColor.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            r1 = target == null ? null : (STThemeColor.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public STThemeColor xgetThemeColor() {
        STThemeColor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STThemeColor) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public boolean isSetThemeColor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void unsetThemeColor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public byte[] getThemeTint() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public STUcharHexNumber xgetThemeTint() {
        STUcharHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUcharHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public boolean isSetThemeTint() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void unsetThemeTint() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public byte[] getThemeShade() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public STUcharHexNumber xgetThemeShade() {
        STUcharHexNumber target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STUcharHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public boolean isSetThemeShade() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void unsetThemeShade() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public BigInteger getSz() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public STEighthPointMeasure xgetSz() {
        STEighthPointMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STEighthPointMeasure) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public boolean isSetSz() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void setSz(BigInteger sz) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setBigIntegerValue(sz);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void xsetSz(STEighthPointMeasure sz) {
        synchronized (monitor()) {
            check_orphaned();
            STEighthPointMeasure target = (STEighthPointMeasure) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STEighthPointMeasure) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(sz);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void unsetSz() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public BigInteger getSpace() {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
            bigIntegerValue = target == null ? null : target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public STPointMeasure xgetSpace() {
        STPointMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPointMeasure) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STPointMeasure) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public boolean isSetSpace() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void setSpace(BigInteger space) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setBigIntegerValue(space);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void xsetSpace(STPointMeasure space) {
        synchronized (monitor()) {
            check_orphaned();
            STPointMeasure target = (STPointMeasure) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STPointMeasure) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(space);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void unsetSpace() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public Object getShadow() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public STOnOff xgetShadow() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public boolean isSetShadow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void setShadow(Object shadow) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setObjectValue(shadow);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void xsetShadow(STOnOff shadow) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(shadow);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void unsetShadow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public Object getFrame() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public STOnOff xgetFrame() {
        STOnOff target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public boolean isSetFrame() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void setFrame(Object frame) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setObjectValue(frame);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void xsetFrame(STOnOff frame) {
        synchronized (monitor()) {
            check_orphaned();
            STOnOff target = (STOnOff) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STOnOff) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(frame);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
    public void unsetFrame() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }
}
