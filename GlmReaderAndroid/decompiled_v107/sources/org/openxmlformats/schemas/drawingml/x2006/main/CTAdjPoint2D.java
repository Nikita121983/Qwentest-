package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTAdjPoint2D extends XmlObject {
    public static final DocumentFactory<CTAdjPoint2D> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctadjpoint2d1656type");
    public static final SchemaType type = Factory.getType();

    Object getX();

    Object getY();

    void setX(Object obj);

    void setY(Object obj);

    STAdjCoordinate xgetX();

    STAdjCoordinate xgetY();

    void xsetX(STAdjCoordinate sTAdjCoordinate);

    void xsetY(STAdjCoordinate sTAdjCoordinate);
}
