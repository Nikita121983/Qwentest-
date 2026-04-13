package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

/* loaded from: classes12.dex */
public interface CTBookmark extends CTBookmarkRange {
    public static final DocumentFactory<CTBookmark> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbookmarkd672type");
    public static final SchemaType type = Factory.getType();

    String getName();

    void setName(String str);

    STString xgetName();

    void xsetName(STString sTString);
}
