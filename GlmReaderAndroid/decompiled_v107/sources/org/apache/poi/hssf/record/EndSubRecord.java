package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public final class EndSubRecord extends SubRecord {
    private static final int ENCODED_SIZE = 0;
    public static final short sid = 0;

    public EndSubRecord() {
    }

    public EndSubRecord(LittleEndianInput in, int size) {
        this(in, size, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EndSubRecord(LittleEndianInput in, int size, int cmoOt) {
        if ((size & 255) != 0) {
            throw new RecordFormatException("Unexpected size (" + size + ")");
        }
    }

    @Override // org.apache.poi.hssf.record.SubRecord
    public boolean isTerminating() {
        return true;
    }

    @Override // org.apache.poi.hssf.record.SubRecord
    public void serialize(LittleEndianOutput out) {
        out.writeShort(0);
        out.writeShort(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.record.SubRecord
    public int getDataSize() {
        return 0;
    }

    public short getSid() {
        return (short) 0;
    }

    @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.Duplicatable
    public EndSubRecord copy() {
        return new EndSubRecord();
    }

    @Override // org.apache.poi.hssf.record.SubRecord, org.apache.poi.common.usermodel.GenericRecord
    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.END;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }
}
