package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEmptyElement;

/* loaded from: classes11.dex */
public class CTColorMappingOverrideImpl extends XmlComplexContentImpl implements CTColorMappingOverride {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "masterClrMapping"), new QName(XSSFRelation.NS_DRAWINGML, "overrideClrMapping")};
    private static final long serialVersionUID = 1;

    public CTColorMappingOverrideImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride
    public CTEmptyElement getMasterClrMapping() {
        CTEmptyElement cTEmptyElement;
        synchronized (monitor()) {
            check_orphaned();
            CTEmptyElement target = (CTEmptyElement) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTEmptyElement = target == null ? null : target;
        }
        return cTEmptyElement;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride
    public boolean isSetMasterClrMapping() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride
    public void setMasterClrMapping(CTEmptyElement masterClrMapping) {
        generatedSetterHelperImpl(masterClrMapping, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride
    public CTEmptyElement addNewMasterClrMapping() {
        CTEmptyElement target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmptyElement) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride
    public void unsetMasterClrMapping() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride
    public CTColorMapping getOverrideClrMapping() {
        CTColorMapping cTColorMapping;
        synchronized (monitor()) {
            check_orphaned();
            CTColorMapping target = (CTColorMapping) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTColorMapping = target == null ? null : target;
        }
        return cTColorMapping;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride
    public boolean isSetOverrideClrMapping() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride
    public void setOverrideClrMapping(CTColorMapping overrideClrMapping) {
        generatedSetterHelperImpl(overrideClrMapping, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride
    public CTColorMapping addNewOverrideClrMapping() {
        CTColorMapping target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColorMapping) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride
    public void unsetOverrideClrMapping() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
