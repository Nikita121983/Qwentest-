package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.poi.ss.util.CellUtil;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTEffectExtent;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapDistance;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapText;

/* loaded from: classes11.dex */
public class CTWrapSquareImpl extends XmlComplexContentImpl implements CTWrapSquare {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "effectExtent"), new QName("", CellUtil.WRAP_TEXT), new QName("", "distT"), new QName("", "distB"), new QName("", "distL"), new QName("", "distR")};
    private static final long serialVersionUID = 1;

    public CTWrapSquareImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public CTEffectExtent getEffectExtent() {
        CTEffectExtent cTEffectExtent;
        synchronized (monitor()) {
            check_orphaned();
            CTEffectExtent target = (CTEffectExtent) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTEffectExtent = target == null ? null : target;
        }
        return cTEffectExtent;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public boolean isSetEffectExtent() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void setEffectExtent(CTEffectExtent effectExtent) {
        generatedSetterHelperImpl(effectExtent, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public CTEffectExtent addNewEffectExtent() {
        CTEffectExtent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEffectExtent) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void unsetEffectExtent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public STWrapText.Enum getWrapText() {
        STWrapText.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            r1 = target == null ? null : (STWrapText.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public STWrapText xgetWrapText() {
        STWrapText target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STWrapText) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void setWrapText(STWrapText.Enum wrapText) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setEnumValue(wrapText);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void xsetWrapText(STWrapText wrapText) {
        synchronized (monitor()) {
            check_orphaned();
            STWrapText target = (STWrapText) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STWrapText) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(wrapText);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public long getDistT() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public STWrapDistance xgetDistT() {
        STWrapDistance target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public boolean isSetDistT() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void setDistT(long distT) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setLongValue(distT);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void xsetDistT(STWrapDistance distT) {
        synchronized (monitor()) {
            check_orphaned();
            STWrapDistance target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STWrapDistance) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(distT);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void unsetDistT() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public long getDistB() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public STWrapDistance xgetDistB() {
        STWrapDistance target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public boolean isSetDistB() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void setDistB(long distB) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setLongValue(distB);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void xsetDistB(STWrapDistance distB) {
        synchronized (monitor()) {
            check_orphaned();
            STWrapDistance target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STWrapDistance) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(distB);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void unsetDistB() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public long getDistL() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public STWrapDistance xgetDistL() {
        STWrapDistance target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public boolean isSetDistL() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void setDistL(long distL) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setLongValue(distL);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void xsetDistL(STWrapDistance distL) {
        synchronized (monitor()) {
            check_orphaned();
            STWrapDistance target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STWrapDistance) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(distL);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void unsetDistL() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public long getDistR() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public STWrapDistance xgetDistR() {
        STWrapDistance target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public boolean isSetDistR() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void setDistR(long distR) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setLongValue(distR);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void xsetDistR(STWrapDistance distR) {
        synchronized (monitor()) {
            check_orphaned();
            STWrapDistance target = (STWrapDistance) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STWrapDistance) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(distR);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
    public void unsetDistR() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }
}
