package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTTableCol extends XmlObject {
    public static final DocumentFactory<CTTableCol> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablecol19edtype");
    public static final SchemaType type = Factory.getType();

    CTOfficeArtExtensionList addNewExtLst();

    CTOfficeArtExtensionList getExtLst();

    Object getW();

    boolean isSetExtLst();

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setW(Object obj);

    void unsetExtLst();

    STCoordinate xgetW();

    void xsetW(STCoordinate sTCoordinate);
}
