package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTBaseStyles extends XmlObject {
    public static final DocumentFactory<CTBaseStyles> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbasestyles122etype");
    public static final SchemaType type = Factory.getType();

    CTColorScheme addNewClrScheme();

    CTOfficeArtExtensionList addNewExtLst();

    CTStyleMatrix addNewFmtScheme();

    CTFontScheme addNewFontScheme();

    CTColorScheme getClrScheme();

    CTOfficeArtExtensionList getExtLst();

    CTStyleMatrix getFmtScheme();

    CTFontScheme getFontScheme();

    boolean isSetExtLst();

    void setClrScheme(CTColorScheme cTColorScheme);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setFmtScheme(CTStyleMatrix cTStyleMatrix);

    void setFontScheme(CTFontScheme cTFontScheme);

    void unsetExtLst();
}
