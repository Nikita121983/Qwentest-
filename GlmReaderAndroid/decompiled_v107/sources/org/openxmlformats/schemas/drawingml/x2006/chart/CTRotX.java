package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTRotX extends XmlObject {
    public static final DocumentFactory<CTRotX> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctrotx3c3btype");
    public static final SchemaType type = Factory.getType();

    byte getVal();

    boolean isSetVal();

    void setVal(byte b);

    void unsetVal();

    STRotX xgetVal();

    void xsetVal(STRotX sTRotX);
}
