package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STGrouping;

/* loaded from: classes11.dex */
public interface CTGrouping extends XmlObject {
    public static final DocumentFactory<CTGrouping> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgroupingdcd9type");
    public static final SchemaType type = Factory.getType();

    STGrouping.Enum getVal();

    boolean isSetVal();

    void setVal(STGrouping.Enum r1);

    void unsetVal();

    STGrouping xgetVal();

    void xsetVal(STGrouping sTGrouping);
}
