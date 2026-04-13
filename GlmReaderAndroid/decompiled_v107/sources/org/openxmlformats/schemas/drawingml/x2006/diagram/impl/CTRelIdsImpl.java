package org.openxmlformats.schemas.drawingml.x2006.diagram.impl;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

/* loaded from: classes11.dex */
public class CTRelIdsImpl extends XmlComplexContentImpl implements CTRelIds {
    private static final QName[] PROPERTY_QNAME = {new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "dm"), new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "lo"), new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "qs"), new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "cs")};
    private static final long serialVersionUID = 1;

    public CTRelIdsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public String getDm() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public STRelationshipId xgetDm() {
        STRelationshipId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public void setDm(String dm) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(dm);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public void xsetDm(STRelationshipId dm) {
        synchronized (monitor()) {
            check_orphaned();
            STRelationshipId target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STRelationshipId) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(dm);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public String getLo() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public STRelationshipId xgetLo() {
        STRelationshipId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public void setLo(String lo) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(lo);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public void xsetLo(STRelationshipId lo) {
        synchronized (monitor()) {
            check_orphaned();
            STRelationshipId target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STRelationshipId) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(lo);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public String getQs() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public STRelationshipId xgetQs() {
        STRelationshipId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public void setQs(String qs) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(qs);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public void xsetQs(STRelationshipId qs) {
        synchronized (monitor()) {
            check_orphaned();
            STRelationshipId target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STRelationshipId) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(qs);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public String getCs() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public STRelationshipId xgetCs() {
        STRelationshipId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public void setCs(String cs) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(cs);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.diagram.CTRelIds
    public void xsetCs(STRelationshipId cs) {
        synchronized (monitor()) {
            check_orphaned();
            STRelationshipId target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STRelationshipId) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(cs);
        }
    }
}
