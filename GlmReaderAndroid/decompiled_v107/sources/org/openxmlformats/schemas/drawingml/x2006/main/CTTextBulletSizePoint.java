package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTTextBulletSizePoint extends XmlObject {
    public static final DocumentFactory<CTTextBulletSizePoint> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextbulletsizepointe4f1type");
    public static final SchemaType type = Factory.getType();

    int getVal();

    void setVal(int i);

    STTextFontSize xgetVal();

    void xsetVal(STTextFontSize sTTextFontSize);
}
