package org.apache.poi.schemas.vmldrawing.impl;

import javax.xml.namespace.QName;
import org.apache.poi.schemas.vmldrawing.CTXML;
import org.apache.poi.schemas.vmldrawing.XmlDocument;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes10.dex */
public class XmlDocumentImpl extends XmlComplexContentImpl implements XmlDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-poi-apache-org:vmldrawing", "xml")};
    private static final long serialVersionUID = 1;

    public XmlDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.poi.schemas.vmldrawing.XmlDocument
    public CTXML getXml() {
        CTXML ctxml;
        synchronized (monitor()) {
            check_orphaned();
            CTXML target = (CTXML) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            ctxml = target == null ? null : target;
        }
        return ctxml;
    }

    @Override // org.apache.poi.schemas.vmldrawing.XmlDocument
    public void setXml(CTXML xml) {
        generatedSetterHelperImpl(xml, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.poi.schemas.vmldrawing.XmlDocument
    public CTXML addNewXml() {
        CTXML target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTXML) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
