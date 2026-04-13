package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleTypeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;

/* loaded from: classes11.dex */
public class SimpleTypeDocumentImpl extends XmlComplexContentImpl implements SimpleTypeDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "simpleType")};
    private static final long serialVersionUID = 1;

    public SimpleTypeDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleTypeDocument
    public TopLevelSimpleType getSimpleType() {
        TopLevelSimpleType topLevelSimpleType;
        synchronized (monitor()) {
            check_orphaned();
            TopLevelSimpleType target = (TopLevelSimpleType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            topLevelSimpleType = target == null ? null : target;
        }
        return topLevelSimpleType;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleTypeDocument
    public void setSimpleType(TopLevelSimpleType simpleType) {
        generatedSetterHelperImpl(simpleType, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.SimpleTypeDocument
    public TopLevelSimpleType addNewSimpleType() {
        TopLevelSimpleType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (TopLevelSimpleType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
