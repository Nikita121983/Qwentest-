package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextTabAlignType;

/* loaded from: classes11.dex */
public class CTTextTabStopImpl extends XmlComplexContentImpl implements CTTextTabStop {
    private static final QName[] PROPERTY_QNAME = {new QName("", "pos"), new QName("", "algn")};
    private static final long serialVersionUID = 1;

    public CTTextTabStopImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop
    public Object getPos() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop
    public STCoordinate32 xgetPos() {
        STCoordinate32 target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop
    public boolean isSetPos() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop
    public void setPos(Object pos) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(pos);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop
    public void xsetPos(STCoordinate32 pos) {
        synchronized (monitor()) {
            check_orphaned();
            STCoordinate32 target = (STCoordinate32) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STCoordinate32) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(pos);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop
    public void unsetPos() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop
    public STTextTabAlignType.Enum getAlgn() {
        STTextTabAlignType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            r1 = target == null ? null : (STTextTabAlignType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop
    public STTextTabAlignType xgetAlgn() {
        STTextTabAlignType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextTabAlignType) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop
    public boolean isSetAlgn() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop
    public void setAlgn(STTextTabAlignType.Enum algn) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(algn);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop
    public void xsetAlgn(STTextTabAlignType algn) {
        synchronized (monitor()) {
            check_orphaned();
            STTextTabAlignType target = (STTextTabAlignType) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STTextTabAlignType) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(algn);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop
    public void unsetAlgn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
