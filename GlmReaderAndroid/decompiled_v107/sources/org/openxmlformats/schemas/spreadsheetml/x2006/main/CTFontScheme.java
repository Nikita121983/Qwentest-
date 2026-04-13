package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFontScheme;

/* loaded from: classes12.dex */
public interface CTFontScheme extends XmlObject {
    public static final DocumentFactory<CTFontScheme> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctfontschemebf5dtype");
    public static final SchemaType type = Factory.getType();

    STFontScheme.Enum getVal();

    void setVal(STFontScheme.Enum r1);

    STFontScheme xgetVal();

    void xsetVal(STFontScheme sTFontScheme);
}
