package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTTextBulletSizePercent extends XmlObject {
    public static final DocumentFactory<CTTextBulletSizePercent> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextbulletsizepercent9b26type");
    public static final SchemaType type = Factory.getType();

    String getVal();

    void setVal(String str);

    STTextBulletSizePercent xgetVal();

    void xsetVal(STTextBulletSizePercent sTTextBulletSizePercent);
}
