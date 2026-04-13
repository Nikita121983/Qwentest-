package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextTabAlignType;

/* loaded from: classes11.dex */
public interface CTTextTabStop extends XmlObject {
    public static final DocumentFactory<CTTextTabStop> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttexttabstopb57btype");
    public static final SchemaType type = Factory.getType();

    STTextTabAlignType.Enum getAlgn();

    Object getPos();

    boolean isSetAlgn();

    boolean isSetPos();

    void setAlgn(STTextTabAlignType.Enum r1);

    void setPos(Object obj);

    void unsetAlgn();

    void unsetPos();

    STTextTabAlignType xgetAlgn();

    STCoordinate32 xgetPos();

    void xsetAlgn(STTextTabAlignType sTTextTabAlignType);

    void xsetPos(STCoordinate32 sTCoordinate32);
}
