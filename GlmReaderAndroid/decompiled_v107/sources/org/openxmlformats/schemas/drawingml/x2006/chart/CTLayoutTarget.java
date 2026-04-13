package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLayoutTarget;

/* loaded from: classes11.dex */
public interface CTLayoutTarget extends XmlObject {
    public static final DocumentFactory<CTLayoutTarget> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlayouttarget1001type");
    public static final SchemaType type = Factory.getType();

    STLayoutTarget.Enum getVal();

    boolean isSetVal();

    void setVal(STLayoutTarget.Enum r1);

    void unsetVal();

    STLayoutTarget xgetVal();

    void xsetVal(STLayoutTarget sTLayoutTarget);
}
