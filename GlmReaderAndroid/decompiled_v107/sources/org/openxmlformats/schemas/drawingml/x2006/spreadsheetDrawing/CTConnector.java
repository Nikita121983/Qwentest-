package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;

/* loaded from: classes11.dex */
public interface CTConnector extends XmlObject {
    public static final DocumentFactory<CTConnector> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctconnector3d37type");
    public static final SchemaType type = Factory.getType();

    CTConnectorNonVisual addNewNvCxnSpPr();

    CTShapeProperties addNewSpPr();

    CTShapeStyle addNewStyle();

    boolean getFPublished();

    String getMacro();

    CTConnectorNonVisual getNvCxnSpPr();

    CTShapeProperties getSpPr();

    CTShapeStyle getStyle();

    boolean isSetFPublished();

    boolean isSetMacro();

    boolean isSetStyle();

    void setFPublished(boolean z);

    void setMacro(String str);

    void setNvCxnSpPr(CTConnectorNonVisual cTConnectorNonVisual);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setStyle(CTShapeStyle cTShapeStyle);

    void unsetFPublished();

    void unsetMacro();

    void unsetStyle();

    XmlBoolean xgetFPublished();

    XmlString xgetMacro();

    void xsetFPublished(XmlBoolean xmlBoolean);

    void xsetMacro(XmlString xmlString);
}
