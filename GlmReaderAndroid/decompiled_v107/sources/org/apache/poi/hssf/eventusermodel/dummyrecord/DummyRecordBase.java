package org.apache.poi.hssf.eventusermodel.dummyrecord;

import org.apache.poi.hssf.record.Record;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
abstract class DummyRecordBase extends Record {
    @Override // org.apache.poi.hssf.record.Record
    public final short getSid() {
        return (short) -1;
    }

    @Override // org.apache.poi.hssf.record.RecordBase
    public int serialize(int offset, byte[] data) {
        throw new RecordFormatException("Cannot serialize a dummy record");
    }

    @Override // org.apache.poi.hssf.record.RecordBase
    public final int getRecordSize() {
        throw new RecordFormatException("Cannot serialize a dummy record");
    }
}
