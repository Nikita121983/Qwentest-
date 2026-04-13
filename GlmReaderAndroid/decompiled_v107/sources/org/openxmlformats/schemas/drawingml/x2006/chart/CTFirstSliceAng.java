package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTFirstSliceAng extends XmlObject {
    public static final DocumentFactory<CTFirstSliceAng> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfirstsliceang0ceetype");
    public static final SchemaType type = Factory.getType();

    int getVal();

    boolean isSetVal();

    void setVal(int i);

    void unsetVal();

    STFirstSliceAng xgetVal();

    void xsetVal(STFirstSliceAng sTFirstSliceAng);
}
