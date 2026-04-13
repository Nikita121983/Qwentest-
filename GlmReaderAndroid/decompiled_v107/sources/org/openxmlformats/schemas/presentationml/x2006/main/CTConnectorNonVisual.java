package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;

/* loaded from: classes11.dex */
public interface CTConnectorNonVisual extends XmlObject {
    public static final DocumentFactory<CTConnectorNonVisual> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctconnectornonvisual0f45type");
    public static final SchemaType type = Factory.getType();

    CTNonVisualConnectorProperties addNewCNvCxnSpPr();

    CTNonVisualDrawingProps addNewCNvPr();

    CTApplicationNonVisualDrawingProps addNewNvPr();

    CTNonVisualConnectorProperties getCNvCxnSpPr();

    CTNonVisualDrawingProps getCNvPr();

    CTApplicationNonVisualDrawingProps getNvPr();

    void setCNvCxnSpPr(CTNonVisualConnectorProperties cTNonVisualConnectorProperties);

    void setCNvPr(CTNonVisualDrawingProps cTNonVisualDrawingProps);

    void setNvPr(CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps);
}
