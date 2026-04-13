package org.w3.x2000.x09.xmldsig.impl;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.w3.x2000.x09.xmldsig.DigestMethodType;
import org.w3.x2000.x09.xmldsig.DigestValueType;
import org.w3.x2000.x09.xmldsig.ReferenceType;
import org.w3.x2000.x09.xmldsig.TransformsType;

/* loaded from: classes12.dex */
public class ReferenceTypeImpl extends XmlComplexContentImpl implements ReferenceType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XML_DIGSIG_NS, "Transforms"), new QName(SignatureFacet.XML_DIGSIG_NS, "DigestMethod"), new QName(SignatureFacet.XML_DIGSIG_NS, "DigestValue"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME), new QName("", "URI"), new QName("", PackageRelationship.TYPE_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public ReferenceTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public TransformsType getTransforms() {
        TransformsType transformsType;
        synchronized (monitor()) {
            check_orphaned();
            TransformsType target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            transformsType = target == null ? null : target;
        }
        return transformsType;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public boolean isSetTransforms() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public void setTransforms(TransformsType transforms) {
        generatedSetterHelperImpl(transforms, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public TransformsType addNewTransforms() {
        TransformsType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public void unsetTransforms() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public DigestMethodType getDigestMethod() {
        DigestMethodType digestMethodType;
        synchronized (monitor()) {
            check_orphaned();
            DigestMethodType target = (DigestMethodType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            digestMethodType = target == null ? null : target;
        }
        return digestMethodType;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public void setDigestMethod(DigestMethodType digestMethod) {
        generatedSetterHelperImpl(digestMethod, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public DigestMethodType addNewDigestMethod() {
        DigestMethodType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DigestMethodType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public byte[] getDigestValue() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public DigestValueType xgetDigestValue() {
        DigestValueType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DigestValueType) get_store().find_element_user(PROPERTY_QNAME[2], 0);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public void setDigestValue(byte[] digestValue) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.setByteArrayValue(digestValue);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public void xsetDigestValue(DigestValueType digestValue) {
        synchronized (monitor()) {
            check_orphaned();
            DigestValueType target = (DigestValueType) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (DigestValueType) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.set(digestValue);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public XmlID xgetId() {
        XmlID target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public void xsetId(XmlID id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlID target = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlID) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(id);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public String getURI() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public XmlAnyURI xgetURI() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public boolean isSetURI() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public void setURI(String uri) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(uri);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public void xsetURI(XmlAnyURI uri) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(uri);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public void unsetURI() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public String getType() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public XmlAnyURI xgetType() {
        XmlAnyURI target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public void setType(String type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setStringValue(type);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public void xsetType(XmlAnyURI type) {
        synchronized (monitor()) {
            check_orphaned();
            XmlAnyURI target = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlAnyURI) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(type);
        }
    }

    @Override // org.w3.x2000.x09.xmldsig.ReferenceType
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }
}
