package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTRgbColor extends XmlObject {
    public static final DocumentFactory<CTRgbColor> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctrgbcolor95dftype");
    public static final SchemaType type = Factory.getType();

    byte[] getRgb();

    boolean isSetRgb();

    void setRgb(byte[] bArr);

    void unsetRgb();

    STUnsignedIntHex xgetRgb();

    void xsetRgb(STUnsignedIntHex sTUnsignedIntHex);
}
