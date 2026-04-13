package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;

/* loaded from: classes12.dex */
public interface CTNumFmt extends XmlObject {
    public static final DocumentFactory<CTNumFmt> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnumfmt00e1type");
    public static final SchemaType type = Factory.getType();

    String getFormat();

    STNumberFormat.Enum getVal();

    boolean isSetFormat();

    void setFormat(String str);

    void setVal(STNumberFormat.Enum r1);

    void unsetFormat();

    STString xgetFormat();

    STNumberFormat xgetVal();

    void xsetFormat(STString sTString);

    void xsetVal(STNumberFormat sTNumberFormat);
}
