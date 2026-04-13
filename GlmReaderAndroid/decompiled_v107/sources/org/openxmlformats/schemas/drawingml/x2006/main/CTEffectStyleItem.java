package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTEffectStyleItem extends XmlObject {
    public static final DocumentFactory<CTEffectStyleItem> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cteffectstyleitem05c4type");
    public static final SchemaType type = Factory.getType();

    CTEffectContainer addNewEffectDag();

    CTEffectList addNewEffectLst();

    CTScene3D addNewScene3D();

    CTShape3D addNewSp3D();

    CTEffectContainer getEffectDag();

    CTEffectList getEffectLst();

    CTScene3D getScene3D();

    CTShape3D getSp3D();

    boolean isSetEffectDag();

    boolean isSetEffectLst();

    boolean isSetScene3D();

    boolean isSetSp3D();

    void setEffectDag(CTEffectContainer cTEffectContainer);

    void setEffectLst(CTEffectList cTEffectList);

    void setScene3D(CTScene3D cTScene3D);

    void setSp3D(CTShape3D cTShape3D);

    void unsetEffectDag();

    void unsetEffectLst();

    void unsetScene3D();

    void unsetSp3D();
}
