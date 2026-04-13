package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTRotY extends XmlObject {
    public static final DocumentFactory<CTRotY> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctroty8f1atype");
    public static final SchemaType type = Factory.getType();

    int getVal();

    boolean isSetVal();

    void setVal(int i);

    void unsetVal();

    STRotY xgetVal();

    void xsetVal(STRotY sTRotY);
}
