package org.w3.x2000.x09.xmldsig.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.w3.x2000.x09.xmldsig.TransformDocument;
import org.w3.x2000.x09.xmldsig.TransformType;

/* loaded from: classes12.dex */
public class TransformDocumentImpl extends XmlComplexContentImpl implements TransformDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XML_DIGSIG_NS, "Transform")};
    private static final long serialVersionUID = 1;

    public TransformDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformDocument
    public TransformType getTransform() {
        TransformType transformType;
        synchronized (monitor()) {
            check_orphaned();
            TransformType target = (TransformType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            transformType = target == null ? null : target;
        }
        return transformType;
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformDocument
    public void setTransform(TransformType transform) {
        generatedSetterHelperImpl(transform, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.w3.x2000.x09.xmldsig.TransformDocument
    public TransformType addNewTransform() {
        TransformType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (TransformType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
