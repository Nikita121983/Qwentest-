package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STErrDir;

/* loaded from: classes11.dex */
public interface CTErrDir extends XmlObject {
    public static final DocumentFactory<CTErrDir> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cterrdirc214type");
    public static final SchemaType type = Factory.getType();

    STErrDir.Enum getVal();

    void setVal(STErrDir.Enum r1);

    STErrDir xgetVal();

    void xsetVal(STErrDir sTErrDir);
}
