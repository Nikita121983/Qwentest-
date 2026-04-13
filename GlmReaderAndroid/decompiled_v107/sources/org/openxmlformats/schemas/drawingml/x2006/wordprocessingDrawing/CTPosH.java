package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STRelFromH;

/* loaded from: classes11.dex */
public interface CTPosH extends XmlObject {
    public static final DocumentFactory<CTPosH> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctposh7fabtype");
    public static final SchemaType type = Factory.getType();

    STAlignH$Enum getAlign();

    int getPosOffset();

    STRelFromH.Enum getRelativeFrom();

    boolean isSetAlign();

    boolean isSetPosOffset();

    void setAlign(STAlignH$Enum sTAlignH$Enum);

    void setPosOffset(int i);

    void setRelativeFrom(STRelFromH.Enum r1);

    void unsetAlign();

    void unsetPosOffset();

    STAlignH xgetAlign();

    STPositionOffset xgetPosOffset();

    STRelFromH xgetRelativeFrom();

    void xsetAlign(STAlignH sTAlignH);

    void xsetPosOffset(STPositionOffset sTPositionOffset);

    void xsetRelativeFrom(STRelFromH sTRelFromH);
}
