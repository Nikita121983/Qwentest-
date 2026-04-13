package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface FtrDocument extends XmlObject {
    public static final DocumentFactory<FtrDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ftre182doctype");
    public static final SchemaType type = Factory.getType();

    CTHdrFtr addNewFtr();

    CTHdrFtr getFtr();

    void setFtr(CTHdrFtr cTHdrFtr);
}
