package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBrClear;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBrType;

/* loaded from: classes12.dex */
public interface CTBr extends XmlObject {
    public static final DocumentFactory<CTBr> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbr7dd8type");
    public static final SchemaType type = Factory.getType();

    STBrClear.Enum getClear();

    STBrType.Enum getType();

    boolean isSetClear();

    boolean isSetType();

    void setClear(STBrClear.Enum r1);

    void setType(STBrType.Enum r1);

    void unsetClear();

    void unsetType();

    STBrClear xgetClear();

    STBrType xgetType();

    void xsetClear(STBrClear sTBrClear);

    void xsetType(STBrType sTBrType);
}
