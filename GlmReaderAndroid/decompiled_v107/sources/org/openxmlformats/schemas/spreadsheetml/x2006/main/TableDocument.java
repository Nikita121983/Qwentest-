package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface TableDocument extends XmlObject {
    public static final DocumentFactory<TableDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "table0b99doctype");
    public static final SchemaType type = Factory.getType();

    CTTable addNewTable();

    CTTable getTable();

    void setTable(CTTable cTTable);
}
