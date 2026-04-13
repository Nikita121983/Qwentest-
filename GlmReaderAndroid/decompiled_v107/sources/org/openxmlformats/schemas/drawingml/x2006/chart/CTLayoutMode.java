package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLayoutMode;

/* loaded from: classes11.dex */
public interface CTLayoutMode extends XmlObject {
    public static final DocumentFactory<CTLayoutMode> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlayoutmode53eftype");
    public static final SchemaType type = Factory.getType();

    STLayoutMode.Enum getVal();

    boolean isSetVal();

    void setVal(STLayoutMode.Enum r1);

    void unsetVal();

    STLayoutMode xgetVal();

    void xsetVal(STLayoutMode sTLayoutMode);
}
