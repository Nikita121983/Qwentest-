package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure;

/* loaded from: classes12.dex */
public class CTPageMarImpl extends XmlComplexContentImpl implements CTPageMar {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "top"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "right"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bottom"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "left"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "header"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "footer"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "gutter")};
    private static final long serialVersionUID = 1;

    public CTPageMarImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public Object getTop() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public STSignedTwipsMeasure xgetTop() {
        STSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public void setTop(Object top) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setObjectValue(top);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public void xsetTop(STSignedTwipsMeasure top) {
        synchronized (monitor()) {
            check_orphaned();
            STSignedTwipsMeasure target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (STSignedTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(top);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public Object getRight() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public STTwipsMeasure xgetRight() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public void setRight(Object right) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.setObjectValue(right);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public void xsetRight(STTwipsMeasure right) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[1]);
            }
            target.set(right);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public Object getBottom() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public STSignedTwipsMeasure xgetBottom() {
        STSignedTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public void setBottom(Object bottom) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setObjectValue(bottom);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public void xsetBottom(STSignedTwipsMeasure bottom) {
        synchronized (monitor()) {
            check_orphaned();
            STSignedTwipsMeasure target = (STSignedTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STSignedTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(bottom);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public Object getLeft() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public STTwipsMeasure xgetLeft() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public void setLeft(Object left) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setObjectValue(left);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public void xsetLeft(STTwipsMeasure left) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(left);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public Object getHeader() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public STTwipsMeasure xgetHeader() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public void setHeader(Object header) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setObjectValue(header);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public void xsetHeader(STTwipsMeasure header) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(header);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public Object getFooter() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public STTwipsMeasure xgetFooter() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public void setFooter(Object footer) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setObjectValue(footer);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public void xsetFooter(STTwipsMeasure footer) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(footer);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public Object getGutter() {
        Object objectValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            objectValue = target == null ? null : target.getObjectValue();
        }
        return objectValue;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public STTwipsMeasure xgetGutter() {
        STTwipsMeasure target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public void setGutter(Object gutter) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setObjectValue(gutter);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
    public void xsetGutter(STTwipsMeasure gutter) {
        synchronized (monitor()) {
            check_orphaned();
            STTwipsMeasure target = (STTwipsMeasure) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (STTwipsMeasure) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(gutter);
        }
    }
}
