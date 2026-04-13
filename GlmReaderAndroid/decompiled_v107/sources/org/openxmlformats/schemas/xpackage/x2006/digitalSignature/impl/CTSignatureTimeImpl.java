package org.openxmlformats.schemas.xpackage.x2006.digitalSignature.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.STFormat;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.STValue;

/* loaded from: classes12.dex */
public class CTSignatureTimeImpl extends XmlComplexContentImpl implements CTSignatureTime {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/package/2006/digital-signature", "Format"), new QName("http://schemas.openxmlformats.org/package/2006/digital-signature", "Value")};
    private static final long serialVersionUID = 1;

    public CTSignatureTimeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime
    public String getFormat() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime
    public STFormat xgetFormat() {
        STFormat target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STFormat) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime
    public void setFormat(String format) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(format);
        }
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime
    public void xsetFormat(STFormat format) {
        synchronized (monitor()) {
            check_orphaned();
            STFormat target = (STFormat) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (STFormat) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(format);
        }
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime
    public String getValue() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime
    public STValue xgetValue() {
        STValue target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime
    public void setValue(String value) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(value);
        }
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTSignatureTime
    public void xsetValue(STValue value) {
        synchronized (monitor()) {
            check_orphaned();
            STValue target = (STValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (STValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(value);
        }
    }
}
