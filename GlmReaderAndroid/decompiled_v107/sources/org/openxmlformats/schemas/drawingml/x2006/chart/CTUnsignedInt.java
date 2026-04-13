package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTUnsignedInt extends XmlObject {
    public static final DocumentFactory<CTUnsignedInt> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctunsignedinte8ectype");
    public static final SchemaType type = Factory.getType();

    long getVal();

    void setVal(long j);

    XmlUnsignedInt xgetVal();

    void xsetVal(XmlUnsignedInt xmlUnsignedInt);
}
