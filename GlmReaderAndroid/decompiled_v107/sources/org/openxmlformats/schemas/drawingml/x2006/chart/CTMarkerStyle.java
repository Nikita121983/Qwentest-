package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STMarkerStyle;

/* loaded from: classes11.dex */
public interface CTMarkerStyle extends XmlObject {
    public static final DocumentFactory<CTMarkerStyle> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmarkerstyle1f6ftype");
    public static final SchemaType type = Factory.getType();

    STMarkerStyle.Enum getVal();

    void setVal(STMarkerStyle.Enum r1);

    STMarkerStyle xgetVal();

    void xsetVal(STMarkerStyle sTMarkerStyle);
}
