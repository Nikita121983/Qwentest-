package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STPanose;

/* loaded from: classes11.dex */
public interface CTTextFont extends XmlObject {
    public static final DocumentFactory<CTTextFont> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextfont92b7type");
    public static final SchemaType type = Factory.getType();

    byte getCharset();

    byte[] getPanose();

    byte getPitchFamily();

    String getTypeface();

    boolean isSetCharset();

    boolean isSetPanose();

    boolean isSetPitchFamily();

    void setCharset(byte b);

    void setPanose(byte[] bArr);

    void setPitchFamily(byte b);

    void setTypeface(String str);

    void unsetCharset();

    void unsetPanose();

    void unsetPitchFamily();

    XmlByte xgetCharset();

    STPanose xgetPanose();

    STPitchFamily xgetPitchFamily();

    STTextTypeface xgetTypeface();

    void xsetCharset(XmlByte xmlByte);

    void xsetPanose(STPanose sTPanose);

    void xsetPitchFamily(STPitchFamily sTPitchFamily);

    void xsetTypeface(STTextTypeface sTTextTypeface);
}
