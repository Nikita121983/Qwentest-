package org.openxmlformats.schemas.xpackage.x2006.digitalSignature.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.SignatureTimeDocument;

/* loaded from: classes12.dex */
public class SignatureTimeDocumentImpl extends XmlComplexContentImpl implements SignatureTimeDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/package/2006/digital-signature", "SignatureTime")};
    private static final long serialVersionUID = 1;

    public SignatureTimeDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.SignatureTimeDocument
    public CTSignatureTime getSignatureTime() {
        CTSignatureTime cTSignatureTime;
        synchronized (monitor()) {
            check_orphaned();
            CTSignatureTime target = (CTSignatureTime) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTSignatureTime = target == null ? null : target;
        }
        return cTSignatureTime;
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.SignatureTimeDocument
    public void setSignatureTime(CTSignatureTime signatureTime) {
        generatedSetterHelperImpl(signatureTime, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.SignatureTimeDocument
    public CTSignatureTime addNewSignatureTime() {
        CTSignatureTime target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSignatureTime) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
