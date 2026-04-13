package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetDataSet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames;

/* loaded from: classes12.dex */
public class CTExternalBookImpl extends XmlComplexContentImpl implements CTExternalBook {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sheetNames"), new QName(XSSFRelation.NS_SPREADSHEETML, "definedNames"), new QName(XSSFRelation.NS_SPREADSHEETML, "sheetDataSet"), new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "id")};
    private static final long serialVersionUID = 1;

    public CTExternalBookImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public CTExternalSheetNames getSheetNames() {
        CTExternalSheetNames cTExternalSheetNames;
        synchronized (monitor()) {
            check_orphaned();
            CTExternalSheetNames target = (CTExternalSheetNames) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTExternalSheetNames = target == null ? null : target;
        }
        return cTExternalSheetNames;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public boolean isSetSheetNames() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public void setSheetNames(CTExternalSheetNames sheetNames) {
        generatedSetterHelperImpl(sheetNames, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public CTExternalSheetNames addNewSheetNames() {
        CTExternalSheetNames target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalSheetNames) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public void unsetSheetNames() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public CTExternalDefinedNames getDefinedNames() {
        CTExternalDefinedNames cTExternalDefinedNames;
        synchronized (monitor()) {
            check_orphaned();
            CTExternalDefinedNames target = (CTExternalDefinedNames) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTExternalDefinedNames = target == null ? null : target;
        }
        return cTExternalDefinedNames;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public boolean isSetDefinedNames() {
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

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public void setDefinedNames(CTExternalDefinedNames definedNames) {
        generatedSetterHelperImpl(definedNames, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public CTExternalDefinedNames addNewDefinedNames() {
        CTExternalDefinedNames target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalDefinedNames) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public void unsetDefinedNames() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public CTExternalSheetDataSet getSheetDataSet() {
        CTExternalSheetDataSet cTExternalSheetDataSet;
        synchronized (monitor()) {
            check_orphaned();
            CTExternalSheetDataSet target = (CTExternalSheetDataSet) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTExternalSheetDataSet = target == null ? null : target;
        }
        return cTExternalSheetDataSet;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public boolean isSetSheetDataSet() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public void setSheetDataSet(CTExternalSheetDataSet sheetDataSet) {
        generatedSetterHelperImpl(sheetDataSet, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public CTExternalSheetDataSet addNewSheetDataSet() {
        CTExternalSheetDataSet target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalSheetDataSet) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public void unsetSheetDataSet() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public STRelationshipId xgetId() {
        STRelationshipId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public void setId(String id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setStringValue(id);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalBook
    public void xsetId(STRelationshipId id) {
        synchronized (monitor()) {
            check_orphaned();
            STRelationshipId target = (STRelationshipId) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STRelationshipId) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(id);
        }
    }
}
