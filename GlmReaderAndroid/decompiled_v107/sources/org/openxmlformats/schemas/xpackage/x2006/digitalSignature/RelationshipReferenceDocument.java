package org.openxmlformats.schemas.xpackage.x2006.digitalSignature;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface RelationshipReferenceDocument extends XmlObject {
    public static final DocumentFactory<RelationshipReferenceDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "relationshipreference8903doctype");
    public static final SchemaType type = Factory.getType();

    CTRelationshipReference addNewRelationshipReference();

    CTRelationshipReference getRelationshipReference();

    void setRelationshipReference(CTRelationshipReference cTRelationshipReference);
}
