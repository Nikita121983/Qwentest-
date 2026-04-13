package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

/* loaded from: classes12.dex */
public interface CTPageBorder extends CTBorder {
    public static final DocumentFactory<CTPageBorder> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpageborderd76dtype");
    public static final SchemaType type = Factory.getType();

    String getId();

    boolean isSetId();

    void setId(String str);

    void unsetId();

    STRelationshipId xgetId();

    void xsetId(STRelationshipId sTRelationshipId);
}
