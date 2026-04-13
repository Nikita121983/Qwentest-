package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTFontFamily extends XmlObject {
    public static final DocumentFactory<CTFontFamily> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfontfamily685ctype");
    public static final SchemaType type = Factory.getType();

    int getVal();

    void setVal(int i);

    STFontFamily xgetVal();

    void xsetVal(STFontFamily sTFontFamily);
}
