package com.microsoft.schemas.office.drawing.x2008.diagram;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;

/* loaded from: classes.dex */
public interface CTGroupShape extends XmlObject {
    public static final DocumentFactory<CTGroupShape> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctgroupshape48cbtype");
    public static final SchemaType type = Factory.getType();

    CTOfficeArtExtensionList addNewExtLst();

    CTGroupShape addNewGrpSp();

    CTGroupShapeProperties addNewGrpSpPr();

    CTGroupShapeNonVisual addNewNvGrpSpPr();

    CTShape addNewSp();

    CTOfficeArtExtensionList getExtLst();

    CTGroupShape getGrpSpArray(int i);

    CTGroupShape[] getGrpSpArray();

    List<CTGroupShape> getGrpSpList();

    CTGroupShapeProperties getGrpSpPr();

    CTGroupShapeNonVisual getNvGrpSpPr();

    CTShape getSpArray(int i);

    CTShape[] getSpArray();

    List<CTShape> getSpList();

    CTGroupShape insertNewGrpSp(int i);

    CTShape insertNewSp(int i);

    boolean isSetExtLst();

    void removeGrpSp(int i);

    void removeSp(int i);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setGrpSpArray(int i, CTGroupShape cTGroupShape);

    void setGrpSpArray(CTGroupShape[] cTGroupShapeArr);

    void setGrpSpPr(CTGroupShapeProperties cTGroupShapeProperties);

    void setNvGrpSpPr(CTGroupShapeNonVisual cTGroupShapeNonVisual);

    void setSpArray(int i, CTShape cTShape);

    void setSpArray(CTShape[] cTShapeArr);

    int sizeOfGrpSpArray();

    int sizeOfSpArray();

    void unsetExtLst();
}
