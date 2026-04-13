package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface SstDocument extends XmlObject {
    public static final DocumentFactory<SstDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "sstf81fdoctype");
    public static final SchemaType type = Factory.getType();

    CTSst addNewSst();

    CTSst getSst();

    void setSst(CTSst cTSst);
}
