package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;

/* loaded from: classes12.dex */
public interface CTColor extends XmlObject {
    public static final DocumentFactory<CTColor> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcolor6d4ftype");
    public static final SchemaType type = Factory.getType();

    STThemeColor.Enum getThemeColor();

    byte[] getThemeShade();

    byte[] getThemeTint();

    Object getVal();

    boolean isSetThemeColor();

    boolean isSetThemeShade();

    boolean isSetThemeTint();

    void setThemeColor(STThemeColor.Enum r1);

    void setThemeShade(byte[] bArr);

    void setThemeTint(byte[] bArr);

    void setVal(Object obj);

    void unsetThemeColor();

    void unsetThemeShade();

    void unsetThemeTint();

    STThemeColor xgetThemeColor();

    STUcharHexNumber xgetThemeShade();

    STUcharHexNumber xgetThemeTint();

    STHexColor xgetVal();

    void xsetThemeColor(STThemeColor sTThemeColor);

    void xsetThemeShade(STUcharHexNumber sTUcharHexNumber);

    void xsetThemeTint(STUcharHexNumber sTUcharHexNumber);

    void xsetVal(STHexColor sTHexColor);
}
