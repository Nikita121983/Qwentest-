package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontScalePercentOrPercentString;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextSpacingPercentOrPercentString;

/* loaded from: classes11.dex */
public class CTTextNormalAutofitImpl extends XmlComplexContentImpl implements CTTextNormalAutofit {
    private static final QName[] PROPERTY_QNAME = {new QName("", "fontScale"), new QName("", "lnSpcReduction")};
    private static final long serialVersionUID = 1;

    public CTTextNormalAutofitImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit
    public Object getFontScale() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit
    public STTextFontScalePercentOrPercentString xgetFontScale() {
        STTextFontScalePercentOrPercentString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextFontScalePercentOrPercentString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STTextFontScalePercentOrPercentString) get_default_attribute_value(PROPERTY_QNAME[0]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit
    public boolean isSetFontScale() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit
    public void setFontScale(Object fontScale) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(fontScale);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit
    public void xsetFontScale(STTextFontScalePercentOrPercentString fontScale) {
        synchronized (monitor()) {
            check_orphaned();
            STTextFontScalePercentOrPercentString target = (STTextFontScalePercentOrPercentString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STTextFontScalePercentOrPercentString) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(fontScale);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit
    public void unsetFontScale() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit
    public Object getLnSpcReduction() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit
    public STTextSpacingPercentOrPercentString xgetLnSpcReduction() {
        STTextSpacingPercentOrPercentString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTextSpacingPercentOrPercentString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STTextSpacingPercentOrPercentString) get_default_attribute_value(PROPERTY_QNAME[1]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit
    public boolean isSetLnSpcReduction() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit
    public void setLnSpcReduction(Object lnSpcReduction) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setObjectValue(lnSpcReduction);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit
    public void xsetLnSpcReduction(STTextSpacingPercentOrPercentString lnSpcReduction) {
        synchronized (monitor()) {
            check_orphaned();
            STTextSpacingPercentOrPercentString target = (STTextSpacingPercentOrPercentString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STTextSpacingPercentOrPercentString) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(lnSpcReduction);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit
    public void unsetLnSpcReduction() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
