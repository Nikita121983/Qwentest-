package org.apache.poi.hssf.record.common;

import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public interface SharedFeature extends GenericRecord {
    SharedFeature copy();

    int getDataSize();

    void serialize(LittleEndianOutput littleEndianOutput);

    String toString();
}
