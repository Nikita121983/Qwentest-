package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTDepthPercent extends XmlObject {
    public static final DocumentFactory<CTDepthPercent> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdepthpercent117atype");
    public static final SchemaType type = Factory.getType();

    Object getVal();

    boolean isSetVal();

    void setVal(Object obj);

    void unsetVal();

    STDepthPercent xgetVal();

    void xsetVal(STDepthPercent sTDepthPercent);
}
