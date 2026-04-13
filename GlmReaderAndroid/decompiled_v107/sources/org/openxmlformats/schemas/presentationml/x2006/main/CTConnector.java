package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;

/* loaded from: classes11.dex */
public interface CTConnector extends XmlObject {
    public static final DocumentFactory<CTConnector> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctconnector3522type");
    public static final SchemaType type = Factory.getType();

    CTExtensionListModify addNewExtLst();

    CTConnectorNonVisual addNewNvCxnSpPr();

    CTShapeProperties addNewSpPr();

    CTShapeStyle addNewStyle();

    CTExtensionListModify getExtLst();

    CTConnectorNonVisual getNvCxnSpPr();

    CTShapeProperties getSpPr();

    CTShapeStyle getStyle();

    boolean isSetExtLst();

    boolean isSetStyle();

    void setExtLst(CTExtensionListModify cTExtensionListModify);

    void setNvCxnSpPr(CTConnectorNonVisual cTConnectorNonVisual);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setStyle(CTShapeStyle cTShapeStyle);

    void unsetExtLst();

    void unsetStyle();
}
