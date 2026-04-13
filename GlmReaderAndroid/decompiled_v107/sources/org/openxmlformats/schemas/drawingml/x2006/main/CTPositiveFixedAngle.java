package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTPositiveFixedAngle extends XmlObject {
    public static final DocumentFactory<CTPositiveFixedAngle> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpositivefixedangle78f3type");
    public static final SchemaType type = Factory.getType();

    int getVal();

    void setVal(int i);

    STPositiveFixedAngle xgetVal();

    void xsetVal(STPositiveFixedAngle sTPositiveFixedAngle);
}
