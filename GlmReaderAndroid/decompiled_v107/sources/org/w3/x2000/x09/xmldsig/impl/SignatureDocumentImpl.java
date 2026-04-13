package org.w3.x2000.x09.xmldsig.impl;

import javax.xml.namespace.QName;
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.w3.x2000.x09.xmldsig.SignatureDocument;
import org.w3.x2000.x09.xmldsig.SignatureType;

/* loaded from: classes12.dex */
public class SignatureDocumentImpl extends XmlComplexContentImpl implements SignatureDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XML_DIGSIG_NS, AttributeLayout.ATTRIBUTE_SIGNATURE)};
    private static final long serialVersionUID = 1;

    public SignatureDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureDocument
    public SignatureType getSignature() {
        SignatureType signatureType;
        synchronized (monitor()) {
            check_orphaned();
            SignatureType target = (SignatureType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            signatureType = target == null ? null : target;
        }
        return signatureType;
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureDocument
    public void setSignature(SignatureType signature) {
        generatedSetterHelperImpl(signature, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.w3.x2000.x09.xmldsig.SignatureDocument
    public SignatureType addNewSignature() {
        SignatureType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (SignatureType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
