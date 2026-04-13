package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STShape;

/* loaded from: classes11.dex */
public interface CTShape extends XmlObject {
    public static final DocumentFactory<CTShape> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctshape89e5type");
    public static final SchemaType type = Factory.getType();

    STShape.Enum getVal();

    boolean isSetVal();

    void setVal(STShape.Enum r1);

    void unsetVal();

    STShape xgetVal();

    void xsetVal(STShape sTShape);
}
