package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;

/* loaded from: classes12.dex */
public interface CTHighlight extends XmlObject {
    public static final DocumentFactory<CTHighlight> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cthighlight071etype");
    public static final SchemaType type = Factory.getType();

    STHighlightColor.Enum getVal();

    void setVal(STHighlightColor.Enum r1);

    STHighlightColor xgetVal();

    void xsetVal(STHighlightColor sTHighlightColor);
}
