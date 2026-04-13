package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

/* loaded from: classes12.dex */
public interface CTAltChunk extends XmlObject {
    public static final DocumentFactory<CTAltChunk> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctaltchunk5c24type");
    public static final SchemaType type = Factory.getType();

    CTAltChunkPr addNewAltChunkPr();

    CTAltChunkPr getAltChunkPr();

    String getId();

    boolean isSetAltChunkPr();

    boolean isSetId();

    void setAltChunkPr(CTAltChunkPr cTAltChunkPr);

    void setId(String str);

    void unsetAltChunkPr();

    void unsetId();

    STRelationshipId xgetId();

    void xsetId(STRelationshipId sTRelationshipId);
}
