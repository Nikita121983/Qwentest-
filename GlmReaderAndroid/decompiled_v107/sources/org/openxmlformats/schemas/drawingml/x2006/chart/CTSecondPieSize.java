package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTSecondPieSize extends XmlObject {
    public static final DocumentFactory<CTSecondPieSize> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsecondpiesize24edtype");
    public static final SchemaType type = Factory.getType();

    Object getVal();

    boolean isSetVal();

    void setVal(Object obj);

    void unsetVal();

    STSecondPieSize xgetVal();

    void xsetVal(STSecondPieSize sTSecondPieSize);
}
