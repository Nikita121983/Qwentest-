package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;

/* loaded from: classes11.dex */
public interface CTLineEndProperties extends XmlObject {
    public static final DocumentFactory<CTLineEndProperties> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlineendproperties8acbtype");
    public static final SchemaType type = Factory.getType();

    STLineEndLength.Enum getLen();

    STLineEndType.Enum getType();

    STLineEndWidth.Enum getW();

    boolean isSetLen();

    boolean isSetType();

    boolean isSetW();

    void setLen(STLineEndLength.Enum r1);

    void setType(STLineEndType.Enum r1);

    void setW(STLineEndWidth.Enum r1);

    void unsetLen();

    void unsetType();

    void unsetW();

    STLineEndLength xgetLen();

    STLineEndType xgetType();

    STLineEndWidth xgetW();

    void xsetLen(STLineEndLength sTLineEndLength);

    void xsetType(STLineEndType sTLineEndType);

    void xsetW(STLineEndWidth sTLineEndWidth);
}
