package com.microsoft.schemas.office.word.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.microsoft.schemas.office.word.CTWrap;
import com.microsoft.schemas.office.word.STHorizontalAnchor;
import com.microsoft.schemas.office.word.STHorizontalAnchor$Enum;
import com.microsoft.schemas.office.word.STVerticalAnchor;
import com.microsoft.schemas.office.word.STVerticalAnchor$Enum;
import com.microsoft.schemas.office.word.STWrapSide;
import com.microsoft.schemas.office.word.STWrapSide$Enum;
import com.microsoft.schemas.office.word.STWrapType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class CTWrapImpl extends XmlComplexContentImpl implements CTWrap {
    private static final QName[] PROPERTY_QNAME = {new QName("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY), new QName("", "side"), new QName("", "anchorx"), new QName("", "anchory")};
    private static final long serialVersionUID = 1;

    public CTWrapImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public STWrapType.Enum getType() {
        STWrapType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            r1 = target == null ? null : (STWrapType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public STWrapType xgetType() {
        STWrapType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STWrapType) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public void setType(STWrapType.Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public void xsetType(STWrapType type) {
        synchronized (monitor()) {
            check_orphaned();
            STWrapType target = (STWrapType) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STWrapType) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(type);
        }
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public STWrapSide$Enum getSide() {
        STWrapSide$Enum sTWrapSide$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            sTWrapSide$Enum = target == null ? null : (STWrapSide$Enum) target.getEnumValue();
        }
        return sTWrapSide$Enum;
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public STWrapSide xgetSide() {
        STWrapSide target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public boolean isSetSide() {
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

    @Override // com.microsoft.schemas.office.word.CTWrap
    public void setSide(STWrapSide$Enum side) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(side);
        }
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public void xsetSide(STWrapSide side) {
        synchronized (monitor()) {
            check_orphaned();
            STWrapSide target = get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STWrapSide) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(side);
        }
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public void unsetSide() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public STHorizontalAnchor$Enum getAnchorx() {
        STHorizontalAnchor$Enum sTHorizontalAnchor$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            sTHorizontalAnchor$Enum = target == null ? null : (STHorizontalAnchor$Enum) target.getEnumValue();
        }
        return sTHorizontalAnchor$Enum;
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public STHorizontalAnchor xgetAnchorx() {
        STHorizontalAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public boolean isSetAnchorx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public void setAnchorx(STHorizontalAnchor$Enum anchorx) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setEnumValue(anchorx);
        }
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public void xsetAnchorx(STHorizontalAnchor anchorx) {
        synchronized (monitor()) {
            check_orphaned();
            STHorizontalAnchor target = get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STHorizontalAnchor) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(anchorx);
        }
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public void unsetAnchorx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public STVerticalAnchor$Enum getAnchory() {
        STVerticalAnchor$Enum sTVerticalAnchor$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            sTVerticalAnchor$Enum = target == null ? null : (STVerticalAnchor$Enum) target.getEnumValue();
        }
        return sTVerticalAnchor$Enum;
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public STVerticalAnchor xgetAnchory() {
        STVerticalAnchor target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public boolean isSetAnchory() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public void setAnchory(STVerticalAnchor$Enum anchory) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setEnumValue(anchory);
        }
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public void xsetAnchory(STVerticalAnchor anchory) {
        synchronized (monitor()) {
            check_orphaned();
            STVerticalAnchor target = get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STVerticalAnchor) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(anchory);
        }
    }

    @Override // com.microsoft.schemas.office.word.CTWrap
    public void unsetAnchory() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }
}
