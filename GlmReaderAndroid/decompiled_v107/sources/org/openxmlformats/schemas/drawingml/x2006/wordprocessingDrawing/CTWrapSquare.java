package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapText;

/* loaded from: classes11.dex */
public interface CTWrapSquare extends XmlObject {
    public static final DocumentFactory<CTWrapSquare> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctwrapsquare2678type");
    public static final SchemaType type = Factory.getType();

    CTEffectExtent addNewEffectExtent();

    long getDistB();

    long getDistL();

    long getDistR();

    long getDistT();

    CTEffectExtent getEffectExtent();

    STWrapText.Enum getWrapText();

    boolean isSetDistB();

    boolean isSetDistL();

    boolean isSetDistR();

    boolean isSetDistT();

    boolean isSetEffectExtent();

    void setDistB(long j);

    void setDistL(long j);

    void setDistR(long j);

    void setDistT(long j);

    void setEffectExtent(CTEffectExtent cTEffectExtent);

    void setWrapText(STWrapText.Enum r1);

    void unsetDistB();

    void unsetDistL();

    void unsetDistR();

    void unsetDistT();

    void unsetEffectExtent();

    STWrapDistance xgetDistB();

    STWrapDistance xgetDistL();

    STWrapDistance xgetDistR();

    STWrapDistance xgetDistT();

    STWrapText xgetWrapText();

    void xsetDistB(STWrapDistance sTWrapDistance);

    void xsetDistL(STWrapDistance sTWrapDistance);

    void xsetDistR(STWrapDistance sTWrapDistance);

    void xsetDistT(STWrapDistance sTWrapDistance);

    void xsetWrapText(STWrapText sTWrapText);
}
