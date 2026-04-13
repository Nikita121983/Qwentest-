package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOrientation;

/* loaded from: classes11.dex */
public interface CTOrientation extends XmlObject {
    public static final DocumentFactory<CTOrientation> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctorientationcb16type");
    public static final SchemaType type = Factory.getType();

    STOrientation.Enum getVal();

    boolean isSetVal();

    void setVal(STOrientation.Enum r1);

    void unsetVal();

    STOrientation xgetVal();

    void xsetVal(STOrientation sTOrientation);
}
