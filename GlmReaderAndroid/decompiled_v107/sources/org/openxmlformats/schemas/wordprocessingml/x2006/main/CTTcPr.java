package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTTcPr extends CTTcPrInner {
    public static final DocumentFactory<CTTcPr> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttcpree37type");
    public static final SchemaType type = Factory.getType();

    CTTcPrChange addNewTcPrChange();

    CTTcPrChange getTcPrChange();

    boolean isSetTcPrChange();

    void setTcPrChange(CTTcPrChange cTTcPrChange);

    void unsetTcPrChange();
}
