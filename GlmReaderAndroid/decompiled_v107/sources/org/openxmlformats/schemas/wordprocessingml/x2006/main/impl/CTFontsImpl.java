package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHint;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTheme;

/* loaded from: classes12.dex */
public class CTFontsImpl extends XmlComplexContentImpl implements CTFonts {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "hint"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "ascii"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hAnsi"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "eastAsia"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cs"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "asciiTheme"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "hAnsiTheme"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "eastAsiaTheme"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "cstheme")};
    private static final long serialVersionUID = 1;

    public CTFontsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public STHint.Enum getHint() {
        STHint.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STHint.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public STHint xgetHint() {
        STHint target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STHint) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public boolean isSetHint() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void setHint(STHint.Enum hint) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(hint);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void xsetHint(STHint hint) {
        synchronized (monitor()) {
            check_orphaned();
            STHint target = (STHint) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STHint) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(hint);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void unsetHint() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public String getAscii() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public STString xgetAscii() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public boolean isSetAscii() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void setAscii(String ascii) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(ascii);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void xsetAscii(STString ascii) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(ascii);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void unsetAscii() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public String getHAnsi() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public STString xgetHAnsi() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public boolean isSetHAnsi() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void setHAnsi(String hAnsi) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(hAnsi);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void xsetHAnsi(STString hAnsi) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(hAnsi);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void unsetHAnsi() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public String getEastAsia() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public STString xgetEastAsia() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public boolean isSetEastAsia() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void setEastAsia(String eastAsia) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(eastAsia);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void xsetEastAsia(STString eastAsia) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(eastAsia);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void unsetEastAsia() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public String getCs() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public STString xgetCs() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public boolean isSetCs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void setCs(String cs) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(cs);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void xsetCs(STString cs) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(cs);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void unsetCs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public STTheme.Enum getAsciiTheme() {
        STTheme.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            r1 = target == null ? null : (STTheme.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public STTheme xgetAsciiTheme() {
        STTheme target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTheme) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public boolean isSetAsciiTheme() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void setAsciiTheme(STTheme.Enum asciiTheme) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setEnumValue(asciiTheme);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void xsetAsciiTheme(STTheme asciiTheme) {
        synchronized (monitor()) {
            check_orphaned();
            STTheme target = (STTheme) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STTheme) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(asciiTheme);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void unsetAsciiTheme() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public STTheme.Enum getHAnsiTheme() {
        STTheme.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            r1 = target == null ? null : (STTheme.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public STTheme xgetHAnsiTheme() {
        STTheme target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTheme) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public boolean isSetHAnsiTheme() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void setHAnsiTheme(STTheme.Enum hAnsiTheme) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setEnumValue(hAnsiTheme);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void xsetHAnsiTheme(STTheme hAnsiTheme) {
        synchronized (monitor()) {
            check_orphaned();
            STTheme target = (STTheme) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STTheme) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(hAnsiTheme);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void unsetHAnsiTheme() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public STTheme.Enum getEastAsiaTheme() {
        STTheme.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            r1 = target == null ? null : (STTheme.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public STTheme xgetEastAsiaTheme() {
        STTheme target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTheme) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public boolean isSetEastAsiaTheme() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void setEastAsiaTheme(STTheme.Enum eastAsiaTheme) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setEnumValue(eastAsiaTheme);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void xsetEastAsiaTheme(STTheme eastAsiaTheme) {
        synchronized (monitor()) {
            check_orphaned();
            STTheme target = (STTheme) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (STTheme) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(eastAsiaTheme);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void unsetEastAsiaTheme() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public STTheme.Enum getCstheme() {
        STTheme.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            r1 = target == null ? null : (STTheme.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public STTheme xgetCstheme() {
        STTheme target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTheme) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public boolean isSetCstheme() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void setCstheme(STTheme.Enum cstheme) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setEnumValue(cstheme);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void xsetCstheme(STTheme cstheme) {
        synchronized (monitor()) {
            check_orphaned();
            STTheme target = (STTheme) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STTheme) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(cstheme);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts
    public void unsetCstheme() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }
}
