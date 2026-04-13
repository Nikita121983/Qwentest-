package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTDigSigBlob;

/* loaded from: classes11.dex */
public class CTDigSigBlobImpl extends XmlComplexContentImpl implements CTDigSigBlob {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "blob")};
    private static final long serialVersionUID = 1;

    public CTDigSigBlobImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTDigSigBlob
    public byte[] getBlob() {
        byte[] byteArrayValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            byteArrayValue = target == null ? null : target.getByteArrayValue();
        }
        return byteArrayValue;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTDigSigBlob
    public XmlBase64Binary xgetBlob() {
        XmlBase64Binary target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTDigSigBlob
    public void setBlob(byte[] blob) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setByteArrayValue(blob);
        }
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTDigSigBlob
    public void xsetBlob(XmlBase64Binary blob) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBase64Binary target = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (XmlBase64Binary) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(blob);
        }
    }
}
