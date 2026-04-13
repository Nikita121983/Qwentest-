package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;

/* loaded from: classes12.dex */
public class CTPageMarginsImpl extends XmlComplexContentImpl implements CTPageMargins {
    private static final QName[] PROPERTY_QNAME = {new QName("", "left"), new QName("", "right"), new QName("", "top"), new QName("", "bottom"), new QName("", "header"), new QName("", "footer")};
    private static final long serialVersionUID = 1;

    public CTPageMarginsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public double getLeft() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public XmlDouble xgetLeft() {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public void setLeft(double left) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setDoubleValue(left);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public void xsetLeft(XmlDouble left) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDouble target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlDouble) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(left);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public double getRight() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public XmlDouble xgetRight() {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public void setRight(double right) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setDoubleValue(right);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public void xsetRight(XmlDouble right) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDouble target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (XmlDouble) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(right);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public double getTop() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public XmlDouble xgetTop() {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public void setTop(double top) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setDoubleValue(top);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public void xsetTop(XmlDouble top) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDouble target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (XmlDouble) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(top);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public double getBottom() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public XmlDouble xgetBottom() {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public void setBottom(double bottom) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setDoubleValue(bottom);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public void xsetBottom(XmlDouble bottom) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDouble target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (XmlDouble) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(bottom);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public double getHeader() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public XmlDouble xgetHeader() {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public void setHeader(double header) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setDoubleValue(header);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public void xsetHeader(XmlDouble header) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDouble target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (XmlDouble) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(header);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public double getFooter() {
        double doubleValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            doubleValue = target == null ? 0.0d : target.getDoubleValue();
        }
        return doubleValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public XmlDouble xgetFooter() {
        XmlDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public void setFooter(double footer) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setDoubleValue(footer);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins
    public void xsetFooter(XmlDouble footer) {
        synchronized (monitor()) {
            check_orphaned();
            XmlDouble target = (XmlDouble) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (XmlDouble) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(footer);
        }
    }
}
