package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

/* loaded from: classes10.dex */
public final class ContinueRecord extends StandardRecord {
    public static final short sid = 60;
    private byte[] _data;

    public ContinueRecord(byte[] data) {
        this._data = (byte[]) data.clone();
    }

    public ContinueRecord(ContinueRecord other) {
        super(other);
        this._data = other._data == null ? null : (byte[]) other._data.clone();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    protected int getDataSize() {
        return this._data.length;
    }

    @Override // org.apache.poi.hssf.record.StandardRecord
    public void serialize(LittleEndianOutput out) {
        out.write(this._data);
    }

    public byte[] getData() {
        return this._data;
    }

    @Override // org.apache.poi.hssf.record.Record
    public short getSid() {
        return (short) 60;
    }

    public ContinueRecord(RecordInputStream in) {
        this._data = in.readRemainder();
    }

    @Override // org.apache.poi.hssf.record.StandardRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public ContinueRecord copy() {
        return new ContinueRecord(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CONTINUE;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("data", new Supplier() { // from class: org.apache.poi.hssf.record.ContinueRecord$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ContinueRecord.this.getData();
            }
        });
    }
}
