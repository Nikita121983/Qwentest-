package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTTblGrid extends CTTblGridBase {
    public static final DocumentFactory<CTTblGrid> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttblgrid2eeetype");
    public static final SchemaType type = Factory.getType();

    CTTblGridChange addNewTblGridChange();

    CTTblGridChange getTblGridChange();

    boolean isSetTblGridChange();

    void setTblGridChange(CTTblGridChange cTTblGridChange);

    void unsetTblGridChange();
}
