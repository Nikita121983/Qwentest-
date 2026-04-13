package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute;

/* loaded from: classes11.dex */
public class AttributeDocumentImpl extends XmlComplexContentImpl implements AttributeDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "attribute")};
    private static final long serialVersionUID = 1;

    public AttributeDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AttributeDocument
    public TopLevelAttribute getAttribute() {
        TopLevelAttribute topLevelAttribute;
        synchronized (monitor()) {
            check_orphaned();
            TopLevelAttribute target = (TopLevelAttribute) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            topLevelAttribute = target == null ? null : target;
        }
        return topLevelAttribute;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AttributeDocument
    public void setAttribute(TopLevelAttribute attribute) {
        generatedSetterHelperImpl(attribute, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AttributeDocument
    public TopLevelAttribute addNewAttribute() {
        TopLevelAttribute target;
        synchronized (monitor()) {
            check_orphaned();
            target = (TopLevelAttribute) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
