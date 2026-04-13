package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTPoint2D extends XmlObject {
    public static final DocumentFactory<CTPoint2D> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpoint2d8193type");
    public static final SchemaType type = Factory.getType();

    Object getX();

    Object getY();

    void setX(Object obj);

    void setY(Object obj);

    STCoordinate xgetX();

    STCoordinate xgetY();

    void xsetX(STCoordinate sTCoordinate);

    void xsetY(STCoordinate sTCoordinate);
}
