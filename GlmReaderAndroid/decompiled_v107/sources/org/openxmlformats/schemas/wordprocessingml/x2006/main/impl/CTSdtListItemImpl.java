package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtListItem;

/* loaded from: classes12.dex */
public class CTSdtListItemImpl extends XmlComplexContentImpl implements CTSdtListItem {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "displayText"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "value")};
    private static final long serialVersionUID = 1;

    public CTSdtListItemImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtListItem
    public String getDisplayText() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtListItem
    public STString xgetDisplayText() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtListItem
    public boolean isSetDisplayText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtListItem
    public void setDisplayText(String displayText) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(displayText);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtListItem
    public void xsetDisplayText(STString displayText) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(displayText);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtListItem
    public void unsetDisplayText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtListItem
    public String getValue() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtListItem
    public STString xgetValue() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtListItem
    public boolean isSetValue() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtListItem
    public void setValue(String value) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(value);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtListItem
    public void xsetValue(STString value) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(value);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtListItem
    public void unsetValue() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
