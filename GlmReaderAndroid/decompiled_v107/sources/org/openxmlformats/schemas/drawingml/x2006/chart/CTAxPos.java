package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STAxPos;

/* loaded from: classes11.dex */
public interface CTAxPos extends XmlObject {
    public static final DocumentFactory<CTAxPos> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctaxposff69type");
    public static final SchemaType type = Factory.getType();

    STAxPos.Enum getVal();

    void setVal(STAxPos.Enum r1);

    STAxPos xgetVal();

    void xsetVal(STAxPos sTAxPos);
}
