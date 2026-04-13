package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STDispBlanksAs;

/* loaded from: classes11.dex */
public interface CTDispBlanksAs extends XmlObject {
    public static final DocumentFactory<CTDispBlanksAs> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdispblanksas3069type");
    public static final SchemaType type = Factory.getType();

    STDispBlanksAs.Enum getVal();

    boolean isSetVal();

    void setVal(STDispBlanksAs.Enum r1);

    void unsetVal();

    STDispBlanksAs xgetVal();

    void xsetVal(STDispBlanksAs sTDispBlanksAs);
}
