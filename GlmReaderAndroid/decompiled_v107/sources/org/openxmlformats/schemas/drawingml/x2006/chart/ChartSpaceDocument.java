package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface ChartSpaceDocument extends XmlObject {
    public static final DocumentFactory<ChartSpaceDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "chartspace36e0doctype");
    public static final SchemaType type = Factory.getType();

    CTChartSpace addNewChartSpace();

    CTChartSpace getChartSpace();

    void setChartSpace(CTChartSpace cTChartSpace);
}
