package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.w3.x2000.x09.xmldsig.DigestMethodType;
import org.w3.x2000.x09.xmldsig.DigestValueType;

/* loaded from: classes11.dex */
public class DigestAlgAndValueTypeImpl extends XmlComplexContentImpl implements DigestAlgAndValueType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XML_DIGSIG_NS, "DigestMethod"), new QName(SignatureFacet.XML_DIGSIG_NS, "DigestValue")};
    private static final long serialVersionUID = 1;

    public DigestAlgAndValueTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.DigestAlgAndValueType
    public DigestMethodType getDigestMethod() {
        DigestMethodType digestMethodType;
        synchronized (monitor()) {
            check_orphaned();
            DigestMethodType target = (DigestMethodType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            digestMethodType = target == null ? null : target;
        }
        return digestMethodType;
    }

    @Override // org.etsi.uri.x01903.v13.DigestAlgAndValueType
    public void setDigestMethod(DigestMethodType digestMethod) {
        generatedSetterHelperImpl(digestMethod, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.DigestAlgAndValueType
    public DigestMethodType addNewDigestMethod() {
        DigestMethodType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DigestMethodType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.DigestAlgAndValueType
    public byte[] getDigestValue() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.etsi.uri.x01903.v13.DigestAlgAndValueType
    public DigestValueType xgetDigestValue() {
        DigestValueType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (DigestValueType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.DigestAlgAndValueType
    public void setDigestValue(byte[] digestValue) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setByteArrayValue(digestValue);
        }
    }

    @Override // org.etsi.uri.x01903.v13.DigestAlgAndValueType
    public void xsetDigestValue(DigestValueType digestValue) {
        synchronized (monitor()) {
            check_orphaned();
            DigestValueType target = (DigestValueType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (DigestValueType) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(digestValue);
        }
    }
}
