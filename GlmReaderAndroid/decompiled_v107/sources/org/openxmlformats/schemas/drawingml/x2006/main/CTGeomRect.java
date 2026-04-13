package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTGeomRect extends XmlObject {
    public static final DocumentFactory<CTGeomRect> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgeomrect53dbtype");
    public static final SchemaType type = Factory.getType();

    Object getB();

    Object getL();

    Object getR();

    Object getT();

    void setB(Object obj);

    void setL(Object obj);

    void setR(Object obj);

    void setT(Object obj);

    STAdjCoordinate xgetB();

    STAdjCoordinate xgetL();

    STAdjCoordinate xgetR();

    STAdjCoordinate xgetT();

    void xsetB(STAdjCoordinate sTAdjCoordinate);

    void xsetL(STAdjCoordinate sTAdjCoordinate);

    void xsetR(STAdjCoordinate sTAdjCoordinate);

    void xsetT(STAdjCoordinate sTAdjCoordinate);
}
