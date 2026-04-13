package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;

/* loaded from: classes11.dex */
public class GroupDocumentImpl extends XmlComplexContentImpl implements GroupDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "group")};
    private static final long serialVersionUID = 1;

    public GroupDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.GroupDocument
    public NamedGroup getGroup() {
        NamedGroup namedGroup;
        synchronized (monitor()) {
            check_orphaned();
            NamedGroup target = (NamedGroup) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            namedGroup = target == null ? null : target;
        }
        return namedGroup;
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.GroupDocument
    public void setGroup(NamedGroup group) {
        generatedSetterHelperImpl(group, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.xsdschema.GroupDocument
    public NamedGroup addNewGroup() {
        NamedGroup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (NamedGroup) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
