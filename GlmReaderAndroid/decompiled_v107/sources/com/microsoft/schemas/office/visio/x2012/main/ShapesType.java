package com.microsoft.schemas.office.visio.x2012.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes.dex */
public interface ShapesType extends XmlObject {
    public static final DocumentFactory<ShapesType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "shapestypef507type");
    public static final SchemaType type = Factory.getType();

    ShapeSheetType addNewShape();

    ShapeSheetType getShapeArray(int i);

    ShapeSheetType[] getShapeArray();

    List<ShapeSheetType> getShapeList();

    ShapeSheetType insertNewShape(int i);

    void removeShape(int i);

    void setShapeArray(int i, ShapeSheetType shapeSheetType);

    void setShapeArray(ShapeSheetType[] shapeSheetTypeArr);

    int sizeOfShapeArray();
}
