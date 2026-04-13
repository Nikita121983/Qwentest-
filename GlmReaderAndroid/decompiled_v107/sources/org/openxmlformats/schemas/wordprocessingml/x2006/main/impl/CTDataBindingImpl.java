package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding;

/* loaded from: classes12.dex */
public class CTDataBindingImpl extends XmlComplexContentImpl implements CTDataBinding {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "prefixMappings"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "xpath"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "storeItemID")};
    private static final long serialVersionUID = 1;

    public CTDataBindingImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
    public String getPrefixMappings() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
    public STString xgetPrefixMappings() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
    public boolean isSetPrefixMappings() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
    public void setPrefixMappings(String prefixMappings) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(prefixMappings);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
    public void xsetPrefixMappings(STString prefixMappings) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(prefixMappings);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
    public void unsetPrefixMappings() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
    public String getXpath() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
    public STString xgetXpath() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
    public void setXpath(String xpath) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(xpath);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
    public void xsetXpath(STString xpath) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(xpath);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
    public String getStoreItemID() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
    public STString xgetStoreItemID() {
        STString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
    public void setStoreItemID(String storeItemID) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(storeItemID);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDataBinding
    public void xsetStoreItemID(STString storeItemID) {
        synchronized (monitor()) {
            check_orphaned();
            STString target = (STString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STString) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(storeItemID);
        }
    }
}
