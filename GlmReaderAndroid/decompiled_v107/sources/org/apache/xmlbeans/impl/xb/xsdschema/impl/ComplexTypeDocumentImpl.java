package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexTypeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;

/* loaded from: classes11.dex */
public class ComplexTypeDocumentImpl extends XmlComplexContentImpl implements ComplexTypeDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "complexType")};
    private static final long serialVersionUID = 1;

    public ComplexTypeDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexTypeDocument
    public TopLevelComplexType getComplexType() {
        TopLevelComplexType topLevelComplexType;
        synchronized (monitor()) {
            check_orphaned();
            TopLevelComplexType target = (TopLevelComplexType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            topLevelComplexType = target == null ? null : target;
        }
        return topLevelComplexType;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexTypeDocument
    public void setComplexType(TopLevelComplexType complexType) {
        generatedSetterHelperImpl(complexType, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.ComplexTypeDocument
    public TopLevelComplexType addNewComplexType() {
        TopLevelComplexType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (TopLevelComplexType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
