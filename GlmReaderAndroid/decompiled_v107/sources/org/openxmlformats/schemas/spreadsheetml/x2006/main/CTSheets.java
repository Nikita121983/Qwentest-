package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTSheets extends XmlObject {
    public static final DocumentFactory<CTSheets> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsheets49fdtype");
    public static final SchemaType type = Factory.getType();

    CTSheet addNewSheet();

    CTSheet getSheetArray(int i);

    CTSheet[] getSheetArray();

    List<CTSheet> getSheetList();

    CTSheet insertNewSheet(int i);

    void removeSheet(int i);

    void setSheetArray(int i, CTSheet cTSheet);

    void setSheetArray(CTSheet[] cTSheetArr);

    int sizeOfSheetArray();
}
