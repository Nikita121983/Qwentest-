package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathShadeType;

/* loaded from: classes11.dex */
public class CTPathShadePropertiesImpl extends XmlComplexContentImpl implements CTPathShadeProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "fillToRect"), new QName("", "path")};
    private static final long serialVersionUID = 1;

    public CTPathShadePropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties
    public CTRelativeRect getFillToRect() {
        CTRelativeRect cTRelativeRect;
        synchronized (monitor()) {
            check_orphaned();
            CTRelativeRect target = (CTRelativeRect) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTRelativeRect = target == null ? null : target;
        }
        return cTRelativeRect;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties
    public boolean isSetFillToRect() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties
    public void setFillToRect(CTRelativeRect fillToRect) {
        generatedSetterHelperImpl(fillToRect, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties
    public CTRelativeRect addNewFillToRect() {
        CTRelativeRect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRelativeRect) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties
    public void unsetFillToRect() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties
    public STPathShadeType.Enum getPath() {
        STPathShadeType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            r1 = target == null ? null : (STPathShadeType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties
    public STPathShadeType xgetPath() {
        STPathShadeType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPathShadeType) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties
    public boolean isSetPath() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties
    public void setPath(STPathShadeType.Enum path) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(path);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties
    public void xsetPath(STPathShadeType path) {
        synchronized (monitor()) {
            check_orphaned();
            STPathShadeType target = (STPathShadeType) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STPathShadeType) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(path);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties
    public void unsetPath() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
