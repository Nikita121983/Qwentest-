package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTNonVisualPictureProperties extends XmlObject {
    public static final DocumentFactory<CTNonVisualPictureProperties> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctnonvisualpicturepropertiesee3ftype");
    public static final SchemaType type = Factory.getType();

    CTOfficeArtExtensionList addNewExtLst();

    CTPictureLocking addNewPicLocks();

    CTOfficeArtExtensionList getExtLst();

    CTPictureLocking getPicLocks();

    boolean getPreferRelativeResize();

    boolean isSetExtLst();

    boolean isSetPicLocks();

    boolean isSetPreferRelativeResize();

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setPicLocks(CTPictureLocking cTPictureLocking);

    void setPreferRelativeResize(boolean z);

    void unsetExtLst();

    void unsetPicLocks();

    void unsetPreferRelativeResize();

    XmlBoolean xgetPreferRelativeResize();

    void xsetPreferRelativeResize(XmlBoolean xmlBoolean);
}
