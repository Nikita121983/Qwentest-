package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STAlignH;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STAlignH$Enum;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STPositionOffset;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STRelFromH;

/* loaded from: classes11.dex */
public class CTPosHImpl extends XmlComplexContentImpl implements CTPosH {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "align"), new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "posOffset"), new QName("", "relativeFrom")};
    private static final long serialVersionUID = 1;

    public CTPosHImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public STAlignH$Enum getAlign() {
        STAlignH$Enum sTAlignH$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            sTAlignH$Enum = target == null ? null : (STAlignH$Enum) target.getEnumValue();
        }
        return sTAlignH$Enum;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public STAlignH xgetAlign() {
        STAlignH target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public boolean isSetAlign() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public void setAlign(STAlignH$Enum align) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setEnumValue(align);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public void xsetAlign(STAlignH align) {
        synchronized (monitor()) {
            check_orphaned();
            STAlignH target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (STAlignH) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(align);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public void unsetAlign() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public int getPosOffset() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target != null) {
                i = target.getIntValue();
            }
        }
        return i;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public STPositionOffset xgetPosOffset() {
        STPositionOffset target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STPositionOffset) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public boolean isSetPosOffset() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public void setPosOffset(int posOffset) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setIntValue(posOffset);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public void xsetPosOffset(STPositionOffset posOffset) {
        synchronized (monitor()) {
            check_orphaned();
            STPositionOffset target = (STPositionOffset) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (STPositionOffset) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(posOffset);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public void unsetPosOffset() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public STRelFromH.Enum getRelativeFrom() {
        STRelFromH.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            r1 = target == null ? null : (STRelFromH.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public STRelFromH xgetRelativeFrom() {
        STRelFromH target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STRelFromH) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public void setRelativeFrom(STRelFromH.Enum relativeFrom) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setEnumValue(relativeFrom);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
    public void xsetRelativeFrom(STRelFromH relativeFrom) {
        synchronized (monitor()) {
            check_orphaned();
            STRelFromH target = (STRelFromH) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STRelFromH) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(relativeFrom);
        }
    }
}
