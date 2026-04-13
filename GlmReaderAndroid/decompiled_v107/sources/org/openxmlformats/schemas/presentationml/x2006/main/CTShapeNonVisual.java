package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingShapeProps;

/* loaded from: classes11.dex */
public interface CTShapeNonVisual extends XmlObject {
    public static final DocumentFactory<CTShapeNonVisual> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctshapenonvisualb619type");
    public static final SchemaType type = Factory.getType();

    CTNonVisualDrawingProps addNewCNvPr();

    CTNonVisualDrawingShapeProps addNewCNvSpPr();

    CTApplicationNonVisualDrawingProps addNewNvPr();

    CTNonVisualDrawingProps getCNvPr();

    CTNonVisualDrawingShapeProps getCNvSpPr();

    CTApplicationNonVisualDrawingProps getNvPr();

    void setCNvPr(CTNonVisualDrawingProps cTNonVisualDrawingProps);

    void setCNvSpPr(CTNonVisualDrawingShapeProps cTNonVisualDrawingShapeProps);

    void setNvPr(CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps);
}
