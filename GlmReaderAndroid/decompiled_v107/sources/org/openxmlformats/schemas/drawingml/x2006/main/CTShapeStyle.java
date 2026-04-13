package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTShapeStyle extends XmlObject {
    public static final DocumentFactory<CTShapeStyle> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctshapestyle81ebtype");
    public static final SchemaType type = Factory.getType();

    CTStyleMatrixReference addNewEffectRef();

    CTStyleMatrixReference addNewFillRef();

    CTFontReference addNewFontRef();

    CTStyleMatrixReference addNewLnRef();

    CTStyleMatrixReference getEffectRef();

    CTStyleMatrixReference getFillRef();

    CTFontReference getFontRef();

    CTStyleMatrixReference getLnRef();

    void setEffectRef(CTStyleMatrixReference cTStyleMatrixReference);

    void setFillRef(CTStyleMatrixReference cTStyleMatrixReference);

    void setFontRef(CTFontReference cTFontReference);

    void setLnRef(CTStyleMatrixReference cTStyleMatrixReference);
}
