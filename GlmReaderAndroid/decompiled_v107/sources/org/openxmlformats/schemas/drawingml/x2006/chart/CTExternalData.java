package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

/* loaded from: classes11.dex */
public interface CTExternalData extends XmlObject {
    public static final DocumentFactory<CTExternalData> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctexternaldata2e07type");
    public static final SchemaType type = Factory.getType();

    CTBoolean addNewAutoUpdate();

    CTBoolean getAutoUpdate();

    String getId();

    boolean isSetAutoUpdate();

    void setAutoUpdate(CTBoolean cTBoolean);

    void setId(String str);

    void unsetAutoUpdate();

    STRelationshipId xgetId();

    void xsetId(STRelationshipId sTRelationshipId);
}
