package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup;

/* loaded from: classes11.dex */
public class AttributeGroupDocumentImpl extends XmlComplexContentImpl implements AttributeGroupDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup")};
    private static final long serialVersionUID = 1;

    public AttributeGroupDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupDocument
    public NamedAttributeGroup getAttributeGroup() {
        NamedAttributeGroup namedAttributeGroup;
        synchronized (monitor()) {
            check_orphaned();
            NamedAttributeGroup target = (NamedAttributeGroup) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            namedAttributeGroup = target == null ? null : target;
        }
        return namedAttributeGroup;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupDocument
    public void setAttributeGroup(NamedAttributeGroup attributeGroup) {
        generatedSetterHelperImpl(attributeGroup, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupDocument
    public NamedAttributeGroup addNewAttributeGroup() {
        NamedAttributeGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NamedAttributeGroup) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
