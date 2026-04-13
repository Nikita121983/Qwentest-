package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTParaRPrChange extends CTTrackChange {
    public static final DocumentFactory<CTParaRPrChange> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpararprchange986etype");
    public static final SchemaType type = Factory.getType();

    CTParaRPrOriginal addNewRPr();

    CTParaRPrOriginal getRPr();

    void setRPr(CTParaRPrOriginal cTParaRPrOriginal);
}
