package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument;

/* loaded from: classes11.dex */
public class PropertiesDocumentImpl extends XmlComplexContentImpl implements PropertiesDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/officeDocument/2006/extended-properties", "Properties")};
    private static final long serialVersionUID = 1;

    public PropertiesDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument
    public CTProperties getProperties() {
        CTProperties cTProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTProperties target = (CTProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTProperties = target == null ? null : target;
        }
        return cTProperties;
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument
    public void setProperties(CTProperties properties) {
        generatedSetterHelperImpl(properties, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.PropertiesDocument
    public CTProperties addNewProperties() {
        CTProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
