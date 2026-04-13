package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTPath2DLineTo extends XmlObject {
    public static final DocumentFactory<CTPath2DLineTo> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpath2dlineto4f41type");
    public static final SchemaType type = Factory.getType();

    CTAdjPoint2D addNewPt();

    CTAdjPoint2D getPt();

    void setPt(CTAdjPoint2D cTAdjPoint2D);
}
