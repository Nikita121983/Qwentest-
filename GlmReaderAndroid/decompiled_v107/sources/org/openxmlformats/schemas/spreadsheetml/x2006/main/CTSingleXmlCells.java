package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTSingleXmlCells extends XmlObject {
    public static final DocumentFactory<CTSingleXmlCells> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsinglexmlcells5a6btype");
    public static final SchemaType type = Factory.getType();

    CTSingleXmlCell addNewSingleXmlCell();

    CTSingleXmlCell getSingleXmlCellArray(int i);

    CTSingleXmlCell[] getSingleXmlCellArray();

    List<CTSingleXmlCell> getSingleXmlCellList();

    CTSingleXmlCell insertNewSingleXmlCell(int i);

    void removeSingleXmlCell(int i);

    void setSingleXmlCellArray(int i, CTSingleXmlCell cTSingleXmlCell);

    void setSingleXmlCellArray(CTSingleXmlCell[] cTSingleXmlCellArr);

    int sizeOfSingleXmlCellArray();
}
