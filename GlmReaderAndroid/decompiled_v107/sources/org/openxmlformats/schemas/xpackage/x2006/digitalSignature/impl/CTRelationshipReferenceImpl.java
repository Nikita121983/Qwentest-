package org.openxmlformats.schemas.xpackage.x2006.digitalSignature.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTRelationshipReference;

/* loaded from: classes12.dex */
public class CTRelationshipReferenceImpl extends JavaStringHolderEx implements CTRelationshipReference {
    private static final QName[] PROPERTY_QNAME = {new QName("", "SourceId")};
    private static final long serialVersionUID = 1;

    public CTRelationshipReferenceImpl(SchemaType sType) {
        super(sType, true);
    }

    protected CTRelationshipReferenceImpl(SchemaType sType, boolean b) {
        super(sType, b);
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTRelationshipReference
    public String getSourceId() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTRelationshipReference
    public XmlString xgetSourceId() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTRelationshipReference
    public void setSourceId(String sourceId) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(sourceId);
        }
    }

    @Override // org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTRelationshipReference
    public void xsetSourceId(XmlString sourceId) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[0]);
            }
            target.set(sourceId);
        }
    }
}
