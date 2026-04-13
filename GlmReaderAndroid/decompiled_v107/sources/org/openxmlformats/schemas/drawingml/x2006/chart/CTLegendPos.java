package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLegendPos;

/* loaded from: classes11.dex */
public interface CTLegendPos extends XmlObject {
    public static final DocumentFactory<CTLegendPos> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlegendpos053ftype");
    public static final SchemaType type = Factory.getType();

    STLegendPos.Enum getVal();

    boolean isSetVal();

    void setVal(STLegendPos.Enum r1);

    void unsetVal();

    STLegendPos xgetVal();

    void xsetVal(STLegendPos sTLegendPos);
}
