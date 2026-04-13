package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.impl.xb.xsdschema.MinInclusiveDocument;

/* loaded from: classes11.dex */
public class MinInclusiveDocumentImpl extends XmlComplexContentImpl implements MinInclusiveDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "minInclusive")};
    private static final long serialVersionUID = 1;

    public MinInclusiveDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.MinInclusiveDocument
    public Facet getMinInclusive() {
        Facet facet;
        synchronized (monitor()) {
            check_orphaned();
            Facet target = (Facet) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            facet = target == null ? null : target;
        }
        return facet;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.MinInclusiveDocument
    public void setMinInclusive(Facet minInclusive) {
        generatedSetterHelperImpl(minInclusive, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.MinInclusiveDocument
    public Facet addNewMinInclusive() {
        Facet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Facet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
