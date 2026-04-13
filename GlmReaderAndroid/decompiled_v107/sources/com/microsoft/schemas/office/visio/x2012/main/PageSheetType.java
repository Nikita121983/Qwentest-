package com.microsoft.schemas.office.visio.x2012.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes.dex */
public interface PageSheetType extends SheetType {
    public static final DocumentFactory<PageSheetType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "pagesheettype679btype");
    public static final SchemaType type = Factory.getType();

    String getUniqueID();

    boolean isSetUniqueID();

    void setUniqueID(String str);

    void unsetUniqueID();

    XmlString xgetUniqueID();

    void xsetUniqueID(XmlString xmlString);
}
