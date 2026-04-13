package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBrClear;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBrType;

/* loaded from: classes12.dex */
public class CTBrImpl extends XmlComplexContentImpl implements CTBr {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY), new QName(XSSFRelation.NS_WORDPROCESSINGML, "clear")};
    private static final long serialVersionUID = 1;

    public CTBrImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr
    public STBrType.Enum getType() {
        STBrType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STBrType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr
    public STBrType xgetType() {
        STBrType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBrType) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr
    public void setType(STBrType.Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr
    public void xsetType(STBrType type) {
        synchronized (monitor()) {
            check_orphaned();
            STBrType target = (STBrType) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STBrType) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(type);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr
    public STBrClear.Enum getClear() {
        STBrClear.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            r1 = target == null ? null : (STBrClear.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr
    public STBrClear xgetClear() {
        STBrClear target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STBrClear) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr
    public boolean isSetClear() {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr
    public void setClear(STBrClear.Enum clear) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(clear);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr
    public void xsetClear(STBrClear clear) {
        synchronized (monitor()) {
            check_orphaned();
            STBrClear target = (STBrClear) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STBrClear) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(clear);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr
    public void unsetClear() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
