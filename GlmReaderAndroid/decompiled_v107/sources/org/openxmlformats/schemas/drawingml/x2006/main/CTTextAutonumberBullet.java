package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAutonumberScheme;

/* loaded from: classes11.dex */
public interface CTTextAutonumberBullet extends XmlObject {
    public static final DocumentFactory<CTTextAutonumberBullet> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextautonumberbulletd602type");
    public static final SchemaType type = Factory.getType();

    int getStartAt();

    STTextAutonumberScheme.Enum getType();

    boolean isSetStartAt();

    void setStartAt(int i);

    void setType(STTextAutonumberScheme.Enum r1);

    void unsetStartAt();

    STTextBulletStartAtNum xgetStartAt();

    STTextAutonumberScheme xgetType();

    void xsetStartAt(STTextBulletStartAtNum sTTextBulletStartAtNum);

    void xsetType(STTextAutonumberScheme sTTextAutonumberScheme);
}
