package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickLblPos;

/* loaded from: classes11.dex */
public interface CTTickLblPos extends XmlObject {
    public static final DocumentFactory<CTTickLblPos> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctticklblposff61type");
    public static final SchemaType type = Factory.getType();

    STTickLblPos.Enum getVal();

    boolean isSetVal();

    void setVal(STTickLblPos.Enum r1);

    void unsetVal();

    STTickLblPos xgetVal();

    void xsetVal(STTickLblPos sTTickLblPos);
}
