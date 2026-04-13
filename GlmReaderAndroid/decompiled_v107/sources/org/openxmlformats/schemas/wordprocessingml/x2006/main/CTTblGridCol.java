package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;

/* loaded from: classes12.dex */
public interface CTTblGridCol extends XmlObject {
    public static final DocumentFactory<CTTblGridCol> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttblgridcolbfectype");
    public static final SchemaType type = Factory.getType();

    Object getW();

    boolean isSetW();

    void setW(Object obj);

    void unsetW();

    STTwipsMeasure xgetW();

    void xsetW(STTwipsMeasure sTTwipsMeasure);
}
