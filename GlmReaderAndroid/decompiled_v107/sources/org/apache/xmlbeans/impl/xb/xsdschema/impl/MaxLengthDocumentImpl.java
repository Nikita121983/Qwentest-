package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.MaxLengthDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NumFacet;

/* loaded from: classes11.dex */
public class MaxLengthDocumentImpl extends XmlComplexContentImpl implements MaxLengthDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "maxLength")};
    private static final long serialVersionUID = 1;

    public MaxLengthDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.MaxLengthDocument
    public NumFacet getMaxLength() {
        NumFacet numFacet;
        synchronized (monitor()) {
            check_orphaned();
            NumFacet target = (NumFacet) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            numFacet = target == null ? null : target;
        }
        return numFacet;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.MaxLengthDocument
    public void setMaxLength(NumFacet maxLength) {
        generatedSetterHelperImpl(maxLength, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.MaxLengthDocument
    public NumFacet addNewMaxLength() {
        NumFacet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NumFacet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
