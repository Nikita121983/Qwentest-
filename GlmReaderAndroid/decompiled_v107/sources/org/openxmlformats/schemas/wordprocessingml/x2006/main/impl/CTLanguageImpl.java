package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STLang;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage;

/* loaded from: classes12.dex */
public class CTLanguageImpl extends XmlComplexContentImpl implements CTLanguage {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "val"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "eastAsia"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bidi")};
    private static final long serialVersionUID = 1;

    public CTLanguageImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public String getVal() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public STLang xgetVal() {
        STLang target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLang) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public boolean isSetVal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public void setVal(String val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public void xsetVal(STLang val) {
        synchronized (monitor()) {
            check_orphaned();
            STLang target = (STLang) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STLang) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public void unsetVal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public String getEastAsia() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public STLang xgetEastAsia() {
        STLang target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLang) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public boolean isSetEastAsia() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public void setEastAsia(String eastAsia) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(eastAsia);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public void xsetEastAsia(STLang eastAsia) {
        synchronized (monitor()) {
            check_orphaned();
            STLang target = (STLang) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STLang) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(eastAsia);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public void unsetEastAsia() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public String getBidi() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public STLang xgetBidi() {
        STLang target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STLang) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public boolean isSetBidi() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public void setBidi(String bidi) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(bidi);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public void xsetBidi(STLang bidi) {
        synchronized (monitor()) {
            check_orphaned();
            STLang target = (STLang) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STLang) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(bidi);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage
    public void unsetBidi() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }
}
