package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualPictureProperties;

/* loaded from: classes11.dex */
public interface CTPictureNonVisual extends XmlObject {
    public static final DocumentFactory<CTPictureNonVisual> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpicturenonvisualb236type");
    public static final SchemaType type = Factory.getType();

    CTNonVisualPictureProperties addNewCNvPicPr();

    CTNonVisualDrawingProps addNewCNvPr();

    CTApplicationNonVisualDrawingProps addNewNvPr();

    CTNonVisualPictureProperties getCNvPicPr();

    CTNonVisualDrawingProps getCNvPr();

    CTApplicationNonVisualDrawingProps getNvPr();

    void setCNvPicPr(CTNonVisualPictureProperties cTNonVisualPictureProperties);

    void setCNvPr(CTNonVisualDrawingProps cTNonVisualDrawingProps);

    void setNvPr(CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps);
}
