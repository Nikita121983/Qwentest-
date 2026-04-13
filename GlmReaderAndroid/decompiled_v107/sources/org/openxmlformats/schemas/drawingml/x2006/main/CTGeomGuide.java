package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTGeomGuide extends XmlObject {
    public static final DocumentFactory<CTGeomGuide> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgeomguidef191type");
    public static final SchemaType type = Factory.getType();

    String getFmla();

    String getName();

    void setFmla(String str);

    void setName(String str);

    STGeomGuideFormula xgetFmla();

    STGeomGuideName xgetName();

    void xsetFmla(STGeomGuideFormula sTGeomGuideFormula);

    void xsetName(STGeomGuideName sTGeomGuideName);
}
