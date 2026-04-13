package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTPivotCacheRecords extends XmlObject {
    public static final DocumentFactory<CTPivotCacheRecords> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpivotcacherecords5be1type");
    public static final SchemaType type = Factory.getType();

    CTExtensionList addNewExtLst();

    CTRecord addNewR();

    long getCount();

    CTExtensionList getExtLst();

    CTRecord getRArray(int i);

    CTRecord[] getRArray();

    List<CTRecord> getRList();

    CTRecord insertNewR(int i);

    boolean isSetCount();

    boolean isSetExtLst();

    void removeR(int i);

    void setCount(long j);

    void setExtLst(CTExtensionList cTExtensionList);

    void setRArray(int i, CTRecord cTRecord);

    void setRArray(CTRecord[] cTRecordArr);

    int sizeOfRArray();

    void unsetCount();

    void unsetExtLst();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);
}
