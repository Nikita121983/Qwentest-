package com.microsoft.schemas.office.drawing.x2008.diagram;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.diagram.STModelId;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;

/* loaded from: classes.dex */
public interface CTShape extends XmlObject {
    public static final DocumentFactory<CTShape> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctshape6416type");
    public static final SchemaType type = Factory.getType();

    CTOfficeArtExtensionList addNewExtLst();

    CTShapeNonVisual addNewNvSpPr();

    CTShapeProperties addNewSpPr();

    CTShapeStyle addNewStyle();

    CTTextBody addNewTxBody();

    CTTransform2D addNewTxXfrm();

    CTOfficeArtExtensionList getExtLst();

    Object getModelId();

    CTShapeNonVisual getNvSpPr();

    CTShapeProperties getSpPr();

    CTShapeStyle getStyle();

    CTTextBody getTxBody();

    CTTransform2D getTxXfrm();

    boolean isSetExtLst();

    boolean isSetStyle();

    boolean isSetTxBody();

    boolean isSetTxXfrm();

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setModelId(Object obj);

    void setNvSpPr(CTShapeNonVisual cTShapeNonVisual);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setStyle(CTShapeStyle cTShapeStyle);

    void setTxBody(CTTextBody cTTextBody);

    void setTxXfrm(CTTransform2D cTTransform2D);

    void unsetExtLst();

    void unsetStyle();

    void unsetTxBody();

    void unsetTxXfrm();

    STModelId xgetModelId();

    void xsetModelId(STModelId sTModelId);
}
