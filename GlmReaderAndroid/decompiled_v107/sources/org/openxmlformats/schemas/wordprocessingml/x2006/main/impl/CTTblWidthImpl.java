package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMeasurementOrPercent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

/* loaded from: classes12.dex */
public class CTTblWidthImpl extends XmlComplexContentImpl implements CTTblWidth {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "w"), new QName(XSSFRelation.NS_WORDPROCESSINGML, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY)};
    private static final long serialVersionUID = 1;

    public CTTblWidthImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth
    public Object getW() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth
    public STMeasurementOrPercent xgetW() {
        STMeasurementOrPercent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STMeasurementOrPercent) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth
    public boolean isSetW() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth
    public void setW(Object w) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(w);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth
    public void xsetW(STMeasurementOrPercent w) {
        synchronized (monitor()) {
            check_orphaned();
            STMeasurementOrPercent target = (STMeasurementOrPercent) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STMeasurementOrPercent) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(w);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth
    public void unsetW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth
    public STTblWidth.Enum getType() {
        STTblWidth.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            r1 = target == null ? null : (STTblWidth.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth
    public STTblWidth xgetType() {
        STTblWidth target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTblWidth) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth
    public boolean isSetType() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth
    public void setType(STTblWidth.Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth
    public void xsetType(STTblWidth type) {
        synchronized (monitor()) {
            check_orphaned();
            STTblWidth target = (STTblWidth) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STTblWidth) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(type);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
