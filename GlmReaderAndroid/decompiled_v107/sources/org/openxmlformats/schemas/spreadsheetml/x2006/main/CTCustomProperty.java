package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

/* loaded from: classes12.dex */
public interface CTCustomProperty extends XmlObject {
    public static final DocumentFactory<CTCustomProperty> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcustompropertybfeftype");
    public static final SchemaType type = Factory.getType();

    String getId();

    String getName();

    void setId(String str);

    void setName(String str);

    STRelationshipId xgetId();

    STXstring xgetName();

    void xsetId(STRelationshipId sTRelationshipId);

    void xsetName(STXstring sTXstring);
}
