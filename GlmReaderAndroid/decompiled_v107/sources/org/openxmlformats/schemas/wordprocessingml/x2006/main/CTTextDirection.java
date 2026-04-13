package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTextDirection;

/* loaded from: classes12.dex */
public interface CTTextDirection extends XmlObject {
    public static final DocumentFactory<CTTextDirection> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttextdirection0940type");
    public static final SchemaType type = Factory.getType();

    STTextDirection.Enum getVal();

    void setVal(STTextDirection.Enum r1);

    STTextDirection xgetVal();

    void xsetVal(STTextDirection sTTextDirection);
}
