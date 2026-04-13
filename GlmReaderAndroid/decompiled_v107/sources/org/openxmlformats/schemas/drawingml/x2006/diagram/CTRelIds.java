package org.openxmlformats.schemas.drawingml.x2006.diagram;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;

/* loaded from: classes11.dex */
public interface CTRelIds extends XmlObject {
    public static final DocumentFactory<CTRelIds> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctrelidsfef2type");
    public static final SchemaType type = Factory.getType();

    String getCs();

    String getDm();

    String getLo();

    String getQs();

    void setCs(String str);

    void setDm(String str);

    void setLo(String str);

    void setQs(String str);

    STRelationshipId xgetCs();

    STRelationshipId xgetDm();

    STRelationshipId xgetLo();

    STRelationshipId xgetQs();

    void xsetCs(STRelationshipId sTRelationshipId);

    void xsetDm(STRelationshipId sTRelationshipId);

    void xsetLo(STRelationshipId sTRelationshipId);

    void xsetQs(STRelationshipId sTRelationshipId);
}
