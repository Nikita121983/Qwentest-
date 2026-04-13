package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTPath2DList extends XmlObject {
    public static final DocumentFactory<CTPath2DList> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpath2dlistb010type");
    public static final SchemaType type = Factory.getType();

    CTPath2D addNewPath();

    CTPath2D getPathArray(int i);

    CTPath2D[] getPathArray();

    List<CTPath2D> getPathList();

    CTPath2D insertNewPath(int i);

    void removePath(int i);

    void setPathArray(int i, CTPath2D cTPath2D);

    void setPathArray(CTPath2D[] cTPath2DArr);

    int sizeOfPathArray();
}
