package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTIndexedColors extends XmlObject {
    public static final DocumentFactory<CTIndexedColors> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctindexedcolorsa0a0type");
    public static final SchemaType type = Factory.getType();

    CTRgbColor addNewRgbColor();

    CTRgbColor getRgbColorArray(int i);

    CTRgbColor[] getRgbColorArray();

    List<CTRgbColor> getRgbColorList();

    CTRgbColor insertNewRgbColor(int i);

    void removeRgbColor(int i);

    void setRgbColorArray(int i, CTRgbColor cTRgbColor);

    void setRgbColorArray(CTRgbColor[] cTRgbColorArr);

    int sizeOfRgbColorArray();
}
