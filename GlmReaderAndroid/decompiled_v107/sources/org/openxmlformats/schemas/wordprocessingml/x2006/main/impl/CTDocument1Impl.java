package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass$Enum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;

/* loaded from: classes12.dex */
public class CTDocument1Impl extends CTDocumentBaseImpl implements CTDocument1 {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "body"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "conformance")};
    private static final long serialVersionUID = 1;

    public CTDocument1Impl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1
    public CTBody getBody() {
        CTBody cTBody;
        synchronized (monitor()) {
            check_orphaned();
            CTBody target = (CTBody) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTBody = target == null ? null : target;
        }
        return cTBody;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1
    public boolean isSetBody() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1
    public void setBody(CTBody body) {
        generatedSetterHelperImpl(body, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1
    public CTBody addNewBody() {
        CTBody target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBody) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1
    public void unsetBody() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1
    public STConformanceClass$Enum getConformance() {
        STConformanceClass$Enum sTConformanceClass$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            sTConformanceClass$Enum = target == null ? null : (STConformanceClass$Enum) target.getEnumValue();
        }
        return sTConformanceClass$Enum;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1
    public STConformanceClass xgetConformance() {
        STConformanceClass target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1
    public boolean isSetConformance() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1
    public void setConformance(STConformanceClass$Enum conformance) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(conformance);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1
    public void xsetConformance(STConformanceClass conformance) {
        synchronized (monitor()) {
            check_orphaned();
            STConformanceClass target = get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STConformanceClass) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(conformance);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1
    public void unsetConformance() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
