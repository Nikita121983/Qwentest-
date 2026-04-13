package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTConnectionSite extends XmlObject {
    public static final DocumentFactory<CTConnectionSite> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctconnectionsite6660type");
    public static final SchemaType type = Factory.getType();

    CTAdjPoint2D addNewPos();

    Object getAng();

    CTAdjPoint2D getPos();

    void setAng(Object obj);

    void setPos(CTAdjPoint2D cTAdjPoint2D);

    STAdjAngle xgetAng();

    void xsetAng(STAdjAngle sTAdjAngle);
}
