package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface HdrDocument extends XmlObject {
    public static final DocumentFactory<HdrDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "hdra530doctype");
    public static final SchemaType type = Factory.getType();

    CTHdrFtr addNewHdr();

    CTHdrFtr getHdr();

    void setHdr(CTHdrFtr cTHdrFtr);
}
