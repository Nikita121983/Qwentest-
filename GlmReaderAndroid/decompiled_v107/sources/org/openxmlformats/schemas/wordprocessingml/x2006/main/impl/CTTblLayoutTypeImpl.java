package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblLayoutType;

/* loaded from: classes12.dex */
public class CTTblLayoutTypeImpl extends XmlComplexContentImpl implements CTTblLayoutType {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY)};
    private static final long serialVersionUID = 1;

    public CTTblLayoutTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType
    public STTblLayoutType.Enum getType() {
        STTblLayoutType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STTblLayoutType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType
    public STTblLayoutType xgetType() {
        STTblLayoutType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTblLayoutType) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType
    public void setType(STTblLayoutType.Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType
    public void xsetType(STTblLayoutType type) {
        synchronized (monitor()) {
            check_orphaned();
            STTblLayoutType target = (STTblLayoutType) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STTblLayoutType) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(type);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
