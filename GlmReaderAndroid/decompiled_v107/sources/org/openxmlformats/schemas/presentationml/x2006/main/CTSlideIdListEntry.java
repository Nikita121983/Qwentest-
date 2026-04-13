package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

/* loaded from: classes11.dex */
public interface CTSlideIdListEntry extends XmlObject {
    public static final DocumentFactory<CTSlideIdListEntry> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctslideidlistentry427dtype");
    public static final SchemaType type = Factory.getType();

    CTExtensionList addNewExtLst();

    CTExtensionList getExtLst();

    long getId();

    String getId2();

    boolean isSetExtLst();

    void setExtLst(CTExtensionList cTExtensionList);

    void setId(long j);

    void setId2(String str);

    void unsetExtLst();

    STSlideId xgetId();

    STRelationshipId xgetId2();

    void xsetId(STSlideId sTSlideId);

    void xsetId2(STRelationshipId sTRelationshipId);
}
