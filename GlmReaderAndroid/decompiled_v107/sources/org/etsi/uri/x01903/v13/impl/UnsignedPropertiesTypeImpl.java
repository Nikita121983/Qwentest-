package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType;

/* loaded from: classes11.dex */
public class UnsignedPropertiesTypeImpl extends XmlComplexContentImpl implements UnsignedPropertiesType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "UnsignedSignatureProperties"), new QName(SignatureFacet.XADES_132_NS, "UnsignedDataObjectProperties"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public UnsignedPropertiesTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public UnsignedSignaturePropertiesType getUnsignedSignatureProperties() {
        UnsignedSignaturePropertiesType unsignedSignaturePropertiesType;
        synchronized (monitor()) {
            check_orphaned();
            UnsignedSignaturePropertiesType target = (UnsignedSignaturePropertiesType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            unsignedSignaturePropertiesType = target == null ? null : target;
        }
        return unsignedSignaturePropertiesType;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public boolean isSetUnsignedSignatureProperties() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public void setUnsignedSignatureProperties(UnsignedSignaturePropertiesType unsignedSignatureProperties) {
        generatedSetterHelperImpl(unsignedSignatureProperties, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public UnsignedSignaturePropertiesType addNewUnsignedSignatureProperties() {
        UnsignedSignaturePropertiesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (UnsignedSignaturePropertiesType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public void unsetUnsignedSignatureProperties() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public UnsignedDataObjectPropertiesType getUnsignedDataObjectProperties() {
        UnsignedDataObjectPropertiesType unsignedDataObjectPropertiesType;
        synchronized (monitor()) {
            check_orphaned();
            UnsignedDataObjectPropertiesType target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            unsignedDataObjectPropertiesType = target == null ? null : target;
        }
        return unsignedDataObjectPropertiesType;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public boolean isSetUnsignedDataObjectProperties() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public void setUnsignedDataObjectProperties(UnsignedDataObjectPropertiesType unsignedDataObjectProperties) {
        generatedSetterHelperImpl(unsignedDataObjectProperties, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public UnsignedDataObjectPropertiesType addNewUnsignedDataObjectProperties() {
        UnsignedDataObjectPropertiesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public void unsetUnsignedDataObjectProperties() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public void xsetId(XmlID id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(id);
        }
    }

    @Override // org.etsi.uri.x01903.v13.UnsignedPropertiesType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }
}
