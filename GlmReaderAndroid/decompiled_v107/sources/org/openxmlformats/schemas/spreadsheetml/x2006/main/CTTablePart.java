package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

/* loaded from: classes12.dex */
public interface CTTablePart extends XmlObject {
    public static final DocumentFactory<CTTablePart> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablepart1140type");
    public static final SchemaType type = Factory.getType();

    String getId();

    void setId(String str);

    STRelationshipId xgetId();

    void xsetId(STRelationshipId sTRelationshipId);
}
