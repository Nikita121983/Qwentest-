package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrValType;

/* loaded from: classes11.dex */
public interface CTErrValType extends XmlObject {
    public static final DocumentFactory<CTErrValType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cterrvaltyped0e6type");
    public static final SchemaType type = Factory.getType();

    STErrValType.Enum getVal();

    boolean isSetVal();

    void setVal(STErrValType.Enum r1);

    void unsetVal();

    STErrValType xgetVal();

    void xsetVal(STErrValType sTErrValType);
}
