package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTHoleSize extends XmlObject {
    public static final DocumentFactory<CTHoleSize> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctholesize0f3btype");
    public static final SchemaType type = Factory.getType();

    Object getVal();

    boolean isSetVal();

    void setVal(Object obj);

    void unsetVal();

    STHoleSize xgetVal();

    void xsetVal(STHoleSize sTHoleSize);
}
