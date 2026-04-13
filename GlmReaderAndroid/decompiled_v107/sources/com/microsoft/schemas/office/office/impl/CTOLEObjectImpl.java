package com.microsoft.schemas.office.office.impl;

import com.microsoft.schemas.office.office.CTOLEObject;
import com.microsoft.schemas.office.office.STOLEDrawAspect;
import com.microsoft.schemas.office.office.STOLELinkType;
import com.microsoft.schemas.office.office.STOLEType;
import com.microsoft.schemas.office.office.STOLEUpdateMode;
import com.microsoft.schemas.office.office.STOLEUpdateMode$Enum;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalseBlank;

/* loaded from: classes.dex */
public class CTOLEObjectImpl extends XmlComplexContentImpl implements CTOLEObject {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:office:office", "LinkType"), new QName("urn:schemas-microsoft-com:office:office", "LockedField"), new QName("urn:schemas-microsoft-com:office:office", "FieldCodes"), new QName("", PackageRelationship.TYPE_ATTRIBUTE_NAME), new QName("", "ProgID"), new QName("", "ShapeID"), new QName("", "DrawAspect"), new QName("", "ObjectID"), new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "id"), new QName("", "UpdateMode")};
    private static final long serialVersionUID = 1;

    public CTOLEObjectImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public String getLinkType() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public STOLELinkType xgetLinkType() {
        STOLELinkType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public boolean isSetLinkType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void setLinkType(String linkType) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(linkType);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void xsetLinkType(STOLELinkType linkType) {
        synchronized (monitor()) {
            check_orphaned();
            STOLELinkType target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (STOLELinkType) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(linkType);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void unsetLinkType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public STTrueFalseBlank.Enum getLockedField() {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            r1 = target == null ? null : (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public STTrueFalseBlank xgetLockedField() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public boolean isSetLockedField() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void setLockedField(STTrueFalseBlank.Enum lockedField) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(lockedField);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void xsetLockedField(STTrueFalseBlank lockedField) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(lockedField);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void unsetLockedField() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public String getFieldCodes() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public XmlString xgetFieldCodes() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[2], 0);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public boolean isSetFieldCodes() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void setFieldCodes(String fieldCodes) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(fieldCodes);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void xsetFieldCodes(XmlString fieldCodes) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.set(fieldCodes);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void unsetFieldCodes() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public STOLEType.Enum getType() {
        STOLEType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            r1 = target == null ? null : (STOLEType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public STOLEType xgetType() {
        STOLEType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOLEType) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void setType(STOLEType.Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void xsetType(STOLEType type) {
        synchronized (monitor()) {
            check_orphaned();
            STOLEType target = (STOLEType) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STOLEType) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(type);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public String getProgID() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public XmlString xgetProgID() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public boolean isSetProgID() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void setProgID(String progID) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(progID);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void xsetProgID(XmlString progID) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(progID);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void unsetProgID() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public String getShapeID() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public XmlString xgetShapeID() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public boolean isSetShapeID() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void setShapeID(String shapeID) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setStringValue(shapeID);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void xsetShapeID(XmlString shapeID) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(shapeID);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void unsetShapeID() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public STOLEDrawAspect.Enum getDrawAspect() {
        STOLEDrawAspect.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            r1 = target == null ? null : (STOLEDrawAspect.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public STOLEDrawAspect xgetDrawAspect() {
        STOLEDrawAspect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STOLEDrawAspect) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public boolean isSetDrawAspect() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void setDrawAspect(STOLEDrawAspect.Enum drawAspect) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setEnumValue(drawAspect);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void xsetDrawAspect(STOLEDrawAspect drawAspect) {
        synchronized (monitor()) {
            check_orphaned();
            STOLEDrawAspect target = (STOLEDrawAspect) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STOLEDrawAspect) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(drawAspect);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void unsetDrawAspect() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public String getObjectID() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public XmlString xgetObjectID() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public boolean isSetObjectID() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void setObjectID(String objectID) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setStringValue(objectID);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void xsetObjectID(XmlString objectID) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(objectID);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void unsetObjectID() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public STRelationshipId xgetId() {
        STRelationshipId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setStringValue(id);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void xsetId(STRelationshipId id) {
        synchronized (monitor()) {
            check_orphaned();
            STRelationshipId target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (STRelationshipId) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(id);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public STOLEUpdateMode$Enum getUpdateMode() {
        STOLEUpdateMode$Enum sTOLEUpdateMode$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            sTOLEUpdateMode$Enum = target == null ? null : (STOLEUpdateMode$Enum) target.getEnumValue();
        }
        return sTOLEUpdateMode$Enum;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public STOLEUpdateMode xgetUpdateMode() {
        STOLEUpdateMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public boolean isSetUpdateMode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void setUpdateMode(STOLEUpdateMode$Enum updateMode) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setEnumValue(updateMode);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void xsetUpdateMode(STOLEUpdateMode updateMode) {
        synchronized (monitor()) {
            check_orphaned();
            STOLEUpdateMode target = get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (STOLEUpdateMode) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(updateMode);
        }
    }

    @Override // com.microsoft.schemas.office.office.CTOLEObject
    public void unsetUpdateMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }
}
