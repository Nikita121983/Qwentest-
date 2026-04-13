package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblOverlap;

/* loaded from: classes12.dex */
public interface CTTblOverlap extends XmlObject {
    public static final DocumentFactory<CTTblOverlap> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttbloverlap231ftype");
    public static final SchemaType type = Factory.getType();

    STTblOverlap.Enum getVal();

    void setVal(STTblOverlap.Enum r1);

    STTblOverlap xgetVal();

    void xsetVal(STTblOverlap sTTblOverlap);
}
