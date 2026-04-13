package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTPerspective extends XmlObject {
    public static final DocumentFactory<CTPerspective> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctperspectivefd2atype");
    public static final SchemaType type = Factory.getType();

    short getVal();

    boolean isSetVal();

    void setVal(short s);

    void unsetVal();

    STPerspective xgetVal();

    void xsetVal(STPerspective sTPerspective);
}
