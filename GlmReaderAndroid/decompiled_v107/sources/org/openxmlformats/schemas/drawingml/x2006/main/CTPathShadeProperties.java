package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathShadeType;

/* loaded from: classes11.dex */
public interface CTPathShadeProperties extends XmlObject {
    public static final DocumentFactory<CTPathShadeProperties> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpathshadeproperties7ccctype");
    public static final SchemaType type = Factory.getType();

    CTRelativeRect addNewFillToRect();

    CTRelativeRect getFillToRect();

    STPathShadeType.Enum getPath();

    boolean isSetFillToRect();

    boolean isSetPath();

    void setFillToRect(CTRelativeRect cTRelativeRect);

    void setPath(STPathShadeType.Enum r1);

    void unsetFillToRect();

    void unsetPath();

    STPathShadeType xgetPath();

    void xsetPath(STPathShadeType sTPathShadeType);
}
