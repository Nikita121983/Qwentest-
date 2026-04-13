package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideFormula;
import org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName;

/* loaded from: classes11.dex */
public class CTGeomGuideImpl extends XmlComplexContentImpl implements CTGeomGuide {
    private static final QName[] PROPERTY_QNAME = {new QName("", "name"), new QName("", "fmla")};
    private static final long serialVersionUID = 1;

    public CTGeomGuideImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide
    public STGeomGuideName xgetName() {
        STGeomGuideName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGeomGuideName) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(name);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide
    public void xsetName(STGeomGuideName name) {
        synchronized (monitor()) {
            check_orphaned();
            STGeomGuideName target = (STGeomGuideName) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STGeomGuideName) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(name);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide
    public String getFmla() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide
    public STGeomGuideFormula xgetFmla() {
        STGeomGuideFormula target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STGeomGuideFormula) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide
    public void setFmla(String fmla) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(fmla);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide
    public void xsetFmla(STGeomGuideFormula fmla) {
        synchronized (monitor()) {
            check_orphaned();
            STGeomGuideFormula target = (STGeomGuideFormula) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STGeomGuideFormula) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(fmla);
        }
    }
}
