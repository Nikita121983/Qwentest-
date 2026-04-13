package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel;

/* loaded from: classes12.dex */
public class CTRelImpl extends XmlComplexContentImpl implements CTRel {
    private static final QName[] PROPERTY_QNAME = {new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "id")};
    private static final long serialVersionUID = 1;

    public CTRelImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel
    public STRelationshipId xgetId() {
        STRelationshipId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel
    public void xsetId(STRelationshipId id) {
        synchronized (monitor()) {
            check_orphaned();
            STRelationshipId target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STRelationshipId) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(id);
        }
    }
}
