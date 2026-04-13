package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarGrouping;

/* loaded from: classes11.dex */
public interface CTBarGrouping extends XmlObject {
    public static final DocumentFactory<CTBarGrouping> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbargrouping8bf0type");
    public static final SchemaType type = Factory.getType();

    STBarGrouping.Enum getVal();

    boolean isSetVal();

    void setVal(STBarGrouping.Enum r1);

    void unsetVal();

    STBarGrouping xgetVal();

    void xsetVal(STBarGrouping sTBarGrouping);
}
