package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STRectAlignment;
import org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode;

/* loaded from: classes11.dex */
public class CTTileInfoPropertiesImpl extends XmlComplexContentImpl implements CTTileInfoProperties {
    private static final QName[] PROPERTY_QNAME = {new QName("", "tx"), new QName("", "ty"), new QName("", "sx"), new QName("", "sy"), new QName("", "flip"), new QName("", "algn")};
    private static final long serialVersionUID = 1;

    public CTTileInfoPropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public Object getTx() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public STCoordinate xgetTx() {
        STCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public boolean isSetTx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void setTx(Object tx) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(tx);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void xsetTx(STCoordinate tx) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate target = (STCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(tx);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void unsetTx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public Object getTy() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public STCoordinate xgetTy() {
        STCoordinate target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public boolean isSetTy() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void setTy(Object ty) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setObjectValue(ty);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void xsetTy(STCoordinate ty) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate target = (STCoordinate) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STCoordinate) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(ty);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void unsetTy() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public Object getSx() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public STPercentage xgetSx() {
        STPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public boolean isSetSx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void setSx(Object sx) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(sx);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void xsetSx(STPercentage sx) {
        synchronized (monitor()) {
            check_orphaned();
            STPercentage target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STPercentage) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(sx);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void unsetSx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public Object getSy() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public STPercentage xgetSy() {
        STPercentage target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public boolean isSetSy() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void setSy(Object sy) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setObjectValue(sy);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void xsetSy(STPercentage sy) {
        synchronized (monitor()) {
            check_orphaned();
            STPercentage target = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STPercentage) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(sy);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void unsetSy() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public STTileFlipMode.Enum getFlip() {
        STTileFlipMode.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
            r1 = target == null ? null : (STTileFlipMode.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public STTileFlipMode xgetFlip() {
        STTileFlipMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTileFlipMode) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STTileFlipMode) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public boolean isSetFlip() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void setFlip(STTileFlipMode.Enum flip) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setEnumValue(flip);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void xsetFlip(STTileFlipMode flip) {
        synchronized (monitor()) {
            check_orphaned();
            STTileFlipMode target = (STTileFlipMode) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STTileFlipMode) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(flip);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void unsetFlip() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public STRectAlignment.Enum getAlgn() {
        STRectAlignment.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            r1 = target == null ? null : (STRectAlignment.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public STRectAlignment xgetAlgn() {
        STRectAlignment target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRectAlignment) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public boolean isSetAlgn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void setAlgn(STRectAlignment.Enum algn) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setEnumValue(algn);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void xsetAlgn(STRectAlignment algn) {
        synchronized (monitor()) {
            check_orphaned();
            STRectAlignment target = (STRectAlignment) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STRectAlignment) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(algn);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTileInfoProperties
    public void unsetAlgn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }
}
