package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTZoom;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumberOrPercent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STZoom;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STZoom$Enum;

/* loaded from: classes12.dex */
public class CTZoomImpl extends XmlComplexContentImpl implements CTZoom {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "val"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "percent")};
    private static final long serialVersionUID = 1;

    public CTZoomImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTZoom
    public STZoom$Enum getVal() {
        STZoom$Enum sTZoom$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            sTZoom$Enum = target == null ? null : (STZoom$Enum) target.getEnumValue();
        }
        return sTZoom$Enum;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTZoom
    public STZoom xgetVal() {
        STZoom target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTZoom
    public boolean isSetVal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTZoom
    public void setVal(STZoom$Enum val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTZoom
    public void xsetVal(STZoom val) {
        synchronized (monitor()) {
            check_orphaned();
            STZoom target = get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STZoom) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(val);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTZoom
    public void unsetVal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTZoom
    public Object getPercent() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTZoom
    public STDecimalNumberOrPercent xgetPercent() {
        STDecimalNumberOrPercent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDecimalNumberOrPercent) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTZoom
    public void setPercent(Object percent) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setObjectValue(percent);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTZoom
    public void xsetPercent(STDecimalNumberOrPercent percent) {
        synchronized (monitor()) {
            check_orphaned();
            STDecimalNumberOrPercent target = (STDecimalNumberOrPercent) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STDecimalNumberOrPercent) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(percent);
        }
    }
}
