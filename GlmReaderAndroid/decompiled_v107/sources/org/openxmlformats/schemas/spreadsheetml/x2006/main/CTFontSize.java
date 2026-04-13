package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTFontSize extends XmlObject {
    public static final DocumentFactory<CTFontSize> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfontsizeb3b9type");
    public static final SchemaType type = Factory.getType();

    double getVal();

    void setVal(double d);

    XmlDouble xgetVal();

    void xsetVal(XmlDouble xmlDouble);
}
