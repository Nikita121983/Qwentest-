package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTCellWatches extends XmlObject {
    public static final DocumentFactory<CTCellWatches> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcellwatches531atype");
    public static final SchemaType type = Factory.getType();

    CTCellWatch addNewCellWatch();

    CTCellWatch getCellWatchArray(int i);

    CTCellWatch[] getCellWatchArray();

    List<CTCellWatch> getCellWatchList();

    CTCellWatch insertNewCellWatch(int i);

    void removeCellWatch(int i);

    void setCellWatchArray(int i, CTCellWatch cTCellWatch);

    void setCellWatchArray(CTCellWatch[] cTCellWatchArr);

    int sizeOfCellWatchArray();
}
