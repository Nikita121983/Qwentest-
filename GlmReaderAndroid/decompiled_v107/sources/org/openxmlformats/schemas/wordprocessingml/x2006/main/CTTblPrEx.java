package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTTblPrEx extends CTTblPrExBase {
    public static final DocumentFactory<CTTblPrEx> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttblprex863ftype");
    public static final SchemaType type = Factory.getType();

    CTTblPrExChange addNewTblPrExChange();

    CTTblPrExChange getTblPrExChange();

    boolean isSetTblPrExChange();

    void setTblPrExChange(CTTblPrExChange cTTblPrExChange);

    void unsetTblPrExChange();
}
