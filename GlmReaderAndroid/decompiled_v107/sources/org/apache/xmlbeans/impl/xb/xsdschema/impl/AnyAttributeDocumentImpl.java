package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AnyAttributeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;

/* loaded from: classes11.dex */
public class AnyAttributeDocumentImpl extends XmlComplexContentImpl implements AnyAttributeDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "anyAttribute")};
    private static final long serialVersionUID = 1;

    public AnyAttributeDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyAttributeDocument
    public Wildcard getAnyAttribute() {
        Wildcard wildcard;
        synchronized (monitor()) {
            check_orphaned();
            Wildcard target = (Wildcard) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            wildcard = target == null ? null : target;
        }
        return wildcard;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyAttributeDocument
    public void setAnyAttribute(Wildcard anyAttribute) {
        generatedSetterHelperImpl(anyAttribute, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AnyAttributeDocument
    public Wildcard addNewAnyAttribute() {
        Wildcard target;
        synchronized (monitor()) {
            check_orphaned();
            target = (Wildcard) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
