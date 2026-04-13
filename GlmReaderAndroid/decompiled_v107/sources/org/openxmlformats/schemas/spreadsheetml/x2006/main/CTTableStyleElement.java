package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STTableStyleType;

/* loaded from: classes12.dex */
public interface CTTableStyleElement extends XmlObject {
    public static final DocumentFactory<CTTableStyleElement> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablestyleelementa658type");
    public static final SchemaType type = Factory.getType();

    long getDxfId();

    long getSize();

    STTableStyleType.Enum getType();

    boolean isSetDxfId();

    boolean isSetSize();

    void setDxfId(long j);

    void setSize(long j);

    void setType(STTableStyleType.Enum r1);

    void unsetDxfId();

    void unsetSize();

    STDxfId xgetDxfId();

    XmlUnsignedInt xgetSize();

    STTableStyleType xgetType();

    void xsetDxfId(STDxfId sTDxfId);

    void xsetSize(XmlUnsignedInt xmlUnsignedInt);

    void xsetType(STTableStyleType sTTableStyleType);
}
