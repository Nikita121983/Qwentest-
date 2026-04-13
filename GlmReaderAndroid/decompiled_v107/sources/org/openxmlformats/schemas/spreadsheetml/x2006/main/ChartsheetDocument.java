package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface ChartsheetDocument extends XmlObject {
    public static final DocumentFactory<ChartsheetDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "chartsheet99dedoctype");
    public static final SchemaType type = Factory.getType();

    CTChartsheet addNewChartsheet();

    CTChartsheet getChartsheet();

    void setChartsheet(CTChartsheet cTChartsheet);
}
