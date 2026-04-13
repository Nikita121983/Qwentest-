package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTMapInfo extends XmlObject {
    public static final DocumentFactory<CTMapInfo> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmapinfo1a09type");
    public static final SchemaType type = Factory.getType();

    CTMap addNewMap();

    CTSchema addNewSchema();

    CTMap getMapArray(int i);

    CTMap[] getMapArray();

    List<CTMap> getMapList();

    CTSchema getSchemaArray(int i);

    CTSchema[] getSchemaArray();

    List<CTSchema> getSchemaList();

    String getSelectionNamespaces();

    CTMap insertNewMap(int i);

    CTSchema insertNewSchema(int i);

    void removeMap(int i);

    void removeSchema(int i);

    void setMapArray(int i, CTMap cTMap);

    void setMapArray(CTMap[] cTMapArr);

    void setSchemaArray(int i, CTSchema cTSchema);

    void setSchemaArray(CTSchema[] cTSchemaArr);

    void setSelectionNamespaces(String str);

    int sizeOfMapArray();

    int sizeOfSchemaArray();

    XmlString xgetSelectionNamespaces();

    void xsetSelectionNamespaces(XmlString xmlString);
}
