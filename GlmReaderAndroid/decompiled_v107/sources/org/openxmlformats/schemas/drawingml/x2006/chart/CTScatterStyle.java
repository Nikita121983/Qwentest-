package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STScatterStyle;

/* loaded from: classes11.dex */
public interface CTScatterStyle extends XmlObject {
    public static final DocumentFactory<CTScatterStyle> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctscatterstyle94c9type");
    public static final SchemaType type = Factory.getType();

    STScatterStyle.Enum getVal();

    boolean isSetVal();

    void setVal(STScatterStyle.Enum r1);

    void unsetVal();

    STScatterStyle xgetVal();

    void xsetVal(STScatterStyle sTScatterStyle);
}
