package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface WorkbookDocument extends XmlObject {
    public static final DocumentFactory<WorkbookDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "workbookec17doctype");
    public static final SchemaType type = Factory.getType();

    CTWorkbook addNewWorkbook();

    CTWorkbook getWorkbook();

    void setWorkbook(CTWorkbook cTWorkbook);
}
