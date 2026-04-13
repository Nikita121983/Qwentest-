package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.DocumentationReferencesType;
import org.etsi.uri.x01903.v13.IdentifierType;
import org.etsi.uri.x01903.v13.ObjectIdentifierType;

/* loaded from: classes11.dex */
public class ObjectIdentifierTypeImpl extends XmlComplexContentImpl implements ObjectIdentifierType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "Identifier"), new QName(SignatureFacet.XADES_132_NS, "Description"), new QName(SignatureFacet.XADES_132_NS, "DocumentationReferences")};
    private static final long serialVersionUID = 1;

    public ObjectIdentifierTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.etsi.uri.x01903.v13.ObjectIdentifierType
    public IdentifierType getIdentifier() {
        IdentifierType identifierType;
        synchronized (monitor()) {
            check_orphaned();
            IdentifierType target = (IdentifierType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            identifierType = target == null ? null : target;
        }
        return identifierType;
    }

    @Override // org.etsi.uri.x01903.v13.ObjectIdentifierType
    public void setIdentifier(IdentifierType identifier) {
        generatedSetterHelperImpl(identifier, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.ObjectIdentifierType
    public IdentifierType addNewIdentifier() {
        IdentifierType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (IdentifierType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.ObjectIdentifierType
    public String getDescription() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.etsi.uri.x01903.v13.ObjectIdentifierType
    public XmlString xgetDescription() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.ObjectIdentifierType
    public boolean isSetDescription() {
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

    @Override // org.etsi.uri.x01903.v13.ObjectIdentifierType
    public void setDescription(String description) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(description);
        }
    }

    @Override // org.etsi.uri.x01903.v13.ObjectIdentifierType
    public void xsetDescription(XmlString description) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(description);
        }
    }

    @Override // org.etsi.uri.x01903.v13.ObjectIdentifierType
    public void unsetDescription() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.etsi.uri.x01903.v13.ObjectIdentifierType
    public DocumentationReferencesType getDocumentationReferences() {
        DocumentationReferencesType documentationReferencesType;
        synchronized (monitor()) {
            check_orphaned();
            DocumentationReferencesType target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            documentationReferencesType = target == null ? null : target;
        }
        return documentationReferencesType;
    }

    @Override // org.etsi.uri.x01903.v13.ObjectIdentifierType
    public boolean isSetDocumentationReferences() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.etsi.uri.x01903.v13.ObjectIdentifierType
    public void setDocumentationReferences(DocumentationReferencesType documentationReferences) {
        generatedSetterHelperImpl(documentationReferences, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.etsi.uri.x01903.v13.ObjectIdentifierType
    public DocumentationReferencesType addNewDocumentationReferences() {
        DocumentationReferencesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.etsi.uri.x01903.v13.ObjectIdentifierType
    public void unsetDocumentationReferences() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }
}
