package com.microsoft.schemas.office.visio.x2012.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes.dex */
public interface MastersDocument extends XmlObject {
    public static final DocumentFactory<MastersDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "masters0341doctype");
    public static final SchemaType type = Factory.getType();

    MastersType addNewMasters();

    MastersType getMasters();

    void setMasters(MastersType mastersType);
}
