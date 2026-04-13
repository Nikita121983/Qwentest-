package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;

/* loaded from: classes11.dex */
public interface CTGroupShape extends XmlObject {
    public static final DocumentFactory<CTGroupShape> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgroupshape6c36type");
    public static final SchemaType type = Factory.getType();

    CTConnector addNewCxnSp();

    CTGraphicalObjectFrame addNewGraphicFrame();

    CTGroupShape addNewGrpSp();

    CTGroupShapeProperties addNewGrpSpPr();

    CTGroupShapeNonVisual addNewNvGrpSpPr();

    CTPicture addNewPic();

    CTShape addNewSp();

    CTConnector getCxnSpArray(int i);

    CTConnector[] getCxnSpArray();

    List<CTConnector> getCxnSpList();

    CTGraphicalObjectFrame getGraphicFrameArray(int i);

    CTGraphicalObjectFrame[] getGraphicFrameArray();

    List<CTGraphicalObjectFrame> getGraphicFrameList();

    CTGroupShape getGrpSpArray(int i);

    CTGroupShape[] getGrpSpArray();

    List<CTGroupShape> getGrpSpList();

    CTGroupShapeProperties getGrpSpPr();

    CTGroupShapeNonVisual getNvGrpSpPr();

    CTPicture getPicArray(int i);

    CTPicture[] getPicArray();

    List<CTPicture> getPicList();

    CTShape getSpArray(int i);

    CTShape[] getSpArray();

    List<CTShape> getSpList();

    CTConnector insertNewCxnSp(int i);

    CTGraphicalObjectFrame insertNewGraphicFrame(int i);

    CTGroupShape insertNewGrpSp(int i);

    CTPicture insertNewPic(int i);

    CTShape insertNewSp(int i);

    void removeCxnSp(int i);

    void removeGraphicFrame(int i);

    void removeGrpSp(int i);

    void removePic(int i);

    void removeSp(int i);

    void setCxnSpArray(int i, CTConnector cTConnector);

    void setCxnSpArray(CTConnector[] cTConnectorArr);

    void setGraphicFrameArray(int i, CTGraphicalObjectFrame cTGraphicalObjectFrame);

    void setGraphicFrameArray(CTGraphicalObjectFrame[] cTGraphicalObjectFrameArr);

    void setGrpSpArray(int i, CTGroupShape cTGroupShape);

    void setGrpSpArray(CTGroupShape[] cTGroupShapeArr);

    void setGrpSpPr(CTGroupShapeProperties cTGroupShapeProperties);

    void setNvGrpSpPr(CTGroupShapeNonVisual cTGroupShapeNonVisual);

    void setPicArray(int i, CTPicture cTPicture);

    void setPicArray(CTPicture[] cTPictureArr);

    void setSpArray(int i, CTShape cTShape);

    void setSpArray(CTShape[] cTShapeArr);

    int sizeOfCxnSpArray();

    int sizeOfGraphicFrameArray();

    int sizeOfGrpSpArray();

    int sizeOfPicArray();

    int sizeOfSpArray();
}
