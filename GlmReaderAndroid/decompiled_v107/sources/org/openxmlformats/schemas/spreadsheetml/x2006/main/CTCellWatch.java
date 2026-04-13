package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTCellWatch extends XmlObject {
    public static final DocumentFactory<CTCellWatch> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcellwatch3dectype");
    public static final SchemaType type = Factory.getType();

    String getR();

    void setR(String str);

    STCellRef xgetR();

    void xsetR(STCellRef sTCellRef);
}
