package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STString;

/* loaded from: classes12.dex */
public interface CTSym extends XmlObject {
    public static final DocumentFactory<CTSym> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsym0dabtype");
    public static final SchemaType type = Factory.getType();

    byte[] getChar();

    String getFont();

    boolean isSetChar();

    boolean isSetFont();

    void setChar(byte[] bArr);

    void setFont(String str);

    void unsetChar();

    void unsetFont();

    STShortHexNumber xgetChar();

    STString xgetFont();

    void xsetChar(STShortHexNumber sTShortHexNumber);

    void xsetFont(STString sTString);
}
