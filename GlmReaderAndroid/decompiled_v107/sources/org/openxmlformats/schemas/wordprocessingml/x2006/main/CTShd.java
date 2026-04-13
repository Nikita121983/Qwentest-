package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;

/* loaded from: classes12.dex */
public interface CTShd extends XmlObject {
    public static final DocumentFactory<CTShd> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctshd58c3type");
    public static final SchemaType type = Factory.getType();

    Object getColor();

    Object getFill();

    STThemeColor.Enum getThemeColor();

    STThemeColor.Enum getThemeFill();

    byte[] getThemeFillShade();

    byte[] getThemeFillTint();

    byte[] getThemeShade();

    byte[] getThemeTint();

    STShd.Enum getVal();

    boolean isSetColor();

    boolean isSetFill();

    boolean isSetThemeColor();

    boolean isSetThemeFill();

    boolean isSetThemeFillShade();

    boolean isSetThemeFillTint();

    boolean isSetThemeShade();

    boolean isSetThemeTint();

    void setColor(Object obj);

    void setFill(Object obj);

    void setThemeColor(STThemeColor.Enum r1);

    void setThemeFill(STThemeColor.Enum r1);

    void setThemeFillShade(byte[] bArr);

    void setThemeFillTint(byte[] bArr);

    void setThemeShade(byte[] bArr);

    void setThemeTint(byte[] bArr);

    void setVal(STShd.Enum r1);

    void unsetColor();

    void unsetFill();

    void unsetThemeColor();

    void unsetThemeFill();

    void unsetThemeFillShade();

    void unsetThemeFillTint();

    void unsetThemeShade();

    void unsetThemeTint();

    STHexColor xgetColor();

    STHexColor xgetFill();

    STThemeColor xgetThemeColor();

    STThemeColor xgetThemeFill();

    STUcharHexNumber xgetThemeFillShade();

    STUcharHexNumber xgetThemeFillTint();

    STUcharHexNumber xgetThemeShade();

    STUcharHexNumber xgetThemeTint();

    STShd xgetVal();

    void xsetColor(STHexColor sTHexColor);

    void xsetFill(STHexColor sTHexColor);

    void xsetThemeColor(STThemeColor sTThemeColor);

    void xsetThemeFill(STThemeColor sTThemeColor);

    void xsetThemeFillShade(STUcharHexNumber sTUcharHexNumber);

    void xsetThemeFillTint(STUcharHexNumber sTUcharHexNumber);

    void xsetThemeShade(STUcharHexNumber sTUcharHexNumber);

    void xsetThemeTint(STUcharHexNumber sTUcharHexNumber);

    void xsetVal(STShd sTShd);
}
