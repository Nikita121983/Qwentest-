package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface ObjectIdentifierType extends XmlObject {
    public static final DocumentFactory<ObjectIdentifierType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "objectidentifiertype2f56type");
    public static final SchemaType type = Factory.getType();

    DocumentationReferencesType addNewDocumentationReferences();

    IdentifierType addNewIdentifier();

    String getDescription();

    DocumentationReferencesType getDocumentationReferences();

    IdentifierType getIdentifier();

    boolean isSetDescription();

    boolean isSetDocumentationReferences();

    void setDescription(String str);

    void setDocumentationReferences(DocumentationReferencesType documentationReferencesType);

    void setIdentifier(IdentifierType identifierType);

    void unsetDescription();

    void unsetDocumentationReferences();

    XmlString xgetDescription();

    void xsetDescription(XmlString xmlString);
}
