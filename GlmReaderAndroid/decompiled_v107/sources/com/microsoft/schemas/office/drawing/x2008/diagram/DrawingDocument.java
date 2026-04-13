package com.microsoft.schemas.office.drawing.x2008.diagram;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes.dex */
public interface DrawingDocument extends XmlObject {
    public static final DocumentFactory<DrawingDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "drawing324ddoctype");
    public static final SchemaType type = Factory.getType();

    CTDrawing addNewDrawing();

    CTDrawing getDrawing();

    void setDrawing(CTDrawing cTDrawing);
}
