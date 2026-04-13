package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface MapInfoDocument extends XmlObject {
    public static final DocumentFactory<MapInfoDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "mapinfo5715doctype");
    public static final SchemaType type = Factory.getType();

    CTMapInfo addNewMapInfo();

    CTMapInfo getMapInfo();

    void setMapInfo(CTMapInfo cTMapInfo);
}
