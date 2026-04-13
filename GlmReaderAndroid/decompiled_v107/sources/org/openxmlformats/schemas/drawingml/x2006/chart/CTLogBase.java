package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTLogBase extends XmlObject {
    public static final DocumentFactory<CTLogBase> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlogbase9191type");
    public static final SchemaType type = Factory.getType();

    double getVal();

    void setVal(double d);

    STLogBase xgetVal();

    void xsetVal(STLogBase sTLogBase);
}
