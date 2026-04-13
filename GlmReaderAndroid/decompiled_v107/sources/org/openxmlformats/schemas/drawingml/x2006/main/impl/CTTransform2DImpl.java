package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STAngle;

/* loaded from: classes11.dex */
public class CTTransform2DImpl extends XmlComplexContentImpl implements CTTransform2D {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "off"), new QName(XSSFRelation.NS_DRAWINGML, "ext"), new QName("", "rot"), new QName("", "flipH"), new QName("", "flipV")};
    private static final long serialVersionUID = 1;

    public CTTransform2DImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public CTPoint2D getOff() {
        CTPoint2D cTPoint2D;
        synchronized (monitor()) {
            check_orphaned();
            CTPoint2D target = (CTPoint2D) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTPoint2D = target == null ? null : target;
        }
        return cTPoint2D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public boolean isSetOff() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public void setOff(CTPoint2D off) {
        generatedSetterHelperImpl(off, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public CTPoint2D addNewOff() {
        CTPoint2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPoint2D) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public void unsetOff() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public CTPositiveSize2D getExt() {
        CTPositiveSize2D cTPositiveSize2D;
        synchronized (monitor()) {
            check_orphaned();
            CTPositiveSize2D target = (CTPositiveSize2D) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTPositiveSize2D = target == null ? null : target;
        }
        return cTPositiveSize2D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public boolean isSetExt() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public void setExt(CTPositiveSize2D ext) {
        generatedSetterHelperImpl(ext, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public CTPositiveSize2D addNewExt() {
        CTPositiveSize2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPositiveSize2D) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public void unsetExt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public int getRot() {
        int intValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
            intValue = target == null ? 0 : target.getIntValue();
        }
        return intValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public STAngle xgetRot() {
        STAngle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STAngle) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STAngle) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public boolean isSetRot() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public void setRot(int rot) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setIntValue(rot);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public void xsetRot(STAngle rot) {
        synchronized (monitor()) {
            check_orphaned();
            STAngle target = (STAngle) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STAngle) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(rot);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public void unsetRot() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public boolean getFlipH() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public XmlBoolean xgetFlipH() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public boolean isSetFlipH() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public void setFlipH(boolean flipH) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setBooleanValue(flipH);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public void xsetFlipH(XmlBoolean flipH) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(flipH);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public void unsetFlipH() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public boolean getFlipV() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public XmlBoolean xgetFlipV() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public boolean isSetFlipV() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public void setFlipV(boolean flipV) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setBooleanValue(flipV);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public void xsetFlipV(XmlBoolean flipV) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(flipV);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D
    public void unsetFlipV() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
