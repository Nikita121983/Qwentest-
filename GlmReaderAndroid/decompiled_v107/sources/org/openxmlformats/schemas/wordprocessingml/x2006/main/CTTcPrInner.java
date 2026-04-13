package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTTcPrInner extends CTTcPrBase {
    public static final DocumentFactory<CTTcPrInner> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttcprinnerc56dtype");
    public static final SchemaType type = Factory.getType();

    CTTrackChange addNewCellDel();

    CTTrackChange addNewCellIns();

    CTCellMergeTrackChange addNewCellMerge();

    CTTrackChange getCellDel();

    CTTrackChange getCellIns();

    CTCellMergeTrackChange getCellMerge();

    boolean isSetCellDel();

    boolean isSetCellIns();

    boolean isSetCellMerge();

    void setCellDel(CTTrackChange cTTrackChange);

    void setCellIns(CTTrackChange cTTrackChange);

    void setCellMerge(CTCellMergeTrackChange cTCellMergeTrackChange);

    void unsetCellDel();

    void unsetCellIns();

    void unsetCellMerge();
}
