package com.microsoft.schemas.office.drawing.x2008.diagram;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps;

/* loaded from: classes.dex */
public interface CTGroupShapeNonVisual extends XmlObject {
    public static final DocumentFactory<CTGroupShapeNonVisual> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgroupshapenonvisualf80ctype");
    public static final SchemaType type = Factory.getType();

    CTNonVisualGroupDrawingShapeProps addNewCNvGrpSpPr();

    CTNonVisualDrawingProps addNewCNvPr();

    CTNonVisualGroupDrawingShapeProps getCNvGrpSpPr();

    CTNonVisualDrawingProps getCNvPr();

    void setCNvGrpSpPr(CTNonVisualGroupDrawingShapeProps cTNonVisualGroupDrawingShapeProps);

    void setCNvPr(CTNonVisualDrawingProps cTNonVisualDrawingProps);
}
