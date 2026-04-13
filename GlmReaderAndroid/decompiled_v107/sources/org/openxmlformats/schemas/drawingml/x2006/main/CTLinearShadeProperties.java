package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTLinearShadeProperties extends XmlObject {
    public static final DocumentFactory<CTLinearShadeProperties> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlinearshadeproperties7f0ctype");
    public static final SchemaType type = Factory.getType();

    int getAng();

    boolean getScaled();

    boolean isSetAng();

    boolean isSetScaled();

    void setAng(int i);

    void setScaled(boolean z);

    void unsetAng();

    void unsetScaled();

    STPositiveFixedAngle xgetAng();

    XmlBoolean xgetScaled();

    void xsetAng(STPositiveFixedAngle sTPositiveFixedAngle);

    void xsetScaled(XmlBoolean xmlBoolean);
}
