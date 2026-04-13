package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension;

/* loaded from: classes11.dex */
public class CTOfficeArtExtensionImpl extends XmlComplexContentImpl implements CTOfficeArtExtension {
    private static final QName[] PROPERTY_QNAME = {new QName("", "uri")};
    private static final long serialVersionUID = 1;

    public CTOfficeArtExtensionImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension
    public String getUri() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension
    public XmlToken xgetUri() {
        XmlToken target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension
    public void setUri(String uri) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(uri);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtension
    public void xsetUri(XmlToken uri) {
        synchronized (monitor()) {
            check_orphaned();
            XmlToken target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlToken) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(uri);
        }
    }
}
