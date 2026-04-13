package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTTblPr extends CTTblPrBase {
    public static final DocumentFactory<CTTblPr> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttblpr5b72type");
    public static final SchemaType type = Factory.getType();

    CTTblPrChange addNewTblPrChange();

    CTTblPrChange getTblPrChange();

    boolean isSetTblPrChange();

    void setTblPrChange(CTTblPrChange cTTblPrChange);

    void unsetTblPrChange();
}
