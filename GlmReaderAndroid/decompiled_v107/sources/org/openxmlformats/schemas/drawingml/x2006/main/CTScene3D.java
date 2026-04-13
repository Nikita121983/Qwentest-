package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTScene3D extends XmlObject {
    public static final DocumentFactory<CTScene3D> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctscene3d736etype");
    public static final SchemaType type = Factory.getType();

    CTBackdrop addNewBackdrop();

    CTCamera addNewCamera();

    CTOfficeArtExtensionList addNewExtLst();

    CTLightRig addNewLightRig();

    CTBackdrop getBackdrop();

    CTCamera getCamera();

    CTOfficeArtExtensionList getExtLst();

    CTLightRig getLightRig();

    boolean isSetBackdrop();

    boolean isSetExtLst();

    void setBackdrop(CTBackdrop cTBackdrop);

    void setCamera(CTCamera cTCamera);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setLightRig(CTLightRig cTLightRig);

    void unsetBackdrop();

    void unsetExtLst();
}
