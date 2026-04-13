package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

/* loaded from: classes12.dex */
public interface CTJc extends XmlObject {
    public static final DocumentFactory<CTJc> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctjc158ftype");
    public static final SchemaType type = Factory.getType();

    STJc.Enum getVal();

    void setVal(STJc.Enum r1);

    STJc xgetVal();

    void xsetVal(STJc sTJc);
}
