package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;

/* loaded from: classes11.dex */
public class CTRegularTextRunImpl extends XmlComplexContentImpl implements CTRegularTextRun {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "rPr"), new QName(XSSFRelation.NS_DRAWINGML, "t")};
    private static final long serialVersionUID = 1;

    public CTRegularTextRunImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun
    public CTTextCharacterProperties getRPr() {
        CTTextCharacterProperties cTTextCharacterProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextCharacterProperties target = (CTTextCharacterProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTextCharacterProperties = target == null ? null : target;
        }
        return cTTextCharacterProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun
    public boolean isSetRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun
    public void setRPr(CTTextCharacterProperties rPr) {
        generatedSetterHelperImpl(rPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun
    public CTTextCharacterProperties addNewRPr() {
        CTTextCharacterProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextCharacterProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun
    public void unsetRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun
    public String getT() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun
    public XmlString xgetT() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun
    public void setT(String t) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(t);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun
    public void xsetT(XmlString t) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(t);
        }
    }
}
