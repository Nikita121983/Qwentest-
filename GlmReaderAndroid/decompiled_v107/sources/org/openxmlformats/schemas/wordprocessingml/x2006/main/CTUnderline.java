package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;

/* loaded from: classes12.dex */
public interface CTUnderline extends XmlObject {
    public static final DocumentFactory<CTUnderline> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctunderline8406type");
    public static final SchemaType type = Factory.getType();

    Object getColor();

    STThemeColor.Enum getThemeColor();

    byte[] getThemeShade();

    byte[] getThemeTint();

    STUnderline.Enum getVal();

    boolean isSetColor();

    boolean isSetThemeColor();

    boolean isSetThemeShade();

    boolean isSetThemeTint();

    boolean isSetVal();

    void setColor(Object obj);

    void setThemeColor(STThemeColor.Enum r1);

    void setThemeShade(byte[] bArr);

    void setThemeTint(byte[] bArr);

    void setVal(STUnderline.Enum r1);

    void unsetColor();

    void unsetThemeColor();

    void unsetThemeShade();

    void unsetThemeTint();

    void unsetVal();

    STHexColor xgetColor();

    STThemeColor xgetThemeColor();

    STUcharHexNumber xgetThemeShade();

    STUcharHexNumber xgetThemeTint();

    STUnderline xgetVal();

    void xsetColor(STHexColor sTHexColor);

    void xsetThemeColor(STThemeColor sTThemeColor);

    void xsetThemeShade(STUcharHexNumber sTUcharHexNumber);

    void xsetThemeTint(STUcharHexNumber sTUcharHexNumber);

    void xsetVal(STUnderline sTUnderline);
}
