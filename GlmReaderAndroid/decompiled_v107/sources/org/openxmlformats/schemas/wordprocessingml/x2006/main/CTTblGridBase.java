package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTTblGridBase extends XmlObject {
    public static final DocumentFactory<CTTblGridBase> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttblgridbasea11dtype");
    public static final SchemaType type = Factory.getType();

    CTTblGridCol addNewGridCol();

    CTTblGridCol getGridColArray(int i);

    CTTblGridCol[] getGridColArray();

    List<CTTblGridCol> getGridColList();

    CTTblGridCol insertNewGridCol(int i);

    void removeGridCol(int i);

    void setGridColArray(int i, CTTblGridCol cTTblGridCol);

    void setGridColArray(CTTblGridCol[] cTTblGridColArr);

    int sizeOfGridColArray();
}
