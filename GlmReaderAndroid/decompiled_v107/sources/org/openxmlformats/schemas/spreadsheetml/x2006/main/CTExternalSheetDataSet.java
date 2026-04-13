package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTExternalSheetDataSet extends XmlObject {
    public static final DocumentFactory<CTExternalSheetDataSet> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctexternalsheetdataset07adtype");
    public static final SchemaType type = Factory.getType();

    CTExternalSheetData addNewSheetData();

    CTExternalSheetData getSheetDataArray(int i);

    CTExternalSheetData[] getSheetDataArray();

    List<CTExternalSheetData> getSheetDataList();

    CTExternalSheetData insertNewSheetData(int i);

    void removeSheetData(int i);

    void setSheetDataArray(int i, CTExternalSheetData cTExternalSheetData);

    void setSheetDataArray(CTExternalSheetData[] cTExternalSheetDataArr);

    int sizeOfSheetDataArray();
}
