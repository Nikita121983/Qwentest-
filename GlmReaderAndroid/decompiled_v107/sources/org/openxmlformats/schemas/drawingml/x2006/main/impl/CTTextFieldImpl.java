package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid;

/* loaded from: classes11.dex */
public class CTTextFieldImpl extends XmlComplexContentImpl implements CTTextField {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "rPr"), new QName(XSSFRelation.NS_DRAWINGML, "pPr"), new QName(XSSFRelation.NS_DRAWINGML, "t"), new QName("", "id"), new QName("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY)};
    private static final long serialVersionUID = 1;

    public CTTextFieldImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public CTTextCharacterProperties getRPr() {
        CTTextCharacterProperties cTTextCharacterProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextCharacterProperties target = (CTTextCharacterProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTextCharacterProperties = target == null ? null : target;
        }
        return cTTextCharacterProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public boolean isSetRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public void setRPr(CTTextCharacterProperties rPr) {
        generatedSetterHelperImpl(rPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public CTTextCharacterProperties addNewRPr() {
        CTTextCharacterProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextCharacterProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public void unsetRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public CTTextParagraphProperties getPPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTTextParagraphProperties target = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTextParagraphProperties = target == null ? null : target;
        }
        return cTTextParagraphProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public boolean isSetPPr() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public void setPPr(CTTextParagraphProperties pPr) {
        generatedSetterHelperImpl(pPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public CTTextParagraphProperties addNewPPr() {
        CTTextParagraphProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public void unsetPPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public String getT() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public XmlString xgetT() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[2], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public boolean isSetT() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public void setT(String t) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.setStringValue(t);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public void xsetT(XmlString t) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            target.set(t);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public void unsetT() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public String getId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public STGuid xgetId() {
        STGuid target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public void xsetId(STGuid id) {
        synchronized (monitor()) {
            check_orphaned();
            STGuid target = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STGuid) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(id);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public String getType() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public XmlString xgetType() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public void setType(String type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setStringValue(type);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public void xsetType(XmlString type) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(type);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextField
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
